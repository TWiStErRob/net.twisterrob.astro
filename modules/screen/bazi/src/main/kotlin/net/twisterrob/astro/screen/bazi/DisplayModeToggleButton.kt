package net.twisterrob.astro.screen.bazi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
internal fun DisplayModeToggleButton(
	displayMode: TimePickerDisplayMode,
	onDisplayModeChange: (TimePickerDisplayMode) -> Unit,
	modifier: Modifier = Modifier,
) {
	if (displayMode == TimePickerDisplayMode.Picker) {
		IconButton(
			onClick = { onDisplayModeChange(TimePickerDisplayMode.Input) },
			modifier = modifier
		) {
			Icon(
				imageVector = Icons.Filled.Edit,
				contentDescription = stringResource(R.string.screen_bazi__time_picker_mode_picker_to_input_cd)
			)
		}
	} else {
		IconButton(
			onClick = { onDisplayModeChange(TimePickerDisplayMode.Picker) },
			modifier = modifier
		) {
			Icon(
				imageVector = Icons.Filled.DateRange,
				contentDescription = stringResource(R.string.screen_bazi__time_picker_mode_input_to_picker_cd)
			)
		}
	}
}
