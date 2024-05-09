package net.twisterrob.astro.build.testing

import net.twisterrob.astro.build.dsl.isCI
import net.twisterrob.astro.build.dsl.javaToolchains
import net.twisterrob.astro.build.dsl.libs
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JavaLanguageVersion

internal fun Project.configureTestTask(task: Test) {
	task.javaLauncher.set(javaToolchains.launcherFor {
		languageVersion.set(JavaLanguageVersion.of(libs.versions.java.toolchainTest.get()))
	})
	task.ignoreFailures = isCI.get()
	task.systemProperties(junitPlatformProperties())
}
