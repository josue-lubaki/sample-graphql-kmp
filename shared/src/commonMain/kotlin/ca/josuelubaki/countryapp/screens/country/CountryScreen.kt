package ca.josuelubaki.countryapp.screens.country

import ca.josuelubaki.countryapp.screens.continent.ContinentViewModel
import ca.josuelubaki.countryapp.screens.continent.CurrentBottomSheetContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import ca.josuelubaki.countryapp.presentation.common.ErrorLayout
import ca.josuelubaki.countryapp.presentation.common.StuffButton
import ca.josuelubaki.countryapp.presentation.common.StuffCard
import ca.josuelubaki.countryapp.utils.UIErrorType
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CountryScreen(
    viewModel: CountryViewModel,
    code: String?,
    onBackPress: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.setup(code)
    }

    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

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
            ErrorLayout(uiState.error, viewModel::attemptCountryQuery)
        }

        AnimatedVisibility(uiState.data != null, enter = fadeIn(), exit = fadeOut()) {
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "${uiState.data?.name}\n${uiState.data?.code}"
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
                },
                sheetTonalElevation = 0.dp,
                sheetPeekHeight = 0.dp,
                sheetShape = RectangleShape,
                containerColor = Color.Transparent,
                sheetContent = {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                        tonalElevation = 0.dp,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 32.dp,
                                end = 32.dp,
                                bottom = 32.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(32.dp)
                        ) {
                            when (uiState.currentBottomSheetContent) {
                                CurrentBottomSheetContent.STATES -> {
                                    stickyHeader {
                                        Text(
                                            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                                                .padding(top = 32.dp).fillMaxSize(),
                                            text = "States",
                                            style = MaterialTheme.typography.bodyLarge,
                                            textDecoration = TextDecoration.Underline,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    uiState.data?.states?.forEach {
                                        item {
                                            Column {
                                                Text(
                                                    "${it.name}\n${it.code}",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    textAlign = TextAlign.Start,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Spacer(Modifier.height(16.dp))
                                                Divider(color = MaterialTheme.colorScheme.primary)
                                            }
                                        }
                                    }
                                }

                                CurrentBottomSheetContent.LANGUAGES -> {
                                    stickyHeader {
                                        Text(
                                            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)
                                                .padding(top = 32.dp).fillMaxSize(),
                                            text = "Languages",
                                            style = MaterialTheme.typography.bodyLarge,
                                            textDecoration = TextDecoration.Underline,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                    uiState.data?.languages?.forEach {
                                        item {
                                            Column {
                                                Text(
                                                    "${it.name} - ${it.native}",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    textAlign = TextAlign.Start,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Spacer(Modifier.height(16.dp))
                                                Text(
                                                    "Code: ${it.code}, Rtl: ${it.rtl}",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    textAlign = TextAlign.Start,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Spacer(Modifier.height(16.dp))
                                                Divider(color = MaterialTheme.colorScheme.primary)
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            ) {
                Scaffold { paddingValues ->
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.fillMaxSize().padding(paddingValues),
                        columns = StaggeredGridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        verticalItemSpacing = 16.dp,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            StuffCard {
                                Text(
                                    "${uiState.data?.emoji}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            StuffCard {
                                Text(
                                    "Continent",
                                    style = MaterialTheme.typography.labelLarge,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    "${uiState.data?.continent?.name}\n${uiState.data?.continent?.code}",
                                    style = MaterialTheme.typography.titleMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            StuffCard {
                                Text(
                                    "Currency",
                                    style = MaterialTheme.typography.labelLarge,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    "${uiState.data?.currency}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            StuffCard {
                                Text(
                                    "Native",
                                    style = MaterialTheme.typography.labelLarge,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    "${uiState.data?.native}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        item {
                            StuffCard {
                                Text(
                                    "Phone",
                                    style = MaterialTheme.typography.labelLarge,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    "${uiState.data?.phone}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        if (!uiState.data?.states.isNullOrEmpty())
                            item {
                                StuffButton(
                                    "States",
                                    colors = ButtonDefaults.outlinedButtonColors(
                                    )
                                ) {
                                    scope.launch {
                                        viewModel.setCurrentBottomSheet(CurrentBottomSheetContent.STATES)
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            }

                        if (!uiState.data?.languages.isNullOrEmpty())
                            item {
                                StuffButton(
                                    "Languages", colors = ButtonDefaults.outlinedButtonColors(
                                    )
                                ) {
                                    scope.launch {
                                        viewModel.setCurrentBottomSheet(CurrentBottomSheetContent.LANGUAGES)
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            }
                    }
                }
            }

        }
    }

}