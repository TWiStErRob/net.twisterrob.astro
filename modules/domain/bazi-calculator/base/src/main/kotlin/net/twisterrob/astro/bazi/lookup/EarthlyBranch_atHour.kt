package net.twisterrob.astro.bazi.lookup

import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.units.canonicalMod

private const val HOURS_IN_DAY: Int = 24

/**
 * Calculates the Earthly Branch for a specific hour.
 *
 * Each branch covers 2 hours of a day, starting at 23:00-01:00 for [EarthlyBranch.Zi].
 */
public fun EarthlyBranch.Companion.atHour(hour: Int): EarthlyBranch {
	require(hour in 0..<HOURS_IN_DAY) { "Invalid hour: ${hour}" }
	val rounded = (hour + 1).canonicalMod(HOURS_IN_DAY)
	return EarthlyBranch.entries[rounded / 2]
}
