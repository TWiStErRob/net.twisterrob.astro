package net.twisterrob.astro.build

import io.gitlab.arturbosch.detekt.Detekt
import net.twisterrob.astro.build.dsl.isCI
import net.twisterrob.astro.build.dsl.libs

plugins {
	id("io.gitlab.arturbosch.detekt")
}

detekt {
	ignoreFailures = isCI.get()
	allRules = true
	basePath = rootProject.projectDir.absolutePath

	parallel = true
	config.from(rootProject.file("config/detekt/detekt.yml"))
	plugins.withId("com.android.base") {
		config.from(rootProject.file("config/detekt/detekt-compose.yml"))
	}

	tasks.withType<Detekt>().configureEach {
		// Target version of the generated JVM bytecode. It is used for type resolution.
		jvmTarget = libs.versions.java.target.get()
		reports {
			html.required.set(true) // human
			xml.required.set(true) // checkstyle
			txt.required.set(true) // console
			// https://sarifweb.azurewebsites.net
			sarif.required.set(true) // Github Code Scanning
		}
	}
}

dependencies {
	detektPlugins(libs.detekt.rules.libraries)
	detektPlugins(libs.detekt.rules.composeTwitter)
	detektPlugins(libs.detekt.rules.composeKode)
}
