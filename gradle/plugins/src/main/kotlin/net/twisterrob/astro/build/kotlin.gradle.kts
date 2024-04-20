package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.libs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.jetbrains.kotlin.jvm")
}

kotlin {
	jvmToolchain(libs.versions.java.toolchain.get().toInt())
}

dependencies {
	implementation(libs.kotlin.stdlib)
}

// TODO kotlin.target.compilerOptions { ... }
tasks.withType<KotlinCompile>().configureEach {
	compilerOptions {
		allWarningsAsErrors = true
		verbose = true
		freeCompilerArgs.add("-opt-in=kotlin.ExperimentalStdlibApi")
		freeCompilerArgs.add("-Xcontext-receivers")
	}
}
