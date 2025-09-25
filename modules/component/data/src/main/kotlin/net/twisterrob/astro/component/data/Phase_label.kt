package net.twisterrob.astro.component.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import net.twisterrob.astro.bazi.model.Phase

/**
 * Localized name for Wuxing [Phase]s.
 */
public val Phase.label: String
	@Composable
	@ReadOnlyComposable
	get() = stringResource(id = this.labelRes)

@get:StringRes
private val Phase.labelRes: Int
	get() = when (this) {
		Phase.Mu -> R.string.component_data__phase_wood
		Phase.Huo -> R.string.component_data__phase_fire
		Phase.Tu -> R.string.component_data__phase_earth
		Phase.Jin -> R.string.component_data__phase_metal
		Phase.Shui -> R.string.component_data__phase_water
	}
