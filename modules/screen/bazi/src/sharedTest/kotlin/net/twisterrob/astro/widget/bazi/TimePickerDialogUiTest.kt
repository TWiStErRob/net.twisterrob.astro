package net.twisterrob.astro.widget.bazi

import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.twisterrob.astro.screen.bazi.TimePickerDialog
import net.twisterrob.astro.test.compose.pressBack
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.robolectric.annotation.Config
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@RunWith(AndroidJUnit4::class)
@Config(qualifiers = "long") // TimePickerDialog seems to be too tall for default Robolectric screen size.
class TimePickerDialogUiTest {

	@get:Rule
	val compose = createComposeRule()

	@Test
	fun `back button hides the picker`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.pressBack()

		verify(mockListeners.onHideTimePicker).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `cancel hides the picker`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onNode(hasAnyAncestor(isDialog()) and hasText("Cancel")).performClick()

		verify(mockListeners.onHideTimePicker).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `ok selects the time given`() {
		val mockListeners = TestListeners()
		val state = ZonedDateTime.now()
		compose.setContent(state = state, listeners = mockListeners)

		compose.onNodeWithText("OK").performClick()

		val timeHourAndMinute = state.toLocalTime().truncatedTo(ChronoUnit.MINUTES)
		verify(mockListeners.onSelectTime).invoke(timeHourAndMinute)
		mockListeners.verifyNoMoreInteractions()
	}

	@Test
	fun `now selects the current time`() {
		val mockListeners = TestListeners()
		compose.setContent(listeners = mockListeners)

		compose.onNodeWithText("Now").performClick()

		verify(mockListeners.onResetToNow).invoke()
		mockListeners.verifyNoMoreInteractions()
	}

	private fun ComposeContentTestRule.setContent(
		listeners: TestListeners = TestListeners(),
		state: ZonedDateTime = ZonedDateTime.now(),
	) {
		setContent {
			TimePickerDialog(
				state = state,
				onSelectTime = listeners.onSelectTime,
				onHideTimePicker = listeners.onHideTimePicker,
				onResetToNow = listeners.onResetToNow,
			)
		}
	}
}

private class TestListeners(
	val onSelectTime: (LocalTime) -> Unit = mock(),
	val onHideTimePicker: () -> Unit = mock(),
	val onResetToNow: () -> Unit = mock(),
) {
	fun verifyNoMoreInteractions() {
		verifyNoMoreInteractions(
			onSelectTime,
			onHideTimePicker,
			onResetToNow,
		)
	}
}
