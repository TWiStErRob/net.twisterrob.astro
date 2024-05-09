package net.twisterrob.astro.bazi.lookup

import net.twisterrob.astro.bazi.model.EarthlyBranch
import net.twisterrob.astro.bazi.model.HeavenlyStem
import net.twisterrob.astro.bazi.model.HeavenlyStem.Bing
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ding
import net.twisterrob.astro.bazi.model.HeavenlyStem.Geng
import net.twisterrob.astro.bazi.model.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ji
import net.twisterrob.astro.bazi.model.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ren
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu
import net.twisterrob.astro.bazi.model.HeavenlyStem.Xin
import net.twisterrob.astro.bazi.model.HeavenlyStem.Yi
import net.twisterrob.astro.units.canonicalMod

/**
 * Looks up the Heavenly Stem based on already calculated year stem and month branch.
 *
 * @see [table] for more details.
 */
public fun HeavenlyStem.Companion.lookupSolarMonth(yearStem: HeavenlyStem, monthBranch: EarthlyBranch): HeavenlyStem {
	// First month is Yin, not Zi so adjust it.
	val orderAdjust = monthBranch.order - 2
	// Zero-based index, so subtract 1.
	val zeroAdjust = orderAdjust - 1
	// Cycle one up to make sure the number is positive in 0..11.
	val cycled = zeroAdjust.canonicalMod(EarthlyBranch.COUNT)
	return table.getValue(yearStem)[cycled]
}

/**
 * Heavenly Stem table for solar months.
 *
 * Sources:
 *  * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_months
 */
private val table: Map<HeavenlyStem, List<HeavenlyStem>> = mapOf(

	// 甲: 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁
	Jia to listOf(Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding),
	// 乙: 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己
	Yi to listOf(Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji),
	// 丙: 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛
	Bing to listOf(Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin),
	// 丁: 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸
	Ding to listOf(Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui),
	// 戊: 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙
	Wu to listOf(Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi),

	// Second half is the repetition of the first, so that it's easier to index into it.

	// 己: 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁
	Ji to listOf(Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding),
	// 庚: 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己
	Geng to listOf(Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji),
	// 辛: 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛
	Xin to listOf(Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin),
	// 壬: 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸
	Ren to listOf(Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui),
	// 癸: 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙
	Gui to listOf(Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi),
)
