import net.twisterrob.astro.build.dsl.libs

plugins {
	id("com.android.application")
	id("net.twisterrob.astro.build.android-base")
}

android {
	defaultConfig {
		targetSdk = libs.versions.android.targetSdk.get().toInt()
	}
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
}
