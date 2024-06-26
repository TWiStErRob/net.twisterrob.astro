@file:Suppress("NOTHING_TO_INLINE", "detekt.TooManyFunctions")

package net.twisterrob.astro.units

@JvmInline
internal value class Deg(internal val value: Double)

internal inline val Int.deg: Deg
	get() = Deg(this.toDouble())

internal inline val Double.deg: Deg
	get() = Deg(this)

internal inline operator fun Int.times(other: Deg): Deg =
	Deg(this * other.value)

internal inline operator fun Deg.times(other: Int): Deg =
	Deg(this.value * other)

internal inline operator fun Long.times(other: Deg): Deg =
	Deg(this * other.value)

internal inline operator fun Deg.times(other: Long): Deg =
	Deg(this.value * other)

internal inline operator fun Double.times(other: Deg): Deg =
	Deg(this * other.value)

internal inline operator fun Deg.times(other: Double): Deg =
	Deg(this.value * other)

internal inline operator fun Deg.plus(other: Deg): Deg =
	Deg(this.value + other.value)

internal inline operator fun Deg.minus(other: Deg): Deg =
	Deg(this.value - other.value)

internal inline operator fun Deg.div(other: Deg): Double =
	this.value / other.value

internal inline operator fun Deg.rem(other: Deg): Deg =
	Deg(this.value % other.value)

internal inline fun asin(x: Double): Deg =
	kotlin.math.asin(x).rad.deg

internal inline fun atan2(y: Double, x: Double): Deg =
	kotlin.math.atan2(y, x).rad.deg

internal inline fun sin(x: Deg): Double =
	sin(x.rad)

internal inline fun cos(x: Deg): Double =
	cos(x.rad)

/**
 * @see canonicalMod(Int)
 */
internal inline fun Deg.canonicalMod(deg: Deg): Deg =
	Deg(this.value.canonicalMod(deg.value))
