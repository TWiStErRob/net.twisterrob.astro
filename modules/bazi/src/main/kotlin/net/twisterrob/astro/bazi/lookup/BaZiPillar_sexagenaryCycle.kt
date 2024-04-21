package net.twisterrob.astro.bazi.lookup

import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem

/**
 * 0-indexed sexagenary cycle lookup.
 *
 * For a `1..60` based lookup, use `sexagenaryCycle(index - 1)`.
 */
public fun BaZi.Pillar.Companion.sexagenaryCycle(index: Int): BaZi.Pillar {
	require(index in 0..59) { "Sexagenary cycle index must be between 0 and 59, but was ${index}." }
	val heavenlyStem = HeavenlyStem.at(index % 10 + 1)
	val earthlyBranch = EarthlyBranch.at(index % 12 + 1)
	return BaZi.Pillar(heavenlyStem, earthlyBranch)
}
