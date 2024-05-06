package net.twisterrob.astro.bazi

import io.kotest.assertions.withClue
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch.Chen
import net.twisterrob.astro.bazi.model.EarthlyBranch.Chou
import net.twisterrob.astro.bazi.model.EarthlyBranch.Hai
import net.twisterrob.astro.bazi.model.EarthlyBranch.Mao
import net.twisterrob.astro.bazi.model.EarthlyBranch.Shen
import net.twisterrob.astro.bazi.model.EarthlyBranch.Si
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wei
import net.twisterrob.astro.bazi.model.EarthlyBranch.Yin
import net.twisterrob.astro.bazi.model.EarthlyBranch.You
import net.twisterrob.astro.bazi.model.EarthlyBranch.Zi
import net.twisterrob.astro.bazi.model.HeavenlyStem.Bing
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ding
import net.twisterrob.astro.bazi.model.HeavenlyStem.Geng
import net.twisterrob.astro.bazi.model.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ji
import net.twisterrob.astro.bazi.model.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu
import net.twisterrob.astro.bazi.model.HeavenlyStem.Xin
import net.twisterrob.astro.bazi.model.HeavenlyStem.Yi
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

/**
 * Base test class to validate a [BaZiCalculator] implementation.
 */
@Suppress(
	"unused",
	"detekt.UnnecessaryAbstractClass",
	"detekt.MagicNumber",
	"detekt.UndocumentedPublicClass",
	"detekt.UndocumentedPublicFunction",
	"detekt.TooManyFunctions",
)
abstract class BaZiCalculatorTest {

	/**
	 * Store an instance of the test subject.
	 * Use `get()` to create it for every test.
	 */
	abstract val subject: BaZiCalculator

	protected open fun check(date: LocalDate, expected: BaZi) {
		expected.hour should beNull()

		val result = subject.calculate(date)

		withClue(date.toString()) {
			result shouldBe expected
		}
	}

	protected open fun check(dateTime: LocalDateTime, expected: BaZi) {
		val result = subject.calculate(dateTime)

		withClue(dateTime.toString()) {
			result shouldBe expected
		}
	}

	@Nested
	inner class `solar month transitions` {

		@Nested
		inner class april {

			@Test fun `before leap year - before`() {
				check(
					date = LocalDate.of(1947, Month.APRIL, 5),
					expected = BaZi(
						year = BaZi.Pillar(Ding, Hai),
						month = BaZi.Pillar(Gui, Mao),
						day = BaZi.Pillar(Jia, Yin),
						hour = null,
					),
				)
			}

			@Test fun `before leap year - after`() {
				check(
					date = LocalDate.of(1947, Month.APRIL, 6),
					expected = BaZi(
						year = BaZi.Pillar(Ding, Hai),
						month = BaZi.Pillar(Jia, Chen),
						day = BaZi.Pillar(Yi, Mao),
						hour = null,
					),
				)
			}

			@Test fun `leap year - before`() {
				check(
					date = LocalDate.of(1948, Month.APRIL, 4),
					expected = BaZi(
						year = BaZi.Pillar(Wu, Zi),
						month = BaZi.Pillar(Yi, Mao),
						day = BaZi.Pillar(Ji, Wei),
						hour = null,
					),
				)
			}

			@Test fun `leap year - after`() {
				check(
					date = LocalDate.of(1948, Month.APRIL, 5),
					expected = BaZi(
						year = BaZi.Pillar(Wu, Zi),
						month = BaZi.Pillar(Bing, Chen),
						day = BaZi.Pillar(Geng, Shen),
						hour = null,
					),
				)
			}

			@Test fun `after leap year - before`() {
				check(
					date = LocalDate.of(1949, Month.APRIL, 5),
					expected = BaZi(
						year = BaZi.Pillar(Ji, Chou),
						// month = BaZi.Pillar(Ding, Mao), // midnight
						month = BaZi.Pillar(Wu, Chen), // noon
						day = BaZi.Pillar(Yi, Chou),
						hour = null,
					),
				)
			}

			@Test fun `after leap year - after`() {
				check(
					date = LocalDate.of(1949, Month.APRIL, 6),
					expected = BaZi(
						year = BaZi.Pillar(Ji, Chou),
						month = BaZi.Pillar(Wu, Chen),
						day = BaZi.Pillar(Bing, Yin),
						hour = null,
					),
				)
			}
		}

		@Nested
		inner class may {

			@Test fun `before leap year - before`() {
				check(
					date = LocalDate.of(2003, Month.MAY, 5),
					expected = BaZi(
						year = BaZi.Pillar(Gui, Wei),
						month = BaZi.Pillar(Bing, Chen),
						day = BaZi.Pillar(Wu, Yin),
						hour = null,
					),
				)
			}

			@Test fun `before leap year - after`() {
				check(
					LocalDate.of(2003, Month.MAY, 6),
					BaZi(
						year = BaZi.Pillar(Gui, Wei),
						month = BaZi.Pillar(Ding, Si),
						day = BaZi.Pillar(Ji, Mao),
						hour = null,
					),
				)
			}

			@Test fun `leap year - before`() {
				check(
					date = LocalDate.of(2004, Month.MAY, 4),
					expected = BaZi(
						year = BaZi.Pillar(Jia, Shen),
						month = BaZi.Pillar(Wu, Chen),
						day = BaZi.Pillar(Gui, Wei),
						hour = null,
					),
				)
			}

			@Test fun `leap year - after`() {
				check(
					date = LocalDate.of(2004, Month.MAY, 5),
					expected = BaZi(
						year = BaZi.Pillar(Jia, Shen),
						month = BaZi.Pillar(Ji, Si),
						day = BaZi.Pillar(Jia, Shen),
						hour = null,
					),
				)
			}

			@Test fun `after leap year - before`() {
				check(
					date = LocalDate.of(2005, Month.MAY, 4),
					expected = BaZi(
						year = BaZi.Pillar(Yi, You),
						month = BaZi.Pillar(Geng, Chen),
						day = BaZi.Pillar(Wu, Zi),
						hour = null,
					),
				)
			}

			@Test fun `after leap year - after`() {
				check(
					date = LocalDate.of(2005, Month.MAY, 5),
					expected = BaZi(
						year = BaZi.Pillar(Yi, You),
						month = BaZi.Pillar(Xin, Si),
						day = BaZi.Pillar(Ji, Chou),
						hour = null,
					),
				)
			}
		}
	}
}
