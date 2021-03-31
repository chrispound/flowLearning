package com.continuedlearning.flow.completedtasks.stateflow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StateFlowViewModel () : ViewModel() {

    val _mutableStateFlow = MutableStateFlow(State.LOADING)
    val state = _mutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            SlowRunningProducer().generateState(_mutableStateFlow)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("onCleared", "viewModelCleared")
    }
}