package net.twisterrob.astro.bazi.model

import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.BaZi.Pillar
import org.junit.jupiter.api.Test

class BaZiTest {

	@Test fun `day master represents the day's heavenly stem`() {
		val subject = BaZi(
			Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
			Pillar(HeavenlyStem.Bing, EarthlyBranch.Yin),
			Pillar(HeavenlyStem.Wu, EarthlyBranch.Chen),
			Pillar(HeavenlyStem.Geng, EarthlyBranch.Si),
		)

		subject.dayMaster shouldBe subject.day.heavenlyStem
		subject.dayMaster shouldBe HeavenlyStem.Wu
	}
}
