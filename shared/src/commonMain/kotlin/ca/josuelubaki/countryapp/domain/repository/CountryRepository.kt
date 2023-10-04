package ca.josuelubaki.countryapp.domain.repository

import ca.josuelubaki.countryapp.common.ContinentQuery
import ca.josuelubaki.countryapp.common.ContinentsQuery
import ca.josuelubaki.countryapp.common.CountryQuery
import com.apollographql.apollo3.ApolloCall

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

interface CountryRepository {
    suspend fun getCountryDetailsByCode(code : String) : ApolloCall<CountryQuery.Data>
}