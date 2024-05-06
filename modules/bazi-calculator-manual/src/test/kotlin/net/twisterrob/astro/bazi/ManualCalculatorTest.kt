package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.model.BaZi
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.TestFactory
import java.time.LocalDateTime

class ManualCalculatorTest : BaZiCalculatorTest() {

	private val realSubject = ManualCalculator()
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

	@TestFactory fun `sexagenary years`(): Iterable<DynamicNode> =
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.map { subject.verifyCycle(it) }

	@TestFactory fun `solar term branches of non-leap year`(): Iterable<DynamicNode> =
		listOf(2018, 2022).map { year -> subject.verifySolarTermBranchesIn(year) }

	@TestFactory fun `solar term stems of non-leap years`(): Iterable<DynamicNode> =
		(1900..2100).map { year -> subject.verifySolarTermStemsIn(year) }

	@TestFactory fun `sexagenary days`(): Iterable<DynamicNode> =
		SexagenaryDayTestCase.ALL_KNOWN_CYCLES.map { subject.verifyCycle(it) }

	@TestFactory fun `special days`(): Iterable<DynamicNode> =
		SexagenaryDayTestCase.WIKIPEDIA_EXAMPLES.map { subject.verifyDay(it) }

	@TestFactory fun `sexagenary hours`(): Iterable<DynamicNode> =
		SexagenaryHourTestCase.ALL_KNOWN_CYCLES.map { subject.verifyCycle(it) }
}
