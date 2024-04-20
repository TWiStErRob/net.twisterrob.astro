package net.twisterrob.astro.bazi

	/**
	 * Based on the Group 44 PDF.
	 * Ten Thousand Year Calendar / Wan Nian Li (万年历).
	 */
public class Group44Calculator {

	public fun calculate(): BaZi {
		val year = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi)
		val month = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi)
		val day = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi)
		val hour = BaZi.Pillar(HeavenlyStem.Jia, EarthlyBranch.Zi)
		return BaZi(year, month, day, hour)
	}
}
