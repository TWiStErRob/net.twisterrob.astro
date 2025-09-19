package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import net.twisterrob.astro.bazi.model.Phase

internal class PhaseProvider : PreviewParameterProvider<Phase> {
	override val values: Sequence<Phase>
		get() = Phase.entries.asSequence()
}
