@file:Suppress("NOTHING_TO_INLINE")

package net.twisterrob.astro.units

@JvmInline
internal value class JulianDay(internal val value: Double)

internal inline val Double.jd: JulianDay get() = JulianDay(this)
internal inline val Int.jd: JulianDay get() = this.toDouble().jd

internal inline operator fun JulianDay.plus(other: JulianDay): JulianDay = JulianDay(this.value + other.value)
internal inline operator fun JulianDay.minus(other: JulianDay): JulianDay = JulianDay(this.value - other.value)

internal inline operator fun JulianDay.times(other: Double): JulianDay = JulianDay(this.value * other)
internal inline operator fun Double.times(other: JulianDay): JulianDay = JulianDay(this * other.value)
