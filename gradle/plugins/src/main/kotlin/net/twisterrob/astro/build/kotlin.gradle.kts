package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.kotlin
import net.twisterrob.astro.build.dsl.libs
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.dsl.HasConfigurableKotlinCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

plugins {
	// One of these is implied.
	id("org.gradle.java-base")
	// id("org.jetbrains.kotlin.jvm")
	// id("org.jetbrains.kotlin.android")
}

dependencies {
	// Required due to `kotlin.stdlib.default.dependency=false`.
	"implementation"(libs.kotlin.stdlib)
}

kotlin {
	explicitApi = ExplicitApiMode.Strict
	jvmToolchain {
		languageVersion = libs.versions.java.toolchain.map(JavaLanguageVersion::of)
	}
	@Suppress("UNCHECKED_CAST")
	this as HasConfigurableKotlinCompilerOptions<KotlinJvmCompilerOptions>
	compilerOptions {
		jvmTarget = libs.versions.java.target.map(JvmTarget::fromTarget)
		// https://youtrack.jetbrains.com/issue/KT-71375#focus=Comments-27-11124578.0-0
		// freeCompilerArgs.add(jvmTarget.map { "-Xjdk-release=${it.target}" })
		allWarningsAsErrors = true
		freeCompilerArgs.add("-opt-in=kotlin.ExperimentalStdlibApi")
		freeCompilerArgs.add("-Xcontext-parameters")
	}
}

pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
	java {
		// > Execution failed for task ':<net.twisterrob.astro.library>:compileKotlin'.
		// > Inconsistent JVM Target Compatibility Between Java and Kotlin Tasks
		// > Inconsistent JVM-target compatibility detected for tasks 'compileJava' (25) and 'compileKotlin' (21).
		sourceCompatibility = libs.versions.java.target.map(JavaVersion::toVersion).get()
		targetCompatibility = libs.versions.java.target.map(JavaVersion::toVersion).get()
	}
	tasks.withType<JavaCompile>().configureEach {
		// https://cs.android.com/android-studio/platform/tools/base/+/mirror-goog-studio-main:build-system/gradle-core/src/main/java/com/android/build/gradle/tasks/JavaCompileUtils.kt;l=357
		//options.release = libs.versions.java.target.map(String::toInt)
	}
}

pluginManager.withPlugin("com.android.base") {
	android {
		compileOptions.apply {
			// > Execution failed for task ':<net.twisterrob.astro.android-library>:compileDebugKotlin'.
			// > Inconsistent JVM targets between Java and Kotlin compile tasks: 25 and 21.
			// > To fix this issue, use the same JVM target for both tasks.
			// > For more details, see https://issuetracker.google.com/408242956.
			sourceCompatibility = libs.versions.java.target.map(JavaVersion::toVersion).get()
			targetCompatibility = libs.versions.java.target.map(JavaVersion::toVersion).get()
		}
	}
}
