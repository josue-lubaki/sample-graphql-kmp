package ca.josuelubaki.countryapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * created by Josue Lubaki
 * date : 2023-10-02
 * version : 1.0.0
 */

@Composable
fun ContinentCard(
    text: String,
    color: Color = MaterialTheme.colorScheme.inverseOnSurface,
    onClick: () -> Unit
) {

    OutlinedCard(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            ),
        colors = CardDefaults.cardColors(containerColor = color),
        border = BorderStroke(
            width = 1.5.dp,
            color = MaterialTheme.colorScheme.onBackground
        )
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .size(140.dp),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(500),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}