package net.twisterrob.astro.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class IsLandscapeRoboTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	@Test
	@Config(qualifiers = "")
	fun config_default() {
		composeTestRule.setContent {
			assertFalse(isLandscape)
		}
	}


	@Test
	@Config(qualifiers = "land")
	fun config_landscape() {
		composeTestRule.setContent {
			assertTrue(isLandscape)
		}
	}

	@Test
	@Config(qualifiers = "port")
	fun config_portrait() {
		composeTestRule.setContent {
			assertFalse(isLandscape)
		}
	}
}
