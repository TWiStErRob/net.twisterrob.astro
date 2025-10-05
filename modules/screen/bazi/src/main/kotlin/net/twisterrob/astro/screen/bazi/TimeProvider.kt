package net.twisterrob.astro.screen.bazi

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

public interface TimeProvider {
	public val zoned: ZonedDateTime
	public val date: LocalDate
	public val time: LocalTime
	public val zone: ZoneId
}
