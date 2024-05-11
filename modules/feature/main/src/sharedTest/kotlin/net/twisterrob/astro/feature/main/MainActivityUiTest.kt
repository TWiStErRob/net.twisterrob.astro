package net.twisterrob.astro.feature.main

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
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
		val appContext: Context = ApplicationProvider.getApplicationContext()
		compose
			.onNodeWithText(appContext.getString(R.string.feature_main__title))
			.assertExists()
	}
}
