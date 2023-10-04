package ca.josuelubaki.countryapp.presentation.continents

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ca.josuelubaki.countryapp.presentation.common.ContinentCard

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

@Composable
fun ContinentsScreen(
    viewModel: ContinentsViewModel,
    onNavigate: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit){
        viewModel.onEvent(ContinentsEvent.OnLoadAllContinents)
    }

    Content(
        state = state,
        onNavigate = onNavigate
    )
}

@Composable
private fun Content(
    state: ContinentsState,
    onNavigate : (String) -> Unit
) {
    AnimatedContent(
        targetState = state,
        label = "continents - animated content",
        transitionSpec = {
            ContentTransform(
                targetContentEnter = fadeIn(animationSpec = tween(300)),
                initialContentExit = fadeOut(animationSpec = tween(300))
            )
        }
    ){currentState ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            when(currentState){
                is ContinentsState.Success -> {
                    Column(
                        modifier = Modifier.padding(top = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Continents \uD83C\uDF0E",
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(24.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            currentState.data?.forEach {
                                item {
                                    ContinentCard(text = it.name){
                                        onNavigate(it.code)
                                    }
                                }
                            }
                        }
                    }
                }
                is ContinentsState.Error -> {
                    Text(
                        currentState.error.getErrorMessage(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                is ContinentsState.Loading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
                else -> Unit
            }
        }
    }
}
