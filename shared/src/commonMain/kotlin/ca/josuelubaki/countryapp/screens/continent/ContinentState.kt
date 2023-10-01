package ca.josuelubaki.countryapp.screens.continent

import ca.josuelubaki.countryapp.common.ContinentQuery
import ca.josuelubaki.countryapp.common.CountryQuery
import ca.josuelubaki.countryapp.utils.UIErrorType

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

enum class CurrentBottomSheetContent {
    STATES, LANGUAGES
}

data class ContinentState(
    val isLoading: Boolean = true,
    val error: UIErrorType = UIErrorType.Nothing,

    val continentId: String? = null,
    val countries: List<ContinentQuery.Country> = listOf(),
    val continentCodeAndName: Pair<String, String>? = null
)