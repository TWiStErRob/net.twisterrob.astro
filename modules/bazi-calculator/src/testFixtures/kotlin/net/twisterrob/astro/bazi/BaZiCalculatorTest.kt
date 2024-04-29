package net.twisterrob.astro.bazi

import io.kotest.assertions.withClue
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
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
	inner class ChineseZodiacYears {

		@CsvSource(
			"1912, Zi",
			"1913, Chou",
			"1914, Yin",
			"1915, Mao",
			"1916, Chen",
			"1917, Si",
			"1918, Wu",
			"1919, Wei",
			"1920, Shen",
			"1921, You",
			"1922, Xu",
			"1923, Hai",

			"1924, Zi",
			"1925, Chou",
			"1926, Yin",
			"1927, Mao",
			"1928, Chen",
			"1929, Si",
			"1930, Wu",
			"1931, Wei",
			"1932, Shen",
			"1933, You",
			"1934, Xu",
			"1935, Hai",

			"1936, Zi",
			"1937, Chou",
			"1938, Yin",
			"1939, Mao",
			"1940, Chen",
			"1941, Si",
			"1942, Wu",
			"1943, Wei",
			"1944, Shen",
			"1945, You",
			"1946, Xu",
			"1947, Hai",

			"1948, Zi",
			"1949, Chou",
			"1950, Yin",
			"1951, Mao",
			"1952, Chen",
			"1953, Si",
			"1954, Wu",
			"1955, Wei",
			"1956, Shen",
			"1957, You",
			"1958, Xu",
			"1959, Hai",

			"1960, Zi",
			"1961, Chou",
			"1962, Yin",
			"1963, Mao",
			"1964, Chen",
			"1965, Si",
			"1966, Wu",
			"1967, Wei",
			"1968, Shen",
			"1969, You",
			"1970, Xu",
			"1971, Hai",

			"1972, Zi",
			"1973, Chou",
			"1974, Yin",
			"1975, Mao",
			"1976, Chen",
			"1977, Si",
			"1978, Wu",
			"1979, Wei",
			"1980, Shen",
			"1981, You",
			"1982, Xu",
			"1983, Hai",

			"1984, Zi",
			"1985, Chou",
			"1986, Yin",
			"1987, Mao",
			"1988, Chen",
			"1989, Si",
			"1990, Wu",
			"1991, Wei",
			"1992, Shen",
			"1993, You",
			"1994, Xu",
			"1995, Hai",

			"1996, Zi",
			"1997, Chou",
			"1998, Yin",
			"1999, Mao",
			"2000, Chen",
			"2001, Si",
			"2002, Wu",
			"2003, Wei",
			"2004, Shen",
			"2005, You",
			"2006, Xu",
			"2007, Hai",

			"2008, Zi",
			"2009, Chou",
			"2010, Yin",
			"2011, Mao",
			"2012, Chen",
			"2013, Si",
			"2014, Wu",
			"2015, Wei",
			"2016, Shen",
			"2017, You",
			"2018, Xu",
			"2019, Hai",

			"2020, Zi",
			"2021, Chou",
			"2022, Yin",
			"2023, Mao",
			"2024, Chen",
			"2025, Si",
			"2026, Wu",
			"2027, Wei",
			"2028, Shen",
			"2029, You",
			"2030, Xu",
			"2031, Hai",

			"2032, Zi",
			"2033, Chou",
			"2034, Yin",
			"2035, Mao",
			"2036, Chen",
			"2037, Si",
			"2038, Wu",
			"2039, Wei",
			"2040, Shen",
			"2041, You",
			"2042, Xu",
			"2043, Hai",
		)
		// Source: https://www.travelchinaguide.com/intro/chinese-zodiac-years-chart.htm
		// https://www.travelchinaguide.com/images/photogallery/2019/zodiac-year-chart.jpg
		@ParameterizedTest fun `chinese zodiac years`(
			year: Int,
			earthlyBranch: EarthlyBranch,
		) {
			val result = subject.calculate(LocalDate.of(year, 7, 1).atStartOfDay())

			result.year.earthlyBranch shouldBe earthlyBranch
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
	inner class WikipediaSexagenaryExamples {

		@Test fun `sexagenary day example 1`() {
			val result = subject.calculate(LocalDate.of(1949, Month.OCTOBER, 1))

			result.day shouldBe BaZi.Pillar(Jia, Zi) // 甲子
		}

		@Test fun `sexagenary day detailed example 1`() {
			val result = subject.calculate(LocalDate.of(1592, Month.DECEMBER, 31))


			result.day shouldBe BaZi.Pillar(Jia, Shen) // 甲申
		}

		@Test fun `sexagenary day detailed example 2`() {
			val result = subject.calculate(LocalDate.of(1338, Month.AUGUST, 4))


			result.day shouldBe BaZi.Pillar(Xin, Hai) // 辛亥
		}

		@Test fun `sexagenary day detailed example 3`() {
			val result = subject.calculate(LocalDate.of(-104, Month.MAY, 25))


			result.day shouldBe BaZi.Pillar(Geng, Yin) // 庚寅
		}

		@Test fun `sexagenary day mental example 1`() {
			val result = subject.calculate(LocalDate.of(-719, Month.FEBRUARY, 22))


			result.day shouldBe BaZi.Pillar(Ji, Si) // 己巳
		}

		@Test fun `sexagenary day mental example 2`() {
			val result = subject.calculate(LocalDate.of(-210, Month.NOVEMBER, 1))


			result.day shouldBe BaZi.Pillar(Gui, Chou) // 癸丑
		}

		@Test fun `sexagenary day mental example 3`() {
			val result = subject.calculate(LocalDate.of(1912, Month.FEBRUARY, 18))


			result.day shouldBe BaZi.Pillar(Jia, Zi) // 甲子
		}

		@Test fun `sexagenary day mental example 4`() {
			val result = subject.calculate(LocalDate.of(1949, Month.OCTOBER, 1))


			result.day shouldBe BaZi.Pillar(Jia, Zi) // 甲子
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
