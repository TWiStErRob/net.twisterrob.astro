package net.twisterrob.astro.widget.bazi

import net.twisterrob.astro.screen.bazi.BaZiState
import net.twisterrob.astro.screen.bazi.BaZiViewModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime

class BaZiViewModelTest {

	private val currentTimeProvider = FixedTimeProvider(at = fakeCurrentTime())
	private val subject = BaZiViewModel(currentTimeProvider)

	@Test
	fun `test instants are very different`() {
		val instant1 = fakeCurrentTime()
		val instant2 = aSpecificInstant()

		assertNotEquals(instant1, instant2)
		assertNotEquals(instant1.zone, instant2.zone)
		assertNotEquals(instant1.year, instant2.year)
		assertNotEquals(instant1.monthValue, instant2.monthValue)
		assertNotEquals(instant1.dayOfMonth, instant2.dayOfMonth)
		assertNotEquals(instant1.hour, instant2.hour)
		assertNotEquals(instant1.minute, instant2.minute)
		assertNotEquals(instant1.second, instant2.second)
		assertNotEquals(instant1.nano / 1_000_000, instant2.nano / 1_000_000)
		assertNotEquals(instant1.nano, instant2.nano)

		val bazi1 = subject.bazi(instant1)
		val bazi2 = subject.bazi(instant2)
		assertNotEquals(bazi1, bazi2)
		assertNotEquals(bazi1.year.heavenlyStem, bazi2.year.heavenlyStem)
		assertNotEquals(bazi1.year.earthlyBranch, bazi2.year.earthlyBranch)
		assertNotEquals(bazi1.month.heavenlyStem, bazi2.month.heavenlyStem)
		assertNotEquals(bazi1.month.earthlyBranch, bazi2.month.earthlyBranch)
		assertNotEquals(bazi1.day.heavenlyStem, bazi2.day.heavenlyStem)
		assertNotEquals(bazi1.day.earthlyBranch, bazi2.day.earthlyBranch)
		assertNotEquals(bazi1.hour?.heavenlyStem, bazi2.hour?.heavenlyStem)
		assertNotEquals(bazi1.hour?.earthlyBranch, bazi2.hour?.earthlyBranch)
	}

	@Test
	fun `initial state`() {
		val current = subject.uiState.value

		assertDefaultValues(current)
		assertNotNull(current.bazi.hour)
	}

