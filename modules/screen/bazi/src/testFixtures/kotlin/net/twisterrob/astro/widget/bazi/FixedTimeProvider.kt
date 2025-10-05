package net.twisterrob.astro.widget.bazi

import net.twisterrob.astro.screen.bazi.TimeProvider
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

public class FixedTimeProvider(
	at: ZonedDateTime,
) : TimeProvider {
	override val zoned: ZonedDateTime = at
	override val date: LocalDate = at.toLocalDate()
	override val time: LocalTime = at.toLocalTime()
	override val zone: ZoneId = at.zone
}
