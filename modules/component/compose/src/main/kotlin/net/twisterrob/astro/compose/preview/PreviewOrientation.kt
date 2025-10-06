package net.twisterrob.astro.compose.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.compose.isLandscape
import net.twisterrob.astro.compose.isPortrait

/**
 * Annotate a preview to show it in both orientations.
 */
@Retention(AnnotationRetention.BINARY)
@Target(
	AnnotationTarget.ANNOTATION_CLASS,
	AnnotationTarget.FUNCTION
)
@Preview(name = "Phone - Portrait", device = Devices.PHONE)
@Preview(name = "Phone - Landscape", device = "spec:width=411dp, height=891dp, orientation=landscape, dpi=420")
public annotation class PreviewOrientation

@PreviewOrientation
@Composable
private fun PreviewOrientationPreview() {
	Column {
		Text("isLandscape = ${isLandscape}")
		Text("isPortrait = ${isPortrait}")
	}
}
