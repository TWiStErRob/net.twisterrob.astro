package net.twisterrob.astro.bazi

import net.twisterrob.astro.bazi.lookup.atHour
import net.twisterrob.astro.bazi.lookup.sexagenaryCycle
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
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS

/**
 * Based on the Group 44 PDF (bazi-knowledge-manual-calculation-pdf-free.pdf).
 * Ten Thousand (10000) Year Calendar / Wan Nian Li (万年历).
 * Hsia Calendar (夏历)
 */
public class Group44Calculator : BaZiCalculator {

	override fun calculate(dateTime: LocalDateTime): BaZi {
		val year = calculateYear(dateTime)
		val month = calculateMonth(dateTime)
		val day = calculateDay(dateTime)
		val hour = calculateHour(dateTime)
		return BaZi(year, month, day, hour)
	}

	/**
	 * Solve `2637 + x = 60 y`, for `x` is the year and `y` is the cycle count.
	 */

	internal fun calculateYear(date: LocalDateTime): BaZi.Pillar {
		//val cycles = (2637 + date.year) / 60
		val remainder = (2637 + date.year) % 60
		val adjusted = remainder.let { if (it == 0) 60 else it }
		return BaZi.Pillar.sexagenaryCycle(adjusted - 1)
	}

	internal fun calculateMonthTable(date: LocalDateTime): BaZi.Pillar {
		val month = date.monthValue - 1
		val yearStem = calculateYear(date).heavenlyStem
		val heavenlyStem = tableMonth.getValue(yearStem)[month]
		return BaZi.Pillar(heavenlyStem, months[month])
	}

	internal fun calculateMonth(date: LocalDateTime): BaZi.Pillar {
		// 年干
		val yearlyStem = calculateYear(date).heavenlyStem
		val yearlyStemLabel = monthHeavenlyStemNumbers.getValue(yearlyStem)

		// 月支
		val monthlyBranch = months[date.monthValue - 1]
		val monthlyBranchLabel = monthEarthlyBranchNumbers.getValue(monthlyBranch)

		val offset = heavenlyStemOffsets.getValue(yearlyStem)

		val labelSum = yearlyStemLabel + monthlyBranchLabel + offset
		val adjusted = (labelSum % 10).let { if (it == 0) 10 else it }

		return BaZi.Pillar(HeavenlyStem.at(adjusted), monthlyBranch)
	}

	/**
	 * Using reference point 1944-01-01 as it is a Jia (1) Zi (1) day.
	 */
	internal fun calculateDay(date: LocalDateTime): BaZi.Pillar {
		when {
			date.year >= 1944 -> {
				val daysSinceReference =
					(date.year - 1944) * 365 + leapYearsBetween(1944, date.year) + (date.dayOfYear - 1)
				val position = (daysSinceReference % 60).let { if (it == 0) 60 else it }

				return BaZi.Pillar.sexagenaryCycle(position - 1)
			}

			else -> {
				val daysUntilReference =
					(date.year - 1944) * 365 - leapYearsBetween(date.year, 1944) + (date.dayOfYear - 1)
				val position = 60 + (daysUntilReference + 1) % 60
				return BaZi.Pillar.sexagenaryCycle(position - 1)
			}
		}
	}

	internal fun leapYearsBetween(year1: Int, year2: Int): Int {
		val leapYearsBeforeYear1 = (year1 - 1) / 4 - (year1 - 1) / 100 + (year1 - 1) / 400
		val leapYearsBeforeYear2 = (year2 - 1) / 4 - (year2 - 1) / 100 + (year2 - 1) / 400
		return leapYearsBeforeYear2 - leapYearsBeforeYear1
	}

	internal fun calculateHour(date: LocalDateTime): BaZi.Pillar {
		// 时支
		val hourlyBranch = EarthlyBranch.atHour(date.hour)
		// 日干
		val dayStem = calculateDay(date).heavenlyStem

		val labelSum =
			hourHeavenlyStemNumbers.getValue(dayStem) + heavenlyStemOffsets.getValue(dayStem) + hourlyBranch.order
		val adjusted = (labelSum % 10).let { if (it == 0) 10 else it }

		return BaZi.Pillar(HeavenlyStem.at(adjusted), hourlyBranch)
	}

