package net.twisterrob.astro.bazi.test.data

import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.ints.between
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.EarthlyBranch
import org.junit.jupiter.api.Test
import java.time.temporal.ChronoUnit
import java.util.Collections

class SolarTermTestCaseUnitTest {

	@Test
	fun `the test data has full cycles`() {
		SolarTermTestCase.ALL_KNOWN_YEARS.forEach { cycle ->
			cycle shouldHaveSize 24
			val entries = EarthlyBranch.entries.toMutableList()
			Collections.rotate(entries, -1)
			cycle.map { it.branch }.toSet() shouldContainExactly entries
		}
	}

	@Test
	fun `the test data is consistent with terms`() {
		SolarTermTestCase.ALL_KNOWN_YEARS.forEach { cycle ->
			cycle.forEach { testCase ->
				val lengthD = ChronoUnit.DAYS.between(testCase.startTime, testCase.endTime)
				withClue("${testCase.startTime}—${testCase.endTime} term") {
					lengthD shouldBe between(14, 15)
				}
			}
		}
	}

	@Test
	fun `the test data is self-consistent with times`() {
		SolarTermTestCase.ALL_KNOWN_YEARS.forEach { cycle ->
			cycle.forEach { testCase ->
				testCase.startTime shouldBeBefore testCase.endTime
				testCase.midTime shouldBeAfter testCase.startTime
				testCase.midTime shouldBeBefore testCase.endTime
			}
		}
	}

	@Test
	fun `the test data is consistent with times`() {
		SolarTermTestCase.ALL_KNOWN_YEARS.forEach { cycle ->
			cycle.take(59).zip(cycle.drop(1)).forEach { (testCase, nextTestCase) ->
				testCase.endTime shouldBe nextTestCase.startTime
			}
		}
	}
}
