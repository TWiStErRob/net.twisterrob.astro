import com.android.build.gradle.ProguardFiles.getDefaultProguardFile

plugins {
	id("com.android.application")
	id("net.twisterrob.astro.build.android-base")
}

android {
	defaultConfig {
		targetSdk = 34
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
