import net.twisterrob.gradle.settings.enableFeaturePreviewQuietly

// TODEL https://github.com/gradle/gradle/issues/18971
rootProject.name = "net-twisterrob-astro"

pluginManagement {
	includeBuild("gradle/plugins")
}

plugins {
	id("net.twisterrob.gradle.plugin.settings") version "0.17"
	id("org.gradle.toolchains.foojay-resolver-convention") version ("0.8.0")
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
	repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
	repositories {
		mavenCentral()
	}
}

enableFeaturePreviewQuietly("TYPESAFE_PROJECT_ACCESSORS", "Type-safe project accessors")

includeModule(":bazi-model")
includeModule(":bazi-calculator")
includeModule(":bazi-calculator-group44")
includeModule(":bazi-calculator-manual")
includeModule(":bazi-calculator-wikipedia")
includeModule(":test-helpers")

fun includeModule(path: String) {
	include(path)
	val module = project(path)
	module.projectDir = file("modules").resolve(module.projectDir.relativeTo(settings.rootDir))
}
