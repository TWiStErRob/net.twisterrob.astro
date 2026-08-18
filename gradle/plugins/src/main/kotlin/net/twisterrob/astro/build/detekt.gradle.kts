package net.twisterrob.astro.build

import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import net.twisterrob.astro.build.dsl.isCI
import net.twisterrob.astro.build.dsl.libs
import org.gradle.kotlin.dsl.configure

pluginManager.apply("dev.detekt")

extensions.configure<DetektExtension> {
	ignoreFailures = isCI.get()
	allRules = true
	basePath.set(rootProject.layout.projectDirectory)

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
			checkstyle.required.set(true) // checkstyle
			markdown.required.set(true) // console
			// https://sarifweb.azurewebsites.net
			sarif.required.set(true) // Github Code Scanning
		}
	}
}

dependencies {
	add("detektPlugins", libs.detekt.rules.libraries)
	add("detektPlugins", libs.detekt.rules.composeTwitter)
	add("detektPlugins", libs.detekt.rules.composeKode)
}
