package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.model.BaZi
import org.junit.jupiter.api.Assumptions
import java.time.LocalDateTime

class SolarCalculatorUnitTest : BaZiCalculatorTest() {

	private val realSubject = SolarCalculator()
	override val subject: BaZiCalculator = object : BaZiCalculator {
		override fun calculate(dateTime: LocalDateTime): BaZi {
			try {
				return realSubject.calculate(dateTime)
			} catch (e: Exception) {
				if (e.message?.contains("is before the Gregorian Calendar begins at") == true) {
					Assumptions.assumeTrue(false, e.message)
					error("Unreachable")
				} else {
					throw e
				}
			}
		}
	}
}
