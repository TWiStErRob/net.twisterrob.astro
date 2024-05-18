@file:Suppress("detekt.MaxLineLength")

package net.twisterrob.astro.build

import com.android.build.api.variant.HasUnitTestBuilder
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.androidComponents
import net.twisterrob.astro.build.dsl.isIdeaSync
import net.twisterrob.astro.build.dsl.libs
import net.twisterrob.astro.build.testing.configureTestTask

plugins {
	id("com.android.base")
}
// REPORT Imperatively applying it, because putting it just above in the plugins block would fail with:
// > > Task :plugins:generatePrecompiledScriptPluginAccessors FAILED
// > 
// > FAILURE: Build failed with an exception.
// > 
// > * Where:
// > Precompiled script plugin 'gradle\plugins\src\main\kotlin\net\twisterrob\astro\build\android-base.gradle.kts' line: 1
// > 
// > * What went wrong:
// > An exception occurred applying plugin request [id: 'com.android.compose.screenshot']
// > > Failed to apply plugin 'com.android.compose.screenshot'.
// >    > Extension of type 'AndroidComponentsExtension' does not exist. Currently registered extension types:
// >      [ExtraPropertiesExtension, VersionCatalogsExtension, KotlinAndroidProjectExtension, KotlinTestsRegistry]
//
// Moving it outside to android-library.gradle.kts gets through that failure, but then it fails with:
// >  > Task :plugins:generatePrecompiledScriptPluginAccessors FAILED
// > 
// > FAILURE: Build failed with an exception.
// > 
// > * Where:
// > Precompiled script plugin 'gradle\plugins\src\main\kotlin\net.twisterrob.astro.android-library.gradle.kts' line: 1
// > 
// > * What went wrong:
// > An exception occurred applying plugin request [id: 'com.android.compose.screenshot']
// > > Failed to apply plugin 'com.android.compose.screenshot'.
// >    > Please enable screenshotTest source set first to apply the screenshot test plugin.
// >      Add "android.experimental.enableScreenshotTest=true" to gradle.properties
// adding `android.experimental.enableScreenshotTest=true` to gradle/plugins/gradle.properties does not help.
apply(plugin = "com.android.compose.screenshot")

dependencies {
	"testImplementation"(project(":component:test-base-robolectric"))
	"androidTestImplementation"(project(":component:test-base-instrumentation"))
	"screenshotTestImplementation"(project(":component:test-base-screenshot"))
}

androidComponents {
	beforeVariants { variantBuilder ->
		(variantBuilder as HasUnitTestBuilder).enableUnitTest = variantBuilder.buildType == "debug"
	}
}

android {
	defaultConfig {
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		@Suppress("UnstableApiUsage")
		experimentalProperties["android.experimental.enableScreenshotTest"] = true
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
	testOptions {
		unitTests {
			isIncludeAndroidResources = true
			all { task ->
				if (task.name.endsWith("ScreenshotTest")) {
					// REPORT Disabling the test task.
					// It's not actually needed for screenshot testing (validateDebugScreenshotTest is a Test task).
					// Disabling it because it would trigger the following warnings:
					// > > Task :*:testReleaseScreenshotTest
					//
					// > The automatic loading of test framework implementation dependencies has been deprecated.
					// > This is scheduled to be removed in Gradle 9.0.
					// > Declare the desired test framework directly on the test suite or explicitly declare the test framework implementation dependencies on the test's runtime classpath.
					// > Consult the upgrading guide for further information:
					// > https://docs.gradle.org/8.7/userguide/upgrading_version_8.html#test_framework_implementation_dependencies
					// 
					// > No test executed. This behavior has been deprecated.
					// > This will fail with an error in Gradle 9.0.
					// > There are test sources present but no test was executed. Please check your test configuration.
					// > Consult the upgrading guide for further information:
					// > https://docs.gradle.org/8.7/userguide/upgrading_version_8.html#test_task_fail_on_no_test_executed
					task.enabled = false
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
			val minSdk = libs.versions.android.minSdk.get().toInt()
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
					targetDevices.add(devices["pixel7ProApi${minSdk}"])
				}
			}
		}
	}
}
