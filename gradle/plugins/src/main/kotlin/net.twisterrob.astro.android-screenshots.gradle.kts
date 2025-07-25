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

plugins {
	id("com.android.compose.screenshot")
}

dependencies {
	"screenshotTestImplementation"(project(":component:test-base-screenshot"))
}

tasks.withType<PreviewScreenshotValidationTask>().configureEach {
	// On CI we want it to "just pass", even if there's a failure.
	// The CI will detect problems based on the JUnit XML report.
	ignoreFailures = isCI.get()
}

// TODEL https://issuetracker.google.com/issues/433076233
tasks.withType<PreviewScreenshotUpdateTask>().configureEach {
	// Reverse @org.gradle.api.tasks.CacheableTask on this task, so Gradle doesn't do FROM-CACHE on it.
	outputs.doNotCacheIf("https://issuetracker.google.com/issues/433076233") { true }
}

android {
	@Suppress("UnstableApiUsage")
	experimentalProperties["android.experimental.enableScreenshotTest"] = true

	testOptions {
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
