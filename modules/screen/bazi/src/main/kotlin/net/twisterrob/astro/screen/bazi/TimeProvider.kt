package net.twisterrob.astro.screen.bazi

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Abstraction over the current time to allow faking it in tests.
 */
public interface TimeProvider {
	/**
	 * The current date and time including the time zone.
	 */
	public val zoned: ZonedDateTime

	/**
	 * The current date in the current time zone.
	 */
	public val date: LocalDate

	/**
	 * The current time in the current time zone.
	 */
	public val time: LocalTime

	/**
	 * The current time zone.
	 */
	public val zone: ZoneId
}
