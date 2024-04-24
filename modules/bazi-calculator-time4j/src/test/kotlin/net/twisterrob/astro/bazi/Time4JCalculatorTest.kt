package net.twisterrob.astro.bazi

import io.kotest.matchers.shouldBe
import net.time4j.PlainTimestamp
import net.time4j.calendar.ChineseCalendar
import net.time4j.calendar.SexagesimalName
import net.time4j.calendar.SolarTerm
import net.time4j.calendar.astro.AstronomicalSeason
import net.time4j.format.expert.ChronoFormatter
import net.time4j.format.expert.PatternType
import net.time4j.tz.OffsetSign
import net.time4j.tz.ZonalOffset
import org.junit.jupiter.api.Test
import java.util.Locale

class Time4JCalculatorTest : BaZiCalculatorTest() {

	override val subject: BaZiCalculator = Time4JCalculator()

	@Test fun test() {
		val formatter =
			ChronoFormatter.setUp(ChineseCalendar.axis(), Locale.ENGLISH)
				.addPattern("EEE, d. MMMM r(U) ", PatternType.CLDR_DATE)
				.addText(ChineseCalendar.SOLAR_TERM)
				.build()
		val winter =
			AstronomicalSeason.WINTER_SOLSTICE
				.inYear(2018)
				.toZonalTimestamp(ZonalOffset.ofHours(OffsetSign.AHEAD_OF_UTC, 8))
				.calendarDate
		val chineseDate = winter.transform(ChineseCalendar::class.java)
		formatter.with(Locale.CHINESE).parse("周六, 16. 十一月 2018(戊戌) 冬至") shouldBe chineseDate
		formatter.format(chineseDate) shouldBe "Sat, 16. Eleventh Month 2018(wù-xū) dōngzhì"
	}

	@Test fun test2() {
		val formatter =
			ChronoFormatter.setUp(ChineseCalendar.axis(), Locale.ENGLISH)
				.addPattern("EEE, d. MMMM r(U) ", PatternType.CLDR_DATE)
				.addText(ChineseCalendar.SOLAR_TERM)
				.build()
//		val year = BaZi.Pillar(Jia, Shen)
//		val month = BaZi.Pillar(Wu, Chen)
//		val day = BaZi.Pillar(Gui, Wei)
		val winter = PlainTimestamp.of(2004, 5, 4, 12, 35)
		val chineseDate = winter.calendarDate.transform(ChineseCalendar::class.java)
		chineseDate.year.stem shouldBe SexagesimalName.Stem.JIA_1_WOOD_YANG
		chineseDate.year.branch shouldBe SexagesimalName.Branch.SHEN_9_MONKEY
		chineseDate.solarTerm shouldBe SolarTerm.MAJOR_03_GUYU_030
		chineseDate.month.number shouldBe 3
		chineseDate.sexagesimalDay.stem shouldBe SexagesimalName.Stem.GUI_10_WATER_YIN
		chineseDate.sexagesimalDay.branch shouldBe SexagesimalName.Branch.WEI_8_SHEEP
		formatter.format(chineseDate) shouldBe "Tue, 16. Third Month 2004(jiǎ-shēn) gǔyǔ"
	}

//	
//	@Test fun test2() {
//		val gregorianDate = PlainTimestamp.from(date)
//
//		val yearPrinter =  { gregorianDate : PlainDate, buffer: StringBuilder, attributes: AttributeQuery -> // for display of cyclic year
//			buffer.append(gregorianDate.transform(ChineseCalendar.axis()).year.getDisplayName(Locale.TRADITIONAL_CHINESE))
//			Collections.emptySet()
//		}
//
//		val  zodiacPrinter = { gregorianDate: PlainDate, buffer: StringBuilder, attributes: AttributeQuery ->
//			buffer.append(gregorianDate.transform(ChineseCalendar.axis()).year.getZodiac(Locale.TRADITIONAL_CHINESE))
//			Collections.emptySet()
//		}
//
//		val gf = ChronoFormatter.setUp(PlainDate.axis(), Locale.TRADITIONAL_CHINESE)
//			.addPattern("y(", PatternType.CLDR)
//			.addCustomized(PlainDate.COMPONENT, yearPrinter, ChronoParser.unsupported())
//			//.startSection(Attributes.NUMBER_SYSTEM, NumberSystem.CHINESE_MANDARIN) // month and day in Chinese?
//			.addPattern(")年M月d日(", PatternType.CLDR)
//			//.endSection()
//			.addCustomized(PlainDate.COMPONENT, zodiacPrinter, ChronoParser.unsupported())
//			.addLiteral(')')
//			.build();
//
//		val s = gf.print(PlainDate.of(2023, 11, 5))
//		println(s) // 2023(癸卯)年11月5日(兔)
//	}
}
