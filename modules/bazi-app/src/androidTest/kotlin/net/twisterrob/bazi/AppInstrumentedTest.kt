package net.twisterrob.bazi

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppInstrumentedTest {

	@Test
	fun testPackageName() {
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext

		assertEquals("net.twisterrob.bazi", appContext.packageName)
	}
}
