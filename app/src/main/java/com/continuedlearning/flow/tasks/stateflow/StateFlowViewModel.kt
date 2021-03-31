package com.continuedlearning.flow.tasks.stateflow

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class StateFlowViewModel () : ViewModel() {

    val _mutableStateFlow = MutableStateFlow(State.LOADING)
    val state = _mutableStateFlow.asStateFlow()

    init {
        SlowRunningProducer().generateState(_mutableStateFlow)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("onCleared", "viewModelCleared")
    }
}