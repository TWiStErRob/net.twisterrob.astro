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
import androidx.compose.ui.res.stringResource
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
		val hour = bazi.hour
		if (hour != null) {
			Pillar(
				title = stringResource(R.string.widget_bazi__pillar_title_hour),
				pillar = hour,
				modifier = space25percent,
			)
		} else {
			Pillar(
				title = stringResource(R.string.widget_bazi__pillar_title_hour),
				top = {
					DisabledContentText {
						Character(
							symbol = stringResource(R.string.widget_bazi__pillar_unknown_hour_stem_symbol),
							color = LocalContentColor.current,
							label = stringResource(R.string.widget_bazi__pillar_unknown_hour_stem_label)
						)
					}
				},
				bottom = {
					DisabledContentText {
						Character(
							symbol = stringResource(R.string.widget_bazi__pillar_unknown_hour_branch_symbol),
							color = LocalContentColor.current,
							label = "${stringResource(R.string.widget_bazi__pillar_unknown_hour_branch_label)}\n"
						)
					}
				},
				modifier = space25percent,
			)
		}
		Pillar(
			title = stringResource(R.string.widget_bazi__pillar_title_day),
			pillar = bazi.day,
			modifier = space25percent,
		)
		Pillar(
			title = stringResource(R.string.widget_bazi__pillar_title_month),
			pillar = bazi.month,
			modifier = space25percent,
		)
		Pillar(
			title = stringResource(R.string.widget_bazi__pillar_title_year),
			pillar = bazi.year,
			modifier = space25percent,
		)
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
				color = @Suppress("detekt.MaxChainedCallsOnSameLine")
				pillar.earthlyBranch.zodiac.charge.phase.color,
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
		style = MaterialTheme.typography.displayMedium,
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
