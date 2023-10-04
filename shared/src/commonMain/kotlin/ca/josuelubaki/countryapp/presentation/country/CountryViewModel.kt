package ca.josuelubaki.countryapp.presentation.country

import ca.josuelubaki.countryapp.data.repository.CountryRepositoryImpl
import ca.josuelubaki.countryapp.domain.usecases.GetCountryDetailsByCodeUseCase
import ca.josuelubaki.countryapp.presentation.countries.CurrentBottomSheetContent
import ca.josuelubaki.countryapp.utils.HttpError
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * created by Josue Lubaki
 * date : 2023-10-01
 * version : 1.0.0
 */

class CountryViewModel(
    private val getCountryDetailsByCodeUseCase: GetCountryDetailsByCodeUseCase,
    private val dispatcher : CoroutineDispatcher
) : ViewModel() {
    private val _state = MutableStateFlow<CountryState>(CountryState.Idle)
    val state get() : StateFlow<CountryState> = _state.asStateFlow()

    private val _currentBottomSheetContent = MutableStateFlow(CurrentBottomSheetContent.STATES)
    val currentBottomSheetContent = _currentBottomSheetContent.asStateFlow()

    fun onEvent(event: CountryEvent){
        when (event){
            is CountryEvent.OnLoadCountry -> {
                getCountryDetails(event.code)
            }
            is CountryEvent.OnSetCurrentBottomSheet -> {
                setCurrentBottomSheet(event.currentBottomSheetContent)
            }
        }
    }

    private fun setCurrentBottomSheet(currentBottomSheetContent: CurrentBottomSheetContent) {
        _currentBottomSheetContent.value = currentBottomSheetContent
    }

    private fun getCountryDetails(countryCode : String?) {
        viewModelScope.launch(dispatcher) {
            countryCode?.let {
                _state.value = CountryState.Loading
                _state.value = try {
                    val country = getCountryDetailsByCodeUseCase(it)
                    CountryState.Success(country)
                } catch (exception: Exception) {
                    CountryState.Error(
                        if ((exception as ApolloException).suppressedExceptions.isNotEmpty()) HttpError.APOLLO_EXCEPTION
                        else HttpError.UNKNOWN
                    )
                }
            } ?: kotlin.run {
                _state.value = CountryState.Error(error = HttpError.CODE_MISSING)
            }
        }
    }
}