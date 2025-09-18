package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.min
import net.twisterrob.astro.bazi.model.Phase

@Composable
public fun WuXingCycle(
	phase: Phase,
	onItemClick: (Phase) -> Unit,
	modifier: Modifier = Modifier,
) {
	val active = remember { mutableStateSetOf<Phase>() }
	val phases = generateSequence(phase) { it.livening }
		.take(Phase.entries.size)
		.toList()
	BoxWithConstraints(
		modifier = modifier.fillMaxSize(),
		contentAlignment = Alignment.Center,
	) {
		val size = min(maxWidth, maxHeight)
		// Just a guess, arbitrary ratio based on size.
		val circleRadius = size / @Suppress("detekt.MagicNumber") 5
		Box(
			modifier = Modifier
				// Offset of the circle compared to the pentagon/pentagram.
				.padding(circleRadius), // 0 = inside, radius = outside
			contentAlignment = Alignment.Center,
		) {
			CycleLines(
				phases = phases,
				active = active,
				next = Phase::conquering,
			)
			CycleLines(
				phases = phases,
				active = active,
				next = Phase::livening,
			)
		}
		Circles(
			phases = phases,
			active = active,
			circleRadius = circleRadius,
			onItemClick = onItemClick,
		)
	}
}

@Preview
@Composable
private fun WuXingCyclePreview(
	@PreviewParameter(PhaseProvider::class) phase: Phase,
) {
	WuXingCycle(
		phase = phase,
		onItemClick = {},
	)
}
