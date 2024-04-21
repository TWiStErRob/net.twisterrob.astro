package net.twisterrob.astro.bazi.lookup

import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem

public fun BaZi.Pillar.Companion.sexagenaryCycle(index: Int): BaZi.Pillar {
	require(index in 0..59) { "Sexagenary cycle index must be between 0 and 59, but was ${index}." }
	val heavenlyStem = HeavenlyStem.at(index % 10 + 1)
	val earthlyBranch = EarthlyBranch.at(index % 12 + 1)
	return BaZi.Pillar(heavenlyStem, earthlyBranch)
}
