package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import net.twisterrob.astro.widget.bazi.BaZi
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
	onRefresh: () -> Unit,
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
		Text(
			text = state.dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)),
			modifier = Modifier
				.align(CenterHorizontally)
				.clickable(onClick = onRefresh)
				.padding(4.dp),
		)
		BaZi(
			bazi = state.baZi,
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
				baZi = SolarCalculator().calculate(LocalDateTime.now()),
			),
			onRefresh = {},
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
