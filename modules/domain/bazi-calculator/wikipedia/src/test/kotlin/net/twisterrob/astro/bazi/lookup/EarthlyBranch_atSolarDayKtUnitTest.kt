package net.twisterrob.astro.bazi.lookup

import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.EarthlyBranch
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate

class EarthlyBranch_atSolarDayKtUnitTest {

	@CsvSource(
		// Edge of Gregorian year.
		"2023-12-31, Zi",
		"2024-01-01, Zi",
		"2024-06-20, Wu",

		// Edges of Chinese solar months in non-leap year.
		"2023-01-05, Zi",
		"2023-01-06, Chou",
		"2023-01-07, Chou",

		"2023-02-04, Chou",
		"2023-02-05, Yin",
		"2023-02-06, Yin",

		"2023-03-05, Yin",
		"2023-03-06, Mao",
		"2023-03-07, Mao",

		"2023-04-05, Mao",
		"2023-04-06, Chen",
		"2023-04-07, Chen",

		"2023-05-05, Chen",
		"2023-05-06, Si",
		"2023-05-07, Si",

		"2023-06-05, Si",
		"2023-06-06, Wu",
		"2023-06-07, Wu",

		"2023-07-06, Wu",
		"2023-07-07, Wei",
		"2023-07-08, Wei",

		"2023-08-07, Wei",
		"2023-08-08, Shen",
		"2023-08-09, Shen",

		"2023-09-07, Shen",
		"2023-09-08, You",
		"2023-09-09, You",

		"2023-10-07, You",
		"2023-10-08, Xu",
		"2023-10-09, Xu",

		"2023-11-06, Xu",
		"2023-11-07, Hai",
		"2023-11-08, Hai",

		"2023-12-06, Hai",
		"2023-12-07, Zi",
		"2023-12-08, Zi",

		// Edges of Chinese solar months in leap year.
		"2024-01-05, Zi",
		"2024-01-06, Chou",
		"2024-01-07, Chou",

		"2024-02-04, Chou",
		"2024-02-05, Yin",
		"2024-02-06, Yin",

		"2024-03-04, Yin",
		"2024-03-05, Mao",
		"2024-03-06, Mao",

		"2024-04-04, Mao",
		"2024-04-05, Chen",
		"2024-04-06, Chen",

		"2024-05-04, Chen",
		"2024-05-05, Si",
		"2024-05-06, Si",

		"2024-06-04, Si",
		"2024-06-05, Wu",
		"2024-06-06, Wu",

		"2024-07-05, Wu",
		"2024-07-06, Wei",
		"2024-07-07, Wei",

		"2024-08-06, Wei",
		"2024-08-07, Shen",
		"2024-08-08, Shen",

		"2024-09-06, Shen",
		"2024-09-07, You",
		"2024-09-08, You",

		"2024-10-06, You",
		"2024-10-07, Xu",
		"2024-10-08, Xu",

		"2024-11-05, Xu",
		"2024-11-06, Hai",
		"2024-11-07, Hai",

		"2024-12-05, Hai",
		"2024-12-06, Zi",
		"2024-12-07, Zi",

		"2025-01-04, Zi",
		"2025-01-05, Chou",
		"2025-01-06, Chou",

		"2025-02-03, Chou",
		"2025-02-04, Yin",
		"2025-02-05, Yin",
	)
	@ParameterizedTest
	fun `month Earthly Branch at specific days of the year`(date: LocalDate, expected: EarthlyBranch) {
		val result = EarthlyBranch.monthAtSolarDay(date)

		result shouldBe expected
	}
}
