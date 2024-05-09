@file:Suppress("NOTHING_TO_INLINE")

package net.twisterrob.astro.units

@JvmInline
internal value class Rad(internal val value: Double)

internal inline val Double.rad: Rad get() = Rad(this)
internal inline val Int.rad: Rad get() = this.toDouble().rad

internal inline val Deg.rad: Rad get() = Math.toRadians(this.value).rad
internal inline val Rad.deg: Deg get() = Math.toDegrees(this.value).deg

internal inline fun sin(x: Rad): Double = kotlin.math.sin(x.value)
internal inline fun cos(x: Rad): Double = kotlin.math.cos(x.value)
