package net.twisterrob.astro.screen.bazi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.twisterrob.astro.bazi.SolarCalculator
import net.twisterrob.astro.bazi.model.BaZi
import java.time.Duration
import java.time.Period
import java.time.ZonedDateTime
import java.time.temporal.Temporal
import java.time.temporal.TemporalAmount

/**
 * [ViewModel] for [BaZiScreen].
 */
@Suppress("detekt.TooManyFunctions")
public class BaZiViewModel : ViewModel() {
	private val _uiState = MutableStateFlow(BaZiState.now())
	internal val uiState: StateFlow<BaZiState> = _uiState.asStateFlow()

	internal fun refresh() {
		_uiState.update { _ ->
			BaZiState.now()
		}
	}

	//@formatter:off
	internal fun increaseYear() { update(Period.ofYears(+1)) }
	internal fun decreaseYear() { update(Period.ofYears( -1)) }
	internal fun increaseMonth() { update(Period.ofMonths(+1)) }
	internal fun decreaseMonth() { update(Period.ofMonths(-1)) }
	internal fun increaseDay() { update(Period.ofDays(+1)) }
	internal fun decreaseDay() { update(Period.ofDays(-1)) }
	internal fun increaseHour() { update(Duration.ofHours(+1)) }
	internal fun decreaseHour() { update(Duration.ofHours(-1)) }
	//@formatter:on

	private fun update(amount: TemporalAmount) {
		update(amount::addTo)
	}

	private fun update(adjuster: (Temporal) -> Temporal) {
		_uiState.update { state ->
			val dateTime = adjuster(state.dateTime) as ZonedDateTime
			BaZiState(
				dateTime = dateTime,
				baZi = SolarCalculator().calculate(dateTime.toLocalDateTime()),
			)
		}
	}
}

internal class BaZiState(
	val dateTime: ZonedDateTime,
	val baZi: BaZi,
) {
	companion object {
		fun now(): BaZiState {
			val dateTime = ZonedDateTime.now()
			return BaZiState(
				dateTime,
				SolarCalculator().calculate(dateTime.toLocalDateTime())
			)
		}
	}
}
