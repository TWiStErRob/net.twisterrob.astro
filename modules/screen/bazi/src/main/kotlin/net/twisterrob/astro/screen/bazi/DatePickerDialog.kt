@file:SuppressLint("UnsafeOptInUsageError") // REPORT false positive, only name matches.

package net.twisterrob.astro.screen.bazi

import android.annotation.SuppressLint
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
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
	onSelectDate: (LocalDate) -> Unit,
	onHideDatePicker: () -> Unit,
	onResetToToday: () -> Unit,
) {
	val pickerState = rememberDatePickerState(
		initialSelectedDateMillis = state.toInstant().toEpochMilli()
	)
	DatePickerDialog(
		confirmButton = {
			TextButton({ onSelectDate(pickerState.selectedLocalDate) }) {
				Text(stringResource(R.string.screen_bazi__date_picker_dialog_ok))
			}
		},
		onDismissRequest = onHideDatePicker,
		dismissButton = {
			TextButton(onResetToToday) {
				Text(stringResource(R.string.screen_bazi__date_picker_dialog_current))
			}
			TextButton(onHideDatePicker) {
				Text(stringResource(R.string.screen_bazi__date_picker_dialog_cancel))
			}
		}
	) {
		DatePicker(state = pickerState)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
private val DatePickerState.selectedLocalDate: LocalDate
	get() {
		// Strange representation of a "date", so convert it back to an actual date.
		val instant = Instant.ofEpochMilli(selectedDateMillis ?: error("Missing date"))
		return LocalDate.ofInstant(instant, ZoneOffset.UTC)
	}

@Preview
@Composable
private fun Preview() {
	AppTheme {
		DatePickerDialog(
			state = ZonedDateTime.now(),
			onHideDatePicker = {},
			onResetToToday = {},
			onSelectDate = {},
		)
	}
}
