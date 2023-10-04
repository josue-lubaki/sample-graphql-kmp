package ca.josuelubaki.countryapp.utils

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

interface States<out T> {
    data object Loading : States<Nothing>
    data object Idle : States<Nothing>
    data class Success<out T>(val data : T) : States<T>
    data class Error(val error : HttpError) : States<Nothing>
}