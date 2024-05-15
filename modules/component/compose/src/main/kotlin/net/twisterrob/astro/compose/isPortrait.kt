package net.twisterrob.astro.compose

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration

/**
 * Determine whether the device is in portrait orientation.
 *
 * @see isLandscape
 */
public val isPortrait: Boolean
	@Composable
	@ReadOnlyComposable
	get() = LocalConfiguration.current.orientation == ORIENTATION_PORTRAIT
