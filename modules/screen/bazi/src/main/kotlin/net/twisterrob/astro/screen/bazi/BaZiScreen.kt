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
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.widget.bazi.BaZi
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
		onYearIncreased = viewModel::increaseYear,
		onYearDecreased = viewModel::decreaseYear,
		onMonthIncreased = viewModel::increaseMonth,
		onMonthDecreased = viewModel::decreaseMonth,
		onDayIncreased = viewModel::increaseDay,
		onDayDecreased = viewModel::decreaseDay,
		onHourIncreased = viewModel::increaseHour,
		onHourDecreased = viewModel::decreaseHour,
	)
}

@Composable
private fun BaZiScreen(
	modifier: Modifier = Modifier,
	state: BaZiState,
	onRefresh: () -> Unit,
	onYearIncreased: () -> Unit = {},
	onYearDecreased: () -> Unit = {},
	onMonthIncreased: () -> Unit = {},
	onMonthDecreased: () -> Unit = {},
	onDayIncreased: () -> Unit = {},
	onDayDecreased: () -> Unit = {},
	onHourIncreased: () -> Unit = {},
	onHourDecreased: () -> Unit = {},
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
			onYearIncreased = onYearIncreased,
			onYearDecreased = onYearDecreased,
			onMonthIncreased = onMonthIncreased,
			onMonthDecreased = onMonthDecreased,
			onDayIncreased = onDayIncreased,
			onDayDecreased = onDayDecreased,
			onHourIncreased = onHourIncreased,
			onHourDecreased = onHourDecreased,
		)
	}
}

@Preview
@Composable
private fun BaZiScreenPreview() {
	AppTheme {
		BaZiScreen(
			state = BaZiState.now(),
			onRefresh = {},
			onYearIncreased = {},
			onYearDecreased = {},
			onMonthIncreased = {},
			onMonthDecreased = {},
			onDayIncreased = {},
			onDayDecreased = {},
			onHourIncreased = {},
			onHourDecreased = {},
		)
	}
}
