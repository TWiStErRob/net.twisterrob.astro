package net.twisterrob.astro.bazi

/**
 * Earthly Branches, or Terrestrial Branches / Dìzhī (地支).
 *
 * 12 revolutionary points of [net.twisterrob.astro.bazi.Phase.Planet.Jupiter] (11.8 years).
 *
 * https://en.wikipedia.org/wiki/Earthly_Branch
 */
public enum class EarthlyBranch(
	public val order: Int,
	public val zodiac: Zodiac,
) {

	/**
	 * 子: 鼠 (rat).
	 * 23-01
	 */
	Zi(1, Zodiac.Rat),

	/**
	 * 丑: 牛 (ox).
	 * 01-03
	 */
	Chou(2, Zodiac.Ox),

	/**
	 * 寅: 虎 (tiger).
	 * 03-05
	 */
	Yin(3, Zodiac.Tiger),

	/**
	 * 卯: 兔 (rabbit).
	 * 05-07
	 */
	Mao(4, Zodiac.Rabbit),

	/**
	 * 辰: 龙 (dragon).
	 * 07-09
	 */
	Chen(5, Zodiac.Dragon),

	/**
	 * 巳: 蛇 (snake).
	 * 09-11
	 */
	Si(6, Zodiac.Snake),

	/**
	 * 午: 马 (horse).
	 * 11-13
	 */
	Wu(7, Zodiac.Horse),

	/**
	 * 未: 羊 (goat).
	 * 13-15
	 */
	Wei(8, Zodiac.Goat),

	/**
	 * 申: 猴 (monkey).
	 * 15-17
	 */
	Shen(9, Zodiac.Monkey),

	/**
	 * 酉: 鸡 (rooster).
	 * 17-19
	 */
	You(10, Zodiac.Rooster),

	/**
	 * 戌: 狗 (dog).
	 * 19-21
	 */
	Xu(11, Zodiac.Dog),

	/**
	 * 亥: 猪 (pig).
	 * 21-23
	 */
	Hai(12, Zodiac.Pig),
}
