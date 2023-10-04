package ca.josuelubaki.countryapp.presentation.continents

import ca.josuelubaki.countryapp.presentation.countries.CountriesEvent

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

sealed class ContinentsEvent {
    data object OnLoadAllContinents : ContinentsEvent()
}