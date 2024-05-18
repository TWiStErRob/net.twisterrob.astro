// REPORT Need to make it an opt-in plugin, because it can't handle NO-SOURCES.
// > Task :*:validateDebugScreenshotTest (when there are no `src/screenshotTest` sources)
// No test executed. This behavior has been deprecated. This will fail with an error in Gradle 9.0.
// There are test sources present but no test was executed. Please check your test configuration.
// Consult the upgrading guide for further information:
// https://docs.gradle.org/8.8-rc-1/userguide/upgrading_version_8.html#test_task_fail_on_no_test_executed

@file:Suppress("detekt.MaxLineLength")

import net.twisterrob.astro.build.dsl.android

// REPORT Imperatively applying it, because putting it just above in the plugins block would fail with:
// > > Task :plugins:generatePrecompiledScriptPluginAccessors FAILED
// > 
// > FAILURE: Build failed with an exception.
// > 
// > * Where:
// > Precompiled script plugin 'gradle\plugins\src\main\kotlin\net\twisterrob\astro\build\android-base.gradle.kts' line: 1
// > 
// > * What went wrong:
// > An exception occurred applying plugin request [id: 'com.android.compose.screenshot']
// > > Failed to apply plugin 'com.android.compose.screenshot'.
// >    > Extension of type 'AndroidComponentsExtension' does not exist. Currently registered extension types:
// >      [ExtraPropertiesExtension, VersionCatalogsExtension, KotlinAndroidProjectExtension, KotlinTestsRegistry]
//
// Moving it outside to android-library.gradle.kts gets through that failure, but then it fails with:
// >  > Task :plugins:generatePrecompiledScriptPluginAccessors FAILED
// > 
// > FAILURE: Build failed with an exception.
// > 
// > * Where:
// > Precompiled script plugin 'gradle\plugins\src\main\kotlin\net.twisterrob.astro.android-library.gradle.kts' line: 1
// > 
// > * What went wrong:
// > An exception occurred applying plugin request [id: 'com.android.compose.screenshot']
// > > Failed to apply plugin 'com.android.compose.screenshot'.
// >    > Please enable screenshotTest source set first to apply the screenshot test plugin.
// >      Add "android.experimental.enableScreenshotTest=true" to gradle.properties
// adding `android.experimental.enableScreenshotTest=true` to gradle/plugins/gradle.properties does not help.
apply(plugin = "com.android.compose.screenshot")

dependencies {
	"screenshotTestImplementation"(project(":component:test-base-screenshot"))
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
