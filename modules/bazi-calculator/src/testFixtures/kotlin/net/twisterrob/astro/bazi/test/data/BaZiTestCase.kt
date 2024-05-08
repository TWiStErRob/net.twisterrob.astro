package net.twisterrob.astro.bazi.test.data

import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch.Chen
import net.twisterrob.astro.bazi.model.EarthlyBranch.Chou
import net.twisterrob.astro.bazi.model.EarthlyBranch.Hai
import net.twisterrob.astro.bazi.model.EarthlyBranch.Mao
import net.twisterrob.astro.bazi.model.EarthlyBranch.Si
import net.twisterrob.astro.bazi.model.EarthlyBranch.Xu
import net.twisterrob.astro.bazi.model.EarthlyBranch.Yin
import net.twisterrob.astro.bazi.model.EarthlyBranch.You
import net.twisterrob.astro.bazi.model.EarthlyBranch.Zi
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ding
import net.twisterrob.astro.bazi.model.HeavenlyStem.Geng
import net.twisterrob.astro.bazi.model.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.model.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ren
import java.time.LocalDateTime
import java.time.Month
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS

class BaZiTestCase(
	val name: String,
	val sources: List<String>,
	val dateTime: LocalDateTime,
	val location: String,
	val bazi: BaZi,
) {

	companion object {

		val CELEBRITIES: List<BaZiTestCase> = listOf(
			BaZiTestCase(
				name = "Bruce Lee",
				sources = listOf(
					"From: Group44",
					// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1940&MO=11&DA=27&HO=8&MI=0&CI=San%20Francisco%2C%20CA%2C%20USA&NA=BL&GE=1&TU=false&CST=1
					"kinaiasztrologia.com confirmed",
				),
				dateTime = LocalDateTime.of(1940, 11, 27, 8, 0),
				location = "San Francisco, CA, USA",
				bazi = BaZi(
					year = BaZi.Pillar(Geng, Chen),
					month = BaZi.Pillar(Ding, Hai),
					day = BaZi.Pillar(Jia, Xu),
					hour = BaZi.Pillar(WuHS, Chen)
				),
			),
			BaZiTestCase(
				name = "Mao Zedong",
				sources = listOf(
					"From: Group44",
					// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1893&MO=12&DA=26&HO=8&MI=0&CI=Shaoshan%2C%20Xiangtan%2C%20Hunan%2C%20China&NA=MZ&GE=1&TU=false&CST=1
					"kinaiasztrologia.com confirmed",
				),
				dateTime = LocalDateTime.of(1893, 12, 26, 8 /*7-9*/, 0),
				location = "Shaoshan, Xiangtan, Hunan, China",
				bazi = BaZi(
					year = BaZi.Pillar(Gui, Si),
					month = BaZi.Pillar(Jia, Zi),
					day = BaZi.Pillar(Ding, You),
					hour = BaZi.Pillar(Jia, Chen)
				),
			),
		)

		val SOLAR_YEAR_TRANSITIONS: List<BaZiTestCase> = listOf(
			BaZiTestCase(
				name = "2022/23 solar new year: before",
				sources = emptyList(),
				// TODO https://github.com/TWiStErRob/net.twisterrob.astro/issues/14
				dateTime = LocalDateTime.of(2023, Month.FEBRUARY, 4, 2, 31, 0), // should be ~2:42
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Ren, Yin),
					month = BaZi.Pillar(Gui, Chou),
					day = BaZi.Pillar(Gui, Si),
					hour = BaZi.Pillar(Gui, Chou),
				),
			),
			BaZiTestCase(
				name = "2022/23 solar new year: after",
				sources = emptyList(),
				// TODO https://github.com/TWiStErRob/net.twisterrob.astro/issues/14
				dateTime = LocalDateTime.of(2023, Month.FEBRUARY, 4, 2, 32, 0), // should be ~2:42
				location = "UTC",
				bazi = BaZi(
					year = BaZi.Pillar(Gui, Mao),
					month = BaZi.Pillar(Jia, Yin),
					day = BaZi.Pillar(Gui, Si),
					hour = BaZi.Pillar(Gui, Chou),
				),
			),
		)

		val ALL_CASES: List<BaZiTestCase> =
			mapOf(
				"Celebrity" to CELEBRITIES,
				"Solar year transitions" to SOLAR_YEAR_TRANSITIONS,
			)
				.flatMap { (name, cases) ->
					cases.map {
						BaZiTestCase(
							name = "${name}: ${it.name}",
							sources = it.sources,
							dateTime = it.dateTime,
							location = it.location,
							bazi = it.bazi,
						)
					}
				}
	}
}
