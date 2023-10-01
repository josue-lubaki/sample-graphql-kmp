package ca.josuelubaki.countryapp.data.repository

import ca.josuelubaki.countryapp.data.api.CountryAPI

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class CountryRepository(private val service: CountryAPI) {

    suspend fun countryQuery(code: String) = service.countryQuery(code)
}