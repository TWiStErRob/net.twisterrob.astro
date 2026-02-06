package net.twisterrob.astro.build

import com.android.build.api.variant.HasUnitTestBuilder
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.androidComponents
import net.twisterrob.astro.build.dsl.isIdeaSync
import net.twisterrob.astro.build.dsl.libs
import net.twisterrob.astro.build.testing.configureTestTask

plugins {
	id("com.android.base")
	id("net.twisterrob.astro.build.testing-fixtures")
}

dependencies {
	"testImplementation"(project(":component:test-base-robolectric"))
	"androidTestImplementation"(project(":component:test-base-instrumentation"))
}

androidComponents {
	beforeVariants { variantBuilder ->
		(variantBuilder as HasUnitTestBuilder).enableUnitTest = variantBuilder.buildType == "debug"
	}
}

android {
	defaultConfig.apply {
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	sourceSets {
		named("test") {
			kotlin.srcDir("src/sharedTest/kotlin")
		}
		if (!isIdeaSync) {
			// Only attach the source folder not during sync,
			// because Android Studio is not capable of handling the same test in multiple sourceSets.
			named("androidTest") {
				kotlin.srcDir("src/sharedTest/kotlin")
			}
		}
	}
	@Suppress("UnstableApiUsage")
	testOptions.apply {
		unitTests {
			isIncludeAndroidResources = true
			all { task ->
				if (task.name.endsWith("ScreenshotTest")) {
					// Handled by net.twisterrob.astro.android-screenshots.gradle.kts
					return@all
				}
				configureTestTask(task)
				// Workaround for this warning/error in modules without any test sources:
				// > > Task :component:compose:testDebugUnitTest
				// > No test executed. This behavior has been deprecated.
				// > This will fail with an error in Gradle 9.0.
				// > There are test sources present but no test was executed.
				// > Please check your test configuration.
				// > Consult the upgrading guide for further information:
				// > https://docs.gradle.org/8.7/userguide/upgrading_version_8.html#test_task_fail_on_no_test_executed
				// The issue is that this file is present on the Test.candidateClassFiles classpath:
				// `build/intermediates/compile_and_runtime_not_namespaced_r_class_jar/debugUnitTest/processDebugUnitTestResources/R.jar`
				// and that makes it look like there are tests, when the test sourceSet folders are empty.
				// Excluding R.jar files will make it run as `:...:testDebugUnitTest NO-SOURCE`.
				task.exclude("R.jar")
			}
		}
		managedDevices {
			val minSdk = libs.versions.android.minSdk.map(String::toInt).get()
			localDevices {
				// ./gradlew pixel7ProApi34DebugAndroidTest
				create("pixel7ProApi${minSdk}") {
					device = "Pixel 7 Pro"
					apiLevel = minSdk
					systemImageSource = "aosp-atd"
					require64Bit = true
				}
			}
			groups {
				// ./gradlew defaultGroupDebugAndroidTest
				create("default") {
					targetDevices.add(localDevices["pixel7ProApi${minSdk}"])
				}
			}
		}
	}
}
