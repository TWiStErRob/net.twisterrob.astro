package net.twisterrob.bazi

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUiTest {

	@get:Rule
	val compose = createAndroidComposeRule<MainActivity>()

	@Test
	fun test() {
		compose
			.onNodeWithText("Hello Android!")
			.assertExists()
	}
}
