package net.twisterrob.astro.widget.bazi

import net.twisterrob.astro.screen.bazi.CurrentTimeProvider
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

public class MutableTimeProvider : CurrentTimeProvider {
	override var zoned: ZonedDateTime = super.zoned
	override var date: LocalDate = super.date
	override var time: LocalTime = super.time
	override var zone: ZoneId = super.zone
}
