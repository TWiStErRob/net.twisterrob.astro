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
public val Icons.Filled.RemoveCircleOutline: ImageVector
	get() {
		if (_removeCircleOutline != null) {
			@Suppress("detekt.UnsafeCallOnNullableType")
			return _removeCircleOutline!!
		}
		_removeCircleOutline = materialIcon(name = "Filled.RemoveCircleOutline") {
			@Suppress("detekt.MagicNumber", "detekt.NamedArguments")
			materialPath {
				moveTo(7.0f, 11.0f)
				verticalLineToRelative(2.0f)
				horizontalLineToRelative(10.0f)
				verticalLineToRelative(-2.0f)
				lineTo(7.0f, 11.0f)
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
		return _removeCircleOutline!!
	}

private var _removeCircleOutline: ImageVector? = null

@Preview
@Composable
private fun RemoveCircleOutlinePreview() {
	Icon(
		imageVector = Icons.Filled.RemoveCircleOutline,
		contentDescription = "preview"
	)
}
