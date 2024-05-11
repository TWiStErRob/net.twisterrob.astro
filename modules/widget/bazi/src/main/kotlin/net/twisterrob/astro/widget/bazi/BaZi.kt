package net.twisterrob.astro.widget.bazi

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem

/**
 * Widget to render a basic BaZi chart.
 */
@Composable
public fun BaZi(bazi: BaZi, modifier: Modifier = Modifier) {
	Text(
		modifier = modifier,
		text = bazi.toString(),
	)
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
