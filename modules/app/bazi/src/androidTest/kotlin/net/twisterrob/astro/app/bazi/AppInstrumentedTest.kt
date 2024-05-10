package net.twisterrob.astro.app.bazi

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppInstrumentedTest {

	@Test
	fun testPackageName() {
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext

		appContext.packageName shouldBe "net.twisterrob.bazi"
	}

	@Test
	fun testApplicationClass() {
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext

		appContext.applicationContext should beInstanceOf<App>()
	}
}
