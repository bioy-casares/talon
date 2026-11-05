package io.nisfeb.talon.ui.theme


import androidx.compose.ui.unit.dp
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

// Brand palette — see DESIGN.md for the full rationale. Do not hardcode
// these hexes in composables; always go through MaterialTheme.colorScheme.
// Grayscale by default: colour only where it says something (red for
// errors and unread counts, a ship's own mark, a chosen accent).
private val LightColors = lightColorScheme(
    primary              = Color(0xFF1F1F1F),
    onPrimary            = Color(0xFFFFFFFF),
    primaryContainer     = Color(0xFFE8E8E8),
    onPrimaryContainer   = Color(0xFF1F1F1F),

    secondary            = Color(0xFF404040),
    onSecondary          = Color(0xFFFFFFFF),
    secondaryContainer   = Color(0xFFE8E8E8),
    onSecondaryContainer = Color(0xFF1F1F1F),

    tertiary             = Color(0xFF525252),
    onTertiary           = Color(0xFFFFFFFF),
    tertiaryContainer    = Color(0xFFEDEDED),
    onTertiaryContainer  = Color(0xFF262626),

    background           = Color(0xFFFAFAFA),
    onBackground         = Color(0xFF171717),
    surface              = Color(0xFFFFFFFF),
    onSurface            = Color(0xFF171717),
    surfaceVariant       = Color(0xFFF2F2F2),
    onSurfaceVariant     = Color(0xFF5C5C5C),

    outline              = Color(0xFFA3A3A3),
    outlineVariant       = Color(0xFFE5E5E5),
    error                = Color(0xFFDC2626),
    onError              = Color(0xFFFFFFFF),
    errorContainer       = Color(0xFFFEE2E2),
    onErrorContainer     = Color(0xFF7F1D1D),
    // Material 3's newer surface roles. Unset, they fall back to the
    // baseline lavender-tinted greys and an amber surfaceTint, which
    // put purple top bars, cards, menus and dialogs, and amber-washed
    // elevated surfaces, on top of this warm stone palette in light mode.
    surfaceTint          = Color(0xFF5C5C5C),
    surfaceDim           = Color(0xFFE5E5E5),
    surfaceBright        = Color(0xFFFFFFFF),
    surfaceContainerLowest  = Color(0xFFFFFFFF),
    // Every popup is drawn in the surface colour, as Talon's own are and
    // as its cards are: Material gives sheets, menus and dialogs a shade
    // each (Low, the default, High), so each kind was a different grey
    // from the others and from the screen under it.
    surfaceContainerLow     = Color(0xFFFFFFFF),
    surfaceContainer        = Color(0xFFFFFFFF),
    surfaceContainerHigh    = Color(0xFFFFFFFF),
    surfaceContainerHighest = Color(0xFFE5E5E5),
    inverseSurface       = Color(0xFF171717),
    inverseOnSurface     = Color(0xFFFAFAFA),
    inversePrimary       = Color(0xFFD4D4D4),
    scrim                = Color(0xFF000000),
)

private val DarkColors = darkColorScheme(
    primary              = Color(0xFFEDEDED),
    onPrimary            = Color(0xFF141414),
    primaryContainer     = Color(0xFF333333),
    onPrimaryContainer   = Color(0xFFEDEDED),

    secondary            = Color(0xFFCFCFCF),
    onSecondary          = Color(0xFF141414),
    secondaryContainer   = Color(0xFF333333),
    onSecondaryContainer = Color(0xFFEDEDED),

    tertiary             = Color(0xFFBDBDBD),
    onTertiary           = Color(0xFF141414),
    tertiaryContainer    = Color(0xFF2E2E2E),
    onTertiaryContainer  = Color(0xFFE5E5E5),

    background           = Color(0xFF121212),
    onBackground         = Color(0xFFF5F5F5),
    surface              = Color(0xFF1A1A1A),
    onSurface            = Color(0xFFF2F2F2),
    surfaceVariant       = Color(0xFF262626),
    onSurfaceVariant     = Color(0xFFA3A3A3),

    outline              = Color(0xFF4A4A4A),
    outlineVariant       = Color(0xFF2B2B2B),
    error                = Color(0xFFF87171),
    onError              = Color(0xFF7F1D1D),
    errorContainer       = Color(0xFF7F1D1D),
    onErrorContainer     = Color(0xFFFECACA),
    surfaceTint          = Color(0xFFA3A3A3),
    surfaceDim           = Color(0xFF121212),
    surfaceBright        = Color(0xFF333333),
    surfaceContainerLowest  = Color(0xFF0D0D0D),
    // The surface colour, for every popup: see the light palette.
    surfaceContainerLow     = Color(0xFF1A1A1A),
    surfaceContainer        = Color(0xFF1A1A1A),
    surfaceContainerHigh    = Color(0xFF1A1A1A),
    surfaceContainerHighest = Color(0xFF2E2E2E),
    inverseSurface       = Color(0xFFF2F2F2),
    inverseOnSurface     = Color(0xFF171717),
    inversePrimary       = Color(0xFF404040),
    scrim                = Color(0xFF000000),
)

