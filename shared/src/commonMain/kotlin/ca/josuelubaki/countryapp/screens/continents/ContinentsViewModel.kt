package ca.josuelubaki.countryapp.screens.continents

import ca.josuelubaki.countryapp.data.repository.ContinentsRepository
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

class ContinentsViewModel(
    private val continentsRepository: ContinentsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContinentsUIState())
    val uiState = _uiState.asStateFlow()

    fun attemptContinentsQuery() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isLoading = true,
                error = UIErrorType.Nothing
            )
        }
        continentsRepository.continentsQuery().toFlow().catch { exceptions ->
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
//                    error = if ((exceptions as ApolloException).suppressedExceptions.map { it as ApolloException }
//                            .any { it is ApolloNetworkException })
//                        UIErrorType.Network else UIErrorType.Other()
                    error = UIErrorType.Other()
                )
            }
        }.collectLatest { res ->
            if (!res.hasErrors()) {
                val continents = res.data?.continents
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = if (continents.isNullOrEmpty()) UIErrorType.Other("API returned empty list") else UIErrorType.Nothing,
                        continents = continents ?: emptyList()
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
    }
}