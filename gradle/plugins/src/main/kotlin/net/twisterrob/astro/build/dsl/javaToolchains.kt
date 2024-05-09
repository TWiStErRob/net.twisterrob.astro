package net.twisterrob.astro.build.dsl

import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaToolchainService

internal val Project.javaToolchains: JavaToolchainService
	get() = extensions.getByName("javaToolchains") as JavaToolchainService
