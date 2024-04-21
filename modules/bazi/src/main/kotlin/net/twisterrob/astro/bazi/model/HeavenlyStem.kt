package net.twisterrob.astro.bazi.model

/**
 * Heavenly Stems, or Celestial Stems / Tiāngān (天幹 / 天干).
 *
 * https://en.wikipedia.org/wiki/Heavenly_Stem
 */
public enum class HeavenlyStem(
	public val order: Int,
	public val yinYang: YinYang,
	public val phase: Phase,
) {

	/**
	 * 甲: 阳木 (yang wood).
	 */
	Jia(1, YinYang.Yang, Phase.Mu),

	/**
	 * 乙: 阴木 (yin wood).
	 */
	Yi(2, YinYang.Yin, Phase.Mu),

	/**
	 * 丙: 阳火 (yang fire).
	 */
	Bing(3, YinYang.Yang, Phase.Huo),

	/**
	 * 丁: 阴火 (yin fire).
	 */
	Ding(4, YinYang.Yin, Phase.Huo),

	/**
	 * 戊: 阳土 (yang earth).
	 */
	Wu(5, YinYang.Yang, Phase.Tu),

	/**
	 * 己: 阴土 (yin earth).
	 */
	Ji(6, YinYang.Yin, Phase.Tu),

	/**
	 * 庚: 阳金 (yang metal).
	 */
	Geng(7, YinYang.Yang, Phase.Jin),

	/**
	 * 辛: 阴金 (yin metal).
	 */
	Xin(8, YinYang.Yin, Phase.Jin),

	/**
	 * 壬: 阳水 (yang water).
	 */
	Ren(9, YinYang.Yang, Phase.Shui),

	/**
	 * 癸: 阴水 (yin water).
	 */
	Gui(10, YinYang.Yin, Phase.Shui),

	;

	public companion object {

		internal fun at(position: Int): HeavenlyStem =
			entries.single { it.order == position }
	}
}
