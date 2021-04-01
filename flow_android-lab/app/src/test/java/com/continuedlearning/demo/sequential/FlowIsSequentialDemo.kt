package com.continuedlearning.demo.sequential

class FlowIsSequentialDemo {
    private val demoData = listOf(1, 5, 8, -1, -3, -6)

    /**
     * Convert to a sequence
     */
    fun helloIterable(): Int {
        println("Starting iterable demo")
        var processCount = 0
        val result = demoData
            .asSequence()
            .filter {
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