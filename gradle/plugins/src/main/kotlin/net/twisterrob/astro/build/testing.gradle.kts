package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.isCI
import net.twisterrob.astro.build.dsl.libs
import java.util.Properties

plugins {
	id("org.gradle.java")
	id("org.gradle.java-test-fixtures")
}

@Suppress("UnstableApiUsage")
testing.suites.withType<JvmTestSuite>().configureEach {
	useJUnitJupiter(libs.versions.junit.jupiter)

	dependencies {
		implementation(project(":component:test-base-unit"))
	}

	targets.configureEach {
		testTask.configure {
			javaLauncher.set(javaToolchains.launcherFor {
				languageVersion.set(JavaLanguageVersion.of(libs.versions.java.toolchainTest.get()))
			})
			ignoreFailures = isCI.get()
			systemProperties(
				rootProject.file("config/junit/junit-platform.properties")
					.reader()
					.use { Properties().apply { load(it) } }
					.mapKeys { (k, _) -> k.toString() }
			)
		}
	}
}

dependencies {
	testFixturesImplementation(project(":component:test-base-unit"))
}
