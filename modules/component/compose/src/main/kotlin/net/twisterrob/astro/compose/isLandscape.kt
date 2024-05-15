package net.twisterrob.astro.compose

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration

/**
 * Determine whether the device is in landscape orientation.
 *
 * @see isPortrait
 */
public val isLandscape: Boolean
	@Composable
	@ReadOnlyComposable
	get() = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE
