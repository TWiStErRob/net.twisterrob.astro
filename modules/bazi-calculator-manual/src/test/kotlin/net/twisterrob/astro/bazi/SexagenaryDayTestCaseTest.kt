package net.twisterrob.astro.bazi

import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.lookup.sexagenaryCycle
import net.twisterrob.astro.bazi.model.BaZi
import org.junit.jupiter.api.Test

class SexagenaryDayTestCaseTest {

	@Test
	fun `the test data has full cycles`() {
		SexagenaryDayTestCase.CYCLES.forEach { cycle ->
			cycle shouldHaveSize 60
			cycle.forEachIndexed { index, testCase ->
				testCase.cyclicOrdinal shouldBe index + 1
			}
		}
	}

	@Test
	fun `the test data is consistent with cycles`() {
		SexagenaryDayTestCase.CYCLES.forEach { cycle ->
			cycle.forEach { testCase ->
				val expectedPillar = BaZi.Pillar.sexagenaryCycle(testCase.cyclicOrdinal - 1)
				val actualPillar = BaZi.Pillar(testCase.dayStem, testCase.dayBranch)

				withClue(testCase) {
					actualPillar shouldBe expectedPillar
				}
			}
		}
	}

	@Test
	fun `the test data is consistent with dates`() {
		SexagenaryDayTestCase.CYCLES.forEach { cycle ->
			cycle.take(59).zip(cycle.drop(1)).forEach { (testCase, nextTestCase) ->
				testCase.date shouldBeBefore nextTestCase.date
				nextTestCase.date shouldBe testCase.date.plusDays(1)
			}
		}
	}
}
