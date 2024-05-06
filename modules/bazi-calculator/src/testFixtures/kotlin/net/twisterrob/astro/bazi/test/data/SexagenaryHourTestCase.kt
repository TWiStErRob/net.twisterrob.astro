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
import java.time.Month.DECEMBER
import java.time.Month.JANUARY
import java.time.temporal.ChronoUnit
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS
import net.twisterrob.astro.bazi.test.previous

/**
 * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_hours
 */
@Suppress("detekt.LongParameterList")
class SexagenaryHourTestCase(
	val cyclicOrdinal: Int,
	val hourStem: HeavenlyStem,
	val hourBranch: EarthlyBranch,

	/**
	 * The stem of the day this test case covers.
	 * Except when [branchOfHour] == [Zi] between 23:00-00:00.
	 * where it's the previous [dayStem], see [HeavenlyStem.previous].
	 */
	val dayStem: HeavenlyStem,
	val branchOfHour: EarthlyBranch,

	/**
	 * The first second of the cycle, inclusive.
	 */
	val startTime: LocalDateTime,

	/**
	 * The last second of the cycle, exclusive.
	 */
	val endTime: LocalDateTime,
) {

	val hourPillar: BaZi.Pillar
		get() = BaZi.Pillar(hourStem, hourBranch)

	val midTime: LocalDateTime
		get() = startTime.plusSeconds(ChronoUnit.SECONDS.between(startTime, endTime) / 2)

	@Suppress("detekt.NamedArguments", "detekt.LargeClass")
	companion object {

		val CYCLE_18071224: List<SexagenaryHourTestCase> = listOf(
			SexagenaryHourTestCase(
				1, Jia, Zi, Jia, Zi,
				LocalDateTime.of(1807, DECEMBER, 24, 23, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 1, 0),
			),
			SexagenaryHourTestCase(
				2, Yi, Chou, Jia, Chou,
				LocalDateTime.of(1807, DECEMBER, 25, 1, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 3, 0),
			),
			SexagenaryHourTestCase(
				3, Bing, Yin, Jia, Yin,
				LocalDateTime.of(1807, DECEMBER, 25, 3, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 5, 0),
			),
			SexagenaryHourTestCase(
				4, Ding, Mao, Jia, Mao,
				LocalDateTime.of(1807, DECEMBER, 25, 5, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 7, 0),
			),
			SexagenaryHourTestCase(
				5, WuHS, Chen, Jia, Chen,
				LocalDateTime.of(1807, DECEMBER, 25, 7, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 9, 0),
			),
			SexagenaryHourTestCase(
				6, Ji, Si, Jia, Si,
				LocalDateTime.of(1807, DECEMBER, 25, 9, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 11, 0),
			),
			SexagenaryHourTestCase(
				7, Geng, WuEB, Jia, WuEB,
				LocalDateTime.of(1807, DECEMBER, 25, 11, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 13, 0),
			),
			SexagenaryHourTestCase(
				8, Xin, Wei, Jia, Wei,
				LocalDateTime.of(1807, DECEMBER, 25, 13, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 15, 0),
			),
			SexagenaryHourTestCase(
				9, Ren, Shen, Jia, Shen,
				LocalDateTime.of(1807, DECEMBER, 25, 15, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 17, 0),
			),
			SexagenaryHourTestCase(
				10, Gui, You, Jia, You,
				LocalDateTime.of(1807, DECEMBER, 25, 17, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 19, 0),
			),
			SexagenaryHourTestCase(
				11, Jia, Xu, Jia, Xu,
				LocalDateTime.of(1807, DECEMBER, 25, 19, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 21, 0),
			),
			SexagenaryHourTestCase(
				12, Yi, Hai, Jia, Hai,
				LocalDateTime.of(1807, DECEMBER, 25, 21, 0),
				LocalDateTime.of(1807, DECEMBER, 25, 23, 0),
			),
			SexagenaryHourTestCase(
				13, Bing, Zi, Yi, Zi,
				LocalDateTime.of(1807, DECEMBER, 25, 23, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 1, 0),
			),
			SexagenaryHourTestCase(
				14, Ding, Chou, Yi, Chou,
				LocalDateTime.of(1807, DECEMBER, 26, 1, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 3, 0),
			),
			SexagenaryHourTestCase(
				15, WuHS, Yin, Yi, Yin,
				LocalDateTime.of(1807, DECEMBER, 26, 3, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 5, 0),
			),
			SexagenaryHourTestCase(
				16, Ji, Mao, Yi, Mao,
				LocalDateTime.of(1807, DECEMBER, 26, 5, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 7, 0),
			),
			SexagenaryHourTestCase(
				17, Geng, Chen, Yi, Chen,
				LocalDateTime.of(1807, DECEMBER, 26, 7, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 9, 0),
			),
			SexagenaryHourTestCase(
				18, Xin, Si, Yi, Si,
				LocalDateTime.of(1807, DECEMBER, 26, 9, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 11, 0),
			),
			SexagenaryHourTestCase(
				19, Ren, WuEB, Yi, WuEB,
				LocalDateTime.of(1807, DECEMBER, 26, 11, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 13, 0),
			),
			SexagenaryHourTestCase(
				20, Gui, Wei, Yi, Wei,
				LocalDateTime.of(1807, DECEMBER, 26, 13, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 15, 0),
			),
			SexagenaryHourTestCase(
				21, Jia, Shen, Yi, Shen,
				LocalDateTime.of(1807, DECEMBER, 26, 15, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 17, 0),
			),
			SexagenaryHourTestCase(
				22, Yi, You, Yi, You,
				LocalDateTime.of(1807, DECEMBER, 26, 17, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 19, 0),
			),
			SexagenaryHourTestCase(
				23, Bing, Xu, Yi, Xu,
				LocalDateTime.of(1807, DECEMBER, 26, 19, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 21, 0),
			),
			SexagenaryHourTestCase(
				24, Ding, Hai, Yi, Hai,
				LocalDateTime.of(1807, DECEMBER, 26, 21, 0),
				LocalDateTime.of(1807, DECEMBER, 26, 23, 0),
			),
			SexagenaryHourTestCase(
				25, WuHS, Zi, Bing, Zi,
				LocalDateTime.of(1807, DECEMBER, 26, 23, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 1, 0),
			),
			SexagenaryHourTestCase(
				26, Ji, Chou, Bing, Chou,
				LocalDateTime.of(1807, DECEMBER, 27, 1, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 3, 0),
			),
			SexagenaryHourTestCase(
				27, Geng, Yin, Bing, Yin,
				LocalDateTime.of(1807, DECEMBER, 27, 3, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 5, 0),
			),
			SexagenaryHourTestCase(
				28, Xin, Mao, Bing, Mao,
				LocalDateTime.of(1807, DECEMBER, 27, 5, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 7, 0),
			),
			SexagenaryHourTestCase(
				29, Ren, Chen, Bing, Chen,
				LocalDateTime.of(1807, DECEMBER, 27, 7, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 9, 0),
			),
			SexagenaryHourTestCase(
				30, Gui, Si, Bing, Si,
				LocalDateTime.of(1807, DECEMBER, 27, 9, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 11, 0),
			),
			SexagenaryHourTestCase(
				31, Jia, WuEB, Bing, WuEB,
				LocalDateTime.of(1807, DECEMBER, 27, 11, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 13, 0),
			),
			SexagenaryHourTestCase(
				32, Yi, Wei, Bing, Wei,
				LocalDateTime.of(1807, DECEMBER, 27, 13, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 15, 0),
			),
			SexagenaryHourTestCase(
				33, Bing, Shen, Bing, Shen,
				LocalDateTime.of(1807, DECEMBER, 27, 15, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 17, 0),
			),
			SexagenaryHourTestCase(
				34, Ding, You, Bing, You,
				LocalDateTime.of(1807, DECEMBER, 27, 17, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 19, 0),
			),
			SexagenaryHourTestCase(
				35, WuHS, Xu, Bing, Xu,
				LocalDateTime.of(1807, DECEMBER, 27, 19, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 21, 0),
			),
			SexagenaryHourTestCase(
				36, Ji, Hai, Bing, Hai,
				LocalDateTime.of(1807, DECEMBER, 27, 21, 0),
				LocalDateTime.of(1807, DECEMBER, 27, 23, 0),
			),
			SexagenaryHourTestCase(
				37, Geng, Zi, Ding, Zi,
				LocalDateTime.of(1807, DECEMBER, 27, 23, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 1, 0),
			),
			SexagenaryHourTestCase(
				38, Xin, Chou, Ding, Chou,
				LocalDateTime.of(1807, DECEMBER, 28, 1, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 3, 0),
			),
			SexagenaryHourTestCase(
				39, Ren, Yin, Ding, Yin,
				LocalDateTime.of(1807, DECEMBER, 28, 3, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 5, 0),
			),
			SexagenaryHourTestCase(
				40, Gui, Mao, Ding, Mao,
				LocalDateTime.of(1807, DECEMBER, 28, 5, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 7, 0),
			),
			SexagenaryHourTestCase(
				41, Jia, Chen, Ding, Chen,
				LocalDateTime.of(1807, DECEMBER, 28, 7, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 9, 0),
			),
			SexagenaryHourTestCase(
				42, Yi, Si, Ding, Si,
				LocalDateTime.of(1807, DECEMBER, 28, 9, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 11, 0),
			),
			SexagenaryHourTestCase(
				43, Bing, WuEB, Ding, WuEB,
				LocalDateTime.of(1807, DECEMBER, 28, 11, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 13, 0),
			),
			SexagenaryHourTestCase(
				44, Ding, Wei, Ding, Wei,
				LocalDateTime.of(1807, DECEMBER, 28, 13, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 15, 0),
			),
			SexagenaryHourTestCase(
				45, WuHS, Shen, Ding, Shen,
				LocalDateTime.of(1807, DECEMBER, 28, 15, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 17, 0),
			),
			SexagenaryHourTestCase(
				46, Ji, You, Ding, You,
				LocalDateTime.of(1807, DECEMBER, 28, 17, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 19, 0),
			),
			SexagenaryHourTestCase(
				47, Geng, Xu, Ding, Xu,
				LocalDateTime.of(1807, DECEMBER, 28, 19, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 21, 0),
			),
			SexagenaryHourTestCase(
				48, Xin, Hai, Ding, Hai,
				LocalDateTime.of(1807, DECEMBER, 28, 21, 0),
				LocalDateTime.of(1807, DECEMBER, 28, 23, 0),
			),
			SexagenaryHourTestCase(
				49, Ren, Zi, WuHS, Zi,
				LocalDateTime.of(1807, DECEMBER, 28, 23, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 1, 0),
			),
			SexagenaryHourTestCase(
				50, Gui, Chou, WuHS, Chou,
				LocalDateTime.of(1807, DECEMBER, 29, 1, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 3, 0),
			),
			SexagenaryHourTestCase(
				51, Jia, Yin, WuHS, Yin,
				LocalDateTime.of(1807, DECEMBER, 29, 3, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 5, 0),
			),
			SexagenaryHourTestCase(
				52, Yi, Mao, WuHS, Mao,
				LocalDateTime.of(1807, DECEMBER, 29, 5, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 7, 0),
			),
			SexagenaryHourTestCase(
				53, Bing, Chen, WuHS, Chen,
				LocalDateTime.of(1807, DECEMBER, 29, 7, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 9, 0),
			),
			SexagenaryHourTestCase(
				54, Ding, Si, WuHS, Si,
				LocalDateTime.of(1807, DECEMBER, 29, 9, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 11, 0),
			),
			SexagenaryHourTestCase(
				55, WuHS, WuEB, WuHS, WuEB,
				LocalDateTime.of(1807, DECEMBER, 29, 11, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 13, 0),
			),
			SexagenaryHourTestCase(
				56, Ji, Wei, WuHS, Wei,
				LocalDateTime.of(1807, DECEMBER, 29, 13, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 15, 0),
			),
			SexagenaryHourTestCase(
				57, Geng, Shen, WuHS, Shen,
				LocalDateTime.of(1807, DECEMBER, 29, 15, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 17, 0),
			),
			SexagenaryHourTestCase(
				58, Xin, You, WuHS, You,
				LocalDateTime.of(1807, DECEMBER, 29, 17, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 19, 0),
			),
			SexagenaryHourTestCase(
				59, Ren, Xu, WuHS, Xu,
				LocalDateTime.of(1807, DECEMBER, 29, 19, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 21, 0),
			),
			SexagenaryHourTestCase(
				60, Gui, Hai, WuHS, Hai,
				LocalDateTime.of(1807, DECEMBER, 29, 21, 0),
				LocalDateTime.of(1807, DECEMBER, 29, 23, 0),
			),
		)

		val CYCLE_18071229: List<SexagenaryHourTestCase> = listOf(
			SexagenaryHourTestCase(
				1, Jia, Zi, Ji, Zi,
				LocalDateTime.of(1807, DECEMBER, 29, 23, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 1, 0),
			),
			SexagenaryHourTestCase(
				2, Yi, Chou, Ji, Chou,
				LocalDateTime.of(1807, DECEMBER, 30, 1, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 3, 0),
			),
			SexagenaryHourTestCase(
				3, Bing, Yin, Ji, Yin,
				LocalDateTime.of(1807, DECEMBER, 30, 3, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 5, 0),
			),
			SexagenaryHourTestCase(
				4, Ding, Mao, Ji, Mao,
				LocalDateTime.of(1807, DECEMBER, 30, 5, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 7, 0),
			),
			SexagenaryHourTestCase(
				5, WuHS, Chen, Ji, Chen,
				LocalDateTime.of(1807, DECEMBER, 30, 7, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 9, 0),
			),
			SexagenaryHourTestCase(
				6, Ji, Si, Ji, Si,
				LocalDateTime.of(1807, DECEMBER, 30, 9, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 11, 0),
			),
			SexagenaryHourTestCase(
				7, Geng, WuEB, Ji, WuEB,
				LocalDateTime.of(1807, DECEMBER, 30, 11, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 13, 0),
			),
			SexagenaryHourTestCase(
				8, Xin, Wei, Ji, Wei,
				LocalDateTime.of(1807, DECEMBER, 30, 13, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 15, 0),
			),
			SexagenaryHourTestCase(
				9, Ren, Shen, Ji, Shen,
				LocalDateTime.of(1807, DECEMBER, 30, 15, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 17, 0),
			),
			SexagenaryHourTestCase(
				10, Gui, You, Ji, You,
				LocalDateTime.of(1807, DECEMBER, 30, 17, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 19, 0),
			),
			SexagenaryHourTestCase(
				11, Jia, Xu, Ji, Xu,
				LocalDateTime.of(1807, DECEMBER, 30, 19, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 21, 0),
			),
			SexagenaryHourTestCase(
				12, Yi, Hai, Ji, Hai,
				LocalDateTime.of(1807, DECEMBER, 30, 21, 0),
				LocalDateTime.of(1807, DECEMBER, 30, 23, 0),
			),
			SexagenaryHourTestCase(
				13, Bing, Zi, Geng, Zi,
				LocalDateTime.of(1807, DECEMBER, 30, 23, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 1, 0),
			),
			SexagenaryHourTestCase(
				14, Ding, Chou, Geng, Chou,
				LocalDateTime.of(1807, DECEMBER, 31, 1, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 3, 0),
			),
			SexagenaryHourTestCase(
				15, WuHS, Yin, Geng, Yin,
				LocalDateTime.of(1807, DECEMBER, 31, 3, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 5, 0),
			),
			SexagenaryHourTestCase(
				16, Ji, Mao, Geng, Mao,
				LocalDateTime.of(1807, DECEMBER, 31, 5, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 7, 0),
			),
			SexagenaryHourTestCase(
				17, Geng, Chen, Geng, Chen,
				LocalDateTime.of(1807, DECEMBER, 31, 7, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 9, 0),
			),
			SexagenaryHourTestCase(
				18, Xin, Si, Geng, Si,
				LocalDateTime.of(1807, DECEMBER, 31, 9, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 11, 0),
			),
			SexagenaryHourTestCase(
				19, Ren, WuEB, Geng, WuEB,
				LocalDateTime.of(1807, DECEMBER, 31, 11, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 13, 0),
			),
			SexagenaryHourTestCase(
				20, Gui, Wei, Geng, Wei,
				LocalDateTime.of(1807, DECEMBER, 31, 13, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 15, 0),
			),
			SexagenaryHourTestCase(
				21, Jia, Shen, Geng, Shen,
				LocalDateTime.of(1807, DECEMBER, 31, 15, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 17, 0),
			),
			SexagenaryHourTestCase(
				22, Yi, You, Geng, You,
				LocalDateTime.of(1807, DECEMBER, 31, 17, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 19, 0),
			),
			SexagenaryHourTestCase(
				23, Bing, Xu, Geng, Xu,
				LocalDateTime.of(1807, DECEMBER, 31, 19, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 21, 0),
			),
			SexagenaryHourTestCase(
				24, Ding, Hai, Geng, Hai,
				LocalDateTime.of(1807, DECEMBER, 31, 21, 0),
				LocalDateTime.of(1807, DECEMBER, 31, 23, 0),
			),
			SexagenaryHourTestCase(
				25, WuHS, Zi, Xin, Zi,
				LocalDateTime.of(1807, DECEMBER, 31, 23, 0),
				LocalDateTime.of(1808, JANUARY, 1, 1, 0),
			),
			SexagenaryHourTestCase(
				26, Ji, Chou, Xin, Chou,
				LocalDateTime.of(1808, JANUARY, 1, 1, 0),
				LocalDateTime.of(1808, JANUARY, 1, 3, 0),
			),
			SexagenaryHourTestCase(
				27, Geng, Yin, Xin, Yin,
				LocalDateTime.of(1808, JANUARY, 1, 3, 0),
				LocalDateTime.of(1808, JANUARY, 1, 5, 0),
			),
			SexagenaryHourTestCase(
				28, Xin, Mao, Xin, Mao,
				LocalDateTime.of(1808, JANUARY, 1, 5, 0),
				LocalDateTime.of(1808, JANUARY, 1, 7, 0),
			),
			SexagenaryHourTestCase(
				29, Ren, Chen, Xin, Chen,
				LocalDateTime.of(1808, JANUARY, 1, 7, 0),
				LocalDateTime.of(1808, JANUARY, 1, 9, 0),
			),
			SexagenaryHourTestCase(
				30, Gui, Si, Xin, Si,
				LocalDateTime.of(1808, JANUARY, 1, 9, 0),
				LocalDateTime.of(1808, JANUARY, 1, 11, 0),
			),
			SexagenaryHourTestCase(
				31, Jia, WuEB, Xin, WuEB,
				LocalDateTime.of(1808, JANUARY, 1, 11, 0),
				LocalDateTime.of(1808, JANUARY, 1, 13, 0),
			),
			SexagenaryHourTestCase(
				32, Yi, Wei, Xin, Wei,
				LocalDateTime.of(1808, JANUARY, 1, 13, 0),
				LocalDateTime.of(1808, JANUARY, 1, 15, 0),
			),
			SexagenaryHourTestCase(
				33, Bing, Shen, Xin, Shen,
				LocalDateTime.of(1808, JANUARY, 1, 15, 0),
				LocalDateTime.of(1808, JANUARY, 1, 17, 0),
			),
			SexagenaryHourTestCase(
				34, Ding, You, Xin, You,
				LocalDateTime.of(1808, JANUARY, 1, 17, 0),
				LocalDateTime.of(1808, JANUARY, 1, 19, 0),
			),
			SexagenaryHourTestCase(
				35, WuHS, Xu, Xin, Xu,
				LocalDateTime.of(1808, JANUARY, 1, 19, 0),
				LocalDateTime.of(1808, JANUARY, 1, 21, 0),
			),
			SexagenaryHourTestCase(
				36, Ji, Hai, Xin, Hai,
				LocalDateTime.of(1808, JANUARY, 1, 21, 0),
				LocalDateTime.of(1808, JANUARY, 1, 23, 0),
			),
			SexagenaryHourTestCase(
				37, Geng, Zi, Ren, Zi,
				LocalDateTime.of(1808, JANUARY, 1, 23, 0),
				LocalDateTime.of(1808, JANUARY, 2, 1, 0),
			),
			SexagenaryHourTestCase(
				38, Xin, Chou, Ren, Chou,
				LocalDateTime.of(1808, JANUARY, 2, 1, 0),
				LocalDateTime.of(1808, JANUARY, 2, 3, 0),
			),
			SexagenaryHourTestCase(
				39, Ren, Yin, Ren, Yin,
				LocalDateTime.of(1808, JANUARY, 2, 3, 0),
				LocalDateTime.of(1808, JANUARY, 2, 5, 0),
			),
			SexagenaryHourTestCase(
				40, Gui, Mao, Ren, Mao,
				LocalDateTime.of(1808, JANUARY, 2, 5, 0),
				LocalDateTime.of(1808, JANUARY, 2, 7, 0),
			),
			SexagenaryHourTestCase(
				41, Jia, Chen, Ren, Chen,
				LocalDateTime.of(1808, JANUARY, 2, 7, 0),
				LocalDateTime.of(1808, JANUARY, 2, 9, 0),
			),
			SexagenaryHourTestCase(
				42, Yi, Si, Ren, Si,
				LocalDateTime.of(1808, JANUARY, 2, 9, 0),
				LocalDateTime.of(1808, JANUARY, 2, 11, 0),
			),
			SexagenaryHourTestCase(
				43, Bing, WuEB, Ren, WuEB,
				LocalDateTime.of(1808, JANUARY, 2, 11, 0),
				LocalDateTime.of(1808, JANUARY, 2, 13, 0),
			),
			SexagenaryHourTestCase(
				44, Ding, Wei, Ren, Wei,
				LocalDateTime.of(1808, JANUARY, 2, 13, 0),
				LocalDateTime.of(1808, JANUARY, 2, 15, 0),
			),
			SexagenaryHourTestCase(
				45, WuHS, Shen, Ren, Shen,
				LocalDateTime.of(1808, JANUARY, 2, 15, 0),
				LocalDateTime.of(1808, JANUARY, 2, 17, 0),
			),
			SexagenaryHourTestCase(
				46, Ji, You, Ren, You,
				LocalDateTime.of(1808, JANUARY, 2, 17, 0),
				LocalDateTime.of(1808, JANUARY, 2, 19, 0),
			),
			SexagenaryHourTestCase(
				47, Geng, Xu, Ren, Xu,
				LocalDateTime.of(1808, JANUARY, 2, 19, 0),
				LocalDateTime.of(1808, JANUARY, 2, 21, 0),
			),
			SexagenaryHourTestCase(
				48, Xin, Hai, Ren, Hai,
				LocalDateTime.of(1808, JANUARY, 2, 21, 0),
				LocalDateTime.of(1808, JANUARY, 2, 23, 0),
			),
			SexagenaryHourTestCase(
				49, Ren, Zi, Gui, Zi,
				LocalDateTime.of(1808, JANUARY, 2, 23, 0),
				LocalDateTime.of(1808, JANUARY, 3, 1, 0),
			),
			SexagenaryHourTestCase(
				50, Gui, Chou, Gui, Chou,
				LocalDateTime.of(1808, JANUARY, 3, 1, 0),
				LocalDateTime.of(1808, JANUARY, 3, 3, 0),
			),
			SexagenaryHourTestCase(
				51, Jia, Yin, Gui, Yin,
				LocalDateTime.of(1808, JANUARY, 3, 3, 0),
				LocalDateTime.of(1808, JANUARY, 3, 5, 0),
			),
			SexagenaryHourTestCase(
				52, Yi, Mao, Gui, Mao,
				LocalDateTime.of(1808, JANUARY, 3, 5, 0),
				LocalDateTime.of(1808, JANUARY, 3, 7, 0),
			),
			SexagenaryHourTestCase(
				53, Bing, Chen, Gui, Chen,
				LocalDateTime.of(1808, JANUARY, 3, 7, 0),
				LocalDateTime.of(1808, JANUARY, 3, 9, 0),
			),
			SexagenaryHourTestCase(
				54, Ding, Si, Gui, Si,
				LocalDateTime.of(1808, JANUARY, 3, 9, 0),
				LocalDateTime.of(1808, JANUARY, 3, 11, 0),
			),
			SexagenaryHourTestCase(
				55, WuHS, WuEB, Gui, WuEB,
				LocalDateTime.of(1808, JANUARY, 3, 11, 0),
				LocalDateTime.of(1808, JANUARY, 3, 13, 0),
			),
			SexagenaryHourTestCase(
				56, Ji, Wei, Gui, Wei,
				LocalDateTime.of(1808, JANUARY, 3, 13, 0),
				LocalDateTime.of(1808, JANUARY, 3, 15, 0),
			),
			SexagenaryHourTestCase(
				57, Geng, Shen, Gui, Shen,
				LocalDateTime.of(1808, JANUARY, 3, 15, 0),
				LocalDateTime.of(1808, JANUARY, 3, 17, 0),
			),
			SexagenaryHourTestCase(
				58, Xin, You, Gui, You,
				LocalDateTime.of(1808, JANUARY, 3, 17, 0),
				LocalDateTime.of(1808, JANUARY, 3, 19, 0),
			),
			SexagenaryHourTestCase(
				59, Ren, Xu, Gui, Xu,
				LocalDateTime.of(1808, JANUARY, 3, 19, 0),
				LocalDateTime.of(1808, JANUARY, 3, 21, 0),
			),
			SexagenaryHourTestCase(
				60, Gui, Hai, Gui, Hai,
				LocalDateTime.of(1808, JANUARY, 3, 21, 0),
				LocalDateTime.of(1808, JANUARY, 3, 23, 0),
			),
		)

		val ALL_KNOWN_CYCLES: List<List<SexagenaryHourTestCase>> = listOf(
			CYCLE_18071224,
			CYCLE_18071229,
		)
	}
}
