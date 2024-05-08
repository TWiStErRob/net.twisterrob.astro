plugins {
	id("com.android.application")
//	id("net.twisterrob.astro.build.kotlin")
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
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}
	testOptions {
		unitTests {
			isIncludeAndroidResources = true
		}
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
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
//	kotlinOptions {
//		jvmTarget = "1.8"
//	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}
