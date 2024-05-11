@file:Suppress("NOTHING_TO_INLINE")

package net.twisterrob.astro.units

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

@JvmInline
internal value class JulianDay(internal val value: Double)

internal inline val Double.jd: JulianDay
	get() = JulianDay(this)

internal inline operator fun JulianDay.minus(other: JulianDay): Duration =
	(this.value - other.value).days
