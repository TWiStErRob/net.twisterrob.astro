package net.twisterrob.astro.widget.bazi

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Modifier to allow holding the tap and getting multiple [onClick] callbacks.
 *
 * Originally from [blog post by Carter Hudson](https://proandroiddev.com/creating-a-repeating-button-with-jetpack-compose-b39c4f559f7d).
 * Then migrated to newer compose APIs at [StackOverflow](https://stackoverflow.com/a/76190587/253468).
 */
@SuppressLint("ModifierFactoryUnreferencedReceiver") // False positive https://issuetracker.google.com/issues/278679009
@Suppress("detekt.LongParameterList", "detekt.MaxLineLength")
internal fun Modifier.repeatingClickable(
	interactionSource: InteractionSource,
	enabled: Boolean = true,
	maxDelayMillis: Long = 1000,
	minDelayMillis: Long = 5,
	delayDecayFactor: Float = .20f,
	onClick: () -> Unit,
): Modifier =
	// TODO understand and action: https://mrmans0n.github.io/compose-rules/rules/#avoid-modifier-extension-factory-functions
	@Suppress("detekt.ModifierComposed")
	composed {
		val currentClickListener by rememberUpdatedState(onClick)
		val isEnabled by rememberUpdatedState(enabled)

		pointerInput(interactionSource, isEnabled) {
			coroutineScope {
				awaitEachGesture {
					val down = awaitFirstDown(requireUnconsumed = false)
					val job = launch {
						var currentDelayMillis = maxDelayMillis
						while (isEnabled && down.pressed) {
							currentClickListener()
							delay(currentDelayMillis)
							val nextMillis =
								currentDelayMillis - currentDelayMillis * delayDecayFactor
							currentDelayMillis = nextMillis.toLong().coerceAtLeast(minDelayMillis)
						}
					}
					waitForUpOrCancellation()
					job.cancel()
				}
			}
		}
	}
