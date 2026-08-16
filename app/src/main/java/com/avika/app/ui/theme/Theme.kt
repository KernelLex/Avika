package com.avika.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AvikaLightColorScheme = lightColorScheme(
    primary = AvikaTeal,
    onPrimary = AvikaSurface,
    primaryContainer = AvikaTealLight,
    onPrimaryContainer = AvikaTealDark,
    secondary = AvikaAmber,
    onSecondary = AvikaSurface,
    secondaryContainer = AvikaAmberLight,
    onSecondaryContainer = AvikaTealDark,
    background = AvikaBackground,
    onBackground = AvikaOnSurface,
    surface = AvikaSurface,
    onSurface = AvikaOnSurface,
    surfaceVariant = AvikaTealLight,
    onSurfaceVariant = AvikaOnSurfaceMuted,
    outline = AvikaOutline,
    error = AvikaError,
)

private val AvikaDarkColorScheme = darkColorScheme(
    primary = AvikaTealLight,
    onPrimary = AvikaTealDark,
    primaryContainer = AvikaTealDark,
    onPrimaryContainer = AvikaTealLight,
    secondary = AvikaAmber,
    onSecondary = AvikaTealDark,
    background = androidx.compose.ui.graphics.Color(0xFF161C1B),
    onBackground = androidx.compose.ui.graphics.Color(0xFFE4E2DD),
    surface = androidx.compose.ui.graphics.Color(0xFF1E2423),
    onSurface = androidx.compose.ui.graphics.Color(0xFFE4E2DD),
)

@Composable
fun AvikaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) AvikaDarkColorScheme else AvikaLightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AvikaTypography,
        content = content
    )
}
