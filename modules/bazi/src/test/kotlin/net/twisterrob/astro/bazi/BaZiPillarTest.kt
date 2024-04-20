package net.twisterrob.astro.bazi

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BaZiPillarTest {

	@CsvSource(
		"1, Jia, Zi",
		"2, Yi, Chou",
		"3, Bing, Yin",
		"4, Ding, Mao",
		"5, Wu, Chen",
		"6, Ji, Si",
		"7, Geng, Wu",
		"8, Xin, Wei",
		"9, Ren, Shen",
		"10, Gui, You",
		"11, Jia, Xu",
		"12, Yi, Hai",
		"13, Bing, Zi",
		"14, Ding, Chou",
		"15, Wu, Yin",
		"16, Ji, Mao",
		"17, Geng, Chen",
		"18, Xin, Si",
		"19, Ren, Wu",
		"20, Gui, Wei",
		"21, Jia, Shen",
		"22, Yi, You",
		"23, Bing, Xu",
		"24, Ding, Hai",
		"25, Wu, Zi",
		"26, Ji, Chou",
		"27, Geng, Yin",
		"28, Xin, Mao",
		"29, Ren, Chen",
		"30, Gui, Si",
		"31, Jia, Wu",
		"32, Yi, Wei",
		"33, Bing, Shen",
		"34, Ding, You",
		"35, Wu, Xu",
		"36, Ji, Hai",
		"37, Geng, Zi",
		"38, Xin, Chou",
		"39, Ren, Yin",
		"40, Gui, Mao",
		"41, Jia, Chen",
		"42, Yi, Si",
		"43, Bing, Wu",
		"44, Ding, Wei",
		"45, Wu, Shen",
		"46, Ji, You",
		"47, Geng, Xu",
		"48, Xin, Hai",
		"49, Ren, Zi",
		"50, Gui, Chou",
		"51, Jia, Yin",
		"52, Yi, Mao",
		"53, Bing, Chen",
		"54, Ding, Si",
		"55, Wu, Wu",
		"56, Ji, Wei",
		"57, Geng, Shen",
		"58, Xin, You",
		"59, Ren, Xu",
		"60, Gui, Hai",
	)
	@ParameterizedTest fun `sexagenary cycle pillars`(
		position: Int,
		heavenlyStem: HeavenlyStem,
		earthlyBranch: EarthlyBranch
	) {
		val subject = BaZi.Pillar.at(position)

		subject.heavenlyStem shouldBe heavenlyStem
		subject.earthlyBranch shouldBe earthlyBranch
	}
}
