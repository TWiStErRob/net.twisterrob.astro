package net.twisterrob.astro.build

import com.android.build.api.dsl.Lint
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.lint

plugins.withId("com.android.base") {
	android.lint(Lint::configure)
}

plugins.withId("com.android.lint") {
	lint(Lint::configure)
}

private fun Lint.configure() {
	warningsAsErrors = true
	checkAllWarnings = true
	lintConfig = rootProject.file("config/lint/lint.xml")
}
