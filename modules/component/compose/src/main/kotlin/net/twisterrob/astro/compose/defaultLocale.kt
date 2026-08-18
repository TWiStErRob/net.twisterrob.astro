package net.twisterrob.astro.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.ConfigurationCompat
import java.util.Locale

/**
 * Get the default locale from the Android system.
 */
public val defaultLocale: Locale
	@Composable
	@ReadOnlyComposable
	get() = ConfigurationCompat.getLocales(LocalConfiguration.current).get(0) ?: LocalLocale.current.platformLocale

@Preview("default")
@Preview("English", locale = "en")
@Preview("magyar", locale = "hu")
@Composable
private fun DefaultLocalePreview() {
	Text("${defaultLocale} (${defaultLocale.displayName})")
}
