package net.twisterrob.astro.bazi.lookup

import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem

/**
 * Sexagenary cycle / "gan zhi" (干支)
 * 0-indexed sexagenary cycle lookup.
 *
 * For a `1..60` based lookup, use `sexagenaryCycle(index - 1)`.
 *
 * https://en.wikipedia.org/wiki/Sexagenary_cycle
 * https://zh.wikipedia.org/wiki/%E5%B9%B2%E6%94%AF
 */
public fun BaZi.Pillar.Companion.sexagenaryCycle(index: Int): BaZi.Pillar {
	require(index in 0..<SEXAGENARY_CYCLE) {
		"Sexagenary cycle index must be between 0 and ${SEXAGENARY_CYCLE - 1}, but was ${index}."
	}
	val heavenlyStem = HeavenlyStem.at(index % HeavenlyStem.COUNT + 1)
	val earthlyBranch = EarthlyBranch.at(index % EarthlyBranch.COUNT + 1)
	return BaZi.Pillar(heavenlyStem, earthlyBranch)
}
