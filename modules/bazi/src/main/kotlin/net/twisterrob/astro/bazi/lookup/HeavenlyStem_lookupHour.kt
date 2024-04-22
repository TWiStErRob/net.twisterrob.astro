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

/**
 * Looks up the Heavenly Stem based on already calculated day stem and hour branch.
 *
 * @see [table] for more details.
 */
public fun HeavenlyStem.Companion.lookupHour(dayStem: HeavenlyStem, hourBranch: EarthlyBranch): HeavenlyStem =
	table.getValue(dayStem)[hourBranch.order - 1]

/**
 * Heavenly Stem table for hours.
 *
 * Sources:
 *  * https://en.wikipedia.org/wiki/Sexagenary_cycle#Sexagenary_hours
 *  * Kinai Asztrologia book, page 42.
 *  * Group 44 PDF, page 20.
 */
private val table: Map<HeavenlyStem, List<HeavenlyStem>> = mapOf(
	// 甲 to 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙
	Jia to listOf(Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi),
	// 乙 to 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁
	Yi to listOf(Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding),
	// 丙 to 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己
	Bing to listOf(Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji),
	// 丁 to 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛
	Ding to listOf(Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin),
	// 戊 to 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸
	Wu to listOf(Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui),

	// Second half is the repetition of the first, so that it's easier to index into it.

	// 己 to 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙
	Ji to listOf(Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi),
	// 庚 to 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁
	Geng to listOf(Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding),
	// 辛 to 戊, 己, 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己
	Xin to listOf(Wu, Ji, Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji),
	// 壬 to 庚, 辛, 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛
	Ren to listOf(Geng, Xin, Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin),
	// 癸 to 壬, 癸, 甲, 乙, 丙, 丁, 戊, 己, 庚, 辛, 壬, 癸
	Gui to listOf(Ren, Gui, Jia, Yi, Bing, Ding, Wu, Ji, Geng, Xin, Ren, Gui),
)
