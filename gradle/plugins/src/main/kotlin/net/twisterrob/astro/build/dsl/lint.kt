package net.twisterrob.astro.build.dsl

import com.android.build.api.dsl.Lint
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get

/**
 * This is useful to emulate the `lint` extension in a convention plugin
 * where the `com.android.lint` are not applied, but implied.
 *
 * @see com.android.build.api.dsl.Lint
 */
internal val Project.lint: Lint
	get() = this.extensions["lint"] as Lint

/**
 * This is useful to emulate the `lint` block in a convention plugin
 * where the `com.android.lint` are not applied, but implied.
 *
 * @param block the configuration for common Lint things.
 * Using an [Action] to take advantage of `kotlin-dsl`.
 */
internal fun Project.lint(block: Action<Lint>) {
	block.execute(lint)
}
