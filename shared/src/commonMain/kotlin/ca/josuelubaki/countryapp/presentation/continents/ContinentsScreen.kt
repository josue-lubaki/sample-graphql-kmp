//package ca.josuelubaki.countryapp.presentation.continents
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import ca.josuelubaki.countryapp.common.ContinentsQuery
//
///**
// * created by Josue Lubaki
// * date : 2023-10-01
// * version : 1.0.0
// */
//
//@Composable
//fun ContinentsScreen(
//    viewModel : ContinentsViewModel,
//    onNavigate: (String) -> Unit
//) {
//    val state by viewModel.state.collectAsState()
//    val continents = remember { mutableStateOf<List<ContinentsQuery.Continent>>(emptyList()) }
//    val errors = remember { mutableStateOf<String?>(null) }
//
//    LaunchedEffect(true){
//        viewModel.getContinents()
//    }
//
//    LaunchedEffect(state){
//        when(val targetState = state){
//            is ContinentsState.Success -> {
//                continents.value = targetState.data
//                errors.value = null
//            }
//            is ContinentsState.Error -> {
//                errors.value = targetState.error
//            }
//            else -> Unit
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background),
//        contentAlignment = Alignment.Center
//    ){
//        if(errors.value != null){
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.background),
//                contentAlignment = Alignment.Center
//            ){
//                Text(
//                    text = "Error",
//                    style = MaterialTheme.typography.displaySmall,
//                    color = MaterialTheme.colorScheme.onBackground
//                )
//            }
//        }
//        if(continents.value.isNotEmpty() && errors.value != null){
//            LazyColumn {
//                items(continents.value){
//                    Text(
//                        text = it.name,
//                        color = MaterialTheme.colorScheme.onBackground,
//                        style = MaterialTheme.typography.bodyLarge,
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//            }
//        }
//        else {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.background),
//                contentAlignment = Alignment.Center
//            ){
//                Text(
//                    text = "Loading...",
//                    style = MaterialTheme.typography.displaySmall,
//                    color = MaterialTheme.colorScheme.onBackground
//                )
//            }
//        }
//    }
//
////    when (val targetState = state) {
////        is ContinentsState.Success -> {
////
////        }
////        is ContinentsState.Error -> {
////            Box(
////                modifier = Modifier
////                    .fillMaxSize()
////                    .background(MaterialTheme.colorScheme.background),
////                contentAlignment = Alignment.Center
////            ){
////                Text(
////                    text = "Error",
////                    style = MaterialTheme.typography.displaySmall,
////                    color = MaterialTheme.colorScheme.onBackground
////                )
////            }
////        }
////        ContinentsState.Loading -> {
////            Box(
////                modifier = Modifier
////                    .fillMaxSize()
////                    .background(MaterialTheme.colorScheme.background),
////                contentAlignment = Alignment.Center
////            ){
////                Text(
////                    text = "Loading...",
////                    style = MaterialTheme.typography.displaySmall,
////                    color = MaterialTheme.colorScheme.onBackground
////                )
////            }
////        }
////        else -> Unit
////    }
//
//
//
//}