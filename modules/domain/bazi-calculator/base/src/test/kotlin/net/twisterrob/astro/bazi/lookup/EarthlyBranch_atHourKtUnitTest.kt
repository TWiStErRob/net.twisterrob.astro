package net.twisterrob.astro.bazi.lookup

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.EarthlyBranch
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class EarthlyBranch_atHourKtUnitTest {

	@CsvSource(
		"0, Zi",
		"1, Chou",
		"2, Chou",
		"3, Yin",
		"4, Yin",
		"5, Mao",
		"6, Mao",
		"7, Chen",
		"8, Chen",
		"9, Si",
		"10, Si",
		"11, Wu",
		"12, Wu",
		"13, Wei",
		"14, Wei",
		"15, Shen",
		"16, Shen",
		"17, You",
		"18, You",
		"19, Xu",
		"20, Xu",
		"21, Hai",
		"22, Hai",
		"23, Zi",
	)
	@ParameterizedTest
	fun `Earthly Branch at specific hours of the day`(hour: Int, expected: EarthlyBranch) {
		val result = EarthlyBranch.atHour(hour)

		result shouldBe expected
	}

	@ValueSource(ints = [-2, -1 /*, 0..23*/, 24, 25])
	@ParameterizedTest
	fun `Earthly Branch at invalid hours of the day`(hour: Int) {
		shouldThrow<IllegalArgumentException> {
			EarthlyBranch.atHour(hour)
		}
	}
}
