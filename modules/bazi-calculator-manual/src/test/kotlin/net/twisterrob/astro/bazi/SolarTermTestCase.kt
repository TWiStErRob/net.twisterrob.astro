package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.EarthlyBranch.Chen
import net.twisterrob.astro.bazi.model.EarthlyBranch.Chou
import net.twisterrob.astro.bazi.model.EarthlyBranch.Hai
import net.twisterrob.astro.bazi.model.EarthlyBranch.Mao
import net.twisterrob.astro.bazi.model.EarthlyBranch.Shen
import net.twisterrob.astro.bazi.model.EarthlyBranch.Si
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wei
import net.twisterrob.astro.bazi.model.EarthlyBranch.Xu
import net.twisterrob.astro.bazi.model.EarthlyBranch.Yin
import net.twisterrob.astro.bazi.model.EarthlyBranch.You
import net.twisterrob.astro.bazi.model.EarthlyBranch.Zi
import java.time.Month
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
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB

@Suppress("detekt.LongParameterList")
class SolarTermTestCase(
	val expectedBranch: EarthlyBranch,
	val startYear: Int,
	val startMonth: Month,
	val startDay: Int,
	val endYear: Int,
	val endMonth: Month,
	val endDay: Int,
) {

	companion object {

		@Suppress("detekt.NamedArguments")
		val NON_LEAP_YEAR = listOf(
			SolarTermTestCase(Yin, 0, FEBRUARY, 4, 0, MARCH, 6),
			SolarTermTestCase(Mao, 0, MARCH, 6, 0, APRIL, 5),
			SolarTermTestCase(Chen, 0, APRIL, 5, 0, MAY, 6),
			SolarTermTestCase(Si, 0, MAY, 6, 0, JUNE, 6),
			SolarTermTestCase(WuEB, 0, JUNE, 6, 0, JULY, 7),
			SolarTermTestCase(Wei, 0, JULY, 7, 0, AUGUST, 8),
			SolarTermTestCase(Shen, 0, AUGUST, 8, 0, SEPTEMBER, 8),
			SolarTermTestCase(You, 0, SEPTEMBER, 8, 0, OCTOBER, 8),
			SolarTermTestCase(Xu, 0, OCTOBER, 8, 0, NOVEMBER, 7),
			SolarTermTestCase(Hai, 0, NOVEMBER, 7, 0, DECEMBER, 7),
			SolarTermTestCase(Zi, 0, DECEMBER, 7, 1, JANUARY, 6),
			SolarTermTestCase(Chou, 1, JANUARY, 6, 1, FEBRUARY, 4),
		)
	}
}
