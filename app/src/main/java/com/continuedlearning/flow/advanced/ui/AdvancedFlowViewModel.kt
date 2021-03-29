package com.continuedlearning.flow.advanced.ui

import android.util.Log
import androidx.lifecycle.*
import com.continuedlearning.flow.advanced.data.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class AdvancedFlowViewModel : ViewModel() {
    private val _searchApiResult =  ResultRepository().makeRequest().flowOn(Dispatchers.IO).asLiveData()
    val searchApiResult: LiveData<String>
    get() = _searchApiResult


    val slowProducerFlow = flow {
        for(i in 1..Int.MAX_VALUE) {
            emit(i)
        }

        for(i in 1..Int.MAX_VALUE) {
            emit(i)
        }
    }

    private val _stateFlowSample = MutableStateFlow(State.LOADING)
    val stateFlowSample: StateFlow<State>
        get() = _stateFlowSample.asStateFlow()

    init {

        viewModelScope.launch {
            ResultRepository().makeRequest().flowOn(Dispatchers.IO)
                .catch {
                    _stateFlowSample.value = State.ERROR

                }
                .onCompletion {
                    _stateFlowSample.value = State.DONE
                }
                .collect {
                    _stateFlowSample.value = State.COMPLETE
                }
        }
    }

}

enum class State {
    LOADING,
    COMPLETE,
    ERROR,
    DONE
}
