package net.twisterrob.astro.bazi.model

import net.twisterrob.astro.bazi.model.Phase.Planet.Jupiter
import net.twisterrob.astro.bazi.model.Phase.Planet.Mars
import net.twisterrob.astro.bazi.model.Phase.Planet.Mercury
import net.twisterrob.astro.bazi.model.Phase.Planet.Saturn
import net.twisterrob.astro.bazi.model.Phase.Planet.Venus

/**
 * Five Phases, Five Elements, or Five Agents / Wuxing (五行 / 五行).
 *
 * https://en.wikipedia.org/wiki/Wuxing_(Chinese_philosophy)
 */
@Suppress(
	"ConstructorParameterNaming",
	"detekt.MagicNumber",
)
public enum class Phase(

	/**
	 * Numeric order.
	 */
	public val order: Int,

	/**
	 * Planet associated with the phase.
	 */
	public val planet: Planet,

	/**
	 * Workaround for `UNINITIALIZED_ENUM_ENTRY` Kotlin compiler error.
	 *
	 * @see livening
	 */
	private val _livening: () -> Phase,

	/**
	 * Workaround for `UNINITIALIZED_ENUM_ENTRY` Kotlin compiler error.
	 *
	 * @see conquering
	 */
	private val _conquering: () -> Phase,
) {

	/**
	 * 木: Wood.
	 */
	Mu(
		order = 1,
		planet = Jupiter,
		_livening = { Huo }, // Used to generate Fire.
		_conquering = { Tu }, // Wood grows on Earth and held it with its roots.
	),

	/**
	 * 火: Fire.
	 */
	Huo(
		order = 2,
		planet = Mars,
		_livening = { Tu }, // Produces ashes that is returned to the Earth.
		_conquering = { Jin }, // Fire will melt the Metal.
	),

	/**
	 * 土: Earth.
	 */
	Tu(
		order = 3,
		planet = Saturn,
		_livening = { Jin }, // From the Earth, mines enable us to retrieve Metal.
		_conquering = { Shui }, // Earth absorbs the Water and thus stops the water flow.
	),

	/**
	 * 金: Metal.
	 */
	Jin(
		order = 4,
		planet = Venus,
		_livening = { Shui }, // Melts down to produce Water.
		_conquering = { Mu }, // Metal tools are used to cut Wood.
	),

	/**
	 * 水: Water.
	 */
	Shui(
		order = 5,
		planet = Mercury,
		_livening = { Mu }, // Provides water for the Wood.
		_conquering = { Huo }, // Water is used to put out Fire.
	),

	;

	/**
	 * Inter-livening (相生): Generating / Producing / Enhancing / Strengthening / Nurturing / Supporting.
	 * @see Phase.generativeCycle
	 * @see Phase.reverseGenerativeCycle
	 */
	public val livening: Phase get() = _livening()

	/**
	 * Inter-conquering (相剋): Overcoming / Controlling / Weakening / Restraining / Reducing / Attacking.
	 * @see Phase.destructiveCycle
	 * @see Phase.reverseDestructiveCycle
	 * @see Phase.excessiveDestructiveCycle
	 */
	public val conquering: Phase get() = _conquering()

	/**
	 * Placeholder to be able to create static extensions.
	 */
	public companion object;

	/**
	 * Celestial bodies. Planets in the Solar System.
	 */
	public enum class Planet {

		/**
		 * @see Phase.Jin
		 */
		Venus,

		/**
		 * @see Phase.Mu
		 */
		Jupiter,

		/**
		 * @see Phase.Huo
		 */
		Mars,

		/**
		 * @see Phase.Shui
		 */
		Mercury,

		/**
		 * @see Phase.Tu
		 */
		Saturn,

		;

		/**
		 * Placeholder to be able to create static extensions.
		 */
		public companion object;
	}
}

