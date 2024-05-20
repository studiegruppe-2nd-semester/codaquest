package com.example.codaquest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    secondary = md_theme_dark_secondary,
    tertiary = md_theme_dark_tertiary,
    error = md_theme_dark_error,
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
    inversePrimary = md_theme_dark_primaryGreyedOut,
)

@Composable
fun CodaQuestTheme(
    content:
    @Composable()
    () -> Unit,
) {
    val colors = DarkColors

    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = Typography,
    )
}
