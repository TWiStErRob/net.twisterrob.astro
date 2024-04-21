package net.twisterrob.astro.bazi.lookup

import java.time.chrono.IsoChronology

public fun Int.isLeapYear(): Boolean =
	IsoChronology.INSTANCE.isLeapYear(this.toLong())
