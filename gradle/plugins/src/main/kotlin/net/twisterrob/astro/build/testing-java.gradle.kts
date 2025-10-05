package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.libs
import net.twisterrob.astro.build.testing.configureTestTask

plugins {
	id("org.gradle.java")
	id("org.gradle.java-test-fixtures")
	id("net.twisterrob.astro.build.testing-fixtures")
}

@Suppress("UnstableApiUsage")
testing.suites.withType<JvmTestSuite>().configureEach {
	useJUnitJupiter(libs.versions.junit.jupiter)

	dependencies {
		implementation(project(":component:test-base-unit"))
	}

	targets.configureEach {
		testTask.configure { configureTestTask(this) }
	}
}
