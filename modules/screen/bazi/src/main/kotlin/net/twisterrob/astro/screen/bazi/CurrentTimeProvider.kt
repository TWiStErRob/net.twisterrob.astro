package net.twisterrob.astro.screen.bazi

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

internal object CurrentTimeProvider : TimeProvider {
	override val zoned: ZonedDateTime get() = ZonedDateTime.now()
	override val date: LocalDate get() = LocalDate.now()
	override val time: LocalTime get() = LocalTime.now()
	override val zone: ZoneId get() = ZoneId.systemDefault()
}
