package net.twisterrob.astro.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.widget.greeting.Greeting

@Composable
internal fun MainScreen() {
	Scaffold(
		modifier = Modifier.fillMaxSize(),
	) { innerPadding ->
		Greeting(
			name = "Android",
			modifier = Modifier.padding(innerPadding)
		)
	}
}

@Preview
@Composable
internal fun MainScreenPreview() {
	AppTheme {
		MainScreen()
	}
}
