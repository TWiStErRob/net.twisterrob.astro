package net.twisterrob.astro

import com.android.build.api.dsl.ApplicationExtension
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AppPlugin : Plugin<Project> {
	override fun apply(target: Project) {

		target.pluginManager.apply("com.android.application")
		target.pluginManager.apply("net.twisterrob.astro.build.android-base")

		target.android {
			this as ApplicationExtension
			defaultConfig {
				targetSdk = target.libs.versions.android.targetSdk.map(String::toInt).get()
			}
			buildTypes {
				debug {
					applicationIdSuffix = ".debug"
				}
			}
			packaging {
				// TODEL workaround for https://issuetracker.google.com/issues/353554169
				jniLibs.keepDebugSymbols.add("**/libandroidx.graphics.path.so")
			}
		}
	}
}
