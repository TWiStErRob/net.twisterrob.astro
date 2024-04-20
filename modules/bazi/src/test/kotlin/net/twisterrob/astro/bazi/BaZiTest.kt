package net.twisterrob.astro.bazi

import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.shouldNot
import net.twisterrob.astro.bazi.BaZi.Pillar
import org.junit.jupiter.api.Test

class BaZiTest {

	@Test fun test() {
		val subject = BaZi(
			Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
			Pillar(HeavenlyStem.Bing, EarthlyBranch.Yin),
			Pillar(HeavenlyStem.Wu, EarthlyBranch.Chen),
			Pillar(HeavenlyStem.Geng, EarthlyBranch.Si),
		)

		subject shouldNot beNull()
	}
}
