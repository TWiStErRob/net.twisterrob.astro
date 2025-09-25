import net.twisterrob.gradle.doNotNagAbout
import net.twisterrob.gradle.settings.enableFeaturePreviewQuietly

// TODEL https://github.com/gradle/gradle/issues/18971
rootProject.name = "net-twisterrob-astro"

pluginManagement {
	includeBuild("gradle/plugins")
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
}

plugins {
	id("net.twisterrob.gradle.plugin.settings") version "0.18"
	id("net.twisterrob.gradle.plugin.nagging") version "0.18"
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
	repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
	repositories {
		google()
		mavenCentral()
		exclusiveContent {
			forRepository {
				maven {
					// Using the pre-release repository for latest Kotlin support.
					name = "compose-compiler"
					url = uri("https://androidx.dev/storage/compose-compiler/repository/")
				}
			}
			filter {
				includeVersionByRegex("androidx.compose.compiler", "compiler", """\d+\.\d+\.\d+-dev-.*""")
			}
		}
	}
}

enableFeaturePreviewQuietly("TYPESAFE_PROJECT_ACCESSORS", "Type-safe project accessors")

includeGroup(":app")
include(":app:bazi")
includeGroup(":feature")
include(":feature:main")
includeGroup(":domain")
include(":domain:bazi-model")
include(":domain:bazi-calculator")
include(":domain:bazi-calculator:base")
include(":domain:bazi-calculator:group44")
include(":domain:bazi-calculator:solar")
include(":domain:bazi-calculator:wikipedia")
includeGroup(":component")
include(":component:compose")
include(":component:data")
include(":component:test-base-compose")
include(":component:test-base-instrumentation")
include(":component:test-base-robolectric")
include(":component:test-base-screenshot")
include(":component:test-base-unit")
include(":component:theme")
includeGroup(":screen")
include(":screen:bazi")
includeGroup(":widget")
include(":widget:greeting")
include(":widget:bazi-chart")
include(":widget:wuxing-cycle")

fun includeGroup(path: String) {
	include(path)
	val module = project(path)
	module.projectDir = file("modules").resolve(module.projectDir.relativeTo(settings.rootDir))
}

val gradleVersion: String = GradleVersion.current().version

// TODEL Gradle 9.1 vs detekt 1.23.8 https://github.com/detekt/detekt/issues/8452
@Suppress("detekt.MaxLineLength")
doNotNagAbout(
	"The ReportingExtension.file(String) method has been deprecated. " +
			"This is scheduled to be removed in Gradle 10. " +
			"Please use the getBaseDirectory().file(String) or getBaseDirectory().dir(String) method instead. " +
			"Consult the upgrading guide for further information: " +
			"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_9.html#reporting_extension_file",
	"at io.gitlab.arturbosch.detekt.DetektPlugin.apply(DetektPlugin.kt:28)",
)

// TODEL Gradle 9.1 vs com.android.compose.screenshot 0.0.1-alpha11 https://issuetracker.google.com/issues/444260626
@Suppress("detekt.MaxLineLength")
doNotNagAbout(
	Regex(
		"Declaring dependencies using multi-string notation has been deprecated. ".escape() +
				"This will fail with an error in Gradle 10. ".escape() +
				"Please use single-string notation instead: ".escape() +
				"\"${"com.android.tools.layoutlib:layoutlib-runtime:".escape()}\\d+\\.\\d+\\.\\d+${"\". ".escape()}" +
				"Consult the upgrading guide for further information: ".escape() +
				"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_9.html#dependency_multi_string_notation".escape() +
				".*",
	),
	//"at com.android.compose.screenshot.layoutlibExtractor.LayoutlibDataFromMaven\$Companion.create(LayoutlibDataFromMaven.kt:52)",
)

// TODEL Gradle 9.1 vs AGP 8.13 https://issuetracker.google.com/issues/444260628
@Suppress("detekt.MaxLineLength")
doNotNagAbout(
	Regex(
		"Declaring dependencies using multi-string notation has been deprecated. ".escape() +
				"This will fail with an error in Gradle 10. ".escape() +
				"Please use single-string notation instead: ".escape() +
				"\"${"com.android.tools.build:aapt2:".escape()}\\d+\\.\\d+\\.\\d+-\\d+:(windows|linux|osx)${"\". ".escape()}" +
				"Consult the upgrading guide for further information: ".escape() +
				"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_9.html#dependency_multi_string_notation".escape() +
				".*",
	),
	//"at com.android.build.gradle.internal.res.Aapt2FromMaven\$Companion.create(Aapt2FromMaven.kt:139)",
)

// TODEL Gradle 9.1 vs AGP 8.13 https://issuetracker.google.com/issues/444260628
@Suppress("detekt.MaxLineLength")
doNotNagAbout(
	Regex(
		"Declaring dependencies using multi-string notation has been deprecated. ".escape() +
				"This will fail with an error in Gradle 10. ".escape() +
				"Please use single-string notation instead: ".escape() +
				"\"${"com.android.tools.lint:lint-gradle:".escape()}\\d+\\.\\d+\\.\\d+${"\". ".escape()}" +
				"Consult the upgrading guide for further information: ".escape() +
				"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_9.html#dependency_multi_string_notation".escape() +
				".*",
	),
	//"at com.android.build.gradle.internal.lint.LintFromMaven\$Companion.from(AndroidLintInputs.kt:2850)",
)

private fun String.escape(): String = Regex.escape(this)
