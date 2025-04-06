import net.twisterrob.gradle.doNotNagAbout
import net.twisterrob.gradle.isDoNotNagAboutDiagnosticsEnabled
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
	id("net.twisterrob.gradle.plugin.nagging") version "0.17"
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

val gradleVersion = GradleVersion.current().version

// TODEL https://issuetracker.google.com/issues/370546370
// See also https://github.com/gradle/gradle/issues/32422
@Suppress("MaxLineLength")
doNotNagAbout(
	"Declaring an 'is-' property with a Boolean type has been deprecated. " +
			"Starting with Gradle 9.0, this property will be ignored by Gradle. " +
			"The combination of method name and return type is not consistent with Java Bean property rules and will become unsupported in future versions of Groovy. " +
			"Add a method named 'getCrunchPngs' with the same behavior and mark the old one with @Deprecated, " +
			"or change the type of 'com.android.build.gradle.internal.dsl.BuildType\$AgpDecorated.isCrunchPngs' (and the setter) to 'boolean'. " +
			"Consult the upgrading guide for further information: " +
			"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_8.html#groovy_boolean_properties",
)
@Suppress("MaxLineLength")
doNotNagAbout(
	"Declaring an 'is-' property with a Boolean type has been deprecated. " +
			"Starting with Gradle 9.0, this property will be ignored by Gradle. " +
			"The combination of method name and return type is not consistent with Java Bean property rules and will become unsupported in future versions of Groovy. " +
			"Add a method named 'getUseProguard' with the same behavior and mark the old one with @Deprecated, " +
			"or change the type of 'com.android.build.gradle.internal.dsl.BuildType.isUseProguard' (and the setter) to 'boolean'. " +
			"Consult the upgrading guide for further information: " +
			"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_8.html#groovy_boolean_properties",
)
@Suppress("MaxLineLength")
doNotNagAbout(
	"Declaring an 'is-' property with a Boolean type has been deprecated. " +
			"Starting with Gradle 9.0, this property will be ignored by Gradle. " +
			"The combination of method name and return type is not consistent with Java Bean property rules and will become unsupported in future versions of Groovy. " +
			"Add a method named 'getWearAppUnbundled' with the same behavior and mark the old one with @Deprecated, " +
			"or change the type of 'com.android.build.api.variant.impl.ApplicationVariantImpl.isWearAppUnbundled' (and the setter) to 'boolean'. " +
			"Consult the upgrading guide for further information: " +
			"https://docs.gradle.org/${gradleVersion}/userguide/upgrading_version_8.html#groovy_boolean_properties",
)
