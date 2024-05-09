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

dependencies {
	"testImplementation"(project(":test-helpers"))
	"androidTestImplementation"(project(":test-helpers"))
}

androidComponents {
	beforeVariants { variantBuilder ->
		(variantBuilder as HasUnitTestBuilder).enableUnitTest = variantBuilder.buildType == "debug"
	}
}

android {
	defaultConfig {
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
	testOptions {
		unitTests {
			isIncludeAndroidResources = true
			all {
				configureTestTask(it)
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
