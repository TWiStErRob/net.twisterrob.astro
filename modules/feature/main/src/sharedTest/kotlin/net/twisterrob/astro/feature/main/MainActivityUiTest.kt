package net.twisterrob.astro.feature.main

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
			.onNodeWithText(compose.activity.getString(R.string.feature_main__title))
			.assertExists()
	}
}
