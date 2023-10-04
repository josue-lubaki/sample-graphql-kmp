package ca.josuelubaki.countryapp.presentation.country

import ca.josuelubaki.countryapp.domain.models.Country
import ca.josuelubaki.countryapp.utils.HttpError

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

sealed class CountryState {
    data object Loading : CountryState()
    data object Idle : CountryState()
    data class Success(val data: Country? = null) : CountryState()
    data class Error(val error : HttpError) : CountryState()
}