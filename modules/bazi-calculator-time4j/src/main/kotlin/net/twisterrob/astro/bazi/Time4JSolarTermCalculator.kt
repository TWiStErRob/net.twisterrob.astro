package net.twisterrob.astro.bazi

import net.time4j.PlainTime
import net.time4j.PlainTimestamp
import net.time4j.calendar.ChineseCalendar
import net.time4j.calendar.SolarTerm
import net.twisterrob.astro.bazi.lookup.atHour
import net.twisterrob.astro.bazi.lookup.lookupHour
import net.twisterrob.astro.bazi.lookup.lookupSolarMonth
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import java.time.LocalDateTime

public class Time4JSolarTermCalculator : BaZiCalculator {

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
		val branch: EarthlyBranch = date.solarTerm!!.branch
		return BaZi.Pillar(
			heavenlyStem = HeavenlyStem.lookupSolarMonth(date.year.heavenlyStem, branch),
			earthlyBranch = branch,
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

internal val SolarTerm.branch: EarthlyBranch
	get() = EarthlyBranch.from(this)

internal fun EarthlyBranch.Companion.from(solarTerm: SolarTerm): EarthlyBranch =
	when (solarTerm) {
		SolarTerm.MINOR_01_LICHUN_315 -> EarthlyBranch.Yin
		SolarTerm.MAJOR_01_YUSHUI_330 -> EarthlyBranch.Yin
		SolarTerm.MINOR_02_JINGZHE_345 -> EarthlyBranch.Mao
		SolarTerm.MAJOR_02_CHUNFEN_000 -> EarthlyBranch.Mao
		SolarTerm.MINOR_03_QINGMING_015 -> EarthlyBranch.Chen
		SolarTerm.MAJOR_03_GUYU_030 -> EarthlyBranch.Chen
		SolarTerm.MINOR_04_LIXIA_045 -> EarthlyBranch.Si
		SolarTerm.MAJOR_04_XIAOMAN_060 -> EarthlyBranch.Si
		SolarTerm.MINOR_05_MANGZHONG_075 -> EarthlyBranch.Wu
		SolarTerm.MAJOR_05_XIAZHI_090 -> EarthlyBranch.Wu
		SolarTerm.MINOR_06_XIAOSHU_105 -> EarthlyBranch.Wei
		SolarTerm.MAJOR_06_DASHU_120 -> EarthlyBranch.Wei
		SolarTerm.MINOR_07_LIQIU_135 -> EarthlyBranch.Shen
		SolarTerm.MAJOR_07_CHUSHU_150 -> EarthlyBranch.Shen
		SolarTerm.MINOR_08_BAILU_165 -> EarthlyBranch.You
		SolarTerm.MAJOR_08_QIUFEN_180 -> EarthlyBranch.You
		SolarTerm.MINOR_09_HANLU_195 -> EarthlyBranch.Xu
		SolarTerm.MAJOR_09_SHUANGJIANG_210 -> EarthlyBranch.Xu
		SolarTerm.MINOR_10_LIDONG_225 -> EarthlyBranch.Hai
		SolarTerm.MAJOR_10_XIAOXUE_240 -> EarthlyBranch.Hai
		SolarTerm.MINOR_11_DAXUE_255 -> EarthlyBranch.Zi
		SolarTerm.MAJOR_11_DONGZHI_270 -> EarthlyBranch.Zi
		SolarTerm.MINOR_12_XIAOHAN_285 -> EarthlyBranch.Chou
		SolarTerm.MAJOR_12_DAHAN_300 -> EarthlyBranch.Chou
	}
