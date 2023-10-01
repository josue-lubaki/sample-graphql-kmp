//package ca.josuelubaki.countryapp.presentation.continent
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//
///**
// * created by Josue Lubaki
// * date : 2023-10-01
// * version : 1.0.0
// */
//
//@Composable
//fun ContinentScreen(
//    viewModel : ContinentViewModel,
//    code: String?,
//    onCountryClick: (String) -> Unit,
//    onBackPress: () -> Unit
//) {
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background),
//        contentAlignment = Alignment.Center
//    ){
//        Text(
//            text = "Continent",
//            style = MaterialTheme.typography.displaySmall,
//            color = MaterialTheme.colorScheme.onBackground
//        )
//    }
//
//}