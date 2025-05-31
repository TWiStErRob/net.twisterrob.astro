package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.android

plugins.withId("com.android.base") {
	android {
		lint {
			warningsAsErrors = true
			checkAllWarnings = true
			lintConfig = rootProject.file("config/lint/lint.xml")
		}
	}
}
