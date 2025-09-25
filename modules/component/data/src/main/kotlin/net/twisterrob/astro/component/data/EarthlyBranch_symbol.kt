package net.twisterrob.astro.component.data

import net.twisterrob.astro.bazi.model.EarthlyBranch

public val EarthlyBranch.symbol: String
	get() = when (this) {
		EarthlyBranch.Zi -> "子" // Rat
		EarthlyBranch.Chou -> "丑" // Ox
		EarthlyBranch.Yin -> "寅" // Tiger
		EarthlyBranch.Mao -> "卯" // Rabbit
		EarthlyBranch.Chen -> "辰" // Dragon
		EarthlyBranch.Si -> "巳" // Snake
		EarthlyBranch.Wu -> "午" // Horse
		EarthlyBranch.Wei -> "未" // Goat
		EarthlyBranch.Shen -> "申" // Monkey
		EarthlyBranch.You -> "酉" // Rooster
		EarthlyBranch.Xu -> "戌" // Dog
		EarthlyBranch.Hai -> "亥" // Pig
	}
