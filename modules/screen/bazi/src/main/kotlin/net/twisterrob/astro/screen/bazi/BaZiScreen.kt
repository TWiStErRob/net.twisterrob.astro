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

		onReset = viewModel::reset,

		onPickDate = viewModel::pickDate,
		onHideDatePicker = viewModel::hideDatePicker,
		onResetToToday = viewModel::resetToToday,
		onSelectDate = viewModel::selectDate,

		onPickTime = viewModel::pickTime,
		onHideTimePicker = viewModel::hideTimePicker,
		onResetToNow = viewModel::resetToNow,
		onSelectTime = viewModel::selectTime,

		onPickZone = viewModel::pickZone,
		onHideZonePicker = viewModel::hideZonePicker,
		onResetToZone = viewModel::resetToZone,
		onSelectZone = viewModel::selectZone,
		onPickOffset = viewModel::pickZone,

		onYearAdd = viewModel::increaseYear,
		onYearSubtract = viewModel::decreaseYear,
		onMonthAdd = viewModel::increaseMonth,
		onMonthSubtract = viewModel::decreaseMonth,
		onDayAdd = viewModel::increaseDay,
		onDaySubtract = viewModel::decreaseDay,
		onHourAdd = viewModel::increaseHour,
		onHourSubtract = viewModel::decreaseHour,
		onMinuteAdd = viewModel::increaseMinute,
		onMinuteSubtract = viewModel::decreaseMinute,
	)
}

@Composable
private fun BaZiScreen(
	state: BaZiState,

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
	onHideZonePicker: () -> Unit,
	onResetToZone: () -> Unit,
	onSelectZone: (ZoneId) -> Unit,
	onPickOffset: () -> Unit,

	onYearAdd: () -> Unit,
	onYearSubtract: () -> Unit,
	onMonthAdd: () -> Unit,
	onMonthSubtract: () -> Unit,
	onDayAdd: () -> Unit,
	onDaySubtract: () -> Unit,
	onHourAdd: () -> Unit,
	onHourSubtract: () -> Unit,
	onMinuteAdd: () -> Unit,
	onMinuteSubtract: () -> Unit,

	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier,
	) {
		DateTimeZonePickers(
			modifier = Modifier
				.fillMaxWidth()
				.padding(4.dp),
			state = state.dateTime,

			onReset = onReset,

			onPickDate = onPickDate,
			onHideDatePicker = onHideDatePicker,
			onResetToToday = onResetToToday,
			onSelectDate = onSelectDate,

			onPickTime = onPickTime,
			onHideTimePicker = onHideTimePicker,
			onResetToNow = onResetToNow,
			onSelectTime = onSelectTime,

			onPickZone = onPickZone,
			onSelectZone = onSelectZone,
			onHideZonePicker = onHideZonePicker,
			onResetToZone = onResetToZone,
			onPickOffset = onPickOffset,
		)
		BaZiChart(
			modifier = Modifier
				.padding(top = 24.dp),
			bazi = state.bazi,

			onYearAdd = onYearAdd,
			onYearSubtract = onYearSubtract,
			onMonthAdd = onMonthAdd,
			onMonthSubtract = onMonthSubtract,
			onDayAdd = onDayAdd,
			onDaySubtract = onDaySubtract,
			onHourAdd = onHourAdd,
			onHourSubtract = onHourSubtract,
			onMinuteAdd = onMinuteAdd,
			onMinuteSubtract = onMinuteSubtract,
		)
	}
}

@Preview
@Composable
private fun Preview() {
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
			onHideZonePicker = {},
			onResetToZone = {},
			onSelectZone = {},
			onPickOffset = {},

			onYearAdd = {},
			onYearSubtract = {},
			onMonthAdd = {},
			onMonthSubtract = {},
			onDayAdd = {},
			onDaySubtract = {},
			onHourAdd = {},
			onHourSubtract = {},
			onMinuteAdd = {},
			onMinuteSubtract = {},
		)
	}
}
