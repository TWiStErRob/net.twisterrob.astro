package net.twisterrob.astro.build

import com.android.build.api.variant.HasUnitTestBuilder
import net.twisterrob.astro.build.dsl.isIdeaSync
import net.twisterrob.astro.build.dsl.libs
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.androidComponents
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
	id("com.android.base")
	id("org.jetbrains.kotlin.android")
	id("net.twisterrob.astro.build.kotlin")
//	id("net.twisterrob.astro.build.testing")
//	id("net.twisterrob.astro.build.detekt")
}

android {
	val subPackage = project.path
		.replace("-", ".")
		.replace(":", ".")
	namespace = "net.twisterrob.astro${subPackage}"
	compileSdk = 34

	defaultConfig {
		minSdk = 34

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}
	@Suppress("UnstableApiUsage")
	testOptions {
		unitTests {
			isIncludeAndroidResources = true
		}
		managedDevices {
			localDevices {
				// ./gradlew pixel7ProApi34DebugAndroidTest
				create("pixel7ProApi34") {
					device = "Pixel 7 Pro"
					apiLevel = 34
					systemImageSource = "aosp-atd"
					require64Bit = true
				}
			}
			groups {
				// ./gradlew defaultGroupDebugAndroidTest
				create("default") {
					targetDevices.add(devices["pixel7ProApi34"])
				}
			}
		}
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
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.kotlin.composeCompiler.get()
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

androidComponents {
	beforeVariants { variantBuilder ->
		(variantBuilder as HasUnitTestBuilder).enableUnitTest = variantBuilder.buildType == "debug"
	}
}

kotlin {
	explicitApi = ExplicitApiMode.Strict
	jvmToolchain(libs.versions.java.toolchain.get().toInt())
}
