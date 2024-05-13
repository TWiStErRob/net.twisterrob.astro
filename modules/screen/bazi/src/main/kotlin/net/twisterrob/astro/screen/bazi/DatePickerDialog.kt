package net.twisterrob.astro.screen.bazi

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.component.theme.AppTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DatePickerDialog(
	state: ZonedDateTime,
	onDateSelected: (LocalDate) -> Unit,
	onHideDatePicker: () -> Unit,
	onResetToToday: () -> Unit,
) {
	val datePickerState = rememberDatePickerState(
		initialSelectedDateMillis = state.toInstant().toEpochMilli()
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

@Preview
@Composable
private fun DatePickerDialogPreview() {
	AppTheme {
		DatePickerDialog(
			state = ZonedDateTime.now(),
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},
		)
	}
}
