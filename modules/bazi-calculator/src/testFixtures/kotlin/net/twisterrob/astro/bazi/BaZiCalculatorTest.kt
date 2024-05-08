package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.test.data.BaZiHourlessTestCase
import net.twisterrob.astro.bazi.test.data.BaZiTestCase
import net.twisterrob.astro.bazi.test.data.SexagenaryDayTestCase
import net.twisterrob.astro.bazi.test.data.SexagenaryHourTestCase
import net.twisterrob.astro.bazi.test.data.SexagenaryYearTestCase
import net.twisterrob.astro.bazi.test.data.SolarTermTestCase
import net.twisterrob.astro.bazi.test.test
import net.twisterrob.astro.bazi.test.testDay
import net.twisterrob.astro.bazi.test.testHour
import net.twisterrob.astro.bazi.test.testSolarTerm
import net.twisterrob.astro.bazi.test.testYear
import org.junit.jupiter.api.DynamicContainer.dynamicContainer
import org.junit.jupiter.api.DynamicNode
import org.junit.jupiter.api.TestFactory

/**
 * Base test class to validate a [BaZiCalculator] implementation.
 */
@Suppress(
	"unused",
	"detekt.MagicNumber",
	"detekt.StringLiteralDuplication", // TODEL https://github.com/detekt/detekt/issues/7271
	"detekt.UndocumentedPublicFunction", // Test methods only, they're fine without.
	"detekt.UnnecessaryAbstractClass", // Needs to be abstract so JUnit doesn't run it.
)
abstract class BaZiCalculatorTest {

	/**
	 * Store an instance of the test subject.
	 * Use `get()` to create it for every test.
	 */
	abstract val subject: BaZiCalculator

	@TestFactory fun `random tests`(): Iterable<DynamicNode> =
		BaZiTestCase.ALL_CASES.map { subject.test(it.dateTime, it.bazi) }

	@TestFactory fun `random hourless tests`(): Iterable<DynamicNode> =
		BaZiHourlessTestCase.ALL_CASES.map { subject.test(it.date, it.bazi) }

	@TestFactory fun `sexagenary years`(): Iterable<DynamicNode> =
		SexagenaryYearTestCase.ALL_KNOWN_CYCLES.map { cycle ->
			val start = cycle.first().year
			val end = cycle.last().year
			dynamicContainer("cycle ${start}—${end}", cycle.map(subject::testYear))
		}

	@TestFactory fun `solar terms`(): Iterable<DynamicNode> =
		SolarTermTestCase.ALL_KNOWN_YEARS.map { cycle ->
			val year = cycle.first().startTime.year
			dynamicContainer("year ${year}", cycle.map(subject::testSolarTerm))
		}

	@TestFactory fun `sexagenary days`(): Iterable<DynamicNode> =
		SexagenaryDayTestCase.ALL_KNOWN_CYCLES.map { cycle ->
			val start = cycle.first().date
			val end = cycle.last().date
			dynamicContainer("cycle ${start}—${end}", cycle.map { subject.testDay(null, it) })
		}

	@TestFactory fun `special days`(): Iterable<DynamicNode> =
		SexagenaryDayTestCase.ALL_CASES.map {
			subject.testDay(it.key, it.value)
		}

	@TestFactory fun `sexagenary hours`(): Iterable<DynamicNode> =
		SexagenaryHourTestCase.ALL_KNOWN_CYCLES.map { cycle ->
			val start = cycle.first().startTime
			val end = cycle.last().endTime
			dynamicContainer("cycle ${start}—${end}", cycle.map(subject::testHour))
		}
}
