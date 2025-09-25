package net.twisterrob.astro.compose

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Determine whether the device is in portrait orientation.
 *
 * @see isLandscape
 */
public val isPortrait: Boolean
	@Composable
	@ReadOnlyComposable
	get() = LocalConfiguration.current.orientation == ORIENTATION_PORTRAIT

@Preview("phone is portrait", device = Devices.PHONE)
@Preview("foldable is portrait", device = Devices.FOLDABLE)
@Preview("table is landscape", device = Devices.TABLET)
@Preview("desktop is landscape", device = Devices.DESKTOP)
@Composable
private fun IsPortraitPreview() {
	Text("isPortrait = ${isPortrait}")
}
