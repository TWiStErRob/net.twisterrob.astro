@file:Suppress("detekt.TooManyFunctions")

package net.twisterrob.astro.widget.bazi.chart

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.component.data.color
import net.twisterrob.astro.component.data.label
import net.twisterrob.astro.component.data.symbol
import net.twisterrob.astro.component.theme.AppTheme

/**
 * Widget to render a basic BaZi chart.
 */
@Suppress("detekt.LongMethod")
@Composable
public fun BaZiChart(
	bazi: BaZi,
	onYearAdd: () -> Unit,
	onYearSubtract: () -> Unit,
	onMonthAdd: () -> Unit,
	onMonthSubtract: () -> Unit,
	onDayAdd: () -> Unit,
	onDaySubtract: () -> Unit,
	onHourAdd: () -> Unit,
	onHourSubtract: () -> Unit,
	onMinuteAdd: () -> Unit,
	onMinuteSubtract: () -> Unit,
	modifier: Modifier = Modifier,
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
				pillar = hour,
				header = {
					Row {
						Adjuster(
							title = stringResource(R.string.widget_bazi_chart__pillar_title_hour),
							onAdd = onHourAdd,
							onSubtract = onHourSubtract,
						)
						Text(" ")
						Adjuster(
							title = stringResource(R.string.widget_bazi_chart__pillar_title_minute),
							onAdd = onMinuteAdd,
							onSubtract = onMinuteSubtract,
						)
					}
				},
			)
		} else {
			Pillar(
				modifier = space25percent,
				top = { MissingHourPillarStem() },
				bottom = { MissingHourPillarBranch() },
				header = {
					Adjuster(
						title = stringResource(R.string.widget_bazi_chart__pillar_title_hour),
						onAdd = null,
						onSubtract = null,
					)
				},
			)
		}
		Pillar(
			modifier = space25percent,
			pillar = bazi.day,
			header = {
				Adjuster(
					title = stringResource(R.string.widget_bazi_chart__pillar_title_day),
					onAdd = onDayAdd,
					onSubtract = onDaySubtract,
				)
			},
		)
		Pillar(
			modifier = space25percent,
			pillar = bazi.month,
			header = {
				Adjuster(
					title = stringResource(R.string.widget_bazi_chart__pillar_title_month),
					onAdd = onMonthAdd,
					onSubtract = onMonthSubtract,
				)
			},
		)
		Pillar(
			modifier = space25percent,
			pillar = bazi.year,
			header = {
				Adjuster(
					title = stringResource(R.string.widget_bazi_chart__pillar_title_year),
					onAdd = onYearAdd,
					onSubtract = onYearSubtract,
				)
			},
		)
	}
}

@Composable
private fun Pillar(
	modifier: Modifier = Modifier,
	// TODEL These shouldn't have default parameters, workaround for https://github.com/mrmans0n/compose-rules/issues/333
	header: @Composable () -> Unit = {},
	top: @Composable () -> Unit = {},
	bottom: @Composable () -> Unit = {},
) {
	Column(
		modifier = modifier
			.semantics { isTraversalGroup = true },
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		header()
		top()
		bottom()
	}
}

@Composable
private fun Adjuster(
	title: String,
	onAdd: (() -> Unit)?,
	onSubtract: (() -> Unit)?,
) {
	Column(
		modifier = Modifier
			.semantics { isTraversalGroup = true },
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(4.dp),
	) {
		SmallButton(
			icon = Icons.Filled.AddCircleOutline,
			cd = R.string.widget_bazi_chart__pillar_title_plus,
			onClick = onAdd ?: {},
			enabled = onAdd != null,
		)
		Text(
			text = title,
			style = MaterialTheme.typography.labelMedium,
		)
		SmallButton(
			icon = Icons.Filled.RemoveCircleOutline,
			cd = R.string.widget_bazi_chart__pillar_title_minus,
			onClick = onSubtract ?: {},
			enabled = onAdd != null,
		)
	}
}

@Composable
private fun SmallButton(
	icon: ImageVector,
	@StringRes cd: Int,
	onClick: () -> Unit,
	enabled: Boolean = true,
) {
	CompositionLocalProvider(
		LocalMinimumInteractiveComponentSize provides Dp.Unspecified
	) {
		val interactionSource = remember { MutableInteractionSource() }
		IconButton(
			modifier = Modifier
				.repeatingClickable(interactionSource, true, onClick = onClick)
				.size(32.dp),
			interactionSource = interactionSource,
			onClick = {},
			enabled = enabled,
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
	pillar: BaZi.Pillar,
	modifier: Modifier = Modifier,
	@SuppressLint("ComposableLambdaParameterNaming")
	header: @Composable () -> Unit,
) {
	Pillar(
		modifier = modifier,
		header = header,
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
	symbol: String,
	color: Color,
	label: String,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally,
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
private fun MissingHourPillarStem() {
	DisabledContentText {
		Character(
			symbol = stringResource(R.string.widget_bazi_chart__pillar_unknown_hour_stem_symbol),
			color = LocalContentColor.current,
			label = stringResource(R.string.widget_bazi_chart__pillar_unknown_hour_stem_label)
		)
	}
}

@Composable
private fun MissingHourPillarBranch() {
	DisabledContentText {
		Character(
			symbol = stringResource(R.string.widget_bazi_chart__pillar_unknown_hour_branch_symbol),
			color = LocalContentColor.current,
			label = "${stringResource(R.string.widget_bazi_chart__pillar_unknown_hour_branch_label)}\n"
		)
	}
}

@Composable
private fun DisabledContentText(content: @Composable () -> Unit) {
	CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)) {
		content()
	}
}

private val HeavenlyStem.label: String
	@Composable
	@ReadOnlyComposable
	get() = "${polarity.label} ${phase.label}"

private val EarthlyBranch.label: String
	@Composable
	@ReadOnlyComposable
	get() = "${zodiac.label}\n${zodiac.charge.label}"

@Preview
@Composable
private fun BaZiChartFullPreview() {
	AppTheme {
		BaZiChart(
			bazi = BaZi(
				year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
				month = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Si),
				day = BaZi.Pillar(HeavenlyStem.Wu, EarthlyBranch.Shen),
				hour = BaZi.Pillar(HeavenlyStem.Xin, EarthlyBranch.Mao),
			),
			onYearAdd = {},
			onYearSubtract = {},
			onMonthAdd = {},
			onMonthSubtract = {},
			onDayAdd = {},
			onDaySubtract = {},
			onHourAdd = {},
			onHourSubtract = {},
			onMinuteAdd = {},
			onMinuteSubtract = {},
		)
	}
}

@Preview
@Composable
private fun BaZiChartHourlessPreview() {
	AppTheme {
		BaZiChart(
			bazi = BaZi(
				year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
				month = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Si),
				day = BaZi.Pillar(HeavenlyStem.Wu, EarthlyBranch.Shen),
				hour = null,
			),
			onYearAdd = {},
			onYearSubtract = {},
			onMonthAdd = {},
			onMonthSubtract = {},
			onDayAdd = {},
			onDaySubtract = {},
			onHourAdd = {},
			onHourSubtract = {},
			onMinuteAdd = {},
			onMinuteSubtract = {},
		)
	}
}
