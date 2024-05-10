package net.twisterrob.astro.widget.greeting

import org.junit.Assert.assertEquals
import org.junit.Test

class GreetingUnitTest {

	@Test
	fun testGreet() {
		assertEquals("Hello Name!", greet("Name"))
	}
}
