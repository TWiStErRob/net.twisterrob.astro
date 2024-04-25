package net.twisterrob.astro.bazi

import java.time.LocalDateTime
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

internal class SolarCoordinateApproximator {

	/**
	 * https://aa.usno.navy.mil/faq/sun_approx
	 */
	@Suppress(
		"LocalVariableName", // Using names from math.
	)
	internal fun approximateSolarLongitude(dateTime: LocalDateTime): Double {
		// Unit: Julian days
		val JD = dateTime.julianDay
		// Elapsed since J2000 epoch.
		// Unit: Julian days
		val D = JD - J2000_0
		// Mean anomaly of the Sun
		// Unit: degrees; Range: might not be 0-360.
		val g = 357.529 + 0.98560028 * D
		// Mean longitude of the Sun
		// Unit: degrees; Range: might not be 0-360.
		val q = 280.459 + 0.98564736 * D
		// Geocentric apparent ecliptic longitude of the Sun (adjusted for aberration)
		// Unit: degrees; Range: might not be 0-360.
		val L = q + 1.915 * sin(g) + 0.020 * sin(2 * g)
		// Sun's ecliptic latitude
		val b = 0
		// The distance of the Sun from the Earth
		// Unit: astronomical units (AU)
		val R = 1.00014 - 0.01671 * cos(g) - 0.00014 * cos(2 * g)
		// Mean obliquity of the ecliptic
		// Unit: degrees
		val e = 23.439 - 0.00000036 * D
		// Sun's right ascension (celestial equivalent of longitude) 
		// tan(RA) = cos(e) * sin (L) / cos (L)
		// RA is always in the same quadrant as L.
		// If RA is obtained in degrees, it can be converted to hours simply by dividing by 15.
		// RA is conventionally reduced to the range 0h to 24h.
		val RA = atan2(cos(e) * sin(L), cos(L))
		// the Sun's declination 
		// sin(d) = sin(e) * sin(L)
		val d = asin(sin(e) * sin(L))
		// The Equation of Time, apparent solar time minus mean solar time
		val EqT = q / 15 - RA
		// angular semidiameter of the Sun
		// Unit: degrees
		val SD = 0.2666 / R
		return RA
	}

	companion object {

		/**
		 * Epoch referred to as "J2000.0", which is 2000 January 1.5, Julian date 2451545.0.
		 */
		private const val J2000_0 = 2451545.0
	}
}
