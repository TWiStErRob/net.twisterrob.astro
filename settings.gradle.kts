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

val gradleVersion: String = GradleVersion.current().version

// TODEL Gradle 8.14 vs AGP 8.9 https://issuetracker.google.com/issues/408334529
@Suppress("detekt.MaxLineLength")
doNotNagAbout(
	"Retrieving attribute with a null key. " +
			"This behavior has been deprecated. " +
			"This will fail with an error in Gradle 10. " +
			"Don't request attributes from attribute containers using null keys. " +
			"Consult the upgrading guide for further information: " +
			"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_8.html#null-attribute-lookup",
	"at com.android.build.gradle.internal.ide.dependencies.ArtifactUtils.isAndroidProjectDependency(ArtifactUtils.kt:539)",
)

// TODEL Gradle 8.5 vs AGP 8.10.1 https://issuetracker.google.com/issues/316308499
@Suppress("detekt.MaxLineLength")
doNotNagAbout(
	"The StartParameter.isConfigurationCacheRequested property has been deprecated. " +
			"This is scheduled to be removed in Gradle 10. " +
			"Please use 'configurationCache.requested' property on 'BuildFeatures' service instead. " +
			"Consult the upgrading guide for further information: " +
			"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_8.html#deprecated_startparameter_is_configuration_cache_requested",
	"at com.android.build.gradle.internal.attribution.BuildAnalyzerConfiguratorService\$initBuildAnalyzerService$1.execute(BuildAnalyzerConfiguratorService.kt:133)",
)
