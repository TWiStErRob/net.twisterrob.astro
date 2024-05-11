package net.twisterrob.astro.units

import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CanonicalModKtUnitTest {

	@CsvSource(
		"1.0, 2.0, 1.0",
		"5.0, 2.0, 1.0",
		"1.23, 1.0, 0.23",
		"1.0, -2.0, -1.0",
		"-1.0, 2.0, 1.0",
		"2.0, -3.0, -1.0",
		"-3.0, 2.0, 1.0",
		"-3.0, -2.0, -1.0",
		"34.567, 20.0, 14.567",
		"-34.567, 20.0, 5.433",
	)
	@ParameterizedTest
	fun `test canonicalMod`(n: Double, cycle: Double, expected: Double) {
		val result: Double = n.canonicalMod(cycle)

		result shouldBe (expected plusOrMinus 1e-14)
	}

	@CsvSource(
		"1, 2, 1",
		"5, 2, 1",
		"1, -2, -1",
		"-1, 2, 1",
		"2, -3, -1",
		"-3, 2, 1",
		"-3, -2, -1",
	)
	@ParameterizedTest
	fun `test canonicalMod`(n: Int, cycle: Int, expected: Int) {
		val result: Int = n.canonicalMod(cycle)

		result shouldBe expected
	}
}
