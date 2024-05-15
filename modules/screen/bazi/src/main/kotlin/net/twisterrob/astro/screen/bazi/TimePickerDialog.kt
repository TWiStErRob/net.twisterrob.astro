package net.twisterrob.astro.screen.bazi

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.component.theme.AppTheme
import java.time.LocalTime
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TimePickerDialog(
	state: ZonedDateTime,
	onSelectTime: (LocalTime) -> Unit,
	onHideTimePicker: () -> Unit,
	onResetToNow: () -> Unit,
) {
	val pickerState = rememberTimePickerState(
		initialHour = state.hour,
		initialMinute = state.minute,
	)
	DatePickerDialog(
		confirmButton = {
			TextButton({ onSelectTime(pickerState.selectedLocalTime) }) {
				Text(stringResource(R.string.screen_bazi__time_picker_dialog_ok))
			}
		},
		onDismissRequest = onHideTimePicker,
		dismissButton = {
			TextButton(onResetToNow) {
				Text(stringResource(R.string.screen_bazi__time_picker_dialog_current))
			}
			TextButton(onHideTimePicker) {
				Text(stringResource(R.string.screen_bazi__time_picker_dialog_cancel))
			}
		}
	) {
		DynamicTimePicker(
			state = pickerState,
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
private val TimePickerState.selectedLocalTime: LocalTime
	get() = LocalTime.of(this.hour, this.minute)

@Preview
@Composable
private fun TimePickerDialogPreview() {
	AppTheme {
		TimePickerDialog(
			state = ZonedDateTime.now(),
			onHideTimePicker = {},
			onResetToNow = {},
			onSelectTime = {},
		)
	}
}
