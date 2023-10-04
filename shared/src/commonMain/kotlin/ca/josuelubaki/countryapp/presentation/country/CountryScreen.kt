package ca.josuelubaki.countryapp.presentation.country

import ca.josuelubaki.countryapp.presentation.common.PrimaryButton
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.josuelubaki.countryapp.domain.models.Country
import ca.josuelubaki.countryapp.presentation.common.SectionWithTitle
import ca.josuelubaki.countryapp.presentation.common.TopBar
import ca.josuelubaki.countryapp.presentation.countries.CurrentBottomSheetContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(
    viewModel: CountryViewModel,
    code: String?,
    onBackPress: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(CountryEvent.OnLoadCountry(code))
    }

    val state by viewModel.state.collectAsState()
    val currentBottomSheetContent by viewModel.currentBottomSheetContent.collectAsState()

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    val onSetCurrentBottomSheetHandler = { newState : CurrentBottomSheetContent ->
        viewModel.onEvent(CountryEvent.OnSetCurrentBottomSheet(newState))
    }

    Content(
        state = state,
        scope = scope,
        scaffoldState = scaffoldState,
        onBackPress = onBackPress,
        currentBottomSheetContent = currentBottomSheetContent,
        onSetCurrentBottomSheet = onSetCurrentBottomSheetHandler
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    state: CountryState,
    scope: CoroutineScope,
    currentBottomSheetContent : CurrentBottomSheetContent,
    onSetCurrentBottomSheet : (CurrentBottomSheetContent) -> Unit,
    scaffoldState: BottomSheetScaffoldState,
    onBackPress : () -> Unit
) {
    AnimatedContent(
        targetState = state,
        label = "country detail - animation"
    ){ currentState ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            when (currentState) {
                is CountryState.Success ->  {
                    ContentSuccess(
                        scope = scope,
                        data = currentState.data,
                        currentBottomSheetContent = currentBottomSheetContent,
                        onSetCurrentBottomSheet = onSetCurrentBottomSheet,
                        scaffoldState = scaffoldState,
                        onBackPress = onBackPress
                    )
                }
                is CountryState.Error -> {
                    Text(
                        currentState.error.getErrorMessage(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                is CountryState.Loading -> {
                    CircularProgressIndicator()
                }
                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContentSuccess(
    scope : CoroutineScope,
    data: Country?,
    currentBottomSheetContent: CurrentBottomSheetContent,
    onSetCurrentBottomSheet : (CurrentBottomSheetContent) -> Unit,
    scaffoldState: BottomSheetScaffoldState,
    onBackPress : () -> Unit
) {
    AnimatedVisibility(
        visible = data != null,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    title = "${data?.name} | ${data?.code}",
                    onBackPress = onBackPress
                )
            },
            sheetTonalElevation = 0.dp,
            sheetPeekHeight = 0.dp,
            containerColor = Color.Transparent,
            sheetShape = RectangleShape,
            sheetContent = {
                BottomSheetContent(
                    data = data,
                    currentBottomSheetContent = currentBottomSheetContent
                )
            },
            sheetContainerColor = MaterialTheme.colorScheme.secondary,
            sheetDragHandle = {
                Icon(
                    imageVector = Icons.Filled.DragHandle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                )
            }
        ) {
            Scaffold { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = "${data?.emoji}",
                            fontSize = 120.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    item {
                        SectionWithTitle(title = "Continents"){
                            Text(
                                text = "${data?.continent?.name} | ${data?.continent?.code}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    item {
                        SectionWithTitle(title = "Currency"){
                            Text(
                                "${data?.currency}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    item {
                        SectionWithTitle(title = "Native"){
                            Text(
                                "${data?.native}",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    item {
                        SectionWithTitle(title = "Phone"){
                            Text(
                                text = "+${data?.phone}",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ){

                            if (!data?.states.isNullOrEmpty()) {
                                PrimaryButton(
                                    text = "States",
                                    colors = ButtonDefaults.outlinedButtonColors()
                                ) {
                                    scope.launch {
                                        onSetCurrentBottomSheet(CurrentBottomSheetContent.STATES)
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            }

                            if (!data?.languages.isNullOrEmpty()){
                                PrimaryButton(
                                    text = "Languages",
                                    colors = ButtonDefaults.outlinedButtonColors()
                                ) {
                                    scope.launch {
                                        onSetCurrentBottomSheet(CurrentBottomSheetContent.LANGUAGES)
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
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BottomSheetContent(
    data : Country?,
    currentBottomSheetContent : CurrentBottomSheetContent
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        tonalElevation = 0.dp,
        color = MaterialTheme.colorScheme.secondaryContainer,
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
            when (currentBottomSheetContent) {
                CurrentBottomSheetContent.STATES -> {
                    stickyHeader {
                        Text(
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(top = 16.dp).fillMaxSize(),
                            text = "States",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                    }
                    data?.states?.forEach {
                        item {
                            val separator = if(!it.code.isNullOrBlank()) " | " else ""
                            Column {
                                Text(
                                    text = "${it.name} $separator ${it.code.orEmpty()}",
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
                            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(top = 16.dp).fillMaxSize(),
                            text = "Languages",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                    }
                    data?.languages?.forEach {
                        item {
                            Column{
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${it.name} / ${it.native}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Start,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(Modifier.weight(1f))
                                    Text(
                                        text = it.code.uppercase(),
                                        style = MaterialTheme.typography.titleMedium,
                                        textAlign = TextAlign.Start,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(Modifier.height(8.dp))
                                Divider(color = MaterialTheme.colorScheme.primary)
                            }

                        }
                    }
                }
            }
        }
    }
}
