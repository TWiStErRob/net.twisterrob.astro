package net.twisterrob.astro.widget.bazi

import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

@RunWith(AndroidJUnit4::class)
class BaZiChartUiTest {

	@get:Rule
	val compose = createComposeRule()

	@Test
	fun `full is rendered`() {
		compose.setContent {
			BaZiChart(
				bazi = BaZi(
					year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
					month = BaZi.Pillar(HeavenlyStem.Yi, EarthlyBranch.Chou),
					day = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Yin),
					hour = BaZi.Pillar(HeavenlyStem.Ding, EarthlyBranch.Mao),
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
		compose
			.onRoot()
			.assertExists()
	}

	@Test
	fun `hourless is rendered`() {
		compose.setContent {
			BaZiChart(
				bazi = BaZi(
					year = BaZi.Pillar(HeavenlyStem.Geng, EarthlyBranch.Chen),
					month = BaZi.Pillar(HeavenlyStem.Xin, EarthlyBranch.Si),
					day = BaZi.Pillar(HeavenlyStem.Ren, EarthlyBranch.Wu),
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
		compose
			.onRoot()
			.assertExists()
	}

	@Test
	fun `year add is clickable`() {
		val mockListener: () -> Unit = mock()
		compose.setContent {
			BaZiChart(
				bazi = BaZi(
					year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
					month = BaZi.Pillar(HeavenlyStem.Yi, EarthlyBranch.Chou),
					day = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Yin),
					hour = BaZi.Pillar(HeavenlyStem.Ding, EarthlyBranch.Mao),
				),
				onYearAdd = mockListener,
				onYearSubtract = {},
				onMonthAdd = {},
				onMonthSubtract = {},
				onDayAdd = {},
				onDaySubtract = {},
				onHourAdd = {},
				onHourSubtract = {},
			)
		}
		compose.clickPillarAction("Year", "add")
		verify(mockListener).invoke()
		verifyNoMoreInteractions(mockListener)
	}
}

private fun ComposeContentTestRule.clickPillarAction(title: String, contentDescription: String) {
	onAllNodesWithContentDescription(contentDescription)
		.filterToOne(hasAnySibling(hasText(title)))
		.performClick()
}
