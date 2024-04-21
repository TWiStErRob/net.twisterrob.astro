package net.twisterrob.astro.bazi.lookup

import net.twisterrob.astro.bazi.model.EarthlyBranch
import java.time.LocalDate
import java.time.Month
import java.time.chrono.IsoChronology

/**
 * References:
 *  * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
 */
public fun EarthlyBranch.Companion.monthAtSolarDay(date: LocalDate): EarthlyBranch {
	val reference = date.withYear(0)
		.let { if (it < solarYearStart(date.year).withYear(0)) it.plusYears(1) else it }
	// TODO account for leap years.
	return when (reference) {
		in solarYearStart(date.year).withYear(0)..<LocalDate.of(0, Month.MARCH, 6) -> EarthlyBranch.Yin
		in LocalDate.of(0, Month.MARCH, 6)..<LocalDate.of(0, Month.APRIL, 5) -> EarthlyBranch.Mao
		in LocalDate.of(0, Month.APRIL, 5)..<LocalDate.of(0, Month.MAY, 6) -> EarthlyBranch.Chen
		in LocalDate.of(0, Month.MAY, 6)..<LocalDate.of(0, Month.JUNE, 6) -> EarthlyBranch.Si
		in LocalDate.of(0, Month.JUNE, 6)..<LocalDate.of(0, Month.JULY, 7) -> EarthlyBranch.Wu
		in LocalDate.of(0, Month.JULY, 7)..<LocalDate.of(0, Month.AUGUST, 8) -> EarthlyBranch.Wei
		in LocalDate.of(0, Month.AUGUST, 8)..<LocalDate.of(0, Month.SEPTEMBER, 8) -> EarthlyBranch.Shen
		in LocalDate.of(0, Month.SEPTEMBER, 8)..<LocalDate.of(0, Month.OCTOBER, 8) -> EarthlyBranch.You
		in LocalDate.of(0, Month.OCTOBER, 8)..<LocalDate.of(0, Month.NOVEMBER, 7) -> EarthlyBranch.Xu
		in LocalDate.of(0, Month.NOVEMBER, 7)..<LocalDate.of(0, Month.DECEMBER, 7) -> EarthlyBranch.Hai
		in LocalDate.of(0, Month.DECEMBER, 7)..<LocalDate.of(1, Month.JANUARY, 6) -> EarthlyBranch.Zi
		in LocalDate.of(1, Month.JANUARY, 6)..solarYearStart(date.year + 1).withYear(1) -> EarthlyBranch.Chou
		else -> error("Unknown date: ${date} -> ${reference}")
	}
}

public fun solarYearStart(year: Int): LocalDate {
	return if (IsoChronology.INSTANCE.isLeapYear((year - 1).toLong())) {
		LocalDate.of(year, Month.FEBRUARY, 4)
	} else {
		LocalDate.of(year, Month.FEBRUARY, 5)
	}
}
