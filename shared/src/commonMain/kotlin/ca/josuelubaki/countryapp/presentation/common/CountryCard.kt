package ca.josuelubaki.countryapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

@Composable
fun CountryCard(
    modifier: Modifier = Modifier,
    code: String,
    name: String,
    emoji: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    emoji,
                    style = MaterialTheme.typography.labelLarge,
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    code,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(
                name,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}