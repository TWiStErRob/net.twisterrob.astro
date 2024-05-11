package net.twisterrob.astro.widget.bazi

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
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

internal fun Modifier.repeatingClickable(
	interactionSource: InteractionSource,
	enabled: Boolean,
	maxDelayMillis: Long = 1000,
	minDelayMillis: Long = 5,
	delayDecayFactor: Float = .20f,
	onClick: () -> Unit,
): Modifier =
	composed {
		val currentClickListener by rememberUpdatedState(onClick)

		pointerInput(interactionSource, enabled) {
			@Suppress("DEPRECATION")
			forEachGesture {
				coroutineScope {
					awaitPointerEventScope {
						val down = awaitFirstDown(requireUnconsumed = false)
						val heldButtonJob = launch {
							var currentDelayMillis = maxDelayMillis
							while (enabled && down.pressed) {
								currentClickListener()
								delay(currentDelayMillis)
								val nextMillis = currentDelayMillis - (currentDelayMillis * delayDecayFactor)
								currentDelayMillis = nextMillis.toLong().coerceAtLeast(minDelayMillis)
							}
						}
						waitForUpOrCancellation()
						heldButtonJob.cancel()
					}
				}
			}
		}
	}
