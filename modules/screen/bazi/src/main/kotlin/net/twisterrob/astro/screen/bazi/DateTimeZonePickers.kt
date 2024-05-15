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
internal fun DateTimeZonePickers(
	state: DateTimeState,

	onPickDate: () -> Unit,
	onHideDatePicker: () -> Unit,
	onResetToToday: () -> Unit,
	onSelectDate: (LocalDate) -> Unit,

	onPickTime: () -> Unit,
	onHideTimePicker: () -> Unit,
	onResetToNow: () -> Unit,
	onSelectTime: (LocalTime) -> Unit,

	onPickZone: () -> Unit,
	onSelectZone: (ZoneId) -> Unit,
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
			onSelectDate = onSelectDate,
			onHideDatePicker = onHideDatePicker,
			onResetToToday = onResetToToday,
		)
	}
	if (state.isPickingTime) {
		TimePickerDialog(
			state = state.dateTime,
			onSelectTime = onSelectTime,
			onHideTimePicker = onHideTimePicker,
			onResetToNow = onResetToNow,
		)
	}
	if (state.isPickingZone) {
		ZonePickerDialog(
			state = state.dateTime,
			onSelectZone = onSelectZone,
			onHideZonePicker = onHideZonePicker,
			onResetToZone = onResetToZone,
		)
	}
}

@Preview
@Composable
private fun Preview() {
	AppTheme {
		DateTimeZonePickers(
			state = DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = false,
				isPickingTime = false,
				isPickingZone = false,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onSelectDate = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onSelectTime = {},

			onPickZone = {},
			onSelectZone = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onPickOffset = {},
		)
	}
}

@Preview
@Composable
private fun DatePickerPreview() {
	AppTheme {
		DateTimeZonePickers(
			state = DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = true,
				isPickingTime = false,
				isPickingZone = false,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onSelectDate = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onSelectTime = {},

			onPickZone = {},
			onSelectZone = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onPickOffset = {},
		)
	}
}

@Preview
@Composable
private fun TimePickerPreview() {
	AppTheme {
		DateTimeZonePickers(
			state = DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = false,
				isPickingTime = true,
				isPickingZone = false,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onSelectDate = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onSelectTime = {},

			onPickZone = {},
			onSelectZone = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onPickOffset = {},
		)
	}
}

@Preview
@Composable
private fun ZonePickerPreview() {
	AppTheme {
		DateTimeZonePickers(
			state = DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = false,
				isPickingTime = false,
				isPickingZone = true,
			),

			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onSelectDate = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onSelectTime = {},

			onPickZone = {},
			onSelectZone = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onPickOffset = {},
		)
	}
}
