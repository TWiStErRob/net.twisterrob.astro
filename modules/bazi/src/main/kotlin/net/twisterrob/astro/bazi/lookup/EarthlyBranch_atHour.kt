package net.twisterrob.astro.bazi.lookup

import net.twisterrob.astro.bazi.model.EarthlyBranch

public fun EarthlyBranch.Companion.atHour(hour: Int): EarthlyBranch {
	require(hour in 0..23) { "Invalid hour: ${hour}" }
	val rounded = ((hour + 1) % 24 + 24) % 24
	return EarthlyBranch.entries[rounded / 2]
}
