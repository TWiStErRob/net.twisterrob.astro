/**
 * Pentagon Layout
 * * Five circles arranged at the corners of a pentagon.
 * * Circles positioned slightly outside the pentagon’s corners (on a larger orbit).
 *
 * Circle Properties
 * * Colors (clockwise from top/North): Yellow, Gray, Blue, Green, Red.
 * * Circles are clickable, supporting multiple active circles simultaneously.
 * * Clicking a circle triggers visual feedback: glow/expansion; clicking again deactivates it.
 *
 * Connecting Lines
 * * Lines connect adjacent circles.
 * * Lines display gradients blending the colors of connected circles.
 * * Active lines animate with a flowing gradient and subtle pulsing effect.
 *
 * Interactions & Animations
 * * Multiple circles can be active at the same time.
 * * Each active circle independently animates its connected lines.
 * * Visual feedback includes circle glow, scaling, and animated flowing lines.
 */
package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.bazi.model.Phase

@Composable
public fun WuXingCycle(
	onItemClick: (Phase) -> Unit,
	modifier: Modifier = Modifier,
	circleRadius: Dp = 50.dp,
	size: Dp = 300.dp,
) {
	val active = remember { mutableStateSetOf<Phase>() }
	Box(
		modifier = modifier
			.size(size + circleRadius * 3),
		contentAlignment = Alignment.Center
	) {
		CycleLines(
			phases = phases,
			active = active,
			next = Phase::conquering,
			size = size,
		)
		CycleLines(
			phases = phases,
			active = active,
			next = Phase::livening,
			size = size,
		)
		Circles(
			phases = phases,
			active = active,
			size = size,
			circleRadius = circleRadius,
			offset = circleRadius / 4,
			onItemClick = onItemClick,
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun WuXingCyclePreview() {
	WuXingCycle(
		onItemClick = {},
	)
}
