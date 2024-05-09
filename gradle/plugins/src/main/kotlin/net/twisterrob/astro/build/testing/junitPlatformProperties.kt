package net.twisterrob.astro.build.testing

import org.gradle.api.Project
import java.util.Properties

fun Project.junitPlatformProperties(): Map<String, Any> =
	rootProject.file("config/junit/junit-platform.properties")
		.reader()
		.use { Properties().apply { load(it) } }
		.mapKeys { (k, _) -> k.toString() }
