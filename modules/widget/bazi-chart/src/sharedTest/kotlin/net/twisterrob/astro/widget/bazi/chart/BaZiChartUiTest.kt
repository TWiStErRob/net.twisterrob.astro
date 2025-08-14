package net.twisterrob.astro.widget.bazi.chart

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsNotEnabled
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

	private val fixtFullBazi: BaZi
		get() = BaZi(
			year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi),
			month = BaZi.Pillar(HeavenlyStem.Yi, EarthlyBranch.Chou),
			day = BaZi.Pillar(HeavenlyStem.Bing, EarthlyBranch.Yin),
			hour = BaZi.Pillar(HeavenlyStem.Ding, EarthlyBranch.Mao),
		)

	private val fixtHourlessBazi: BaZi
		get() = BaZi(
			year = BaZi.Pillar(HeavenlyStem.Geng, EarthlyBranch.Chen),
			month = BaZi.Pillar(HeavenlyStem.Xin, EarthlyBranch.Si),
			day = BaZi.Pillar(HeavenlyStem.Ren, EarthlyBranch.Wu),
			hour = null,
		)

	@Test
	fun `full is rendered`() {
		compose.setContent(bazi = fixtFullBazi)

		compose
			.onRoot()
			.assertExists()
	}

	@Test
	fun `hourless is rendered`() {
		compose.setContent(bazi = fixtHourlessBazi)

		compose
			.onRoot()
			.assertExists()
	}

	@Test
	fun `year add is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Year", "add").performClick()

		verify(mockListeners.onYearAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `year subtract is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Year", "subtract").performClick()

		verify(mockListeners.onYearSubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `month add is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Month", "add").performClick()

		verify(mockListeners.onMonthAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `month subtract is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Month", "subtract").performClick()

		verify(mockListeners.onMonthSubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `day add is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Day", "add").performClick()

		verify(mockListeners.onDayAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `day subtract is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Day", "subtract").performClick()

		verify(mockListeners.onDaySubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `hour add is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Hour", "add").performClick()

		verify(mockListeners.onHourAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `hour subtract is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Hour", "subtract").performClick()

		verify(mockListeners.onHourSubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `minute add is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Minute", "add").performClick()

		verify(mockListeners.onMinuteAdd).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `minute subtract is clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onPillarAction("Minute", "subtract").performClick()

		verify(mockListeners.onMinuteSubtract).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `hourless hour add is not clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners, bazi = fixtHourlessBazi)

		compose.onPillarAction("Hour", "add").assertIsNotEnabled()

		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `hourless hour subtract is not clickable`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners, bazi = fixtHourlessBazi)

		compose.onPillarAction("Hour", "subtract").assertIsNotEnabled()

		mockListeners.verifyNoMoreInteractions()
	}


	@Test
	fun `hourless minute add is not visible`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners, bazi = fixtHourlessBazi)

		compose.onPillarAction("Minute", "add").assertDoesNotExist()

		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `hourless minute subtract is not visible`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners, bazi = fixtHourlessBazi)

		compose.onPillarAction("Minute", "subtract").assertDoesNotExist()

		mockListeners.verifyNoMoreInteractions()
	}

	private fun ComposeContentTestRule.setContent(
		listeners: TestListeners = TestListeners(),
		bazi: BaZi = fixtFullBazi,
	) {
		setContent {
			BaZiChart(
				bazi = bazi,
				onYearAdd = listeners.onYearAdd,
				onYearSubtract = listeners.onYearSubtract,
				onMonthAdd = listeners.onMonthAdd,
				onMonthSubtract = listeners.onMonthSubtract,
				onDayAdd = listeners.onDayAdd,
				onDaySubtract = listeners.onDaySubtract,
				onHourAdd = listeners.onHourAdd,
				onHourSubtract = listeners.onHourSubtract,
				onMinuteAdd = listeners.onMinuteAdd,
				onMinuteSubtract = listeners.onMinuteSubtract,
			)
		}
	}
}

@Suppress("detekt.LongParameterList")
private class TestListeners(
	val onYearAdd: () -> Unit = mock(),
	val onYearSubtract: () -> Unit = mock(),
	val onMonthAdd: () -> Unit = mock(),
	val onMonthSubtract: () -> Unit = mock(),
	val onDayAdd: () -> Unit = mock(),
	val onDaySubtract: () -> Unit = mock(),
	val onHourAdd: () -> Unit = mock(),
	val onHourSubtract: () -> Unit = mock(),
	val onMinuteAdd: () -> Unit = mock(),
	val onMinuteSubtract: () -> Unit = mock(),
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
			onMinuteAdd,
			onMinuteSubtract,
		)
	}
}

private fun ComposeContentTestRule.onPillarAction(title: String, contentDescription: String): SemanticsNodeInteraction =
	onAllNodesWithContentDescription(contentDescription)
		.filterToOne(hasAnySibling(hasText(title)))
