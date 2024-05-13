package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.twisterrob.astro.bazi.SolarCalculator
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.widget.bazi.chart.BaZiChart
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Screen that displays the current BaZi.
 */
@Composable
public fun BaZiScreen(
	modifier: Modifier = Modifier,
	viewModel: BaZiViewModel = viewModel(),
) {
	val state by viewModel.uiState.collectAsState()
	BaZiScreen(
		modifier = modifier,
		state = state,
		onPickDate = viewModel::pickDate,
		onHideDatePicker = viewModel::hideDatePicker,
		onResetToToday = viewModel::resetToToday,
		onDateSelected = viewModel::selectDate,

		onPickTime = viewModel::pickTime,
		onHideTimePicker = viewModel::hideTimePicker,
		onResetToNow = viewModel::resetToNow,
		onTimeSelected = viewModel::selectTime,

		onPickZone = viewModel::pickZone,
		onHideZonePicker = viewModel::hideZonePicker,
		onResetToZone = viewModel::resetToZone,
		onZoneSelected = viewModel::selectZone,
		onPickOffset = viewModel::pickOffset,

		onYearAdd = viewModel::increaseYear,
		onYearSubtract = viewModel::decreaseYear,
		onMonthAdd = viewModel::increaseMonth,
		onMonthSubtract = viewModel::decreaseMonth,
		onDayAdd = viewModel::increaseDay,
		onDaySubtract = viewModel::decreaseDay,
		onHourAdd = viewModel::increaseHour,
		onHourSubtract = viewModel::decreaseHour,
	)
}

@Composable
private fun BaZiScreen(
	state: BaZiState,

	onPickDate: () -> Unit,
	onHideDatePicker: () -> Unit,
	onResetToToday: () -> Unit,
	onDateSelected: (LocalDate) -> Unit,

	onPickTime: () -> Unit,
	onHideTimePicker: () -> Unit,
	onResetToNow: () -> Unit,
	onTimeSelected: (LocalTime) -> Unit,

	onPickZone: () -> Unit,
	onHideZonePicker: () -> Unit,
	onResetToZone: () -> Unit,
	onZoneSelected: (ZoneId) -> Unit,
	onPickOffset: () -> Unit,

	onYearAdd: () -> Unit,
	onYearSubtract: () -> Unit,
	onMonthAdd: () -> Unit,
	onMonthSubtract: () -> Unit,
	onDayAdd: () -> Unit,
	onDaySubtract: () -> Unit,
	onHourAdd: () -> Unit,
	onHourSubtract: () -> Unit,

	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier,
	) {
		DateTimeEditors(
			modifier = Modifier
				.fillMaxWidth()
				.padding(4.dp),
			state = state.dateTime,
			onPickDate = onPickDate,
			onHideDatePicker = onHideDatePicker,
			onResetToToday = onResetToToday,
			onDateSelected = onDateSelected,
			onPickTime = onPickTime,
			onHideTimePicker = onHideTimePicker,
			onResetToNow = onResetToNow,
			onTimeSelected = onTimeSelected,
			onPickZone = onPickZone,
			onZoneSelected = onZoneSelected,
			onHideZonePicker = onHideZonePicker,
			onResetToZone = onResetToZone,
			onPickOffset = onPickOffset,
		)
		BaZiChart(
			bazi = state.bazi,
			modifier = Modifier
				.padding(top = 24.dp),
			onYearAdd = onYearAdd,
			onYearSubtract = onYearSubtract,
			onMonthAdd = onMonthAdd,
			onMonthSubtract = onMonthSubtract,
			onDayAdd = onDayAdd,
			onDaySubtract = onDaySubtract,
			onHourAdd = onHourAdd,
			onHourSubtract = onHourSubtract,
		)
	}
}

@Preview
@Composable
private fun BaZiScreenPreview() {
	AppTheme {
		BaZiScreen(
			state = BaZiState(
				bazi = SolarCalculator().calculate(LocalDateTime.now()),
				dateTime = DateTimeState(
					dateTime = ZonedDateTime.now(),
					isPickingDate = false,
					isPickingTime = false,
					isPickingZone = false,
				),
			),
			onPickDate = {},
			onHideDatePicker = {},
			onResetToToday = {},
			onDateSelected = {},

			onPickTime = {},
			onHideTimePicker = {},
			onResetToNow = {},
			onTimeSelected = {},

			onPickZone = {},
			onHideZonePicker = {},
			onResetToZone = {},
			onZoneSelected = {},
			onPickOffset = {},

			onYearAdd = {},
			onYearSubtract = {},
			onMonthAdd = {},
			onMonthSubtract = {},
			onDayAdd = {},
			onDaySubtract = {},
			onHourAdd = {},
			onHourSubtract = {},
		)
	}
}
