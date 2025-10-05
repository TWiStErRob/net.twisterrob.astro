package net.twisterrob.astro.build

import com.android.build.api.dsl.TestedExtension
import net.twisterrob.astro.build.dsl.android

pluginManager.withPlugin("org.gradle.java-test-fixtures") {
	dependencies {
		"testFixturesImplementation"(project(":component:test-base-fixtures"))
	}
	configurations {
		named("testFixturesCompileClasspath") {
			shouldResolveConsistentlyWith(named("testRuntimeClasspath").get())
		}
	}
}

pluginManager.withPlugin("com.android.library") {
	android {
		@Suppress("UnstableApiUsage")
		(this as TestedExtension).testFixtures {
			enable = true
		}
	}

	dependencies {
		"testFixturesImplementation"(project(":component:test-base-fixtures"))
	}
}
