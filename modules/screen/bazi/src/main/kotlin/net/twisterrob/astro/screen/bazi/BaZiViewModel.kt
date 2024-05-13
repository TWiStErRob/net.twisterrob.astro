package net.twisterrob.astro.screen.bazi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.twisterrob.astro.bazi.SolarCalculator
import net.twisterrob.astro.bazi.model.BaZi
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
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
			bazi = bazi(dateTime),
			dateTime = DateTimeState(
				dateTime = dateTime,
				isPickingDate = false,
				isPickingTime = false,
			),
		)
	})
	internal val uiState: StateFlow<BaZiState> = _uiState.asStateFlow()

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

	private fun updateDateTime(adjuster: (ZonedDateTime) -> Temporal) {
		_uiState.update { state ->
			val dateTime = adjuster(state.dateTime.dateTime) as ZonedDateTime
			BaZiState(
				bazi = bazi(dateTime),
				dateTime = state.dateTime.copy(
					dateTime = dateTime,
					isPickingDate = false,
					isPickingTime = false,
				),
			)
		}
	}

	private fun bazi(dateTime: ZonedDateTime): BaZi {
		// Hack to adjust for GMT+1 / DST, will fix when TZ handling is implemented.
		val localTime = dateTime.toLocalDateTime().minusHours(1)
		return SolarCalculator().calculate(localTime)
	}

	internal fun resetToToday() {
		updateDateTime { current ->
			LocalDate.now().atTime(current.toLocalTime()).atZone(current.zone)
		}
	}

	internal fun resetToNow() {
		updateDateTime { current ->
			// Hack to adjust for GMT+1 / DST, will fix when TZ handling is implemented.
			current.atTime(LocalTime.now().minusHours(1))
		}
	}

	internal fun pickDate() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingDate = true)) }
	}

	internal fun pickTime() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingTime = true)) }
	}

	internal fun hideDatePicker() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingDate = false)) }
	}

	internal fun hideTimePicker() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingTime = false)) }
	}

	internal fun selectDate(date: LocalDate) {
		updateDateTime { current ->
			val keepOriginalTime = date.atTime(current.toLocalTime())
			keepOriginalTime.atZone(current.zone)
		}
	}

	internal fun selectTime(time: LocalTime) {
		updateDateTime { current ->
			// Hack to adjust for GMT+1 / DST, will fix when TZ handling is implemented.
			current.atTime(time).minusHours(1)
		}
	}
}

private fun ZonedDateTime.atTime(localTime: LocalTime): ZonedDateTime =
	ZonedDateTime.of(toLocalDate(), localTime, zone)

internal data class BaZiState(
	val bazi: BaZi,
	val dateTime: DateTimeState,
)

internal data class DateTimeState(
	val dateTime: ZonedDateTime,
	val isPickingDate: Boolean,
	val isPickingTime: Boolean,
)
