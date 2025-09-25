package net.twisterrob.astro.component.data

import net.twisterrob.astro.bazi.model.HeavenlyStem

public val HeavenlyStem.symbol: String
	get() = when (this) {
		HeavenlyStem.Jia -> "甲"
		HeavenlyStem.Yi -> "乙"
		HeavenlyStem.Bing -> "丙"
		HeavenlyStem.Ding -> "丁"
		HeavenlyStem.Wu -> "戊"
		HeavenlyStem.Ji -> "己"
		HeavenlyStem.Geng -> "庚"
		HeavenlyStem.Xin -> "辛"
		HeavenlyStem.Ren -> "壬"
		HeavenlyStem.Gui -> "癸"
	}
