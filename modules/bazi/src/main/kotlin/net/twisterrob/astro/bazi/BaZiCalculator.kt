package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.model.BaZi
import java.time.LocalDateTime

public interface BaZiCalculator {

	public fun calculate(dateTime: LocalDateTime): BaZi
}
