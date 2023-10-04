package ca.josuelubaki.countryapp.presentation.continents

import ca.josuelubaki.countryapp.domain.models.Continent
import ca.josuelubaki.countryapp.utils.HttpError

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

sealed class ContinentsState {
    data object Loading : ContinentsState()
    data object Idle : ContinentsState()
    data class Success(val data: List<Continent>? = emptyList()) : ContinentsState()
    data class Error(val error : HttpError) : ContinentsState()

}