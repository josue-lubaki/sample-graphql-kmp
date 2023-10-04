package ca.josuelubaki.countryapp.di

import ca.josuelubaki.common.BuildKonfig
import ca.josuelubaki.countryapp.data.api.CountryAPI
import ca.josuelubaki.countryapp.data.api.CountryService
import ca.josuelubaki.countryapp.data.repository.ContinentRepositoryImpl
import ca.josuelubaki.countryapp.data.repository.ContinentsRepositoryImpl
import ca.josuelubaki.countryapp.data.repository.CountryRepositoryImpl
import ca.josuelubaki.countryapp.domain.repository.ContinentRepository
import ca.josuelubaki.countryapp.domain.repository.ContinentsRepository
import ca.josuelubaki.countryapp.domain.repository.CountryRepository
import ca.josuelubaki.countryapp.domain.usecases.GetAllContinentsUseCase
import ca.josuelubaki.countryapp.domain.usecases.GetCountriesByContinentIdUseCase
import ca.josuelubaki.countryapp.domain.usecases.GetCountryDetailsByCodeUseCase
import ca.josuelubaki.countryapp.presentation.countries.CountriesViewModel
import ca.josuelubaki.countryapp.presentation.continents.ContinentsViewModel
import ca.josuelubaki.countryapp.presentation.country.CountryViewModel
import ca.josuelubaki.countryapp.utils.provideDispatcher
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

private val dataModule = module {
    // Services
    single<ApolloClient> {
        ApolloClient.Builder()
            .serverUrl(BuildKonfig.BASE_URL)
            .normalizedCache(MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024))
            .build()
    }
    single<CountryAPI> { CountryService(get()) }

    // Repositories
    single<ContinentsRepository> { ContinentsRepositoryImpl(get()) }
    single<ContinentRepository> { ContinentRepositoryImpl(get()) }
    single<CountryRepository> { CountryRepositoryImpl(get()) }
}

private val utilityModule = module {
    factory<CoroutineDispatcher> { provideDispatcher().dispatcher }
}

private val domainModule = module {
    // UseCases
    factory<GetAllContinentsUseCase> { GetAllContinentsUseCase(get()) }
    factory<GetCountriesByContinentIdUseCase> { GetCountriesByContinentIdUseCase(get()) }
    factory<GetCountryDetailsByCodeUseCase> { GetCountryDetailsByCodeUseCase(get()) }

    // ViewModels
    factory<ContinentsViewModel> { ContinentsViewModel(get(), get()) }
    factory<CountriesViewModel> { CountriesViewModel(get(), get()) }
    factory<CountryViewModel> { CountryViewModel(get(), get()) }
}

private val sharedModule = listOf(dataModule, utilityModule, domainModule)

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}