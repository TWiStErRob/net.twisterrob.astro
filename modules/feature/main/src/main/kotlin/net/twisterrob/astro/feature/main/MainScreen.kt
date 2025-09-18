package net.twisterrob.astro.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.screen.bazi.BaZiScreen
import net.twisterrob.astro.widget.wuxing.cycle.WuXingCycle

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
		Column(
			modifier = Modifier.padding(innerPadding)
		) {
			BaZiScreen()
			WuXingCycle(
				modifier = Modifier
					.align(alignment = Alignment.CenterHorizontally),
				size = 150.dp,
				onItemClick = {},
			)
		}
	}
}

@Preview
@Composable
internal fun MainScreenPreview() {
	AppTheme {
		MainScreen()
	}
}
