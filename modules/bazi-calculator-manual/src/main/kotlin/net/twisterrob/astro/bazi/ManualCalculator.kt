package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.lookup.sexagenaryCycle
import net.twisterrob.astro.bazi.model.BaZi
import java.time.LocalDateTime

@Suppress("UNUSED_PARAMETER")
public class ManualCalculator : BaZiCalculator {

	override fun calculate(dateTime: LocalDateTime): BaZi {
		return BaZi(
			year = calculateYear(dateTime),
			month = calculateMonth(dateTime),
			day = calculateDay(dateTime),
			hour = calculateHour(dateTime),
		)
	}

	private fun calculateYear(dateTime: LocalDateTime): BaZi.Pillar {
		return BaZi.Pillar.sexagenaryCycle(0)
	}

	private fun calculateMonth(dateTime: LocalDateTime): BaZi.Pillar {
		return BaZi.Pillar.sexagenaryCycle(0)
	}

	private fun calculateDay(dateTime: LocalDateTime): BaZi.Pillar {
		return BaZi.Pillar.sexagenaryCycle(0)
	}

	private fun calculateHour(dateTime: LocalDateTime): BaZi.Pillar {
		return BaZi.Pillar.sexagenaryCycle(0)
	}
}
