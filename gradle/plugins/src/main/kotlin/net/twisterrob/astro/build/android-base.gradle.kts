package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.libs

plugins {
	id("com.android.base")
	id("org.jetbrains.kotlin.android")
	id("net.twisterrob.astro.build.kotlin")
	id("net.twisterrob.astro.build.testing-android")
	id("net.twisterrob.astro.build.detekt")
}

android {
	val subPackage = project.path
		.replace("-", ".")
		.replace(":", ".")
	namespace = "net.twisterrob.astro${subPackage}"
	compileSdk = libs.versions.android.compileSdk.get().toInt()

	defaultConfig {
		minSdk = libs.versions.android.minSdk.get().toInt()

		vectorDrawables {
			useSupportLibrary = true
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
