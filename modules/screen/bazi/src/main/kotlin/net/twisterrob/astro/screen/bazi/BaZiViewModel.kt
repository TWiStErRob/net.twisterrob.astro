package net.twisterrob.astro.screen.bazi

import androidx.annotation.VisibleForTesting
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
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAmount

/**
 * [ViewModel] for [BaZiScreen].
 */
@Suppress("detekt.TooManyFunctions")
public class BaZiViewModel : ViewModel() {
	private val _uiState = MutableStateFlow(
		adjustDateTimeState(
			DateTimeState(
				dateTime = ZonedDateTime.now(),
				isPickingDate = false,
				isPickingTime = false,
				isPickingZone = false,
			),
		) { it }
	)
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
	internal fun increaseMinute() { updateDateTime(Duration.ofMinutes(+1)) }
	internal fun decreaseMinute() { updateDateTime(Duration.ofMinutes(-1)) }
	//@formatter:on

	private fun updateDateTime(amount: TemporalAmount) {
		updateDateTime { current ->
			current.plus(amount)
		}
	}

	private fun updateDateTime(adjuster: (ZonedDateTime) -> ZonedDateTime) {
		_uiState.update { state ->
			adjustDateTimeState(state.dateTime, adjuster)
		}
	}

	private fun adjustDateTimeState(
		dateTimeState: DateTimeState,
		adjuster: (ZonedDateTime) -> ZonedDateTime,
	): BaZiState {
		val dateTime = adjuster(dateTimeState.dateTime)
			.with(ChronoField.SECOND_OF_MINUTE, 0)
			.with(ChronoField.NANO_OF_SECOND, 0)
		return BaZiState(
			bazi = bazi(dateTime),
			dateTime = dateTimeState.copy(
				dateTime = dateTime,
				isPickingDate = false,
				isPickingTime = false,
				isPickingZone = false,
			),
		)
	}

	private fun bazi(dateTime: ZonedDateTime): BaZi {
		// Hack to adjust for GMT+1 / DST, will fix when TZ handling is implemented.
		val localTime = dateTime.toLocalDateTime().minusHours(1)
		return SolarCalculator().calculate(localTime)
	}

	internal fun resetToToday() {
		selectDate(LocalDate.now())
	}

	internal fun resetToNow() {
		selectTime(LocalTime.now())
	}

	internal fun resetToZone() {
		selectZone(ZoneId.systemDefault())
	}

	internal fun pickDate() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingDate = true)) }
	}

	internal fun pickTime() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingTime = true)) }
	}

	internal fun pickZone() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingZone = true)) }
	}

	internal fun hideDatePicker() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingDate = false)) }
	}

	internal fun hideTimePicker() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingTime = false)) }
	}

	internal fun hideZonePicker() {
		_uiState.update { it.copy(dateTime = it.dateTime.copy(isPickingZone = false)) }
	}

	/**
	 * Allow screenshot tests to set "now" to a specific instant, so that rendering is consistent.
	 */
	@VisibleForTesting(otherwise = VisibleForTesting.NONE)
	public fun select(dateTime: ZonedDateTime) {
		updateDateTime { _ -> dateTime }
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

	internal fun selectZone(zone: ZoneId) {
		updateDateTime { current ->
			current.withZoneSameLocal(zone)
		}
	}

	private companion object {
		fun ZonedDateTime.atTime(localTime: LocalTime): ZonedDateTime =
			ZonedDateTime.of(this.toLocalDate(), localTime, this.zone)
	}
}

internal data class BaZiState(
	val bazi: BaZi,
	val dateTime: DateTimeState,
)

internal data class DateTimeState(
	val dateTime: ZonedDateTime,
	val isPickingDate: Boolean,
	val isPickingTime: Boolean,
	val isPickingZone: Boolean,
)
