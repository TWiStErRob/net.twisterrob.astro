package net.twisterrob.astro.component.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
	primary = Purple80,
	secondary = PurpleGrey80,
	tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
	primary = Purple40,
	secondary = PurpleGrey40,
	tertiary = Pink40

	/* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

/**
 * The app's main theme.
 */
@Composable
public fun AppTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	dynamicColor: Boolean = true,
	content: @Composable () -> Unit,
) {
	MaterialTheme(
		colorScheme = LocalContext.current.colorScheme(dynamicColor, darkTheme),
		typography = AppTypography,
		content = content
	)
}

private fun Context.colorScheme(dynamicColor: Boolean, darkTheme: Boolean): ColorScheme =
	when {
		dynamicColor ->
			if (darkTheme) {
				dynamicDarkColorScheme(this)
			} else {
				dynamicLightColorScheme(this)
			}

		darkTheme ->
			DarkColorScheme

		else ->
			LightColorScheme
	}
