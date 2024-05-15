package net.twisterrob.astro.test.compose

import android.content.Context
import androidx.annotation.StringRes
import androidx.test.core.app.ApplicationProvider

/**
 * Get a string from the target application context resources.
 *
 * @param resId Resource ID of the string.
 */
public fun string(@StringRes resId: Int): String {
	val appContext: Context = ApplicationProvider.getApplicationContext()
	return appContext.getString(resId)
}
