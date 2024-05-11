package net.twisterrob.astro.widget.bazi

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.bazi.model.Phase
import net.twisterrob.astro.component.theme.AppTheme

/**
 * Widget to render a basic BaZi chart.
 */
@Composable
public fun BaZi(
	modifier: Modifier = Modifier,
	bazi: BaZi,
	onHourIncreased: () -> Unit = {},
	onHourDecreased: () -> Unit = {},
	onDayIncreased: () -> Unit = {},
	onDayDecreased: () -> Unit = {},
	onMonthIncreased: () -> Unit = {},
	onMonthDecreased: () -> Unit = {},
	onYearIncreased: () -> Unit = {},
	onYearDecreased: () -> Unit = {},
) {
	Row(
		modifier = modifier
			.fillMaxWidth(),
	) {
		val space25percent = Modifier.weight(1f)
		val hour = bazi.hour
		if (hour != null) {
			Pillar(
				modifier = space25percent,
				title = stringResource(R.string.widget_bazi__pillar_title_hour),
				pillar = hour,
				onIncreased = onHourIncreased,
				onDecreased = onHourDecreased,
			)
		} else {
			Pillar(
				modifier = space25percent,
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
				onIncreased = null,
				onDecreased = null,
			)
		}
		Pillar(
			modifier = space25percent,
			title = stringResource(R.string.widget_bazi__pillar_title_day),
			pillar = bazi.day,
			onIncreased = onDayIncreased,
			onDecreased = onDayDecreased,
		)
		Pillar(
			modifier = space25percent,
			title = stringResource(R.string.widget_bazi__pillar_title_month),
			pillar = bazi.month,
			onIncreased = onMonthIncreased,
			onDecreased = onMonthDecreased,
		)
		Pillar(
			modifier = space25percent,
			title = stringResource(R.string.widget_bazi__pillar_title_year),
			pillar = bazi.year,
			onIncreased = onYearIncreased,
			onDecreased = onYearDecreased,
		)
	}
}

@Composable
private fun Pillar(
	modifier: Modifier = Modifier,
	title: String,
	top: @Composable () -> Unit,
	bottom: @Composable () -> Unit,
	onIncreased: (() -> Unit)?,
	onDecreased: (() -> Unit)?,
) {
	Column(
		modifier = modifier,
		horizontalAlignment = CenterHorizontally,
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(4.dp),
		) {
			onDecreased?.let { onDecreased ->
				SmallButton(
					icon = Icons.Filled.RemoveCircleOutline,
					cd = R.string.widget_bazi__pillar_title_minus,
					onClick = onDecreased,
				)
			}
			Text(
				text = title,
				style = MaterialTheme.typography.labelMedium,
			)
			onIncreased?.let { onIncreased ->
				SmallButton(
					icon = Icons.Filled.AddCircleOutline,
					cd = R.string.widget_bazi__pillar_title_plus,
					onClick = onIncreased,
				)
			}
		}
		top()
		bottom()
	}
}

@Composable
private fun SmallButton(
	icon: ImageVector,
	@StringRes cd: Int,
	onClick: () -> Unit,
) {
	@OptIn(ExperimentalMaterial3Api::class)
	CompositionLocalProvider(
		LocalMinimumInteractiveComponentEnforcement provides false
	) {
		IconButton(
			modifier = Modifier
				.size(16.dp),
			onClick = onClick,
		) {
			Icon(
				imageVector = icon,
				contentDescription = stringResource(cd)
			)
		}
	}
}

@Composable
private fun Pillar(
	modifier: Modifier = Modifier,
	title: String,
	pillar: BaZi.Pillar,
	onIncreased: () -> Unit,
	onDecreased: () -> Unit,
) {
	Pillar(
		modifier = modifier,
		title = title,
		onIncreased = onIncreased,
		onDecreased = onDecreased,
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
	)
}

@Composable
private fun Character(
	modifier: Modifier = Modifier,
	symbol: String,
	color: Color,
	label: String,
) {
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
			bazi = BaZi(
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
			bazi = BaZi(
				year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
				month = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Si),
				day = BaZi.Pillar(HeavenlyStem.Wu, EarthlyBranch.Shen),
				hour = null,
			)
		)
	}
}
