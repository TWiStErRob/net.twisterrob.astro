package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.model.BaZi
import java.time.LocalDate
import java.time.LocalDateTime

public interface BaZiCalculator {

	public fun calculate(dateTime: LocalDateTime): BaZi

	public fun calculate(date: LocalDate): BaZi =
		calculate(date.atTime(12, 0, 0)).copy(hour = null)
}
