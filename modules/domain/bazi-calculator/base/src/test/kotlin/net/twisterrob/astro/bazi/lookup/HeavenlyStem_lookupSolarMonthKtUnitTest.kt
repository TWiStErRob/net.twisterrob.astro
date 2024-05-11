package net.twisterrob.astro.bazi.lookup

import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HeavenlyStem_lookupSolarMonthKtUnitTest {

	@TestFactory fun `all sexagenary pairs return a value`() =
		(1..60)
			.map { position ->
				val pillar = BaZi.Pillar.sexagenaryCycle(position - 1)
				dynamicTest("for #${position}: ${pillar.heavenlyStem} ${pillar.earthlyBranch}") {
					HeavenlyStem.lookupSolarMonth(pillar.heavenlyStem, pillar.earthlyBranch)
				}
			}

	@TestFactory
	fun `halves of the table are equal`() =
		listOf(
			HeavenlyStem.Jia to HeavenlyStem.Ji,
			HeavenlyStem.Yi to HeavenlyStem.Geng,
			HeavenlyStem.Bing to HeavenlyStem.Xin,
			HeavenlyStem.Ding to HeavenlyStem.Ren,
			HeavenlyStem.Wu to HeavenlyStem.Gui,
		).map { yearStems ->
			dynamicContainer(
				"Years ${yearStems.first} and ${yearStems.second} are equivalent",
				EarthlyBranch.entries
					.map { monthBranch ->
						dynamicTest("for ${monthBranch} month") {
							val firstMonth = HeavenlyStem.lookupSolarMonth(yearStems.first, monthBranch)
							val secondMonth = HeavenlyStem.lookupSolarMonth(yearStems.second, monthBranch)
							firstMonth shouldBe secondMonth
						}
					}
			)
		}

	@CsvSource(
		"First stem, Jia, Zi, Bing",
		"Last stem, Yi, Hai, Ding",
		"First month, Bing, Yin, Geng",
		"Second half of the table, Xin, Wei, Yi",
	)
	@ParameterizedTest
	fun `table is correct at specific positions`(
		@Suppress("UNUSED_PARAMETER") description: String,
		yearStem: HeavenlyStem,
		monthBranch: EarthlyBranch,
		expected: HeavenlyStem,
	) {
		val actual = HeavenlyStem.lookupSolarMonth(yearStem, monthBranch)
		actual shouldBe expected
	}
}
