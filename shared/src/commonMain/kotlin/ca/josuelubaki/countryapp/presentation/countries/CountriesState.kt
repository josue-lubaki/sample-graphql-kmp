package ca.josuelubaki.countryapp.presentation.countries

import ca.josuelubaki.countryapp.domain.models.Country
import ca.josuelubaki.countryapp.utils.HttpError

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

sealed class CountriesState {
    data object Loading : CountriesState()
    data object Idle : CountriesState()
    data class Success(
        val countries: List<Country>? = emptyList(),
        val name : String?,
    ) : CountriesState()
    data class Error(val error : HttpError) : CountriesState()
}

enum class CurrentBottomSheetContent {
    STATES, LANGUAGES
}