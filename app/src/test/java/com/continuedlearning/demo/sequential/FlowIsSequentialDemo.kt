package com.continuedlearning.demo.sequential

import com.continuedlearning.flow.Demo

class FlowIsSequentialDemo : Demo {
    private val demoData = listOf(1, 5, 8, -1, -3, -6)
    override suspend fun launchDemo() {
        helloSequence()
        helloIterable()
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