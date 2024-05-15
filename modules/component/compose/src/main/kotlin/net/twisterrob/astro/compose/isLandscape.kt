package net.twisterrob.astro.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration

public val isLandscape: Boolean
	@Composable
	@ReadOnlyComposable
	get() = with(LocalConfiguration.current) { screenHeightDp < screenWidthDp }
