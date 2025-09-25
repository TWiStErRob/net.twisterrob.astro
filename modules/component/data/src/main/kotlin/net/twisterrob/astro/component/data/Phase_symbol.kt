package net.twisterrob.astro.component.data

import net.twisterrob.astro.bazi.model.Phase

public val Phase.symbol: String
	get() = when (this) {
		Phase.Mu -> "木" // Wood
		Phase.Huo -> "火" // Fire
		Phase.Tu -> "土" // Earth
		Phase.Jin -> "金" // Metal
		Phase.Shui -> "水" // Water
	}
