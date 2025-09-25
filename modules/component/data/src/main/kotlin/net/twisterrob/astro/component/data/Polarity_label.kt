package net.twisterrob.astro.component.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import net.twisterrob.astro.bazi.model.Polarity

/**
 * Localized name for [Polarity]s.
 */
public val Polarity.label: String
	@Composable
	@ReadOnlyComposable
	get() = stringResource(id = this.labelRes)

@get:StringRes
private val Polarity.labelRes: Int
	get() = when (this) {
		Polarity.Yang -> R.string.component_data__polarity_yang
		Polarity.Yin -> R.string.component_data__polarity_yin
	}
