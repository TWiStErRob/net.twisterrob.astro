package net.twisterrob.astro.bazi

import net.twisterrob.astro.units.AU
import net.twisterrob.astro.units.Deg
import net.twisterrob.astro.units.JulianDay
import net.twisterrob.astro.units.asin
import net.twisterrob.astro.units.atan2
import net.twisterrob.astro.units.au
import net.twisterrob.astro.units.cos
import net.twisterrob.astro.units.deg
import net.twisterrob.astro.units.duration
import net.twisterrob.astro.units.jd
import net.twisterrob.astro.units.lowestPositiveRem
import net.twisterrob.astro.units.minus
import net.twisterrob.astro.units.plus
import net.twisterrob.astro.units.sin
import net.twisterrob.astro.units.times
import java.time.LocalDateTime
import kotlin.time.Duration

internal class SolarCoordinateApproximator {

	data class Results(

		val apparentSolarLongitude: Deg,

		/**
		 * Right ascension is the celestial equivalent of terrestrial longitude.
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

	internal fun approximateSolarLongitude(dateTime: LocalDateTime): Results =
		approximateSolarLongitude(dateTime.julianDay.jd)

	/**
	 * https://aa.usno.navy.mil/faq/sun_approx
	 */
	@Suppress(
		"LocalVariableName", "detekt.VariableNaming", // Using names from math.
		"detekt.MagicNumber", // Tried to name what I can as a constant.
	)
	internal fun approximateSolarLongitude(jd: JulianDay): Results {
		// Elapsed since J2000 epoch.
		val D: JulianDay = jd - J2000_0
		// Mean anomaly of the Sun
		// Range: might not be 0-360.
		val g: Deg = (357.529.deg + (0.98560028 * D).deg).lowestPositiveRem(360.deg)
		// Mean longitude of the Sun
		// Range: might not be 0-360.
		val q: Deg = (280.459.deg + (0.98564736 * D).deg).lowestPositiveRem(360.deg)
		// Geocentric apparent ecliptic longitude of the Sun (adjusted for aberration)
		// Range: might not be 0-360.
		val L: Deg = (q + 1.915 * sin(g) + 0.020 * sin(2 * g)).lowestPositiveRem(360.deg)
		// Sun's ecliptic latitude
		val b: Deg = 0.deg
		// The distance of the Sun from the Earth
		val R: AU = 1.00014.au - 0.01671 * cos(g) - 0.00014 * cos(2 * g)
		// Mean obliquity of the ecliptic
		val e: Deg = 23.439.deg - (0.00000036 * D).deg
		// Sun's right ascension (tan(RA) = cos(e) * sin (L) / cos (L))
		// RA is always in the same quadrant as L.
		// RA is conventionally reduced to the range 0h to 24h, or 0 to 360 degrees.
		val RA: Deg = atan2(cos(e) * sin(L) + sin(b) * sin(e), cos(L)).lowestPositiveRem(360.deg)
		// the Sun's declination (sin(d) = sin(e) * sin(L))
		// Range: -90..+90
		val d: Deg = asin(sin(e) * sin(L) + sin(b) * sin(e))
		// The Equation of Time, apparent solar time minus mean solar time
		val EqT: Duration = q.duration - RA.duration
		// angular semidiameter of the Sun
		val SD: Deg = (0.2666 / R.value).deg
		return Results(
			apparentSolarLongitude = L,
			rightAscension = RA,
			declination = d,
			distance = R,
			semiDiameter = SD,
			equationOfTime = EqT,
		)
	}

	companion object {

		/**
		 * Epoch referred to as "J2000.0", which is 2000 January 1.5, Julian date 2451545.0.
		 */
		private val J2000_0 = 2_451_545.0.jd
	}
}
