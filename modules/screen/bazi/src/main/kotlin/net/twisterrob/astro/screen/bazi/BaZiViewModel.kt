package net.twisterrob.astro.screen.bazi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.twisterrob.astro.bazi.SolarCalculator
import net.twisterrob.astro.bazi.model.BaZi
import java.time.ZonedDateTime

/**
 * [ViewModel] for [BaZiScreen].
 */
public class BaZiViewModel : ViewModel() {
	private val _uiState = MutableStateFlow(BaZiState.now())
	internal val uiState: StateFlow<BaZiState> = _uiState.asStateFlow()

	internal fun update() {
		_uiState.value = BaZiState.now()
	}
}

internal class BaZiState(
	val dateTime: ZonedDateTime,
	val baZi: BaZi,
) {
	companion object {
		fun now(): BaZiState {
			val dateTime = ZonedDateTime.now()
			return BaZiState(
				dateTime,
				SolarCalculator().calculate(dateTime.toLocalDateTime())
			)
		}
	}
}
