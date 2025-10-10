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
		languageVersion = libs.versions.java.toolchainHostTest.map(JavaLanguageVersion::of)
	}
	task.ignoreFailures = isCI.get()
	task.useJUnitPlatform()
	task.systemProperties(junitPlatformProperties())
	// Hide (https://stackoverflow.com/a/79098701/253468)
	// > Java HotSpot(TM) 64-Bit Server VM warning:
	// > Sharing is only supported for boot loader classes because bootstrap classpath has been appended
	task.jvmArgs("-Xshare:off")

	// See org.mockito.Mockito JavaDoc, section 0.3 in 5.20.0.
	val mockito = task.classpath.elements.map { files ->
		files.single { it.asFile.name.startsWith("mockito-core-") }
	}
	task.jvmArgumentProviders.add { listOf("-javaagent:${mockito.get().asFile}") }

	// > WARNING: A restricted method in java.lang.System has been called
	// > WARNING: java.lang.System::load has been called by org.conscrypt.NativeLibraryUtil in an unnamed module
	// > (${GRADLE_USER_HOME}/caches/modules-2/files-2.1/org.conscrypt/conscrypt-openjdk-uber/2.5.2/
	// > d858f142ea189c62771c505a6548d8606ac098fe/conscrypt-openjdk-uber-2.5.2.jar)
	// > WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
	// > WARNING: Restricted methods will be blocked in a future release unless native access is enabled
	task.jvmArgs("--enable-native-access=ALL-UNNAMED")
}
