package ca.josuelubaki.countryapp

import android.app.Application
import ca.josuelubaki.countryapp.di.initKoin

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class CountryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}