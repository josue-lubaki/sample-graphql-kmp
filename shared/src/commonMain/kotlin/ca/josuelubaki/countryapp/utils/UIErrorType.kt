package ca.josuelubaki.countryapp.utils

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

sealed class UIErrorType {
    data object Network : UIErrorType()
    data class Other(val error: String = "Something went wrong") : UIErrorType()
    data object Nothing : UIErrorType()

}