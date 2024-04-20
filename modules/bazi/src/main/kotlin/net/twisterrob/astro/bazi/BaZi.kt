package net.twisterrob.astro.bazi

/**
 * The Eight Characters / "Ba Zi" (ُ八字).
 * Zi Ping’s Eight Characters (子平八字).
 * The Four Pillars of Destiny / "Si Zhu" (四柱).
 *
 * https://en.wikipedia.org/wiki/Four_Pillars_of_Destiny
 */
public class BaZi(
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
	public val hour: Pillar,
) {

	public companion object;

	public data class Pillar(
		public val heavenlyStem: HeavenlyStem,
		public val earthlyBranch: EarthlyBranch,
	) {

		override fun toString(): String =
			"${heavenlyStem} ${earthlyBranch}"

		public companion object;
	}
}
