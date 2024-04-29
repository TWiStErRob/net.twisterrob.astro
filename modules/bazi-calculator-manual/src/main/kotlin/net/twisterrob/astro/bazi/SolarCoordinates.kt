package net.twisterrob.astro.bazi

import net.twisterrob.astro.units.AU
import net.twisterrob.astro.units.Deg
import kotlin.time.Duration

internal data class SolarCoordinates(

	/**
	 * Apparent longitude is celestial longitude corrected for aberration and nutation as opposed to mean longitude.
	 * @see rightAscension
	 */
	val apparentSolarLongitude: Deg,

	/**
	 * Right ascension is the celestial equivalent of terrestrial longitude.
	 * @see apparentSolarLongitude
	 */
	val rightAscension: Deg,

	/**
	 * Declination is the celestial equivalent of terrestrial latitude.
	 */
	val declination: Deg,

	/**
	 * Distance of the Sun from the Earth.
	 */
	val distance: AU,

	/**
	 * Angular semi-diameter of the Sun.
	 * It is a measure of the visible size of a celestial body as seen from a specific point.
	 * In this case, it's the visible size of the Sun as seen from Earth.
	 */
	val semiDiameter: Deg,

	/**
	 * Equation of Time is the difference between apparent solar time and mean solar time.
	 * It is the difference between the hour angle of the mean Sun and the hour angle of the true Sun.
	 * It is a measure of the difference between time as read from a sundial and time as read from a clock.
	 */
	val equationOfTime: Duration,
)
