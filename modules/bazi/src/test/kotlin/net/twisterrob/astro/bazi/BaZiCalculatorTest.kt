package net.twisterrob.astro.bazi

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
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB

@Suppress("unused", "ClassName")
abstract class BaZiCalculatorTest {

	abstract val subject: BaZiCalculator

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
			// Location: San Francisco, CA, USA
			val result = subject.calculate(LocalDateTime.of(1940, 11, 27, 8, 0))

			// From: Group44, kinaiasztrologia.com confirmed.
			// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1940&MO=11&DA=27&HO=8&MI=0&CI=San%20Francisco%2C%20CA%2C%20USA&NA=BL&GE=1&TU=false&CST=1
			result shouldBe BaZi(
				BaZi.Pillar(Geng, Chen),
				BaZi.Pillar(Ding, Hai),
				BaZi.Pillar(Jia, Xu),
				BaZi.Pillar(Wu, Chen)
			)
		}

		@Test fun `Mao Zedong`() {
			// Location: Shaoshan, Xiangtan, Hunan, China
			val result = subject.calculate(LocalDateTime.of(1893, 12, 26, 8 /*7-9*/, 0))

			// From: Group44, kinaiasztrologia.com confirmed.
			// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1893&MO=12&DA=26&HO=8&MI=0&CI=Shaoshan%2C%20Xiangtan%2C%20Hunan%2C%20China&NA=MZ&GE=1&TU=false&CST=1
			result shouldBe BaZi(
				BaZi.Pillar(Gui, Si),
				BaZi.Pillar(Jia, Zi),
				BaZi.Pillar(Ding, You),
				BaZi.Pillar(Jia, Chen)
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
			val result = subject.calculate(LocalDate.of(1948, Month.JANUARY, 21))

			result shouldBe BaZi(
				BaZi.Pillar(Ding, Hai), // 丁亥
				BaZi.Pillar(Gui, Chou), // 癸丑
				BaZi.Pillar(Yi, Si), // 乙巳
				null,
			)
		}
	}

	@Nested
	inner class `gregorian year transition` {

		@Test fun `before new year`() {
			val result = subject.calculate(LocalDate.of(1947, Month.DECEMBER, 31))

			result shouldBe BaZi(
				BaZi.Pillar(Ding, Hai),
				BaZi.Pillar(Ren, Zi),
				BaZi.Pillar(Jia, Shen),
				null,
			)
		}

		@Test fun `after new year`() {
			val result = subject.calculate(LocalDate.of(1948, Month.JANUARY, 1))

			result shouldBe BaZi(
				BaZi.Pillar(Ding, Hai),
				BaZi.Pillar(Ren, Zi),
				BaZi.Pillar(Yi, You),
				null,
			)
		}
	}

	@Nested
	inner class `solar year transition` {

		@Test fun `solar year transition 1947-48 - before`() {
			val result = subject.calculate(LocalDate.of(1948, Month.FEBRUARY, 4))

			result shouldBe BaZi(
				BaZi.Pillar(Ding, Hai),
				BaZi.Pillar(Gui, Chou),
				BaZi.Pillar(Ji, Wei),
				null,
			)
		}

		@Test fun `solar year transition 1947-48 - after`() {
			val result = subject.calculate(LocalDate.of(1948, Month.FEBRUARY, 5))

			result shouldBe BaZi(
				BaZi.Pillar(Wu, Zi), // 戊子
				BaZi.Pillar(Jia, Yin), // 甲寅
				BaZi.Pillar(Geng, Shen), // 庚申
				null,
			)
		}

		@Test fun `solar year transition 1948-49 - before`() {
			val result = subject.calculate(LocalDate.of(1949, Month.FEBRUARY, 3))

			result shouldBe BaZi(
				BaZi.Pillar(Wu, Zi),
				BaZi.Pillar(Yi, Chou),
				BaZi.Pillar(Jia, Zi),
				null,
			)
		}

		@Test fun `solar year transition 1948-49 - after`() {
			val result = subject.calculate(LocalDate.of(1949, Month.FEBRUARY, 4))

			result shouldBe BaZi(
				BaZi.Pillar(Ji, Chou),
				BaZi.Pillar(Bing, Yin),
				BaZi.Pillar(Yi, Chou),
				null,
			)
		}

		@Test fun `solar year transition 1949-50 - before`() {
			val result = subject.calculate(LocalDate.of(1950, Month.FEBRUARY, 4))

			result shouldBe BaZi(
				BaZi.Pillar(Ji, Chou),
				BaZi.Pillar(Ding, Chou),
				BaZi.Pillar(Geng, WuEB),
				null,
			)
		}

		@Test fun `solar year transition 1949-50 - after`() {
			val result = subject.calculate(LocalDate.of(1950, Month.FEBRUARY, 5))

			result shouldBe BaZi(
				BaZi.Pillar(Geng, Yin),
				BaZi.Pillar(Wu, Yin),
				BaZi.Pillar(Xin, Wei),
				null,
			)
		}

		@Test fun `solar year transition 1950-51 - before`() {
			val result = subject.calculate(LocalDate.of(1951, Month.FEBRUARY, 4))

			result shouldBe BaZi(
				BaZi.Pillar(Geng, Yin),
				BaZi.Pillar(Ji, Chou),
				BaZi.Pillar(Yi, Hai),
				null,
			)
		}

		@Test fun `solar year transition 1950-51 - after`() {
			val result = subject.calculate(LocalDate.of(1951, Month.FEBRUARY, 5))

			result shouldBe BaZi(
				BaZi.Pillar(Xin, Mao),
				BaZi.Pillar(Geng, Yin),
				BaZi.Pillar(Bing, Zi),
				null,
			)
		}

		@Test fun `solar year transition 1951-52 - before`() {
			val result = subject.calculate(LocalDate.of(1952, Month.FEBRUARY, 4))

			result shouldBe BaZi(
				BaZi.Pillar(Xin, Mao),
				BaZi.Pillar(Xin, Chou),
				BaZi.Pillar(Geng, Chen),
				null,
			)
		}

		@Test fun `solar year transition 1951-52 - after`() {
			val result = subject.calculate(LocalDate.of(1952, Month.FEBRUARY, 5))

			result shouldBe BaZi(
				BaZi.Pillar(Ren, Chen),
				BaZi.Pillar(Ren, Yin),
				BaZi.Pillar(Xin, Si),
				null,
			)
		}

		@Test fun `solar year transition 1952-53 - before`() {
			val result = subject.calculate(LocalDate.of(1953, Month.FEBRUARY, 3))

			result shouldBe BaZi(
				BaZi.Pillar(Ren, Chen),
				BaZi.Pillar(Gui, Chou),
				BaZi.Pillar(Yi, You),
				null,
			)
		}

		@Test fun `solar year transition 1952-53 - after`() {
			val result = subject.calculate(LocalDate.of(1953, Month.FEBRUARY, 4))

			result shouldBe BaZi(
				BaZi.Pillar(Gui, Si),
				BaZi.Pillar(Jia, Yin),
				BaZi.Pillar(Bing, Xu),
				null,
			)
		}
	}

	@Nested
	inner class `solar month transitions` {

		@Nested
		inner class `april` {

			@Test fun `before leap year - before`() {
				val result = subject.calculate(LocalDate.of(1947, Month.APRIL, 5))

				result shouldBe BaZi(
					BaZi.Pillar(Ding, Hai),
					BaZi.Pillar(Gui, Mao),
					BaZi.Pillar(Jia, Yin),
					null,
				)
			}

			@Test fun `before leap year - after`() {
				val result = subject.calculate(LocalDate.of(1947, Month.APRIL, 6))

				result shouldBe BaZi(
					BaZi.Pillar(Ding, Hai),
					BaZi.Pillar(Jia, Chen),
					BaZi.Pillar(Yi, Mao),
					null,
				)
			}

			@Test fun `leap year - before`() {
				val result = subject.calculate(LocalDate.of(1948, Month.APRIL, 4))

				result shouldBe BaZi(
					BaZi.Pillar(Wu, Zi),
					BaZi.Pillar(Yi, Mao),
					BaZi.Pillar(Ji, Wei),
					null,
				)
			}

			@Test fun `leap year - after`() {
				val result = subject.calculate(LocalDate.of(1948, Month.APRIL, 5))

				result shouldBe BaZi(
					BaZi.Pillar(Wu, Zi),
					BaZi.Pillar(Bing, Chen),
					BaZi.Pillar(Geng, Shen),
					null,
				)
			}

			@Test fun `after leap year - before`() {
				val result = subject.calculate(LocalDate.of(1949, Month.APRIL, 5))

				result shouldBe BaZi(
					BaZi.Pillar(Ji, Chou),
					BaZi.Pillar(Ding, Mao),
					BaZi.Pillar(Yi, Chou),
					null,
				)
			}

			@Test fun `after leap year - after`() {
				val result = subject.calculate(LocalDate.of(1949, Month.APRIL, 6))

				result shouldBe BaZi(
					BaZi.Pillar(Ji, Chou),
					BaZi.Pillar(Wu, Chen),
					BaZi.Pillar(Bing, Yin),
					null,
				)
			}
		}

		@Nested
		inner class `may` {

			@Test fun `before leap year - before`() {
				val result = subject.calculate(LocalDate.of(2003, Month.MAY, 5))

				result shouldBe BaZi(
					BaZi.Pillar(Gui, Wei),
					BaZi.Pillar(Bing, Chen),
					BaZi.Pillar(Wu, Yin),
					null,
				)
			}

			@Test fun `before leap year - after`() {
				val result = subject.calculate(LocalDate.of(2003, Month.MAY, 6))

				result shouldBe BaZi(
					BaZi.Pillar(Gui, Wei),
					BaZi.Pillar(Ding, Si),
					BaZi.Pillar(Ji, Mao),
					null,
				)
			}

			@Test fun `leap year - before`() {
				val result = subject.calculate(LocalDate.of(2004, Month.MAY, 4))

				result shouldBe BaZi(
					BaZi.Pillar(Jia, Shen),
					BaZi.Pillar(Wu, Chen),
					BaZi.Pillar(Gui, Wei),
					null,
				)
			}

			@Test fun `leap year - after`() {
				val result = subject.calculate(LocalDate.of(2004, Month.MAY, 5))

				result shouldBe BaZi(
					BaZi.Pillar(Jia, Shen),
					BaZi.Pillar(Ji, Si),
					BaZi.Pillar(Jia, Shen),
					null,
				)
			}

			@Test fun `after leap year - before`() {
				val result = subject.calculate(LocalDate.of(2005, Month.MAY, 4))

				result shouldBe BaZi(
					BaZi.Pillar(Yi, You),
					BaZi.Pillar(Geng, Chen),
					BaZi.Pillar(Wu, Zi),
					null,
				)
			}

			@Disabled("This needs solar calculations!")
			@Test fun `after leap year - after`() {
				val result = subject.calculate(LocalDate.of(2005, Month.MAY, 5))

				result shouldBe BaZi(
					BaZi.Pillar(Yi, You),
					BaZi.Pillar(Xin, Si),
					BaZi.Pillar(Ji, Chou),
					null,
				)
			}
		}
	}
}
