// REPORT Need to make it an opt-in plugin, because it can't handle NO-SOURCES.
// > Task :*:validateDebugScreenshotTest (when there are no `src/screenshotTest` sources)
// No test executed. This behavior has been deprecated. This will fail with an error in Gradle 9.0.
// There are test sources present but no test was executed. Please check your test configuration.
// Consult the upgrading guide for further information:
// https://docs.gradle.org/8.8-rc-1/userguide/upgrading_version_8.html#test_task_fail_on_no_test_executed

@file:Suppress("detekt.MaxLineLength")

import com.android.build.api.variant.HasHostTests
import com.android.compose.screenshot.tasks.PreviewScreenshotValidationTask
import com.android.compose.screenshot.tasks.ScreenshotTestReportTask
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.androidComponents
import net.twisterrob.astro.build.dsl.isCI
import org.gradle.api.internal.exceptions.MarkedVerificationException
import org.gradle.internal.logging.ConsoleRenderer
import org.gradle.kotlin.dsl.support.serviceOf
import org.gradle.tooling.events.FinishEvent
import org.gradle.tooling.events.OperationCompletionListener
import org.gradle.tooling.events.task.TaskFailureResult
import org.gradle.tooling.events.task.TaskFinishEvent

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

android {
	@Suppress("UnstableApiUsage")
	experimentalProperties["android.experimental.enableScreenshotTest"] = true

	testOptions {
		unitTests {
			isIncludeAndroidResources = true
			all { task ->
				if (task.name.endsWith("ScreenshotTest")) {
					// REPORT Disabling the test task.
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
					task.enabled = false
				}
			}
		}
	}
}

// REPORT Polyfill user friendly behavior:
// No-one can read binary result files or wants to read XML for a stack trace of screenshot failures.
// Instead, provide a clickable link to the HTML report which includes the visual diff.
// The original failure looks like this (it'll still show up in the console):
// > > Task :...:validateDebugScreenshotTest
// >
// > FAILURE: Build failed with an exception.
// >
// > * What went wrong:
// > Execution failed for task ':...:validateDebugScreenshotTest'.
// > > There were failing tests. See the results at: file:///.../build/test-results/validateDebugScreenshotTest/
// This workaround adds an additional failure:
// > Execution failed for task ':...:debugScreenshotReport'.
// > > There were failing tests. See the report at: file:///.../build/reports/screenshotTest/preview/debug/index.html
androidComponents.onVariants { variant ->
	if (variant !is HasHostTests || !variant.debuggable) {
		// The tasks we're looking for don't exist here (see PreviewScreenshotGradlePlugin), so just stop.
		return@onVariants
	}

	val taskStateService = project.gradle.sharedServices
		.registerIfAbsent("taskState", TaskStateService::class.java) { }
	serviceOf<BuildEventsListenerRegistry>().onTaskCompletion(taskStateService)
	val validateTaskPath = "${project.path}:validate${variant.name.replaceFirstChar { it.uppercase() }}ScreenshotTest"
	val reportTaskName = "${variant.name}ScreenshotReport"
	val reportTask = tasks.named<ScreenshotTestReportTask>(reportTaskName)

	reportTask.configure {
		usesService(taskStateService)
		doLast {
			val isValidateFailed = taskStateService.get().isFailed(validateTaskPath)
			if (isValidateFailed) {
				// Mimic what org.gradle.api.tasks.testing.AbstractTestTask.handleTestFailures does.
				val report = this@configure.outputDir.file("index.html").get().asFile
				val reportUrl = ConsoleRenderer().asClickableFileUrl(report)
				throw MarkedVerificationException("There were failing tests. See the report at: ${reportUrl}")
			}
		}
	}
}

abstract class TaskStateService : BuildService<BuildServiceParameters.None>, OperationCompletionListener {
	private val state: MutableMap<String, TaskFinishEvent> = mutableMapOf()

	fun isFailed(taskPath: String): Boolean {
		val state = state[taskPath] ?: error("Task ${taskPath} is not complete yet.")
		return state.result is TaskFailureResult
	}

	override fun onFinish(event: FinishEvent) {
		if (event is TaskFinishEvent) {
			state[event.descriptor.taskPath] = event
		}
	}
}
