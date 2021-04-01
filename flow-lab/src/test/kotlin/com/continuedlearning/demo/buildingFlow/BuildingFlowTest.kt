package com.continuedlearning.demo.buildingFlow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion

class BuildingFlowTest {

    fun buildAFlow(): Flow<Int> {
        TODO("Not yet implemented")
    }

    fun buildAFlowWithOperators(input: List<Int>): Flow<String> {
        TODO("Not yet implemented")
    }

    fun combineFlowTest(flowA: Flow<Any>, flowB: Flow<Any>): Flow<String> {
        TODO("Not yet implemented")
    }

    fun catchCrashingFlow() =
        flow {
            emit(1)
            throw Throwable("the flow has crashed")
        }.onCompletion { throwable ->
            if (throwable == null) assert(false)
        }

}