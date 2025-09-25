package net.twisterrob.astro.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.tooling.preview.Preview
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Compose-aware [DateTimeFormatter] factory that
 * uses the [androidx.compose.ui.platform.LocalConfiguration] [java.util.Locale].
 *
 * @see DateTimeFormatter
 * @see androidx.compose.ui.platform.LocalConfiguration
 */
@Suppress("detekt.TopLevelComposableFunctions") // Mirror API.
public object ComposeDateTimeFormatter {

	/**
	 * @see DateTimeFormatter.ofLocalizedDate
	 */
	@Composable
	@ReadOnlyComposable
	public fun ofLocalizedDate(dateStyle: FormatStyle): DateTimeFormatter =
		DateTimeFormatter
			.ofLocalizedDate(dateStyle)
			.withLocale(defaultLocale)

	/**
	 * @see DateTimeFormatter.ofLocalizedTime
	 */
	@Composable
	@ReadOnlyComposable
	public fun ofLocalizedTime(timeStyle: FormatStyle): DateTimeFormatter =
		DateTimeFormatter
			.ofLocalizedTime(timeStyle)
			.withLocale(defaultLocale)

	/**
	 * @see DateTimeFormatter.ofPattern
	 */
	@Composable
	@ReadOnlyComposable
	public fun ofPattern(pattern: String): DateTimeFormatter =
		DateTimeFormatter
			.ofPattern(pattern)
			.withLocale(defaultLocale)
}

@Preview(locale = "en")
@Preview(locale = "hu")
@Composable
private fun OfLocalizedDatePreview() {
	val formatter = ComposeDateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
	Text(formatter.format(ZonedDateTime.now()))
}

@Preview(locale = "en")
@Preview(locale = "hu")
@Composable
private fun OfLocalizedTimePreview() {
	val formatter = ComposeDateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)
	Text(formatter.format(ZonedDateTime.now()))
}

@Preview(locale = "en")
@Preview(locale = "hu")
@Composable
private fun OfPatternPreview() {
	val formatter = ComposeDateTimeFormatter.ofPattern("EEE ZZZZ")
	Text(formatter.format(ZonedDateTime.now()))
}
