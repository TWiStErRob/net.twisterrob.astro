package net.twisterrob.astro.screen.bazi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.component.theme.AppTheme
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

@Composable
internal fun DateTimeEditors(
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
	DateTimeDisplay(
		modifier = modifier,
		state = state.dateTime,
		onPickDate = onPickDate,
		onPickTime = onPickTime,
	)
	if (state.isPickingDate) {
		DatePickerDialog(
			state = state.dateTime,
			onDateSelected = onDateSelected,
			onHideDatePicker = onHideDatePicker,
			onResetToToday = onResetToToday,
		)
	}
	if (state.isPickingTime) {
		TimePickerDialog(
			state = state.dateTime,
			onTimeSelected = onTimeSelected,
			onHideTimePicker = onHideTimePicker,
			onResetToNow = onResetToNow,
		)
	}
}

@Preview
@Composable
private fun DateTimeDisplayPreview() {
	AppTheme {
		DateTimeEditors(
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
		DateTimeEditors(
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
		DateTimeEditors(
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
