package ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF1EB980),
    primaryContainer = Color(0xFF045D56),
    secondary = Color(0xFFAFFFB),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    background = colorDarkBackground,
    tertiary = Color(0xFF2E2F38)
)

private val LightColorPalette = lightColorScheme(
    primary = Color(0xFF1EB980),
    primaryContainer = Color(0xFF045D56),
    secondary = Color(0xFF00291D),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    background = colorLightBackground,
    tertiary = Color.White
)

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}