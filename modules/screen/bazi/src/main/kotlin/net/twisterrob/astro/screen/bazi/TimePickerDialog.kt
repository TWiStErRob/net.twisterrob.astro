package net.twisterrob.astro.screen.bazi

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.compose.preview.PreviewOrientation
import java.time.LocalTime
import java.time.ZonedDateTime

internal enum class TimePickerDisplayMode {
	Input,
	Picker,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TimePickerDialog(
	state: ZonedDateTime,
	onSelectTime: (LocalTime) -> Unit,
	onHideTimePicker: () -> Unit,
	onResetToNow: () -> Unit,
	displayMode: TimePickerDisplayMode = TimePickerDisplayMode.Picker,
) {
	val pickerState = rememberTimePickerState(
		initialHour = state.hour,
		initialMinute = state.minute,
	)
	var displayModeState by rememberSaveable { mutableStateOf(displayMode) }
	TimePickerDialog(
		title = {
			Text(
				text = stringResource(
					when (displayModeState) {
						TimePickerDisplayMode.Input -> R.string.screen_bazi__time_picker_mode_input_title
						TimePickerDisplayMode.Picker -> R.string.screen_bazi__time_picker_mode_picker_title
					}
				)
			)
		},
		modeToggleButton = {
			DisplayModeToggleButton(
				displayMode = displayModeState,
				onDisplayModeChange = { displayModeState = it },
			)
		},
		confirmButton = {
			TextButton({ onSelectTime(pickerState.selectedLocalTime) }) {
				Text(stringResource(R.string.screen_bazi__time_picker_dialog_ok))
			}
		},
		dismissButton = {
			TextButton(onResetToNow) {
				Text(stringResource(R.string.screen_bazi__time_picker_dialog_current))
			}
			TextButton(onHideTimePicker) {
				Text(stringResource(R.string.screen_bazi__time_picker_dialog_cancel))
			}
		},
		onDismissRequest = onHideTimePicker,
	) {
		when (displayModeState) {
			TimePickerDisplayMode.Input -> TimeInput(pickerState)
			TimePickerDisplayMode.Picker -> TimePicker(pickerState)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
private val TimePickerState.selectedLocalTime: LocalTime
	get() = LocalTime.of(this.hour, this.minute)

@PreviewOrientation
@Composable
private fun PickerPreview() {
	AppTheme {
		TimePickerDialog(
			state = ZonedDateTime.now(),
			onSelectTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			displayMode = TimePickerDisplayMode.Picker,
		)
	}
}

@PreviewOrientation
@Composable
private fun InputPreview() {
	AppTheme {
		TimePickerDialog(
			state = ZonedDateTime.now(),
			onSelectTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			displayMode = TimePickerDisplayMode.Input,
		)
	}
}
