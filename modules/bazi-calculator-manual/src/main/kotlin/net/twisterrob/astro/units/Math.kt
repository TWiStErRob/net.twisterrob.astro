package net.twisterrob.astro.units

internal fun Double.lowestPositiveRem(cycle: Double): Double =
	((this % cycle) + cycle) % cycle

internal fun Int.lowestPositiveRem(cycle: Int): Int =
	((this % cycle) + cycle) % cycle
