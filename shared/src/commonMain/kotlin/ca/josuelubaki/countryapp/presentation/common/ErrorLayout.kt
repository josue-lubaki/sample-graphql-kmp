package ca.josuelubaki.countryapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ca.josuelubaki.countryapp.utils.UIErrorType

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

@Composable
fun ErrorLayout(
    errorType: UIErrorType,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (errorType) {
            UIErrorType.Network -> Text("No internet!", style = MaterialTheme.typography.bodyLarge)
            is UIErrorType.Other -> Text(errorType.error, style = MaterialTheme.typography.bodyLarge)
            else -> {/* no-op */
            }
        }

        TextButton(onClick) {
            Text(
                "Retry?",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }

}