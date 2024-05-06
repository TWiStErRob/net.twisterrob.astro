package net.twisterrob.astro.bazi.test

import io.kotest.assertions.withClue
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import net.twisterrob.astro.bazi.BaZiCalculator
import net.twisterrob.astro.bazi.lookup.atHour
import net.twisterrob.astro.bazi.lookup.lookupSolarMonth
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.EarthlyBranch.Zi
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.bazi.test.data.SexagenaryDayTestCase
import net.twisterrob.astro.bazi.test.data.SexagenaryHourTestCase
import net.twisterrob.astro.bazi.test.data.SexagenaryYearTestCase
import net.twisterrob.astro.bazi.test.data.SolarTermTestCase
import net.twisterrob.astro.units.canonicalMod
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.DynamicTest.dynamicTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

fun BaZiCalculator.verify(date: LocalDate, expected: BaZi): DynamicNode =
	dynamicTest("${date}'s BaZi is ${expected}") {
		expected.hour should beNull()

		val result = this.calculate(date)

		result shouldBe expected
	}

fun BaZiCalculator.verify(dateTime: LocalDateTime, expected: BaZi): DynamicNode =
	dynamicTest("${dateTime}'s BaZi is ${expected}") {
		expected.hour shouldNot beNull()

		val result = this.calculate(dateTime)

		result shouldBe expected
	}

/**
 * @see SexagenaryYearTestCase.Companion
 */
