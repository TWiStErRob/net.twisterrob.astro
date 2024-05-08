package net.twisterrob.bazi

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.bazi.ui.theme.AppTheme

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = greet(name),
		modifier = modifier
	)
}

internal fun greet(name: String): String =
	"Hello ${name}!"

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	AppTheme {
		Greeting("Preview")
	}
}
