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
import org.junit.runner.RunWith
import java.time.ZonedDateTime
import java.time.format.FormatStyle
import java.util.Locale

@RunWith(AndroidJUnit4::class)
class ComposeDateTimeFormatterTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	private val testDate = ZonedDateTime.parse("2020-01-12T23:34:45+05:30[Asia/Kolkata]")
	private val testConfiguration = Configuration().apply {
		setLocales(LocaleList(Locale.forLanguageTag("hu")))
	}

	@Test
	fun ofLocalizedDate() {
		composeTestRule.setContent {
			CompositionLocalProvider(
				LocalConfiguration provides testConfiguration,
			) {
				val subject = ComposeDateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
				val actual = subject.format(testDate)
				assertEquals("2020. január 12.", actual) // January
			}
		}
	}

	@Test
	fun ofLocalizedTime() {
		composeTestRule.setContent {
			CompositionLocalProvider(
				LocalConfiguration provides testConfiguration,
			) {
				val subject = ComposeDateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)
				val actual = subject.format(testDate)
				assertEquals("23:34:45 indiai téli idő", actual) // Indian Winter Time
			}
		}
	}

	@Test
	fun ofPattern() {
		composeTestRule.setContent {
			CompositionLocalProvider(
				LocalConfiguration provides testConfiguration,
			) {
				val subject = ComposeDateTimeFormatter.ofPattern("EEEE ZZZZ")
				val actual = subject.format(testDate)
				assertEquals("vasárnap GMT+05:30", actual) // Sunday
			}
		}
	}
}