@JvmName("verifyCycleYear")
fun BaZiCalculator.verifyCycle(cycle: List<SexagenaryYearTestCase>): DynamicNode =
	dynamicContainer("cycle ${cycle[0].year}",
		cycle.map { tc ->
			dynamicContainer(
				"${tc.year} year",
				listOfNotNull(
					dynamicTest("${tc.midDate} (middle) stem is ${tc.stem}") {
						val result = this.calculate(tc.midDate)
						result.year.heavenlyStem shouldBe tc.stem
					},
					dynamicTest("${tc.midDate} (middle) branch is ${tc.branch}") {
						val result = this.calculate(tc.midDate)
						result.year.earthlyBranch shouldBe tc.branch
					},
					// TODO https://github.com/TWiStErRob/net.twisterrob.astro/issues/14
					tc.solarStart?.let { startDate ->
						dynamicTest("${startDate} (start) stem is ${tc.stem}") {
							val result = this.calculate(startDate.plusMinutes(12))
							result.year.heavenlyStem shouldBe tc.stem
						}
					},
					tc.solarStart?.let { startDate ->
						dynamicTest("${startDate} (start) branch is ${tc.branch}") {
							val result = this.calculate(startDate.plusMinutes(12))
							result.year.earthlyBranch shouldBe tc.branch
						}
					},
					tc.solarEnd?.let { endDate ->
						dynamicTest("${endDate} (end) stem is ${tc.stem}") {
							val result = this.calculate(endDate.minusMinutes(12))
							result.year.heavenlyStem shouldBe tc.stem
						}
					},
					tc.solarEnd?.let { endDate ->
						dynamicTest("${endDate} (end) branch is ${tc.branch}") {
							val result = this.calculate(endDate.minusMinutes(12))
							result.year.earthlyBranch shouldBe tc.branch
						}
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

private fun BaZiCalculator.verifySolarTermStems(year: Int, tc: SolarTermTestCase): DynamicNode {
	// +7 because 1900 was Yang Metal Rat and Yang Metal is order 7.
	val heavenlyStem = HeavenlyStem.at((year + 7 - 1) % HeavenlyStem.COUNT + 1)
	val startDate = LocalDate.of(year + tc.startYear, tc.startMonth, tc.startDay)
	val endDate = LocalDate.of(year + tc.endYear, tc.endMonth, tc.endDay)
	val midDate = startDate.plusDays(ChronoUnit.DAYS.between(startDate, endDate) / 2)
	val expectedStem = HeavenlyStem.lookupSolarMonth(heavenlyStem, tc.expectedBranch)
	return dynamicTest("${midDate} month stem is ${expectedStem}") {
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
	dynamicContainer("${cycle[0].date} cycle", cycle.map { verifyDay(null, it) })

fun BaZiCalculator.verifyDay(name: String?, tc: SexagenaryDayTestCase): DynamicNode =
	dynamicContainer(
		"${if (name == null) "" else "${name}: "}#${tc.cyclicOrdinal}: ${tc.dayStem} ${tc.dayBranch}",
		listOf(
			dynamicContainer(
				"${tc.date} all time",
				(tc.date.atStartOfDay()..<tc.date.atStartOfDay().plusDays(1))
					.filter { it.minute % 30 == 0 }
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

/**
 * @see SexagenaryHourTestCase.Companion
 */
@JvmName("verifyCycleHour")
fun BaZiCalculator.verifyCycle(cycle: List<SexagenaryHourTestCase>): DynamicNode =
	dynamicContainer("${cycle[0].startTime.toLocalDate()} cycle", cycle.map { verifyHour(it) })

private fun BaZiCalculator.verifyHour(tc: SexagenaryHourTestCase): DynamicNode {
	val justTheEnd = tc.endTime.minusMinutes(1)
	return dynamicContainer(
		"#${tc.cyclicOrdinal}: ${tc.dayStem} d ${tc.branchOfHour} h -> ${tc.hourPillar}",
		listOf(
			dynamicContainer(
				"${tc.startTime}-${tc.endTime}",
				(tc.startTime..<tc.endTime)
					.map { time ->
						dynamicTest("${time} is ${tc.hourPillar}") {
							val result = this.calculate(time)
							withClue("hour pillar") {
								result.hour shouldBe tc.hourPillar
							}
						}
					}
			),
			dynamicContainer(
				"branch associations",
				listOf(
					dynamicTest("${tc.startTime} (start)") {
						EarthlyBranch.atHour(tc.startTime.hour) shouldBe tc.branchOfHour
					},
					dynamicTest("${tc.midTime} (middle)") {
						EarthlyBranch.atHour(tc.midTime.hour) shouldBe tc.branchOfHour
					},
					dynamicTest("${justTheEnd} (end)") {
						EarthlyBranch.atHour(justTheEnd.hour) shouldBe tc.branchOfHour
					},
				),
			),
			dynamicContainer(
				"day stem",
				listOf(
					dynamicTest("${tc.startTime} (start)") {
						val expected = if (tc.branchOfHour == Zi && tc.startTime.hour >= 23) {
							tc.dayStem.previous()
						} else {
							tc.dayStem
						}
						val result = this.calculate(tc.startTime)
						result.day.heavenlyStem shouldBe expected
					},
					dynamicTest("${tc.midTime} (middle)") {
						val result = this.calculate(tc.midTime)
						result.day.heavenlyStem shouldBe tc.dayStem
					},
					dynamicTest("${justTheEnd} (end)") {
						val result = this.calculate(justTheEnd)
						result.day.heavenlyStem shouldBe tc.dayStem
					},
				),
			),
			dynamicTest("${tc.midTime} (middle) hour stem is ${tc.hourStem}") {
				val result = this.calculate(tc.midTime)
				result.hour?.heavenlyStem shouldBe tc.hourStem
			},
			dynamicTest("${tc.midTime} (middle) hour branch is ${tc.hourBranch}") {
				val result = this.calculate(tc.midTime)
				result.hour?.earthlyBranch shouldBe tc.hourBranch
			},
		)
	)
}

private operator fun LocalDate.rangeUntil(endDate: LocalDate): Iterable<LocalDate> =
	generateSequence(this) { it.plusDays(1) }.takeWhile { it < endDate }.asIterable()

private operator fun LocalDateTime.rangeUntil(endDate: LocalDateTime): Iterable<LocalDateTime> =
	generateSequence(this) { it.plusMinutes(15) }.takeWhile { it < endDate }.asIterable()

internal fun HeavenlyStem.previous(): HeavenlyStem =
	HeavenlyStem.at((order - 1 - 1).canonicalMod(HeavenlyStem.COUNT) + 1)
