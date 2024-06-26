package net.twisterrob.astro.bazi.test.data

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
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.bazi.model.HeavenlyStem.Bing
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ding
import net.twisterrob.astro.bazi.model.HeavenlyStem.Geng
import net.twisterrob.astro.bazi.model.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ji
import net.twisterrob.astro.bazi.model.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ren
import net.twisterrob.astro.bazi.model.HeavenlyStem.Xin
import net.twisterrob.astro.bazi.model.HeavenlyStem.Yi
import java.time.LocalDate
import java.time.LocalDate.of
import java.time.LocalDateTime
import java.time.Month.FEBRUARY
import java.time.Month.JANUARY
import java.time.temporal.ChronoUnit.DAYS
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS

@Suppress("detekt.LongParameterList")
class SexagenaryYearTestCase(
	val cyclicOrdinal: Int,
	val stem: HeavenlyStem,
	val branch: EarthlyBranch,

	/**
	 * The first day of the year in lunar calendar, inclusive.
	 */
	val startDate: LocalDate,

	/**
	 * The last day of the year in lunar calendar, inclusive.
	 */
	val endDate: LocalDate,

	/**
	 * The first minute of the year in solar calendar (Lichun / 立春, beginning of spring), inclusive.
	 */
	val solarStart: LocalDateTime? = null,

	/**
	 * The first minute of the next year in solar calendar. So exclusive, subtract 1 minute to get last minute.
	 */
	val solarEnd: LocalDateTime? = null,
) {

	val year: Int
		get() = startDate.year

	val midDate: LocalDate
		get() = startDate.plusDays(DAYS.between(startDate, endDate) / 2)

	/**
	 * https://en.wikipedia.org/wiki/Sexagenary_cycle
	 * Mostly the same as https://www.travelchinaguide.com/intro/chinese-zodiac-years-chart.htm,
	 * but there are some discrepancies in some years.
	 *
	 * Branches: https://www.travelchinaguide.com/images/photogallery/2019/zodiac-year-chart.jpg
	 */
	@Suppress("detekt.NamedArguments", "detekt.MaxLineLength")
	companion object {

		/**
		 * https://en.wikipedia.org/wiki/Sexagenary_cycle#1804%E2%80%931923
		 */
		val CYCLE_1804: List<SexagenaryYearTestCase> = listOf(
			SexagenaryYearTestCase(1, Jia, Zi, of(1804, FEBRUARY, 11), of(1805, JANUARY, 30)),
			SexagenaryYearTestCase(2, Yi, Chou, of(1805, JANUARY, 31), of(1806, FEBRUARY, 17)),
			SexagenaryYearTestCase(3, Bing, Yin, of(1806, FEBRUARY, 18), of(1807, FEBRUARY, 6)),
			SexagenaryYearTestCase(4, Ding, Mao, of(1807, FEBRUARY, 7), of(1808, JANUARY, 27)),
			SexagenaryYearTestCase(5, WuHS, Chen, of(1808, JANUARY, 28), of(1809, FEBRUARY, 14)),
			SexagenaryYearTestCase(6, Ji, Si, of(1809, FEBRUARY, 15), of(1810, FEBRUARY, 3)),
			SexagenaryYearTestCase(7, Geng, WuEB, of(1810, FEBRUARY, 4), of(1811, JANUARY, 24)),
			SexagenaryYearTestCase(8, Xin, Wei, of(1811, JANUARY, 25), of(1812, FEBRUARY, 12)),
			SexagenaryYearTestCase(9, Ren, Shen, of(1812, FEBRUARY, 13), of(1813, JANUARY, 31)),
			SexagenaryYearTestCase(10, Gui, You, of(1813, FEBRUARY, 1), of(1814, JANUARY, 20)),
			SexagenaryYearTestCase(11, Jia, Xu, of(1814, JANUARY, 21), of(1815, FEBRUARY, 8)),
			SexagenaryYearTestCase(12, Yi, Hai, of(1815, FEBRUARY, 9), of(1816, JANUARY, 28)),
			SexagenaryYearTestCase(13, Bing, Zi, of(1816, JANUARY, 29), of(1817, FEBRUARY, 15)),
			SexagenaryYearTestCase(14, Ding, Chou, of(1817, FEBRUARY, 16), of(1818, FEBRUARY, 5)),
			SexagenaryYearTestCase(15, WuHS, Yin, of(1818, FEBRUARY, 6), of(1819, JANUARY, 25)),
			SexagenaryYearTestCase(16, Ji, Mao, of(1819, JANUARY, 26), of(1820, FEBRUARY, 13)),
			SexagenaryYearTestCase(17, Geng, Chen, of(1820, FEBRUARY, 14), of(1821, FEBRUARY, 2)),
			SexagenaryYearTestCase(18, Xin, Si, of(1821, FEBRUARY, 3), of(1822, JANUARY, 22)),
			SexagenaryYearTestCase(19, Ren, WuEB, of(1822, JANUARY, 23), of(1823, FEBRUARY, 10)),
			SexagenaryYearTestCase(20, Gui, Wei, of(1823, FEBRUARY, 11), of(1824, JANUARY, 30)),
			SexagenaryYearTestCase(21, Jia, Shen, of(1824, JANUARY, 31), of(1825, FEBRUARY, 17)),
			SexagenaryYearTestCase(22, Yi, You, of(1825, FEBRUARY, 18), of(1826, FEBRUARY, 6)),
			SexagenaryYearTestCase(23, Bing, Xu, of(1826, FEBRUARY, 7), of(1827, JANUARY, 26)),
			SexagenaryYearTestCase(24, Ding, Hai, of(1827, JANUARY, 27), of(1828, FEBRUARY, 15)),
			SexagenaryYearTestCase(25, WuHS, Zi, of(1828, FEBRUARY, 16), of(1829, FEBRUARY, 3)),
			SexagenaryYearTestCase(26, Ji, Chou, of(1829, FEBRUARY, 4), of(1830, JANUARY, 24)),
			SexagenaryYearTestCase(27, Geng, Yin, of(1830, JANUARY, 25), of(1831, FEBRUARY, 12)),
			SexagenaryYearTestCase(28, Xin, Mao, of(1831, FEBRUARY, 13), of(1832, FEBRUARY, 1)),
			SexagenaryYearTestCase(29, Ren, Chen, of(1832, FEBRUARY, 2), of(1833, FEBRUARY, 19)),
			SexagenaryYearTestCase(30, Gui, Si, of(1833, FEBRUARY, 20), of(1834, FEBRUARY, 8)),
			SexagenaryYearTestCase(31, Jia, WuEB, of(1834, FEBRUARY, 9), of(1835, JANUARY, 28)),
			SexagenaryYearTestCase(32, Yi, Wei, of(1835, JANUARY, 29), of(1836, FEBRUARY, 16)),
			SexagenaryYearTestCase(33, Bing, Shen, of(1836, FEBRUARY, 17), of(1837, FEBRUARY, 5)),
			SexagenaryYearTestCase(34, Ding, You, of(1837, FEBRUARY, 6), of(1838, JANUARY, 25)),
			SexagenaryYearTestCase(35, WuHS, Xu, of(1838, JANUARY, 26), of(1839, FEBRUARY, 13)),
			SexagenaryYearTestCase(36, Ji, Hai, of(1839, FEBRUARY, 14), of(1840, FEBRUARY, 2)),
			SexagenaryYearTestCase(37, Geng, Zi, of(1840, FEBRUARY, 3), of(1841, JANUARY, 22)),
			SexagenaryYearTestCase(38, Xin, Chou, of(1841, JANUARY, 23), of(1842, FEBRUARY, 9)),
			SexagenaryYearTestCase(39, Ren, Yin, of(1842, FEBRUARY, 10), of(1843, JANUARY, 29)),
			SexagenaryYearTestCase(40, Gui, Mao, of(1843, JANUARY, 30), of(1844, FEBRUARY, 17)),
			SexagenaryYearTestCase(41, Jia, Chen, of(1844, FEBRUARY, 18), of(1845, FEBRUARY, 6)),
			SexagenaryYearTestCase(42, Yi, Si, of(1845, FEBRUARY, 7), of(1846, JANUARY, 26)),
			SexagenaryYearTestCase(43, Bing, WuEB, of(1846, JANUARY, 27), of(1847, FEBRUARY, 15)),
			SexagenaryYearTestCase(44, Ding, Wei, of(1847, FEBRUARY, 16), of(1848, FEBRUARY, 4)),
			SexagenaryYearTestCase(45, WuHS, Shen, of(1848, FEBRUARY, 5), of(1849, JANUARY, 23)),
			SexagenaryYearTestCase(46, Ji, You, of(1849, JANUARY, 24), of(1850, FEBRUARY, 11)),
			SexagenaryYearTestCase(47, Geng, Xu, of(1850, FEBRUARY, 12), of(1851, JANUARY, 31)),
			SexagenaryYearTestCase(48, Xin, Hai, of(1851, FEBRUARY, 1), of(1852, FEBRUARY, 19)),
			SexagenaryYearTestCase(49, Ren, Zi, of(1852, FEBRUARY, 20), of(1853, FEBRUARY, 7)),
			SexagenaryYearTestCase(50, Gui, Chou, of(1853, FEBRUARY, 8), of(1854, JANUARY, 28)),
			SexagenaryYearTestCase(51, Jia, Yin, of(1854, JANUARY, 29), of(1855, FEBRUARY, 16)),
			SexagenaryYearTestCase(52, Yi, Mao, of(1855, FEBRUARY, 17), of(1856, FEBRUARY, 6)),
			SexagenaryYearTestCase(53, Bing, Chen, of(1856, FEBRUARY, 7), of(1857, JANUARY, 25)),
			SexagenaryYearTestCase(54, Ding, Si, of(1857, JANUARY, 26), of(1858, FEBRUARY, 13)),
			SexagenaryYearTestCase(55, WuHS, WuEB, of(1858, FEBRUARY, 14), of(1859, FEBRUARY, 2)),
			SexagenaryYearTestCase(56, Ji, Wei, of(1859, FEBRUARY, 3), of(1860, JANUARY, 22)),
			SexagenaryYearTestCase(57, Geng, Shen, of(1860, JANUARY, 23), of(1861, FEBRUARY, 9)),
			SexagenaryYearTestCase(58, Xin, You, of(1861, FEBRUARY, 10), of(1862, JANUARY, 29)),
			SexagenaryYearTestCase(59, Ren, Xu, of(1862, JANUARY, 30), of(1863, FEBRUARY, 17)),
			SexagenaryYearTestCase(60, Gui, Hai, of(1863, FEBRUARY, 18), of(1864, FEBRUARY, 7)),
		)

		/**
		 * https://en.wikipedia.org/wiki/Sexagenary_cycle#1804%E2%80%931923
		 */
		val CYCLE_1864: List<SexagenaryYearTestCase> = listOf(
			SexagenaryYearTestCase(1, Jia, Zi, of(1864, FEBRUARY, 8), of(1865, JANUARY, 26)),
			SexagenaryYearTestCase(2, Yi, Chou, of(1865, JANUARY, 27), of(1866, FEBRUARY, 14)),
			SexagenaryYearTestCase(3, Bing, Yin, of(1866, FEBRUARY, 15), of(1867, FEBRUARY, 4)),
			SexagenaryYearTestCase(4, Ding, Mao, of(1867, FEBRUARY, 5), of(1868, JANUARY, 24)),
			SexagenaryYearTestCase(5, WuHS, Chen, of(1868, JANUARY, 25), of(1869, FEBRUARY, 10)),
			SexagenaryYearTestCase(6, Ji, Si, of(1869, FEBRUARY, 11), of(1870, JANUARY, 31)),
			SexagenaryYearTestCase(7, Geng, WuEB, of(1870, FEBRUARY, 1), of(1871, FEBRUARY, 19)),
			SexagenaryYearTestCase(8, Xin, Wei, of(1871, FEBRUARY, 20), of(1872, FEBRUARY, 8)),
			SexagenaryYearTestCase(9, Ren, Shen, of(1872, FEBRUARY, 9), of(1873, JANUARY, 28)),
			SexagenaryYearTestCase(10, Gui, You, of(1873, JANUARY, 29), of(1874, FEBRUARY, 16)),
			SexagenaryYearTestCase(11, Jia, Xu, of(1874, FEBRUARY, 17), of(1875, FEBRUARY, 5)),
			SexagenaryYearTestCase(12, Yi, Hai, of(1875, FEBRUARY, 6), of(1876, JANUARY, 25)),
			SexagenaryYearTestCase(13, Bing, Zi, of(1876, JANUARY, 26), of(1877, FEBRUARY, 12)),
			SexagenaryYearTestCase(14, Ding, Chou, of(1877, FEBRUARY, 13), of(1878, FEBRUARY, 1)),
			SexagenaryYearTestCase(15, WuHS, Yin, of(1878, FEBRUARY, 2), of(1879, JANUARY, 22)),
			SexagenaryYearTestCase(16, Ji, Mao, of(1879, JANUARY, 23), of(1880, FEBRUARY, 9)),
			SexagenaryYearTestCase(17, Geng, Chen, of(1880, FEBRUARY, 10), of(1881, JANUARY, 29)),
			SexagenaryYearTestCase(18, Xin, Si, of(1881, JANUARY, 30), of(1882, FEBRUARY, 17)),
			SexagenaryYearTestCase(19, Ren, WuEB, of(1882, FEBRUARY, 18), of(1883, FEBRUARY, 7)),
			SexagenaryYearTestCase(20, Gui, Wei, of(1883, FEBRUARY, 8), of(1884, JANUARY, 27)),
			SexagenaryYearTestCase(21, Jia, Shen, of(1884, JANUARY, 28), of(1885, FEBRUARY, 14)),
			SexagenaryYearTestCase(22, Yi, You, of(1885, FEBRUARY, 15), of(1886, FEBRUARY, 3)),
			SexagenaryYearTestCase(23, Bing, Xu, of(1886, FEBRUARY, 4), of(1887, JANUARY, 23)),
			SexagenaryYearTestCase(24, Ding, Hai, of(1887, JANUARY, 24), of(1888, FEBRUARY, 11)),
			SexagenaryYearTestCase(25, WuHS, Zi, of(1888, FEBRUARY, 12), of(1889, JANUARY, 30)),
			SexagenaryYearTestCase(26, Ji, Chou, of(1889, JANUARY, 31), of(1890, JANUARY, 20)),
			SexagenaryYearTestCase(27, Geng, Yin, of(1890, JANUARY, 21), of(1891, FEBRUARY, 8)),
			SexagenaryYearTestCase(28, Xin, Mao, of(1891, FEBRUARY, 9), of(1892, JANUARY, 29)),
			SexagenaryYearTestCase(29, Ren, Chen, of(1892, JANUARY, 30), of(1893, FEBRUARY, 16)),
			SexagenaryYearTestCase(30, Gui, Si, of(1893, FEBRUARY, 17), of(1894, FEBRUARY, 5)),
			SexagenaryYearTestCase(31, Jia, WuEB, of(1894, FEBRUARY, 6), of(1895, JANUARY, 25)),
			SexagenaryYearTestCase(32, Yi, Wei, of(1895, JANUARY, 26), of(1896, FEBRUARY, 12)),
			SexagenaryYearTestCase(33, Bing, Shen, of(1896, FEBRUARY, 13), of(1897, FEBRUARY, 1)),
			SexagenaryYearTestCase(34, Ding, You, of(1897, FEBRUARY, 2), of(1898, JANUARY, 21)),
			SexagenaryYearTestCase(35, WuHS, Xu, of(1898, JANUARY, 22), of(1899, FEBRUARY, 9)),
			SexagenaryYearTestCase(36, Ji, Hai, of(1899, FEBRUARY, 10), of(1900, JANUARY, 30)),
			SexagenaryYearTestCase(37, Geng, Zi, of(1900, JANUARY, 31), of(1901, FEBRUARY, 18)),
			SexagenaryYearTestCase(38, Xin, Chou, of(1901, FEBRUARY, 19), of(1902, FEBRUARY, 7)),
			SexagenaryYearTestCase(39, Ren, Yin, of(1902, FEBRUARY, 8), of(1903, JANUARY, 28)),
			SexagenaryYearTestCase(40, Gui, Mao, of(1903, JANUARY, 29), of(1904, FEBRUARY, 15)),
			SexagenaryYearTestCase(41, Jia, Chen, of(1904, FEBRUARY, 16), of(1905, FEBRUARY, 3)),
			SexagenaryYearTestCase(42, Yi, Si, of(1905, FEBRUARY, 4), of(1906, JANUARY, 24)),
			SexagenaryYearTestCase(43, Bing, WuEB, of(1906, JANUARY, 25), of(1907, FEBRUARY, 12)),
			SexagenaryYearTestCase(44, Ding, Wei, of(1907, FEBRUARY, 13), of(1908, FEBRUARY, 1)),
			SexagenaryYearTestCase(45, WuHS, Shen, of(1908, FEBRUARY, 2), of(1909, JANUARY, 21)),
			SexagenaryYearTestCase(46, Ji, You, of(1909, JANUARY, 22), of(1910, FEBRUARY, 9)),
			SexagenaryYearTestCase(47, Geng, Xu, of(1910, FEBRUARY, 10), of(1911, JANUARY, 29)),
			SexagenaryYearTestCase(48, Xin, Hai, of(1911, JANUARY, 30), of(1912, FEBRUARY, 17)),
			SexagenaryYearTestCase(49, Ren, Zi, of(1912, FEBRUARY, 18), of(1913, FEBRUARY, 5)),
			SexagenaryYearTestCase(50, Gui, Chou, of(1913, FEBRUARY, 6), of(1914, JANUARY, 25)),
			SexagenaryYearTestCase(51, Jia, Yin, of(1914, JANUARY, 26), of(1915, FEBRUARY, 13)),
			SexagenaryYearTestCase(52, Yi, Mao, of(1915, FEBRUARY, 14), of(1916, FEBRUARY, 2)),
			SexagenaryYearTestCase(53, Bing, Chen, of(1916, FEBRUARY, 3), of(1917, JANUARY, 22)),
			SexagenaryYearTestCase(54, Ding, Si, of(1917, JANUARY, 23), of(1918, FEBRUARY, 10)),
			SexagenaryYearTestCase(55, WuHS, WuEB, of(1918, FEBRUARY, 11), of(1919, JANUARY, 31)),
			SexagenaryYearTestCase(56, Ji, Wei, of(1919, FEBRUARY, 1), of(1920, FEBRUARY, 19)),
			SexagenaryYearTestCase(57, Geng, Shen, of(1920, FEBRUARY, 20), of(1921, FEBRUARY, 7)),
			SexagenaryYearTestCase(58, Xin, You, of(1921, FEBRUARY, 8), of(1922, JANUARY, 27)),
			SexagenaryYearTestCase(59, Ren, Xu, of(1922, JANUARY, 28), of(1923, FEBRUARY, 15)),
			SexagenaryYearTestCase(60, Gui, Hai, of(1923, FEBRUARY, 16), of(1924, FEBRUARY, 4)),
		)

		/**
		 * https://en.wikipedia.org/wiki/Sexagenary_cycle#1924%E2%80%932043
		 */
		val CYCLE_1924: List<SexagenaryYearTestCase> = listOf(
			SexagenaryYearTestCase(1, Jia, Zi, of(1924, FEBRUARY, 5), of(1925, JANUARY, 23)),
			SexagenaryYearTestCase(2, Yi, Chou, of(1925, JANUARY, 24), of(1926, FEBRUARY, 12)),
			SexagenaryYearTestCase(3, Bing, Yin, of(1926, FEBRUARY, 13), of(1927, FEBRUARY, 1)),
			SexagenaryYearTestCase(4, Ding, Mao, of(1927, FEBRUARY, 2), of(1928, JANUARY, 22)),
			SexagenaryYearTestCase(5, WuHS, Chen, of(1928, JANUARY, 23), of(1929, FEBRUARY, 9)),
			SexagenaryYearTestCase(6, Ji, Si, of(1929, FEBRUARY, 10), of(1930, JANUARY, 29)),
			SexagenaryYearTestCase(7, Geng, WuEB, of(1930, JANUARY, 30), of(1931, FEBRUARY, 16)),
			SexagenaryYearTestCase(8, Xin, Wei, of(1931, FEBRUARY, 17), of(1932, FEBRUARY, 6)),
			SexagenaryYearTestCase(9, Ren, Shen, of(1932, FEBRUARY, 7), of(1933, JANUARY, 25)),
			SexagenaryYearTestCase(10, Gui, You, of(1933, JANUARY, 26), of(1934, FEBRUARY, 13)),
			SexagenaryYearTestCase(11, Jia, Xu, of(1934, FEBRUARY, 14), of(1935, FEBRUARY, 3)),
			SexagenaryYearTestCase(12, Yi, Hai, of(1935, FEBRUARY, 4), of(1936, JANUARY, 23)),
			SexagenaryYearTestCase(13, Bing, Zi, of(1936, JANUARY, 24), of(1937, FEBRUARY, 10)),
			SexagenaryYearTestCase(14, Ding, Chou, of(1937, FEBRUARY, 11), of(1938, JANUARY, 30)),
			SexagenaryYearTestCase(15, WuHS, Yin, of(1938, JANUARY, 31), of(1939, FEBRUARY, 18)),
			SexagenaryYearTestCase(16, Ji, Mao, of(1939, FEBRUARY, 19), of(1940, FEBRUARY, 7)),
			SexagenaryYearTestCase(17, Geng, Chen, of(1940, FEBRUARY, 8), of(1941, JANUARY, 26)),
			SexagenaryYearTestCase(18, Xin, Si, of(1941, JANUARY, 27), of(1942, FEBRUARY, 14)),
			SexagenaryYearTestCase(19, Ren, WuEB, of(1942, FEBRUARY, 15), of(1943, FEBRUARY, 4)),
			SexagenaryYearTestCase(20, Gui, Wei, of(1943, FEBRUARY, 5), of(1944, JANUARY, 24)),
			SexagenaryYearTestCase(21, Jia, Shen, of(1944, JANUARY, 25), of(1945, FEBRUARY, 12)),
			SexagenaryYearTestCase(22, Yi, You, of(1945, FEBRUARY, 13), of(1946, FEBRUARY, 1)),
			SexagenaryYearTestCase(23, Bing, Xu, of(1946, FEBRUARY, 2), of(1947, JANUARY, 21)),
			SexagenaryYearTestCase(24, Ding, Hai, of(1947, JANUARY, 22), of(1948, FEBRUARY, 9)),
			SexagenaryYearTestCase(25, WuHS, Zi, of(1948, FEBRUARY, 10), of(1949, JANUARY, 28)),
			SexagenaryYearTestCase(26, Ji, Chou, of(1949, JANUARY, 29), of(1950, FEBRUARY, 16)),
			SexagenaryYearTestCase(27, Geng, Yin, of(1950, FEBRUARY, 17), of(1951, FEBRUARY, 5)),
			SexagenaryYearTestCase(28, Xin, Mao, of(1951, FEBRUARY, 6), of(1952, JANUARY, 26)),
			SexagenaryYearTestCase(29, Ren, Chen, of(1952, JANUARY, 27), of(1953, FEBRUARY, 13)),
			SexagenaryYearTestCase(30, Gui, Si, of(1953, FEBRUARY, 14), of(1954, FEBRUARY, 3)),
			SexagenaryYearTestCase(31, Jia, WuEB, of(1954, FEBRUARY, 4), of(1955, JANUARY, 23)),
			SexagenaryYearTestCase(32, Yi, Wei, of(1955, JANUARY, 24), of(1956, FEBRUARY, 11)),
			SexagenaryYearTestCase(33, Bing, Shen, of(1956, FEBRUARY, 12), of(1957, JANUARY, 30)),
			SexagenaryYearTestCase(34, Ding, You, of(1957, JANUARY, 31), of(1958, FEBRUARY, 18)),
			SexagenaryYearTestCase(35, WuHS, Xu, of(1958, FEBRUARY, 19), of(1959, FEBRUARY, 7)),
			SexagenaryYearTestCase(36, Ji, Hai, of(1959, FEBRUARY, 8), of(1960, JANUARY, 27)),
			SexagenaryYearTestCase(37, Geng, Zi, of(1960, JANUARY, 28), of(1961, FEBRUARY, 14)),
			SexagenaryYearTestCase(38, Xin, Chou, of(1961, FEBRUARY, 15), of(1962, FEBRUARY, 4)),
			SexagenaryYearTestCase(39, Ren, Yin, of(1962, FEBRUARY, 5), of(1963, JANUARY, 25)),
			SexagenaryYearTestCase(40, Gui, Mao, of(1963, JANUARY, 26), of(1964, FEBRUARY, 12)),
			SexagenaryYearTestCase(41, Jia, Chen, of(1964, FEBRUARY, 13), of(1965, FEBRUARY, 1)),
			SexagenaryYearTestCase(42, Yi, Si, of(1965, FEBRUARY, 2), of(1966, JANUARY, 21)),
			SexagenaryYearTestCase(43, Bing, WuEB, of(1966, JANUARY, 22), of(1967, FEBRUARY, 8)),
			SexagenaryYearTestCase(44, Ding, Wei, of(1967, FEBRUARY, 9), of(1968, JANUARY, 29)),
			SexagenaryYearTestCase(45, WuHS, Shen, of(1968, JANUARY, 30), of(1969, FEBRUARY, 16)),
			SexagenaryYearTestCase(46, Ji, You, of(1969, FEBRUARY, 17), of(1970, FEBRUARY, 5)),
			SexagenaryYearTestCase(47, Geng, Xu, of(1970, FEBRUARY, 6), of(1971, JANUARY, 26)),
			SexagenaryYearTestCase(48, Xin, Hai, of(1971, JANUARY, 27), of(1972, FEBRUARY, 14)),
			SexagenaryYearTestCase(49, Ren, Zi, of(1972, FEBRUARY, 15), of(1973, FEBRUARY, 2)),
			SexagenaryYearTestCase(50, Gui, Chou, of(1973, FEBRUARY, 3), of(1974, JANUARY, 22)),
			SexagenaryYearTestCase(51, Jia, Yin, of(1974, JANUARY, 23), of(1975, FEBRUARY, 10)),
			SexagenaryYearTestCase(52, Yi, Mao, of(1975, FEBRUARY, 11), of(1976, JANUARY, 30)),
			SexagenaryYearTestCase(53, Bing, Chen, of(1976, JANUARY, 31), of(1977, FEBRUARY, 17)),
			SexagenaryYearTestCase(54, Ding, Si, of(1977, FEBRUARY, 18), of(1978, FEBRUARY, 6)),
			SexagenaryYearTestCase(55, WuHS, WuEB, of(1978, FEBRUARY, 7), of(1979, JANUARY, 27)),
			SexagenaryYearTestCase(56, Ji, Wei, of(1979, JANUARY, 28), of(1980, FEBRUARY, 15)),
			SexagenaryYearTestCase(57, Geng, Shen, of(1980, FEBRUARY, 16), of(1981, FEBRUARY, 4)),
			SexagenaryYearTestCase(58, Xin, You, of(1981, FEBRUARY, 5), of(1982, JANUARY, 24)),
			SexagenaryYearTestCase(59, Ren, Xu, of(1982, JANUARY, 25), of(1983, FEBRUARY, 12)),
			SexagenaryYearTestCase(60, Gui, Hai, of(1983, FEBRUARY, 13), of(1984, FEBRUARY, 1)),
		)

		/**
		 * https://en.wikipedia.org/wiki/Sexagenary_cycle#1924%E2%80%932043
		 */
		val CYCLE_1984: List<SexagenaryYearTestCase> = listOf(
			SexagenaryYearTestCase(1, Jia, Zi, of(1984, FEBRUARY, 2), of(1985, JANUARY, 20)),
			SexagenaryYearTestCase(2, Yi, Chou, of(1985, JANUARY, 21), of(1986, FEBRUARY, 8)),
			SexagenaryYearTestCase(3, Bing, Yin, of(1986, FEBRUARY, 9), of(1987, JANUARY, 29)),
			SexagenaryYearTestCase(4, Ding, Mao, of(1987, JANUARY, 30), of(1988, FEBRUARY, 17)),
			SexagenaryYearTestCase(5, WuHS, Chen, of(1988, FEBRUARY, 18), of(1989, FEBRUARY, 5)),
			SexagenaryYearTestCase(6, Ji, Si, of(1989, FEBRUARY, 6), of(1990, JANUARY, 26)),
			SexagenaryYearTestCase(7, Geng, WuEB, of(1990, JANUARY, 27), of(1991, FEBRUARY, 14)),
			SexagenaryYearTestCase(8, Xin, Wei, of(1991, FEBRUARY, 15), of(1992, FEBRUARY, 3)),
			SexagenaryYearTestCase(9, Ren, Shen, of(1992, FEBRUARY, 4), of(1993, JANUARY, 22)),
			SexagenaryYearTestCase(10, Gui, You, of(1993, JANUARY, 23), of(1994, FEBRUARY, 10)),
			SexagenaryYearTestCase(11, Jia, Xu, of(1994, FEBRUARY, 11), of(1995, JANUARY, 30)),
			SexagenaryYearTestCase(12, Yi, Hai, of(1995, JANUARY, 31), of(1996, FEBRUARY, 18)),
			SexagenaryYearTestCase(13, Bing, Zi, of(1996, FEBRUARY, 19), of(1997, FEBRUARY, 6)),
			SexagenaryYearTestCase(14, Ding, Chou, of(1997, FEBRUARY, 7), of(1998, JANUARY, 27)),
			SexagenaryYearTestCase(15, WuHS, Yin, of(1998, JANUARY, 28), of(1999, FEBRUARY, 15)),
			SexagenaryYearTestCase(16, Ji, Mao, of(1999, FEBRUARY, 16), of(2000, FEBRUARY, 4)),
			SexagenaryYearTestCase(17, Geng, Chen, of(2000, FEBRUARY, 5), of(2001, JANUARY, 23), null, NY._2001),
			SexagenaryYearTestCase(18, Xin, Si, of(2001, JANUARY, 24), of(2002, FEBRUARY, 11), NY._2001, NY._2002),
			SexagenaryYearTestCase(19, Ren, WuEB, of(2002, FEBRUARY, 12), of(2003, JANUARY, 31), NY._2002, NY._2003),
			SexagenaryYearTestCase(20, Gui, Wei, of(2003, FEBRUARY, 1), of(2004, JANUARY, 21), NY._2003, NY._2004),
			SexagenaryYearTestCase(21, Jia, Shen, of(2004, JANUARY, 22), of(2005, FEBRUARY, 8), NY._2004, NY._2005),
			SexagenaryYearTestCase(22, Yi, You, of(2005, FEBRUARY, 9), of(2006, JANUARY, 29), NY._2005, NY._2006),
			SexagenaryYearTestCase(23, Bing, Xu, of(2006, JANUARY, 30), of(2007, FEBRUARY, 17), NY._2006, NY._2007),
			SexagenaryYearTestCase(24, Ding, Hai, of(2007, FEBRUARY, 18), of(2008, FEBRUARY, 6), NY._2007, NY._2008),
			SexagenaryYearTestCase(25, WuHS, Zi, of(2008, FEBRUARY, 7), of(2009, JANUARY, 25), NY._2008, NY._2009),
			SexagenaryYearTestCase(26, Ji, Chou, of(2009, JANUARY, 26), of(2010, FEBRUARY, 13), NY._2009, NY._2010),
			SexagenaryYearTestCase(27, Geng, Yin, of(2010, FEBRUARY, 14), of(2011, FEBRUARY, 2), NY._2010, NY._2011),
			SexagenaryYearTestCase(28, Xin, Mao, of(2011, FEBRUARY, 3), of(2012, JANUARY, 22), NY._2011, NY._2012),
			SexagenaryYearTestCase(29, Ren, Chen, of(2012, JANUARY, 23), of(2013, FEBRUARY, 9), NY._2012, NY._2013),
			SexagenaryYearTestCase(30, Gui, Si, of(2013, FEBRUARY, 10), of(2014, JANUARY, 30), NY._2013, NY._2014),
			SexagenaryYearTestCase(31, Jia, WuEB, of(2014, JANUARY, 31), of(2015, FEBRUARY, 18), NY._2014, NY._2015),
			SexagenaryYearTestCase(32, Yi, Wei, of(2015, FEBRUARY, 19), of(2016, FEBRUARY, 7), NY._2015, NY._2016),
			SexagenaryYearTestCase(33, Bing, Shen, of(2016, FEBRUARY, 8), of(2017, JANUARY, 27), NY._2016, NY._2017),
			SexagenaryYearTestCase(34, Ding, You, of(2017, JANUARY, 28), of(2018, FEBRUARY, 15), NY._2017, NY._2018),
			SexagenaryYearTestCase(35, WuHS, Xu, of(2018, FEBRUARY, 16), of(2019, FEBRUARY, 4), NY._2018, NY._2019),
			SexagenaryYearTestCase(36, Ji, Hai, of(2019, FEBRUARY, 5), of(2020, JANUARY, 24), NY._2019, NY._2020),
			SexagenaryYearTestCase(37, Geng, Zi, of(2020, JANUARY, 25), of(2021, FEBRUARY, 11), NY._2020, NY._2021),
			SexagenaryYearTestCase(38, Xin, Chou, of(2021, FEBRUARY, 12), of(2022, JANUARY, 31), NY._2021, NY._2022),
			SexagenaryYearTestCase(39, Ren, Yin, of(2022, FEBRUARY, 1), of(2023, JANUARY, 21), NY._2022, NY._2023),
			SexagenaryYearTestCase(40, Gui, Mao, of(2023, JANUARY, 22), of(2024, FEBRUARY, 9), NY._2023, NY._2024),
			SexagenaryYearTestCase(41, Jia, Chen, of(2024, FEBRUARY, 10), of(2025, JANUARY, 28), NY._2024, NY._2025),
			SexagenaryYearTestCase(42, Yi, Si, of(2025, JANUARY, 29), of(2026, FEBRUARY, 16), NY._2025, NY._2026),
			SexagenaryYearTestCase(43, Bing, WuEB, of(2026, FEBRUARY, 17), of(2027, FEBRUARY, 5), NY._2026, NY._2027),
			SexagenaryYearTestCase(44, Ding, Wei, of(2027, FEBRUARY, 6), of(2028, JANUARY, 25), NY._2027, NY._2028),
			SexagenaryYearTestCase(45, WuHS, Shen, of(2028, JANUARY, 26), of(2029, FEBRUARY, 12), NY._2028, NY._2029),
			SexagenaryYearTestCase(46, Ji, You, of(2029, FEBRUARY, 13), of(2030, FEBRUARY, 2), NY._2029, NY._2030),
			SexagenaryYearTestCase(47, Geng, Xu, of(2030, FEBRUARY, 3), of(2031, JANUARY, 22), NY._2030, null),
			SexagenaryYearTestCase(48, Xin, Hai, of(2031, JANUARY, 23), of(2032, FEBRUARY, 10)),
			SexagenaryYearTestCase(49, Ren, Zi, of(2032, FEBRUARY, 11), of(2033, JANUARY, 30)),
			SexagenaryYearTestCase(50, Gui, Chou, of(2033, JANUARY, 31), of(2034, FEBRUARY, 18)),
			SexagenaryYearTestCase(51, Jia, Yin, of(2034, FEBRUARY, 19), of(2035, FEBRUARY, 7)),
			SexagenaryYearTestCase(52, Yi, Mao, of(2035, FEBRUARY, 8), of(2036, JANUARY, 27)),
			SexagenaryYearTestCase(53, Bing, Chen, of(2036, JANUARY, 28), of(2037, FEBRUARY, 14)),
			SexagenaryYearTestCase(54, Ding, Si, of(2037, FEBRUARY, 15), of(2038, FEBRUARY, 3)),
			SexagenaryYearTestCase(55, WuHS, WuEB, of(2038, FEBRUARY, 4), of(2039, JANUARY, 23)),
			SexagenaryYearTestCase(56, Ji, Wei, of(2039, JANUARY, 24), of(2040, FEBRUARY, 11)),
			SexagenaryYearTestCase(57, Geng, Shen, of(2040, FEBRUARY, 12), of(2041, JANUARY, 31)),
			SexagenaryYearTestCase(58, Xin, You, of(2041, FEBRUARY, 1), of(2042, JANUARY, 21)),
			SexagenaryYearTestCase(59, Ren, Xu, of(2042, JANUARY, 22), of(2043, FEBRUARY, 9)),
			SexagenaryYearTestCase(60, Gui, Hai, of(2043, FEBRUARY, 10), of(2044, JANUARY, 29)),
		)

		/**
		 * Solar new years for [CYCLE_1984] to reduce duplication.
		 * Source: 2019-2026 based on https://www.hko.gov.hk/en/gts/astronomy/Solar_Term.htm (and archived versions), times converted to UTC.
		 * Source: 2019-2026 confirmed at https://en.wikipedia.org/wiki/Lichun (https://zh.wikipedia.org/zh/%E7%AB%8B%E6%98%A5)
		 * Source: 2001-2030 based on https://en.wikipedia.org/wiki/Lichun
		 */
		@Suppress("ObjectPropertyName", "detekt.ObjectPropertyNaming")
		private object NY {

			val _2001: LocalDateTime = LocalDateTime.of(2001, FEBRUARY, 3, 18, 28)
			val _2002: LocalDateTime = LocalDateTime.of(2002, FEBRUARY, 4, 0, 24)
			val _2003: LocalDateTime = LocalDateTime.of(2003, FEBRUARY, 4, 6, 5)
			val _2004: LocalDateTime = LocalDateTime.of(2004, FEBRUARY, 4, 11, 56)
			val _2005: LocalDateTime = LocalDateTime.of(2005, FEBRUARY, 3, 17, 43)
			val _2006: LocalDateTime = LocalDateTime.of(2006, FEBRUARY, 3, 23, 27)
			val _2007: LocalDateTime = LocalDateTime.of(2007, FEBRUARY, 4, 5, 18)
			val _2008: LocalDateTime = LocalDateTime.of(2008, FEBRUARY, 4, 11, 0)
			val _2009: LocalDateTime = LocalDateTime.of(2009, FEBRUARY, 3, 16, 49)
			val _2010: LocalDateTime = LocalDateTime.of(2010, FEBRUARY, 3, 22, 47)
			val _2011: LocalDateTime = LocalDateTime.of(2011, FEBRUARY, 4, 4, 32)
			val _2012: LocalDateTime = LocalDateTime.of(2012, FEBRUARY, 4, 10, 22)
			val _2013: LocalDateTime = LocalDateTime.of(2013, FEBRUARY, 3, 16, 13)
			val _2014: LocalDateTime = LocalDateTime.of(2014, FEBRUARY, 3, 22, 3)
			val _2015: LocalDateTime = LocalDateTime.of(2015, FEBRUARY, 4, 3, 58)
			val _2016: LocalDateTime = LocalDateTime.of(2016, FEBRUARY, 4, 9, 46)
			val _2017: LocalDateTime = LocalDateTime.of(2017, FEBRUARY, 3, 15, 34)
			val _2018: LocalDateTime = LocalDateTime.of(2018, FEBRUARY, 3, 21, 28)
			val _2019: LocalDateTime = LocalDateTime.of(2019, FEBRUARY, 4, 3, 14)
			val _2020: LocalDateTime = LocalDateTime.of(2020, FEBRUARY, 4, 9, 3)
			val _2021: LocalDateTime = LocalDateTime.of(2021, FEBRUARY, 3, 14, 58) // 49 in hk
			val _2022: LocalDateTime = LocalDateTime.of(2022, FEBRUARY, 3, 20, 50) // 51 in hk
			val _2023: LocalDateTime = LocalDateTime.of(2023, FEBRUARY, 4, 2, 42)
			val _2024: LocalDateTime = LocalDateTime.of(2024, FEBRUARY, 4, 8, 27)
			val _2025: LocalDateTime = LocalDateTime.of(2025, FEBRUARY, 3, 14, 10)
			val _2026: LocalDateTime = LocalDateTime.of(2026, FEBRUARY, 3, 20, 2)
			val _2027: LocalDateTime = LocalDateTime.of(2027, FEBRUARY, 4, 1, 46)
			val _2028: LocalDateTime = LocalDateTime.of(2028, FEBRUARY, 4, 7, 31)
			val _2029: LocalDateTime = LocalDateTime.of(2029, FEBRUARY, 3, 13, 20)
			val _2030: LocalDateTime = LocalDateTime.of(2030, FEBRUARY, 3, 19, 8)
		}

		val ALL_KNOWN_CYCLES: List<List<SexagenaryYearTestCase>> = listOf(
			CYCLE_1804,
			CYCLE_1864,
			CYCLE_1924,
			CYCLE_1984,
		)
	}
}
