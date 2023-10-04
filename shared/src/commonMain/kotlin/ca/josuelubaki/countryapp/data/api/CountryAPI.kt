package ca.josuelubaki.countryapp.data.api

import ca.josuelubaki.countryapp.common.ContinentQuery
import ca.josuelubaki.countryapp.common.ContinentsQuery
import ca.josuelubaki.countryapp.common.CountryQuery
import com.apollographql.apollo3.ApolloCall

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

interface CountryAPI {

    suspend fun continentsQuery(): ApolloCall<ContinentsQuery.Data>

    suspend fun continentQuery(id: String): ApolloCall<ContinentQuery.Data>

    suspend fun countryQuery(code: String): ApolloCall<CountryQuery.Data>

}