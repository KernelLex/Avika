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
    onSecondaryContainer = AvikaAmberDark,
    tertiary = AvikaPlum,
    onTertiary = AvikaSurface,
    tertiaryContainer = AvikaPlumLight,
    onTertiaryContainer = AvikaPlum,
    background = AvikaBackground,
    onBackground = AvikaOnSurface,
    surface = AvikaSurface,
    onSurface = AvikaOnSurface,
    surfaceVariant = AvikaSurfaceContainer,
    onSurfaceVariant = AvikaOnSurfaceMuted,
    surfaceContainerLowest = AvikaSurface,
    surfaceContainerLow = AvikaSurfaceContainer,
    surfaceContainer = AvikaSurfaceContainer,
    surfaceContainerHigh = AvikaTealSoft,
    surfaceContainerHighest = AvikaTealSoft,
    outline = AvikaOutline,
    outlineVariant = AvikaOutline,
    error = AvikaError,
)

private val AvikaDarkColorScheme = darkColorScheme(
    primary = AvikaTealLight,
    onPrimary = AvikaTealDark,
    primaryContainer = AvikaTealDark,
    onPrimaryContainer = AvikaTealLight,
    secondary = AvikaAmber,
    onSecondary = AvikaTealDark,
    secondaryContainer = AvikaAmberDark,
    onSecondaryContainer = AvikaAmberLight,
    tertiary = AvikaPlumLight,
    onTertiary = AvikaTealDark,
    tertiaryContainer = AvikaPlum,
    onTertiaryContainer = AvikaPlumLight,
    background = AvikaDarkBackground,
    onBackground = AvikaDarkOnSurface,
    surface = AvikaDarkSurface,
    onSurface = AvikaDarkOnSurface,
    surfaceVariant = AvikaDarkSurfaceContainer,
    onSurfaceVariant = AvikaDarkOnSurfaceMuted,
    surfaceContainerLowest = AvikaDarkBackground,
    surfaceContainerLow = AvikaDarkSurface,
    surfaceContainer = AvikaDarkSurfaceContainer,
    surfaceContainerHigh = AvikaDarkSurfaceContainer,
    surfaceContainerHighest = AvikaDarkSurfaceContainer,
    outline = AvikaDarkOnSurfaceMuted,
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
        shapes = AvikaShapes,
        content = content
    )
}
