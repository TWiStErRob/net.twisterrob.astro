import net.twisterrob.astro.build.dsl.libs
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
	//noinspection JavaPluginLanguageLevel
	id("org.gradle.java-library")
	id("org.jetbrains.kotlin.jvm")
	id("net.twisterrob.astro.build.kotlin")
	id("net.twisterrob.astro.build.testing")
	id("net.twisterrob.astro.build.detekt")
}

kotlin {
	explicitApi = ExplicitApiMode.Strict
}

kotlin {
	jvmToolchain(libs.versions.java.toolchain.get().toInt())
}
