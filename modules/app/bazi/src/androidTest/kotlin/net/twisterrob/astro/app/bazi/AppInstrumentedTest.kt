package net.twisterrob.astro.app.bazi

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppInstrumentedTest {

	@Test
	fun testPackageName() {
		val appContext: Context = ApplicationProvider.getApplicationContext()

		appContext.packageName shouldBe "net.twisterrob.bazi.debug"
	}

	@Test
	fun testApplicationClass() {
		val appContext: Context = ApplicationProvider.getApplicationContext()

		appContext should beInstanceOf<App>()
	}
}
