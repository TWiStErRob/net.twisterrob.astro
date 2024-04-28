package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.lookup.sexagenaryCycle
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.units.canonicalMod
import java.time.LocalDateTime
import java.time.Month

@Suppress("UNUSED_PARAMETER")
public class ManualCalculator : BaZiCalculator {

	override fun calculate(dateTime: LocalDateTime): BaZi {
		return BaZi(
			year = calculateYear(dateTime),
			month = calculateMonth(dateTime),
			day = calculateDay(dateTime),
			hour = calculateHour(dateTime),
		)
	}

	private fun calculateYear(dateTime: LocalDateTime): BaZi.Pillar {
		val solar = SolarCoordinateApproximator().approximateSolarLongitude(dateTime)
		val isPreviousYear = (dateTime.month == Month.JANUARY || dateTime.month == Month.FEBRUARY)
				&& solar.apparentSolarLongitude.value in 270.0..<315.0
		val yearsSinceBeginning = dateTime.year - BASE_YEAR
		val newYearAdjustment = if (isPreviousYear) 1 else 0

		val yearInCycle = (yearsSinceBeginning - newYearAdjustment) % 60
		return BaZi.Pillar.sexagenaryCycle(yearInCycle)
	}

	private fun calculateMonth(dateTime: LocalDateTime): BaZi.Pillar {
		val solar = SolarCoordinateApproximator().approximateSolarLongitude(dateTime)
		val monthBranch = when (val ra = solar.apparentSolarLongitude.value) {
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
			else -> throw IllegalStateException("Invalid solar longitude: ${ra}")
		}
		val yearStem = calculateYear(dateTime).heavenlyStem
		// Only every 2nd combination exists in the sexagenary cycle, so order needs doubling.
		// Month 1 is not Zi (1), but Yin (3) so subtract 3 to make it 0-based and cycle to ensure positive.
		val monthStem = (yearStem.order * 2 + (monthBranch.order - 3).canonicalMod(12)) % 10
		return BaZi.Pillar(HeavenlyStem.at(monthStem + 1), monthBranch)
	}

	private fun calculateDay(dateTime: LocalDateTime): BaZi.Pillar {
		return BaZi.Pillar.sexagenaryCycle(0)
	}

	private fun calculateHour(dateTime: LocalDateTime): BaZi.Pillar {
		return BaZi.Pillar.sexagenaryCycle(0)
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
	}
}
