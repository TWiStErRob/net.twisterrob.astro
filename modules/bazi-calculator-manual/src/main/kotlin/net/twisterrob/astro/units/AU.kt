@file:Suppress("NOTHING_TO_INLINE")

package net.twisterrob.astro.units

@JvmInline
internal value class AU(internal val value: Double)

internal inline val Int.au: AU get() = AU(this.toDouble())
internal inline val Double.au: AU get() = AU(this)

internal inline operator fun AU.plus(other: AU): AU = AU(this.value + other.value)
internal inline operator fun AU.minus(other: AU): AU = AU(this.value - other.value)
