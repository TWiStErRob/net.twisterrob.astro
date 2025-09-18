package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.bazi.model.Phase

@Composable
public fun Circles(
	phases: List<Phase>,
	active: MutableSet<Phase>,
	size: Dp,
	circleRadius: Dp,
	offset: Dp = circleRadius / 2,
	onItemClick: (Phase) -> Unit,
) {
	Box(
		modifier = Modifier
			.size(size + circleRadius * 2),
		contentAlignment = Alignment.Center
	) {
		// Compute circle positions slightly outside the pentagon
		val circlePositions = pentagramPoints(size, outset = offset)
		phases.forEach { phase ->
			val pos = circlePositions[phases.indexOf(phase)]
			val x = with(LocalDensity.current) { pos.x.toDp() }
			val y = with(LocalDensity.current) { pos.y.toDp() }
			PhaseCircle(
				phase = phase,
				modifier = Modifier
					.offset(x - size / 2, y - size / 2),
				radius = circleRadius,
				isActive = phase in active,
				onClick = {
					if (phase in active) {
						active.remove(phase)
					} else {
						active.add(phase)
						onItemClick(phase)
					}
				},
			)
		}
	}
}

@Preview
@Composable
private fun CirclesPreview() {
	Circles(
		phases = phases,
		active = mutableSetOf(),
		size = 300.dp,
		circleRadius = 50.dp,
		onItemClick = { /* click preview */ },
	)
}
