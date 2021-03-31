package com.continuedlearning.demo.sequential

import com.continuedlearning.flow.Demo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class FlowIsSequentialDemo {
    private val demoData = listOf(1, 5, 8, -1, -3, -6)

    /**
     * Convert to a sequence
     */
    fun helloIterable(): Int {
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
        return processCount
    }
}