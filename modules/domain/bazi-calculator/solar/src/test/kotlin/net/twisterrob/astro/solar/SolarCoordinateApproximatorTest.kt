package net.twisterrob.astro.solar

import io.kotest.matchers.Matcher
import io.kotest.matchers.compose.any
import io.kotest.matchers.doubles.ToleranceMatcher
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.solar.SolarCoordinateApproximator.Companion.duration
import net.twisterrob.astro.units.Deg
import net.twisterrob.astro.units.deg
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDateTime
import java.time.Month.APRIL
import kotlin.time.Duration

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
	@ParameterizedTest fun `northward equinox should be the 0 point, every year`(dateTime: LocalDateTime) {
		val result = subject.approximateSolarLongitude(dateTime)

		// 0.0° ± (0.008° = 0.48′ = 28.8″)
		result.rightAscension.value shouldBe Matcher.any(
			0.0.deg plusOrMinus 0.008.deg,
			360.0.deg plusOrMinus 0.008.deg,
		)
	}

	@CsvSource(
		// Timings from https://en.wikipedia.org/wiki/June_solstice
		"2019-06-21T15:54:00",
		"2020-06-20T21:43:00",
		"2021-06-21T03:32:00",
		"2022-06-21T09:14:00",
		"2023-06-21T14:58:00",
		"2024-06-20T20:51:00",
		"2025-06-21T02:42:00",
		"2026-06-21T08:25:00",
		"2027-06-21T14:11:00",
		"2028-06-20T20:02:00",
		"2029-06-21T01:48:00",
	)
	@ParameterizedTest fun `northern solstice should be the 0 point, every year`(dateTime: LocalDateTime) {
		val result = subject.approximateSolarLongitude(dateTime)

		// +90.0° ± (0.004° = 0.24′ = 14.4″)
		result.rightAscension.value shouldBe (90.0.deg plusOrMinus 0.004.deg)
	}

	@CsvSource(
		// Timings from https://en.wikipedia.org/wiki/September_equinox
		"2019-09-23T07:50:00",
		"2020-09-22T13:31:00",
		"2021-09-22T19:21:00",
		"2022-09-23T01:04:00",
		"2023-09-23T06:50:00",
		"2024-09-22T12:44:00",
		"2025-09-22T18:20:00",
		"2026-09-23T00:06:00",
		"2027-09-23T06:02:00",
		"2028-09-22T11:45:00",
		"2029-09-22T17:37:00",
	)
	@ParameterizedTest fun `southward equinox should be the 0 point, every year`(dateTime: LocalDateTime) {
		val result = subject.approximateSolarLongitude(dateTime)

		// ±180.0° ± (0.009° = 0.54′ = 32.4″)
		result.rightAscension.value shouldBe (180.0.deg plusOrMinus 0.009.deg)
	}

	@CsvSource(
		// Timings from https://en.wikipedia.org/wiki/December_solstice
		"2019-12-22T04:19:00",
		"2020-12-21T10:03:00",
		"2021-12-21T15:59:00",
		"2022-12-21T21:48:00",
		"2023-12-22T03:28:00",
		"2024-12-21T09:20:00",
		"2025-12-21T15:03:00",
		"2026-12-21T20:50:00",
		"2027-12-22T02:43:00",
		"2028-12-21T08:20:00",
		"2029-12-21T14:14:00",
	)
	@ParameterizedTest fun `southern solstice should be the 0 point, every year`(dateTime: LocalDateTime) {
		val result = subject.approximateSolarLongitude(dateTime)

		// 270.0° ± (0.012° = 0.72′ = 43.2″)
		result.rightAscension.value shouldBe (270.0.deg plusOrMinus 0.012.deg)
	}

	@Test fun test2() {
		val dateTime = LocalDateTime.of(1947, APRIL, 6, 12, 0, 5)

		val result = subject.approximateSolarLongitude(dateTime)

		// ± 0.0004° = 0.024′ = 1.44″
		result.apparentSolarLongitude.value shouldBe (15.846941794266089.deg plusOrMinus 0.0004.deg)
	}

	@CsvSource(
		"0.0, 0s",
		"15.0, 1h",
		"30.0, 2h",
		"180.0, 12h",
		"1.0, 4m",
		"-15.0, -1h",
		"-30.0, -2h",
	)
	@ParameterizedTest
	fun `deg to duration conversion`(d: Double, expectedDuration: String) {
		val expected = Duration.parse(expectedDuration)
		val deg: Deg = d.deg

		val duration = deg.duration

		duration shouldBe expected
	}
}

private infix fun Deg.plusOrMinus(deg: Deg): ToleranceMatcher =
	this.value.plusOrMinus(deg.value)
