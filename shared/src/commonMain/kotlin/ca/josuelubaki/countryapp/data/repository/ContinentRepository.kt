package ca.josuelubaki.countryapp.data.repository

import ca.josuelubaki.countryapp.data.api.CountryAPI

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class ContinentRepository(private val service: CountryAPI) {

    suspend fun continentQuery(id: String) = service.continentQuery(id)
}