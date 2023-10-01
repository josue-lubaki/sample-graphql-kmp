package ca.josuelubaki.countryapp.screens.continent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.josuelubaki.countryapp.presentation.common.CountryCard
import ca.josuelubaki.countryapp.presentation.common.ErrorLayout
import ca.josuelubaki.countryapp.utils.UIErrorType

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContinentScreen(
    code: String?,
    viewModel: ContinentViewModel,
    onCountryClick: (String) -> Unit,
    onBackPress: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.setup(code)
    }

    val uiState by viewModel.uiState.collectAsState()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            uiState.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            uiState.error != UIErrorType.Nothing,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ErrorLayout(uiState.error, viewModel::attemptContinentQuery)
        }

        AnimatedVisibility(uiState.countries.isNotEmpty(), enter = fadeIn(), exit = fadeOut()) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "${uiState.continentCodeAndName?.second}\n${uiState.continentCodeAndName?.first}"
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = onBackPress
                            ){
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    uiState.countries.forEach {
                        item {
                            CountryCard(code = it.code, name = it.name, emoji = it.emoji) {
                                onCountryClick(it.code)
                            }
                        }
                    }
                }
            }
        }
    }
}