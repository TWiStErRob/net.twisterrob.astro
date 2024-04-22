package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.lookup.atHour
import net.twisterrob.astro.bazi.lookup.isLeapYear
import net.twisterrob.astro.bazi.lookup.lookupHour
import net.twisterrob.astro.bazi.lookup.lookupSolarMonth
import net.twisterrob.astro.bazi.lookup.monthAtSolarDay
import net.twisterrob.astro.bazi.lookup.sexagenaryCycle
import net.twisterrob.astro.bazi.lookup.solarYear
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import kotlin.math.floor

/**
 * Mostly based on:
 *  * https://en.wikipedia.org/wiki/Sexagenary_cycle
 */
public class SexagenaryWikiCalculator : BaZiCalculator {

	override fun calculate(dateTime: LocalDateTime): BaZi {
		val year = calculateYear(dateTime)
		val month = calculateMonth(dateTime)
		val day = calculateDay(dateTime)
		val hour = calculateHour(dateTime)
		return BaZi(year, month, day, hour)
	}

	private fun calculateYear(date: LocalDateTime): BaZi.Pillar {
		fun LocalDateTime.isInSameSolarYear(): Boolean =
			this.toLocalDate().let { it.year == it.solarYear }

		val yearsSinceBeginning = date.year - BASE_YEAR
		val newYearAdjustment = if (!date.isInSameSolarYear()) 1 else 0

		val yearInCycle = (yearsSinceBeginning - newYearAdjustment) % 60
		return BaZi.Pillar.sexagenaryCycle(yearInCycle)
	}

	private fun calculateMonth(date: LocalDateTime): BaZi.Pillar {
		val monthBranch = EarthlyBranch.monthAtSolarDay(date.toLocalDate())
		val yearStem = calculateYear(date).heavenlyStem
		val monthStem = HeavenlyStem.lookupSolarMonth(yearStem, monthBranch)
		return BaZi.Pillar(monthStem, monthBranch)
	}

	private fun calculateDay(date: LocalDateTime): BaZi.Pillar {
		val stem = HeavenlyStem.at(stemIndex(date))
		val branch = EarthlyBranch.at(branchIndex(date))
		return BaZi.Pillar(stem, branch)
	}

	private fun stemIndex(date: LocalDateTime): Int {
		val day = date.dayOfMonth % 10

		@Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
		val month = when (date.month) {
			Month.JANUARY -> if (date.year.isLeapYear()) 7 else 8
			Month.FEBRUARY -> if (date.year.isLeapYear()) 8 else 9
			Month.MARCH -> 7
			Month.APRIL -> 8
			Month.MAY -> 8
			Month.JUNE -> 9
			Month.JULY -> 9
			Month.AUGUST -> 0
			Month.SEPTEMBER -> 1
			Month.OCTOBER -> 1
			Month.NOVEMBER -> 2
			Month.DECEMBER -> 2
		}
		// mod (%) has multiple solutions, to definitely get the smallest positive one:
		// add `3 * 40 = 120 > 100`, which is the least to account for maximum of 100 years' modulus.
		val y = (date.year % 100 + 3 * 40) % 40
		val year = (5 * y + y / 4) % 10
		val century = if (GREGORIAN_START < date.toLocalDate()) {
			val c = date.year / 100
			(4 * c + c / 4 + 2) % 10
		} else { // Julian
			// mod (%) has multiple solutions, to definitely get the smallest positive one:
			// add `14 * 2 = 28 > 26` to account for -2600 (start of calendar) worth of centuries.
			val c = (date.year / 100 + 14 * 2) % 2
			(5 * c) % 10
		}
		var sum = day + month + year + century
		while (sum > 10) {
			sum -= 10
		}
		return sum
	}

	private fun branchIndex(date: LocalDateTime): Int {
		val day = date.dayOfMonth % 12

		@Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
		val month = when (date.month) {
			Month.JANUARY -> if (date.year.isLeapYear()) 7 else 8
			Month.FEBRUARY -> if (date.year.isLeapYear()) 2 else 3
			Month.MARCH -> 7
			Month.APRIL -> 2
			Month.MAY -> 8
			Month.JUNE -> 3
			Month.JULY -> 9
			Month.AUGUST -> 4
			Month.SEPTEMBER -> 11
			Month.OCTOBER -> 5
			Month.NOVEMBER -> 0
			Month.DECEMBER -> 6
		}
		// mod (%) has multiple solutions, to definitely get the smallest positive one:
		// add `7 * 16 = 112 > 100`, which is the least to account for maximum of 100 years' modulus.
		val y = (date.year % 100 + 7 * 16) % 16
		val year = (5 * y + y / 4) % 12
		val century = if (GREGORIAN_START < date.toLocalDate()) {
			val c = date.year / 100
			(8 * c + c / 4 + 2) % 12
		} else {
			// mod (%) has multiple solutions, to definitely get the smallest positive one:
			// add `7 * 4 = 28 > 26` to account for -2600 (start of calendar) worth of centuries.
			val c = (date.year / 100 + 7 * 4) % 4
			(9 * c) % 12
		}
		var sum = day + month + year + century
		while (sum > 12) {
			sum -= 12
		}
		return sum
	}

	private fun calculateHour(date: LocalDateTime): BaZi.Pillar {
		val /*时支*/ hourBranch = EarthlyBranch.atHour(date.hour)
		val /*日干*/ dayStem = calculateDay(date).heavenlyStem
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

		private val GREGORIAN_START: LocalDate = LocalDate.of(1582, Month.OCTOBER, 15)

		// https://en.wikipedia.org/wiki/Sexagenary_cycle#Examples_2
		// Hidden in "More detailed examples", there's an "Algorithm for mental calculation", this is a draft impl of that.
		// Theoretically it might be used as `BaZi.Pillar.sexagenaryCycle(pillarIndex(date))`
		// Added by https://en.wikipedia.org/wiki/User_talk:Q5968661
		// Added in https://en.wikipedia.org/w/index.php?title=Sexagenary_cycle&diff=prev&oldid=857987234
		// Another version: https://en.wikipedia.org/wiki/Talk:Sexagenary_cycle#New_sexagenary_day_chart
		@Suppress("unused") // Abandoned, because can't figure out what `year( mod 400) mod 80( mod 12) x 5` means. 
		private fun pillarIndex(date: LocalDateTime): Int {
			val year = date.year
			val y = (year % 400 % 80 % 12 * 5 + (year % 400 % 80) / 4) % 60
			val c = if (GREGORIAN_START < date.toLocalDate()) {
				year / 400 - year / 100 + 10
			} else {
				8
			}
			val i = if (date.month == Month.JANUARY || date.month == Month.FEBRUARY) {
				if (date.year.isLeapYear()) 6 else 5
			} else {
				0
			}
			val m = (date.monthValue + 1) % 2 * 30 + Math.round(floor(0.6 * date.dayOfMonth + 1)).toInt() - 3 - i
			return (y + c + m + date.dayOfMonth) % 60
		}
	}
}
