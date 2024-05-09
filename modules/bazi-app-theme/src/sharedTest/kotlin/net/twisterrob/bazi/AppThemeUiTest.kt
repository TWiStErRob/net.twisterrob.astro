package net.twisterrob.bazi

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.should
import net.twisterrob.bazi.ui.theme.AppTheme
import net.twisterrob.bazi.ui.theme.AppTypography
import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppThemeUiTest {

	@get:Rule
	val compose = createComposeRule()

	@Test
	fun testTypography() {
		compose.setContent {
			AppTheme { assertSame(AppTypography, MaterialTheme.typography) }
		}
	}

	@Test
	fun testDynamicLight() {
		compose.setContent {
			LocalConfiguration.current.uiModeNight = Configuration.UI_MODE_NIGHT_NO
			AppTheme { assertLightMode() }
		}
	}

	@Test
	fun testDynamicDark() {
		compose.setContent {
			LocalConfiguration.current.uiModeNight = Configuration.UI_MODE_NIGHT_YES
			AppTheme { assertDarkMode() }
		}
	}

	@Test
	fun testNonDynamicLight() {
		compose.setContent {
			LocalConfiguration.current.uiModeNight = Configuration.UI_MODE_NIGHT_NO
			AppTheme(dynamicColor = false) { assertLightMode() }
		}
	}

	@Test
	fun testNonDynamicDark() {
		compose.setContent {
			LocalConfiguration.current.uiModeNight = Configuration.UI_MODE_NIGHT_YES
			AppTheme(dynamicColor = false) { assertDarkMode() }
		}
	}

	@Test
	fun testExplicitLight() {
		compose.setContent {
			AppTheme(darkTheme = false) { assertLightMode() }
		}
	}

	@Test
	fun testExplicitDark() {
		compose.setContent {
			AppTheme(darkTheme = true) { assertDarkMode() }
		}
	}
}

private var Configuration.uiModeNight: Int
	get() = uiMode and Configuration.UI_MODE_NIGHT_MASK
	set(value) {
		apply {
			uiMode = (uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or value
		}
	}

@Composable
private fun assertLightMode() {
	MaterialTheme.colorScheme.background should beLight()
	MaterialTheme.colorScheme.onBackground should beDark()
}

@Composable
private fun assertDarkMode() {
	MaterialTheme.colorScheme.background should beDark()
	MaterialTheme.colorScheme.onBackground should beLight()
}

private fun beDark(): Matcher<Color> =
	object : Matcher<Color> {
		override fun test(value: Color): MatcherResult {
			val luminance = value.luminance()
			val dark = 0.25f
			return MatcherResult(
				luminance < dark,
				{ "${value} (${luminance}) should be dark (<${dark}" },
				{ "${value} (${luminance}) should not be dark (>${dark}" },
			)
		}
	}

private fun beLight(): Matcher<Color> =
	object : Matcher<Color> {
		override fun test(value: Color): MatcherResult {
			val luminance = value.luminance()
			val light = 0.75f
			return MatcherResult(
				luminance > light,
				{ "${value} (${luminance}) should be light (>${light}" },
				{ "${value} (${luminance}) should not be light (<${light}" },
			)
		}
	}
