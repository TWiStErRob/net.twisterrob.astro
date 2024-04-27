package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.model.BaZi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Calculates Ba Zi based on a given instant in time.
 */
public interface BaZiCalculator {

	/**
	 * Calculates Ba Zi based on a given instant in time.
	 */
	public fun calculate(dateTime: LocalDateTime): BaZi

	/**
	 * Calculates Ba Zi based on a given date, no time.
	 */
	public fun calculate(date: LocalDate): BaZi =
		calculate(date.atTime(MIDDAY)).copy(hour = null)

	public companion object {

		private val MIDDAY = LocalTime.of(0, 0, 0)
	}
}
