package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.bazi.model.Phase

@Composable
public fun WuXingCycle(
	phase: Phase,
	size: Dp,
	circleRadius: Dp,
	onItemClick: (Phase) -> Unit,
	modifier: Modifier = Modifier,
) {
	val active = remember { mutableStateSetOf<Phase>() }
	val phases = generateSequence(phase) { it.livening }
		.take(Phase.entries.size)
		.toList()
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
private fun WuXingCyclePreview(
	@PreviewParameter(PhaseProvider::class) phase: Phase,
) {
	WuXingCycle(
		phase = phase,
		size = 300.dp,
		circleRadius = 50.dp,
		onItemClick = {},
	)
}
