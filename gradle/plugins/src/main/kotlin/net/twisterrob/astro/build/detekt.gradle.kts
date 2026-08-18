package net.twisterrob.astro.build

import dev.detekt.gradle.Detekt
import net.twisterrob.astro.build.dsl.isCI
import net.twisterrob.astro.build.dsl.libs

plugins {
	id("dev.detekt")
}

detekt {
	ignoreFailures = isCI.get()
	allRules = true
	basePath = rootProject.layout.projectDirectory

	parallel = true
	config.from(rootProject.file("config/detekt/detekt.yml"))
	plugins.withId("com.android.base") {
		config.from(rootProject.file("config/detekt/detekt-compose.yml"))
	}

	tasks.withType<Detekt>().configureEach {
		// Target version of the generated JVM bytecode. It is used for type resolution.
		jvmTarget = libs.versions.java.target.get()
		reports {
			html.required = true // human
			xml.required = true // checkstyle
			txt.required = true // console
			// https://sarifweb.azurewebsites.net
			sarif.required = true // Github Code Scanning
		}
	}
}

dependencies {
	detektPlugins(libs.detekt.rules.libraries)
	detektPlugins(libs.detekt.rules.composeTwitter)
	detektPlugins(libs.detekt.rules.composeKode)
}
