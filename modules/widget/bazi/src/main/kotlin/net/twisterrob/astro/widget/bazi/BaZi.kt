package net.twisterrob.astro.widget.bazi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.bazi.model.Phase

/**
 * Widget to render a basic BaZi chart.
 */
@Composable
public fun BaZi(bazi: BaZi, modifier: Modifier = Modifier) {
	Row(
		modifier = modifier
			.fillMaxWidth(),
	) {
		val space25percent = Modifier.weight(1f)
		Pillar(
			title = "Year",
			pillar = bazi.year,
			modifier = space25percent,
		)
		Pillar(
			title = "Month",
			pillar = bazi.month,
			modifier = space25percent,
		)
		Pillar(
			title = "Day",
			pillar = bazi.day,
			modifier = space25percent,
		)
		val hour = bazi.hour
		if (hour != null) {
			Pillar(
				title = "Hour",
				pillar = hour,
				modifier = space25percent,
			)
		} else {
			Box(
				modifier = space25percent,
			)
		}
	}
}

@Composable
private fun Pillar(title: String, pillar: BaZi.Pillar, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
		horizontalAlignment = CenterHorizontally,
	) {
		Text(
			text = title,
		)
		Character(
			character = pillar.heavenlyStem,
		)
		Character(
			character = pillar.earthlyBranch,
		)
	}
}

@Composable
private fun Character(character: HeavenlyStem, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
		horizontalAlignment = CenterHorizontally,
	) {
		Text(
			text = character.symbol,
			style = MaterialTheme.typography.displaySmall,
		)
		Text(
			text = "${character.polarity.name} ${character.phase.label}",
		)
	}
}

@Composable
private fun Character(character: EarthlyBranch, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
		horizontalAlignment = CenterHorizontally,
	) {
		Text(
			text = character.symbol,
			style = MaterialTheme.typography.displaySmall,
		)
		Text(
			text = character.zodiac.name,
		)
	}
}

private val EarthlyBranch.symbol: String
	get() = when (this) {
		EarthlyBranch.Zi -> "子"
		EarthlyBranch.Chou -> "丑"
		EarthlyBranch.Yin -> "寅"
		EarthlyBranch.Mao -> "卯"
		EarthlyBranch.Chen -> "辰"
		EarthlyBranch.Si -> "巳"
		EarthlyBranch.Wu -> "午"
		EarthlyBranch.Wei -> "未"
		EarthlyBranch.Shen -> "申"
		EarthlyBranch.You -> "酉"
		EarthlyBranch.Xu -> "戌"
		EarthlyBranch.Hai -> "亥"
	}

private val HeavenlyStem.symbol: String
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

private val Phase.label: String
	get() = when (this) {
		Phase.Mu -> "Wood"
		Phase.Huo -> "Fire"
		Phase.Tu -> "Earth"
		Phase.Jin -> "Metal"
		Phase.Shui -> "Water"
	}

@Preview
@Composable
private fun BaZiFullPreview() {
	BaZi(
		BaZi(
			year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
			month = BaZi.Pillar(HeavenlyStem.Yi, EarthlyBranch.Chou),
			day = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Yin),
			hour = BaZi.Pillar(HeavenlyStem.Ding, EarthlyBranch.Mao),
		)
	)
}

@Preview
@Composable
private fun BaZiHourlessPreview() {
	BaZi(
		BaZi(
			year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
			month = BaZi.Pillar(HeavenlyStem.Yi, EarthlyBranch.Chou),
			day = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Yin),
			hour = null,
		)
	)
}
