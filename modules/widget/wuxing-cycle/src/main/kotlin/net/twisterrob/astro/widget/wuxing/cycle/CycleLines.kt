package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.bazi.model.Phase
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun CycleLines(
	phases: List<Phase>,
	active: Set<Phase>,
	size: Dp,
	next: Phase.() -> Phase,
) {
	require(phases.size == Phase.entries.size) { "All phases must be covered." }
	require(phases.distinct() == phases) { "Phases must be unique." }

	val thick = 8.dp
	val thin = 4.dp
	val positions: List<Offset> = pentagramPoints(size - thin, 0.dp)
	Canvas(
		modifier = Modifier
			.width(size)
			.height(size),
	) {
		for (phase in phases) {
			val startPhase = phase
			val endPhase = phase.next()
			val isActive = startPhase in active || endPhase in active

			val start = positions[phases.indexOf(startPhase)]
			val end = positions[phases.indexOf(endPhase)]
			val stroke = if (isActive) thick else thin
			val brush = Brush.linearGradient(
				colors = listOf(startPhase.color, endPhase.color),
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
		}
	}
}

@Composable
internal fun pentagramPoints(size: Dp, outset: Dp): List<Offset> {
	val pixelSize = with(LocalDensity.current) { size.toPx() }
	val outsetSize = with(LocalDensity.current) { outset.toPx() }
	val center = Offset(pixelSize / 2f, pixelSize / 2f)
	val radius = (pixelSize / 2f) + outsetSize
	val startAngle = -Math.PI / 2
	val pointsInPentagram = 5
	val angleStep = 2 * Math.PI / pointsInPentagram

	return List(pointsInPentagram) { i ->
		val angle = startAngle + i * angleStep
		Offset(
			x = center.x + radius * cos(angle).toFloat(),
			y = center.y + radius * sin(angle).toFloat()
		)
	}
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
private fun DestructiveCyclePreview() {
	CycleLines(
		phases = Phase.entries,
		active = setOf(Phase.Shui),
		next = Phase::conquering,
		size = 200.dp,
	)
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
private fun GenerativeCyclePreview() {
	CycleLines(
		phases = Phase.entries,
		active = setOf(Phase.Shui),
		next = Phase::livening,
		size = 200.dp,
	)
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
private fun GenerativeCycleAllSelectedPreview() {
	CycleLines(
		phases = Phase.entries,
		active = Phase.entries.toSet(),
		next = Phase::livening,
		size = 200.dp,
	)
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
private fun GenerativeCycleNoSelectedPreview() {
	CycleLines(
		phases = Phase.entries,
		active = emptySet(),
		next = Phase::livening,
		size = 200.dp,
	)
}