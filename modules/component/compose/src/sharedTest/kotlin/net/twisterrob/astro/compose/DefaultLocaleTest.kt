package net.twisterrob.astro.compose

import android.content.res.Configuration
import android.os.LocaleList
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertNotNull
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
class DefaultLocaleTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	@Test
	fun defaultLocale_isNotNull() {
		composeTestRule.setContent {
			assertNotNull(defaultLocale)
		}
	}

	@Test
	fun defaultLocale_noLocale() {
		composeTestRule.setContent {
			CompositionLocalProvider(
				LocalConfiguration provides Configuration()
			) {
				val locale = defaultLocale
				assertEquals(Locale.getDefault(), locale)
			}
		}
	}

	@Test
	fun defaultLocale_singleLocale() {
		composeTestRule.setContent {
			CompositionLocalProvider(
				LocalConfiguration provides Configuration().apply {
					setLocales(LocaleList.forLanguageTags("hu"))
				}
			) {
				val locale = defaultLocale
				assertEquals(Locale.forLanguageTag("hu"), locale)
			}
		}
	}

	@Test
	fun defaultLocale_firstLocale() {
		composeTestRule.setContent {
			CompositionLocalProvider(
				LocalConfiguration provides Configuration().apply {
					setLocales(LocaleList.forLanguageTags("de,fr"))
				}
			) {
				val locale = defaultLocale
				assertEquals(Locale.forLanguageTag("de"), locale)
			}
		}
	}
}
