package io.nisfeb.talon.ui

import io.nisfeb.talon.ui.theme.ThemePreference

/**
 * "Reset settings": every look-and-layout choice back to how a new
 * install starts. What the owner made or stored is kept (saved themes,
 * installed fonts, the home place, calendars, Orrery's own settings);
 * only which of them is in use goes back to the default. The rail's
 * shown/hidden items are reset by the caller through the ship, which
 * keeps them ([HIDDEN_BY_DEFAULT]).
 */
fun resetLookAndLayout(ui: UiSettings, theme: ThemePreference) {
    theme.setMode(ThemePreference.Mode.System)
    ui.setAccentSettings(AccentSettings())
    ui.setThemeSettings(ui.themeSettings.value.copy(activeId = null))
    ui.setFontSettings(ui.fontSettings.value.copy(family = null))
    ui.setFontScale(1.0f)
    ui.setDensity(Density.Comfortable)
    ui.setKeybinds(emptyMap())
    ui.setMicProcessing(io.nisfeb.talon.call.MicProcessing())
    ui.setGroupChannelOrder(GroupChannelOrder.Recent)
    ui.setFolderItemOrder(FolderItemOrder.Manual)
    ui.setChatPaneListFraction(DEFAULT_LIST_FRACTION)
    ui.setRightPaneWidthDp(DEFAULT_RIGHT_PANE_WIDTH.value)
    ui.setRailItemOrder(RailItem.entries)
    ui.setHideComposerButtons(false)
    ui.setPowerFeaturesEnabled(false)
    ui.setSwipeQuotes(true)
    ui.setHomeTwentyFourHour(false)
    ui.setSmartSearchPreferred(false)
}
