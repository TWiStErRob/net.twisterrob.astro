package net.twisterrob.astro.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.screen.bazi.BaZiScreen

@Composable
internal fun MainScreen() {
	Scaffold(
		topBar = {
			@OptIn(ExperimentalMaterial3Api::class)
			CenterAlignedTopAppBar(
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer,
					titleContentColor = MaterialTheme.colorScheme.primary,
				),
				title = {
					Text(stringResource(R.string.feature_main__title))
				},
			)
		},
		modifier = Modifier.fillMaxSize(),
	) { innerPadding ->
		BaZiScreen(Modifier.padding(innerPadding))
	}
}

@Preview
@Composable
internal fun MainScreenPreview() {
	AppTheme {
		MainScreen()
	}
}
