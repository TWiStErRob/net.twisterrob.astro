import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
	id("org.gradle.java-library")
	id("net.twisterrob.astro.build.kotlin")
	id("net.twisterrob.astro.build.testing")
	id("net.twisterrob.astro.build.detekt")
}

kotlin {
	explicitApi = ExplicitApiMode.Strict
}
