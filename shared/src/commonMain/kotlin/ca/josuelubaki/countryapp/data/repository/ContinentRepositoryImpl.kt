package ca.josuelubaki.countryapp.data.repository

import ca.josuelubaki.countryapp.common.ContinentQuery
import ca.josuelubaki.countryapp.data.api.CountryAPI
import ca.josuelubaki.countryapp.domain.repository.ContinentRepository
import com.apollographql.apollo3.ApolloCall

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class ContinentRepositoryImpl(private val service: CountryAPI) : ContinentRepository {

    override suspend fun getContinentById(id: String) = service.continentQuery(id)
}