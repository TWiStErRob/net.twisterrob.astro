package net.twisterrob.astro.bazi

import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.lookup.lookupSolarMonth
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
import net.twisterrob.astro.bazi.model.HeavenlyStem
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import java.time.LocalDate
import java.time.LocalTime
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
import java.time.temporal.ChronoUnit
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB

class ManualCalculatorTest : BaZiCalculatorTest() {

	override val subject: BaZiCalculator = ManualCalculator()

	/**
	 * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
	 */
	@TestFactory fun `branch of non-leap-year solar terms`(): Iterable<DynamicNode> {
		return listOf(2018, 2022).map { year ->
			dynamicContainer("year ${year}", SolarTermTestCase.NON_LEAP_YEAR.map { tc ->
				val startDate = LocalDate.of(year + tc.startYear, tc.startMonth, tc.startDay)
				val endDate = LocalDate.of(year + tc.endYear, tc.endMonth, tc.endDay)
				dynamicContainer(
					"solar term ${startDate}-${endDate} is ${tc.expectedBranch} month",
					(startDate..<endDate).map { date ->
						dynamicTest(date.toString()) {
							val result = subject.calculate(date.atTime(LocalTime.NOON))
							result.month.earthlyBranch shouldBe tc.expectedBranch
						}
					}
				)
			})
		}
	}

	/**
	 * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
	 */
	@TestFactory fun `stem of solar terms`(): Iterable<DynamicNode> {
		return (1900..2100).map { year ->
			dynamicContainer("year ${year}",
				SolarTermTestCase.NON_LEAP_YEAR.map { tc ->
					// +7 because 1900 was Yang Metal Rat and Yang Metal is order 7.
					val heavenlyStem = HeavenlyStem.at((year + 7 - 1) % HeavenlyStem.COUNT + 1)
					val startDate = LocalDate.of(year + tc.startYear, tc.startMonth, tc.startDay)
					val endDate = LocalDate.of(year + tc.endYear, tc.endMonth, tc.endDay)
					val midDate = startDate.plusDays(ChronoUnit.DAYS.between(startDate, endDate) / 2)
					val expectedStem = HeavenlyStem.lookupSolarMonth(heavenlyStem, tc.expectedBranch)
					dynamicTest("${midDate} stem is ${expectedStem}") {
						val result = subject.calculate(midDate.atTime(LocalTime.NOON))
						result.month.heavenlyStem shouldBe expectedStem
					}
				}
			)
		}
	}

	data class SolarTermTestCase(
		val expectedBranch: EarthlyBranch,
		val startYear: Int,
		val startMonth: Month,
		val startDay: Int,
		val endYear: Int,
		val endMonth: Month,
		val endDay: Int,
	) {

		companion object {

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
}

private operator fun LocalDate.rangeTo(endDate: LocalDate): Iterable<LocalDate> =
	generateSequence(this) { it.plusDays(1) }.takeWhile { it <= endDate }.asIterable()

private operator fun LocalDate.rangeUntil(endDate: LocalDate): Iterable<LocalDate> =
	generateSequence(this) { it.plusDays(1) }.takeWhile { it < endDate }.asIterable()
