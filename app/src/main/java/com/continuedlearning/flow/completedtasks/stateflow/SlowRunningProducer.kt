package com.continuedlearning.flow.completedtasks.stateflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import kotlin.random.Random

class SlowRunningProducer {
    private val stateList = listOf(State.LOADING, State.COMPLETED, State.DONE, State.ERROR)

    suspend fun generateState(stateFlow: MutableStateFlow<State>) {
        withContext(Dispatchers.IO) {
            while (true) {
                val randomState = Random.nextInt(0, 4)
                stateFlow.value = stateList[randomState]
            }
        }
    }
}
