package ca.josuelubaki.countryapp.data.repository

import ca.josuelubaki.countryapp.data.api.CountryAPI
import ca.josuelubaki.countryapp.domain.repository.CountryRepository

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class CountryRepositoryImpl(private val service: CountryAPI) : CountryRepository {

    override suspend fun getCountryDetailsByCode(code: String) = service.countryQuery(code)
}