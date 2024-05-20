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
	 */
	public val livening: Phase get() = _livening()

	/**
	 * Inter-conquering (相剋): Overcoming / Controlling / Weakening / Restraining / Reducing / Attacking.
	 */
	public val conquering: Phase get() = _conquering()

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
