package net.twisterrob.astro.bazi

import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.EarthlyBranch.Chen
import net.twisterrob.astro.bazi.EarthlyBranch.Chou
import net.twisterrob.astro.bazi.EarthlyBranch.Hai
import net.twisterrob.astro.bazi.EarthlyBranch.Si
import net.twisterrob.astro.bazi.EarthlyBranch.Xu
import net.twisterrob.astro.bazi.EarthlyBranch.You
import net.twisterrob.astro.bazi.EarthlyBranch.Zi
import net.twisterrob.astro.bazi.HeavenlyStem.Bing
import net.twisterrob.astro.bazi.HeavenlyStem.Ding
import net.twisterrob.astro.bazi.HeavenlyStem.Geng
import net.twisterrob.astro.bazi.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.HeavenlyStem.Ji
import net.twisterrob.astro.bazi.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.HeavenlyStem.Ren
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import net.twisterrob.astro.bazi.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.HeavenlyStem.Wu as WuHS

class Group44CalculatorTest {

	private val subject = Group44Calculator()

	@Nested
	inner class Group44Examples {

		@Test fun `year pillar example`() {
			val time = LocalDate.of(1982, 1, 1).atStartOfDay()
			val result = subject.calculate(time)

			result.year shouldBe BaZi.Pillar(Ren, Xu)
		}

		@Test fun `year pillar no fraction example`() {
			val time = LocalDate.of(1983, 1, 1).atStartOfDay()
			val result = subject.calculate(time)

			result.year shouldBe BaZi.Pillar(Gui, Hai)
		}

		@Test fun `month pillar table example`() {
			val time = LocalDate.of(1984, 3, 1).atStartOfDay()
			val result = subject.calculateMonthTable(time)

			result shouldBe BaZi.Pillar(WuHS, Chen)
		}

		@Test fun `month pillar example`() {
			val time = LocalDate.of(1983, 1, 1).atStartOfDay()
			val result = subject.calculate(time)

			result.year shouldBe BaZi.Pillar(Gui, Hai)
		}

		@Test fun `leap years example after`() {
			val result = subject.leapYearsBetween(1944, 1983)

			result shouldBe 10
		}

		@Test fun `leap years example before`() {
			val result = subject.leapYearsBetween(1936, 1944)

			result shouldBe 2
		}

		@Test fun `day reference point`() {
			val result = subject.calculate(LocalDate.of(1944, 1, 1).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Jia, Zi)
		}

		@Test fun `day pillar year example after`() {
			val result = subject.calculate(LocalDate.of(1983, 1, 1).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Ji, Chou)
		}

		@Test fun `day pillar day example after`() {
			val result = subject.calculate(LocalDate.of(1983, 3, 7).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Jia, WuEB)
		}

		@Test fun `day pillar year example before`() {
			val result = subject.calculate(LocalDate.of(1936, 1, 1).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Ren, WuEB)
		}

		@Test fun `day pillar year example before (extrapolated for date)`() {
			val result = subject.calculate(LocalDate.of(1936, 7, 3).atStartOfDay())

			result.day shouldBe BaZi.Pillar(Bing, Xu)
		}
	}

	@Nested
	inner class Celebrities {

		@Test fun `Bruce Lee`() {
			val result = subject.calculate(LocalDateTime.of(1940, 11, 27, 8, 0))

			// From: Group44, kinaiasztrologia.com confirmed.
			// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1940&MO=11&DA=27&HO=8&MI=0&CI=San%20Francisco%2C%20CA%2C%20USA&NA=BL&GE=1&TU=false&CST=1
			result shouldBe BaZi(
				BaZi.Pillar(Geng, Chen),
				BaZi.Pillar(Ding, Hai),
				BaZi.Pillar(Jia, Xu),
				BaZi.Pillar(WuHS, Chen)
			)
		}

		@Test fun `Mao Zedong`() {
			val result = subject.calculate(LocalDateTime.of(1893, 12, 26, 8 /*7-9*/, 0))

			// From: Group44, kinaiasztrologia.com confirmed.
			// https://kinaiasztrologia.com/kalkulator/kinai_horoszkop_kalkulator.html?id=1&YE=1893&MO=12&DA=26&HO=8&MI=0&CI=Shaoshan%2C%20Xiangtan%2C%20Hunan%2C%20China&NA=MZ&GE=1&TU=false&CST=1
			result shouldBe BaZi(
				BaZi.Pillar(Gui, Si),
				BaZi.Pillar(Jia, Zi),
				BaZi.Pillar(Ding, You),
				BaZi.Pillar(Jia, Chen)
			)
		}
	}
}
