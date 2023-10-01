//package ca.josuelubaki.countryapp.presentation.continents
//
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import moe.tlaster.precompose.viewmodel.ViewModel
//import moe.tlaster.precompose.viewmodel.viewModelScope
//import org.koin.core.component.KoinComponent
//import org.koin.core.component.inject
//
///**
// * created by Josue Lubaki
// * date : 2023-10-01
// * version : 1.0.0
// */
//
//class ContinentsViewModel(
//) : ViewModel(), KoinComponent {
//
//    private val repository: CountryRepository by inject()
//    private val dispatcher : CoroutineDispatcher by inject()
//
//    private val _state = MutableStateFlow<ContinentsState>(ContinentsState.Idle)
//    val state get() : StateFlow<ContinentsState> = _state
//
//    init {
//        getContinents()
//    }
//
//    fun getContinents() {
//        _state.value = ContinentsState.Loading
//        viewModelScope.launch(dispatcher) {
//            try {
//                repository.getAllContinents().collect {
//                    if (it.isNotEmpty()){
//                        _state.value = ContinentsState.Success(data = it)
//                    }
//                    else {
//                        _state.value = ContinentsState.Success(data = emptyList())
//                    }
//                }
//            } catch (e : Exception) {
//                _state.value = ContinentsState.Error(e.message)
//            }
//        }
//    }
//}