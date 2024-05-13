package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
			TextButton({ onSelectTime(pickerState.selectedLocalTime) }) { Text("OK") }
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
				state = pickerState,
				modifier = Modifier.align(Alignment.CenterHorizontally)
			)
		}
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
