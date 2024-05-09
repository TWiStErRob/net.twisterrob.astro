import net.twisterrob.astro.build.dsl.libs

plugins {
	id("com.android.application")
	id("net.twisterrob.astro.build.android-base")
}

android {
	defaultConfig {
		targetSdk = libs.versions.android.targetSdk.get().toInt()
	}
}
