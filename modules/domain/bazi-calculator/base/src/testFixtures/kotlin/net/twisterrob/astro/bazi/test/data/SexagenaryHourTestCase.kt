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
import net.twisterrob.astro.bazi.test.previous
import java.time.LocalDateTime
import java.time.LocalDateTime.of
import java.time.Month.DECEMBER
import java.time.Month.JANUARY
import java.time.temporal.ChronoUnit
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS

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

		/**
		 * Source: Picked a random day and looked up in an online calculator,
		 * then cycled around to the start and manually calculated the rest.
		 */
		val CYCLE_18071224: List<SexagenaryHourTestCase> = listOf(
			//@formatter:off
			SexagenaryHourTestCase(1, Jia, Zi, Jia, Zi, of(1807, DECEMBER, 24, 23, 0), of(1807, DECEMBER, 25, 1, 0)),
			SexagenaryHourTestCase(2, Yi, Chou, Jia, Chou, of(1807, DECEMBER, 25, 1, 0), of(1807, DECEMBER, 25, 3, 0)),
			SexagenaryHourTestCase(3, Bing, Yin, Jia, Yin, of(1807, DECEMBER, 25, 3, 0), of(1807, DECEMBER, 25, 5, 0)),
			SexagenaryHourTestCase(4, Ding, Mao, Jia, Mao, of(1807, DECEMBER, 25, 5, 0), of(1807, DECEMBER, 25, 7, 0)),
			SexagenaryHourTestCase(5, WuHS, Chen, Jia, Chen, of(1807, DECEMBER, 25, 7, 0), of(1807, DECEMBER, 25, 9, 0)),
			SexagenaryHourTestCase(6, Ji, Si, Jia, Si, of(1807, DECEMBER, 25, 9, 0), of(1807, DECEMBER, 25, 11, 0)),
			SexagenaryHourTestCase(7, Geng, WuEB, Jia, WuEB, of(1807, DECEMBER, 25, 11, 0), of(1807, DECEMBER, 25, 13, 0)),
			SexagenaryHourTestCase(8, Xin, Wei, Jia, Wei, of(1807, DECEMBER, 25, 13, 0), of(1807, DECEMBER, 25, 15, 0)),
			SexagenaryHourTestCase(9, Ren, Shen, Jia, Shen, of(1807, DECEMBER, 25, 15, 0), of(1807, DECEMBER, 25, 17, 0)),
			SexagenaryHourTestCase(10, Gui, You, Jia, You, of(1807, DECEMBER, 25, 17, 0), of(1807, DECEMBER, 25, 19, 0)),
			SexagenaryHourTestCase(11, Jia, Xu, Jia, Xu, of(1807, DECEMBER, 25, 19, 0), of(1807, DECEMBER, 25, 21, 0)),
			SexagenaryHourTestCase(12, Yi, Hai, Jia, Hai, of(1807, DECEMBER, 25, 21, 0), of(1807, DECEMBER, 25, 23, 0)),
			SexagenaryHourTestCase(13, Bing, Zi, Yi, Zi, of(1807, DECEMBER, 25, 23, 0), of(1807, DECEMBER, 26, 1, 0)),
			SexagenaryHourTestCase(14, Ding, Chou, Yi, Chou, of(1807, DECEMBER, 26, 1, 0), of(1807, DECEMBER, 26, 3, 0)),
			SexagenaryHourTestCase(15, WuHS, Yin, Yi, Yin, of(1807, DECEMBER, 26, 3, 0), of(1807, DECEMBER, 26, 5, 0)),
			SexagenaryHourTestCase(16, Ji, Mao, Yi, Mao, of(1807, DECEMBER, 26, 5, 0), of(1807, DECEMBER, 26, 7, 0)),
			SexagenaryHourTestCase(17, Geng, Chen, Yi, Chen, of(1807, DECEMBER, 26, 7, 0), of(1807, DECEMBER, 26, 9, 0)),
			SexagenaryHourTestCase(18, Xin, Si, Yi, Si, of(1807, DECEMBER, 26, 9, 0), of(1807, DECEMBER, 26, 11, 0)),
			SexagenaryHourTestCase(19, Ren, WuEB, Yi, WuEB, of(1807, DECEMBER, 26, 11, 0), of(1807, DECEMBER, 26, 13, 0)),
			SexagenaryHourTestCase(20, Gui, Wei, Yi, Wei, of(1807, DECEMBER, 26, 13, 0), of(1807, DECEMBER, 26, 15, 0)),
			SexagenaryHourTestCase(21, Jia, Shen, Yi, Shen, of(1807, DECEMBER, 26, 15, 0), of(1807, DECEMBER, 26, 17, 0)),
			SexagenaryHourTestCase(22, Yi, You, Yi, You, of(1807, DECEMBER, 26, 17, 0), of(1807, DECEMBER, 26, 19, 0)),
			SexagenaryHourTestCase(23, Bing, Xu, Yi, Xu, of(1807, DECEMBER, 26, 19, 0), of(1807, DECEMBER, 26, 21, 0)),
			SexagenaryHourTestCase(24, Ding, Hai, Yi, Hai, of(1807, DECEMBER, 26, 21, 0), of(1807, DECEMBER, 26, 23, 0)),
			SexagenaryHourTestCase(25, WuHS, Zi, Bing, Zi, of(1807, DECEMBER, 26, 23, 0), of(1807, DECEMBER, 27, 1, 0)),
			SexagenaryHourTestCase(26, Ji, Chou, Bing, Chou, of(1807, DECEMBER, 27, 1, 0), of(1807, DECEMBER, 27, 3, 0)),
			SexagenaryHourTestCase(27, Geng, Yin, Bing, Yin, of(1807, DECEMBER, 27, 3, 0), of(1807, DECEMBER, 27, 5, 0)),
			SexagenaryHourTestCase(28, Xin, Mao, Bing, Mao, of(1807, DECEMBER, 27, 5, 0), of(1807, DECEMBER, 27, 7, 0)),
			SexagenaryHourTestCase(29, Ren, Chen, Bing, Chen, of(1807, DECEMBER, 27, 7, 0), of(1807, DECEMBER, 27, 9, 0)),
			SexagenaryHourTestCase(30, Gui, Si, Bing, Si, of(1807, DECEMBER, 27, 9, 0), of(1807, DECEMBER, 27, 11, 0)),
			SexagenaryHourTestCase(31, Jia, WuEB, Bing, WuEB, of(1807, DECEMBER, 27, 11, 0), of(1807, DECEMBER, 27, 13, 0)),
			SexagenaryHourTestCase(32, Yi, Wei, Bing, Wei, of(1807, DECEMBER, 27, 13, 0), of(1807, DECEMBER, 27, 15, 0)),
			SexagenaryHourTestCase(33, Bing, Shen, Bing, Shen, of(1807, DECEMBER, 27, 15, 0), of(1807, DECEMBER, 27, 17, 0)),
			SexagenaryHourTestCase(34, Ding, You, Bing, You, of(1807, DECEMBER, 27, 17, 0), of(1807, DECEMBER, 27, 19, 0)),
			SexagenaryHourTestCase(35, WuHS, Xu, Bing, Xu, of(1807, DECEMBER, 27, 19, 0), of(1807, DECEMBER, 27, 21, 0)),
			SexagenaryHourTestCase(36, Ji, Hai, Bing, Hai, of(1807, DECEMBER, 27, 21, 0), of(1807, DECEMBER, 27, 23, 0)),
			SexagenaryHourTestCase(37, Geng, Zi, Ding, Zi, of(1807, DECEMBER, 27, 23, 0), of(1807, DECEMBER, 28, 1, 0)),
			SexagenaryHourTestCase(38, Xin, Chou, Ding, Chou, of(1807, DECEMBER, 28, 1, 0), of(1807, DECEMBER, 28, 3, 0)),
			SexagenaryHourTestCase(39, Ren, Yin, Ding, Yin, of(1807, DECEMBER, 28, 3, 0), of(1807, DECEMBER, 28, 5, 0)),
			SexagenaryHourTestCase(40, Gui, Mao, Ding, Mao, of(1807, DECEMBER, 28, 5, 0), of(1807, DECEMBER, 28, 7, 0)),
			SexagenaryHourTestCase(41, Jia, Chen, Ding, Chen, of(1807, DECEMBER, 28, 7, 0), of(1807, DECEMBER, 28, 9, 0)),
			SexagenaryHourTestCase(42, Yi, Si, Ding, Si, of(1807, DECEMBER, 28, 9, 0), of(1807, DECEMBER, 28, 11, 0)),
			SexagenaryHourTestCase(43, Bing, WuEB, Ding, WuEB, of(1807, DECEMBER, 28, 11, 0), of(1807, DECEMBER, 28, 13, 0)),
			SexagenaryHourTestCase(44, Ding, Wei, Ding, Wei, of(1807, DECEMBER, 28, 13, 0), of(1807, DECEMBER, 28, 15, 0)),
			SexagenaryHourTestCase(45, WuHS, Shen, Ding, Shen, of(1807, DECEMBER, 28, 15, 0), of(1807, DECEMBER, 28, 17, 0)),
			SexagenaryHourTestCase(46, Ji, You, Ding, You, of(1807, DECEMBER, 28, 17, 0), of(1807, DECEMBER, 28, 19, 0)),
			SexagenaryHourTestCase(47, Geng, Xu, Ding, Xu, of(1807, DECEMBER, 28, 19, 0), of(1807, DECEMBER, 28, 21, 0)),
			SexagenaryHourTestCase(48, Xin, Hai, Ding, Hai, of(1807, DECEMBER, 28, 21, 0), of(1807, DECEMBER, 28, 23, 0)),
			SexagenaryHourTestCase(49, Ren, Zi, WuHS, Zi, of(1807, DECEMBER, 28, 23, 0), of(1807, DECEMBER, 29, 1, 0)),
			SexagenaryHourTestCase(50, Gui, Chou, WuHS, Chou, of(1807, DECEMBER, 29, 1, 0), of(1807, DECEMBER, 29, 3, 0)),
			SexagenaryHourTestCase(51, Jia, Yin, WuHS, Yin, of(1807, DECEMBER, 29, 3, 0), of(1807, DECEMBER, 29, 5, 0)),
			SexagenaryHourTestCase(52, Yi, Mao, WuHS, Mao, of(1807, DECEMBER, 29, 5, 0), of(1807, DECEMBER, 29, 7, 0)),
			SexagenaryHourTestCase(53, Bing, Chen, WuHS, Chen, of(1807, DECEMBER, 29, 7, 0), of(1807, DECEMBER, 29, 9, 0)),
			SexagenaryHourTestCase(54, Ding, Si, WuHS, Si, of(1807, DECEMBER, 29, 9, 0), of(1807, DECEMBER, 29, 11, 0)),
			SexagenaryHourTestCase(55, WuHS, WuEB, WuHS, WuEB, of(1807, DECEMBER, 29, 11, 0), of(1807, DECEMBER, 29, 13, 0)),
			SexagenaryHourTestCase(56, Ji, Wei, WuHS, Wei, of(1807, DECEMBER, 29, 13, 0), of(1807, DECEMBER, 29, 15, 0)),
			SexagenaryHourTestCase(57, Geng, Shen, WuHS, Shen, of(1807, DECEMBER, 29, 15, 0), of(1807, DECEMBER, 29, 17, 0)),
			SexagenaryHourTestCase(58, Xin, You, WuHS, You, of(1807, DECEMBER, 29, 17, 0), of(1807, DECEMBER, 29, 19, 0)),
			SexagenaryHourTestCase(59, Ren, Xu, WuHS, Xu, of(1807, DECEMBER, 29, 19, 0), of(1807, DECEMBER, 29, 21, 0)),
			SexagenaryHourTestCase(60, Gui, Hai, WuHS, Hai, of(1807, DECEMBER, 29, 21, 0), of(1807, DECEMBER, 29, 23, 0)),
			//@formatter:on
		)

		/**
		 * Source: continues from [CYCLE_18071224], and manually calculated the cycles.
		 */
		val CYCLE_18071229: List<SexagenaryHourTestCase> = listOf(
			//@formatter:off
			SexagenaryHourTestCase(1, Jia, Zi, Ji, Zi, of(1807, DECEMBER, 29, 23, 0), of(1807, DECEMBER, 30, 1, 0)),
			SexagenaryHourTestCase(2, Yi, Chou, Ji, Chou, of(1807, DECEMBER, 30, 1, 0), of(1807, DECEMBER, 30, 3, 0)),
			SexagenaryHourTestCase(3, Bing, Yin, Ji, Yin, of(1807, DECEMBER, 30, 3, 0), of(1807, DECEMBER, 30, 5, 0)),
			SexagenaryHourTestCase(4, Ding, Mao, Ji, Mao, of(1807, DECEMBER, 30, 5, 0), of(1807, DECEMBER, 30, 7, 0)),
			SexagenaryHourTestCase(5, WuHS, Chen, Ji, Chen, of(1807, DECEMBER, 30, 7, 0), of(1807, DECEMBER, 30, 9, 0)),
			SexagenaryHourTestCase(6, Ji, Si, Ji, Si, of(1807, DECEMBER, 30, 9, 0), of(1807, DECEMBER, 30, 11, 0)),
			SexagenaryHourTestCase(7, Geng, WuEB, Ji, WuEB, of(1807, DECEMBER, 30, 11, 0), of(1807, DECEMBER, 30, 13, 0)),
			SexagenaryHourTestCase(8, Xin, Wei, Ji, Wei, of(1807, DECEMBER, 30, 13, 0), of(1807, DECEMBER, 30, 15, 0)),
			SexagenaryHourTestCase(9, Ren, Shen, Ji, Shen, of(1807, DECEMBER, 30, 15, 0), of(1807, DECEMBER, 30, 17, 0)),
			SexagenaryHourTestCase(10, Gui, You, Ji, You, of(1807, DECEMBER, 30, 17, 0), of(1807, DECEMBER, 30, 19, 0)),
			SexagenaryHourTestCase(11, Jia, Xu, Ji, Xu, of(1807, DECEMBER, 30, 19, 0), of(1807, DECEMBER, 30, 21, 0)),
			SexagenaryHourTestCase(12, Yi, Hai, Ji, Hai, of(1807, DECEMBER, 30, 21, 0), of(1807, DECEMBER, 30, 23, 0)),
			SexagenaryHourTestCase(13, Bing, Zi, Geng, Zi, of(1807, DECEMBER, 30, 23, 0), of(1807, DECEMBER, 31, 1, 0)),
			SexagenaryHourTestCase(14, Ding, Chou, Geng, Chou, of(1807, DECEMBER, 31, 1, 0), of(1807, DECEMBER, 31, 3, 0)),
			SexagenaryHourTestCase(15, WuHS, Yin, Geng, Yin, of(1807, DECEMBER, 31, 3, 0), of(1807, DECEMBER, 31, 5, 0)),
			SexagenaryHourTestCase(16, Ji, Mao, Geng, Mao, of(1807, DECEMBER, 31, 5, 0), of(1807, DECEMBER, 31, 7, 0)),
			SexagenaryHourTestCase(17, Geng, Chen, Geng, Chen, of(1807, DECEMBER, 31, 7, 0), of(1807, DECEMBER, 31, 9, 0)),
			SexagenaryHourTestCase(18, Xin, Si, Geng, Si, of(1807, DECEMBER, 31, 9, 0), of(1807, DECEMBER, 31, 11, 0)),
			SexagenaryHourTestCase(19, Ren, WuEB, Geng, WuEB, of(1807, DECEMBER, 31, 11, 0), of(1807, DECEMBER, 31, 13, 0)),
			SexagenaryHourTestCase(20, Gui, Wei, Geng, Wei, of(1807, DECEMBER, 31, 13, 0), of(1807, DECEMBER, 31, 15, 0)),
			SexagenaryHourTestCase(21, Jia, Shen, Geng, Shen, of(1807, DECEMBER, 31, 15, 0), of(1807, DECEMBER, 31, 17, 0)),
			SexagenaryHourTestCase(22, Yi, You, Geng, You, of(1807, DECEMBER, 31, 17, 0), of(1807, DECEMBER, 31, 19, 0)),
			SexagenaryHourTestCase(23, Bing, Xu, Geng, Xu, of(1807, DECEMBER, 31, 19, 0), of(1807, DECEMBER, 31, 21, 0)),
			SexagenaryHourTestCase(24, Ding, Hai, Geng, Hai, of(1807, DECEMBER, 31, 21, 0), of(1807, DECEMBER, 31, 23, 0)),
			SexagenaryHourTestCase(25, WuHS, Zi, Xin, Zi, of(1807, DECEMBER, 31, 23, 0), of(1808, JANUARY, 1, 1, 0)),
			SexagenaryHourTestCase(26, Ji, Chou, Xin, Chou, of(1808, JANUARY, 1, 1, 0), of(1808, JANUARY, 1, 3, 0)),
			SexagenaryHourTestCase(27, Geng, Yin, Xin, Yin, of(1808, JANUARY, 1, 3, 0), of(1808, JANUARY, 1, 5, 0)),
			SexagenaryHourTestCase(28, Xin, Mao, Xin, Mao, of(1808, JANUARY, 1, 5, 0), of(1808, JANUARY, 1, 7, 0)),
			SexagenaryHourTestCase(29, Ren, Chen, Xin, Chen, of(1808, JANUARY, 1, 7, 0), of(1808, JANUARY, 1, 9, 0)),
			SexagenaryHourTestCase(30, Gui, Si, Xin, Si, of(1808, JANUARY, 1, 9, 0), of(1808, JANUARY, 1, 11, 0)),
			SexagenaryHourTestCase(31, Jia, WuEB, Xin, WuEB, of(1808, JANUARY, 1, 11, 0), of(1808, JANUARY, 1, 13, 0)),
			SexagenaryHourTestCase(32, Yi, Wei, Xin, Wei, of(1808, JANUARY, 1, 13, 0), of(1808, JANUARY, 1, 15, 0)),
			SexagenaryHourTestCase(33, Bing, Shen, Xin, Shen, of(1808, JANUARY, 1, 15, 0), of(1808, JANUARY, 1, 17, 0)),
			SexagenaryHourTestCase(34, Ding, You, Xin, You, of(1808, JANUARY, 1, 17, 0), of(1808, JANUARY, 1, 19, 0)),
			SexagenaryHourTestCase(35, WuHS, Xu, Xin, Xu, of(1808, JANUARY, 1, 19, 0), of(1808, JANUARY, 1, 21, 0)),
			SexagenaryHourTestCase(36, Ji, Hai, Xin, Hai, of(1808, JANUARY, 1, 21, 0), of(1808, JANUARY, 1, 23, 0)),
			SexagenaryHourTestCase(37, Geng, Zi, Ren, Zi, of(1808, JANUARY, 1, 23, 0), of(1808, JANUARY, 2, 1, 0)),
			SexagenaryHourTestCase(38, Xin, Chou, Ren, Chou, of(1808, JANUARY, 2, 1, 0), of(1808, JANUARY, 2, 3, 0)),
			SexagenaryHourTestCase(39, Ren, Yin, Ren, Yin, of(1808, JANUARY, 2, 3, 0), of(1808, JANUARY, 2, 5, 0)),
			SexagenaryHourTestCase(40, Gui, Mao, Ren, Mao, of(1808, JANUARY, 2, 5, 0), of(1808, JANUARY, 2, 7, 0)),
			SexagenaryHourTestCase(41, Jia, Chen, Ren, Chen, of(1808, JANUARY, 2, 7, 0), of(1808, JANUARY, 2, 9, 0)),
			SexagenaryHourTestCase(42, Yi, Si, Ren, Si, of(1808, JANUARY, 2, 9, 0), of(1808, JANUARY, 2, 11, 0)),
			SexagenaryHourTestCase(43, Bing, WuEB, Ren, WuEB, of(1808, JANUARY, 2, 11, 0), of(1808, JANUARY, 2, 13, 0)),
			SexagenaryHourTestCase(44, Ding, Wei, Ren, Wei, of(1808, JANUARY, 2, 13, 0), of(1808, JANUARY, 2, 15, 0)),
			SexagenaryHourTestCase(45, WuHS, Shen, Ren, Shen, of(1808, JANUARY, 2, 15, 0), of(1808, JANUARY, 2, 17, 0)),
			SexagenaryHourTestCase(46, Ji, You, Ren, You, of(1808, JANUARY, 2, 17, 0), of(1808, JANUARY, 2, 19, 0)),
			SexagenaryHourTestCase(47, Geng, Xu, Ren, Xu, of(1808, JANUARY, 2, 19, 0), of(1808, JANUARY, 2, 21, 0)),
			SexagenaryHourTestCase(48, Xin, Hai, Ren, Hai, of(1808, JANUARY, 2, 21, 0), of(1808, JANUARY, 2, 23, 0)),
			SexagenaryHourTestCase(49, Ren, Zi, Gui, Zi, of(1808, JANUARY, 2, 23, 0), of(1808, JANUARY, 3, 1, 0)),
			SexagenaryHourTestCase(50, Gui, Chou, Gui, Chou, of(1808, JANUARY, 3, 1, 0), of(1808, JANUARY, 3, 3, 0)),
			SexagenaryHourTestCase(51, Jia, Yin, Gui, Yin, of(1808, JANUARY, 3, 3, 0), of(1808, JANUARY, 3, 5, 0)),
			SexagenaryHourTestCase(52, Yi, Mao, Gui, Mao, of(1808, JANUARY, 3, 5, 0), of(1808, JANUARY, 3, 7, 0)),
			SexagenaryHourTestCase(53, Bing, Chen, Gui, Chen, of(1808, JANUARY, 3, 7, 0), of(1808, JANUARY, 3, 9, 0)),
			SexagenaryHourTestCase(54, Ding, Si, Gui, Si, of(1808, JANUARY, 3, 9, 0), of(1808, JANUARY, 3, 11, 0)),
			SexagenaryHourTestCase(55, WuHS, WuEB, Gui, WuEB, of(1808, JANUARY, 3, 11, 0), of(1808, JANUARY, 3, 13, 0)),
			SexagenaryHourTestCase(56, Ji, Wei, Gui, Wei, of(1808, JANUARY, 3, 13, 0), of(1808, JANUARY, 3, 15, 0)),
			SexagenaryHourTestCase(57, Geng, Shen, Gui, Shen, of(1808, JANUARY, 3, 15, 0), of(1808, JANUARY, 3, 17, 0)),
			SexagenaryHourTestCase(58, Xin, You, Gui, You, of(1808, JANUARY, 3, 17, 0), of(1808, JANUARY, 3, 19, 0)),
			SexagenaryHourTestCase(59, Ren, Xu, Gui, Xu, of(1808, JANUARY, 3, 19, 0), of(1808, JANUARY, 3, 21, 0)),
			SexagenaryHourTestCase(60, Gui, Hai, Gui, Hai, of(1808, JANUARY, 3, 21, 0), of(1808, JANUARY, 3, 23, 0)),
			//@formatter:on
		)

		val ALL_KNOWN_CYCLES: List<List<SexagenaryHourTestCase>> = listOf(
			CYCLE_18071224,
			CYCLE_18071229,
		)
	}
}
