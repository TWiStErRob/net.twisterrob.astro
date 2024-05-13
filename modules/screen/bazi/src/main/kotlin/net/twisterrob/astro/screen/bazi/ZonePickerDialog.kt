package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.component.theme.AppTheme
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.TextStyle

@Composable
internal fun ZonePickerDialog(
	state: ZonedDateTime,
	onZoneSelected: (ZoneId) -> Unit,
	onHideZonePicker: () -> Unit,
	onResetToZone: () -> Unit,
) {
	val zones = ZoneId.getAvailableZoneIds().sorted()
	val example = state.toLocalDateTime()
	var selected by remember { mutableStateOf(state.zone) }
	AlertDialog(
		onDismissRequest = onHideZonePicker,
		confirmButton = {
			TextButton({ onZoneSelected(selected) }) { Text("OK") }
		},
		dismissButton = {
			TextButton(onResetToZone) { Text("Here") }
			TextButton(onHideZonePicker) { Text("Cancel") }
		},
		text = {
			val listState = rememberLazyListState(
				initialFirstVisibleItemIndex = (zones.indexOf(selected.id) - 2)
					.coerceAtLeast(0),
			)
			LazyColumn(
				state = listState,
				verticalArrangement = Arrangement.spacedBy(8.dp),
			) {
				items(
					items = zones,
					key = { it },
				) { zone ->
					ZonePickerItem(
						modifier = Modifier
							.fillMaxWidth(),
						zoneId = zone,
						example = example,
						isSelected = zone == selected.id,
						onSelected = { selected = it },
					)
				}
			}
		}
	)
}

@Composable
private fun ZonePickerItem(
	zoneId: String,
	example: LocalDateTime,
	isSelected: Boolean,
	onSelected: (ZoneId) -> Unit,
	modifier: Modifier = Modifier,
) {
	val locale = LocalContext.current.resources.configuration.locales.get(0)
	val zone = ZoneId.of(zoneId)
	CompositionLocalProvider(
		LocalTextStyle provides if (isSelected)
			LocalTextStyle.current.copy(fontWeight = FontWeight.Bold)
		else
			LocalTextStyle.current,
	) {

		Column(
			modifier = modifier
				.clickable(onClick = { onSelected(zone) }),
		) {
			val name = zone.id
			// Note NARROW returns ID and SHORT returns abbreviation, but only in preview.
			val displayName = zone.getDisplayName(TextStyle.FULL, locale)
			val offset = zone.rules.getOffset(example)
			Text(
				text = "${offset} ${name}",
			)
			Text(
				modifier = Modifier.padding(start = 48.dp),
				text = displayName,
			)
		}
	}
}

@Preview
@Composable
private fun DatePickerDialogPreview() {
	AppTheme {
		ZonePickerDialog(
			state = ZonedDateTime.now(),
			onZoneSelected = {},
			onHideZonePicker = {},
			onResetToZone = {},
		)
	}
}
