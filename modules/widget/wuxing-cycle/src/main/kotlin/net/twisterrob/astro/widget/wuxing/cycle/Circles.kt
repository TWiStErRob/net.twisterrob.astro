package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import net.twisterrob.astro.bazi.model.Phase

@Composable
internal fun Circles(
	phases: List<Phase>,
	active: MutableSet<Phase>,
	circleRadius: Dp,
	onSelect: (Phase) -> Unit,
	onDeselect: (Phase) -> Unit,
	modifier: Modifier = Modifier,
) {
	require(phases.size == Phase.entries.size) { "All phases must be covered." }
	BoxWithConstraints(
		modifier = modifier,
		contentAlignment = Alignment.Center,
	) {
		val size = min(maxWidth, maxHeight) - circleRadius
		val circlePositions = pentagonPoints(size)
		phases.forEachIndexed { index, phase ->
			val pos = circlePositions[index]
			PhaseCircle(
				phase = phase,
				modifier = Modifier
					.offset(pos.x - size / 2, pos.y - size / 2),
				radius = circleRadius,
				isActive = phase in active,
				onClick = {
					if (phase in active) {
						active.remove(phase)
						onDeselect(phase)
					} else {
						active.add(phase)
						onSelect(phase)
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
		modifier = Modifier.fillMaxSize(),
		phases = Phase.entries,
		active = mutableSetOf(),
		circleRadius = 50.dp,
		onSelect = {},
		onDeselect = {},
	)
}
