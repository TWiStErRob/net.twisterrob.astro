package net.twisterrob.astro.compose

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Determine whether the device is in landscape orientation.
 *
 * @see isPortrait
 */
public val isLandscape: Boolean
	@Composable
	@ReadOnlyComposable
	get() = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE

@Preview("phone is portrait", device = Devices.PHONE)
@Preview("foldable is portrait", device = Devices.FOLDABLE)
@Preview("tablet is landscape", device = Devices.TABLET)
@Preview("desktop is landscape", device = Devices.DESKTOP)
@Composable
private fun IsLandscapePreview() {
	Text("isLandscape = ${isLandscape}")
}
