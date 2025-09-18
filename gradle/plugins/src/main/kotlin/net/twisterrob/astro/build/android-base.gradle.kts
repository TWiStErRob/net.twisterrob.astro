package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.libs

plugins {
	id("com.android.base")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.plugin.compose")
	id("net.twisterrob.astro.build.kotlin")
	id("net.twisterrob.astro.build.testing-android")
	id("net.twisterrob.astro.build.detekt")
	id("net.twisterrob.astro.build.lint")
}

android {
	val subPackage = project.path
		.replace("-", ".")
		.replace(":", ".")
	namespace = "net.twisterrob.astro${subPackage}"

	resourcePrefix = project.path
		.removePrefix(":")
		.replace("-", "_")
		.replace(":", "_")
		.plus("__")

	compileSdk = libs.versions.android.compileSdk.map(String::toInt).get()
	defaultConfig {
		minSdk = libs.versions.android.minSdk.map(String::toInt).get()

		vectorDrawables {
			useSupportLibrary = true
		}
	}
	buildFeatures {
		compose = true
	}
	packaging {
		resources {
			excludes += "/META-INF/{LICENSE.md,LICENSE-notice.md}"
			excludes += "/META-INF/versions/*/OSGI-INF/MANIFEST.MF"
		}
	}
}
