package net.twisterrob.astro.bazi.model

import net.twisterrob.astro.bazi.model.Zodiac.Dog
import net.twisterrob.astro.bazi.model.Zodiac.Dragon
import net.twisterrob.astro.bazi.model.Zodiac.Goat
import net.twisterrob.astro.bazi.model.Zodiac.Horse
import net.twisterrob.astro.bazi.model.Zodiac.Monkey
import net.twisterrob.astro.bazi.model.Zodiac.Ox
import net.twisterrob.astro.bazi.model.Zodiac.Pig
import net.twisterrob.astro.bazi.model.Zodiac.Rabbit
import net.twisterrob.astro.bazi.model.Zodiac.Rat
import net.twisterrob.astro.bazi.model.Zodiac.Rooster
import net.twisterrob.astro.bazi.model.Zodiac.Snake
import net.twisterrob.astro.bazi.model.Zodiac.Tiger

/**
 * Earthly Branches, or Terrestrial Branches / Dìzhī (地支).
 *
 * 12 revolutionary points of [Phase.Planet.Jupiter] (11.8 years).
 *
 * https://en.wikipedia.org/wiki/Earthly_Branch
 */
@Suppress("detekt.MagicNumber")
public enum class EarthlyBranch(

	/**
	 * Numeric order when enumerated.
	 */
	public val order: Int,

	/**
	 * Associated zodiac.
	 */
	public val zodiac: Zodiac,
) {

	/**
	 * 子: 鼠 (rat).
	 * 23-01
	 */
	Zi(
		order = 1,
		zodiac = Rat,
	),

	/**
	 * 丑: 牛 (ox).
	 * 01-03
	 */
	Chou(
		order = 2,
		zodiac = Ox,
	),

	/**
	 * 寅: 虎 (tiger).
	 * 03-05
	 */
	Yin(
		order = 3,
		zodiac = Tiger,
	),

	/**
	 * 卯: 兔 (rabbit).
	 * 05-07
	 */
	Mao(
		order = 4,
		zodiac = Rabbit,
	),

	/**
	 * 辰: 龙 (dragon).
	 * 07-09
	 */
	Chen(
		order = 5,
		zodiac = Dragon,
	),

	/**
	 * 巳: 蛇 (snake).
	 * 09-11
	 */
	Si(
		order = 6,
		zodiac = Snake,
	),

	/**
	 * 午: 马 (horse).
	 * 11-13
	 */
	Wu(
		order = 7,
		zodiac = Horse,
	),

	/**
	 * 未: 羊 (goat).
	 * 13-15
	 */
	Wei(
		order = 8,
		zodiac = Goat,
	),

	/**
	 * 申: 猴 (monkey).
	 * 15-17
	 */
	Shen(
		order = 9,
		zodiac = Monkey,
	),

	/**
	 * 酉: 鸡 (rooster).
	 * 17-19
	 */
	You(
		order = 10,
		zodiac = Rooster,
	),

	/**
	 * 戌: 狗 (dog).
	 * 19-21
	 */
	Xu(
		order = 11,
		zodiac = Dog,
	),

	/**
	 * 亥: 猪 (pig).
	 * 21-23
	 */
	Hai(
		order = 12,
		zodiac = Pig,
	),

	;

	/**
	 * Placeholder to be able to create static extensions.
	 */
	public companion object {

		/**
		 * Number of Earthly Branches.
		 */
		public val COUNT: Int = entries.size

		/**
		 * Get Earthly Branch by its numeric order.
		 * @param position 1-based position of the [EarthlyBranch].
		 * @return [EarthlyBranch] at the given position, based on its [order].
		 * @throws Exception if there is no [EarthlyBranch] at the given position.
		 */
		@Suppress("detekt.FunctionMinLength")
		public fun at(position: Int): EarthlyBranch =
			EarthlyBranch.entries.singleOrNull { it.order == position }
				?: error("No Earthly Branch at position ${position}.")
	}
}
