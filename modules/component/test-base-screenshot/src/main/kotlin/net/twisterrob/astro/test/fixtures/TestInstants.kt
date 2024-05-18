package net.twisterrob.astro.test.fixtures

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Test instants for consistent testing.
 */
@Suppress("detekt.MagicNumber")
public object TestInstants {

	/**
	 * The birthday of this repository.
	 */
	public val REPO: ZonedDateTime = ZonedDateTime.of(
		LocalDate.of(2024, 4, 20),
		LocalTime.of(14, 20, 31),
		ZoneId.of("Europe/London"),
	)
}
