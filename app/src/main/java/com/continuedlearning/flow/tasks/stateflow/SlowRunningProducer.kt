package com.continuedlearning.flow.tasks.stateflow

import com.continuedlearning.flow.completedtasks.stateflow.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

class SlowRunningProducer {
    private val stateList = listOf(State.LOADING, State.COMPLETED, State.DONE, State.ERROR)

    fun generateState(stateFlow: MutableStateFlow<State>) {
        while (true) {
            val randomState = Random.nextInt(0, 4)
            Thread.sleep(2000)
            stateFlow.value = stateList[randomState]
        }
    }
}
