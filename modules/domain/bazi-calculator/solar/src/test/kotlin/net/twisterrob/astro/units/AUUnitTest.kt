package net.twisterrob.astro.units

import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AUUnitTest {

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
		val au: AU = d.au

		au.value shouldBe d
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
		val au1: AU = d1.au
		val au2: AU = d2.au

		val result: AU = au1 + au2

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
		val au1: AU = d1.au
		val au2: AU = d2.au

		val result: AU = au1 - au2

		result.value shouldBe (expected plusOrMinus TOLERANCE)
	}

	companion object {
		private const val TOLERANCE = 1e-14
	}
}
