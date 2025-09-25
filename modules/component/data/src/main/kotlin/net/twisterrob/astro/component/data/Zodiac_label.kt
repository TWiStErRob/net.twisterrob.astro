package net.twisterrob.astro.component.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import net.twisterrob.astro.bazi.model.Zodiac

/**
 * Localized name for [Zodiac]s.
 */
public val Zodiac.label: String
    @Composable
    @ReadOnlyComposable
    get() = stringResource(id = this.labelRes)

@get:StringRes
private val Zodiac.labelRes: Int
    get() = when (this) {
        Zodiac.Rat -> R.string.component_data__zodiac_rat
        Zodiac.Ox -> R.string.component_data__zodiac_ox
        Zodiac.Tiger -> R.string.component_data__zodiac_tiger
        Zodiac.Rabbit -> R.string.component_data__zodiac_rabbit
        Zodiac.Dragon -> R.string.component_data__zodiac_dragon
        Zodiac.Snake -> R.string.component_data__zodiac_snake
        Zodiac.Horse -> R.string.component_data__zodiac_horse
        Zodiac.Goat -> R.string.component_data__zodiac_goat
        Zodiac.Monkey -> R.string.component_data__zodiac_monkey
        Zodiac.Rooster -> R.string.component_data__zodiac_rooster
        Zodiac.Dog -> R.string.component_data__zodiac_dog
        Zodiac.Pig -> R.string.component_data__zodiac_pig
    }
