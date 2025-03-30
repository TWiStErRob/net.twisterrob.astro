package net.twisterrob.astro

import org.gradle.api.Plugin
import org.gradle.api.Project

class LibPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		target.pluginManager.apply("com.android.library")
		target.pluginManager.apply("net.twisterrob.astro.build.android-base")
	}
}
