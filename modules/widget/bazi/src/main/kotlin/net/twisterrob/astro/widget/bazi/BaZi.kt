package net.twisterrob.astro.widget.bazi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.LineHeightStyle.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.bazi.model.Phase
import net.twisterrob.astro.component.theme.AppTheme

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
			Pillar(
				title = "Hour",
				top = {
					DisabledContentText {
						Character(
							symbol = "?",
							color = LocalContentColor.current,
							label = "Unknown"
						)
					}
				},
				bottom = {
					DisabledContentText {
						Character(
							symbol = "?",
							color = LocalContentColor.current,
							label = "Unknown\n"
						)
					}
				},
				modifier = space25percent,
			)
		}
	}
}

@Composable
private fun Pillar(
	title: String,
	top: @Composable () -> Unit,
	bottom: @Composable () -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier,
		horizontalAlignment = CenterHorizontally,
	) {
		Text(
			text = title,
			style = MaterialTheme.typography.labelLarge,
		)
		top()
		bottom()
	}
}

@Composable
private fun Pillar(title: String, pillar: BaZi.Pillar, modifier: Modifier = Modifier) {
	Pillar(
		title = title,
		top = {
			Character(
				symbol = pillar.heavenlyStem.symbol,
				color = pillar.heavenlyStem.phase.color,
				label = pillar.heavenlyStem.label,
			)
		},
		bottom = {
			Character(
				symbol = pillar.earthlyBranch.symbol,
				color = pillar.earthlyBranch.zodiac.charge.phase.color,
				label = pillar.earthlyBranch.label,
			)
		},
		modifier = modifier,
	)
}

@Composable
private fun Character(symbol: String, color: Color, label: String, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
		horizontalAlignment = CenterHorizontally,
	) {
		CharacterSymbol(
			symbol = symbol,
			color = color,
		)
		CharacterLabel(
			text = label,
		)
	}
}

@Composable
private fun CharacterSymbol(symbol: String, color: Color) {
	Text(
		text = symbol,
		color = color,
		style = MaterialTheme.typography.displayMedium.run {
			copy(
				lineHeight = fontSize * 1.5f,
				lineHeightStyle = LineHeightStyle(
					alignment = Alignment.Bottom,
					trim = LineHeightStyle.Trim.None,
				),
			)
		},
	)
}

@Composable
private fun CharacterLabel(text: String) {
	Text(
		text = text,
		textAlign = TextAlign.Center,
	)
}

@Composable
private fun DisabledContentText(content: @Composable () -> Unit) {
	CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)) {
		content()
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

private val HeavenlyStem.label: String
	get() = "${polarity.name} ${phase.label}"

private val EarthlyBranch.label: String
	get() = "${zodiac.name}\n${zodiac.charge.label}"

private val Phase.label: String
	get() = when (this) {
		Phase.Mu -> "Wood"
		Phase.Huo -> "Fire"
		Phase.Tu -> "Earth"
		Phase.Jin -> "Metal"
		Phase.Shui -> "Water"
	}

private val Phase.color: Color
	@Suppress("detekt.MagicNumber")
	get() = when (this) {
		Phase.Mu -> Color(0xFF90EE90)
		Phase.Huo -> Color(0xFFF08080)
		Phase.Tu -> Color(0xFFDEB887)
		Phase.Jin -> Color(0xFFB0B0B0)
		Phase.Shui -> Color(0xFF87CEFA)
	}

@Preview
@Composable
private fun BaZiFullPreview() {
	AppTheme {
		BaZi(
			BaZi(
				year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
				month = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Si),
				day = BaZi.Pillar(HeavenlyStem.Wu, EarthlyBranch.Shen),
				hour = BaZi.Pillar(HeavenlyStem.Xin, EarthlyBranch.Mao),
			)
		)
	}
}

@Preview
@Composable
private fun BaZiHourlessPreview() {
	AppTheme {
		BaZi(
			BaZi(
				year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
				month = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Si),
				day = BaZi.Pillar(HeavenlyStem.Wu, EarthlyBranch.Shen),
				hour = null,
			)
		)
	}
}
