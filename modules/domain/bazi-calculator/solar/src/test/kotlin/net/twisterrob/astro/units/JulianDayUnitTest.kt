package net.twisterrob.astro.units

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

class JulianDayUnitTest {

	@CsvSource(
		"0.0",
		"1.0",
		"-2.0",
		"3.0",
		"3.14",
		"-2.7",
	)
	@ParameterizedTest
	fun `double constructor`(d: Double) {
		val doubleDeg: JulianDay = d.jd

		doubleDeg.value shouldBe d
	}

	@CsvSource(
		"2024-05-11T08:25:00, 2024-05-11T08:25:00, 0.0",
		"2024-05-11T08:25:00, 2024-05-12T08:25:00, 1.0",
		"2024-05-11T08:25:00, 2024-06-11T08:25:00, 31.0",
		"2024-05-11T08:25:00, 2024-05-11T20:25:00, 0.5",
		"2024-05-11T08:25:00, 2024-05-10T08:25:00, -1.0",
	)
	@ParameterizedTest
	fun `julian day difference`(start: LocalDateTime, end: LocalDateTime, dayDifference: Double) {
		val startJD: JulianDay = start.julianDayTime
		val endJD: JulianDay = end.julianDayTime

		val result: Duration = endJD - startJD

		result shouldBe dayDifference.days
	}
}
