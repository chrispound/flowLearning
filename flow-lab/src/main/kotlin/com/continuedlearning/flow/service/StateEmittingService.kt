package com.continuedlearning.flow.service

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.atomic.AtomicBoolean


class StateEmittingService {

    private val runningService = AtomicBoolean(true)
    private val _stateFlow = MutableStateFlow(State.STARTED)
    val stateFlow = _stateFlow.asStateFlow()

    suspend fun start(timeBetweenItems: Long) {
        runningService.set(true)
        while (runningService.acquire) {
            _stateFlow.emit(State.values().random())
            delay(timeBetweenItems)
        }
    }

    fun stopService() {
        runningService.set(false)
    }
}

enum class State {
    STARTED, LOADING, COMPLETED, FAILED
}