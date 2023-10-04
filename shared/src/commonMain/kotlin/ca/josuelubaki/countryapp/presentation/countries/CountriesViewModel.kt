package ca.josuelubaki.countryapp.presentation.countries

import ca.josuelubaki.countryapp.domain.usecases.GetCountriesByContinentIdUseCase
import ca.josuelubaki.countryapp.utils.HttpError
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class CountriesViewModel(
    private val getCountriesByContinentIdUseCase: GetCountriesByContinentIdUseCase,
    private val dispatcher : CoroutineDispatcher
) : ViewModel() {
    private val _state = MutableStateFlow<CountriesState>(CountriesState.Idle)
    val state get() : StateFlow<CountriesState> = _state.asStateFlow()

    fun onEvent(event: CountriesEvent){
        when (event){
            is CountriesEvent.OnLoadCountries -> {
                getAllCountriesByContinentId(event.code)
            }
        }
    }

    private fun getAllCountriesByContinentId(continentId : String?) {
        viewModelScope.launch(dispatcher) {
            continentId?.let {
                _state.value = CountriesState.Loading
                _state.value = try {
                    val countries = getCountriesByContinentIdUseCase(it)?.countries
                    val name = getCountriesByContinentIdUseCase(it)?.name

                    CountriesState.Success(
                        countries = countries,
                        name = name
                    )
                }
                catch (exception: Exception) {
                    CountriesState.Error(
                        if ((exception as ApolloException).suppressedExceptions.isNotEmpty()) HttpError.APOLLO_EXCEPTION
                        else HttpError.UNKNOWN
                    )
                }
            } ?: kotlin.run {
                _state.value = CountriesState.Error(HttpError.CODE_MISSING)
            }
        }
    }
}