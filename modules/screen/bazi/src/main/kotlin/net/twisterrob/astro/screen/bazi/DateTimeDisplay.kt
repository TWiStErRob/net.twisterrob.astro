package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.component.theme.AppTheme
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle.LONG
import java.time.format.FormatStyle.MEDIUM

@Composable
internal fun DateTimeDisplay(
	state: ZonedDateTime,
	onPickDate: () -> Unit,
	onPickTime: () -> Unit,
	onPickOffset: () -> Unit,
	onPickZone: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Row {
			Text(
				text = state.format(DateTimeFormatter.ofLocalizedDate(LONG)),
				modifier = Modifier
					.clickable(onClick = onPickDate)
			)
			Text(text = " ")
			Text(
				text = state.format(DateTimeFormatter.ofLocalizedTime(MEDIUM)),
				modifier = Modifier
					.clickable(onClick = onPickTime)
			)
			Text(text = " ")
			Text(
				text = state.format(DateTimeFormatter.ofPattern("ZZZZZ (zzz)")),
				modifier = Modifier
					.clickable(onClick = onPickOffset)
			)
		}
		Row {
			Text(
				text = state.format(DateTimeFormatter.ofPattern("VV zzzz")),
				modifier = Modifier
					.clickable(onClick = onPickZone)
			)
		}
	}
}

@Preview
@Composable
private fun Preview() {
	AppTheme {
		DateTimeDisplay(
			state = ZonedDateTime.now(),
			onPickDate = {},
			onPickTime = {},
			onPickZone = {},
			onPickOffset = {},
		)
	}
}

@Preview
@Composable
private fun UTCPreview() {
	AppTheme {
		DateTimeDisplay(
			state = ZonedDateTime.now(ZoneId.of("Etc/UTC")),
			onPickDate = {},
			onPickTime = {},
			onPickZone = {},
			onPickOffset = {},
		)
	}
}

@Preview
@Composable
private fun HungarySummerPreview() {
	AppTheme {
		@Suppress("detekt.MagicNumber")
		DateTimeDisplay(
			state = LocalDate.of(2024, Month.JULY, 1).atStartOfDay(ZoneId.of("Europe/Budapest")),
			onPickDate = {},
			onPickTime = {},
			onPickZone = {},
			onPickOffset = {},
		)
	}
}

@Preview
@Composable
private fun HungaryWinterPreview() {
	AppTheme {
		@Suppress("detekt.MagicNumber")
		DateTimeDisplay(
			state = LocalDate.of(2024, Month.JANUARY, 1).atStartOfDay(ZoneId.of("Europe/Budapest")),
			onPickDate = {},
			onPickTime = {},
			onPickZone = {},
			onPickOffset = {},
		)
	}
}

@Preview
@Composable
private fun IndiaPreview() {
	AppTheme {
		DateTimeDisplay(
			state = ZonedDateTime.now(ZoneId.of("Asia/Kolkata")),
			onPickDate = {},
			onPickTime = {},
			onPickZone = {},
			onPickOffset = {},
		)
	}
}
