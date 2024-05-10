package net.twisterrob.astro.widget.greeting

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GreetingUiTest {

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
