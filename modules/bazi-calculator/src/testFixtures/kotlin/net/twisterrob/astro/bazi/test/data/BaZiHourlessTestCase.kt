package net.twisterrob.astro.bazi.test.data

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
import net.twisterrob.astro.bazi.model.HeavenlyStem.Xin
import net.twisterrob.astro.bazi.model.HeavenlyStem.Yi
import java.time.LocalDate
import java.time.Month
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS

class BaZiHourlessTestCase(
	val name: String,
	val sources: List<String>,
	val date: LocalDate,
	val location: String,
	val bazi: BaZi,
) {

	companion object {
		
		val GREGORIAN_YEAR_TRANSITIONS = listOf(
			BaZiHourlessTestCase(
				name = "1947/1948 new year: before",
				sources = listOf("https://en.wikipedia.org/wiki/1947"),
				date = LocalDate.of(1947, Month.DECEMBER, 31),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Ding, Hai),
					month = BaZi.Pillar(Ren, Zi),
					day = BaZi.Pillar(Jia, Shen),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1947/1948 new year: after",
				sources = listOf("https://en.wikipedia.org/wiki/1948"),
				date = LocalDate.of(1948, Month.JANUARY, 1),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Ding, Hai),
					month = BaZi.Pillar(Ren, Zi),
					day = BaZi.Pillar(Yi, You),
					hour = null,
				),
			),
		)

		val SOLAR_YEAR_TRANSITIONS = listOf(
			BaZiHourlessTestCase(
				// Note: also covered by SexagenaryYearTestCase.CYCLE_1924
				name = "1947 solar new year / 1948 gregorian year",
				sources = emptyList(),
				date = LocalDate.of(1948, Month.JANUARY, 21),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Ding, Hai), // 丁亥
					month = BaZi.Pillar(Gui, Chou), // 癸丑
					day = BaZi.Pillar(Yi, Si), // 乙巳
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1947-48 solar new year: before",
				sources = emptyList(),
				date = LocalDate.of(1948, Month.FEBRUARY, 4),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Ding, Hai),
					month = BaZi.Pillar(Gui, Chou),
					day = BaZi.Pillar(Ji, Wei),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1947-48 solar new year: after",
				sources = emptyList(),
				date = LocalDate.of(1948, Month.FEBRUARY, 5),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(WuHS, Zi), // 戊子
					month = BaZi.Pillar(Jia, Yin), // 甲寅
					day = BaZi.Pillar(Geng, Shen), // 庚申
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1948-49 solar new year: before",
				sources = emptyList(),
				date = LocalDate.of(1949, Month.FEBRUARY, 3),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(WuHS, Zi),
					month = BaZi.Pillar(Yi, Chou),
					day = BaZi.Pillar(Jia, Zi),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1948-49 solar new year: after",
				sources = emptyList(),
				date = LocalDate.of(1949, Month.FEBRUARY, 4),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Ji, Chou),
					month = BaZi.Pillar(Bing, Yin),
					day = BaZi.Pillar(Yi, Chou),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1949-50 solar new year: before",
				sources = emptyList(),
				date = LocalDate.of(1950, Month.FEBRUARY, 4),
				location = "UTC",
				bazi = BaZi(
					// midnight
					year = BaZi.Pillar(Geng, Yin),
					month = BaZi.Pillar(WuHS, Yin),
					day = BaZi.Pillar(Geng, WuEB),
					hour = null,
				),
//				expected = BaZi( // noon
//					year = BaZi.Pillar(Ji, Chou),
//					month = BaZi.Pillar(Ding, Chou),
//					day = BaZi.Pillar(Ji, Si),
//					hour = null,
//				),
			),
			BaZiHourlessTestCase(
				name = "1949-50 solar new year: after",
				sources = emptyList(),
				date = LocalDate.of(1950, Month.FEBRUARY, 5),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Geng, Yin),
					month = BaZi.Pillar(WuHS, Yin),
					day = BaZi.Pillar(Xin, Wei),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1950-51 solar new year: before",
				sources = emptyList(),
				date = LocalDate.of(1951, Month.FEBRUARY, 4),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Geng, Yin),
					month = BaZi.Pillar(Ji, Chou),
					day = BaZi.Pillar(Yi, Hai),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1950-51 solar new year: after",
				sources = emptyList(),
				date = LocalDate.of(1951, Month.FEBRUARY, 5),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Xin, Mao),
					month = BaZi.Pillar(Geng, Yin),
					day = BaZi.Pillar(Bing, Zi),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1951-52 solar new year: before",
				sources = emptyList(),
				date = LocalDate.of(1952, Month.FEBRUARY, 4),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Xin, Mao),
					month = BaZi.Pillar(Xin, Chou),
					day = BaZi.Pillar(Geng, Chen),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1951-52 solar new year: after",
				sources = emptyList(),
				date = LocalDate.of(1952, Month.FEBRUARY, 5),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Ren, Chen),
					month = BaZi.Pillar(Ren, Yin),
					day = BaZi.Pillar(Xin, Si),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1952-53 solar new year: before",
				sources = emptyList(),
				date = LocalDate.of(1953, Month.FEBRUARY, 3),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Ren, Chen),
					month = BaZi.Pillar(Gui, Chou),
					day = BaZi.Pillar(Yi, You),
					hour = null,
				),
			),
			BaZiHourlessTestCase(
				name = "1952-53 solar new year: after",
				sources = emptyList(),
				date = LocalDate.of(1953, Month.FEBRUARY, 4),
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Gui, Si),
					month = BaZi.Pillar(Jia, Yin),
					day = BaZi.Pillar(Bing, Xu),
					hour = null,
				),
			),
		)
		
		val ALL_CASES: List<BaZiHourlessTestCase> = GREGORIAN_YEAR_TRANSITIONS + SOLAR_YEAR_TRANSITIONS
	}
}
