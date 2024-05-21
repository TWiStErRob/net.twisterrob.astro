package net.twisterrob.astro.build

import net.twisterrob.astro.build.dsl.isIdeaSync
import org.gradle.api.provider.Provider
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption
import javax.inject.Inject

open class FilteredComposeCompilerGradleSubplugin(
	private val subplugin: ComposeCompilerGradleSubplugin,
) : KotlinCompilerPluginSupportPlugin by subplugin {

	@Suppress("unused") // Used by Gradle plugin mechanism.
	@Inject constructor(registry: ToolingModelBuilderRegistry)
		: this(@Suppress("INVISIBLE_MEMBER") ComposeCompilerGradleSubplugin(registry))

	override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> =
		subplugin.applyToCompilation(kotlinCompilation)
			.map { list ->
				if (kotlinCompilation.project.isIdeaSync) {
					list.filter { option -> option.key != "strongSkipping" }
				} else {
					list
				}
			}
}
