package com.example.basketball.ui.theme

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Custom colors to match basketball theme
private val Gold = Color(0xFFFFD700)
private val DarkBackground = Color(0xFF000000)
private val DarkSurface = Color(0xFF1C1C1C)
private val Gray = Color(0xFF808080)

private val DarkColorScheme = darkColorScheme(
    primary = Gold,
    onPrimary = Color.Black,
    secondary = Gray,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = Color.White,
    onBackground = Color.White
)

// Light theme could be kept for future use but modified to match brand
private val LightColorScheme = lightColorScheme(
    primary = Gold,
    onPrimary = Color.Black,
    secondary = Gray,
    background = Color.White,
    surface = Color(0xFFF5F5F5),
    onSurface = Color.Black,
    onBackground = Color.Black
)

@Composable
fun BasketBallTheme(
    darkTheme: Boolean = true, // Force dark theme as default
    dynamicColor: Boolean = false, // Disable dynamic color to maintain consistent branding
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


