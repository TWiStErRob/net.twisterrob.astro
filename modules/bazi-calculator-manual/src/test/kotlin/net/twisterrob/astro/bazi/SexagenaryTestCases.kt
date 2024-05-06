package net.twisterrob.astro.bazi

import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.lookup.atHour
import net.twisterrob.astro.bazi.lookup.lookupSolarMonth
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.EarthlyBranch.Zi
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.units.canonicalMod
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.DynamicTest.dynamicTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

/**
 * @see SexagenaryYearTestCase.Companion
 */
@JvmName("verifyCycleYear")
fun BaZiCalculator.verifyCycle(cycle: List<SexagenaryYearTestCase>): DynamicNode =
	dynamicContainer("cycle ${cycle[0].year}",
		cycle.map { tc ->
			dynamicContainer(
				"${tc.year} year",
				listOf(
					dynamicTest("${tc.midDate} stem is ${tc.stem}") {
						val result = this.calculate(tc.midDate)
						result.year.heavenlyStem shouldBe tc.stem
					},
					dynamicTest("${tc.midDate} branch is ${tc.branch}") {
						val result = this.calculate(tc.midDate)
						result.year.earthlyBranch shouldBe tc.branch
					},
				)
			)
		}
	)

/**
 * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
 */
fun BaZiCalculator.verifySolarTermStemsIn(year: Int): DynamicNode =
	dynamicContainer("year ${year}",
		SolarTermTestCase.NON_LEAP_YEAR.map { tc ->
			verifySolarTermStems(year, tc)
		}
	)

private fun BaZiCalculator.verifySolarTermStems(year: Int,tc: SolarTermTestCase): DynamicNode {
	// +7 because 1900 was Yang Metal Rat and Yang Metal is order 7.
	val heavenlyStem = HeavenlyStem.at((year + 7 - 1) % HeavenlyStem.COUNT + 1)
	val startDate = LocalDate.of(year + tc.startYear, tc.startMonth, tc.startDay)
	val endDate = LocalDate.of(year + tc.endYear, tc.endMonth, tc.endDay)
	val midDate = startDate.plusDays(ChronoUnit.DAYS.between(startDate, endDate) / 2)
	val expectedStem = HeavenlyStem.lookupSolarMonth(heavenlyStem, tc.expectedBranch)
	return dynamicTest("${midDate} stem is ${expectedStem}") {
		val result = this.calculate(midDate.atTime(LocalTime.NOON))
		result.month.heavenlyStem shouldBe expectedStem
	}
}

/**
 * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
 */
fun BaZiCalculator.verifySolarTermBranchesIn(year: Int): DynamicNode =
	dynamicContainer("year ${year}", SolarTermTestCase.NON_LEAP_YEAR.map { tc ->
		verifySolarTermBranches(year, tc)
	})

private fun BaZiCalculator.verifySolarTermBranches(year: Int, tc: SolarTermTestCase): DynamicNode {
	val startDate = LocalDate.of(year + tc.startYear, tc.startMonth, tc.startDay)
	val endDate = LocalDate.of(year + tc.endYear, tc.endMonth, tc.endDay)
	return dynamicContainer(
		"solar term ${startDate}-${endDate} is ${tc.expectedBranch} month",
		(startDate..<endDate).map { date ->
			dynamicTest(date.toString()) {
				val result = this.calculate(date.atTime(LocalTime.NOON))
				result.month.earthlyBranch shouldBe tc.expectedBranch
			}
		}
	)
}

/**
 * @see SexagenaryDayTestCase.Companion
 */
@JvmName("verifyCycleDay")
fun BaZiCalculator.verifyCycle(cycle: List<SexagenaryDayTestCase>): DynamicNode =
	dynamicContainer("${cycle[0].date} cycle",
		cycle.map { tc ->
			dynamicContainer(
				"#${tc.cyclicOrdinal}: ${tc.dayStem} ${tc.dayBranch}",
				listOf(
					dynamicContainer(
						"${tc.date} all time",
						(tc.date.atStartOfDay()..<tc.date.atStartOfDay().plusDays(1))
							.filter { it.minute == 0 }
							.map { time ->
								dynamicTest("${time} is ${tc.dayPillar}") {
									val result = this.calculate(time)
									result.day shouldBe tc.dayPillar
								}
							}
					),
					dynamicTest("${tc.date} day") {
						val result = this.calculate(tc.date)
						result.day shouldBe tc.dayPillar
					},
				)
			)
		}
	)

/**
 * @see SexagenaryHourTestCase.Companion
 */
@JvmName("verifyCycleHour")
fun BaZiCalculator.verifyCycle(cycle: List<SexagenaryHourTestCase>): DynamicNode =
	dynamicContainer("${cycle[0].startTime.toLocalDate()} cycle",
		cycle.map { tc ->
			dynamicContainer(
				"#${tc.cyclicOrdinal}: ${tc.dayStem} ${tc.branchOfHour} -> ${tc.hourPillar}",
				listOf(
					dynamicContainer(
						"${tc.startTime}-${tc.endTime}",
						(tc.startTime..<tc.endTime)
							.map { time ->
								dynamicTest("${time} is ${tc.hourPillar}") {
									val result = this.calculate(time)
									result.hour shouldBe tc.hourPillar
								}
							}
					),
					dynamicTest("${tc.startTime} start's branch association") {
						EarthlyBranch.atHour(tc.startTime.hour) shouldBe tc.branchOfHour
					},
					dynamicTest("${tc.midTime} middle's branch association") {
						EarthlyBranch.atHour(tc.midTime.hour) shouldBe tc.branchOfHour
					},
					dynamicTest("${tc.endTime.minusMinutes(1)} end's branch association") {
						EarthlyBranch.atHour(tc.endTime.minusMinutes(1).hour) shouldBe tc.branchOfHour
					},
					dynamicTest("${tc.startTime} day") {
						val expected = if (tc.branchOfHour == Zi && tc.startTime.hour >= 23) {
							tc.dayStem.previous()
						} else {
							tc.dayStem
						}
						val result = this.calculate(tc.startTime)
						result.day.heavenlyStem shouldBe expected
					},
					dynamicTest("${tc.midTime} day") {
						val result = this.calculate(tc.midTime)
						result.day.heavenlyStem shouldBe tc.dayStem
					},
					dynamicTest("${tc.endTime.minusMinutes(1)} day") {
						val result = this.calculate(tc.endTime.minusMinutes(1))
						result.day.heavenlyStem shouldBe tc.dayStem
					},
					dynamicTest("${tc.midTime} hour stem is ${tc.hourStem}") {
						val result = this.calculate(tc.midTime)
						result.hour?.heavenlyStem shouldBe tc.hourStem
					},
					dynamicTest("${tc.midTime} hour branch is ${tc.hourBranch}") {
						val result = this.calculate(tc.midTime)
						result.hour?.earthlyBranch shouldBe tc.hourBranch
					},
				)
			)
		}
	)

private operator fun LocalDate.rangeUntil(endDate: LocalDate): Iterable<LocalDate> =
	generateSequence(this) { it.plusDays(1) }.takeWhile { it < endDate }.asIterable()

private operator fun LocalDateTime.rangeUntil(endDate: LocalDateTime): Iterable<LocalDateTime> =
	generateSequence(this) { it.plusMinutes(15) }.takeWhile { it < endDate }.asIterable()

private fun HeavenlyStem.previous(): HeavenlyStem =
	HeavenlyStem.at((order - 1 - 1).canonicalMod(HeavenlyStem.COUNT) + 1)
