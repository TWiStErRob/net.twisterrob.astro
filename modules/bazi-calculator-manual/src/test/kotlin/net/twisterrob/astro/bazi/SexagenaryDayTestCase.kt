package net.twisterrob.astro.bazi

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
import java.time.LocalDate
import java.time.Month.APRIL
import java.time.Month.AUGUST
import java.time.Month.DECEMBER
import java.time.Month.FEBRUARY
import java.time.Month.JANUARY
import java.time.Month.JULY
import java.time.Month.MARCH
import java.time.Month.NOVEMBER
import java.time.Month.SEPTEMBER
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS

/**
 * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_hours
 */
class SexagenaryDayTestCase(
	val cyclicOrdinal: Int,
	val dayStem: HeavenlyStem,
	val dayBranch: EarthlyBranch,
	val date: LocalDate,
) {

	val dayPillar: BaZi.Pillar
		get() = BaZi.Pillar(dayStem, dayBranch)

	@Suppress("detekt.NamedArguments")
	companion object {

		/**
		 * A cycle that starts on 1st January, which is just a coincidence.
		 * There aren't too many of these years, this year just happened to be one.
		 */
		val CYCLE_20240101 = listOf(
			SexagenaryDayTestCase(1, Jia, Zi, LocalDate.of(2024, JANUARY, 1)),
			SexagenaryDayTestCase(2, Yi, Chou, LocalDate.of(2024, JANUARY, 2)),
			SexagenaryDayTestCase(3, Bing, Yin, LocalDate.of(2024, JANUARY, 3)),
			SexagenaryDayTestCase(4, Ding, Mao, LocalDate.of(2024, JANUARY, 4)),
			SexagenaryDayTestCase(5, WuHS, Chen, LocalDate.of(2024, JANUARY, 5)),
			SexagenaryDayTestCase(6, Ji, Si, LocalDate.of(2024, JANUARY, 6)),
			SexagenaryDayTestCase(7, Geng, WuEB, LocalDate.of(2024, JANUARY, 7)),
			SexagenaryDayTestCase(8, Xin, Wei, LocalDate.of(2024, JANUARY, 8)),
			SexagenaryDayTestCase(9, Ren, Shen, LocalDate.of(2024, JANUARY, 9)),
			SexagenaryDayTestCase(10, Gui, You, LocalDate.of(2024, JANUARY, 10)),
			SexagenaryDayTestCase(11, Jia, Xu, LocalDate.of(2024, JANUARY, 11)),
			SexagenaryDayTestCase(12, Yi, Hai, LocalDate.of(2024, JANUARY, 12)),
			SexagenaryDayTestCase(13, Bing, Zi, LocalDate.of(2024, JANUARY, 13)),
			SexagenaryDayTestCase(14, Ding, Chou, LocalDate.of(2024, JANUARY, 14)),
			SexagenaryDayTestCase(15, WuHS, Yin, LocalDate.of(2024, JANUARY, 15)),
			SexagenaryDayTestCase(16, Ji, Mao, LocalDate.of(2024, JANUARY, 16)),
			SexagenaryDayTestCase(17, Geng, Chen, LocalDate.of(2024, JANUARY, 17)),
			SexagenaryDayTestCase(18, Xin, Si, LocalDate.of(2024, JANUARY, 18)),
			SexagenaryDayTestCase(19, Ren, WuEB, LocalDate.of(2024, JANUARY, 19)),
			SexagenaryDayTestCase(20, Gui, Wei, LocalDate.of(2024, JANUARY, 20)),
			SexagenaryDayTestCase(21, Jia, Shen, LocalDate.of(2024, JANUARY, 21)),
			SexagenaryDayTestCase(22, Yi, You, LocalDate.of(2024, JANUARY, 22)),
			SexagenaryDayTestCase(23, Bing, Xu, LocalDate.of(2024, JANUARY, 23)),
			SexagenaryDayTestCase(24, Ding, Hai, LocalDate.of(2024, JANUARY, 24)),
			SexagenaryDayTestCase(25, WuHS, Zi, LocalDate.of(2024, JANUARY, 25)),
			SexagenaryDayTestCase(26, Ji, Chou, LocalDate.of(2024, JANUARY, 26)),
			SexagenaryDayTestCase(27, Geng, Yin, LocalDate.of(2024, JANUARY, 27)),
			SexagenaryDayTestCase(28, Xin, Mao, LocalDate.of(2024, JANUARY, 28)),
			SexagenaryDayTestCase(29, Ren, Chen, LocalDate.of(2024, JANUARY, 29)),
			SexagenaryDayTestCase(30, Gui, Si, LocalDate.of(2024, JANUARY, 30)),
			SexagenaryDayTestCase(31, Jia, WuEB, LocalDate.of(2024, JANUARY, 31)),
			SexagenaryDayTestCase(32, Yi, Wei, LocalDate.of(2024, FEBRUARY, 1)),
			SexagenaryDayTestCase(33, Bing, Shen, LocalDate.of(2024, FEBRUARY, 2)),
			SexagenaryDayTestCase(34, Ding, You, LocalDate.of(2024, FEBRUARY, 3)),
			SexagenaryDayTestCase(35, WuHS, Xu, LocalDate.of(2024, FEBRUARY, 4)),
			SexagenaryDayTestCase(36, Ji, Hai, LocalDate.of(2024, FEBRUARY, 5)),
			SexagenaryDayTestCase(37, Geng, Zi, LocalDate.of(2024, FEBRUARY, 6)),
			SexagenaryDayTestCase(38, Xin, Chou, LocalDate.of(2024, FEBRUARY, 7)),
			SexagenaryDayTestCase(39, Ren, Yin, LocalDate.of(2024, FEBRUARY, 8)),
			SexagenaryDayTestCase(40, Gui, Mao, LocalDate.of(2024, FEBRUARY, 9)),
			SexagenaryDayTestCase(41, Jia, Chen, LocalDate.of(2024, FEBRUARY, 10)),
			SexagenaryDayTestCase(42, Yi, Si, LocalDate.of(2024, FEBRUARY, 11)),
			SexagenaryDayTestCase(43, Bing, WuEB, LocalDate.of(2024, FEBRUARY, 12)),
			SexagenaryDayTestCase(44, Ding, Wei, LocalDate.of(2024, FEBRUARY, 13)),
			SexagenaryDayTestCase(45, WuHS, Shen, LocalDate.of(2024, FEBRUARY, 14)),
			SexagenaryDayTestCase(46, Ji, You, LocalDate.of(2024, FEBRUARY, 15)),
			SexagenaryDayTestCase(47, Geng, Xu, LocalDate.of(2024, FEBRUARY, 16)),
			SexagenaryDayTestCase(48, Xin, Hai, LocalDate.of(2024, FEBRUARY, 17)),
			SexagenaryDayTestCase(49, Ren, Zi, LocalDate.of(2024, FEBRUARY, 18)),
			SexagenaryDayTestCase(50, Gui, Chou, LocalDate.of(2024, FEBRUARY, 19)),
			SexagenaryDayTestCase(51, Jia, Yin, LocalDate.of(2024, FEBRUARY, 20)),
			SexagenaryDayTestCase(52, Yi, Mao, LocalDate.of(2024, FEBRUARY, 21)),
			SexagenaryDayTestCase(53, Bing, Chen, LocalDate.of(2024, FEBRUARY, 22)),
			SexagenaryDayTestCase(54, Ding, Si, LocalDate.of(2024, FEBRUARY, 23)),
			SexagenaryDayTestCase(55, WuHS, WuEB, LocalDate.of(2024, FEBRUARY, 24)),
			SexagenaryDayTestCase(56, Ji, Wei, LocalDate.of(2024, FEBRUARY, 25)),
			SexagenaryDayTestCase(57, Geng, Shen, LocalDate.of(2024, FEBRUARY, 26)),
			SexagenaryDayTestCase(58, Xin, You, LocalDate.of(2024, FEBRUARY, 27)),
			SexagenaryDayTestCase(59, Ren, Xu, LocalDate.of(2024, FEBRUARY, 28)),
			SexagenaryDayTestCase(60, Gui, Hai, LocalDate.of(2024, FEBRUARY, 29)),
		)

		/**
		 * A cycle that starts on 1st of a month, which is just a coincidence.
		 * It's also a leap year, the cycle starts just after the Gregorian leap day.
		 */
		val CYCLE_20240301 = listOf(
			SexagenaryDayTestCase(1, Jia, Zi, LocalDate.of(2024, MARCH, 1)),
			SexagenaryDayTestCase(2, Yi, Chou, LocalDate.of(2024, MARCH, 2)),
			SexagenaryDayTestCase(3, Bing, Yin, LocalDate.of(2024, MARCH, 3)),
			SexagenaryDayTestCase(4, Ding, Mao, LocalDate.of(2024, MARCH, 4)),
			SexagenaryDayTestCase(5, WuHS, Chen, LocalDate.of(2024, MARCH, 5)),
			SexagenaryDayTestCase(6, Ji, Si, LocalDate.of(2024, MARCH, 6)),
			SexagenaryDayTestCase(7, Geng, WuEB, LocalDate.of(2024, MARCH, 7)),
			SexagenaryDayTestCase(8, Xin, Wei, LocalDate.of(2024, MARCH, 8)),
			SexagenaryDayTestCase(9, Ren, Shen, LocalDate.of(2024, MARCH, 9)),
			SexagenaryDayTestCase(10, Gui, You, LocalDate.of(2024, MARCH, 10)),
			SexagenaryDayTestCase(11, Jia, Xu, LocalDate.of(2024, MARCH, 11)),
			SexagenaryDayTestCase(12, Yi, Hai, LocalDate.of(2024, MARCH, 12)),
			SexagenaryDayTestCase(13, Bing, Zi, LocalDate.of(2024, MARCH, 13)),
			SexagenaryDayTestCase(14, Ding, Chou, LocalDate.of(2024, MARCH, 14)),
			SexagenaryDayTestCase(15, WuHS, Yin, LocalDate.of(2024, MARCH, 15)),
			SexagenaryDayTestCase(16, Ji, Mao, LocalDate.of(2024, MARCH, 16)),
			SexagenaryDayTestCase(17, Geng, Chen, LocalDate.of(2024, MARCH, 17)),
			SexagenaryDayTestCase(18, Xin, Si, LocalDate.of(2024, MARCH, 18)),
			SexagenaryDayTestCase(19, Ren, WuEB, LocalDate.of(2024, MARCH, 19)),
			SexagenaryDayTestCase(20, Gui, Wei, LocalDate.of(2024, MARCH, 20)),
			SexagenaryDayTestCase(21, Jia, Shen, LocalDate.of(2024, MARCH, 21)),
			SexagenaryDayTestCase(22, Yi, You, LocalDate.of(2024, MARCH, 22)),
			SexagenaryDayTestCase(23, Bing, Xu, LocalDate.of(2024, MARCH, 23)),
			SexagenaryDayTestCase(24, Ding, Hai, LocalDate.of(2024, MARCH, 24)),
			SexagenaryDayTestCase(25, WuHS, Zi, LocalDate.of(2024, MARCH, 25)),
			SexagenaryDayTestCase(26, Ji, Chou, LocalDate.of(2024, MARCH, 26)),
			SexagenaryDayTestCase(27, Geng, Yin, LocalDate.of(2024, MARCH, 27)),
			SexagenaryDayTestCase(28, Xin, Mao, LocalDate.of(2024, MARCH, 28)),
			SexagenaryDayTestCase(29, Ren, Chen, LocalDate.of(2024, MARCH, 29)),
			SexagenaryDayTestCase(30, Gui, Si, LocalDate.of(2024, MARCH, 30)),
			SexagenaryDayTestCase(31, Jia, WuEB, LocalDate.of(2024, MARCH, 31)),
			SexagenaryDayTestCase(32, Yi, Wei, LocalDate.of(2024, APRIL, 1)),
			SexagenaryDayTestCase(33, Bing, Shen, LocalDate.of(2024, APRIL, 2)),
			SexagenaryDayTestCase(34, Ding, You, LocalDate.of(2024, APRIL, 3)),
			SexagenaryDayTestCase(35, WuHS, Xu, LocalDate.of(2024, APRIL, 4)),
			SexagenaryDayTestCase(36, Ji, Hai, LocalDate.of(2024, APRIL, 5)),
			SexagenaryDayTestCase(37, Geng, Zi, LocalDate.of(2024, APRIL, 6)),
			SexagenaryDayTestCase(38, Xin, Chou, LocalDate.of(2024, APRIL, 7)),
			SexagenaryDayTestCase(39, Ren, Yin, LocalDate.of(2024, APRIL, 8)),
			SexagenaryDayTestCase(40, Gui, Mao, LocalDate.of(2024, APRIL, 9)),
			SexagenaryDayTestCase(41, Jia, Chen, LocalDate.of(2024, APRIL, 10)),
			SexagenaryDayTestCase(42, Yi, Si, LocalDate.of(2024, APRIL, 11)),
			SexagenaryDayTestCase(43, Bing, WuEB, LocalDate.of(2024, APRIL, 12)),
			SexagenaryDayTestCase(44, Ding, Wei, LocalDate.of(2024, APRIL, 13)),
			SexagenaryDayTestCase(45, WuHS, Shen, LocalDate.of(2024, APRIL, 14)),
			SexagenaryDayTestCase(46, Ji, You, LocalDate.of(2024, APRIL, 15)),
			SexagenaryDayTestCase(47, Geng, Xu, LocalDate.of(2024, APRIL, 16)),
			SexagenaryDayTestCase(48, Xin, Hai, LocalDate.of(2024, APRIL, 17)),
			SexagenaryDayTestCase(49, Ren, Zi, LocalDate.of(2024, APRIL, 18)),
			SexagenaryDayTestCase(50, Gui, Chou, LocalDate.of(2024, APRIL, 19)),
			SexagenaryDayTestCase(51, Jia, Yin, LocalDate.of(2024, APRIL, 20)),
			SexagenaryDayTestCase(52, Yi, Mao, LocalDate.of(2024, APRIL, 21)),
			SexagenaryDayTestCase(53, Bing, Chen, LocalDate.of(2024, APRIL, 22)),
			SexagenaryDayTestCase(54, Ding, Si, LocalDate.of(2024, APRIL, 23)),
			SexagenaryDayTestCase(55, WuHS, WuEB, LocalDate.of(2024, APRIL, 24)),
			SexagenaryDayTestCase(56, Ji, Wei, LocalDate.of(2024, APRIL, 25)),
			SexagenaryDayTestCase(57, Geng, Shen, LocalDate.of(2024, APRIL, 26)),
			SexagenaryDayTestCase(58, Xin, You, LocalDate.of(2024, APRIL, 27)),
			SexagenaryDayTestCase(59, Ren, Xu, LocalDate.of(2024, APRIL, 28)),
			SexagenaryDayTestCase(60, Gui, Hai, LocalDate.of(2024, APRIL, 29)),
		)

		/**
		 * A summer cycle that starts in the middle of the month. It's not a leap year.
		 */
		val CYCLE_20220710 = listOf(
			SexagenaryDayTestCase(1, Jia, Zi, LocalDate.of(2022, JULY, 10)),
			SexagenaryDayTestCase(2, Yi, Chou, LocalDate.of(2022, JULY, 11)),
			SexagenaryDayTestCase(3, Bing, Yin, LocalDate.of(2022, JULY, 12)),
			SexagenaryDayTestCase(4, Ding, Mao, LocalDate.of(2022, JULY, 13)),
			SexagenaryDayTestCase(5, WuHS, Chen, LocalDate.of(2022, JULY, 14)),
			SexagenaryDayTestCase(6, Ji, Si, LocalDate.of(2022, JULY, 15)),
			SexagenaryDayTestCase(7, Geng, WuEB, LocalDate.of(2022, JULY, 16)),
			SexagenaryDayTestCase(8, Xin, Wei, LocalDate.of(2022, JULY, 17)),
			SexagenaryDayTestCase(9, Ren, Shen, LocalDate.of(2022, JULY, 18)),
			SexagenaryDayTestCase(10, Gui, You, LocalDate.of(2022, JULY, 19)),
			SexagenaryDayTestCase(11, Jia, Xu, LocalDate.of(2022, JULY, 20)),
			SexagenaryDayTestCase(12, Yi, Hai, LocalDate.of(2022, JULY, 21)),
			SexagenaryDayTestCase(13, Bing, Zi, LocalDate.of(2022, JULY, 22)),
			SexagenaryDayTestCase(14, Ding, Chou, LocalDate.of(2022, JULY, 23)),
			SexagenaryDayTestCase(15, WuHS, Yin, LocalDate.of(2022, JULY, 24)),
			SexagenaryDayTestCase(16, Ji, Mao, LocalDate.of(2022, JULY, 25)),
			SexagenaryDayTestCase(17, Geng, Chen, LocalDate.of(2022, JULY, 26)),
			SexagenaryDayTestCase(18, Xin, Si, LocalDate.of(2022, JULY, 27)),
			SexagenaryDayTestCase(19, Ren, WuEB, LocalDate.of(2022, JULY, 28)),
			SexagenaryDayTestCase(20, Gui, Wei, LocalDate.of(2022, JULY, 29)),
			SexagenaryDayTestCase(21, Jia, Shen, LocalDate.of(2022, JULY, 30)),
			SexagenaryDayTestCase(22, Yi, You, LocalDate.of(2022, JULY, 31)),
			SexagenaryDayTestCase(23, Bing, Xu, LocalDate.of(2022, AUGUST, 1)),
			SexagenaryDayTestCase(24, Ding, Hai, LocalDate.of(2022, AUGUST, 2)),
			SexagenaryDayTestCase(25, WuHS, Zi, LocalDate.of(2022, AUGUST, 3)),
			SexagenaryDayTestCase(26, Ji, Chou, LocalDate.of(2022, AUGUST, 4)),
			SexagenaryDayTestCase(27, Geng, Yin, LocalDate.of(2022, AUGUST, 5)),
			SexagenaryDayTestCase(28, Xin, Mao, LocalDate.of(2022, AUGUST, 6)),
			SexagenaryDayTestCase(29, Ren, Chen, LocalDate.of(2022, AUGUST, 7)),
			SexagenaryDayTestCase(30, Gui, Si, LocalDate.of(2022, AUGUST, 8)),
			SexagenaryDayTestCase(31, Jia, WuEB, LocalDate.of(2022, AUGUST, 9)),
			SexagenaryDayTestCase(32, Yi, Wei, LocalDate.of(2022, AUGUST, 10)),
			SexagenaryDayTestCase(33, Bing, Shen, LocalDate.of(2022, AUGUST, 11)),
			SexagenaryDayTestCase(34, Ding, You, LocalDate.of(2022, AUGUST, 12)),
			SexagenaryDayTestCase(35, WuHS, Xu, LocalDate.of(2022, AUGUST, 13)),
			SexagenaryDayTestCase(36, Ji, Hai, LocalDate.of(2022, AUGUST, 14)),
			SexagenaryDayTestCase(37, Geng, Zi, LocalDate.of(2022, AUGUST, 15)),
			SexagenaryDayTestCase(38, Xin, Chou, LocalDate.of(2022, AUGUST, 16)),
			SexagenaryDayTestCase(39, Ren, Yin, LocalDate.of(2022, AUGUST, 17)),
			SexagenaryDayTestCase(40, Gui, Mao, LocalDate.of(2022, AUGUST, 18)),
			SexagenaryDayTestCase(41, Jia, Chen, LocalDate.of(2022, AUGUST, 19)),
			SexagenaryDayTestCase(42, Yi, Si, LocalDate.of(2022, AUGUST, 20)),
			SexagenaryDayTestCase(43, Bing, WuEB, LocalDate.of(2022, AUGUST, 21)),
			SexagenaryDayTestCase(44, Ding, Wei, LocalDate.of(2022, AUGUST, 22)),
			SexagenaryDayTestCase(45, WuHS, Shen, LocalDate.of(2022, AUGUST, 23)),
			SexagenaryDayTestCase(46, Ji, You, LocalDate.of(2022, AUGUST, 24)),
			SexagenaryDayTestCase(47, Geng, Xu, LocalDate.of(2022, AUGUST, 25)),
			SexagenaryDayTestCase(48, Xin, Hai, LocalDate.of(2022, AUGUST, 26)),
			SexagenaryDayTestCase(49, Ren, Zi, LocalDate.of(2022, AUGUST, 27)),
			SexagenaryDayTestCase(50, Gui, Chou, LocalDate.of(2022, AUGUST, 28)),
			SexagenaryDayTestCase(51, Jia, Yin, LocalDate.of(2022, AUGUST, 29)),
			SexagenaryDayTestCase(52, Yi, Mao, LocalDate.of(2022, AUGUST, 30)),
			SexagenaryDayTestCase(53, Bing, Chen, LocalDate.of(2022, AUGUST, 31)),
			SexagenaryDayTestCase(54, Ding, Si, LocalDate.of(2022, SEPTEMBER, 1)),
			SexagenaryDayTestCase(55, WuHS, WuEB, LocalDate.of(2022, SEPTEMBER, 2)),
			SexagenaryDayTestCase(56, Ji, Wei, LocalDate.of(2022, SEPTEMBER, 3)),
			SexagenaryDayTestCase(57, Geng, Shen, LocalDate.of(2022, SEPTEMBER, 4)),
			SexagenaryDayTestCase(58, Xin, You, LocalDate.of(2022, SEPTEMBER, 5)),
			SexagenaryDayTestCase(59, Ren, Xu, LocalDate.of(2022, SEPTEMBER, 6)),
			SexagenaryDayTestCase(60, Gui, Hai, LocalDate.of(2022, SEPTEMBER, 7)),
		)

		/**
		 * A winter cycle that starts in the middle of the month. It's not a leap year.
		 * The end of the cycle also wraps over to the next year.
		 */
		val CYCLE_20221107 = listOf(
			SexagenaryDayTestCase(1, Jia, Zi, LocalDate.of(2022, NOVEMBER, 7)),
			SexagenaryDayTestCase(2, Yi, Chou, LocalDate.of(2022, NOVEMBER, 8)),
			SexagenaryDayTestCase(3, Bing, Yin, LocalDate.of(2022, NOVEMBER, 9)),
			SexagenaryDayTestCase(4, Ding, Mao, LocalDate.of(2022, NOVEMBER, 10)),
			SexagenaryDayTestCase(5, WuHS, Chen, LocalDate.of(2022, NOVEMBER, 11)),
			SexagenaryDayTestCase(6, Ji, Si, LocalDate.of(2022, NOVEMBER, 12)),
			SexagenaryDayTestCase(7, Geng, WuEB, LocalDate.of(2022, NOVEMBER, 13)),
			SexagenaryDayTestCase(8, Xin, Wei, LocalDate.of(2022, NOVEMBER, 14)),
			SexagenaryDayTestCase(9, Ren, Shen, LocalDate.of(2022, NOVEMBER, 15)),
			SexagenaryDayTestCase(10, Gui, You, LocalDate.of(2022, NOVEMBER, 16)),
			SexagenaryDayTestCase(11, Jia, Xu, LocalDate.of(2022, NOVEMBER, 17)),
			SexagenaryDayTestCase(12, Yi, Hai, LocalDate.of(2022, NOVEMBER, 18)),
			SexagenaryDayTestCase(13, Bing, Zi, LocalDate.of(2022, NOVEMBER, 19)),
			SexagenaryDayTestCase(14, Ding, Chou, LocalDate.of(2022, NOVEMBER, 20)),
			SexagenaryDayTestCase(15, WuHS, Yin, LocalDate.of(2022, NOVEMBER, 21)),
			SexagenaryDayTestCase(16, Ji, Mao, LocalDate.of(2022, NOVEMBER, 22)),
			SexagenaryDayTestCase(17, Geng, Chen, LocalDate.of(2022, NOVEMBER, 23)),
			SexagenaryDayTestCase(18, Xin, Si, LocalDate.of(2022, NOVEMBER, 24)),
			SexagenaryDayTestCase(19, Ren, WuEB, LocalDate.of(2022, NOVEMBER, 25)),
			SexagenaryDayTestCase(20, Gui, Wei, LocalDate.of(2022, NOVEMBER, 26)),
			SexagenaryDayTestCase(21, Jia, Shen, LocalDate.of(2022, NOVEMBER, 27)),
			SexagenaryDayTestCase(22, Yi, You, LocalDate.of(2022, NOVEMBER, 28)),
			SexagenaryDayTestCase(23, Bing, Xu, LocalDate.of(2022, NOVEMBER, 29)),
			SexagenaryDayTestCase(24, Ding, Hai, LocalDate.of(2022, NOVEMBER, 30)),
			SexagenaryDayTestCase(25, WuHS, Zi, LocalDate.of(2022, DECEMBER, 1)),
			SexagenaryDayTestCase(26, Ji, Chou, LocalDate.of(2022, DECEMBER, 2)),
			SexagenaryDayTestCase(27, Geng, Yin, LocalDate.of(2022, DECEMBER, 3)),
			SexagenaryDayTestCase(28, Xin, Mao, LocalDate.of(2022, DECEMBER, 4)),
			SexagenaryDayTestCase(29, Ren, Chen, LocalDate.of(2022, DECEMBER, 5)),
			SexagenaryDayTestCase(30, Gui, Si, LocalDate.of(2022, DECEMBER, 6)),
			SexagenaryDayTestCase(31, Jia, WuEB, LocalDate.of(2022, DECEMBER, 7)),
			SexagenaryDayTestCase(32, Yi, Wei, LocalDate.of(2022, DECEMBER, 8)),
			SexagenaryDayTestCase(33, Bing, Shen, LocalDate.of(2022, DECEMBER, 9)),
			SexagenaryDayTestCase(34, Ding, You, LocalDate.of(2022, DECEMBER, 10)),
			SexagenaryDayTestCase(35, WuHS, Xu, LocalDate.of(2022, DECEMBER, 11)),
			SexagenaryDayTestCase(36, Ji, Hai, LocalDate.of(2022, DECEMBER, 12)),
			SexagenaryDayTestCase(37, Geng, Zi, LocalDate.of(2022, DECEMBER, 13)),
			SexagenaryDayTestCase(38, Xin, Chou, LocalDate.of(2022, DECEMBER, 14)),
			SexagenaryDayTestCase(39, Ren, Yin, LocalDate.of(2022, DECEMBER, 15)),
			SexagenaryDayTestCase(40, Gui, Mao, LocalDate.of(2022, DECEMBER, 16)),
			SexagenaryDayTestCase(41, Jia, Chen, LocalDate.of(2022, DECEMBER, 17)),
			SexagenaryDayTestCase(42, Yi, Si, LocalDate.of(2022, DECEMBER, 18)),
			SexagenaryDayTestCase(43, Bing, WuEB, LocalDate.of(2022, DECEMBER, 19)),
			SexagenaryDayTestCase(44, Ding, Wei, LocalDate.of(2022, DECEMBER, 20)),
			SexagenaryDayTestCase(45, WuHS, Shen, LocalDate.of(2022, DECEMBER, 21)),
			SexagenaryDayTestCase(46, Ji, You, LocalDate.of(2022, DECEMBER, 22)),
			SexagenaryDayTestCase(47, Geng, Xu, LocalDate.of(2022, DECEMBER, 23)),
			SexagenaryDayTestCase(48, Xin, Hai, LocalDate.of(2022, DECEMBER, 24)),
			SexagenaryDayTestCase(49, Ren, Zi, LocalDate.of(2022, DECEMBER, 25)),
			SexagenaryDayTestCase(50, Gui, Chou, LocalDate.of(2022, DECEMBER, 26)),
			SexagenaryDayTestCase(51, Jia, Yin, LocalDate.of(2022, DECEMBER, 27)),
			SexagenaryDayTestCase(52, Yi, Mao, LocalDate.of(2022, DECEMBER, 28)),
			SexagenaryDayTestCase(53, Bing, Chen, LocalDate.of(2022, DECEMBER, 29)),
			SexagenaryDayTestCase(54, Ding, Si, LocalDate.of(2022, DECEMBER, 30)),
			SexagenaryDayTestCase(55, WuHS, WuEB, LocalDate.of(2022, DECEMBER, 31)),
			SexagenaryDayTestCase(56, Ji, Wei, LocalDate.of(2023, JANUARY, 1)),
			SexagenaryDayTestCase(57, Geng, Shen, LocalDate.of(2023, JANUARY, 2)),
			SexagenaryDayTestCase(58, Xin, You, LocalDate.of(2023, JANUARY, 3)),
			SexagenaryDayTestCase(59, Ren, Xu, LocalDate.of(2023, JANUARY, 4)),
			SexagenaryDayTestCase(60, Gui, Hai, LocalDate.of(2023, JANUARY, 5)),
		)

		val CYCLES = listOf(
			CYCLE_20220710,
			CYCLE_20221107,
			CYCLE_20240101,
			CYCLE_20240301,
		)
	}
}
