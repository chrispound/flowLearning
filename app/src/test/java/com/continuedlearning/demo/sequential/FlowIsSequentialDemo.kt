package com.continuedlearning.demo.sequential

import com.continuedlearning.flow.Demo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class FlowIsSequentialDemo : Demo {
    private val demoData = listOf(1, 5, 8, -1, -3, -6)
    override suspend fun launchDemo() {
        helloSequence()
        helloIterable()
    }

    init {

        callbackFlow<Int> {
            offer(1)
            awaitClose {  }
        }
    }

    private fun helloSequence() {
        println("Starting sequence demo")
        var processCount = 0
        val result = demoData.asSequence()
            .filter {
                processCount++
                it > 0
            }.map {
                processCount++
                it +5
            }.take(2)
            .toList()
        println("Result $result")
        println("Total Process: $processCount")
    }

    private fun helloIterable() {
        println("Starting iterable demo")
        var processCount = 0
        val result = demoData.filter {
            processCount++
            it > 0
        }.map {
            processCount++
            it + 5
        }.take(2)
        println("Result: $result")
        println("Total Process: $processCount")
    }
}