package net.twisterrob.astro.units

internal fun Double.mod(cycle: Double): Double =
	((this % cycle) + cycle) % cycle

internal fun Int.mod(cycle: Int): Int =
	((this % cycle) + cycle) % cycle
