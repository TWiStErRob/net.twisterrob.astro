package net.twisterrob.astro.bazi.lookup

import java.time.chrono.IsoChronology

/**
 * Checks if the specified year is a leap year.
 *
 * @see IsoChronology.isLeapYear
 */
public fun Int.isLeapYear(): Boolean =
	IsoChronology.INSTANCE.isLeapYear(this.toLong())
