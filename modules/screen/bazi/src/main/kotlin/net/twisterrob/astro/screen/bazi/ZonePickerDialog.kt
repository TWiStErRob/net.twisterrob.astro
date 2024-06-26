package net.twisterrob.astro.screen.bazi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.twisterrob.astro.component.theme.AppTheme
import net.twisterrob.astro.compose.defaultLocale
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.TextStyle

@Composable
internal fun ZonePickerDialog(
	state: ZonedDateTime,
	onSelectZone: (ZoneId) -> Unit,
	onHideZonePicker: () -> Unit,
	onResetToZone: () -> Unit,
	zones: ZonesList = ZonesList(ZoneId.getAvailableZoneIds().sorted()),
) {
	var selected by remember { mutableStateOf(state.zone) }
	AlertDialog(
		onDismissRequest = onHideZonePicker,
		confirmButton = {
			TextButton({ onSelectZone(selected) }) {
				Text(stringResource(R.string.screen_bazi__zone_picker_dialog_ok))
			}
		},
		dismissButton = {
			TextButton(onResetToZone) {
				Text(stringResource(R.string.screen_bazi__zone_picker_dialog_current))
			}
			TextButton(onHideZonePicker) {
				Text(stringResource(R.string.screen_bazi__zone_picker_dialog_cancel))
			}
		},
		text = {
			ZoneList(
				zones = zones,
				selected = selected,
				onSelect = { selected = it },
				example = state.toLocalDateTime(),
			)
		}
	)
}

@Immutable
internal data class ZonesList(val items: List<String>)

@Composable
private fun ZoneList(
	zones: ZonesList,
	selected: ZoneId,
	onSelect: (ZoneId) -> Unit,
	example: LocalDateTime,
) {
	val listState = rememberLazyListState(
		initialFirstVisibleItemIndex = (zones.items.indexOf(selected.id) - 2)
			.coerceAtLeast(0),
	)
	LazyColumn(
		state = listState,
		verticalArrangement = Arrangement.spacedBy(8.dp),
	) {
		items(
			items = zones.items,
			key = { it },
		) { zone ->
			ZonePickerItem(
				zoneId = zone,
				example = example,
				isSelected = zone == selected.id,
				onClick = onSelect,
			)
		}
	}
}

@Composable
private fun ZonePickerItem(
	zoneId: String,
	example: LocalDateTime,
	isSelected: Boolean,
	onClick: (ZoneId) -> Unit,
	modifier: Modifier = Modifier,
) {
	val zone = ZoneId.of(zoneId)
	CompositionLocalProvider(
		LocalTextStyle provides if (isSelected) {
			LocalTextStyle.current.copy(fontWeight = FontWeight.Bold)
		} else {
			LocalTextStyle.current
		},
	) {
		Column(
			modifier = modifier
				.clickable(onClick = { onClick(zone) }),
		) {
			val name = zone.id
			// Note NARROW returns ID and SHORT returns abbreviation, but only in preview.
			val displayName = zone.getDisplayName(TextStyle.FULL, defaultLocale)
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
private fun Preview() {
	AppTheme {
		ZonePickerDialog(
			state = ZonedDateTime.now(),
			onSelectZone = {},
			onHideZonePicker = {},
			onResetToZone = {},
		)
	}
}
