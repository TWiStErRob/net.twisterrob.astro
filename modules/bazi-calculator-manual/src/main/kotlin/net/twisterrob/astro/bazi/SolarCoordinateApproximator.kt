package net.twisterrob.astro.bazi

import java.time.LocalDateTime

internal class SolarCoordinateApproximator {

	/**
	 * https://aa.usno.navy.mil/faq/sun_approx
	 */
	@Suppress(
		"LocalVariableName", // Using names from math.
		"UNUSED_VARIABLE", // STOPSHIP WIP
	)
	internal fun approximateSolarLongitude(dateTime: LocalDateTime): Double {
		val JD = dateTime.julianDay
		val D = JD - J2000_0
		return 0.0
	}
	
	companion object {

		/**
		 * Epoch referred to as "J2000.0", which is 2000 January 1.5, Julian date 2451545.0.
		 */
		private const val J2000_0 = 2451545.0
	}
}
