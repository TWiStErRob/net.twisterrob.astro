package net.twisterrob.astro.compose

import android.content.res.Configuration
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IsPortraitTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	@Test
	fun localConfiguration_landscape() {
		composeTestRule.setContent {
			CompositionLocalProvider(
				LocalConfiguration provides Configuration().apply {
					orientation = Configuration.ORIENTATION_LANDSCAPE
				}
			) {
				assertFalse(isPortrait)
			}
		}
	}

	@Test
	fun localConfiguration_portrait() {
		composeTestRule.setContent {
			CompositionLocalProvider(
				LocalConfiguration provides Configuration().apply {
					orientation = Configuration.ORIENTATION_PORTRAIT
				}
			) {
				assertTrue(isPortrait)
			}
		}
	}
}
