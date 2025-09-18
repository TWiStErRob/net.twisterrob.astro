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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import net.twisterrob.astro.bazi.model.Phase

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
		val size = min(maxWidth, maxHeight)
		val positions: List<DpOffset> = pentagramPoints(size)
		Canvas(
			modifier = Modifier
				.size(size),
		) {
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
			}
		}
	}
}

context(density: Density)
@Stable
private fun DpOffset.toPx(): Offset = with(density) { Offset(x.toPx(), y.toPx()) }

/**
 * Convex equilateral pentagon maximally inscribed in a square with sides [size].
 */
@Composable
internal fun pentagramPoints(size: Dp): List<DpOffset> {
	val y = size * 0.391642f       // left/right vertices
	val xLeft = size * 0.182438f   // bottom-left
	val xRight = size * 0.817562f  // bottom-right

	return listOf(
		DpOffset(size / 2f, 0.dp),       // top
		DpOffset(size, y),             // right
		DpOffset(xRight, size),        // bottom-right
		DpOffset(xLeft, size),         // bottom-left
		DpOffset(0.dp, y)                // left
	)
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
