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
import net.twisterrob.astro.bazi.model.EarthlyBranch.Xu
import net.twisterrob.astro.bazi.model.EarthlyBranch.Yin
import net.twisterrob.astro.bazi.model.EarthlyBranch.You
import net.twisterrob.astro.bazi.model.EarthlyBranch.Zi
import net.twisterrob.astro.bazi.model.HeavenlyStem.Bing
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ding
import net.twisterrob.astro.bazi.model.HeavenlyStem.Geng
import net.twisterrob.astro.bazi.model.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ji
import net.twisterrob.astro.bazi.model.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ren
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu
import net.twisterrob.astro.bazi.model.HeavenlyStem.Xin
import net.twisterrob.astro.bazi.model.HeavenlyStem.Yi
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB

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
	inner class Celebrities {

		@Test fun `Bruce Lee`() {
			check(
				// Location: San Francisco, CA, USA
				dateTime = LocalDateTime.of(1940, 11, 27, 8, 0),
				// From: Group44, kinaiasztrologia.com confirmed.
				// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1940&MO=11&DA=27&HO=8&MI=0&CI=San%20Francisco%2C%20CA%2C%20USA&NA=BL&GE=1&TU=false&CST=1
				expected = BaZi(
					year = BaZi.Pillar(Geng, Chen),
					month = BaZi.Pillar(Ding, Hai),
					day = BaZi.Pillar(Jia, Xu),
					hour = BaZi.Pillar(Wu, Chen)
				),
			)
		}

		@Test fun `Mao Zedong`() {
			check(
				// Location: Shaoshan, Xiangtan, Hunan, China
				dateTime = LocalDateTime.of(1893, 12, 26, 8 /*7-9*/, 0),
				// From: Group44, kinaiasztrologia.com confirmed.
				// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1893&MO=12&DA=26&HO=8&MI=0&CI=Shaoshan%2C%20Xiangtan%2C%20Hunan%2C%20China&NA=MZ&GE=1&TU=false&CST=1
				expected = BaZi(
					year = BaZi.Pillar(Gui, Si),
					month = BaZi.Pillar(Jia, Zi),
					day = BaZi.Pillar(Ding, You),
					hour = BaZi.Pillar(Jia, Chen)
				),
			)
		}
	}

	@Nested
	inner class `edge cases` {

		@Test fun `previous solar year`() {
			check(
				date = LocalDate.of(1948, Month.JANUARY, 21),
				expected = BaZi(
					year = BaZi.Pillar(Ding, Hai), // 丁亥
					month = BaZi.Pillar(Gui, Chou), // 癸丑
					day = BaZi.Pillar(Yi, Si), // 乙巳
					hour = null,
				),
			)
		}
	}

	@Nested
	inner class `gregorian year transition` {

		@Test fun `before new year`() {
			check(
				date = LocalDate.of(1947, Month.DECEMBER, 31),
				expected = BaZi(
					year = BaZi.Pillar(Ding, Hai),
					month = BaZi.Pillar(Ren, Zi),
					day = BaZi.Pillar(Jia, Shen),
					hour = null,
				),
			)
		}

		@Test fun `after new year`() {
			check(
				date = LocalDate.of(1948, Month.JANUARY, 1),
				expected = BaZi(
					year = BaZi.Pillar(Ding, Hai),
					month = BaZi.Pillar(Ren, Zi),
					day = BaZi.Pillar(Yi, You),
					hour = null,
				),
			)
		}
	}

	@Nested
	inner class `solar year transition` {

		@Test fun `solar year transition 1947-48 - before`() {
			check(
				date = LocalDate.of(1948, Month.FEBRUARY, 4),
				expected = BaZi(
					year = BaZi.Pillar(Ding, Hai),
					month = BaZi.Pillar(Gui, Chou),
					day = BaZi.Pillar(Ji, Wei),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1947-48 - after`() {
			check(
				date = LocalDate.of(1948, Month.FEBRUARY, 5),
				expected = BaZi(
					year = BaZi.Pillar(Wu, Zi), // 戊子
					month = BaZi.Pillar(Jia, Yin), // 甲寅
					day = BaZi.Pillar(Geng, Shen), // 庚申
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1948-49 - before`() {
			check(
				date = LocalDate.of(1949, Month.FEBRUARY, 3),
				expected = BaZi(
					year = BaZi.Pillar(Wu, Zi),
					month = BaZi.Pillar(Yi, Chou),
					day = BaZi.Pillar(Jia, Zi),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1948-49 - after`() {
			check(
				date = LocalDate.of(1949, Month.FEBRUARY, 4),
				expected = BaZi(
					year = BaZi.Pillar(Ji, Chou),
					month = BaZi.Pillar(Bing, Yin),
					day = BaZi.Pillar(Yi, Chou),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1949-50 - before`() {
			check(
				date = LocalDate.of(1950, Month.FEBRUARY, 4),
				expected = BaZi(
					// midnight
					year = BaZi.Pillar(Geng, Yin),
					month = BaZi.Pillar(Wu, Yin),
					day = BaZi.Pillar(Geng, WuEB),
					hour = null,
				),
//				expected = BaZi( // noon
//					year = BaZi.Pillar(Ji, Chou),
//					month = BaZi.Pillar(Ding, Chou),
//					day = BaZi.Pillar(Ji, Si),
//					hour = null,
//				),
			)
		}

		@Test fun `solar year transition 1949-50 - after`() {
			check(
				date = LocalDate.of(1950, Month.FEBRUARY, 5),
				expected = BaZi(
					year = BaZi.Pillar(Geng, Yin),
					month = BaZi.Pillar(Wu, Yin),
					day = BaZi.Pillar(Xin, Wei),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1950-51 - before`() {
			check(
				date = LocalDate.of(1951, Month.FEBRUARY, 4),
				expected = BaZi(
					year = BaZi.Pillar(Geng, Yin),
					month = BaZi.Pillar(Ji, Chou),
					day = BaZi.Pillar(Yi, Hai),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1950-51 - after`() {
			check(
				date = LocalDate.of(1951, Month.FEBRUARY, 5),
				expected = BaZi(
					year = BaZi.Pillar(Xin, Mao),
					month = BaZi.Pillar(Geng, Yin),
					day = BaZi.Pillar(Bing, Zi),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1951-52 - before`() {
			check(
				date = LocalDate.of(1952, Month.FEBRUARY, 4),
				expected = BaZi(
					year = BaZi.Pillar(Xin, Mao),
					month = BaZi.Pillar(Xin, Chou),
					day = BaZi.Pillar(Geng, Chen),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1951-52 - after`() {
			check(
				date = LocalDate.of(1952, Month.FEBRUARY, 5),
				expected = BaZi(
					year = BaZi.Pillar(Ren, Chen),
					month = BaZi.Pillar(Ren, Yin),
					day = BaZi.Pillar(Xin, Si),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1952-53 - before`() {
			check(
				date = LocalDate.of(1953, Month.FEBRUARY, 3),
				expected = BaZi(
					year = BaZi.Pillar(Ren, Chen),
					month = BaZi.Pillar(Gui, Chou),
					day = BaZi.Pillar(Yi, You),
					hour = null,
				),
			)
		}

		@Test fun `solar year transition 1952-53 - after`() {
			check(
				date = LocalDate.of(1953, Month.FEBRUARY, 4),
				expected = BaZi(
					year = BaZi.Pillar(Gui, Si),
					month = BaZi.Pillar(Jia, Yin),
					day = BaZi.Pillar(Bing, Xu),
					hour = null,
				),
			)
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
