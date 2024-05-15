@file:OptIn(ExperimentalMaterial3Api::class)

package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.compose.isLandscape
import java.time.LocalTime

@Composable
internal fun DynamicTimePicker(
	state: TimePickerState,
	modifier: Modifier = Modifier,
	showModeToggle: Boolean = true,
	displayMode: DynamicTimePickerDisplayMode = defaultDynamicTimePickerDisplayMode,
	colors: TimePickerColors = TimePickerDefaults.colors(),
) {
	var displayModeState by rememberSaveable { mutableStateOf(displayMode) }
	Column(
		modifier = modifier,
	) {
		CompositionLocalProvider(
			LocalContentColor provides AlertDialogDefaults.titleContentColor,
			LocalTextStyle provides LocalTextStyle.current.merge(
				other = MaterialTheme.typography.labelLarge
			)
		) {
			val title = when (displayModeState) {
				DynamicTimePickerDisplayMode.Input -> stringResource(R.string.screen_bazi__time_picker_mode_input_title)
				DynamicTimePickerDisplayMode.Picker -> stringResource(R.string.screen_bazi__time_picker_mode_picker_title)
			}
			Row {
				Text(
					text = title,
					modifier = Modifier.padding(start = 24.dp, end = 12.dp, top = 16.dp, bottom = 24.dp)
				)
				if (showModeToggle && !isLandscape) {
					Spacer(modifier = Modifier.weight(1f))
					DisplayModeToggleButton(
						modifier = Modifier.padding(end = 12.dp, bottom = 12.dp),
						displayMode = displayModeState,
						onDisplayModeChange = { displayModeState = it },
					)
				}
			}
		}
		when {
			displayModeState == DynamicTimePickerDisplayMode.Input || isLandscape ->
				TimeInput(
					state = state,
					modifier = Modifier.align(Alignment.CenterHorizontally),
					colors = colors,
				)

			displayModeState == DynamicTimePickerDisplayMode.Picker ->
				TimePicker(
					state = state,
					modifier = Modifier.align(Alignment.CenterHorizontally),
					colors = colors,
				)
		}
	}
}

internal enum class DynamicTimePickerDisplayMode {
	Input,
	Picker,

	;
}

private val defaultDynamicTimePickerDisplayMode: DynamicTimePickerDisplayMode
	@Composable
	@ReadOnlyComposable
	get() = if (isLandscape) {
		DynamicTimePickerDisplayMode.Input
	} else {
		DynamicTimePickerDisplayMode.Picker
	}

@Composable
private fun DisplayModeToggleButton(
	displayMode: DynamicTimePickerDisplayMode,
	onDisplayModeChange: (DynamicTimePickerDisplayMode) -> Unit,
	modifier: Modifier = Modifier,
) {
	if (displayMode == DynamicTimePickerDisplayMode.Picker) {
		IconButton(onClick = { onDisplayModeChange(DynamicTimePickerDisplayMode.Input) }, modifier = modifier) {
			Icon(
				imageVector = Filled.Edit,
				contentDescription = stringResource(R.string.screen_bazi__time_picker_mode_picker_to_input_cd)
			)
		}
	} else {
		IconButton(onClick = { onDisplayModeChange(DynamicTimePickerDisplayMode.Picker) }, modifier = modifier) {
			Icon(
				imageVector = Filled.DateRange,
				contentDescription = stringResource(R.string.screen_bazi__time_picker_mode_input_to_picker_cd)
			)
		}
	}
}

@Preview
@Composable
private fun PickerPreview() {
	AppTheme {
		DynamicTimePicker(
			state = TimePickerState.now(),
			displayMode = DynamicTimePickerDisplayMode.Picker,
		)
	}
}

@Preview
@Composable
private fun PickerFixedPreview() {
	AppTheme {
		DynamicTimePicker(
			state = TimePickerState.now(),
			displayMode = DynamicTimePickerDisplayMode.Picker,
			showModeToggle = false,
		)
	}
}

@Preview
@Composable
private fun InputPreview() {
	AppTheme {
		DynamicTimePicker(
			state = TimePickerState.now(),
			displayMode = DynamicTimePickerDisplayMode.Input,
		)
	}
}

@Preview
@Composable
private fun InputFixedPreview() {
	AppTheme {
		DynamicTimePicker(
			state = TimePickerState.now(),
			displayMode = DynamicTimePickerDisplayMode.Input,
			showModeToggle = false,
		)
	}
}

private fun TimePickerState.Companion.now(): TimePickerState {
	val time = LocalTime.now()
	return TimePickerState(
		initialHour = time.hour,
		initialMinute = time.minute,
		is24Hour = false,
	)
}
