package ca.josuelubaki.countryapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

internal class IosAppDispatcher : AppDispatcher {
    override val dispatcher: CoroutineDispatcher
        get() = Dispatchers.IO
}

internal actual fun provideDispatcher(): AppDispatcher = IosAppDispatcher()