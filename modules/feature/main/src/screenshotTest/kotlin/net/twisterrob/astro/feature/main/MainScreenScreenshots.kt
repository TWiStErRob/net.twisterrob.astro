package net.twisterrob.astro.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.screen.bazi.BaZiViewModel
import net.twisterrob.astro.test.fixtures.TestInstants

class MainScreenScreenshots {

	@Preview
	@Composable
	private fun Default() {
		viewModel<BaZiViewModel>().select(TestInstants.REPO)
		AppTheme {
			MainScreen()
		}
	}
}
