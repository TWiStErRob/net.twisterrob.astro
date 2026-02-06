package net.twisterrob.astro.build

import com.android.build.api.dsl.Lint
import net.twisterrob.astro.build.dsl.android
import net.twisterrob.astro.build.dsl.lint

plugins.withId("com.android.base") {
	android.lint.apply(Lint::configure)
}

plugins.withId("com.android.lint") {
	lint.apply(Lint::configure)
}

private fun Lint.configure() {
	warningsAsErrors = true
	checkAllWarnings = true
	val cleanPath = project.path.substring(1).replace(':', '+')
	baseline = rootProject.file("config/lint/baseline/${cleanPath}.xml")
	lintConfig = rootProject.file("config/lint/lint.xml")
}
