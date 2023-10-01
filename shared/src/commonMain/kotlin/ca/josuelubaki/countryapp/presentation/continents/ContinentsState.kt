//package ca.josuelubaki.countryapp.presentation.continents
//
//import ca.josuelubaki.countryapp.common.ContinentQuery
//import ca.josuelubaki.countryapp.common.ContinentsQuery
//
///**
// * created by Josue Lubaki
// * date : 2023-10-01
// * version : 1.0.0
// */
//
//sealed class ContinentsState {
//    data object Loading : ContinentsState()
//    data object Idle : ContinentsState()
//    data class Success(val data: List<ContinentsQuery.Continent>) : ContinentsState()
//    data class Error(val error : String?) : ContinentsState()
//
//}