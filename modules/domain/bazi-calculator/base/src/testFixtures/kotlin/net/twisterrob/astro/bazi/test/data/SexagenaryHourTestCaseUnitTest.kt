package net.twisterrob.astro.bazi.test.data

import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.lookup.sexagenaryCycle
import net.twisterrob.astro.bazi.model.BaZi
import org.junit.jupiter.api.Test

class SexagenaryHourTestCaseUnitTest {

	@Test
	fun `the test data has full cycles`() {
		SexagenaryHourTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle shouldHaveSize 60
			cycle.forEachIndexed { index, testCase ->
				testCase.cyclicOrdinal shouldBe index + 1
			}
		}
	}

	@Test
	fun `the test data is consistent with cycles`() {
		SexagenaryHourTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle.forEach { testCase ->
				val expectedPillar = BaZi.Pillar.sexagenaryCycle(testCase.cyclicOrdinal - 1)
				val actualPillar = BaZi.Pillar(testCase.hourStem, testCase.hourBranch)

				withClue(testCase) {
					actualPillar shouldBe expectedPillar
				}
			}
		}
	}

	@Test
	fun `the test data is consistent with dates`() {
		SexagenaryHourTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle.forEach { testCase ->
				testCase.startTime shouldBeBefore testCase.endTime
				testCase.midTime shouldBeAfter testCase.startTime
				testCase.midTime shouldBeBefore testCase.endTime
			}
		}
	}
}
