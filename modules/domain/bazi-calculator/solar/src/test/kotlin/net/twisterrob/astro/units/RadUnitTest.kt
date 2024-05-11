package net.twisterrob.astro.units

import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.PI

@Suppress("detekt.UnusedParameter", "UNUSED_PARAMETER")
class RadUnitTest {

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
		val rad: Rad = d.rad

		rad.value shouldBe d
	}

	@MethodSource("sinCosDegRad")
	@ParameterizedTest
	fun `rad to deg conversion`(sin: Double, cos: Double, degree: Double, radian: Double) {
		val rad: Rad = radian.rad

		val deg: Deg = rad.deg

		deg.value shouldBe (degree plusOrMinus TOLERANCE)
	}

	@MethodSource("sinCosDegRad")
	@ParameterizedTest
	fun `deg to rad conversion`(sin: Double, cos: Double, degree: Double, radian: Double) {
		val deg: Deg = degree.deg

		val rad: Rad = deg.rad

		rad.value shouldBe (radian plusOrMinus TOLERANCE)
	}

	@MethodSource("sinCosDegRad")
	@ParameterizedTest
	fun `sin function`(sin: Double, cos: Double, degree: Double, radian: Double) {
		val rad: Rad = radian.rad

		val sinValue: Double = sin(rad)

		sinValue shouldBe (sin plusOrMinus TOLERANCE)
	}

	@MethodSource("sinCosDegRad")
	@ParameterizedTest
	fun `cos function`(sin: Double, cos: Double, degree: Double, radian: Double) {
		val rad: Rad = radian.rad

		val cosValue: Double = cos(rad)

		cosValue shouldBe (cos plusOrMinus TOLERANCE)
	}

	companion object {
		private const val TOLERANCE = 1e-12

		@JvmStatic
		fun sinCosDegRad(): List<Arguments> =
			listOf(
				arguments(+0.0000000000000000, +1.0000000000000000, 0.0, 0.0),
				arguments(+0.5000000000000000, +0.8660254037844387, 30.0, (PI / 6)),
				arguments(+0.7071067811865476, +0.7071067811865476, 45.0, (PI / 4)),
				arguments(+0.8660254037844387, +0.5000000000000000, 60.0, (PI / 3)),
				arguments(+1.0000000000000000, +0.0000000000000000, 90.0, (PI / 2)),
				arguments(+0.8660254037844387, -0.5000000000000000, 120.0, (2 * PI / 3)),
				arguments(+0.7071067811865476, -0.7071067811865476, 135.0, (3 * PI / 4)),
				arguments(+0.5000000000000000, -0.8660254037844387, 150.0, (5 * PI / 6)),
				arguments(+0.0000000000000000, -1.0000000000000000, 180.0, PI),
				arguments(-0.5000000000000000, -0.8660254037844387, 210.0, (7 * PI / 6)),
				arguments(-0.7071067811865476, -0.7071067811865476, 225.0, (5 * PI / 4)),
				arguments(-0.8660254037844387, -0.5000000000000000, 240.0, (4 * PI / 3)),
				arguments(-1.0000000000000000, +0.0000000000000000, 270.0, (3 * PI / 2)),
				arguments(-0.8660254037844387, +0.5000000000000000, 300.0, (5 * PI / 3)),
				arguments(-0.7071067811865476, +0.7071067811865476, 315.0, (7 * PI / 4)),
				arguments(-0.5000000000000000, +0.8660254037844387, 330.0, (11 * PI / 6)),
				arguments(+0.0000000000000000, +1.0000000000000000, 360.0, (2 * PI))
			)
	}
}
