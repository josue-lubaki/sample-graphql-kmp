package ca.josuelubaki.countryapp.domain.repository

import ca.josuelubaki.countryapp.common.ContinentsQuery
import com.apollographql.apollo3.ApolloCall

/**
 * created by Josue Lubaki
 * date : 2023-10-03
 * version : 1.0.0
 */

interface ContinentsRepository {
    suspend fun getAllContinents() : ApolloCall<ContinentsQuery.Data>
}