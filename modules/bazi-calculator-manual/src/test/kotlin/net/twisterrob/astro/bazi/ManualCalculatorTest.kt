package net.twisterrob.astro.bazi

import org.junit.jupiter.api.Test

class ManualCalculatorTest : BaZiCalculatorTest() {

	override val subject: BaZiCalculator = ManualCalculator()

	@Test fun test() {
	}
}
