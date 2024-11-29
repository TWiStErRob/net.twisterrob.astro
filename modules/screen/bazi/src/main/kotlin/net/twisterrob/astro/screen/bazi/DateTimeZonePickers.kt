package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.compose.preview.PreviewOrientation
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
internal fun DateTimeZonePickers(
	state: DateTimeState,

	onReset: () -> Unit,

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
	Box(
		modifier = modifier,
	) {
		DateTimeDisplay(
			modifier = Modifier
				.fillMaxWidth(),
			state = state.dateTime,
			onPickDate = onPickDate,
			onPickTime = onPickTime,
			onPickZone = onPickZone,
			onPickOffset = onPickOffset,
		)
		Reset(
			modifier = Modifier
				.align(Alignment.TopEnd),
			onClick = onReset,
		)
	}
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

@Composable
private fun Reset(
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	IconButton(
		modifier = modifier,
		onClick = onClick,
	) {
		Icon(
			imageVector = Filled.Refresh,
			contentDescription = stringResource(R.string.screen_bazi__picker_reset_cd)
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

			onReset = {},

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

@PreviewOrientation
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

			onReset = {},

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

@PreviewOrientation
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

			onReset = {},

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

@PreviewOrientation
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

			onReset = {},

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
