package net.twisterrob.bazi

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GreetingRoboTest {

	@get:Rule
	val compose = createComposeRule()

	@Test
	fun test() {
		compose.setContent {
			Greeting("Test Name")
		}
		compose
			.onNodeWithText("Hello Test Name!")
			.assertExists()
	}
}