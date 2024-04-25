package net.twisterrob.astro.bazi

import io.kotest.matchers.doubles.ToleranceMatcher
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.units.Deg
import net.twisterrob.astro.units.deg
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDateTime

class SolarCoordinateApproximatorTest {

	private val subject = SolarCoordinateApproximator()

	@CsvSource(
		// Timings from https://en.wikipedia.org/wiki/March_equinox
		"2019-03-20T21:58:00",
		"2020-03-20T03:50:00",
		"2021-03-20T09:37:00",
		"2022-03-20T15:33:00",
		"2023-03-20T21:25:00",
		"2024-03-20T03:07:00",
		"2025-03-20T09:02:00",
		"2026-03-20T14:46:00",
		"2027-03-20T20:25:00",
		"2028-03-20T02:17:00",
		"2029-03-20T08:01:00",
	)
	@ParameterizedTest fun `equinox should be the 0 point, every year`(dateTime: LocalDateTime) {
		val result = subject.approximateSolarLongitude(dateTime)

		// 0.0° ± (0.008° = 0.48′ = 28.8″)
		result.rightAscension.value shouldBe (0.0.deg plusOrMinus 0.008.deg)
	}
}

private infix fun Deg.plusOrMinus(deg: Deg): ToleranceMatcher = this.value.plusOrMinus(deg.value)
