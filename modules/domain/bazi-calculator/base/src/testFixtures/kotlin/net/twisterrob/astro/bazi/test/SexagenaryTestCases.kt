@file:Suppress(
	"detekt.LongMethod", // Some of the methods are long, because they encode multiple tests.
)

package net.twisterrob.astro.bazi.test

import io.kotest.assertions.withClue
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import net.twisterrob.astro.bazi.BaZiCalculator
import net.twisterrob.astro.bazi.lookup.atHour
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

fun BaZiCalculator.test(date: LocalDate, expected: BaZi): DynamicNode =
	dynamicTest("${date} is ${expected}") {
		expected.hour should beNull()

		val result = this.calculate(date)

		result shouldBe expected
	}

fun BaZiCalculator.test(dateTime: LocalDateTime, expected: BaZi): DynamicNode =
	dynamicTest("${dateTime} is ${expected}") {
		expected.hour shouldNot beNull()

		val result = this.calculate(dateTime)

		result shouldBe expected
	}

fun BaZiCalculator.testYear(tc: SexagenaryYearTestCase): DynamicNode =
	dynamicContainer(
		"${tc.year} year",
		tc.solarStart?.let { solarStart ->
			// TODO https://github.com/TWiStErRob/net.twisterrob.astro/issues/14
			val startTime = solarStart.plusMinutes(11)
			dynamicTest("${startTime} (start) stem is ${tc.stem}") {
				val result = this.calculate(startTime)
				result.year.heavenlyStem shouldBe tc.stem
			}
		},
		tc.solarStart?.let { solarStart ->
			// TODO https://github.com/TWiStErRob/net.twisterrob.astro/issues/14
			val startTime = solarStart.plusMinutes(11)
			dynamicTest("${startTime} (start) branch is ${tc.branch}") {
				val result = this.calculate(startTime)
				result.year.earthlyBranch shouldBe tc.branch
			}
		},
		dynamicTest("${tc.midDate} (middle) stem is ${tc.stem}") {
			val result = this.calculate(tc.midDate)
			result.year.heavenlyStem shouldBe tc.stem
		},
		dynamicTest("${tc.midDate} (middle) branch is ${tc.branch}") {
			val result = this.calculate(tc.midDate)
			result.year.earthlyBranch shouldBe tc.branch
		},
		tc.solarEnd?.let { solarEnd ->
			// TODO https://github.com/TWiStErRob/net.twisterrob.astro/issues/14
			val endTime = solarEnd.minusMinutes(12)
			dynamicTest("${endTime} (end) stem is ${tc.stem}") {
				val result = this.calculate(endTime)
				result.year.heavenlyStem shouldBe tc.stem
			}
		},
		tc.solarEnd?.let { solarEnd ->
			// TODO https://github.com/TWiStErRob/net.twisterrob.astro/issues/14
			val endTime = solarEnd.minusMinutes(12)
			dynamicTest("${endTime} (end) branch is ${tc.branch}") {
				val result = this.calculate(endTime)
				result.year.earthlyBranch shouldBe tc.branch
			}
		},
	)

fun BaZiCalculator.testSolarTerm(tc: SolarTermTestCase): DynamicNode {
	// TODO https://github.com/TWiStErRob/net.twisterrob.astro/issues/14
	val startTime = tc.startTime.plusMinutes(11)
	val endTime = tc.endTime.minusMinutes(12)
	return dynamicContainer(
		"solar term ${tc.startTime}—${tc.endTime}",
		dynamicTest("${startTime} (start) stem is ${tc.stem}") {
			val result = this.calculate(startTime)
			result.month.heavenlyStem shouldBe tc.stem
		},
		dynamicTest("${startTime} (start) branch is ${tc.branch}") {
			val result = this.calculate(startTime)
			result.month.earthlyBranch shouldBe tc.branch
		},
		dynamicTest("${tc.midTime} (middle) stem is ${tc.stem}") {
			val result = this.calculate(tc.midTime)
			result.month.heavenlyStem shouldBe tc.stem
		},
		dynamicTest("${tc.midTime} (middle) branch is ${tc.branch}") {
			val result = this.calculate(tc.midTime)
			result.month.earthlyBranch shouldBe tc.branch
		},
		dynamicTest("${endTime} (end) stem is ${tc.stem}") {
			val result = this.calculate(endTime)
			result.month.heavenlyStem shouldBe tc.stem
		},
		dynamicTest("${endTime} (end) branch is ${tc.branch}") {
			val result = this.calculate(endTime)
			result.month.earthlyBranch shouldBe tc.branch
		},
		dynamicContainer(
			"is ${tc.monthPillar} term in whole range",
			dynamicContainer(
				"starting 24h minutes",
				(startTime..<tc.startTime.plusDays(1)).map { date ->
					dynamicTest(date.toString()) {
						val result = this.calculate(date)
						result.month shouldBe tc.monthPillar
					}
				}
			),
			dynamicContainer(
				"middle days",
				(tc.startTime.plusDays(1).toLocalDate()..<tc.endTime.toLocalDate()).map { date ->
					dynamicTest(date.toString()) {
						val result = this.calculate(date)
						result.month shouldBe tc.monthPillar
					}
				}
			),
			dynamicContainer(
				"ending 24h minutes",
				(tc.endTime.minusDays(1)..<endTime).map { date ->
					dynamicTest(date.toString()) {
						val result = this.calculate(date)
						result.month shouldBe tc.monthPillar
					}
				}
			),
		)
	)
}

fun BaZiCalculator.testDay(name: String?, tc: SexagenaryDayTestCase): DynamicNode =
	dynamicContainer(
		"${if (name == null) "" else "${name}: "}#${tc.cyclicOrdinal}: ${tc.dayStem} ${tc.dayBranch}",
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

fun BaZiCalculator.testHour(tc: SexagenaryHourTestCase): DynamicNode {
	val nearTheEnd = tc.endTime.minusSeconds(1)
	return dynamicContainer(
		"#${tc.cyclicOrdinal}: ${tc.dayStem} d ${tc.branchOfHour} h -> ${tc.hourPillar}",
		dynamicContainer(
			"${tc.startTime}—${tc.endTime}",
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
			dynamicTest("${tc.startTime} (start)") {
				EarthlyBranch.atHour(tc.startTime.hour) shouldBe tc.branchOfHour
			},
			dynamicTest("${tc.midTime} (middle)") {
				EarthlyBranch.atHour(tc.midTime.hour) shouldBe tc.branchOfHour
			},
			dynamicTest("${nearTheEnd} (end)") {
				EarthlyBranch.atHour(nearTheEnd.hour) shouldBe tc.branchOfHour
			},
		),
		dynamicContainer(
			"day stem",
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
			dynamicTest("${nearTheEnd} (end)") {
				val result = this.calculate(nearTheEnd)
				result.day.heavenlyStem shouldBe tc.dayStem
			},
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
}

private operator fun LocalDate.rangeUntil(endDate: LocalDate): Iterable<LocalDate> =
	generateSequence(this) { it.plusDays(1) }.takeWhile { it < endDate }.asIterable()

private operator fun LocalDateTime.rangeUntil(endDate: LocalDateTime): Iterable<LocalDateTime> =
	generateSequence(this) { it.plusMinutes(15) }.takeWhile { it < endDate }.asIterable()

internal fun HeavenlyStem.previous(): HeavenlyStem =
	HeavenlyStem.at((order - 1 - 1).canonicalMod(HeavenlyStem.COUNT) + 1)

private fun dynamicContainer(name: String, vararg nodes: DynamicNode?): DynamicNode =
	dynamicContainer(name, nodes.filterNotNull())
