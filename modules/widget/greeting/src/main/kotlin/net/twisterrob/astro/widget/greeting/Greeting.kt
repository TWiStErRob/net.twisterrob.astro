package net.twisterrob.astro.widget.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Basic widget to get something on screen.
 */
@Composable
public fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = greet(name),
		modifier = modifier
	)
}

internal fun greet(name: String): String =
	"Hello ${name}!"

@Preview
@Composable
private fun GreetingPreview() {
	Greeting("Preview")
}
