package net.twisterrob.astro.test.compose

import android.content.Context
import androidx.annotation.StringRes
import androidx.test.core.app.ApplicationProvider

public fun string(@StringRes resId: Int): String {
	val appContext: Context = ApplicationProvider.getApplicationContext()
	return appContext.getString(resId)
}
