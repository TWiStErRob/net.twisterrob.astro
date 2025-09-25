package net.twisterrob.astro.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.tools.screenshot.PreviewTest
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.screen.bazi.BaZiViewModel
import net.twisterrob.astro.test.fixtures.TestInstants

public class MainScreenScreenshots {

	@Preview
	@PreviewTest
	@Composable
	private fun Default() {
		viewModel<BaZiViewModel>().select(TestInstants.REPO)
		AppTheme {
			MainScreen()
		}
	}

	@Preview(locale = "hu-HU")
	@PreviewTest
	@Composable
	private fun Hungarian() {
		viewModel<BaZiViewModel>().select(TestInstants.REPO)
		AppTheme {
			MainScreen()
		}
	}
}
