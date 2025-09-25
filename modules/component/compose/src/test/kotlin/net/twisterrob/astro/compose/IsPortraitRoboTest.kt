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
class IsPortraitRoboTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	@Test
	@Config(qualifiers = "")
	fun config_default() {
		composeTestRule.setContent {
			assertTrue(isPortrait)
		}
	}


	@Test
	@Config(qualifiers = "land")
	fun config_landscape() {
		composeTestRule.setContent {
			assertFalse(isPortrait)
		}
	}

	@Test
	@Config(qualifiers = "port")
	fun config_portrait() {
		composeTestRule.setContent {
			assertTrue(isPortrait)
		}
	}
}
