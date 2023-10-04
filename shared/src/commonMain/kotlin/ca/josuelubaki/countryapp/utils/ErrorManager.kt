package ca.josuelubaki.countryapp.utils

import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloHttpException
import com.apollographql.apollo3.exception.ApolloNetworkException

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

sealed class HttpError {
    data object UNAUTHORIZED : HttpError()
    data object APOLLO_EXCEPTION : HttpError()
    data object INTERNET_CONNECTION : HttpError()
    data object CODE_MISSING : HttpError()
    data object UNKNOWN : HttpError()

    fun getErrorMessage(): String {
        return when (this) { // TODO : Internalization
            UNAUTHORIZED -> "You are not authorized to access this"
            APOLLO_EXCEPTION -> "An error has occurred in our service"
            INTERNET_CONNECTION -> "Please, verify your internet connection"
            CODE_MISSING -> "Continent code missing"
            else -> "An error has occurred"
        }
    }

    fun handleException(exception: Exception) : HttpError {
        return when (exception) {
            is ApolloException,
            is ApolloNetworkException,
            is ApolloHttpException-> APOLLO_EXCEPTION
            else -> UNKNOWN
        }
    }

    fun handleErrorCode(code: Int) : HttpError {
        return when (code) {
            401 -> UNAUTHORIZED
            else -> UNKNOWN
        }
    }
}