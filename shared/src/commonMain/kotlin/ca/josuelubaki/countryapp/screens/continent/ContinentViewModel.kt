package ca.josuelubaki.countryapp.screens.continent

import ca.josuelubaki.countryapp.data.repository.ContinentRepository
import ca.josuelubaki.countryapp.utils.UIErrorType
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloNetworkException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class ContinentViewModel(
    private val continentRepository: ContinentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContinentState())
    val uiState = _uiState.asStateFlow()

    fun setup(continentId: String?) = viewModelScope.launch {
        _uiState.update { state ->
            state.copy(
                continentId = continentId
            )
        }
        attemptContinentQuery()
    }

    fun attemptContinentQuery() = viewModelScope.launch {
        _uiState.value.continentId?.let { continentId ->
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = UIErrorType.Nothing
                )
            }
            continentRepository.continentQuery(continentId).toFlow()
                .catch { exceptions ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = if ((exceptions as ApolloException).suppressedExceptions.map { it as ApolloException }
                                    .any { it is ApolloNetworkException })
                                UIErrorType.Network else UIErrorType.Other()
                        )
                    }
                }.collectLatest { res ->
                    if (!res.hasErrors()) {
                        val continent = res.data?.continent
                        val countries = continent?.countries
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = if (countries.isNullOrEmpty()) UIErrorType.Other("API returned empty list") else UIErrorType.Nothing,
                                countries = countries ?: emptyList(),
                                continentCodeAndName = Pair(
                                    continent?.code ?: "",
                                    continent?.name ?: ""
                                )
                            )
                        }
                    } else {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = UIErrorType.Other()
                            )
                        }
                    }
                }
        } ?: kotlin.run {
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    error = UIErrorType.Other("Continent code missing")
                )
            }
        }
    }

}