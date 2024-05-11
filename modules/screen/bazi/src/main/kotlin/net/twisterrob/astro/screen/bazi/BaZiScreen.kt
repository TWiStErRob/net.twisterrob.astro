package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.bazi.SolarCalculator
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.widget.bazi.BaZi
import java.time.LocalDateTime

@Composable
public fun BaZiScreen(modifier: Modifier = Modifier) {
	val dateTime = LocalDateTime.now()
	Column(
		modifier = modifier,
	) {
		Text(
			text = dateTime.toString(),
			modifier = Modifier
				.align(CenterHorizontally)
				.padding(4.dp),
		)
		BaZi(
			bazi = SolarCalculator().calculate(dateTime),
		)
	}
}

@Preview
@Composable
private fun BaZiScreenPreview() {
	AppTheme {
		BaZiScreen()
	}
}
