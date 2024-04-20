package net.twisterrob.astro.bazi

/**
 * Five Phases, Five Elements, or Five Agents / Wuxing (五行 / 五行).
 *
 * https://en.wikipedia.org/wiki/Wuxing_(Chinese_philosophy)
 */
@Suppress("UNINITIALIZED_ENUM_ENTRY", "SelfReferenceConstructorParameter")
public enum class Phase(
	public val planet: Planet,

	/**
	 * Inter-livening: Generating / Producing / Enhancing / Strengthening / Nurturing / Supporting.
	 */
	public val livening: Phase,

	/**
	 * Inter-conquering: Overcoming / Controlling / Weakening / Restraining / Reducing / Attacking.
	 */
	public val conquering: Phase,
) {

	/**
	 * 木: Wood.
	 */
	Mu(
		Planet.Jupiter,
		Huo, // Used to generate Fire.
		Tu, // Wood grows on Earth and held it with its roots.
	),

	/**
	 * 火: Fire.
	 */
	Huo(
		Planet.Mars,
		Tu, // Produces ashes that is returned to the Earth.
		Jin, // Fire will melt the Metal.
	),

	/**
	 * 土: Earth.
	 */
	Tu(
		Planet.Saturn,
		Jin, // From the Earth, mines enable us to retrieve Metal.
		Shui, // Earth absorbs the Water and thus stops the water flow.
	),

	/**
	 * 金: Metal.
	 */
	Jin(
		Planet.Venus,
		Shui, // Melts down to produce Water.
		Mu, // Metal tools are used to cut Wood.
	),

	/**
	 * 水: Water.
	 */
	Shui(
		Planet.Mercury,
		Mu, // Provides water for the Wood.
		Huo, // Water is used to put out Fire.
	),

	;

	public companion object;

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
