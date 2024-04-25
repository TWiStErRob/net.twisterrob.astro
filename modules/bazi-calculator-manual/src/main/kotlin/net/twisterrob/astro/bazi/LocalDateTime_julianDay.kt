package net.twisterrob.astro.bazi

import org.threeten.extra.chrono.JulianDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.JulianFields

/**
 * Julian Day for a [LocalDateTime], takes into account the time of day.
 */
internal val LocalDateTime.julianDay: Double
	get() = this.toJulianDate().julianDay + this.toLocalTime().julianTimeFraction

/**
 * https://en.wikipedia.org/wiki/Julian_day#Converting_Julian_calendar_date_to_Julian_Day_Number
 */
private val JulianDate.julianDay: Long
	get() = this.getLong(JulianFields.JULIAN_DAY)

/**
 * https://en.wikipedia.org/wiki/Julian_day#Finding_Julian_date_given_Julian_day_number_and_time_of_day
 */
@Suppress("detekt.MagicNumber")
private val LocalTime.julianTimeFraction: Double
	get() = (this.hour - 12) / 24.0 + this.minute / 1_444.0 + this.second / 86_400.0

private fun LocalDateTime.toJulianDate(): JulianDate =
	JulianDate.from(this.toLocalDate())
