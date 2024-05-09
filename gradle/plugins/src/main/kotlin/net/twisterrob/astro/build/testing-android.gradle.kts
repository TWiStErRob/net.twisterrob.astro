package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.testing.configureTestTask

plugins {
	// id("com.android.base") // assumed
}

dependencies {
	"testImplementation"(project(":test-helpers"))
}

android {
	testOptions {
		unitTests {
			all {
				configureTestTask(it)
			}
		}
	}
}
