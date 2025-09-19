package net.twisterrob.astro.widget.bazi.chart

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import net.twisterrob.astro.bazi.model.Phase
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.widget.wuxing.cycle.WuXingCycle

public class WuXingCycleScreenshots {

	@Preview
	@PreviewTest
	@Composable
	private fun EarthCycle() {
		AppTheme {
			WuXingCycle(
				phase = Phase.Tu,
				onSelect = {},
				onDeselect = {},
			)
		}
	}
}
