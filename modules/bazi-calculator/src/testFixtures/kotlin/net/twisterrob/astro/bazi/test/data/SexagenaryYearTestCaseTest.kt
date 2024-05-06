package net.twisterrob.astro.bazi.test.data

import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.date.shouldBeBefore
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.lookup.sexagenaryCycle
import net.twisterrob.astro.bazi.model.BaZi
import org.junit.jupiter.api.Test

class SexagenaryYearTestCaseTest {

	@Test
	fun `the test data has full cycles`() {
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle shouldHaveSize 60
			cycle.forEachIndexed { index, testCase ->
				testCase.cyclicOrdinal shouldBe index + 1
			}
		}
	}

	@Test
	fun `the test data is consistent with cycles`() {
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle.forEach { testCase ->
				val expectedPillar = BaZi.Pillar.sexagenaryCycle(testCase.cyclicOrdinal - 1)
				val actualPillar = BaZi.Pillar(testCase.stem, testCase.branch)

				withClue(testCase) {
					actualPillar shouldBe expectedPillar
				}
			}
		}
	}

	@Test
	fun `the test data is self-consistent with lunar dates`() {
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle.forEach { testCase ->
				testCase.startDate shouldBeBefore testCase.endDate
			}
		}
	}

	@Test
	fun `the test data is consistent with lunar dates`() {
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle.take(59).zip(cycle.drop(1)).forEach { (testCase, nextTestCase) ->
				testCase.endDate shouldBeBefore nextTestCase.startDate
				nextTestCase.startDate shouldBe testCase.endDate.plusDays(1)
			}
		}
	}

	@Test
	fun `the test data is self-consistent with solar dates`() {
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle.forEach { testCase ->
				if (testCase.solarStart != null && testCase.solarEnd != null) {
					testCase.solarStart shouldBeBefore testCase.solarEnd
				}
			}
		}
	}

	@Test
	fun `the test data is consistent with solar dates`() {
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle.take(59).zip(cycle.drop(1)).forEach { (testCase, nextTestCase) ->
				testCase.solarEnd shouldBe nextTestCase.solarStart
			}
		}
	}

	@Test
	fun `the test data is self-consistent with solar and lunar dates`() {
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.forEach { cycle ->
			cycle.forEach { testCase ->
				if (testCase.solarStart != null) {
					testCase.startDate.year shouldBe testCase.solarStart.year
				}
				if (testCase.solarEnd != null) {
					testCase.endDate.year shouldBe testCase.solarEnd.year
				}
			}
		}
	}
}
