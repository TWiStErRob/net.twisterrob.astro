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
	"UNINITIALIZED_ENUM_ENTRY", "SelfReferenceConstructorParameter",
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
	 * Inter-livening (相生): Generating / Producing / Enhancing / Strengthening / Nurturing / Supporting.
	 */
	public val livening: Phase,

	/**
	 * Inter-conquering (相剋): Overcoming / Controlling / Weakening / Restraining / Reducing / Attacking.
	 */
	public val conquering: Phase,
) {

	/**
	 * 木: Wood.
	 */
	Mu(
		order = 1,
		planet = Jupiter,
		livening = Huo, // Used to generate Fire.
		conquering = Tu, // Wood grows on Earth and held it with its roots.
	),

	/**
	 * 火: Fire.
	 */
	Huo(
		order = 2,
		planet = Mars,
		livening = Tu, // Produces ashes that is returned to the Earth.
		conquering = Jin, // Fire will melt the Metal.
	),

	/**
	 * 土: Earth.
	 */
	Tu(
		order = 3,
		planet = Saturn,
		livening = Jin, // From the Earth, mines enable us to retrieve Metal.
		conquering = Shui, // Earth absorbs the Water and thus stops the water flow.
	),

	/**
	 * 金: Metal.
	 */
	Jin(
		order = 4,
		planet = Venus,
		livening = Shui, // Melts down to produce Water.
		conquering = Mu, // Metal tools are used to cut Wood.
	),

	/**
	 * 水: Water.
	 */
	Shui(
		order = 5,
		planet = Mercury,
		livening = Mu, // Provides water for the Wood.
		conquering = Huo, // Water is used to put out Fire.
	),

	;

	public companion object;

	/**
	 * Planets in the Solar System.
	 */
	public enum class Planet {
		Venus,
		Jupiter,
		Mars,
		Mercury,
		Saturn,

		;

		public companion object;
	}
}
