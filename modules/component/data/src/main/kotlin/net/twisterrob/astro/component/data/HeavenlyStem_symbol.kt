package net.twisterrob.astro.component.data

import net.twisterrob.astro.bazi.model.HeavenlyStem

public val HeavenlyStem.symbol: String
	get() = when (this) {
		HeavenlyStem.Jia -> "甲" // Yang Wood
		HeavenlyStem.Yi -> "乙" // Yin Wood
		HeavenlyStem.Bing -> "丙" // Yang Fire
		HeavenlyStem.Ding -> "丁" // Yin Fire
		HeavenlyStem.Wu -> "戊" // Yang Earth
		HeavenlyStem.Ji -> "己" // Yin Earth
		HeavenlyStem.Geng -> "庚" // Yang Metal
		HeavenlyStem.Xin -> "辛" // Yin Metal
		HeavenlyStem.Ren -> "壬" // Yang Water
		HeavenlyStem.Gui -> "癸" // Yin Water
	}
