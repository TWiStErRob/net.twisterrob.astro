package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.lookup.atHour
import net.twisterrob.astro.bazi.lookup.lookupHour
import net.twisterrob.astro.bazi.lookup.sexagenaryCycle
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.units.canonicalMod
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month
import java.time.ZoneOffset
import java.time.ZonedDateTime

/**
 * Calculator based on solar terms, and calculations from various sources.
 * The goal was to make it as human-friendly as possible.
 */
public class ManualCalculator : BaZiCalculator {
	private val solar = SolarCoordinateApproximator()

	override fun calculate(dateTime: LocalDateTime): BaZi {
		require(GREGORIAN_CUTOVER <= dateTime) {
			"Date ${dateTime} is before the Gregorian Calendar begins at ${GREGORIAN_CUTOVER}."
		}
		return BaZi(
			year = calculateYear(dateTime),
			month = calculateMonth(dateTime),
			day = calculateDay(dateTime),
			hour = calculateHour(dateTime),
		)
	}

	private fun calculateYear(dateTime: LocalDateTime): BaZi.Pillar {
		val sun = solar.approximateSolarLongitude(dateTime)

		@Suppress("detekt.MagicNumber")
		val isPreviousYear = (dateTime.month == Month.JANUARY || dateTime.month == Month.FEBRUARY)
				&& sun.apparentSolarLongitude.value in 270.0..<315.0
		val yearsSinceBeginning = dateTime.year - BASE_YEAR
		val newYearAdjustment = if (isPreviousYear) 1 else 0

		val yearInCycle = (yearsSinceBeginning - newYearAdjustment) % BaZi.Pillar.SEXAGENARY_CYCLE
		return BaZi.Pillar.sexagenaryCycle(yearInCycle)
	}

	private fun calculateMonth(dateTime: LocalDateTime): BaZi.Pillar {
		val sun = solar.approximateSolarLongitude(dateTime)
		val monthBranch = solarTerm(sun.apparentSolarLongitude.value)
		val yearStem = calculateYear(dateTime).heavenlyStem
		// Only every 2nd combination exists in the sexagenary cycle, so order needs doubling.
		// Month 1 is not Zi (1), but Yin (3) so subtract 3 to make it 0-based and cycle to ensure positive.
		@Suppress("detekt.MagicNumber")
		val cycle = (monthBranch.order - 3).canonicalMod(EarthlyBranch.COUNT)
		val monthStem = (yearStem.order * 2 + cycle) % HeavenlyStem.COUNT
		return BaZi.Pillar(HeavenlyStem.at(monthStem + 1), monthBranch)
	}

	@Suppress("detekt.CyclomaticComplexMethod")
	private fun solarTerm(apprentSolarLongitude: Double): EarthlyBranch =
		@Suppress("detekt.MagicNumber")
		when (apprentSolarLongitude) {
			in 315.0..<345.0 -> EarthlyBranch.Yin
			in 345.0..<360.0 -> EarthlyBranch.Mao
			in 0.0..<15.0 -> EarthlyBranch.Mao
			in 15.0..<45.0 -> EarthlyBranch.Chen
			in 45.0..<75.0 -> EarthlyBranch.Si
			in 75.0..<105.0 -> EarthlyBranch.Wu
			in 105.0..<135.0 -> EarthlyBranch.Wei
			in 135.0..<165.0 -> EarthlyBranch.Shen
			in 165.0..<195.0 -> EarthlyBranch.You
			in 195.0..<225.0 -> EarthlyBranch.Xu
			in 225.0..<255.0 -> EarthlyBranch.Hai
			in 255.0..<285.0 -> EarthlyBranch.Zi
			in 285.0..<315.0 -> EarthlyBranch.Chou
			else -> error("Invalid solar longitude: ${apprentSolarLongitude}")
		}

	private fun calculateDay(dateTime: LocalDateTime): BaZi.Pillar {
		val jd = Math.round(dateTime.julianDayTime)
		// See https://ytliu0.github.io/ChineseCalendar/sexagenary.html
		val dayStem = HeavenlyStem.at(((jd - 1) % HeavenlyStem.COUNT).toInt().canonicalMod(HeavenlyStem.COUNT) + 1)
		val dayBranch = EarthlyBranch.at(((jd + 1) % EarthlyBranch.COUNT).toInt().canonicalMod(EarthlyBranch.COUNT) + 1)
		return BaZi.Pillar(dayStem, dayBranch)
	}

	private fun calculateHour(dateTime: LocalDateTime): BaZi.Pillar {
		val hourBranch = EarthlyBranch.atHour(dateTime.hour)
		val dayStem = calculateDay(dateTime.sexagenaryDayBase).heavenlyStem
		val hourStem = HeavenlyStem.lookupHour(dayStem, hourBranch)
		return BaZi.Pillar(hourStem, hourBranch)
	}

	private companion object {

		/**
		 * The base year of the sexagenary cycle is traditionally 2637 BCE,
		 * which is attributed as the invention of the calendar by the legendary Emperor Huangdi.
		 *
		 * However, his reign started in 2697 BCE, and the cycle is 60 years long.
		 * Using an astronomical year (where 0 is a year, and BCE is negative) simplifies calculations.
		 *
		 * References:
		 *  * https://en.wikipedia.org/wiki/Sexagenary_cycle#Conversion_between_cyclic_years_and_Western_years
		 *  * https://en.wikipedia.org/wiki/Yellow_Emperor
		 */
		private const val BASE_YEAR: Int = -2696

		/**
		 * References:
		 *  * https://en.wikipedia.org/wiki/Gregorian_calendar
		 */
		private val GREGORIAN_CUTOVER: LocalDateTime =
			ZonedDateTime.of(LocalDate.of(1582, 10, 15), LocalTime.MIDNIGHT, ZoneOffset.UTC).toLocalDateTime()

		/**
		 * The sexagenary hours start at 23:00, we need to use the day that time is "starting" which is the next day.
		 */
		private val LocalDateTime.sexagenaryDayBase: LocalDateTime
			get() =
				@Suppress("detekt.MagicNumber")
				if (this.hour == 23) {
					this.plusHours(1)
				} else {
					this
				}
	}
}
