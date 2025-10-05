package net.twisterrob.astro.screen.bazi

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

public interface CurrentTimeProvider {
	public val zoned: ZonedDateTime get() = ZonedDateTime.now()
	public val date: LocalDate get() = LocalDate.now()
	public val time: LocalTime get() = LocalTime.now()
	public val zone: ZoneId get() = ZoneId.systemDefault()
}
