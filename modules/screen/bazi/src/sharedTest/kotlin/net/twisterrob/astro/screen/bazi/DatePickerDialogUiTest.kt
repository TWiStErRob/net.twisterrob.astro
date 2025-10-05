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
import org.robolectric.annotation.Config
import java.time.LocalDate
import java.time.ZonedDateTime

@RunWith(AndroidJUnit4::class)
@Config(qualifiers = "long") // DatePickerDialog seems to be too tall for default Robolectric screen size.
class DatePickerDialogUiTest {

	@get:Rule
	val compose = createComposeRule()

	@Test
	@Ignore("https://issuetracker.google.com/issues/371512565")
	fun `back button hides the picker`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.pressBack()

		verify(mockListeners.onHideDatePicker).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `cancel hides the picker`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onNodeWithText(string(R.string.screen_bazi__date_picker_dialog_cancel)).performClick()

		verify(mockListeners.onHideDatePicker).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `ok selects the date given`() {
		val mockListeners = TestListeners()
		val state = ZonedDateTime.now()
		compose.setContent(state = state, listeners = mockListeners)

		compose.onNodeWithText(string(R.string.screen_bazi__date_picker_dialog_ok)).performClick()

		verify(mockListeners.onSelectDate).invoke(state.toLocalDate())
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `now selects the current date`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onNodeWithText(string(R.string.screen_bazi__date_picker_dialog_current)).performClick()

		verify(mockListeners.onResetToToday).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	private fun ComposeContentTestRule.setContent(
		listeners: TestListeners = TestListeners(),
		state: ZonedDateTime = ZonedDateTime.now(),
	) {
		setContent {
			DatePickerDialog(
				state = state,
				onSelectDate = listeners.onSelectDate,
				onHideDatePicker = listeners.onHideDatePicker,
				onResetToToday = listeners.onResetToToday,
			)
		}
		listeners.verifyNoMoreInteractions()
	}

	private class TestListeners(
		val onSelectDate: (LocalDate) -> Unit = mock(),
		val onHideDatePicker: () -> Unit = mock(),
		val onResetToToday: () -> Unit = mock(),
	) {
		fun verifyNoMoreInteractions() {
			verifyNoMoreInteractions(
				onSelectDate,
				onHideDatePicker,
				onResetToToday,
			)
		}
	}
}
