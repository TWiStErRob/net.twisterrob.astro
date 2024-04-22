package net.twisterrob.astro.bazi.lookup

import net.twisterrob.astro.bazi.model.EarthlyBranch
import java.time.LocalDate
import java.time.Month.APRIL
import java.time.Month.AUGUST
import java.time.Month.DECEMBER
import java.time.Month.FEBRUARY
import java.time.Month.JANUARY
import java.time.Month.JULY
import java.time.Month.JUNE
import java.time.Month.MARCH
import java.time.Month.MAY
import java.time.Month.NOVEMBER
import java.time.Month.OCTOBER
import java.time.Month.SEPTEMBER
import java.time.chrono.IsoChronology

/**
 * References:
 *  * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
 */
public fun EarthlyBranch.Companion.monthAtSolarDay(date: LocalDate): EarthlyBranch {
	val year = date.year
	return if (date.solarYear.isLeapYear()) {
		when (date) {
			in LocalDate.of(year - 1, DECEMBER, 7)..<LocalDate.of(year, JANUARY, 6) -> EarthlyBranch.Zi
			in LocalDate.of(year, JANUARY, 6)..<LocalDate.of(year, FEBRUARY, 4) -> EarthlyBranch.Chou
			// --- Solar Year boundary.
			in LocalDate.of(year, FEBRUARY, 4)..<LocalDate.of(year, MARCH, 5) -> EarthlyBranch.Yin
			in LocalDate.of(year, MARCH, 5)..<LocalDate.of(year, APRIL, 5) -> EarthlyBranch.Mao
			in LocalDate.of(year, APRIL, 5)..<LocalDate.of(year, MAY, 5) -> EarthlyBranch.Chen
			in LocalDate.of(year, MAY, 5)..<LocalDate.of(year, JUNE, 5) -> EarthlyBranch.Si
			in LocalDate.of(year, JUNE, 5)..<LocalDate.of(year, JULY, 6) -> EarthlyBranch.Wu
			in LocalDate.of(year, JULY, 6)..<LocalDate.of(year, AUGUST, 7) -> EarthlyBranch.Wei
			in LocalDate.of(year, AUGUST, 7)..<LocalDate.of(year, SEPTEMBER, 7) -> EarthlyBranch.Shen
			in LocalDate.of(year, SEPTEMBER, 7)..<LocalDate.of(year, OCTOBER, 7) -> EarthlyBranch.You
			in LocalDate.of(year, OCTOBER, 7)..<LocalDate.of(year, NOVEMBER, 6) -> EarthlyBranch.Xu
			in LocalDate.of(year, NOVEMBER, 6)..<LocalDate.of(year, DECEMBER, 6) -> EarthlyBranch.Hai
			in LocalDate.of(year, DECEMBER, 6)..<LocalDate.of(year + 1, JANUARY, 5) -> EarthlyBranch.Zi
			in LocalDate.of(year + 1, JANUARY, 5)..LocalDate.of(year + 1, FEBRUARY, 4) -> EarthlyBranch.Chou
			// --- Solar Year boundary.
			else -> error("Unknown date: ${date} for ${solarYearStart(date.year)} / ${year}")
		}
	} else {
		val leapP = if ((date.year - 1).isLeapYear()) -1 else 0
		when (date) {
			in LocalDate.of(year - 1, DECEMBER, 7 + leapP)..<LocalDate.of(year, JANUARY, 6 + leapP) -> EarthlyBranch.Zi
			in LocalDate.of(year, JANUARY, 6 + leapP)..<LocalDate.of(year, FEBRUARY, 5 + leapP) -> EarthlyBranch.Chou
			// --- Solar Year boundary.
			in LocalDate.of(year, FEBRUARY, 5 + leapP)..<LocalDate.of(year, MARCH, 6) -> EarthlyBranch.Yin
			in LocalDate.of(year, MARCH, 6)..<LocalDate.of(year, APRIL, 6) -> EarthlyBranch.Mao
			in LocalDate.of(year, APRIL, 6)..<LocalDate.of(year, MAY, 6) -> EarthlyBranch.Chen
			in LocalDate.of(year, MAY, 6)..<LocalDate.of(year, JUNE, 6) -> EarthlyBranch.Si
			in LocalDate.of(year, JUNE, 6)..<LocalDate.of(year, JULY, 7) -> EarthlyBranch.Wu
			in LocalDate.of(year, JULY, 7)..<LocalDate.of(year, AUGUST, 8) -> EarthlyBranch.Wei
			in LocalDate.of(year, AUGUST, 8)..<LocalDate.of(year, SEPTEMBER, 8) -> EarthlyBranch.Shen
			in LocalDate.of(year, SEPTEMBER, 8)..<LocalDate.of(year, OCTOBER, 8) -> EarthlyBranch.You
			in LocalDate.of(year, OCTOBER, 8)..<LocalDate.of(year, NOVEMBER, 7) -> EarthlyBranch.Xu
			in LocalDate.of(year, NOVEMBER, 7)..<LocalDate.of(year, DECEMBER, 7) -> EarthlyBranch.Hai
			in LocalDate.of(year, DECEMBER, 7)..<LocalDate.of(year + 1, JANUARY, 6) -> EarthlyBranch.Zi
			in LocalDate.of(year + 1, JANUARY, 6)..LocalDate.of(year + 1, FEBRUARY, 5) -> EarthlyBranch.Chou
			// --- Solar Year boundary.
			else -> error("Unknown date: ${date} for ${solarYearStart(date.year)} / ${year}")
		}
	}
}

public fun solarYearStart(year: Int): LocalDate {
	return if (IsoChronology.INSTANCE.isLeapYear((year - 1).toLong())) {
		LocalDate.of(year, FEBRUARY, 4)
	} else {
		LocalDate.of(year, FEBRUARY, 5)
	}
}

public val LocalDate.solarYear: Int
	get() {
		val start = solarYearStart(this.year)
		return if (start <= this) {
			this.year
		} else {
			this.year - 1
		}
	}
