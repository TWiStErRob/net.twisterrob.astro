package net.twisterrob.astro.screen.bazi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.component.theme.AppTheme
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
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

	onPickZone: () -> Unit,
	onZoneSelected: (ZoneId) -> Unit,
	onHideZonePicker: () -> Unit,
	onResetToZone: () -> Unit,
	onPickOffset: () -> Unit,

	modifier: Modifier = Modifier,
) {
	DateTimeDisplay(
		modifier = modifier,
		state = state.dateTime,
		onPickDate = onPickDate,
		onPickTime = onPickTime,
		onPickZone = onPickZone,
		onPickOffset = onPickOffset,
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
	if (state.isPickingZone) {
		ZonePickerDialog(
			state = state.dateTime,
			onZoneSelected = onZoneSelected,
			onHideZonePicker = onHideZonePicker,
			onResetToZone = onResetToZone,
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
				isPickingZone = false,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onTimeSelected = {},

			onPickZone = {},
			onZoneSelected = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onPickOffset = {},
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
				isPickingZone = false,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onTimeSelected = {},

			onPickZone = {},
			onZoneSelected = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onPickOffset = {},
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
				isPickingZone = false,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onTimeSelected = {},

			onPickZone = {},
			onZoneSelected = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onPickOffset = {},
		)
	}
}

@Preview
@Composable
private fun DateTimeDisplayZonePickerPreview() {
	AppTheme {
		DateTimeEditors(
			state = DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = false,
				isPickingTime = false,
				isPickingZone = true,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onTimeSelected = {},

			onPickZone = {},
			onZoneSelected = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onPickOffset = {},
		)
	}
}
