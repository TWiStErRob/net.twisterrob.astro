package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.twisterrob.astro.bazi.SolarCalculator
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.widget.bazi.chart.BaZiChart
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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
		onRefresh = viewModel::refresh,
		onPickDate = viewModel::pickDate,
		onHideDatePicker = viewModel::hideDatePicker,
		onDateSelected = viewModel::selectDate,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BaZiScreen(
	state: BaZiState,
	onRefresh: () -> Unit,
	onPickDate: () -> Unit,
	onHideDatePicker: () -> Unit,
	onDateSelected: (Instant) -> Unit,
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
	if (state.isPickingDate) {
		val datePickerState = rememberDatePickerState(
			initialSelectedDateMillis = state.dateTime.toInstant().toEpochMilli()
		)
		DatePickerDialog(
			confirmButton = {
				fun convertToOnDateSelected() {
					onDateSelected(Instant.ofEpochMilli(datePickerState.selectedDateMillis!!))
				}
				TextButton(::convertToOnDateSelected) { Text("OK") }
			},
			onDismissRequest = onHideDatePicker,
			dismissButton = {
				TextButton(onRefresh) { Text("Now") }
				TextButton(onHideDatePicker) { Text("Cancel") }
			}
		) {
			DatePicker(state = datePickerState)
		}
	}

	Column(
		modifier = modifier,
	) {
		Text(
			text = state.dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)),
			modifier = Modifier
				.align(CenterHorizontally)
				.clickable(onClick = onPickDate)
				.padding(4.dp),
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
				dateTime = ZonedDateTime.now(),
				bazi = SolarCalculator().calculate(LocalDateTime.now()),
				isPickingDate = false,
			),
			onRefresh = {},
			onPickDate = {},
			onHideDatePicker = {},
			onDateSelected = {},
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

@Preview
@Composable
private fun BaZiScreenDatePickerPreview() {
	AppTheme {
		BaZiScreen(
			state = BaZiState(
				dateTime = ZonedDateTime.now(),
				bazi = SolarCalculator().calculate(LocalDateTime.now()),
				isPickingDate = true,
			),
			onRefresh = {},
			onPickDate = {},
			onHideDatePicker = {},
			onDateSelected = {},
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
