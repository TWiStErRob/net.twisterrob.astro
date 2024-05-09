@file:Suppress("NOTHING_TO_INLINE")

package net.twisterrob.astro.units

/**
 * Calculates the remainder of flooring division of this value (dividend) by the other value (divisor).
 * And picks the least positive (canonical) residue representation of the solutions.
 *
 * The result is between zero (inclusive) and [cycle] (exclusive).
 * This is different than the Java/Kotlin built-in [mod] operation.
 */
public inline fun Int.canonicalMod(cycle: Int): Int =
	(this % cycle + cycle) % cycle

/**
 * @see canonicalMod(Int)
 */
public inline fun Double.canonicalMod(cycle: Double): Double =
	(this % cycle + cycle) % cycle
