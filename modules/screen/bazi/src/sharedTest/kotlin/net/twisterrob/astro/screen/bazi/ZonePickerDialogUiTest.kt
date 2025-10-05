package net.twisterrob.astro.screen.bazi

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.twisterrob.astro.test.compose.pressBack
import net.twisterrob.astro.test.compose.string
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import java.time.ZoneId
import java.time.ZonedDateTime

@RunWith(AndroidJUnit4::class)
class ZonePickerDialogUiTest {

	@get:Rule
	val compose = createComposeRule()

	@Test
	@Ignore("https://issuetracker.google.com/issues/371512565")
	fun `back button hides the picker`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.pressBack()

		verify(mockListeners.onHideZonePicker).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `cancel hides the picker`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onNodeWithText(string(R.string.screen_bazi__zone_picker_dialog_cancel)).performClick()

		verify(mockListeners.onHideZonePicker).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `ok selects the zone given`() {
		val mockListeners = TestListeners()
		val state = ZonedDateTime.now()
		compose.setContent(state = state, listeners = mockListeners)

		compose.onNodeWithText(string(R.string.screen_bazi__zone_picker_dialog_ok)).performClick()

		verify(mockListeners.onSelectZone).invoke(state.zone)
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `now selects the current zone`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onNodeWithText(string(R.string.screen_bazi__zone_picker_dialog_current)).performClick()

		verify(mockListeners.onResetToZone).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	private fun ComposeContentTestRule.setContent(
		listeners: TestListeners = TestListeners(),
		state: ZonedDateTime = ZonedDateTime.now(),
	) {
		setContent {
			ZonePickerDialog(
				state = state,
				onSelectZone = listeners.onSelectZone,
				onHideZonePicker = listeners.onHideZonePicker,
				onResetToZone = listeners.onResetToZone,
			)
		}
		listeners.verifyNoMoreInteractions()
	}

	private class TestListeners(
		val onSelectZone: (ZoneId) -> Unit = mock(),
		val onHideZonePicker: () -> Unit = mock(),
		val onResetToZone: () -> Unit = mock(),
	) {
		fun verifyNoMoreInteractions() {
			verifyNoMoreInteractions(
				onSelectZone,
				onHideZonePicker,
				onResetToZone,
			)
		}
	}
}
