package net.twisterrob.astro.bazi

import org.junit.jupiter.api.Disabled

@Disabled("Partial / inconsistent implementation, months are very imprecisely approximated.")
class SexagenaryWikiCalculatorUnitTest : BaZiCalculatorBaseUnitTest() {

	override val subject = SexagenaryWikiCalculator()
}
