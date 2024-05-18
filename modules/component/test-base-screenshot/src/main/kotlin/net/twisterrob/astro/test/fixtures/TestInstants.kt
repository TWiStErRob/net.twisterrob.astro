package net.twisterrob.astro.test.fixtures

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

public object TestInstants {

	public val ROBI: ZonedDateTime = ZonedDateTime.of(
		LocalDate.of(1986, 7, 1),
		LocalTime.of(9, 40, 12, 123456789),
		ZoneId.of("Europe/Budapest")
	)
}
