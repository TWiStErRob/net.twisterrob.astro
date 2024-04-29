package net.twisterrob.astro.bazi

import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDateTime

class LocalDateTime_julianDayTimeKtTest {

	@CsvSource(
		// 1e-4 = 0.0001 precision, 1 second is 0.000011574, so this is ~8.64 seconds precise.
		// 1e-9 = 0.000000001 precision, 1 second is 0.000011574, so this is ~0.08 milliseconds precise.

		// Generated via https://aa.usno.navy.mil/data/JulianDate
		"2024-04-25T08:06:42, 2460425.837986, 1e-4",
		"2024-04-24T22:06:42, 2460425.421319, 1e-4",
		// Current value from https://en.wikipedia.org/wiki/Julian_day#Variants
		"2024-04-25T08:09:00, 2460425.83958, 1e-4",
		// Examples from https://en.wikipedia.org/wiki/Julian_day#Finding_Julian_date_given_Julian_day_number_and_time_of_day
		"2000-01-01T00:00:00, 2451544.5, 1e-9",
		"2000-01-01T06:00:00, 2451544.75, 1e-9",
		"2000-01-01T12:00:00, 2451545.0, 1e-9",
		"2000-01-01T18:00:00, 2451545.25, 1e-9",
		"2000-01-02T00:00:00, 2451545.5, 1e-9",
	)
	@ParameterizedTest fun test(dateTime: LocalDateTime, expectedJulianDay: Double /* UT1 */, precision: Double) {
		val jd = dateTime.julianDayTime

		jd shouldBe (expectedJulianDay plusOrMinus precision)
	}
}
