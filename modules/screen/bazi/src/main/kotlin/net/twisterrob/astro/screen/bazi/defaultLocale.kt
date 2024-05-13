package net.twisterrob.astro.screen.bazi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.os.ConfigurationCompat
import java.util.Locale

@Composable
@ReadOnlyComposable
internal fun defaultLocale(): Locale =
    ConfigurationCompat.getLocales(LocalConfiguration.current).get(0) ?: Locale.getDefault()
