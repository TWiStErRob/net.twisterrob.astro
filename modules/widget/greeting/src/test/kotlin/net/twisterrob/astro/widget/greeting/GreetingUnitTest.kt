package net.twisterrob.astro.widget.greeting

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GreetingUnitTest {

	@Test
	fun testGreet() {
		greet("Name") shouldBe "Hello Name!"
	}
}
