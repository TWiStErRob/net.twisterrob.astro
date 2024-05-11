package net.twisterrob.astro.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.twisterrob.astro.component.theme.AppTheme

/**
 * Application entry point, started from launcher icon.
 */
public class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			AppTheme {
				MainScreen()
			}
		}
	}
}
