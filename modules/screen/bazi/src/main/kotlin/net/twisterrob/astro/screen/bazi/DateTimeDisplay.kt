package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.component.theme.AppTheme
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
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
				val datePickerState = rememberDatePickerState(
					initialSelectedDateMillis = state.dateTime.toInstant().toEpochMilli()
				)
				DatePickerDialog(
					confirmButton = {
						fun convertToOnDateSelected() {
							// Strange representation of a "date", so convert it back to an actual date.
							val instant = Instant.ofEpochMilli(datePickerState.selectedDateMillis!!)
							onDateSelected(LocalDate.ofInstant(instant, ZoneOffset.UTC))
						}
						TextButton(::convertToOnDateSelected) { Text("OK") }
					},
					onDismissRequest = onHideDatePicker,
					dismissButton = {
						TextButton(onResetToToday) { Text("Today") }
						TextButton(onHideDatePicker) { Text("Cancel") }
					}
				) {
					DatePicker(state = datePickerState)
				}
			}
			Text(text = " ")
			Text(
				text = state.dateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)),
				modifier = Modifier
					.clickable(onClick = onPickTime)
			)
			if (state.isPickingTime) {
				val timePickerState = rememberTimePickerState(
					initialHour = state.dateTime.hour,
					initialMinute = state.dateTime.minute,
				)
				DatePickerDialog(
					confirmButton = {
						fun convertToOnTimeSelected() {
							onTimeSelected(LocalTime.of(timePickerState.hour, timePickerState.minute))
						}
						TextButton(::convertToOnTimeSelected) { Text("OK") }
					},
					onDismissRequest = onHideTimePicker,
					dismissButton = {
						TextButton(onResetToNow) { Text("Now") }
						TextButton(onHideTimePicker) { Text("Cancel") }
					}
				) {
					Column(
						modifier = Modifier.fillMaxWidth(),
					) {
						CompositionLocalProvider(
							LocalContentColor provides AlertDialogDefaults.titleContentColor,
							LocalTextStyle provides LocalTextStyle.current.merge(
								other = MaterialTheme.typography.labelLarge
							)
						) {
							Box(contentAlignment = Alignment.BottomStart) {
								Text(
									text = "Select time",
									modifier = Modifier.padding(start = 24.dp, end = 12.dp, top = 16.dp, bottom = 24.dp)
								)
							}
						}
						TimePicker(
							state = timePickerState,
							modifier = Modifier.align(Alignment.CenterHorizontally)
						)
					}
				}
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
