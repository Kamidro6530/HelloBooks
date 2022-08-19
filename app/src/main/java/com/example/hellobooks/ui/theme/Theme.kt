package com.example.hellobooks.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.google.android.material.color.DynamicColors

private val DarkColorScheme = darkColorScheme(
    primary = primary,
    secondary = secondary,
    tertiary = tertiary,
    background = darkgreybackground,
    surface = systemBarsColors
)

private val LightColorScheme = lightColorScheme(
    primary = primary,
    secondary = secondary,
    tertiary = tertiary,
    background = darkgreybackground,
    surface = systemBarsColors

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun HelloBooksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val colors = when {
        Build.VERSION.SDK_INT >= 31 -> {

            when {
                darkTheme -> darkColorScheme(
                    primary = primary,
                    secondary = secondary,
                    tertiary = tertiary,
                    background = darkgreybackground,
                    surface = systemBarsColors
                )
                else -> lightColorScheme(
                    primary = primary,
                    secondary = secondary,
                    tertiary = tertiary,
                    background = darkgreybackground,
                    surface = systemBarsColors
                )
            }
        }
        darkTheme -> DarkColorScheme
        else -> DarkColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.surface.toArgb()
            window.navigationBarColor = colors.surface.toArgb()

            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = darkTheme
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightNavigationBars = darkTheme
        }
    }





    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}