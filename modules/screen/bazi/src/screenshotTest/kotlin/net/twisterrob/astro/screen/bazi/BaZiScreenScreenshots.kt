package net.twisterrob.astro.screen.bazi

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.tools.screenshot.PreviewTest
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.test.fixtures.TestInstants

public class BaZiScreenScreenshots {

	@Preview
	@PreviewTest
	@Composable
	private fun Default() {
		viewModel<BaZiViewModel>().select(TestInstants.REPO)
		AppTheme {
			BaZiScreen()
		}
	}
}
