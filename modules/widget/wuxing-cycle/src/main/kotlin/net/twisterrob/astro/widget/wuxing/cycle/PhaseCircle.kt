package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.bazi.model.Phase
import net.twisterrob.astro.component.data.color
import net.twisterrob.astro.component.data.label

private const val NormalScale = 1f
private const val ActiveScale = 1.2f

@Composable
internal fun PhaseCircle(
	phase: Phase,
	radius: Dp,
	isActive: Boolean,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	val scale by animateFloatAsState(if (isActive) ActiveScale else NormalScale)

	val elevation = if (isActive) radius / @Suppress("detekt.MagicNumber") 3 else 0.dp
	Box(
		modifier = modifier
			.size(radius)
			.scale(scale)
			.shadow(elevation, shape = CircleShape, clip = false)
			.clip(CircleShape)
			.background(phase.color, shape = CircleShape)
			.clickable { onClick() },
		contentAlignment = Alignment.Center,
	) {
		Text(
			text = phase.label,
			style = MaterialTheme.typography.displayMedium,
			fontSize = with(LocalDensity.current) { (radius / 2).toSp() },
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun PentagonCirclePreview(
	@PreviewParameter(PhaseProvider::class) phase: Phase,
) {
	PhaseCircle(
		phase = phase,
		radius = 50.dp,
		isActive = false,
		onClick = {},
	)
}
