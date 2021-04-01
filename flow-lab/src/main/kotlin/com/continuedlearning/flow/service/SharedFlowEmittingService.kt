package com.continuedlearning.flow.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.util.concurrent.atomic.AtomicBoolean

class SharedFlowEmittingService {

    private val runningService = AtomicBoolean(true)
    private val _sharedFlow = MutableSharedFlow<State>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    suspend fun start(timeBetweenItems: Long) {
        runningService.set(true)
        while (runningService.acquire) {
            _sharedFlow.emit(State.values().random())
            delay(timeBetweenItems)
        }
    }

    fun stopService() {
        runningService.set(false)
    }
}