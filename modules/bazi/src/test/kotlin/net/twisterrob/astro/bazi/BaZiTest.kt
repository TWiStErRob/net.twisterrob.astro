package net.twisterrob.astro.bazi

import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.shouldNot
import org.junit.jupiter.api.Test

class BaZiTest {

	@Test fun test() {
		val subject = BaZi()

		subject shouldNot beNull()
	}
}
