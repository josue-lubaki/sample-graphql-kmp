package ca.josuelubaki.countryapp.screens.continents

import ca.josuelubaki.countryapp.common.ContinentsQuery
import ca.josuelubaki.countryapp.utils.UIErrorType

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

data class ContinentsUIState(
    val isLoading: Boolean = true,
    val error: UIErrorType = UIErrorType.Nothing,
    val continents: List<ContinentsQuery.Continent> = listOf()
)