package net.twisterrob.astro.test.compose

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.espresso.Espresso

/**
 * Helper method to press the back button.
 *
 * This is a workaround for the lack of support for back button in [ComposeTestRule].
 */
@Suppress("UnusedReceiverParameter") // Keep it focused, not callable from anywhere.
public fun ComposeTestRule.pressBack() {
	// Also tried https://stackoverflow.com/a/76305761/253468
	// this@ComposeTestRule.onAllNodes(isRoot()).onLast().performKeyPress(KeyEvent(...KEYCODE_BACK...))
	// but didn't work well with dialogs, so resorting to Espresso's battle-tested approach.

	Espresso.pressBack()
}