	public companion object {

		/**
		 * 12 Earthly Branches for month, starting with [EarthlyBranch.Yin] ([Zodiac.Tiger]).
		 */
		private val months: List<EarthlyBranch> = listOf(
			Yin, Mao, Chen, Si, WuEB, Wei, Shen, You, Xu, Hai, Zi, Chou
		)

		private val tableMonth: Map<HeavenlyStem, List<HeavenlyStem>> = mapOf(
			Jia to listOf(Bing, Ding, WuHS, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding),
			Yi to listOf(Bing, Ding, WuHS, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding),
			Bing to listOf(WuHS, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, WuHS, Ji),
			Ding to listOf(WuHS, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, WuHS, Ji),
			WuHS to listOf(Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, WuHS, Ji, Geng, Xin),
			Ji to listOf(Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, WuHS, Ji, Geng, Xin),
			Geng to listOf(Ren, Gui, Jia, Yi, Bing, Ding, WuHS, Ji, Geng, Xin, Ren, Gui),
			Xin to listOf(Ren, Gui, Jia, Yi, Bing, Ding, WuHS, Ji, Geng, Xin, Ren, Gui),
			Ren to listOf(Jia, Yi, Bing, Ding, WuHS, Ji, Geng, Xin, Ren, Gui, Jia, Yi),
			Gui to listOf(Jia, Yi, Bing, Ding, WuHS, Ji, Geng, Xin, Ren, Gui, Jia, Yi),
		)

		@Suppress("RemoveRedundantQualifierName")
		private val monthEarthlyBranchNumbers: Map<EarthlyBranch, Int> = mapOf(
			EarthlyBranch.Yin to 3,
			EarthlyBranch.Mao to 4,
			EarthlyBranch.Chen to 5,
			EarthlyBranch.Si to 6,
			EarthlyBranch.Wu to 7,
			EarthlyBranch.Wei to 8,
			EarthlyBranch.Shen to 9,
			EarthlyBranch.You to 10,
			EarthlyBranch.Xu to 11,
			EarthlyBranch.Hai to 12,
			EarthlyBranch.Zi to 13,
			EarthlyBranch.Chou to 14,
		)

		@Suppress("RemoveRedundantQualifierName")
		private val monthHeavenlyStemNumbers: Map<HeavenlyStem, Int> = mapOf(
			HeavenlyStem.Jia to 1,
			HeavenlyStem.Yi to 1,
			HeavenlyStem.Bing to 2,
			HeavenlyStem.Ding to 2,
			HeavenlyStem.Wu to 3,
			HeavenlyStem.Ji to 3,
			HeavenlyStem.Geng to 4,
			HeavenlyStem.Xin to 4,
			HeavenlyStem.Ren to 5,
			HeavenlyStem.Gui to 5,
		)

		@Suppress("RemoveRedundantQualifierName")
		private val hourHeavenlyStemNumbers: Map<HeavenlyStem, Int> = mapOf(
			HeavenlyStem.Jia to 1,
			HeavenlyStem.Yi to 2,
			HeavenlyStem.Bing to 3,
			HeavenlyStem.Ding to 4,
			HeavenlyStem.Wu to 5,
			HeavenlyStem.Ji to 1,
			HeavenlyStem.Geng to 2,
			HeavenlyStem.Xin to 3,
			HeavenlyStem.Ren to 4,
			HeavenlyStem.Gui to 5,
		)

		@Suppress("RemoveRedundantQualifierName", "UnusedUnaryOperator")
		private val heavenlyStemOffsets: Map<HeavenlyStem, Int> = mapOf(
			HeavenlyStem.Jia to -1,
			HeavenlyStem.Yi to -1,
			HeavenlyStem.Bing to 0,
			HeavenlyStem.Ding to 0,
			HeavenlyStem.Wu to +1,
			HeavenlyStem.Ji to +1,
			HeavenlyStem.Geng to +2,
			HeavenlyStem.Xin to +2,
			HeavenlyStem.Ren to +3,
			HeavenlyStem.Gui to +3,
		)
	}
}
