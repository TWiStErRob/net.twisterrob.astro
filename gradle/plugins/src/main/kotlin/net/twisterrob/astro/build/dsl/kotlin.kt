package net.twisterrob.astro.build.dsl

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension

/**
 * This is useful to emulate the `kotlin` extension in a convention plugin
 * where the `kotlin-jvm` or `kotlin-android` are not applied, but implied.
 *
 * @see org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
 * @see org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
 */
internal val Project.kotlin: KotlinBaseExtension
	get() = this.extensions["kotlin"] as KotlinBaseExtension

/**
 * This is useful to emulate the `kotlin` block in a convention plugin
 * where the `kotlin-jvm` or `kotlin-android` are not applied, but implied.
 *
 * @param block the configuration for common Kotlin things.
 * Using an [Action] to take advantage of `kotlin-dsl`.
 */
internal fun Project.kotlin(block: Action<KotlinBaseExtension>) {
	block.execute(kotlin)
}
