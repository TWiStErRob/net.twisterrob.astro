package net.twisterrob.astro.bazi

import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.EarthlyBranch.Chen
import net.twisterrob.astro.bazi.EarthlyBranch.Chou
import net.twisterrob.astro.bazi.EarthlyBranch.Hai
import net.twisterrob.astro.bazi.EarthlyBranch.Si
import net.twisterrob.astro.bazi.EarthlyBranch.Xu
import net.twisterrob.astro.bazi.EarthlyBranch.You
import net.twisterrob.astro.bazi.EarthlyBranch.Zi
import net.twisterrob.astro.bazi.HeavenlyStem.Bing
import net.twisterrob.astro.bazi.HeavenlyStem.Ding
import net.twisterrob.astro.bazi.HeavenlyStem.Geng
import net.twisterrob.astro.bazi.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.HeavenlyStem.Ji
import net.twisterrob.astro.bazi.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.HeavenlyStem.Ren
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate
import java.time.LocalDateTime
import net.twisterrob.astro.bazi.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.HeavenlyStem.Wu as WuHS

class Group44CalculatorTest {

	private val subject = Group44Calculator()

	@Nested
	inner class Group44Examples {

		@Test fun `year pillar example`() {
			val time = LocalDate.of(1982, 1, 1).atStartOfDay()
			val result = subject.calculate(time)

			result.year shouldBe BaZi.Pillar(Ren, Xu)
		}

		@Test fun `year pillar no fraction example`() {
			val time = LocalDate.of(1983, 1, 1).atStartOfDay()
			val result = subject.calculate(time)

			result.year shouldBe BaZi.Pillar(Gui, Hai)
		}

		@Test fun `month pillar table example`() {
			val time = LocalDate.of(1984, 3, 1).atStartOfDay()
			val result = subject.calculateMonthTable(time)

			result shouldBe BaZi.Pillar(WuHS, Chen)
		}

		@Test fun `month pillar example`() {
			val time = LocalDate.of(1983, 1, 1).atStartOfDay()
			val result = subject.calculate(time)

			result.year shouldBe BaZi.Pillar(Gui, Hai)
		}

		@Test fun `leap years example after`() {
			val result = subject.leapYearsBetween(1944, 1983)

			result shouldBe 10
		}

		@Test fun `leap years example before`() {
			val result = subject.leapYearsBetween(1936, 1944)

			result shouldBe 2
		}

		@Test fun `day reference point`() {
			val result = subject.calculate(LocalDate.of(1944, 1, 1).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Jia, Zi)
		}

		@Test fun `day pillar year example after`() {
			val result = subject.calculate(LocalDate.of(1983, 1, 1).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Ji, Chou)
		}

		@Test fun `day pillar day example after`() {
			val result = subject.calculate(LocalDate.of(1983, 3, 7).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Jia, WuEB)
		}

		@Test fun `day pillar year example before`() {
			val result = subject.calculate(LocalDate.of(1936, 1, 1).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Ren, WuEB)
		}

		@Test fun `day pillar year example before (extrapolated for date)`() {
			val result = subject.calculate(LocalDate.of(1936, 7, 3).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Bing, Xu)
		}
	}

	@Nested
	inner class Celebrities {

		@Test fun `Bruce Lee`() {
			val result = subject.calculate(LocalDateTime.of(1940, 11, 27, 8, 0))

			// From: Group44, kinaiasztrologia.com confirmed.
			// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1940&MO=11&DA=27&HO=8&MI=0&CI=San%20Francisco%2C%20CA%2C%20USA&NA=BL&GE=1&TU=false&CST=1
			result shouldBe BaZi(
				BaZi.Pillar(Geng, Chen),
				BaZi.Pillar(Ding, Hai),
				BaZi.Pillar(Jia, Xu),
				BaZi.Pillar(WuHS, Chen)
			)
		}

		@Test fun `Mao Zedong`() {
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
}
