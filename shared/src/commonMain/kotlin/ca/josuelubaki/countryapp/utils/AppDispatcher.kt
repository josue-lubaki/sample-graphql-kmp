package ca.josuelubaki.countryapp.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

internal interface AppDispatcher {
    val dispatcher : CoroutineDispatcher
}
internal expect fun provideDispatcher() : AppDispatcher