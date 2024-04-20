package net.twisterrob.astro.bazi

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class EarthlyBranchTest {
	
	@CsvSource(
		"23, Zi",
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
	)
	@ParameterizedTest
	fun `Earthly Branch at specific hours of the day`(
		hour: Int,
		expected: EarthlyBranch
	) {
		val result = EarthlyBranch.atHour(hour)
		
		result shouldBe expected
	}
}
