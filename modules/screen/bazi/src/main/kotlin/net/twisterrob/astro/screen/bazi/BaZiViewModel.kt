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
import java.time.temporal.ChronoField
import java.time.temporal.Temporal
import java.time.temporal.TemporalAmount

/**
 * [ViewModel] for [BaZiScreen].
 */
@Suppress("detekt.TooManyFunctions")
public class BaZiViewModel : ViewModel() {
	private val _uiState = MutableStateFlow(run {
		val dateTime = ZonedDateTime.now()
		BaZiState(
			dateTime = dateTime,
			bazi = bazi(dateTime),
		)
	})
	internal val uiState: StateFlow<BaZiState> = _uiState.asStateFlow()

	internal fun refresh() {
		updateDateTime { _ ->
			ZonedDateTime.now()
		}
	}

	//@formatter:off
	internal fun increaseYear() { updateDateTime(Period.ofYears(+1)) }
	internal fun decreaseYear() { updateDateTime(Period.ofYears( -1)) }
	internal fun increaseMonth() { updateDateTime(Period.ofMonths(+1)) }
	internal fun decreaseMonth() { updateDateTime(Period.ofMonths(-1)) }
	internal fun increaseDay() { updateDateTime(Period.ofDays(+1)) }
	internal fun decreaseDay() { updateDateTime(Period.ofDays(-1)) }
	internal fun increaseHour() { updateDateTime(Duration.ofHours(+1)) }
	internal fun decreaseHour() { updateDateTime(Duration.ofHours(-1)) }
	//@formatter:on

	private fun updateDateTime(amount: TemporalAmount) {
		updateDateTime {
			amount
				.addTo(it)
				// Reset time to a half an hour to make it easier to understand
				// as the sexagenary hours are changing on the hour.
				.with(ChronoField.MINUTE_OF_HOUR, @Suppress("detekt.MagicNumber") 30)
				.with(ChronoField.SECOND_OF_MINUTE, 0)
				.with(ChronoField.NANO_OF_SECOND, 0)
		}
	}

	private fun updateDateTime(adjuster: (Temporal) -> Temporal) {
		_uiState.update { state ->
			val dateTime = adjuster(state.dateTime) as ZonedDateTime
			BaZiState(
				dateTime = dateTime,
				bazi = bazi(dateTime),
			)
		}
	}

	private fun bazi(dateTime: ZonedDateTime): BaZi {
		// Hack to adjust for GMT+1 / DST, will fix when TZ handling is implemented.
		val localTime = dateTime.toLocalDateTime().minusHours(1)
		return SolarCalculator().calculate(localTime)
	}
}

internal class BaZiState(
	val dateTime: ZonedDateTime,
	val bazi: BaZi,
)
