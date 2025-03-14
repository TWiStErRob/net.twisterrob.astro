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
	id("net.twisterrob.gradle.plugin.settings") version "0.17"
	id("org.gradle.toolchains.foojay-resolver-convention") version ("0.9.0")
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

fun includeGroup(path: String) {
	include(path)
	val module = project(path)
	module.projectDir = file("modules").resolve(module.projectDir.relativeTo(settings.rootDir))
}
