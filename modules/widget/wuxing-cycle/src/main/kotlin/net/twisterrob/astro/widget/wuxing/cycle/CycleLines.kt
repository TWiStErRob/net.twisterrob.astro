package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.width
import net.twisterrob.astro.bazi.model.Phase
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

@Composable
internal fun CycleLines(
	phases: List<Phase>,
	active: Set<Phase>,
	next: Phase.() -> Phase,
) {
	require(phases.size == Phase.entries.size) { "All phases must be covered." }
	require(phases.distinct() == phases) { "Phases must be unique." }

	BoxWithConstraints(
		modifier = Modifier,
		contentAlignment = Alignment.Center,
	) {
		val thick = 50.dp
		val thin = 20.dp
		val inset = thick / 2
		val size = min(maxWidth, maxHeight)
		val positions2: List<DpOffset> = pentagramPoints(size)
		val positions: List<DpOffset> = pentagramPoints(size)
		val maxStrokePx = with(LocalDensity.current) { thick.toPx() } / 2f
//		val bound = boundingBox(positions).outset(thick / 2)
		Canvas(
			modifier = Modifier
				.size(size),
		) {
//			drawCircle(
//				color = Color.Blue,
//				radius = 4f,
//				center = bound.center.toPx(),
//			)
			drawCircle(
				color = Color.Blue,
				radius = 4f,
				center = DpOffset(size / 2f, size / 2f).toPx(),
			)
			positions.forEach {
				drawCircle(
					color = Color.Red,
					radius = 8f,
					center = it.toPx(),
				)
			}
//			drawCircle(
//				color = Color.Blue,
//				radius = max(bound.width, bound.height).toPx() / 2f,
//				center = bound.center.toPx(),
//				style = Stroke(),
//			)
			drawCircle(
				color = Color.Blue,
				radius = (size / 2).toPx(),
				center = DpOffset(size / 2, size / 2).toPx(),
				style = Stroke(),
			)
//			drawCircle(
//				color = Color.Blue,
//				radius = min(bound.width, bound.height).toPx() / 2f,
//				center = bound.center.toPx(),
//				style = Stroke(),
//			)
			val positionsPx = positions.map { it.toPx() }
			for (phase in phases) {
				val startPhase = phase
				val endPhase = phase.next()
				val isActive = startPhase in active || endPhase in active

				val start = positionsPx[phases.indexOf(startPhase)]
				val end = positionsPx[phases.indexOf(endPhase)]
				val stroke = if (isActive) thick else thin
				val brush = Brush.linearGradient(
					colors = listOf(
						startPhase.color.copy(alpha = .3f),
						endPhase.color.copy(alpha = .3f)
					),
					start = start,
					end = end,
					tileMode = TileMode.Clamp,
				)

				drawLine(
					brush = brush,
					start = start,
					end = end,
					strokeWidth = stroke.toPx(),
					cap = StrokeCap.Round
				)
				drawLine(
					brush = SolidColor(Color.Red),
					start = start,
					end = end,
					strokeWidth = Stroke.HairlineWidth,
					cap = StrokeCap.Round
				)
				drawLine(
					brush = SolidColor(Color.DarkGray),
					start = positions2[phases.indexOf(startPhase)].toPx(),
					end = positions2[phases.indexOf(endPhase)].toPx(),
					strokeWidth = Stroke.HairlineWidth,
					cap = StrokeCap.Round
				)
			}
		}
	}
}

private fun DpRect.outset(amount: Dp): DpRect =
	DpRect(
		left = left - amount,
		top = top - amount,
		right = right + amount,
		bottom = bottom + amount,
	)

private val DpRect.center: DpOffset
	get() = DpOffset(
		x = left + width / 2f,
		y = top + height / 2f,
	)

context(density: Density)
@Stable
private fun DpOffset.toPx(): Offset = with(density) { Offset(x.toPx(), y.toPx()) }

private fun boundingBox(positions: List<DpOffset>): DpRect {
	val minX = positions.minOf { it.x }
	val maxX = positions.maxOf { it.x }
	val minY = positions.minOf { it.y }
	val maxY = positions.maxOf { it.y }
	return DpRect(left = minX, top = minY, right = maxX, bottom = maxY)
}

