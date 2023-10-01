package ca.josuelubaki.countryapp.screens.country

import ca.josuelubaki.countryapp.common.CountryQuery
import ca.josuelubaki.countryapp.screens.continent.CurrentBottomSheetContent
import ca.josuelubaki.countryapp.utils.UIErrorType

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

data class CountryState(
    val isLoading: Boolean = true,
    val error: UIErrorType = UIErrorType.Nothing,

    val countryCode: String? = null,
    val data: CountryQuery.Country? = null, // Cuz, i'm a lazy *****
    val currentBottomSheetContent: CurrentBottomSheetContent = CurrentBottomSheetContent.STATES
)