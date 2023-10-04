package ca.josuelubaki.countryapp.presentation.country

import ca.josuelubaki.countryapp.presentation.countries.CurrentBottomSheetContent

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

sealed class CountryEvent {
    data class OnLoadCountry(val code : String?) : CountryEvent()
    data class OnSetCurrentBottomSheet(val currentBottomSheetContent: CurrentBottomSheetContent) : CountryEvent()
}