/**
 * Convex equilateral (regular) pentagon maximally inscribed in a square with sides [size] without rotation.
 * https://uploads-cdn.omnicalculator.com/images/pentagon/area-of-pentagon.webp?width=425&enlarge=0&format=webp
 * https://www.omnicalculator.com/math/pentagon
 */
@Suppress("LocalVariableName", "NonAsciiCharacters")
@Composable
internal fun pentagramPoints(size: Dp): List<DpOffset> {
	// Notation based on https://en.wikipedia.org/wiki/Pentagon#/media/File:Regular_pentagon_1.svg
	val pentagonVerticesCount = 5
	val φ = (1 + sqrt(5f)) / 2 // φ = (1 + √5) / 2 = 1.6180339
	val π = PI.toFloat()
	val α = 3 * π / pentagonVerticesCount // 540° (3π) total, 108° each
	// Using the input size as the width of the pentagon, which gives us the widest possible pentagon.
	val d = size
	// d = φ * t =>
	val t = d / φ
	// From the width, we can calculate the circumradius `R` so we can get the vertices through angular math.
	// d = 2Rcos(π/10) =>
	val R = d / (2 * cos(π / 10)) // d / 1.902 or d * 0.5257
	val r = t / 2 * tan(π / 5)
	// d = sqrt(...) * h =>
//	val h = d / sqrt(2 - 2 / sqrt(5f)) // * 0.9510565f
	val h = r + R
	// 360° full circle divided into 5 is 72° each
	val angleStep = 2 * π / pentagonVerticesCount
	// α / 2 = 54° is the angle between the floor and the line from the center to the lower corners.
	val what = t * tan(α / 2)

	// To get the horizontal center, we can just use half the width,
	// because the pentagon is as wide as the bounds, by definition of `d`.
	val cx = d / 2f
	// To find the vertical center, we need to find the distance from the center to the bottom,
	// tan(54°) = th / r => th = r * tan(54°)
	val leftOver = (size - what) / 2
	val cy = size / 2 + leftOver / 2
	val center = DpOffset(cx, cy)
//	val center = DpOffset(size / 2f, size / 2f)
//	val center = DpOffset(size / 2f, size * (1 - 0.425f)) // to visually center the pentagon
//	val center = DpOffset(size / 2f, size / 2f + size * 0.1625f / 2) // to visually center the pentagon
//	val R = size / 2 * 0.8506508f
	// The bounding box width of a pentagon: w = r * (cos(π/10) + cos(3π/10)) * 2
//	val R = size * 0.62573786f // (2 * cos(π / 20) * cos(π / 5)).toFloat()
//	val R = size / 2 * cos(π / 10).toFloat()
	val startAngle = -π / 2

	return List(pentagonVerticesCount) { i ->
		val angle = startAngle + i * angleStep
		DpOffset(
			x = center.x + R * cos(angle),
			y = center.y + R * sin(angle),
		)
	} + center
}

@Preview(widthDp = 200, heightDp = 200)
@Composable
private fun DestructiveCyclePreview() {
	CycleLines(
		phases = Phase.entries,
		active = setOf(Phase.Shui),
		next = Phase::conquering,
	)
}

@Preview(widthDp = 200, heightDp = 200)
@Composable
private fun GenerativeCyclePreview() {
	CycleLines(
		phases = Phase.entries,
		active = setOf(Phase.Shui),
		next = Phase::livening,
	)
}

@Preview(widthDp = 200, heightDp = 200)
@Composable
private fun GenerativeCycleAllSelectedPreview() {
	CycleLines(
		phases = Phase.entries,
		active = Phase.entries.toSet(),
		next = Phase::livening,
	)
}

@Preview(widthDp = 200, heightDp = 200)
@Composable
private fun GenerativeCycleNoSelectedPreview() {
	CycleLines(
		phases = Phase.entries,
		active = emptySet(),
		next = Phase::livening,
	)
}

@Preview(widthDp = 400, heightDp = 200)
@Composable
private fun LandscapePreview() {
	CycleLines(
		phases = Phase.entries,
		active = emptySet(),
		next = Phase::livening,
	)
}

@Preview(widthDp = 200, heightDp = 400)
@Composable
private fun PortraitPreview() {
	CycleLines(
		phases = Phase.entries,
		active = emptySet(),
		next = Phase::livening,
	)
}
