package androidx.compose.material.icons.filled

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

/**
 * For a note on filled vs outline, see https://issuetracker.google.com/issues/243679684.
 */
@Suppress("UnusedReceiverParameter")
public val Icons.Filled.AddCircleOutline: ImageVector
	get() {
		if (_addCircleOutline != null) {
			@Suppress("detekt.UnsafeCallOnNullableType")
			return _addCircleOutline!!
		}
		_addCircleOutline = materialIcon(name = "Filled.AddCircleOutline") {
			@Suppress("detekt.MagicNumber", "detekt.NamedArguments")
			materialPath {
				moveTo(13.0f, 7.0f)
				horizontalLineToRelative(-2.0f)
				verticalLineToRelative(4.0f)
				lineTo(7.0f, 11.0f)
				verticalLineToRelative(2.0f)
				horizontalLineToRelative(4.0f)
				verticalLineToRelative(4.0f)
				horizontalLineToRelative(2.0f)
				verticalLineToRelative(-4.0f)
				horizontalLineToRelative(4.0f)
				verticalLineToRelative(-2.0f)
				horizontalLineToRelative(-4.0f)
				lineTo(13.0f, 7.0f)
				close()
				moveTo(12.0f, 2.0f)
				curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f)
				reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f)
				reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f)
				reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f)
				close()
				moveTo(12.0f, 20.0f)
				curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f)
				reflectiveCurveToRelative(3.59f, -8.0f, 8.0f, -8.0f)
				reflectiveCurveToRelative(8.0f, 3.59f, 8.0f, 8.0f)
				reflectiveCurveToRelative(-3.59f, 8.0f, -8.0f, 8.0f)
				close()
			}
		}
		@Suppress("detekt.UnsafeCallOnNullableType")
		return _addCircleOutline!!
	}

private var _addCircleOutline: ImageVector? = null

@Preview
@Composable
private fun AddCircleOutlinePreview() {
	Icon(
		imageVector = Icons.Filled.AddCircleOutline,
		contentDescription = "preview"
	)
}
