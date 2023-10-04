package ca.josuelubaki.countryapp.data.repository

import ca.josuelubaki.countryapp.data.api.CountryAPI
import ca.josuelubaki.countryapp.domain.repository.ContinentsRepository

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class ContinentsRepositoryImpl(private val service: CountryAPI) : ContinentsRepository {
    override suspend fun getAllContinents() = service.continentsQuery()
}