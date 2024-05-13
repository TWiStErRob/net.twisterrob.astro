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
		val mockListeners = setContentWithListeners(MockListeners())

		compose.clickPillarAction("Year", "add")

		verify(mockListeners.onYearAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `year subtract is clickable`() {
		val mockListeners = setContentWithListeners(MockListeners())

		compose.clickPillarAction("Year", "subtract")

		verify(mockListeners.onYearSubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `month add is clickable`() {
		val mockListeners = setContentWithListeners(MockListeners())

		compose.clickPillarAction("Month", "add")

		verify(mockListeners.onMonthAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `month subtract is clickable`() {
		val mockListeners = setContentWithListeners(MockListeners())

		compose.clickPillarAction("Month", "subtract")

		verify(mockListeners.onMonthSubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `day add is clickable`() {
		val mockListeners = setContentWithListeners(MockListeners())

		compose.clickPillarAction("Day", "add")

		verify(mockListeners.onDayAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `day subtract is clickable`() {
		val mockListeners = setContentWithListeners(MockListeners())

		compose.clickPillarAction("Day", "subtract")

		verify(mockListeners.onDaySubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `hour add is clickable`() {
		val mockListeners = setContentWithListeners(MockListeners())

		compose.clickPillarAction("Hour", "add")

		verify(mockListeners.onHourAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `hour subtract is clickable`() {
		val mockListeners = setContentWithListeners(MockListeners())

		compose.clickPillarAction("Hour", "subtract")

		verify(mockListeners.onHourSubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	private fun setContentWithListeners(mockListeners: MockListeners): MockListeners {
		compose.setContent {
			BaZiChart(
				bazi = BaZi(
					year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
					month = BaZi.Pillar(HeavenlyStem.Yi, EarthlyBranch.Chou),
					day = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Yin),
					hour = BaZi.Pillar(HeavenlyStem.Ding, EarthlyBranch.Mao),
				),
				onYearAdd = mockListeners.onYearAdd,
				onYearSubtract = mockListeners.onYearSubtract,
				onMonthAdd = mockListeners.onMonthAdd,
				onMonthSubtract = mockListeners.onMonthSubtract,
				onDayAdd = mockListeners.onDayAdd,
				onDaySubtract = mockListeners.onDaySubtract,
				onHourAdd = mockListeners.onHourAdd,
				onHourSubtract = mockListeners.onHourSubtract,
			)
		}
		return mockListeners
	}

	@Suppress("detekt.LongParameterList")
	private class MockListeners(
		val onYearAdd: () -> Unit = mock(),
		val onYearSubtract: () -> Unit = mock(),
		val onMonthAdd: () -> Unit = mock(),
		val onMonthSubtract: () -> Unit = mock(),
		val onDayAdd: () -> Unit = mock(),
		val onDaySubtract: () -> Unit = mock(),
		val onHourAdd: () -> Unit = mock(),
		val onHourSubtract: () -> Unit = mock(),
	) {
		fun verifyNoMoreInteractions() {
			verifyNoMoreInteractions(
				onYearAdd,
				onYearSubtract,
				onMonthAdd,
				onMonthSubtract,
				onDayAdd,
				onDaySubtract,
				onHourAdd,
				onHourSubtract,
			)
		}
	}
}

private fun ComposeContentTestRule.clickPillarAction(title: String, contentDescription: String) {
	onAllNodesWithContentDescription(contentDescription)
		.filterToOne(hasAnySibling(hasText(title)))
		.performClick()
}
