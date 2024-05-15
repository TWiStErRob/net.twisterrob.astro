package net.twisterrob.astro.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.os.ConfigurationCompat
import java.util.Locale

public val defaultLocale: Locale
	@Composable
	@ReadOnlyComposable
	get() = ConfigurationCompat.getLocales(LocalConfiguration.current).get(0) ?: Locale.getDefault()
