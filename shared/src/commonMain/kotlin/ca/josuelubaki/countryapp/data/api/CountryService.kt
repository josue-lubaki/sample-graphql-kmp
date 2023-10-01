package ca.josuelubaki.countryapp.data.api

import ca.josuelubaki.countryapp.common.ContinentQuery
import ca.josuelubaki.countryapp.common.ContinentsQuery
import ca.josuelubaki.countryapp.common.CountryQuery
import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

internal class CountryService(
    private val apolloClient : ApolloClient
) : CountryAPI {
    override suspend fun continentsQuery(): ApolloCall<ContinentsQuery.Data> {
        return apolloClient.query(ContinentsQuery())
    }

    override suspend fun continentQuery(id: String): ApolloCall<ContinentQuery.Data> {
        return apolloClient.query(ContinentQuery(id))
    }

    override suspend fun countryQuery(code: String): ApolloCall<CountryQuery.Data> {
        return apolloClient.query(CountryQuery(code))
    }
}