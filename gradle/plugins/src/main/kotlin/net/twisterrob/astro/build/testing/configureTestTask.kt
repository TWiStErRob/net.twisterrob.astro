package net.twisterrob.astro.build.testing

import net.twisterrob.astro.build.dsl.isCI
import net.twisterrob.astro.build.dsl.javaToolchains
import net.twisterrob.astro.build.dsl.libs
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.assign

internal fun Project.configureTestTask(task: Test) {
	task.javaLauncher = javaToolchains.launcherFor {
		languageVersion = libs.versions.java.toolchainTest.map(JavaLanguageVersion::of)
	}
	task.ignoreFailures = isCI.get()
	task.useJUnitPlatform()
	task.systemProperties(junitPlatformProperties())
	// Hide (https://stackoverflow.com/a/79098701/253468)
	// > Java HotSpot(TM) 64-Bit Server VM warning:
	// > Sharing is only supported for boot loader classes because bootstrap classpath has been appended
	task.jvmArgs("-Xshare:off")
}