	@Test
	fun `increasing a year adds one year only`() {
		val current = subject.uiState.value

		subject.increaseYear()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.plusYears(1), updated.dateTime.dateTime)
		assertNotEquals(current.bazi, updated.bazi)
		assertDefaultValues(updated)
	}

	@Test
	fun `decreasing a year removes one year only`() {
		val current = subject.uiState.value

		subject.decreaseYear()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.minusYears(1), updated.dateTime.dateTime)
		assertNotEquals(current.bazi, updated.bazi)
		assertDefaultValues(updated)
	}


	@Test
	fun `increasing a month adds one month only`() {
		val current = subject.uiState.value

		subject.increaseMonth()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.plusMonths(1), updated.dateTime.dateTime)
		assertNotEquals(current.bazi, updated.bazi)
		assertDefaultValues(updated)
	}

	@Test
	fun `decreasing a month removes one month only`() {
		val current = subject.uiState.value

		subject.decreaseMonth()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.minusMonths(1), updated.dateTime.dateTime)
		assertNotEquals(current.bazi, updated.bazi)
		assertDefaultValues(updated)
	}

	@Test
	fun `increasing a day adds one day only`() {
		val current = subject.uiState.value

		subject.increaseDay()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.plusDays(1), updated.dateTime.dateTime)
		assertNotEquals(current.bazi, updated.bazi)
		assertDefaultValues(updated)
	}

	@Test
	fun `decreasing a day removes one day only`() {
		val current = subject.uiState.value

		subject.decreaseDay()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.minusDays(1), updated.dateTime.dateTime)
		assertNotEquals(current.bazi, updated.bazi)
		assertDefaultValues(updated)
	}

	@Test
	fun `increasing an hour adds one hour only`() {
		val current = subject.uiState.value

		subject.increaseHour()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.plusHours(1), updated.dateTime.dateTime)
		assertNotSame(current.bazi, updated.bazi) // Cannot predict if it will actually change.
		assertDefaultValues(updated)
	}

	@Test
	fun `decreasing an hour removes one hour only`() {
		val current = subject.uiState.value

		subject.decreaseHour()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.minusHours(1), updated.dateTime.dateTime)
		assertNotSame(current.bazi, updated.bazi) // Cannot predict if it will actually change.
		assertDefaultValues(updated)
	}

	@Test
	fun `increasing a minute adds one minute only`() {
		val current = subject.uiState.value

		subject.increaseMinute()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.plusMinutes(1), updated.dateTime.dateTime)
		assertNotSame(current.bazi, updated.bazi) // Cannot predict if it will actually change.
		assertDefaultValues(updated)
	}

	@Test
	fun `decreasing a minute removes one minute only`() {
		val current = subject.uiState.value

		subject.decreaseMinute()

		val updated = subject.uiState.value

		assertEquals(current.dateTime.dateTime.minusMinutes(1), updated.dateTime.dateTime)
		assertNotSame(current.bazi, updated.bazi) // Cannot predict if it will actually change.
		assertDefaultValues(updated)
	}

	@Nested
	inner class TimePicker {

		@Test
		fun `starting a time pick does not affect current state`() {
			val current = subject.uiState.value

			subject.pickTime()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `hiding a time picker does not affect current state`() {
			subject.pickTime()
			val current = subject.uiState.value

			subject.hideTimePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
			assertDefaultValues(updated)
		}

		@Test
		fun `hiding a time picker without showing does not affect current state`() {
			val current = subject.uiState.value

			subject.hideTimePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
			assertDefaultValues(updated)
		}

		@Test
		fun `hiding a time picker again does not affect current state`() {
			subject.pickTime()
			subject.hideTimePicker()
			val current = subject.uiState.value

			subject.hideTimePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `starting a time pick changes picking state`() {
			subject.pickTime()

			val updated = subject.uiState.value

			assertEquals(false, updated.dateTime.isPickingDate)
			assertEquals(true, updated.dateTime.isPickingTime)
			assertEquals(false, updated.dateTime.isPickingZone)
		}


		@Test
		fun `hiding a time picker changes picking state`() {
			subject.pickTime()
			subject.hideTimePicker()

			val updated = subject.uiState.value

			assertEquals(false, updated.dateTime.isPickingDate)
			assertEquals(false, updated.dateTime.isPickingTime)
			assertEquals(false, updated.dateTime.isPickingZone)
		}

		@Test
		fun `starting a time pick only changes picking state`() {
			subject.pickDate()
			subject.pickZone()

			subject.pickTime()

			val updated = subject.uiState.value

			assertEquals(true, updated.dateTime.isPickingDate)
			assertEquals(true, updated.dateTime.isPickingTime)
			assertEquals(true, updated.dateTime.isPickingZone)
		}

		@Test
		fun `hiding a time picker only changes picking state`() {
			subject.pickDate()
			subject.pickZone()
			subject.pickTime()

			subject.hideTimePicker()

			val updated = subject.uiState.value

			assertEquals(true, updated.dateTime.isPickingDate)
			assertEquals(false, updated.dateTime.isPickingTime)
			assertEquals(true, updated.dateTime.isPickingZone)
		}

		@Test
		fun `selecting a time changes time and finishes picking`() {
			val current = subject.uiState.value
			val newTime = aSpecificInstant().toLocalTime()

			subject.selectTime(newTime)

			val updated = subject.uiState.value

			val expected = current.dateTime.dateTime
				.withHour(1)
				.minusHours(1) // Hack
				.withMinute(23)
			assertEquals(expected, updated.dateTime.dateTime)
			assertNotEquals(current.bazi, updated.bazi)
			assertDefaultValues(updated)
		}

		@Test
		fun `resetting time to now`() {
			subject.select(aSpecificInstant())
			val current = subject.uiState.value

			val now = fakeCurrentTime()
			subject.resetToNow()

			val updated = subject.uiState.value

			val expected = current.dateTime.dateTime
				.withHour(now.hour)
				.minusHours(1) // Hack
				.withMinute(now.minute)
				.withSecond(0)
				.withNano(0)
			assertEquals(expected, updated.dateTime.dateTime)
			assertNotEquals(current.bazi, updated.bazi)
			assertDefaultValues(updated)
		}
	}

	@Nested
	inner class DatePicker {

		@Test
		fun `starting a date pick does not affect current state`() {
			val current = subject.uiState.value

			subject.pickDate()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `hiding a date picker does not affect current state`() {
			subject.pickDate()
			val current = subject.uiState.value

			subject.hideDatePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `hiding a date picker without showing does not affect current state`() {
			val current = subject.uiState.value

			subject.hideDatePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `hiding a date picker again does not affect current state`() {
			subject.pickDate()
			subject.hideDatePicker()
			val current = subject.uiState.value

			subject.hideDatePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `starting a date pick changes picking state`() {
			subject.pickDate()

			val updated = subject.uiState.value

			assertEquals(true, updated.dateTime.isPickingDate)
			assertEquals(false, updated.dateTime.isPickingTime)
			assertEquals(false, updated.dateTime.isPickingZone)
		}

		@Test
		fun `hiding a date picker changes picking state`() {
			subject.pickDate()
			subject.hideDatePicker()

			val updated = subject.uiState.value

			assertEquals(false, updated.dateTime.isPickingDate)
			assertEquals(false, updated.dateTime.isPickingTime)
			assertEquals(false, updated.dateTime.isPickingZone)
		}

		@Test
		fun `starting a date pick only changes picking state`() {
			subject.pickTime()
			subject.pickZone()

			subject.pickDate()

			val updated = subject.uiState.value

			assertEquals(true, updated.dateTime.isPickingDate)
			assertEquals(true, updated.dateTime.isPickingTime)
			assertEquals(true, updated.dateTime.isPickingZone)
		}

		@Test
		fun `hiding a date picker only changes picking state`() {
			subject.pickTime()
			subject.pickZone()
			subject.pickDate()

			subject.hideDatePicker()

			val updated = subject.uiState.value

			assertEquals(false, updated.dateTime.isPickingDate)
			assertEquals(true, updated.dateTime.isPickingTime)
			assertEquals(true, updated.dateTime.isPickingZone)
		}

		@Test
		fun `selecting a date changes date and finishes picking`() {
			val current = subject.uiState.value
			val newDate = aSpecificInstant().toLocalDate()

			subject.selectDate(newDate)

			val updated = subject.uiState.value

			val expected = current.dateTime.dateTime
				.withYear(2017)
				.withMonth(Month.OCTOBER.value)
				.withDayOfMonth(6)
			assertEquals(expected, updated.dateTime.dateTime)
			assertNotEquals(current.bazi, updated.bazi)
			assertDefaultValues(updated)
		}

		@Test
		fun `resetting date to today`() {
			subject.select(aSpecificInstant())
			val current = subject.uiState.value

			val now = fakeCurrentTime()
			subject.resetToToday()

			val updated = subject.uiState.value

			val expected = current.dateTime.dateTime
				.withYear(now.year)
				.withMonth(now.monthValue)
				.withDayOfMonth(now.dayOfMonth)
			assertEquals(expected, updated.dateTime.dateTime)
			assertNotEquals(current.bazi, updated.bazi)
			assertDefaultValues(updated)
		}
	}

	@Nested
	inner class ZonePicker {
		@Test
		fun `starting a zone pick does not affect current state`() {
			val current = subject.uiState.value

			subject.pickZone()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `hiding a zone picker does not affect current state`() {
			subject.pickZone()
			val current = subject.uiState.value

			subject.hideZonePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `hiding a zone picker without showing does not affect current state`() {
			val current = subject.uiState.value

			subject.hideZonePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `hiding a zone picker again does not affect current state`() {
			subject.pickZone()
			subject.hideZonePicker()
			val current = subject.uiState.value

			subject.hideZonePicker()

			val updated = subject.uiState.value

			assertEquals(current.dateTime.dateTime, updated.dateTime.dateTime)
			assertEquals(current.bazi, updated.bazi)
		}

		@Test
		fun `starting a zone pick changes picking state`() {
			subject.pickZone()

			val updated = subject.uiState.value

			assertEquals(false, updated.dateTime.isPickingDate)
			assertEquals(false, updated.dateTime.isPickingTime)
			assertEquals(true, updated.dateTime.isPickingZone)
		}

		@Test
		fun `hiding a zone picker changes picking state`() {
			subject.pickZone()
			subject.hideZonePicker()

			val updated = subject.uiState.value

			assertEquals(false, updated.dateTime.isPickingDate)
			assertEquals(false, updated.dateTime.isPickingTime)
			assertEquals(false, updated.dateTime.isPickingZone)
		}

		@Test
		fun `starting a zone pick only changes picking state`() {
			subject.pickTime()
			subject.pickDate()

			subject.pickZone()

			val updated = subject.uiState.value

			assertEquals(true, updated.dateTime.isPickingDate)
			assertEquals(true, updated.dateTime.isPickingTime)
			assertEquals(true, updated.dateTime.isPickingZone)
		}

		@Test
		fun `hiding a zone picker only changes picking state`() {
			subject.pickTime()
			subject.pickDate()
			subject.pickZone()

			subject.hideZonePicker()

			val updated = subject.uiState.value

			assertEquals(true, updated.dateTime.isPickingDate)
			assertEquals(true, updated.dateTime.isPickingTime)
			assertEquals(false, updated.dateTime.isPickingZone)
		}

		@Test
		fun `selecting a zone changes zone and finishes picking`() {
			val current = subject.uiState.value
			val newZone = aSpecificInstant().zone

			subject.selectZone(newZone)

			val updated = subject.uiState.value

			val expected = current.dateTime.dateTime
				.withZoneSameLocal(newZone)
			assertEquals(expected, updated.dateTime.dateTime)
			assertNotSame(current.bazi, updated.bazi) // Cannot predict if it will actually change.
			assertDefaultValues(updated)
		}
	}

	@Test
	fun `selecting arbitrary date and time`() {
		val current = subject.uiState.value
		val newInstant = aSpecificInstant()

		subject.select(newInstant)

		val updated = subject.uiState.value

		val expected = newInstant
			.withSecond(0)
			.withNano(0)
		assertEquals(expected, updated.dateTime.dateTime)
		assertNotEquals(current.bazi, updated.bazi)
		assertDefaultValues(updated)
	}

	companion object {
		private fun fakeCurrentTime(): ZonedDateTime = ZonedDateTime.of(
			LocalDate.of(2013, Month.JUNE, 27),
			LocalTime.of(12, 34, 56, 7_008_009),
			ZoneId.of("Asia/Kolkata"),
		)

		private fun aSpecificInstant(): ZonedDateTime = ZonedDateTime.of(
			LocalDate.of(2017, Month.OCTOBER, 6),
			LocalTime.of(1, 23, 45, 1_234_567),
			ZoneId.of("US/Pacific")
		)

		private fun assertDefaultValues(updated: BaZiState) {
			assertEquals(0, updated.dateTime.dateTime.second)
			assertEquals(0, updated.dateTime.dateTime.nano)
			assertEquals(false, updated.dateTime.isPickingDate)
			assertEquals(false, updated.dateTime.isPickingTime)
			assertEquals(false, updated.dateTime.isPickingZone)
		}
	}
}
