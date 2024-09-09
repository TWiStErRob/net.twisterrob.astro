package net.twisterrob.astro.bazi.model

import net.twisterrob.astro.bazi.model.Phase.Huo
import net.twisterrob.astro.bazi.model.Phase.Jin
import net.twisterrob.astro.bazi.model.Phase.Mu
import net.twisterrob.astro.bazi.model.Phase.Shui
import net.twisterrob.astro.bazi.model.Phase.Tu
import net.twisterrob.astro.bazi.model.Polarity.Yang
import net.twisterrob.astro.bazi.model.Polarity.Yin

/**
 * Heavenly Stems, or Celestial Stems / Tiāngān (天干).
 *
 * https://en.wikipedia.org/wiki/Heavenly_Stem
 */
@Suppress("detekt.MagicNumber")
public enum class HeavenlyStem(

	/**
	 * Numeric order when enumerated.
	 */
	public val order: Int,

	/**
	 * Associated polarity of the stem.
	 */
	public val polarity: Polarity,

	/**
	 * Associated phase of the stem.
	 */
	public val phase: Phase,
) {

	/**
	 * 甲: 阳木 (yang wood).
	 */
	Jia(
		order = 1,
		polarity = Yang,
		phase = Mu
	),

	/**
	 * 乙: 阴木 (yin wood).
	 */
	Yi(
		order = 2,
		polarity = Yin,
		phase = Mu
	),

	/**
	 * 丙: 阳火 (yang fire).
	 */
	Bing(
		order = 3,
		polarity = Yang,
		phase = Huo
	),

	/**
	 * 丁: 阴火 (yin fire).
	 */
	Ding(
		order = 4,
		polarity = Yin,
		phase = Huo
	),

	/**
	 * 戊: 阳土 (yang earth).
	 */
	Wu(
		order = 5,
		polarity = Yang,
		phase = Tu
	),

	/**
	 * 己: 阴土 (yin earth).
	 */
	Ji(
		order = 6,
		polarity = Yin,
		phase = Tu
	),

	/**
	 * 庚: 阳金 (yang metal).
	 */
	Geng(
		order = 7,
		polarity = Yang,
		phase = Jin
	),

	/**
	 * 辛: 阴金 (yin metal).
	 */
	Xin(
		order = 8,
		polarity = Yin,
		phase = Jin
	),

	/**
	 * 壬: 阳水 (yang water).
	 */
	Ren(
		order = 9,
		polarity = Yang,
		phase = Shui
	),

	/**
	 * 癸: 阴水 (yin water).
	 */
	Gui(
		order = 10,
		polarity = Yin,
		phase = Shui
	),

	;

	/**
	 * Placeholder to be able to create static extensions.
	 */
	public companion object {

		/**
		 * Number of Heavenly Stems.
		 */
		public val COUNT: Int = HeavenlyStem.entries.size

		/**
		 * Get the Heavenly Stem at the given position.
		 *
		 * @param position 1-based position of the [HeavenlyStem].
		 * @return [HeavenlyStem] at the given position, based on its [order].
		 * @throws Exception if there is no [HeavenlyStem] at the given position.
		 */
		@Suppress("detekt.FunctionMinLength")
		public fun at(position: Int): HeavenlyStem =
			entries.singleOrNull { it.order == position }
				?: error("No Heavenly Stem at position ${position}.")
	}
}
