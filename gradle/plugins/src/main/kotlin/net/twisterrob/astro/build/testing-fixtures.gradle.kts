package net.twisterrob.astro.build

import com.android.build.api.dsl.TestedExtension
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.androidComponents
import net.twisterrob.astro.build.dsl.libs

pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
	dependencies {
		// Required due to `kotlin.stdlib.default.dependency=false`.
		"testFixturesImplementation"(libs.kotlin.stdlib)
	}
}

pluginManager.withPlugin("org.gradle.java-test-fixtures") {
	dependencies {
		"testFixturesImplementation"(project(":component:test-base-unit"))
	}
}

pluginManager.withPlugin("com.android.library") {
	android {
		@Suppress("UnstableApiUsage")
		(this as TestedExtension).testFixtures {
			enable = true
		}
	}

	androidComponents.finalizeDsl {
		dependencies {
			"testFixturesImplementation"(project(":component:test-base-fixtures"))
			// Required due to `kotlin.stdlib.default.dependency=false`.
			"testFixturesImplementation"(libs.kotlin.stdlib)
		}
	}
}
