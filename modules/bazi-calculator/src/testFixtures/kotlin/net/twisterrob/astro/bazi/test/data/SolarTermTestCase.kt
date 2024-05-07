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

	/**
	 * STOPSHIP sources https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
	 */
	companion object {

		val SOLAR_TERMS_2019 = listOf(
			SolarTermTestCase(Yi, Chou, LocalDateTime.of(2019, JANUARY, 5, 15, 39, 0), LocalDateTime.of(2019, JANUARY, 20, 9, 0, 0)),
			SolarTermTestCase(Yi, Chou, LocalDateTime.of(2019, JANUARY, 20, 9, 0, 0), LocalDateTime.of(2019, FEBRUARY, 4, 3, 14, 0)),
			SolarTermTestCase(Bing, Yin, LocalDateTime.of(2019, FEBRUARY, 4, 3, 14, 0), LocalDateTime.of(2019, FEBRUARY, 18, 23, 4, 0)),
			SolarTermTestCase(Bing, Yin, LocalDateTime.of(2019, FEBRUARY, 18, 23, 4, 0), LocalDateTime.of(2019, MARCH, 5, 21, 10, 0)),
			SolarTermTestCase(Ding, Mao, LocalDateTime.of(2019, MARCH, 5, 21, 10, 0), LocalDateTime.of(2019, MARCH, 20, 21, 58, 0)),
			SolarTermTestCase(Ding, Mao, LocalDateTime.of(2019, MARCH, 20, 21, 58, 0), LocalDateTime.of(2019, APRIL, 5, 1, 51, 0)),
			SolarTermTestCase(WuHS, Chen, LocalDateTime.of(2019, APRIL, 5, 1, 51, 0), LocalDateTime.of(2019, APRIL, 20, 8, 55, 0)),
			SolarTermTestCase(WuHS, Chen, LocalDateTime.of(2019, APRIL, 20, 8, 55, 0), LocalDateTime.of(2019, MAY, 5, 19, 3, 0)),
			SolarTermTestCase(Ji, Si, LocalDateTime.of(2019, MAY, 5, 19, 3, 0), LocalDateTime.of(2019, MAY, 21, 7, 59, 0)),
			SolarTermTestCase(Ji, Si, LocalDateTime.of(2019, MAY, 21, 7, 59, 0), LocalDateTime.of(2019, JUNE, 5, 23, 6, 0)),
			SolarTermTestCase(Geng, WuEB, LocalDateTime.of(2019, JUNE, 5, 23, 6, 0), LocalDateTime.of(2019, JUNE, 21, 15, 54, 0)),
			SolarTermTestCase(Geng, WuEB, LocalDateTime.of(2019, JUNE, 21, 15, 54, 0), LocalDateTime.of(2019, JULY, 7, 9, 21, 0)),
			SolarTermTestCase(Xin, Wei, LocalDateTime.of(2019, JULY, 7, 9, 21, 0), LocalDateTime.of(2019, JULY, 23, 2, 50, 0)),
			SolarTermTestCase(Xin, Wei, LocalDateTime.of(2019, JULY, 23, 2, 50, 0), LocalDateTime.of(2019, AUGUST, 7, 19, 13, 0)),
			SolarTermTestCase(Ren, Shen, LocalDateTime.of(2019, AUGUST, 7, 19, 13, 0), LocalDateTime.of(2019, AUGUST, 23, 10, 2, 0)),
			SolarTermTestCase(Ren, Shen, LocalDateTime.of(2019, AUGUST, 23, 10, 2, 0), LocalDateTime.of(2019, SEPTEMBER, 7, 22, 17, 0)),
			SolarTermTestCase(Gui, You, LocalDateTime.of(2019, SEPTEMBER, 7, 22, 17, 0), LocalDateTime.of(2019, SEPTEMBER, 23, 7, 50, 0)),
			SolarTermTestCase(Gui, You, LocalDateTime.of(2019, SEPTEMBER, 23, 7, 50, 0), LocalDateTime.of(2019, OCTOBER, 8, 14, 6, 0)),
			SolarTermTestCase(Jia, Xu, LocalDateTime.of(2019, OCTOBER, 8, 14, 6, 0), LocalDateTime.of(2019, OCTOBER, 23, 17, 20, 0)),
			SolarTermTestCase(Jia, Xu, LocalDateTime.of(2019, OCTOBER, 23, 17, 20, 0), LocalDateTime.of(2019, NOVEMBER, 7, 17, 24, 0)),
			SolarTermTestCase(Yi, Hai, LocalDateTime.of(2019, NOVEMBER, 7, 17, 24, 0), LocalDateTime.of(2019, NOVEMBER, 22, 14, 59, 0)),
			SolarTermTestCase(Yi, Hai, LocalDateTime.of(2019, NOVEMBER, 22, 14, 59, 0), LocalDateTime.of(2019, DECEMBER, 7, 10, 18, 0)),
			SolarTermTestCase(Bing, Zi, LocalDateTime.of(2019, DECEMBER, 7, 10, 18, 0), LocalDateTime.of(2019, DECEMBER, 22, 4, 19, 0)),
			SolarTermTestCase(Bing, Zi, LocalDateTime.of(2019, DECEMBER, 22, 4, 19, 0), LocalDateTime.of(2020, JANUARY, 5, 21, 30, 0)),
		)

		val SOLAR_TERMS_2020 = listOf(
			SolarTermTestCase(Ding, Chou, LocalDateTime.of(2020, JANUARY, 5, 21, 30, 0), LocalDateTime.of(2020, JANUARY, 20, 14, 55, 0)),
			SolarTermTestCase(Ding, Chou, LocalDateTime.of(2020, JANUARY, 20, 14, 55, 0), LocalDateTime.of(2020, FEBRUARY, 4, 9, 3, 0)),
			SolarTermTestCase(WuHS, Yin, LocalDateTime.of(2020, FEBRUARY, 4, 9, 3, 0), LocalDateTime.of(2020, FEBRUARY, 19, 4, 57, 0)),
			SolarTermTestCase(WuHS, Yin, LocalDateTime.of(2020, FEBRUARY, 19, 4, 57, 0), LocalDateTime.of(2020, MARCH, 5, 2, 57, 0)),
			SolarTermTestCase(Ji, Mao, LocalDateTime.of(2020, MARCH, 5, 2, 57, 0), LocalDateTime.of(2020, MARCH, 20, 3, 50, 0)),
			SolarTermTestCase(Ji, Mao, LocalDateTime.of(2020, MARCH, 20, 3, 50, 0), LocalDateTime.of(2020, APRIL, 4, 7, 38, 0)),
			SolarTermTestCase(Geng, Chen, LocalDateTime.of(2020, APRIL, 4, 7, 38, 0), LocalDateTime.of(2020, APRIL, 19, 14, 45, 0)),
			SolarTermTestCase(Geng, Chen, LocalDateTime.of(2020, APRIL, 19, 14, 45, 0), LocalDateTime.of(2020, MAY, 5, 0, 51, 0)),
			SolarTermTestCase(Xin, Si, LocalDateTime.of(2020, MAY, 5, 0, 51, 0), LocalDateTime.of(2020, MAY, 20, 13, 49, 0)),
			SolarTermTestCase(Xin, Si, LocalDateTime.of(2020, MAY, 20, 13, 49, 0), LocalDateTime.of(2020, JUNE, 5, 4, 58, 0)),
			SolarTermTestCase(Ren, WuEB, LocalDateTime.of(2020, JUNE, 5, 4, 58, 0), LocalDateTime.of(2020, JUNE, 20, 21, 44, 0)),
			SolarTermTestCase(Ren, WuEB, LocalDateTime.of(2020, JUNE, 20, 21, 44, 0), LocalDateTime.of(2020, JULY, 6, 15, 14, 0)),
			SolarTermTestCase(Gui, Wei, LocalDateTime.of(2020, JULY, 6, 15, 14, 0), LocalDateTime.of(2020, JULY, 22, 8, 37, 0)),
			SolarTermTestCase(Gui, Wei, LocalDateTime.of(2020, JULY, 22, 8, 37, 0), LocalDateTime.of(2020, AUGUST, 7, 1, 6, 0)),
			SolarTermTestCase(Jia, Shen, LocalDateTime.of(2020, AUGUST, 7, 1, 6, 0), LocalDateTime.of(2020, AUGUST, 22, 15, 45, 0)),
			SolarTermTestCase(Jia, Shen, LocalDateTime.of(2020, AUGUST, 22, 15, 45, 0), LocalDateTime.of(2020, SEPTEMBER, 7, 4, 8, 0)),
			SolarTermTestCase(Yi, You, LocalDateTime.of(2020, SEPTEMBER, 7, 4, 8, 0), LocalDateTime.of(2020, SEPTEMBER, 22, 13, 31, 0)),
			SolarTermTestCase(Yi, You, LocalDateTime.of(2020, SEPTEMBER, 22, 13, 31, 0), LocalDateTime.of(2020, OCTOBER, 7, 19, 55, 0)),
			SolarTermTestCase(Bing, Xu, LocalDateTime.of(2020, OCTOBER, 7, 19, 55, 0), LocalDateTime.of(2020, OCTOBER, 22, 23, 0, 0)),
			SolarTermTestCase(Bing, Xu, LocalDateTime.of(2020, OCTOBER, 22, 23, 0, 0), LocalDateTime.of(2020, NOVEMBER, 6, 23, 14, 0)),
			SolarTermTestCase(Ding, Hai, LocalDateTime.of(2020, NOVEMBER, 6, 23, 14, 0), LocalDateTime.of(2020, NOVEMBER, 21, 20, 40, 0)),
			SolarTermTestCase(Ding, Hai, LocalDateTime.of(2020, NOVEMBER, 21, 20, 40, 0), LocalDateTime.of(2020, DECEMBER, 6, 16, 9, 0)),
			SolarTermTestCase(WuHS, Zi, LocalDateTime.of(2020, DECEMBER, 6, 16, 9, 0), LocalDateTime.of(2020, DECEMBER, 21, 10, 2, 0)),
			SolarTermTestCase(WuHS, Zi, LocalDateTime.of(2020, DECEMBER, 21, 10, 2, 0), LocalDateTime.of(2021, JANUARY, 5, 3, 23, 0)),
		)

		val SOLAR_TERMS_2021 = listOf(
			SolarTermTestCase(Ji, Chou, LocalDateTime.of(2021, JANUARY, 5, 3, 23, 0), LocalDateTime.of(2021, JANUARY, 19, 20, 40, 0)),
			SolarTermTestCase(Ji, Chou, LocalDateTime.of(2021, JANUARY, 19, 20, 40, 0), LocalDateTime.of(2021, FEBRUARY, 3, 14, 59, 0)),
			SolarTermTestCase(Geng, Yin, LocalDateTime.of(2021, FEBRUARY, 3, 14, 59, 0), LocalDateTime.of(2021, FEBRUARY, 18, 10, 44, 0)),
			SolarTermTestCase(Geng, Yin, LocalDateTime.of(2021, FEBRUARY, 18, 10, 44, 0), LocalDateTime.of(2021, MARCH, 5, 8, 54, 0)),
			SolarTermTestCase(Xin, Mao, LocalDateTime.of(2021, MARCH, 5, 8, 54, 0), LocalDateTime.of(2021, MARCH, 20, 9, 37, 0)),
			SolarTermTestCase(Xin, Mao, LocalDateTime.of(2021, MARCH, 20, 9, 37, 0), LocalDateTime.of(2021, APRIL, 4, 13, 35, 0)),
			SolarTermTestCase(Ren, Chen, LocalDateTime.of(2021, APRIL, 4, 13, 35, 0), LocalDateTime.of(2021, APRIL, 19, 20, 33, 0)),
			SolarTermTestCase(Ren, Chen, LocalDateTime.of(2021, APRIL, 19, 20, 33, 0), LocalDateTime.of(2021, MAY, 5, 6, 47, 0)),
			SolarTermTestCase(Gui, Si, LocalDateTime.of(2021, MAY, 5, 6, 47, 0), LocalDateTime.of(2021, MAY, 20, 19, 37, 0)),
			SolarTermTestCase(Gui, Si, LocalDateTime.of(2021, MAY, 20, 19, 37, 0), LocalDateTime.of(2021, JUNE, 5, 10, 52, 0)),
			SolarTermTestCase(Jia, WuEB, LocalDateTime.of(2021, JUNE, 5, 10, 52, 0), LocalDateTime.of(2021, JUNE, 21, 3, 32, 0)),
			SolarTermTestCase(Jia, WuEB, LocalDateTime.of(2021, JUNE, 21, 3, 32, 0), LocalDateTime.of(2021, JULY, 6, 21, 5, 0)),
			SolarTermTestCase(Yi, Wei, LocalDateTime.of(2021, JULY, 6, 21, 5, 0), LocalDateTime.of(2021, JULY, 22, 14, 26, 0)),
			SolarTermTestCase(Yi, Wei, LocalDateTime.of(2021, JULY, 22, 14, 26, 0), LocalDateTime.of(2021, AUGUST, 7, 6, 54, 0)),
			SolarTermTestCase(Bing, Shen, LocalDateTime.of(2021, AUGUST, 7, 6, 54, 0), LocalDateTime.of(2021, AUGUST, 22, 21, 35, 0)),
			SolarTermTestCase(Bing, Shen, LocalDateTime.of(2021, AUGUST, 22, 21, 35, 0), LocalDateTime.of(2021, SEPTEMBER, 7, 9, 53, 0)),
			SolarTermTestCase(Ding, You, LocalDateTime.of(2021, SEPTEMBER, 7, 9, 53, 0), LocalDateTime.of(2021, SEPTEMBER, 22, 19, 21, 0)),
			SolarTermTestCase(Ding, You, LocalDateTime.of(2021, SEPTEMBER, 22, 19, 21, 0), LocalDateTime.of(2021, OCTOBER, 8, 1, 39, 0)),
			SolarTermTestCase(WuHS, Xu, LocalDateTime.of(2021, OCTOBER, 8, 1, 39, 0), LocalDateTime.of(2021, OCTOBER, 23, 4, 51, 0)),
			SolarTermTestCase(WuHS, Xu, LocalDateTime.of(2021, OCTOBER, 23, 4, 51, 0), LocalDateTime.of(2021, NOVEMBER, 7, 4, 59, 0)),
			SolarTermTestCase(Ji, Hai, LocalDateTime.of(2021, NOVEMBER, 7, 4, 59, 0), LocalDateTime.of(2021, NOVEMBER, 22, 2, 34, 0)),
			SolarTermTestCase(Ji, Hai, LocalDateTime.of(2021, NOVEMBER, 22, 2, 34, 0), LocalDateTime.of(2021, DECEMBER, 6, 21, 57, 0)),
			SolarTermTestCase(Geng, Zi, LocalDateTime.of(2021, DECEMBER, 6, 21, 57, 0), LocalDateTime.of(2021, DECEMBER, 21, 15, 59, 0)),
			SolarTermTestCase(Geng, Zi, LocalDateTime.of(2021, DECEMBER, 21, 15, 59, 0), LocalDateTime.of(2022, JANUARY, 5, 9, 14, 0)),
		)

		val SOLAR_TERMS_2022 = listOf(
			SolarTermTestCase(Xin, Chou, LocalDateTime.of(2022, JANUARY, 5, 9, 14, 0), LocalDateTime.of(2022, JANUARY, 20, 2, 39, 0)),
			SolarTermTestCase(Xin, Chou, LocalDateTime.of(2022, JANUARY, 20, 2, 39, 0), LocalDateTime.of(2022, FEBRUARY, 3, 20, 51, 0)),
			SolarTermTestCase(Ren, Yin, LocalDateTime.of(2022, FEBRUARY, 3, 20, 51, 0), LocalDateTime.of(2022, FEBRUARY, 18, 16, 43, 0)),
			SolarTermTestCase(Ren, Yin, LocalDateTime.of(2022, FEBRUARY, 18, 16, 43, 0), LocalDateTime.of(2022, MARCH, 5, 14, 44, 0)),
			SolarTermTestCase(Gui, Mao, LocalDateTime.of(2022, MARCH, 5, 14, 44, 0), LocalDateTime.of(2022, MARCH, 20, 15, 33, 0)),
			SolarTermTestCase(Gui, Mao, LocalDateTime.of(2022, MARCH, 20, 15, 33, 0), LocalDateTime.of(2022, APRIL, 4, 19, 20, 0)),
			SolarTermTestCase(Jia, Chen, LocalDateTime.of(2022, APRIL, 4, 19, 20, 0), LocalDateTime.of(2022, APRIL, 20, 2, 24, 0)),
			SolarTermTestCase(Jia, Chen, LocalDateTime.of(2022, APRIL, 20, 2, 24, 0), LocalDateTime.of(2022, MAY, 5, 12, 26, 0)),
			SolarTermTestCase(Yi, Si, LocalDateTime.of(2022, MAY, 5, 12, 26, 0), LocalDateTime.of(2022, MAY, 21, 1, 23, 0)),
			SolarTermTestCase(Yi, Si, LocalDateTime.of(2022, MAY, 21, 1, 23, 0), LocalDateTime.of(2022, JUNE, 5, 16, 26, 0)),
			SolarTermTestCase(Bing, WuEB, LocalDateTime.of(2022, JUNE, 5, 16, 26, 0), LocalDateTime.of(2022, JUNE, 21, 9, 14, 0)),
			SolarTermTestCase(Bing, WuEB, LocalDateTime.of(2022, JUNE, 21, 9, 14, 0), LocalDateTime.of(2022, JULY, 7, 2, 38, 0)),
			SolarTermTestCase(Ding, Wei, LocalDateTime.of(2022, JULY, 7, 2, 38, 0), LocalDateTime.of(2022, JULY, 22, 20, 7, 0)),
			SolarTermTestCase(Ding, Wei, LocalDateTime.of(2022, JULY, 22, 20, 7, 0), LocalDateTime.of(2022, AUGUST, 7, 12, 29, 0)),
			SolarTermTestCase(WuHS, Shen, LocalDateTime.of(2022, AUGUST, 7, 12, 29, 0), LocalDateTime.of(2022, AUGUST, 23, 3, 16, 0)),
			SolarTermTestCase(WuHS, Shen, LocalDateTime.of(2022, AUGUST, 23, 3, 16, 0), LocalDateTime.of(2022, SEPTEMBER, 7, 15, 32, 0)),
			SolarTermTestCase(Ji, You, LocalDateTime.of(2022, SEPTEMBER, 7, 15, 32, 0), LocalDateTime.of(2022, SEPTEMBER, 23, 1, 4, 0)),
			SolarTermTestCase(Ji, You, LocalDateTime.of(2022, SEPTEMBER, 23, 1, 4, 0), LocalDateTime.of(2022, OCTOBER, 8, 7, 22, 0)),
			SolarTermTestCase(Geng, Xu, LocalDateTime.of(2022, OCTOBER, 8, 7, 22, 0), LocalDateTime.of(2022, OCTOBER, 23, 10, 36, 0)),
			SolarTermTestCase(Geng, Xu, LocalDateTime.of(2022, OCTOBER, 23, 10, 36, 0), LocalDateTime.of(2022, NOVEMBER, 7, 10, 45, 0)),
			SolarTermTestCase(Xin, Hai, LocalDateTime.of(2022, NOVEMBER, 7, 10, 45, 0), LocalDateTime.of(2022, NOVEMBER, 22, 8, 20, 0)),
			SolarTermTestCase(Xin, Hai, LocalDateTime.of(2022, NOVEMBER, 22, 8, 20, 0), LocalDateTime.of(2022, DECEMBER, 7, 3, 46, 0)),
			SolarTermTestCase(Ren, Zi, LocalDateTime.of(2022, DECEMBER, 7, 3, 46, 0), LocalDateTime.of(2022, DECEMBER, 21, 21, 48, 0)),
			SolarTermTestCase(Ren, Zi, LocalDateTime.of(2022, DECEMBER, 21, 21, 48, 0), LocalDateTime.of(2023, JANUARY, 5, 15, 5, 0)),
		)

		val SOLAR_TERMS_2023 = listOf(
			SolarTermTestCase(Gui, Chou, LocalDateTime.of(2023, JANUARY, 5, 15, 5, 0), LocalDateTime.of(2023, JANUARY, 20, 8, 30, 0)),
			SolarTermTestCase(Gui, Chou, LocalDateTime.of(2023, JANUARY, 20, 8, 30, 0), LocalDateTime.of(2023, FEBRUARY, 4, 2, 43, 0)),
			SolarTermTestCase(Jia, Yin, LocalDateTime.of(2023, FEBRUARY, 4, 2, 43, 0), LocalDateTime.of(2023, FEBRUARY, 18, 22, 34, 0)),
			SolarTermTestCase(Jia, Yin, LocalDateTime.of(2023, FEBRUARY, 18, 22, 34, 0), LocalDateTime.of(2023, MARCH, 5, 20, 36, 0)),
			SolarTermTestCase(Yi, Mao, LocalDateTime.of(2023, MARCH, 5, 20, 36, 0), LocalDateTime.of(2023, MARCH, 20, 21, 24, 0)),
			SolarTermTestCase(Yi, Mao, LocalDateTime.of(2023, MARCH, 20, 21, 24, 0), LocalDateTime.of(2023, APRIL, 5, 1, 13, 0)),
			SolarTermTestCase(Bing, Chen, LocalDateTime.of(2023, APRIL, 5, 1, 13, 0), LocalDateTime.of(2023, APRIL, 20, 8, 14, 0)),
			SolarTermTestCase(Bing, Chen, LocalDateTime.of(2023, APRIL, 20, 8, 14, 0), LocalDateTime.of(2023, MAY, 5, 18, 19, 0)),
			SolarTermTestCase(Ding, Si, LocalDateTime.of(2023, MAY, 5, 18, 19, 0), LocalDateTime.of(2023, MAY, 21, 7, 9, 0)),
			SolarTermTestCase(Ding, Si, LocalDateTime.of(2023, MAY, 21, 7, 9, 0), LocalDateTime.of(2023, JUNE, 5, 22, 18, 0)),
			SolarTermTestCase(WuHS, WuEB, LocalDateTime.of(2023, JUNE, 5, 22, 18, 0), LocalDateTime.of(2023, JUNE, 21, 14, 58, 0)),
			SolarTermTestCase(WuHS, WuEB, LocalDateTime.of(2023, JUNE, 21, 14, 58, 0), LocalDateTime.of(2023, JULY, 7, 8, 31, 0)),
			SolarTermTestCase(Ji, Wei, LocalDateTime.of(2023, JULY, 7, 8, 31, 0), LocalDateTime.of(2023, JULY, 23, 1, 50, 0)),
			SolarTermTestCase(Ji, Wei, LocalDateTime.of(2023, JULY, 23, 1, 50, 0), LocalDateTime.of(2023, AUGUST, 7, 18, 23, 0)),
			SolarTermTestCase(Geng, Shen, LocalDateTime.of(2023, AUGUST, 7, 18, 23, 0), LocalDateTime.of(2023, AUGUST, 23, 9, 1, 0)),
			SolarTermTestCase(Geng, Shen, LocalDateTime.of(2023, AUGUST, 23, 9, 1, 0), LocalDateTime.of(2023, SEPTEMBER, 7, 21, 27, 0)),
			SolarTermTestCase(Xin, You, LocalDateTime.of(2023, SEPTEMBER, 7, 21, 27, 0), LocalDateTime.of(2023, SEPTEMBER, 23, 6, 50, 0)),
			SolarTermTestCase(Xin, You, LocalDateTime.of(2023, SEPTEMBER, 23, 6, 50, 0), LocalDateTime.of(2023, OCTOBER, 8, 13, 16, 0)),
			SolarTermTestCase(Ren, Xu, LocalDateTime.of(2023, OCTOBER, 8, 13, 16, 0), LocalDateTime.of(2023, OCTOBER, 23, 16, 21, 0)),
			SolarTermTestCase(Ren, Xu, LocalDateTime.of(2023, OCTOBER, 23, 16, 21, 0), LocalDateTime.of(2023, NOVEMBER, 7, 16, 36, 0)),
			SolarTermTestCase(Gui, Hai, LocalDateTime.of(2023, NOVEMBER, 7, 16, 36, 0), LocalDateTime.of(2023, NOVEMBER, 22, 14, 3, 0)),
			SolarTermTestCase(Gui, Hai, LocalDateTime.of(2023, NOVEMBER, 22, 14, 3, 0), LocalDateTime.of(2023, DECEMBER, 7, 9, 33, 0)),
			SolarTermTestCase(Jia, Zi, LocalDateTime.of(2023, DECEMBER, 7, 9, 33, 0), LocalDateTime.of(2023, DECEMBER, 22, 3, 27, 0)),
			SolarTermTestCase(Jia, Zi, LocalDateTime.of(2023, DECEMBER, 22, 3, 27, 0), LocalDateTime.of(2024, JANUARY, 5, 20, 49, 0)),
		)

		val SOLAR_TERMS_2024 = listOf(
			SolarTermTestCase(Yi, Chou, LocalDateTime.of(2024, JANUARY, 5, 20, 49, 0), LocalDateTime.of(2024, JANUARY, 20, 14, 7, 0)),
			SolarTermTestCase(Yi, Chou, LocalDateTime.of(2024, JANUARY, 20, 14, 7, 0), LocalDateTime.of(2024, FEBRUARY, 4, 8, 27, 0)),
			SolarTermTestCase(Bing, Yin, LocalDateTime.of(2024, FEBRUARY, 4, 8, 27, 0), LocalDateTime.of(2024, FEBRUARY, 19, 4, 13, 0)),
			SolarTermTestCase(Bing, Yin, LocalDateTime.of(2024, FEBRUARY, 19, 4, 13, 0), LocalDateTime.of(2024, MARCH, 5, 2, 23, 0)),
			SolarTermTestCase(Ding, Mao, LocalDateTime.of(2024, MARCH, 5, 2, 23, 0), LocalDateTime.of(2024, MARCH, 20, 3, 6, 0)),
			SolarTermTestCase(Ding, Mao, LocalDateTime.of(2024, MARCH, 20, 3, 6, 0), LocalDateTime.of(2024, APRIL, 4, 7, 2, 0)),
			SolarTermTestCase(WuHS, Chen, LocalDateTime.of(2024, APRIL, 4, 7, 2, 0), LocalDateTime.of(2024, APRIL, 19, 14, 0, 0)),
			SolarTermTestCase(WuHS, Chen, LocalDateTime.of(2024, APRIL, 19, 14, 0, 0), LocalDateTime.of(2024, MAY, 5, 0, 10, 0)),
			SolarTermTestCase(Ji, Si, LocalDateTime.of(2024, MAY, 5, 0, 10, 0), LocalDateTime.of(2024, MAY, 20, 13, 0, 0)),
			SolarTermTestCase(Ji, Si, LocalDateTime.of(2024, MAY, 20, 13, 0, 0), LocalDateTime.of(2024, JUNE, 5, 4, 10, 0)),
			SolarTermTestCase(Geng, WuEB, LocalDateTime.of(2024, JUNE, 5, 4, 10, 0), LocalDateTime.of(2024, JUNE, 20, 20, 51, 0)),
			SolarTermTestCase(Geng, WuEB, LocalDateTime.of(2024, JUNE, 20, 20, 51, 0), LocalDateTime.of(2024, JULY, 6, 14, 20, 0)),
			SolarTermTestCase(Xin, Wei, LocalDateTime.of(2024, JULY, 6, 14, 20, 0), LocalDateTime.of(2024, JULY, 22, 7, 44, 0)),
			SolarTermTestCase(Xin, Wei, LocalDateTime.of(2024, JULY, 22, 7, 44, 0), LocalDateTime.of(2024, AUGUST, 7, 0, 9, 0)),
			SolarTermTestCase(Ren, Shen, LocalDateTime.of(2024, AUGUST, 7, 0, 9, 0), LocalDateTime.of(2024, AUGUST, 22, 14, 55, 0)),
			SolarTermTestCase(Ren, Shen, LocalDateTime.of(2024, AUGUST, 22, 14, 55, 0), LocalDateTime.of(2024, SEPTEMBER, 7, 3, 11, 0)),
			SolarTermTestCase(Gui, You, LocalDateTime.of(2024, SEPTEMBER, 7, 3, 11, 0), LocalDateTime.of(2024, SEPTEMBER, 22, 12, 44, 0)),
			SolarTermTestCase(Gui, You, LocalDateTime.of(2024, SEPTEMBER, 22, 12, 44, 0), LocalDateTime.of(2024, OCTOBER, 7, 19, 0, 0)),
			SolarTermTestCase(Jia, Xu, LocalDateTime.of(2024, OCTOBER, 7, 19, 0, 0), LocalDateTime.of(2024, OCTOBER, 22, 22, 15, 0)),
			SolarTermTestCase(Jia, Xu, LocalDateTime.of(2024, OCTOBER, 22, 22, 15, 0), LocalDateTime.of(2024, NOVEMBER, 6, 22, 20, 0)),
			SolarTermTestCase(Yi, Hai, LocalDateTime.of(2024, NOVEMBER, 6, 22, 20, 0), LocalDateTime.of(2024, NOVEMBER, 21, 19, 56, 0)),
			SolarTermTestCase(Yi, Hai, LocalDateTime.of(2024, NOVEMBER, 21, 19, 56, 0), LocalDateTime.of(2024, DECEMBER, 6, 15, 17, 0)),
			SolarTermTestCase(Bing, Zi, LocalDateTime.of(2024, DECEMBER, 6, 15, 17, 0), LocalDateTime.of(2024, DECEMBER, 21, 9, 21, 0)),
			SolarTermTestCase(Bing, Zi, LocalDateTime.of(2024, DECEMBER, 21, 9, 21, 0), LocalDateTime.of(2025, JANUARY, 5, 2, 33, 0)),
		)

		val SOLAR_TERMS_2025 = listOf(
			SolarTermTestCase(Ding, Chou, LocalDateTime.of(2025, JANUARY, 5, 2, 33, 0), LocalDateTime.of(2025, JANUARY, 19, 20, 0, 0)),
			SolarTermTestCase(Ding, Chou, LocalDateTime.of(2025, JANUARY, 19, 20, 0, 0), LocalDateTime.of(2025, FEBRUARY, 3, 14, 10, 0)),
			SolarTermTestCase(WuHS, Yin, LocalDateTime.of(2025, FEBRUARY, 3, 14, 10, 0), LocalDateTime.of(2025, FEBRUARY, 18, 10, 7, 0)),
			SolarTermTestCase(WuHS, Yin, LocalDateTime.of(2025, FEBRUARY, 18, 10, 7, 0), LocalDateTime.of(2025, MARCH, 5, 8, 7, 0)),
			SolarTermTestCase(Ji, Mao, LocalDateTime.of(2025, MARCH, 5, 8, 7, 0), LocalDateTime.of(2025, MARCH, 20, 9, 1, 0)),
			SolarTermTestCase(Ji, Mao, LocalDateTime.of(2025, MARCH, 20, 9, 1, 0), LocalDateTime.of(2025, APRIL, 4, 12, 48, 0)),
			SolarTermTestCase(Geng, Chen, LocalDateTime.of(2025, APRIL, 4, 12, 48, 0), LocalDateTime.of(2025, APRIL, 19, 19, 56, 0)),
			SolarTermTestCase(Geng, Chen, LocalDateTime.of(2025, APRIL, 19, 19, 56, 0), LocalDateTime.of(2025, MAY, 5, 5, 57, 0)),
			SolarTermTestCase(Xin, Si, LocalDateTime.of(2025, MAY, 5, 5, 57, 0), LocalDateTime.of(2025, MAY, 20, 18, 55, 0)),
			SolarTermTestCase(Xin, Si, LocalDateTime.of(2025, MAY, 20, 18, 55, 0), LocalDateTime.of(2025, JUNE, 5, 9, 56, 0)),
			SolarTermTestCase(Ren, WuEB, LocalDateTime.of(2025, JUNE, 5, 9, 56, 0), LocalDateTime.of(2025, JUNE, 21, 2, 42, 0)),
			SolarTermTestCase(Ren, WuEB, LocalDateTime.of(2025, JUNE, 21, 2, 42, 0), LocalDateTime.of(2025, JULY, 6, 20, 5, 0)),
			SolarTermTestCase(Gui, Wei, LocalDateTime.of(2025, JULY, 6, 20, 5, 0), LocalDateTime.of(2025, JULY, 22, 13, 29, 0)),
			SolarTermTestCase(Gui, Wei, LocalDateTime.of(2025, JULY, 22, 13, 29, 0), LocalDateTime.of(2025, AUGUST, 7, 5, 52, 0)),
			SolarTermTestCase(Jia, Shen, LocalDateTime.of(2025, AUGUST, 7, 5, 52, 0), LocalDateTime.of(2025, AUGUST, 22, 20, 34, 0)),
			SolarTermTestCase(Jia, Shen, LocalDateTime.of(2025, AUGUST, 22, 20, 34, 0), LocalDateTime.of(2025, SEPTEMBER, 7, 8, 52, 0)),
			SolarTermTestCase(Yi, You, LocalDateTime.of(2025, SEPTEMBER, 7, 8, 52, 0), LocalDateTime.of(2025, SEPTEMBER, 22, 18, 19, 0)),
			SolarTermTestCase(Yi, You, LocalDateTime.of(2025, SEPTEMBER, 22, 18, 19, 0), LocalDateTime.of(2025, OCTOBER, 8, 0, 41, 0)),
			SolarTermTestCase(Bing, Xu, LocalDateTime.of(2025, OCTOBER, 8, 0, 41, 0), LocalDateTime.of(2025, OCTOBER, 23, 3, 51, 0)),
			SolarTermTestCase(Bing, Xu, LocalDateTime.of(2025, OCTOBER, 23, 3, 51, 0), LocalDateTime.of(2025, NOVEMBER, 7, 4, 4, 0)),
			SolarTermTestCase(Ding, Hai, LocalDateTime.of(2025, NOVEMBER, 7, 4, 4, 0), LocalDateTime.of(2025, NOVEMBER, 22, 1, 36, 0)),
			SolarTermTestCase(Ding, Hai, LocalDateTime.of(2025, NOVEMBER, 22, 1, 36, 0), LocalDateTime.of(2025, DECEMBER, 6, 21, 5, 0)),
			SolarTermTestCase(WuHS, Zi, LocalDateTime.of(2025, DECEMBER, 6, 21, 5, 0), LocalDateTime.of(2025, DECEMBER, 21, 15, 3, 0)),
			SolarTermTestCase(WuHS, Zi, LocalDateTime.of(2025, DECEMBER, 21, 15, 3, 0), LocalDateTime.of(2026, JANUARY, 5, 8, 23, 0)),
		)

		val SOLAR_TERMS_2026 = listOf(
			SolarTermTestCase(Ji, Chou, LocalDateTime.of(2026, JANUARY, 5, 8, 23, 0), LocalDateTime.of(2026, JANUARY, 20, 1, 45, 0)),
			SolarTermTestCase(Ji, Chou, LocalDateTime.of(2026, JANUARY, 20, 1, 45, 0), LocalDateTime.of(2026, FEBRUARY, 3, 20, 2, 0)),
			SolarTermTestCase(Geng, Yin, LocalDateTime.of(2026, FEBRUARY, 3, 20, 2, 0), LocalDateTime.of(2026, FEBRUARY, 18, 15, 52, 0)),
			SolarTermTestCase(Geng, Yin, LocalDateTime.of(2026, FEBRUARY, 18, 15, 52, 0), LocalDateTime.of(2026, MARCH, 5, 13, 59, 0)),
			SolarTermTestCase(Xin, Mao, LocalDateTime.of(2026, MARCH, 5, 13, 59, 0), LocalDateTime.of(2026, MARCH, 20, 14, 46, 0)),
			SolarTermTestCase(Xin, Mao, LocalDateTime.of(2026, MARCH, 20, 14, 46, 0), LocalDateTime.of(2026, APRIL, 4, 18, 40, 0)),
			SolarTermTestCase(Ren, Chen, LocalDateTime.of(2026, APRIL, 4, 18, 40, 0), LocalDateTime.of(2026, APRIL, 20, 1, 39, 0)),
			SolarTermTestCase(Ren, Chen, LocalDateTime.of(2026, APRIL, 20, 1, 39, 0), LocalDateTime.of(2026, MAY, 5, 11, 49, 0)),
			SolarTermTestCase(Gui, Si, LocalDateTime.of(2026, MAY, 5, 11, 49, 0), LocalDateTime.of(2026, MAY, 21, 0, 37, 0)),
			SolarTermTestCase(Gui, Si, LocalDateTime.of(2026, MAY, 21, 0, 37, 0), LocalDateTime.of(2026, JUNE, 5, 15, 48, 0)),
			SolarTermTestCase(Jia, WuEB, LocalDateTime.of(2026, JUNE, 5, 15, 48, 0), LocalDateTime.of(2026, JUNE, 21, 8, 25, 0)),
			SolarTermTestCase(Jia, WuEB, LocalDateTime.of(2026, JUNE, 21, 8, 25, 0), LocalDateTime.of(2026, JULY, 7, 1, 57, 0)),
			SolarTermTestCase(Yi, Wei, LocalDateTime.of(2026, JULY, 7, 1, 57, 0), LocalDateTime.of(2026, JULY, 22, 19, 13, 0)),
			SolarTermTestCase(Yi, Wei, LocalDateTime.of(2026, JULY, 22, 19, 13, 0), LocalDateTime.of(2026, AUGUST, 7, 11, 43, 0)),
			SolarTermTestCase(Bing, Shen, LocalDateTime.of(2026, AUGUST, 7, 11, 43, 0), LocalDateTime.of(2026, AUGUST, 23, 2, 19, 0)),
			SolarTermTestCase(Bing, Shen, LocalDateTime.of(2026, AUGUST, 23, 2, 19, 0), LocalDateTime.of(2026, SEPTEMBER, 7, 14, 41, 0)),
			SolarTermTestCase(Ding, You, LocalDateTime.of(2026, SEPTEMBER, 7, 14, 41, 0), LocalDateTime.of(2026, SEPTEMBER, 23, 0, 5, 0)),
			SolarTermTestCase(Ding, You, LocalDateTime.of(2026, SEPTEMBER, 23, 0, 5, 0), LocalDateTime.of(2026, OCTOBER, 8, 6, 29, 0)),
			SolarTermTestCase(WuHS, Xu, LocalDateTime.of(2026, OCTOBER, 8, 6, 29, 0), LocalDateTime.of(2026, OCTOBER, 23, 9, 38, 0)),
			SolarTermTestCase(WuHS, Xu, LocalDateTime.of(2026, OCTOBER, 23, 9, 38, 0), LocalDateTime.of(2026, NOVEMBER, 7, 9, 52, 0)),
			SolarTermTestCase(Ji, Hai, LocalDateTime.of(2026, NOVEMBER, 7, 9, 52, 0), LocalDateTime.of(2026, NOVEMBER, 22, 7, 23, 0)),
			SolarTermTestCase(Ji, Hai, LocalDateTime.of(2026, NOVEMBER, 22, 7, 23, 0), LocalDateTime.of(2026, DECEMBER, 7, 2, 52, 0)),
			SolarTermTestCase(Geng, Zi, LocalDateTime.of(2026, DECEMBER, 7, 2, 52, 0), LocalDateTime.of(2026, DECEMBER, 21, 20, 50, 0)),
			// TODO end timing is a guess
			SolarTermTestCase(Geng, Zi, LocalDateTime.of(2026, DECEMBER, 21, 20, 50, 0), LocalDateTime.of(2027, JANUARY, 5, 14, 15, 0)),
		)

		val ALL_KNOWN_YEARS = listOf(
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
