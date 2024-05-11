package net.twisterrob.astro.units

import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DegUnitTest {

	@CsvSource(
		"0.0,  0",
		"1.0,  1",
		"-2.0, -2",
		"3.0,  3",
	)
	@ParameterizedTest
	fun `int constructor`(d: Double, i: Int) {
		val intDeg: Deg = i.deg

		intDeg.value shouldBe d
	}

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
		val doubleDeg: Deg = d.deg

		doubleDeg.value shouldBe d
	}

	@CsvSource(
		"1.0,  2.0,  3.0",
		"1.2,  3.4,  4.6",
		"-5.6, 7.8,  2.2",
		"0.0,  1.0,  1.0",
		"1.0,  0.0,  1.0",
		"45.0, 30.0, 75.0",
	)
	@ParameterizedTest
	fun `addition calculation`(d1: Double, d2: Double, expected: Double) {
		val deg1: Deg = d1.deg
		val deg2: Deg = d2.deg

		val result: Deg = deg1 + deg2

		result.value shouldBe (expected plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"3.0,  2.0,  1.0",
		"4.6,  3.4,  1.2",
		"2.2,  7.8,  -5.6",
		"1.0,  1.0,  0.0",
		"1.0,  0.0,  1.0",
		"75.0, 30.0, 45.0",
	)
	@ParameterizedTest
	fun `subtraction calculation`(d1: Double, d2: Double, expected: Double) {
		val deg1: Deg = d1.deg
		val deg2: Deg = d2.deg

		val result: Deg = deg1 - deg2

		result.value shouldBe (expected plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"2.0, 1.0, 2.0",
		"1.0, 2.0, 0.5",
		"-5.0, 2.0, -2.5",
		"5.0, -2.0, -2.5",
		"360.0, 36.0, 10.0",
	)
	@ParameterizedTest
	fun `division calculation`(d1: Double, d2: Double, expected: Double) {
		val deg1: Deg = d1.deg
		val deg2: Deg = d2.deg

		val result: Double = deg1 / deg2

		result shouldBe (expected plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"1.0, 2.0, 1.0",
		"5.0, 2.0, 1.0",
		"1.23, 1.0, 0.23",
		"1.0, -2.0, 1.0",
		"-1.0, 2.0, -1.0",
		"2.0, -3.0, 2.0",
		"-3.0, 2.0, -1.0",
		"734.567, 360.0, 14.567",
		"-734.567, 360.0, -14.567",
	)
	@ParameterizedTest
	fun `remainder calculation`(d1: Double, d2: Double, expected: Double) {
		val deg1: Deg = d1.deg
		val deg2: Deg = d2.deg

		val result: Deg = deg1 % deg2

		result.value shouldBe (expected plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"1.0, 2.0, 1.0",
		"5.0, 2.0, 1.0",
		"734.567, 360.0, 14.567",
		"-734.567, 360.0, 345.433",
	)
	@ParameterizedTest
	fun `canonicalMod calculation`(d1: Double, d2: Double, expected: Double) {
		val deg1: Deg = d1.deg
		val deg2: Deg = d2.deg

		val result: Deg = deg1.canonicalMod(deg2)

		result.value shouldBe (expected plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"12.34, 0, 0.0",
		"123.456, 2, 246.912",
	)
	@ParameterizedTest
	fun `multiplication calculation (int)`(d: Double, i: Int, expected: Double) {
		val deg: Deg = d.deg

		val result1: Deg = i * deg
		val result2: Deg = deg * i

		result1.value shouldBe (expected plusOrMinus TOLERANCE)
		result2.value shouldBe (expected plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"12.34, 0, 0.0",
		"123.456, 2, 246.912",
	)
	@ParameterizedTest
	fun `multiplication calculation (long)`(d: Double, i: Long, expected: Double) {
		val deg: Deg = d.deg

		val result1: Deg = i * deg
		val result2: Deg = deg * i

		result1.value shouldBe (expected plusOrMinus TOLERANCE)
		result2.value shouldBe (expected plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"12.34, 0.0, 0.0",
		"123.456, 2.0, 246.912",
		"123.456, 2.5, 308.64",
	)
	@ParameterizedTest
	fun `multiplication calculation`(d: Double, i: Double, expected: Double) {
		val deg: Deg = d.deg

		val result1: Deg = i * deg
		val result2: Deg = deg * i

		result1.value shouldBe (expected plusOrMinus TOLERANCE)
		result2.value shouldBe (expected plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"+0.0000000000000000, 0.0",
		"+0.5000000000000000, 30.0",
		"+0.7071067811865476, 45.0",
		"+0.8660254037844387, 60.0",
		"+1.0000000000000000, 90.0",
		"+0.8660254037844387, 120.0",
		"+0.7071067811865476, 135.0",
		"+0.5000000000000000, 150.0",
		"+0.0000000000000000, 180.0",
		"-0.5000000000000000, 210.0",
		"-0.7071067811865476, 225.0",
		"-0.8660254037844387, 240.0",
		"-1.0000000000000000, 270.0",
		"-0.8660254037844387, 300.0",
		"-0.7071067811865476, 315.0",
		"-0.5000000000000000, 330.0",
		"+0.0000000000000000, 360.0",
	)
	@ParameterizedTest
	fun `sin function`(sin: Double, degree: Double) {
		val deg: Deg = degree.deg

		val sinValue: Double = sin(deg)

		sinValue shouldBe (sin plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"-1.0000000000000000, -90.0",
		"-0.8660254037844387, -60.0",
		"-0.7071067811865476, -45.0",
		"-0.5000000000000000, -30.0",
		"+0.0000000000000000,   0.0",
		"+0.5000000000000000, +30.0",
		"+0.7071067811865476, +45.0",
		"+0.8660254037844387, +60.0",
		"+1.0000000000000000, +90.0",
	)
	@ParameterizedTest
	fun `asin function`(sin: Double, degree: Double) {
		val deg: Deg = asin(sin)

		deg.value shouldBe (degree plusOrMinus TOLERANCE)
	}

	@CsvSource(
		"+1.0000000000000000, 0.0",
		"+0.8660254037844387, 30.0",
		"+0.7071067811865476, 45.0",
		"+0.5000000000000000, 60.0",
		"+0.0000000000000000, 90.0",
		"-0.5000000000000000, 120.0",
		"-0.7071067811865476, 135.0",
		"-0.8660254037844387, 150.0",
		"-1.0000000000000000, 180.0",
		"-0.8660254037844387, 210.0",
		"-0.7071067811865476, 225.0",
		"-0.5000000000000000, 240.0",
		"+0.0000000000000000, 270.0",
		"+0.5000000000000000, 300.0",
		"+0.7071067811865476, 315.0",
		"+0.8660254037844387, 330.0",
		"+1.0000000000000000, 360.0",
	)
	@ParameterizedTest
	fun `cos function`(cos: Double, degree: Double) {
		val deg: Deg = degree.deg

		val cosValue: Double = cos(deg)

		cosValue shouldBe (cos plusOrMinus TOLERANCE)
	}

	companion object {
		private const val TOLERANCE = 1e-12
	}
}
