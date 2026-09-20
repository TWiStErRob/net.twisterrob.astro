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
	id("net.twisterrob.gradle.plugin.settings") version "0.20"
	id("net.twisterrob.gradle.plugin.nagging") version "0.20"
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
include(":component:test-base-fixtures")
include(":component:test-base-instrumentation")
include(":component:test-base-robolectric")
include(":component:test-base-screenshot")
include(":component:test-base-unit")
include(":component:theme")
includeGroup(":screen")
include(":screen:bazi")
includeGroup(":widget")
include(":widget:bazi-chart")
include(":widget:wuxing-cycle")

fun includeGroup(path: String) {
	include(path)
	val module = project(path)
	module.projectDir = file("modules").resolve(module.projectDir.relativeTo(settings.rootDir))
}

val gradleVersion: String = GradleVersion.current().version

// TODEL detekt 2.0.0-alpha.6 vs Gradle 9.7.0 https://github.com/detekt/detekt/issues/9742
doNotNagAbout(
	"Querying the output of an artifact transform from a task action without declaring it as a task input has been deprecated. " +
		"This is scheduled to be removed in Gradle 10. " +
		"Declare the files or artifacts produced by the configuration using the transform as a task input to properly wire it into the execution plan. " +
		"Consult the upgrading guide for further information: " +
		"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_9.html#undeclared_artifact_transform_input"
)
