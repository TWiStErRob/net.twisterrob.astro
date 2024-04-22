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

class HeavenlyStem_lookupHourKtTest {

	@TestFactory fun `all sexagenary pairs return a value`() =
		(1..60)
			.map { position ->
				val pillar = BaZi.Pillar.sexagenaryCycle(position - 1)
				dynamicTest("for #${position}: ${pillar.heavenlyStem} ${pillar.earthlyBranch}") {
					HeavenlyStem.lookupHour(pillar.heavenlyStem, pillar.earthlyBranch)
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
		).map { dayStems ->
			dynamicContainer(
				"Days ${dayStems.first} and ${dayStems.second} are equivalent",
				EarthlyBranch.entries
					.map { hourBranch ->
						dynamicTest("for ${hourBranch} hour") {
							val firstHour = HeavenlyStem.lookupHour(dayStems.first, hourBranch)
							val secondHour = HeavenlyStem.lookupHour(dayStems.second, hourBranch)
							firstHour shouldBe secondHour
						}
					}
			)
		}

	@CsvSource(
		"Just before midnight, Jia, Zi, Jia",
		"Exactly midnight, Wu, Zi, Ren",
		"Just after midnight, Yi, Chou, Ding",

		"Just before noon, Yi, Wu, Ren",
		"Exactly noon, Bing, Wu, Jia",
		"Just after noon, Gui, Wei, Ji",

		"Second half of table, Xin, Mao, Xin",
	)
	@ParameterizedTest
	fun `table is correct at specific positions`(
		@Suppress("UNUSED_PARAMETER") description: String,
		dayStem: HeavenlyStem,
		hourBranch: EarthlyBranch,
		expected: HeavenlyStem,
	) {
		val actual = HeavenlyStem.lookupHour(dayStem, hourBranch)
		actual shouldBe expected
	}
}