/** The palette for a mode, before any accent override. */
internal fun talonColors(darkTheme: Boolean) = if (darkTheme) DarkColors else LightColors

@Composable
fun TalonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    /** When non-null, overrides `colorScheme.primary` (and its
     *  `onPrimary` contrast pair) with the user's chosen accent.
     *  null = use the brand palette unchanged.
     *
     *  Container variants (`primaryContainer` / `onPrimaryContainer`)
     *  are intentionally left as the brand to avoid a fully-recomputed
     *  tonal palette per accent — the FilterChip/IconButton surfaces
     *  that lean on `primary` get the override; chip backgrounds and
     *  larger primaryContainer fills stay brand-stable. */
    accentOverride: Color? = null,
    /** A user-made theme; it brings its own light or dark mode. */
    customTheme: CustomTheme? = null,
    /** The font the owner chose for the app; null for the system's. Code keeps its own monospace. */
    fontFamily: androidx.compose.ui.text.font.FontFamily? = null,
    /** False leaves the window and its bars alone: for a card drawn over
     *  another app, whose window painted would hide what is behind it. */
    paintWindow: Boolean = true,
    content: @Composable () -> Unit,
) {
    val dark = customTheme?.dark ?: darkTheme
    val base = customTheme?.let(::customScheme) ?: talonColors(dark)
    if (paintWindow) SystemBarsAppearance(dark, base.background)
    val effective = if (accentOverride == null) base else base.copy(
        primary = accentOverride,
        onPrimary = if (accentOverride.luminance() > 0.5f) Color(0xFF1C1917)
        else Color(0xFFFFFFFF),
    )
    MaterialTheme(
        colorScheme = effective,
        typography = if (fontFamily == null) TalonTypography else TalonTypography.inFamily(fontFamily),
        shapes = TalonShapes,
    ) {
        // The two extras a theme may set that Material has no role for.
        val selection = customTheme?.selectionColor()
        val provided = listOfNotNull(
            LocalLinkColor provides customTheme?.linkColor(),
            selection?.let {
                androidx.compose.foundation.text.selection.LocalTextSelectionColors provides
                    androidx.compose.foundation.text.selection.TextSelectionColors(handleColor = it, backgroundColor = it.copy(alpha = 0.4f))
            },
        )
        androidx.compose.runtime.CompositionLocalProvider(*provided.toTypedArray(), content = content)
    }
}

/**
 * The link colour the theme sets, for links and mentions in messages,
 * statuses and markdown; null keeps the standard link blue they are
 * drawn in, whatever the theme. See [CustomTheme.link].
 */
val LocalLinkColor = androidx.compose.runtime.staticCompositionLocalOf<Color?> { null }

/**
 * The app's own corners.
 *
 * Material's defaults round a dialog by 28dp and a sheet by 16, which
 * is nothing like the 8 to 12 every surface in Talon is drawn with, so
 * a dialog opened over the app read as another app's window sitting on
 * top of it. Here rather than at each call site: every Material
 * component takes its shape from this, so the calendar's popup, the
 * orrery action, the menus and the sheets all move together and a new
 * one is right without being told.
 *
 * Buttons are the exception Material makes: it draws them as pills
 * whatever the theme says, so Talon's [io.nisfeb.talon.ui.Button],
 * [io.nisfeb.talon.ui.OutlinedButton] and [io.nisfeb.talon.ui.TextButton]
 * give them [small] like the chips beside them.
 */
private val TalonShapes = androidx.compose.material3.Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(6.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
)
