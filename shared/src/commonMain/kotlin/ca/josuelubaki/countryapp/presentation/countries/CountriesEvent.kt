package ca.josuelubaki.countryapp.presentation.countries

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

sealed class CountriesEvent {
    data class OnLoadCountries(val code : String?) : CountriesEvent()
}