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
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
internal fun DateTimeDisplay(
	state: DateTimeState,

	onPickDate: () -> Unit,
	onHideDatePicker: () -> Unit,
	onResetToToday: () -> Unit,
	onDateSelected: (LocalDate) -> Unit,

	onPickTime: () -> Unit,
	onHideTimePicker: () -> Unit,
	onResetToNow: () -> Unit,
	onTimeSelected: (LocalTime) -> Unit,

	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Row {
			Text(
				text = state.dateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
				modifier = Modifier
					.clickable(onClick = onPickDate)
			)
			if (state.isPickingDate) {
				DatePickerDialog(
					state = state.dateTime,
					onDateSelected = onDateSelected,
					onHideDatePicker = onHideDatePicker,
					onResetToToday = onResetToToday,
				)
			}
			Text(text = " ")
			Text(
				text = state.dateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)),
				modifier = Modifier
					.clickable(onClick = onPickTime)
			)
			if (state.isPickingTime) {
				TimePickerDialog(
					state = state.dateTime,
					onTimeSelected = onTimeSelected,
					onHideTimePicker = onHideTimePicker,
					onResetToNow = onResetToNow,
				)
			}
		}
		Row {
			Text(
				text = state.dateTime.format(DateTimeFormatter.ofPattern("VV zzzz z")),
				modifier = Modifier
					.clickable(onClick = { /* TODO time zone picker */ })
			)
		}
	}
}

@Preview
@Composable
private fun DateTimeDisplayPreview() {
	AppTheme {
		DateTimeDisplay(
			state = DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = false,
				isPickingTime = false,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onTimeSelected = {},
		)
	}
}

@Preview
@Composable
private fun DateTimeDisplayDatePickerPreview() {
	AppTheme {
		DateTimeDisplay(
			state = DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = true,
				isPickingTime = false,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onTimeSelected = {},
		)
	}
}

@Preview
@Composable
private fun DateTimeDisplayTimePickerPreview() {
	AppTheme {
		DateTimeDisplay(
			state = DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = false,
				isPickingTime = true,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onTimeSelected = {},
		)
	}
}
