package ca.josuelubaki.countryapp.presentation.countries

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.josuelubaki.countryapp.common.ContinentQuery
import ca.josuelubaki.countryapp.domain.models.Country
import ca.josuelubaki.countryapp.presentation.common.CountryCard
import ca.josuelubaki.countryapp.presentation.common.TopBar

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

@Composable
fun ContinentScreen(
    code: String?,
    viewModel: CountriesViewModel,
    onCountryClick: (String) -> Unit,
    onBackPress: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(CountriesEvent.OnLoadCountries(code))
    }

    Content(
        state = state,
        onCountryClick = onCountryClick,
        onBackPress = onBackPress
    )
}

@Composable
private fun Content(
    state: CountriesState,
    onCountryClick: (String) -> Unit,
    onBackPress: () -> Unit
) {
    AnimatedContent(
        targetState = state,
        label = "Country list animation",
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
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            val topBarTitle = when(currentState){
                is CountriesState.Success -> currentState.name.orEmpty()
                else -> ""
            }
            Scaffold(
                topBar = {
                    TopBar(
                        title = topBarTitle,
                        onBackPress = onBackPress
                    )
                }
            ) { paddingValues ->
                when(currentState){
                    is CountriesState.Success -> {
                        ContentSuccess(
                            countries = currentState.countries,
                            paddingValues = paddingValues,
                            onCountryClick = onCountryClick
                        )
                    }
                    is CountriesState.Error -> {
                        Text(
                            currentState.error.getErrorMessage(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                    is CountriesState.Loading -> {
                        CircularProgressIndicator()
                    }
                    else -> Unit
                }
            }
        }
    }
}

@Composable
private fun ContentSuccess(
    countries: List<Country>?,
    paddingValues : PaddingValues,
    onCountryClick : (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        countries?.forEach {
            item {
                CountryCard(
                    code = it.code,
                    name = it.name,
                    emoji = it.emoji
                ) {
                    onCountryClick(it.code)
                }
            }
        }
    }
}
