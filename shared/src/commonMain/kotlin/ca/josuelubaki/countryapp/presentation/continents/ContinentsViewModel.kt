package ca.josuelubaki.countryapp.presentation.continents

import ca.josuelubaki.countryapp.domain.usecases.GetAllContinentsUseCase
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

class ContinentsViewModel(
    private val getAllContinentsUseCase: GetAllContinentsUseCase,
    private val dispatcher : CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow<ContinentsState>(ContinentsState.Idle)
    val uiState get() : StateFlow<ContinentsState> = _state.asStateFlow()

    fun onEvent(event: ContinentsEvent){
        when (event){
            is ContinentsEvent.OnLoadAllContinents -> getAllContinents()
        }
    }

    private fun getAllContinents() {
        viewModelScope.launch(dispatcher) {
            _state.value = ContinentsState.Loading
            _state.value = try {
                ContinentsState.Success(getAllContinentsUseCase.invoke())
            } catch (exception: Exception) {
                ContinentsState.Error(
                    if ((exception as ApolloException).suppressedExceptions.isNotEmpty()) HttpError.APOLLO_EXCEPTION
                    else HttpError.UNKNOWN
                )
            }
        }
    }
}