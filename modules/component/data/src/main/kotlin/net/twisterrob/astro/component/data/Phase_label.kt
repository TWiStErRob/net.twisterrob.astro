package net.twisterrob.astro.component.data

import net.twisterrob.astro.bazi.model.Phase

/**
 * Localized name for Wuxing [Phase]s.
 */
public val Phase.label: String
	get() = when (this) {
		Phase.Mu -> "Wood"
		Phase.Huo -> "Fire"
		Phase.Tu -> "Earth"
		Phase.Jin -> "Metal"
		Phase.Shui -> "Water"
	}
