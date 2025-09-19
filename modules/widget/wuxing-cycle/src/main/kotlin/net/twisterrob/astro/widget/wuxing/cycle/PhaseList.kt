package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.runtime.Immutable
import net.twisterrob.astro.bazi.model.Phase

@Immutable
internal data class PhaseList(val items: List<Phase>) {
	constructor(vararg items: Phase): this(items.toList())
}
