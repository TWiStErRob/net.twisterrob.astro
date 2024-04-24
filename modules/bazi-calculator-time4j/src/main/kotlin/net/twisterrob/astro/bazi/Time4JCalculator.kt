package net.twisterrob.astro.bazi

import net.time4j.PlainTime
import net.time4j.PlainTimestamp
import net.time4j.calendar.ChineseCalendar
import net.time4j.calendar.SexagesimalName
import net.twisterrob.astro.bazi.lookup.atHour
import net.twisterrob.astro.bazi.lookup.lookupHour
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import java.time.LocalDateTime

public class Time4JCalculator : BaZiCalculator {

	override fun calculate(dateTime: LocalDateTime): BaZi {
		val timestamp = PlainTimestamp.from(dateTime)
		val chineseDate = timestamp
			.calendarDate
			.transform(ChineseCalendar::class.java)
		return BaZi(
			year = calculateYear(chineseDate),
			month = calculateMonth(chineseDate),
			day = calculateDay(chineseDate),
			hour = calculateHour(chineseDate, timestamp.toTime()),
		)
	}

	private fun calculateYear(date: ChineseCalendar): BaZi.Pillar {
		return BaZi.Pillar(
			heavenlyStem = date.year.heavenlyStem,
			earthlyBranch = date.year.earthlyBranch,
		)
	}

	private fun calculateMonth(date: ChineseCalendar): BaZi.Pillar {
		return BaZi.Pillar(
			heavenlyStem = date.sexagesimalMonth.heavenlyStem,
			earthlyBranch = date.sexagesimalMonth.earthlyBranch,
		)
	}

	private fun calculateDay(date: ChineseCalendar): BaZi.Pillar {
		return BaZi.Pillar(
			heavenlyStem = date.sexagesimalDay.heavenlyStem,
			earthlyBranch = date.sexagesimalDay.earthlyBranch,
		)
	}

	private fun calculateHour(date: ChineseCalendar, time: PlainTime): BaZi.Pillar {
		val branch = EarthlyBranch.atHour(time.hour)
		return BaZi.Pillar(
			heavenlyStem = HeavenlyStem.lookupHour(date.sexagesimalDay.heavenlyStem, branch),
			earthlyBranch = branch,
		)
	}
}

internal val SexagesimalName.earthlyBranch: EarthlyBranch
	get() = EarthlyBranch.from(this.branch!!)

internal val SexagesimalName.heavenlyStem: HeavenlyStem
	get() = HeavenlyStem.from(this.stem!!)

internal fun HeavenlyStem.Companion.from(stem: SexagesimalName.Stem): HeavenlyStem =
	when (stem) {
		SexagesimalName.Stem.JIA_1_WOOD_YANG -> HeavenlyStem.Jia
		SexagesimalName.Stem.YI_2_WOOD_YIN -> HeavenlyStem.Yi
		SexagesimalName.Stem.BING_3_FIRE_YANG -> HeavenlyStem.Bing
		SexagesimalName.Stem.DING_4_FIRE_YIN -> HeavenlyStem.Ding
		SexagesimalName.Stem.WU_5_EARTH_YANG -> HeavenlyStem.Wu
		SexagesimalName.Stem.JI_6_EARTH_YIN -> HeavenlyStem.Ji
		SexagesimalName.Stem.GENG_7_METAL_YANG -> HeavenlyStem.Geng
		SexagesimalName.Stem.XIN_8_METAL_YIN -> HeavenlyStem.Xin
		SexagesimalName.Stem.REN_9_WATER_YANG -> HeavenlyStem.Ren
		SexagesimalName.Stem.GUI_10_WATER_YIN -> HeavenlyStem.Gui
	}

internal fun EarthlyBranch.Companion.from(branch: SexagesimalName.Branch): EarthlyBranch =
	when (branch) {
		SexagesimalName.Branch.ZI_1_RAT -> EarthlyBranch.Zi
		SexagesimalName.Branch.CHOU_2_OX -> EarthlyBranch.Chou
		SexagesimalName.Branch.YIN_3_TIGER -> EarthlyBranch.Yin
		SexagesimalName.Branch.MAO_4_HARE -> EarthlyBranch.Mao
		SexagesimalName.Branch.CHEN_5_DRAGON -> EarthlyBranch.Chen
		SexagesimalName.Branch.SI_6_SNAKE -> EarthlyBranch.Si
		SexagesimalName.Branch.WU_7_HORSE -> EarthlyBranch.Wu
		SexagesimalName.Branch.WEI_8_SHEEP -> EarthlyBranch.Wei
		SexagesimalName.Branch.SHEN_9_MONKEY -> EarthlyBranch.Shen
		SexagesimalName.Branch.YOU_10_FOWL -> EarthlyBranch.You
		SexagesimalName.Branch.XU_11_DOG -> EarthlyBranch.Xu
		SexagesimalName.Branch.HAI_12_PIG -> EarthlyBranch.Hai
	}