/**
 * Inter-promoting / generative cycle (相生)
 *  * [Wood][Phase.Mu] feeds [Fire][Phase.Huo] as fuel
 *  * [Fire][Phase.Huo] produces [Earth][Phase.Tu]
 *    (ash, lava)
 *  * [Earth][Phase.Tu] bears [Metal][Phase.Jin]
 *    (geological processes produce minerals)
 *  * [Metal][Phase.Jin] collects, filters and purifies [Water][Phase.Shui]
 *    (water vapor condenses on metal)
 *  * [Water][Phase.Shui] nourishes [Wood][Phase.Mu]
 *    (water leads to growth of flowers, plants and other changes in nature)
 *
 * https://en.wikipedia.org/wiki/Wuxing_(Chinese_philosophy)#Inter-promoting
 * @see Phase.livening
 */
public val Phase.generativeCycle: List<Phase>
	get() = generateSequence(this) { it.livening }
		.take(Phase.entries.size)
		.toList()

/**
 * Weakening / reverse generative cycle (相洩/相泄)
 *  * [Wood][Phase.Mu] depletes [Water][Phase.Shui]
 *  * [Water][Phase.Shui] rusts [Metal][Phase.Jin]
 *  * [Metal][Phase.Jin] impoverishes [Earth][Phase.Tu]
 *    (erosion, destructive mining of minerals)
 *  * [Earth][Phase.Tu] smothers [Fire][Phase.Huo]
 *  * [Fire][Phase.Huo] burns [Wood][Phase.Mu]
 *    (forest fires)
 *
 * https://en.wikipedia.org/wiki/Wuxing_(Chinese_philosophy)#Weakening
 * @see Phase.livening
 */
public val Phase.reverseGenerativeCycle: List<Phase>
	get() = generativeCycle.reversed()

/**
 * Inter-regulating / destructive cycle (相克)
 *  * [Wood][Phase.Mu] grasps (or stabilizes) [Earth][Phase.Tu]
 *    (roots of trees can prevent soil erosion)
 *  * [Earth][Phase.Tu] contains (or directs) [Water][Phase.Shui]
 *    (dams or river banks)
 *  * [Water][Phase.Shui] dampens (or regulates) [Fire][Phase.Huo]
 *  * [Fire][Phase.Huo] melts (or refines or shapes) [Metal][Phase.Jin]
 *  * [Metal][Phase.Jin] chops (or carves) [Wood][Phase.Mu]
 *
 * https://en.wikipedia.org/wiki/Wuxing_(Chinese_philosophy)#Inter-regulating
 * @see Phase.conquering
 */
public val Phase.destructiveCycle: List<Phase>
	get() = generateSequence(this) { it.conquering }
		.take(Phase.entries.size)
		.toList()

/**
 * Counteracting  / reverse or deficient generative cycle (相侮/相耗)
 *  * [Wood][Phase.Mu] dulls [Metal][Phase.Jin]
 *  * [Metal][Phase.Jin] de-energizes [Fire][Phase.Huo]
 *    (conducting heat away)
 *  * [Fire][Phase.Huo] evaporates [Water][Phase.Shui]
 *  * [Water][Phase.Shui] muddies (or destabilizes) [Earth][Phase.Tu]
 *  * [Earth][Phase.Tu] rots [Wood][Phase.Mu]
 *    (buried wood rots)
 *
 * https://en.wikipedia.org/wiki/Wuxing_(Chinese_philosophy)#Counteracting
 * @see Phase.conquering
 */
public val Phase.reverseDestructiveCycle: List<Phase>
	get() = destructiveCycle.reversed()

/**
 * Overacting / excessive destructive cycle (相乘)
 *  * [Wood][Phase.Mu] depletes [Earth][Phase.Tu]
 *    (depletion of nutrients in soil, over-farming, overcultivation)
 *  * [Earth][Phase.Tu] obstructs [Water][Phase.Shui]
 *    (over-damming)
 *  * [Water][Phase.Shui] extinguishes [Fire][Phase.Huo]
 *  * [Fire][Phase.Huo] melts [Metal][Phase.Jin]
 *    (affecting its integrity)
 *  * [Metal][Phase.Jin] makes [Wood][Phase.Mu] rigid to easily snap.
 *
 * https://en.wikipedia.org/wiki/Wuxing_(Chinese_philosophy)#Overacting
 * @see Phase.conquering
 */
public val Phase.excessiveDestructiveCycle: List<Phase>
	get() = destructiveCycle
