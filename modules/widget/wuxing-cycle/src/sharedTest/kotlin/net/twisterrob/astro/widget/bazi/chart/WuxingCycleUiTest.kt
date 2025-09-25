package net.twisterrob.astro.widget.bazi.chart

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.twisterrob.astro.bazi.model.Phase
import net.twisterrob.astro.component.data.symbol
import net.twisterrob.astro.widget.wuxing.cycle.WuxingCycle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class WuxingCycleUiTest {

	@get:Rule
	val compose = createComposeRule()

	@Test
	fun `a cycle is rendered`() {
		val mockListeners = TestListeners()
		compose.setContent(phase = Phase.entries.random())

		compose
			.onRoot()
			.assertExists()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `phase selected and deselected`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners, phase = Phase.Mu)

		compose.onPhaseAction(Phase.Mu).performClick()
		verify(mockListeners.onSelect).invoke(Phase.Mu)
		mockListeners.verifyNoMoreInteractions()

		compose.onPhaseAction(Phase.Mu).performClick()
		verify(mockListeners.onDeselect).invoke(Phase.Mu)
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `multiple selected and deselected`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners, phase = Phase.Mu)

		compose.onPhaseAction(Phase.Mu).performClick()
		compose.onPhaseAction(Phase.Shui).performClick()
		compose.onPhaseAction(Phase.Mu).performClick()
		compose.onPhaseAction(Phase.Jin).performClick()
		compose.onPhaseAction(Phase.Jin).performClick()
		inOrder(mockListeners.onSelect, mockListeners.onDeselect) {
			verify(mockListeners.onSelect).invoke(Phase.Mu)
			verify(mockListeners.onSelect).invoke(Phase.Shui)
			verify(mockListeners.onDeselect).invoke(Phase.Mu)
			verify(mockListeners.onSelect).invoke(Phase.Jin)
			verify(mockListeners.onDeselect).invoke(Phase.Jin)
			verifyNoMoreInteractions()
		}
	}

	private fun ComposeContentTestRule.setContent(
		listeners: TestListeners = TestListeners(),
		phase: Phase,
	) {
		setContent {
			WuxingCycle(
				phase = phase,
				onSelect = listeners.onSelect,
				onDeselect = listeners.onDeselect,
			)
		}
	}
}

private class TestListeners(
	val onSelect: (Phase) -> Unit = mock(),
	val onDeselect: (Phase) -> Unit = mock(),
) {
	fun verifyNoMoreInteractions() {
		verifyNoMoreInteractions(
			onSelect,
			onDeselect,
		)
	}
}

private fun ComposeContentTestRule.onPhaseAction(phase: Phase): SemanticsNodeInteraction =
	onNodeWithText(phase.symbol)
