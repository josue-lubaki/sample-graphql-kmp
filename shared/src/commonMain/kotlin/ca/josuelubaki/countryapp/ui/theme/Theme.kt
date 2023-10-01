package ca.josuelubaki.countryapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * created by Josue Lubaki
 * date : 2023-09-30
 * version : 1.0.0
 */

@Composable
fun CountriesTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme())
            darkColorScheme(
                primary = Color.White,
                surface = Color.Black,
                onSurface = Color.White,
                onPrimary = Color.Black,
                error= Color.LightGray,
            )
        else lightColorScheme(
            primary = Color.Black,
            surface = Color.White,
            onSurface = Color.Black,
            onPrimary = Color.White,
            error= Color.Blue,
        ),
        typography = Typography,
        shapes = Shapes(
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(12.dp),
            large = RoundedCornerShape(16.dp)
        )
    ) {
        content()
    }
}