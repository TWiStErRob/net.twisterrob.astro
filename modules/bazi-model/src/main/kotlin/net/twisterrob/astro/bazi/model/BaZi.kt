package net.twisterrob.astro.bazi.model

/**
 * The Eight Characters / "Ba Zi" (ُ八字).
 * Zi Ping’s Eight Characters (子平八字).
 * The Four Pillars of Destiny / "Si Zhu" (四柱).
 *
 * https://en.wikipedia.org/wiki/Four_Pillars_of_Destiny
 */
@Suppress("detekt.ForbiddenPublicDataClass") // TODO PoKo
public data class BaZi(

	/**
	 * The Year Pillar / Nia Zhu (年柱).
	 */
	public val year: Pillar,

	/**
	 * The Month Pillar / Yue Zhu (月柱).
	 */
	public val month: Pillar,

	/**
	 * The Day Pillar / Ri Zhu (日柱).
	 */
	public val day: Pillar,

	/**
	 * The Hour Pillar / Shi Zhu (时柱).
	 */
	public val hour: Pillar?,
) {

	/**
	 * Self element / birth element / day master.
	 */
	public val selfElement: HeavenlyStem = day.heavenlyStem

	public companion object;

	/**
	 * A pillar of the Four Pillars of Destiny.
	 * A combination of Heavenly Stem and Earthly Branch which forms the Hexagenary Cycle.
	 *
	 * Note: it's called Pillar, but that has meaning in Ba Zi,
	 * generally in the Sexagenary calendar the combination is just called Gan Zhi (干支).
	 */
	@Suppress("detekt.ForbiddenPublicDataClass") // TODO PoKo
	public data class Pillar(

		/**
		 * Gan (干), or Heavenly Stem (天干).
		 */
		public val heavenlyStem: HeavenlyStem,

		/**
		 * Zhi (支), or Earthly Branch (地支).
		 */
		public val earthlyBranch: EarthlyBranch,
	) {

		override fun toString(): String =
			"${heavenlyStem} ${earthlyBranch}"

		public companion object {

			/**
			 * Number of possible Heavenly Stem and Earthly Branch combinations: 10 * 12 / 2 = 60.
			 *
			 * There are 120 combinations in total, but the ones that are part of the cycle are only the ones where
			 * [HeavenlyStem.polarity] matches [EarthlyBranch.zodiac]'s [Zodiac.charge].
			 */
			public val SEXAGENARY_CYCLE: Int = HeavenlyStem.COUNT * EarthlyBranch.COUNT / 2
		}
	}
}
