// REPORT Need to make it an opt-in plugin, because it can't handle NO-SOURCES.
// > Task :*:validateDebugScreenshotTest (when there are no `src/screenshotTest` sources)
// No test executed. This behavior has been deprecated. This will fail with an error in Gradle 9.0.
// There are test sources present but no test was executed. Please check your test configuration.
// Consult the upgrading guide for further information:
// https://docs.gradle.org/8.8-rc-1/userguide/upgrading_version_8.html#test_task_fail_on_no_test_executed

@file:Suppress("detekt.MaxLineLength")

import com.android.compose.screenshot.tasks.PreviewScreenshotUpdateTask
import com.android.compose.screenshot.tasks.PreviewScreenshotValidationTask
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.isCI
import net.twisterrob.astro.build.dsl.libs

plugins {
	id("com.android.compose.screenshot")
	id("org.gradle.jvm-toolchains")
}

dependencies {
	"screenshotTestImplementation"(project(":component:test-base-screenshot"))
}

tasks.withType<PreviewScreenshotValidationTask>().configureEach {
	// On CI we want it to "just pass", even if there's a failure.
	// The CI will detect problems based on the JUnit XML report.
	ignoreFailures = isCI.get()
}

tasks.withType<Test>().configureEach {
	if (this !is PreviewScreenshotValidationTask && this !is PreviewScreenshotUpdateTask) {
		return@configureEach
	}
	javaLauncher = javaToolchains.launcherFor {
		languageVersion = libs.versions.java.toolchainScreenshotTest.map(JavaLanguageVersion::of)
	}
	// TODEL https://issuetracker.google.com/issues/482314257
	// > A restricted method in java.lang.System has been called
	// > java.lang.System::load has been called by com.android.layoutlib.bridge.Bridge in an unnamed module
	// > (file:/${GRADLE_USER_HOME}/caches/modules-2/files-2.1/com.android.tools.layoutlib/layoutlib/16.1.0-jdk17/76fd67db45d54e91c0a424445a1e4e8f6270a7b5/layoutlib-16.1.0-jdk17.jar)
	// > Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
	// > Restricted methods will be blocked in a future release unless native access is enabled
	jvmArgs("--enable-native-access=ALL-UNNAMED")
	// TODEL https://issuetracker.google.com/issues/482343977
	// > A terminally deprecated method in sun.misc.Unsafe has been called
	// > sun.misc.Unsafe::objectFieldOffset has been called by com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper
	// > (file:/${GRADLE_USER_HOME}/caches/modules-2/files-2.1/com.android.tools.compose/compose-preview-renderer/0.0.1-alpha13/5d804413a73f74cdb6204ff0937bcf713231aca5/compose-preview-renderer-0.0.1-alpha13.jar)
	// > Please consider reporting this to the maintainers of class com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper
	// > sun.misc.Unsafe::objectFieldOffset will be removed in a future release
	jvmArgs("--sun-misc-unsafe-memory-access=allow")
}

android {
	@Suppress("UnstableApiUsage")
	experimentalProperties["android.experimental.enableScreenshotTest"] = true

	testOptions.apply {
		unitTests {
			all { task ->
				if (task.name.endsWith("ScreenshotTest")) {
					// REPORT Disabling the test task, in case someone explicitly executes it.
					// It's not actually needed for screenshot testing (validateDebugScreenshotTest is a Test task).
					// Disabling it because it would trigger the following warnings:
					// > > Task :*:testReleaseScreenshotTest
					//
					// > The automatic loading of test framework implementation dependencies has been deprecated.
					// > This is scheduled to be removed in Gradle 9.0.
					// > Declare the desired test framework directly on the test suite or explicitly declare the test framework implementation dependencies on the test's runtime classpath.
					// > Consult the upgrading guide for further information:
					// > https://docs.gradle.org/8.7/userguide/upgrading_version_8.html#test_framework_implementation_dependencies
					// 
					// > No test executed. This behavior has been deprecated.
					// > This will fail with an error in Gradle 9.0.
					// > There are test sources present but no test was executed. Please check your test configuration.
					// > Consult the upgrading guide for further information:
					// > https://docs.gradle.org/8.7/userguide/upgrading_version_8.html#test_task_fail_on_no_test_executed

					// Gradle 9:
					// > There are test sources present and no filters are applied, 
					// > but the test task did not discover any tests to execute. 
					// > This is likely due to a misconfiguration. 
					// > Please check your test configuration. 
					// > If this is not a misconfiguration, 
					// > this error can be disabled by setting the 'failOnNoDiscoveredTests' property to false.
					task.enabled = false
				}
			}
		}
	}
}
