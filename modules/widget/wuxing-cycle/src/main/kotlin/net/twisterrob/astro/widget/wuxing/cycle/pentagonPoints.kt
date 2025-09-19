package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

/**
 * Convex equilateral (regular) pentagon maximally inscribed in a square with sides [size] without rotation.
 */
@Suppress("LocalVariableName", "NonAsciiCharacters", "detekt.VariableNaming", "detekt.MagicNumber")
@Composable
internal fun pentagonPoints(size: Dp): List<DpOffset> {
	val π = PI.toFloat()
	val (center, radius) = maximallyInscribedPentagonCircumCircle(size)

	val pentagonVertices = 5
	// 360° (2π) full circle divided into 5 is 72° each
	val angleStep = 2 * π / pentagonVertices
	// Start at the top (12 o'clock)
	val startAngle = -(π / 2)

	return List(pentagonVertices) { i ->
		val angle = startAngle + i * angleStep
		DpOffset(
			x = center.x + radius * cos(angle),
			y = center.y + radius * sin(angle),
		)
	} + center
}

/**
 * Notation based on https://en.wikipedia.org/wiki/Pentagon#/media/File:Regular_pentagon_1.svg
 * Math/constants based on https://en.wikipedia.org/wiki/Pentagon
 * Example numbers and calculations validated against https://www.omnicalculator.com/math/pentagon
 */
@Suppress("LocalVariableName", "NonAsciiCharacters", "detekt.VariableNaming", "detekt.MagicNumber")
private fun maximallyInscribedPentagonCircumCircle(size: Dp): Pair<DpOffset, Dp> {
	val π = PI.toFloat()
	val φ = (1 + sqrt(5f)) / 2 // φ = (1 + √5) / 2 = 1.6180339
	// Using the input size as the width of the pentagon, which gives us the widest possible pentagon.
	val d = size
	// d = φ * t =>
	val t = d / φ
	// From the width, we can calculate the circumradius `R` so we can get the vertices through angular math.
	// d = 2Rcos(π/10) =>
	val R = d / (2 * cos(π / 10)) // d / 1.902 or d * 0.5257
	val r = t / (2 * tan(π / 5))
	// d = sqrt(...) * h =>
//	val h = d / sqrt(2 - 2 / sqrt(5f)) // * 0.9510565f
	val h = r + R

	// To get the horizontal center, we can just use half the width,
	// because the pentagon is as wide as the bounds, by definition of `d`.
	val cx = d / 2f
	// To get the vertical center, we can use R as the base, because the top vertex is at 0 height.
	// The remaining space is the difference between the bound's height and the pentagon height.
	val leftOver = (size - h)
	// We split the remaining space equally above and below the pentagon to get perfect center.
	val cy = R + leftOver / 2
	val center = DpOffset(cx, cy)
	return Pair(center, R)
}
