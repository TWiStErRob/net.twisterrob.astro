package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.kotlin
import net.twisterrob.astro.build.dsl.libs
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	// One of these is implied.
	// id("org.jetbrains.kotlin.jvm")
	// id("org.jetbrains.kotlin.android")
}

dependencies {
	"implementation"(libs.kotlin.stdlib)
}

kotlin {
	explicitApi = ExplicitApiMode.Strict
	jvmToolchain(libs.versions.java.toolchain.map(String::toInt).get())
}

// TODO kotlin.target.compilerOptions { ... }
tasks.withType<KotlinCompile>().configureEach {
	compilerOptions {
		allWarningsAsErrors = true
		verbose = true
		freeCompilerArgs.add("-opt-in=kotlin.ExperimentalStdlibApi")
		freeCompilerArgs.add("-Xcontext-parameters")
	}
}
