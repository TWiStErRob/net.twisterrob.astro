package net.twisterrob.astro.widget.wuxing.cycle

import androidx.compose.ui.graphics.Color
import net.twisterrob.astro.bazi.model.Phase

internal val Phase.label: String
	get() = when (this) {
		Phase.Mu -> "木" // "Wood"
		Phase.Huo -> "火" // "Fire"
		Phase.Tu -> "土" // "Earth"
		Phase.Jin -> "金" // "Metal"
		Phase.Shui -> "水" // "Water"
	}

internal val Phase.color: Color
	@Suppress("detekt.MagicNumber")
	get() = when (this) {
		Phase.Mu -> Color(0xFF90EE90)
		Phase.Huo -> Color(0xFFF08080)
		Phase.Tu -> Color(0xFFDEB887)
		Phase.Jin -> Color(0xFFB0B0B0)
		Phase.Shui -> Color(0xFF87CEFA)
	}
