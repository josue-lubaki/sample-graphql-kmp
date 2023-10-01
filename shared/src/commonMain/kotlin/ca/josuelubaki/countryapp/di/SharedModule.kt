package ca.josuelubaki.countryapp.di

import ca.josuelubaki.countryapp.data.api.CountryAPI
import ca.josuelubaki.countryapp.data.api.CountryService
import ca.josuelubaki.countryapp.data.repository.ContinentRepository
import ca.josuelubaki.countryapp.data.repository.ContinentsRepository
import ca.josuelubaki.countryapp.data.repository.CountryRepository
import ca.josuelubaki.countryapp.screens.continent.ContinentViewModel
import ca.josuelubaki.countryapp.screens.continents.ContinentsViewModel
import ca.josuelubaki.countryapp.screens.country.CountryViewModel
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

fun initKoin() {
    startKoin {
        modules(
            module {
                single {
                    ApolloClient.Builder()
                        .serverUrl("https://countries.trevorblades.com/graphql")
                        .normalizedCache(MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024))
                        .build()
                }
                single<CountryAPI> { CountryService(get()) }
                single { ContinentsRepository(get()) }
                single { ContinentRepository(get()) }
                single { CountryRepository(get()) }
                factory { ContinentsViewModel(get()) }
                factory { ContinentViewModel(get()) }
                factory { CountryViewModel(get()) }
            }
        )
    }
}