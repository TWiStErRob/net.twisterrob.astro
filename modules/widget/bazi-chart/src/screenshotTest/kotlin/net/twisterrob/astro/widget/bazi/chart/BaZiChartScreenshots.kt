//package net.twisterrob.astro.widget.bazi.chart

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.widget.bazi.chart.BaZiChart

class BaZiChartScreenshots {

	@Preview
	@Composable
	private fun Full() {
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
			)
		}
	}

	@Preview
	@Composable
	private fun Hourless() {
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
			)
		}
	}
}
