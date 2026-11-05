package io.nisfeb.talon.ui

import io.nisfeb.talon.ui.theme.InMemoryThemePreference
import io.nisfeb.talon.ui.theme.ThemePreference
import kotlin.test.Test
import kotlin.test.assertEquals

class SettingsResetTest {
    @Test
    fun `reset puts the look and layout back and keeps what was made`() {
        val ui = InMemoryUiSettings()
        val theme = InMemoryThemePreference(ThemePreference.Mode.Dark)
        ui.setDensity(Density.Compact)
        ui.setFontScale(1.4f)
        ui.setPowerFeaturesEnabled(true)
        ui.setSwipeQuotes(false)
        ui.setRailItemOrder(RailItem.entries.reversed())
        ui.setAccentSettings(AccentSettings(enabled = true))

        resetLookAndLayout(ui, theme)

        assertEquals(ThemePreference.Mode.System, theme.mode.value)
        assertEquals(Density.Comfortable, ui.density.value)
        assertEquals(1.0f, ui.fontScale.value)
        assertEquals(false, ui.powerFeaturesEnabled.value)
        assertEquals(true, ui.swipeQuotes.value)
        assertEquals(RailItem.entries, ui.railItemOrder.value)
        assertEquals(AccentSettings(), ui.accentSettings.value)
    }
}
