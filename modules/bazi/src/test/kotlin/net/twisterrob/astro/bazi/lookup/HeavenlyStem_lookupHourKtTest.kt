package net.twisterrob.astro.bazi.lookup

import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HeavenlyStem_lookupHourKtTest {

	@Test fun `all pairs return a value`() {
		(1..60)
			.map { BaZi.Pillar.sexagenaryCycle(it - 1) }
			.map { HeavenlyStem.lookupHour(it.heavenlyStem, it.earthlyBranch) }
	}

	@CsvSource(
		"Jia, Ji",
		"Yi, Geng",
		"Bing, Xin",
		"Ding, Ren",
		"Wu, Gui",
	)
	@ParameterizedTest
	fun `halves of the table are equal`(first: HeavenlyStem, second: HeavenlyStem) {
		EarthlyBranch.entries.forEach { hour ->
			withClue("for ${hour} hour") {
				val result1 = HeavenlyStem.lookupHour(first, hour)
				val result2 = HeavenlyStem.lookupHour(second, hour)
				result1 shouldBe result2
			}
		}
	}
}
