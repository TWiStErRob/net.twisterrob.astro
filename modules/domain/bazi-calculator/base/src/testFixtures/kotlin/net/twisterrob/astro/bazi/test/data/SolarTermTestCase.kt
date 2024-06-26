package net.twisterrob.astro.bazi.test.data

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
import java.time.LocalDateTime
import java.time.LocalDateTime.of
import java.time.Month.APRIL
import java.time.Month.AUGUST
import java.time.Month.DECEMBER
import java.time.Month.FEBRUARY
import java.time.Month.JANUARY
import java.time.Month.JULY
import java.time.Month.JUNE
import java.time.Month.MARCH
import java.time.Month.MAY
import java.time.Month.NOVEMBER
import java.time.Month.OCTOBER
import java.time.Month.SEPTEMBER
import java.time.temporal.ChronoUnit
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS

/**
 * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
 */
class SolarTermTestCase(
	val stem: HeavenlyStem,
	val branch: EarthlyBranch,
	val startTime: LocalDateTime,
	val endTime: LocalDateTime,
) {

	val monthPillar: BaZi.Pillar
		get() = BaZi.Pillar(stem, branch)

	val midTime: LocalDateTime
		get() = startTime.plusDays(ChronoUnit.DAYS.between(startTime, endTime) / 2)

	@Suppress("detekt.NamedArguments", "detekt.MaxLineLength")
	companion object {

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal01.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal02.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal03.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal04.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal05.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal06.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal07.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal08.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal09.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal10.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal11.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2018/files/2018cal12.pdf
		 * Source: https://www.hko.gov.hk/en/gts/astron2019/files/2019cal01.pdf
		 */
		val SOLAR_TERMS_2018: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Gui, Chou, of(2018, JANUARY, 5, 9, 49), of(2018, JANUARY, 20, 3, 9)),
			SolarTermTestCase(Gui, Chou, of(2018, JANUARY, 20, 3, 9), of(2018, FEBRUARY, 3, 21, 28)),
			SolarTermTestCase(Jia, Yin, of(2018, FEBRUARY, 3, 21, 28), of(2018, FEBRUARY, 18, 17, 18)),
			SolarTermTestCase(Jia, Yin, of(2018, FEBRUARY, 18, 17, 18), of(2018, MARCH, 5, 15, 28)),
			SolarTermTestCase(Yi, Mao, of(2018, MARCH, 5, 15, 28), of(2018, MARCH, 20, 16, 15)),
			SolarTermTestCase(Yi, Mao, of(2018, MARCH, 20, 16, 15), of(2018, APRIL, 4, 20, 13)),
			SolarTermTestCase(Bing, Chen, of(2018, APRIL, 4, 20, 13), of(2018, APRIL, 20, 3, 13)),
			SolarTermTestCase(Bing, Chen, of(2018, APRIL, 20, 3, 13), of(2018, MAY, 5, 13, 25)),
			SolarTermTestCase(Ding, Si, of(2018, MAY, 5, 13, 25), of(2018, MAY, 21, 2, 15)),
			SolarTermTestCase(Ding, Si, of(2018, MAY, 21, 2, 15), of(2018, JUNE, 5, 17, 29)),
			SolarTermTestCase(WuHS, WuEB, of(2018, JUNE, 5, 17, 29), of(2018, JUNE, 21, 10, 7)),
			SolarTermTestCase(WuHS, WuEB, of(2018, JUNE, 21, 10, 7), of(2018, JULY, 7, 3, 42)),
			SolarTermTestCase(Ji, Wei, of(2018, JULY, 7, 3, 42), of(2018, JULY, 22, 21, 0)),
			SolarTermTestCase(Ji, Wei, of(2018, JULY, 22, 21, 0), of(2018, AUGUST, 7, 13, 31)),
			SolarTermTestCase(Geng, Shen, of(2018, AUGUST, 7, 13, 31), of(2018, AUGUST, 23, 4, 9)),
			SolarTermTestCase(Geng, Shen, of(2018, AUGUST, 23, 4, 9), of(2018, SEPTEMBER, 7, 16, 30)),
			SolarTermTestCase(Xin, You, of(2018, SEPTEMBER, 7, 16, 30), of(2018, SEPTEMBER, 23, 1, 54)),
			SolarTermTestCase(Xin, You, of(2018, SEPTEMBER, 23, 1, 54), of(2018, OCTOBER, 8, 8, 15)),
			SolarTermTestCase(Ren, Xu, of(2018, OCTOBER, 8, 8, 15), of(2018, OCTOBER, 23, 11, 22)),
			SolarTermTestCase(Ren, Xu, of(2018, OCTOBER, 23, 11, 22), of(2018, NOVEMBER, 7, 11, 32)),
			SolarTermTestCase(Gui, Hai, of(2018, NOVEMBER, 7, 11, 32), of(2018, NOVEMBER, 22, 9, 1)),
			SolarTermTestCase(Gui, Hai, of(2018, NOVEMBER, 22, 9, 1), of(2018, DECEMBER, 7, 4, 26)),
			SolarTermTestCase(Jia, Zi, of(2018, DECEMBER, 7, 4, 26), of(2018, DECEMBER, 21, 22, 23)),
			SolarTermTestCase(Jia, Zi, of(2018, DECEMBER, 21, 22, 23), of(2019, JANUARY, 5, 15, 39)),
		)

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2019/files/2019SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astronomy/data/files/24SolarTerms_2019.xml
		 */
		val SOLAR_TERMS_2019: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Yi, Chou, of(2019, JANUARY, 5, 15, 39), of(2019, JANUARY, 20, 9, 0)),
			SolarTermTestCase(Yi, Chou, of(2019, JANUARY, 20, 9, 0), of(2019, FEBRUARY, 4, 3, 14)),
			SolarTermTestCase(Bing, Yin, of(2019, FEBRUARY, 4, 3, 14), of(2019, FEBRUARY, 18, 23, 4)),
			SolarTermTestCase(Bing, Yin, of(2019, FEBRUARY, 18, 23, 4), of(2019, MARCH, 5, 21, 10)),
			SolarTermTestCase(Ding, Mao, of(2019, MARCH, 5, 21, 10), of(2019, MARCH, 20, 21, 58)),
			SolarTermTestCase(Ding, Mao, of(2019, MARCH, 20, 21, 58), of(2019, APRIL, 5, 1, 51)),
			SolarTermTestCase(WuHS, Chen, of(2019, APRIL, 5, 1, 51), of(2019, APRIL, 20, 8, 55)),
			SolarTermTestCase(WuHS, Chen, of(2019, APRIL, 20, 8, 55), of(2019, MAY, 5, 19, 3)),
			SolarTermTestCase(Ji, Si, of(2019, MAY, 5, 19, 3), of(2019, MAY, 21, 7, 59)),
			SolarTermTestCase(Ji, Si, of(2019, MAY, 21, 7, 59), of(2019, JUNE, 5, 23, 6)),
			SolarTermTestCase(Geng, WuEB, of(2019, JUNE, 5, 23, 6), of(2019, JUNE, 21, 15, 54)),
			SolarTermTestCase(Geng, WuEB, of(2019, JUNE, 21, 15, 54), of(2019, JULY, 7, 9, 21)),
			SolarTermTestCase(Xin, Wei, of(2019, JULY, 7, 9, 21), of(2019, JULY, 23, 2, 50)),
			SolarTermTestCase(Xin, Wei, of(2019, JULY, 23, 2, 50), of(2019, AUGUST, 7, 19, 13)),
			SolarTermTestCase(Ren, Shen, of(2019, AUGUST, 7, 19, 13), of(2019, AUGUST, 23, 10, 2)),
			SolarTermTestCase(Ren, Shen, of(2019, AUGUST, 23, 10, 2), of(2019, SEPTEMBER, 7, 22, 17)),
			SolarTermTestCase(Gui, You, of(2019, SEPTEMBER, 7, 22, 17), of(2019, SEPTEMBER, 23, 7, 50)),
			SolarTermTestCase(Gui, You, of(2019, SEPTEMBER, 23, 7, 50), of(2019, OCTOBER, 8, 14, 6)),
			SolarTermTestCase(Jia, Xu, of(2019, OCTOBER, 8, 14, 6), of(2019, OCTOBER, 23, 17, 20)),
			SolarTermTestCase(Jia, Xu, of(2019, OCTOBER, 23, 17, 20), of(2019, NOVEMBER, 7, 17, 24)),
			SolarTermTestCase(Yi, Hai, of(2019, NOVEMBER, 7, 17, 24), of(2019, NOVEMBER, 22, 14, 59)),
			SolarTermTestCase(Yi, Hai, of(2019, NOVEMBER, 22, 14, 59), of(2019, DECEMBER, 7, 10, 18)),
			SolarTermTestCase(Bing, Zi, of(2019, DECEMBER, 7, 10, 18), of(2019, DECEMBER, 22, 4, 19)),
			SolarTermTestCase(Bing, Zi, of(2019, DECEMBER, 22, 4, 19), of(2020, JANUARY, 5, 21, 30)),
		)

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2020/files/2020SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astronomy/data/files/24SolarTerms_2020.xml
		 */
		val SOLAR_TERMS_2020: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Ding, Chou, of(2020, JANUARY, 5, 21, 30), of(2020, JANUARY, 20, 14, 55)),
			SolarTermTestCase(Ding, Chou, of(2020, JANUARY, 20, 14, 55), of(2020, FEBRUARY, 4, 9, 3)),
			SolarTermTestCase(WuHS, Yin, of(2020, FEBRUARY, 4, 9, 3), of(2020, FEBRUARY, 19, 4, 57)),
			SolarTermTestCase(WuHS, Yin, of(2020, FEBRUARY, 19, 4, 57), of(2020, MARCH, 5, 2, 57)),
			SolarTermTestCase(Ji, Mao, of(2020, MARCH, 5, 2, 57), of(2020, MARCH, 20, 3, 50)),
			SolarTermTestCase(Ji, Mao, of(2020, MARCH, 20, 3, 50), of(2020, APRIL, 4, 7, 38)),
			SolarTermTestCase(Geng, Chen, of(2020, APRIL, 4, 7, 38), of(2020, APRIL, 19, 14, 45)),
			SolarTermTestCase(Geng, Chen, of(2020, APRIL, 19, 14, 45), of(2020, MAY, 5, 0, 51)),
			SolarTermTestCase(Xin, Si, of(2020, MAY, 5, 0, 51), of(2020, MAY, 20, 13, 49)),
			SolarTermTestCase(Xin, Si, of(2020, MAY, 20, 13, 49), of(2020, JUNE, 5, 4, 58)),
			SolarTermTestCase(Ren, WuEB, of(2020, JUNE, 5, 4, 58), of(2020, JUNE, 20, 21, 44)),
			SolarTermTestCase(Ren, WuEB, of(2020, JUNE, 20, 21, 44), of(2020, JULY, 6, 15, 14)),
			SolarTermTestCase(Gui, Wei, of(2020, JULY, 6, 15, 14), of(2020, JULY, 22, 8, 37)),
			SolarTermTestCase(Gui, Wei, of(2020, JULY, 22, 8, 37), of(2020, AUGUST, 7, 1, 6)),
			SolarTermTestCase(Jia, Shen, of(2020, AUGUST, 7, 1, 6), of(2020, AUGUST, 22, 15, 45)),
			SolarTermTestCase(Jia, Shen, of(2020, AUGUST, 22, 15, 45), of(2020, SEPTEMBER, 7, 4, 8)),
			SolarTermTestCase(Yi, You, of(2020, SEPTEMBER, 7, 4, 8), of(2020, SEPTEMBER, 22, 13, 31)),
			SolarTermTestCase(Yi, You, of(2020, SEPTEMBER, 22, 13, 31), of(2020, OCTOBER, 7, 19, 55)),
			SolarTermTestCase(Bing, Xu, of(2020, OCTOBER, 7, 19, 55), of(2020, OCTOBER, 22, 23, 0)),
			SolarTermTestCase(Bing, Xu, of(2020, OCTOBER, 22, 23, 0), of(2020, NOVEMBER, 6, 23, 14)),
			SolarTermTestCase(Ding, Hai, of(2020, NOVEMBER, 6, 23, 14), of(2020, NOVEMBER, 21, 20, 40)),
			SolarTermTestCase(Ding, Hai, of(2020, NOVEMBER, 21, 20, 40), of(2020, DECEMBER, 6, 16, 9)),
			SolarTermTestCase(WuHS, Zi, of(2020, DECEMBER, 6, 16, 9), of(2020, DECEMBER, 21, 10, 2)),
			SolarTermTestCase(WuHS, Zi, of(2020, DECEMBER, 21, 10, 2), of(2021, JANUARY, 5, 3, 23)),
		)

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2021/files/2021SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astronomy/data/files/24SolarTerms_2021.xml
		 */
		val SOLAR_TERMS_2021: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Ji, Chou, of(2021, JANUARY, 5, 3, 23), of(2021, JANUARY, 19, 20, 40)),
			SolarTermTestCase(Ji, Chou, of(2021, JANUARY, 19, 20, 40), of(2021, FEBRUARY, 3, 14, 59)),
			SolarTermTestCase(Geng, Yin, of(2021, FEBRUARY, 3, 14, 59), of(2021, FEBRUARY, 18, 10, 44)),
			SolarTermTestCase(Geng, Yin, of(2021, FEBRUARY, 18, 10, 44), of(2021, MARCH, 5, 8, 54)),
			SolarTermTestCase(Xin, Mao, of(2021, MARCH, 5, 8, 54), of(2021, MARCH, 20, 9, 37)),
			SolarTermTestCase(Xin, Mao, of(2021, MARCH, 20, 9, 37), of(2021, APRIL, 4, 13, 35)),
			SolarTermTestCase(Ren, Chen, of(2021, APRIL, 4, 13, 35), of(2021, APRIL, 19, 20, 33)),
			SolarTermTestCase(Ren, Chen, of(2021, APRIL, 19, 20, 33), of(2021, MAY, 5, 6, 47)),
			SolarTermTestCase(Gui, Si, of(2021, MAY, 5, 6, 47), of(2021, MAY, 20, 19, 37)),
			SolarTermTestCase(Gui, Si, of(2021, MAY, 20, 19, 37), of(2021, JUNE, 5, 10, 52)),
			SolarTermTestCase(Jia, WuEB, of(2021, JUNE, 5, 10, 52), of(2021, JUNE, 21, 3, 32)),
			SolarTermTestCase(Jia, WuEB, of(2021, JUNE, 21, 3, 32), of(2021, JULY, 6, 21, 5)),
			SolarTermTestCase(Yi, Wei, of(2021, JULY, 6, 21, 5), of(2021, JULY, 22, 14, 26)),
			SolarTermTestCase(Yi, Wei, of(2021, JULY, 22, 14, 26), of(2021, AUGUST, 7, 6, 54)),
			SolarTermTestCase(Bing, Shen, of(2021, AUGUST, 7, 6, 54), of(2021, AUGUST, 22, 21, 35)),
			SolarTermTestCase(Bing, Shen, of(2021, AUGUST, 22, 21, 35), of(2021, SEPTEMBER, 7, 9, 53)),
			SolarTermTestCase(Ding, You, of(2021, SEPTEMBER, 7, 9, 53), of(2021, SEPTEMBER, 22, 19, 21)),
			SolarTermTestCase(Ding, You, of(2021, SEPTEMBER, 22, 19, 21), of(2021, OCTOBER, 8, 1, 39)),
			SolarTermTestCase(WuHS, Xu, of(2021, OCTOBER, 8, 1, 39), of(2021, OCTOBER, 23, 4, 51)),
			SolarTermTestCase(WuHS, Xu, of(2021, OCTOBER, 23, 4, 51), of(2021, NOVEMBER, 7, 4, 59)),
			SolarTermTestCase(Ji, Hai, of(2021, NOVEMBER, 7, 4, 59), of(2021, NOVEMBER, 22, 2, 34)),
			SolarTermTestCase(Ji, Hai, of(2021, NOVEMBER, 22, 2, 34), of(2021, DECEMBER, 6, 21, 57)),
			SolarTermTestCase(Geng, Zi, of(2021, DECEMBER, 6, 21, 57), of(2021, DECEMBER, 21, 15, 59)),
			SolarTermTestCase(Geng, Zi, of(2021, DECEMBER, 21, 15, 59), of(2022, JANUARY, 5, 9, 14)),
		)

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2022/files/2022SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astronomy/data/files/24SolarTerms_2022.xml
		 */
		val SOLAR_TERMS_2022: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Xin, Chou, of(2022, JANUARY, 5, 9, 14), of(2022, JANUARY, 20, 2, 39)),
			SolarTermTestCase(Xin, Chou, of(2022, JANUARY, 20, 2, 39), of(2022, FEBRUARY, 3, 20, 51)),
			SolarTermTestCase(Ren, Yin, of(2022, FEBRUARY, 3, 20, 51), of(2022, FEBRUARY, 18, 16, 43)),
			SolarTermTestCase(Ren, Yin, of(2022, FEBRUARY, 18, 16, 43), of(2022, MARCH, 5, 14, 44)),
			SolarTermTestCase(Gui, Mao, of(2022, MARCH, 5, 14, 44), of(2022, MARCH, 20, 15, 33)),
			SolarTermTestCase(Gui, Mao, of(2022, MARCH, 20, 15, 33), of(2022, APRIL, 4, 19, 20)),
			SolarTermTestCase(Jia, Chen, of(2022, APRIL, 4, 19, 20), of(2022, APRIL, 20, 2, 24)),
			SolarTermTestCase(Jia, Chen, of(2022, APRIL, 20, 2, 24), of(2022, MAY, 5, 12, 26)),
			SolarTermTestCase(Yi, Si, of(2022, MAY, 5, 12, 26), of(2022, MAY, 21, 1, 23)),
			SolarTermTestCase(Yi, Si, of(2022, MAY, 21, 1, 23), of(2022, JUNE, 5, 16, 26)),
			SolarTermTestCase(Bing, WuEB, of(2022, JUNE, 5, 16, 26), of(2022, JUNE, 21, 9, 14)),
			SolarTermTestCase(Bing, WuEB, of(2022, JUNE, 21, 9, 14), of(2022, JULY, 7, 2, 38)),
			SolarTermTestCase(Ding, Wei, of(2022, JULY, 7, 2, 38), of(2022, JULY, 22, 20, 7)),
			SolarTermTestCase(Ding, Wei, of(2022, JULY, 22, 20, 7), of(2022, AUGUST, 7, 12, 29)),
			SolarTermTestCase(WuHS, Shen, of(2022, AUGUST, 7, 12, 29), of(2022, AUGUST, 23, 3, 16)),
			SolarTermTestCase(WuHS, Shen, of(2022, AUGUST, 23, 3, 16), of(2022, SEPTEMBER, 7, 15, 32)),
			SolarTermTestCase(Ji, You, of(2022, SEPTEMBER, 7, 15, 32), of(2022, SEPTEMBER, 23, 1, 4)),
			SolarTermTestCase(Ji, You, of(2022, SEPTEMBER, 23, 1, 4), of(2022, OCTOBER, 8, 7, 22)),
			SolarTermTestCase(Geng, Xu, of(2022, OCTOBER, 8, 7, 22), of(2022, OCTOBER, 23, 10, 36)),
			SolarTermTestCase(Geng, Xu, of(2022, OCTOBER, 23, 10, 36), of(2022, NOVEMBER, 7, 10, 45)),
			SolarTermTestCase(Xin, Hai, of(2022, NOVEMBER, 7, 10, 45), of(2022, NOVEMBER, 22, 8, 20)),
			SolarTermTestCase(Xin, Hai, of(2022, NOVEMBER, 22, 8, 20), of(2022, DECEMBER, 7, 3, 46)),
			SolarTermTestCase(Ren, Zi, of(2022, DECEMBER, 7, 3, 46), of(2022, DECEMBER, 21, 21, 48)),
			SolarTermTestCase(Ren, Zi, of(2022, DECEMBER, 21, 21, 48), of(2023, JANUARY, 5, 15, 5)),
		)

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2023/files/2023SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astronomy/data/files/24SolarTerms_2023.xml
		 */
		val SOLAR_TERMS_2023: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Gui, Chou, of(2023, JANUARY, 5, 15, 5), of(2023, JANUARY, 20, 8, 30)),
			SolarTermTestCase(Gui, Chou, of(2023, JANUARY, 20, 8, 30), of(2023, FEBRUARY, 4, 2, 43)),
			SolarTermTestCase(Jia, Yin, of(2023, FEBRUARY, 4, 2, 43), of(2023, FEBRUARY, 18, 22, 34)),
			SolarTermTestCase(Jia, Yin, of(2023, FEBRUARY, 18, 22, 34), of(2023, MARCH, 5, 20, 36)),
			SolarTermTestCase(Yi, Mao, of(2023, MARCH, 5, 20, 36), of(2023, MARCH, 20, 21, 24)),
			SolarTermTestCase(Yi, Mao, of(2023, MARCH, 20, 21, 24), of(2023, APRIL, 5, 1, 13)),
			SolarTermTestCase(Bing, Chen, of(2023, APRIL, 5, 1, 13), of(2023, APRIL, 20, 8, 14)),
			SolarTermTestCase(Bing, Chen, of(2023, APRIL, 20, 8, 14), of(2023, MAY, 5, 18, 19)),
			SolarTermTestCase(Ding, Si, of(2023, MAY, 5, 18, 19), of(2023, MAY, 21, 7, 9)),
			SolarTermTestCase(Ding, Si, of(2023, MAY, 21, 7, 9), of(2023, JUNE, 5, 22, 18)),
			SolarTermTestCase(WuHS, WuEB, of(2023, JUNE, 5, 22, 18), of(2023, JUNE, 21, 14, 58)),
			SolarTermTestCase(WuHS, WuEB, of(2023, JUNE, 21, 14, 58), of(2023, JULY, 7, 8, 31)),
			SolarTermTestCase(Ji, Wei, of(2023, JULY, 7, 8, 31), of(2023, JULY, 23, 1, 50)),
			SolarTermTestCase(Ji, Wei, of(2023, JULY, 23, 1, 50), of(2023, AUGUST, 7, 18, 23)),
			SolarTermTestCase(Geng, Shen, of(2023, AUGUST, 7, 18, 23), of(2023, AUGUST, 23, 9, 1)),
			SolarTermTestCase(Geng, Shen, of(2023, AUGUST, 23, 9, 1), of(2023, SEPTEMBER, 7, 21, 27)),
			SolarTermTestCase(Xin, You, of(2023, SEPTEMBER, 7, 21, 27), of(2023, SEPTEMBER, 23, 6, 50)),
			SolarTermTestCase(Xin, You, of(2023, SEPTEMBER, 23, 6, 50), of(2023, OCTOBER, 8, 13, 16)),
			SolarTermTestCase(Ren, Xu, of(2023, OCTOBER, 8, 13, 16), of(2023, OCTOBER, 23, 16, 21)),
			SolarTermTestCase(Ren, Xu, of(2023, OCTOBER, 23, 16, 21), of(2023, NOVEMBER, 7, 16, 36)),
			SolarTermTestCase(Gui, Hai, of(2023, NOVEMBER, 7, 16, 36), of(2023, NOVEMBER, 22, 14, 3)),
			SolarTermTestCase(Gui, Hai, of(2023, NOVEMBER, 22, 14, 3), of(2023, DECEMBER, 7, 9, 33)),
			SolarTermTestCase(Jia, Zi, of(2023, DECEMBER, 7, 9, 33), of(2023, DECEMBER, 22, 3, 27)),
			SolarTermTestCase(Jia, Zi, of(2023, DECEMBER, 22, 3, 27), of(2024, JANUARY, 5, 20, 49)),
		)

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2024/files/2024SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astronomy/data/files/24SolarTerms_2024.xml
		 */
		val SOLAR_TERMS_2024: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Yi, Chou, of(2024, JANUARY, 5, 20, 49), of(2024, JANUARY, 20, 14, 7)),
			SolarTermTestCase(Yi, Chou, of(2024, JANUARY, 20, 14, 7), of(2024, FEBRUARY, 4, 8, 27)),
			SolarTermTestCase(Bing, Yin, of(2024, FEBRUARY, 4, 8, 27), of(2024, FEBRUARY, 19, 4, 13)),
			SolarTermTestCase(Bing, Yin, of(2024, FEBRUARY, 19, 4, 13), of(2024, MARCH, 5, 2, 23)),
			SolarTermTestCase(Ding, Mao, of(2024, MARCH, 5, 2, 23), of(2024, MARCH, 20, 3, 6)),
			SolarTermTestCase(Ding, Mao, of(2024, MARCH, 20, 3, 6), of(2024, APRIL, 4, 7, 2)),
			SolarTermTestCase(WuHS, Chen, of(2024, APRIL, 4, 7, 2), of(2024, APRIL, 19, 14, 0)),
			SolarTermTestCase(WuHS, Chen, of(2024, APRIL, 19, 14, 0), of(2024, MAY, 5, 0, 10)),
			SolarTermTestCase(Ji, Si, of(2024, MAY, 5, 0, 10), of(2024, MAY, 20, 13, 0)),
			SolarTermTestCase(Ji, Si, of(2024, MAY, 20, 13, 0), of(2024, JUNE, 5, 4, 10)),
			SolarTermTestCase(Geng, WuEB, of(2024, JUNE, 5, 4, 10), of(2024, JUNE, 20, 20, 51)),
			SolarTermTestCase(Geng, WuEB, of(2024, JUNE, 20, 20, 51), of(2024, JULY, 6, 14, 20)),
			SolarTermTestCase(Xin, Wei, of(2024, JULY, 6, 14, 20), of(2024, JULY, 22, 7, 44)),
			SolarTermTestCase(Xin, Wei, of(2024, JULY, 22, 7, 44), of(2024, AUGUST, 7, 0, 9)),
			SolarTermTestCase(Ren, Shen, of(2024, AUGUST, 7, 0, 9), of(2024, AUGUST, 22, 14, 55)),
			SolarTermTestCase(Ren, Shen, of(2024, AUGUST, 22, 14, 55), of(2024, SEPTEMBER, 7, 3, 11)),
			SolarTermTestCase(Gui, You, of(2024, SEPTEMBER, 7, 3, 11), of(2024, SEPTEMBER, 22, 12, 44)),
			SolarTermTestCase(Gui, You, of(2024, SEPTEMBER, 22, 12, 44), of(2024, OCTOBER, 7, 19, 0)),
			SolarTermTestCase(Jia, Xu, of(2024, OCTOBER, 7, 19, 0), of(2024, OCTOBER, 22, 22, 15)),
			SolarTermTestCase(Jia, Xu, of(2024, OCTOBER, 22, 22, 15), of(2024, NOVEMBER, 6, 22, 20)),
			SolarTermTestCase(Yi, Hai, of(2024, NOVEMBER, 6, 22, 20), of(2024, NOVEMBER, 21, 19, 56)),
			SolarTermTestCase(Yi, Hai, of(2024, NOVEMBER, 21, 19, 56), of(2024, DECEMBER, 6, 15, 17)),
			SolarTermTestCase(Bing, Zi, of(2024, DECEMBER, 6, 15, 17), of(2024, DECEMBER, 21, 9, 21)),
			SolarTermTestCase(Bing, Zi, of(2024, DECEMBER, 21, 9, 21), of(2025, JANUARY, 5, 2, 33)),
		)

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2025/files/2025SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astronomy/data/files/24SolarTerms_2025.xml
		 */
		val SOLAR_TERMS_2025: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Ding, Chou, of(2025, JANUARY, 5, 2, 33), of(2025, JANUARY, 19, 20, 0)),
			SolarTermTestCase(Ding, Chou, of(2025, JANUARY, 19, 20, 0), of(2025, FEBRUARY, 3, 14, 10)),
			SolarTermTestCase(WuHS, Yin, of(2025, FEBRUARY, 3, 14, 10), of(2025, FEBRUARY, 18, 10, 7)),
			SolarTermTestCase(WuHS, Yin, of(2025, FEBRUARY, 18, 10, 7), of(2025, MARCH, 5, 8, 7)),
			SolarTermTestCase(Ji, Mao, of(2025, MARCH, 5, 8, 7), of(2025, MARCH, 20, 9, 1)),
			SolarTermTestCase(Ji, Mao, of(2025, MARCH, 20, 9, 1), of(2025, APRIL, 4, 12, 48)),
			SolarTermTestCase(Geng, Chen, of(2025, APRIL, 4, 12, 48), of(2025, APRIL, 19, 19, 56)),
			SolarTermTestCase(Geng, Chen, of(2025, APRIL, 19, 19, 56), of(2025, MAY, 5, 5, 57)),
			SolarTermTestCase(Xin, Si, of(2025, MAY, 5, 5, 57), of(2025, MAY, 20, 18, 55)),
			SolarTermTestCase(Xin, Si, of(2025, MAY, 20, 18, 55), of(2025, JUNE, 5, 9, 56)),
			SolarTermTestCase(Ren, WuEB, of(2025, JUNE, 5, 9, 56), of(2025, JUNE, 21, 2, 42)),
			SolarTermTestCase(Ren, WuEB, of(2025, JUNE, 21, 2, 42), of(2025, JULY, 6, 20, 5)),
			SolarTermTestCase(Gui, Wei, of(2025, JULY, 6, 20, 5), of(2025, JULY, 22, 13, 29)),
			SolarTermTestCase(Gui, Wei, of(2025, JULY, 22, 13, 29), of(2025, AUGUST, 7, 5, 52)),
			SolarTermTestCase(Jia, Shen, of(2025, AUGUST, 7, 5, 52), of(2025, AUGUST, 22, 20, 34)),
			SolarTermTestCase(Jia, Shen, of(2025, AUGUST, 22, 20, 34), of(2025, SEPTEMBER, 7, 8, 52)),
			SolarTermTestCase(Yi, You, of(2025, SEPTEMBER, 7, 8, 52), of(2025, SEPTEMBER, 22, 18, 19)),
			SolarTermTestCase(Yi, You, of(2025, SEPTEMBER, 22, 18, 19), of(2025, OCTOBER, 8, 0, 41)),
			SolarTermTestCase(Bing, Xu, of(2025, OCTOBER, 8, 0, 41), of(2025, OCTOBER, 23, 3, 51)),
			SolarTermTestCase(Bing, Xu, of(2025, OCTOBER, 23, 3, 51), of(2025, NOVEMBER, 7, 4, 4)),
			SolarTermTestCase(Ding, Hai, of(2025, NOVEMBER, 7, 4, 4), of(2025, NOVEMBER, 22, 1, 36)),
			SolarTermTestCase(Ding, Hai, of(2025, NOVEMBER, 22, 1, 36), of(2025, DECEMBER, 6, 21, 5)),
			SolarTermTestCase(WuHS, Zi, of(2025, DECEMBER, 6, 21, 5), of(2025, DECEMBER, 21, 15, 3)),
			SolarTermTestCase(WuHS, Zi, of(2025, DECEMBER, 21, 15, 3), of(2026, JANUARY, 5, 8, 23)),
		)

		/**
		 * Source times are in Hong Kong time, converted to UTC.
		 * Source: https://www.hko.gov.hk/en/gts/astron2026/files/2026SolarTerms24.pdf (only minors)
		 * Source: https://www.hko.gov.hk/en/gts/astronomy/data/files/24SolarTerms_2026.xml
		 */
		val SOLAR_TERMS_2026: List<SolarTermTestCase> = listOf(
			SolarTermTestCase(Ji, Chou, of(2026, JANUARY, 5, 8, 23), of(2026, JANUARY, 20, 1, 45)),
			SolarTermTestCase(Ji, Chou, of(2026, JANUARY, 20, 1, 45), of(2026, FEBRUARY, 3, 20, 2)),
			SolarTermTestCase(Geng, Yin, of(2026, FEBRUARY, 3, 20, 2), of(2026, FEBRUARY, 18, 15, 52)),
			SolarTermTestCase(Geng, Yin, of(2026, FEBRUARY, 18, 15, 52), of(2026, MARCH, 5, 13, 59)),
			SolarTermTestCase(Xin, Mao, of(2026, MARCH, 5, 13, 59), of(2026, MARCH, 20, 14, 46)),
			SolarTermTestCase(Xin, Mao, of(2026, MARCH, 20, 14, 46), of(2026, APRIL, 4, 18, 40)),
			SolarTermTestCase(Ren, Chen, of(2026, APRIL, 4, 18, 40), of(2026, APRIL, 20, 1, 39)),
			SolarTermTestCase(Ren, Chen, of(2026, APRIL, 20, 1, 39), of(2026, MAY, 5, 11, 49)),
			SolarTermTestCase(Gui, Si, of(2026, MAY, 5, 11, 49), of(2026, MAY, 21, 0, 37)),
			SolarTermTestCase(Gui, Si, of(2026, MAY, 21, 0, 37), of(2026, JUNE, 5, 15, 48)),
			SolarTermTestCase(Jia, WuEB, of(2026, JUNE, 5, 15, 48), of(2026, JUNE, 21, 8, 25)),
			SolarTermTestCase(Jia, WuEB, of(2026, JUNE, 21, 8, 25), of(2026, JULY, 7, 1, 57)),
			SolarTermTestCase(Yi, Wei, of(2026, JULY, 7, 1, 57), of(2026, JULY, 22, 19, 13)),
			SolarTermTestCase(Yi, Wei, of(2026, JULY, 22, 19, 13), of(2026, AUGUST, 7, 11, 43)),
			SolarTermTestCase(Bing, Shen, of(2026, AUGUST, 7, 11, 43), of(2026, AUGUST, 23, 2, 19)),
			SolarTermTestCase(Bing, Shen, of(2026, AUGUST, 23, 2, 19), of(2026, SEPTEMBER, 7, 14, 41)),
			SolarTermTestCase(Ding, You, of(2026, SEPTEMBER, 7, 14, 41), of(2026, SEPTEMBER, 23, 0, 5)),
			SolarTermTestCase(Ding, You, of(2026, SEPTEMBER, 23, 0, 5), of(2026, OCTOBER, 8, 6, 29)),
			SolarTermTestCase(WuHS, Xu, of(2026, OCTOBER, 8, 6, 29), of(2026, OCTOBER, 23, 9, 38)),
			SolarTermTestCase(WuHS, Xu, of(2026, OCTOBER, 23, 9, 38), of(2026, NOVEMBER, 7, 9, 52)),
			SolarTermTestCase(Ji, Hai, of(2026, NOVEMBER, 7, 9, 52), of(2026, NOVEMBER, 22, 7, 23)),
			SolarTermTestCase(Ji, Hai, of(2026, NOVEMBER, 22, 7, 23), of(2026, DECEMBER, 7, 2, 52)),
			SolarTermTestCase(Geng, Zi, of(2026, DECEMBER, 7, 2, 52), of(2026, DECEMBER, 21, 20, 50)),
			// TODO end timing is a guess
			SolarTermTestCase(Geng, Zi, of(2026, DECEMBER, 21, 20, 50), of(2027, JANUARY, 5, 14, 15)),
		)

		val ALL_KNOWN_YEARS: List<List<SolarTermTestCase>> = listOf(
			SOLAR_TERMS_2018,
			SOLAR_TERMS_2019,
			SOLAR_TERMS_2020,
			SOLAR_TERMS_2021,
			SOLAR_TERMS_2022,
			SOLAR_TERMS_2023,
			SOLAR_TERMS_2024,
			SOLAR_TERMS_2025,
			SOLAR_TERMS_2026,
		)
	}
}
