package net.twisterrob.astro.screen.bazi

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

internal interface TimeProvider {
	val zoned: ZonedDateTime
	val date: LocalDate
	val time: LocalTime
	val zone: ZoneId
}
