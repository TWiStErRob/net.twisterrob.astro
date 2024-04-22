package net.twisterrob.astro.bazi

import io.kotest.matchers.shouldBe
import net.twisterrob.astro.bazi.model.BaZi
import net.twisterrob.astro.bazi.model.EarthlyBranch.Chen
import net.twisterrob.astro.bazi.model.EarthlyBranch.Chou
import net.twisterrob.astro.bazi.model.EarthlyBranch.Hai
import net.twisterrob.astro.bazi.model.EarthlyBranch.Xu
import net.twisterrob.astro.bazi.model.EarthlyBranch.Zi
import net.twisterrob.astro.bazi.model.HeavenlyStem.Bing
import net.twisterrob.astro.bazi.model.HeavenlyStem.Gui
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ji
import net.twisterrob.astro.bazi.model.HeavenlyStem.Jia
import net.twisterrob.astro.bazi.model.HeavenlyStem.Ren
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import net.twisterrob.astro.bazi.model.EarthlyBranch.Wu as WuEB
import net.twisterrob.astro.bazi.model.HeavenlyStem.Wu as WuHS

@Disabled("Group44Calculator is not working, I think the month is lunar, not solar and the hour is somehow wrong.")
class Group44CalculatorTest : BaZiCalculatorTest() {

	override val subject = Group44Calculator()

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
}
