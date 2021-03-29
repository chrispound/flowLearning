package com.continuedlearning.demo.combine

import com.continuedlearning.flow.Demo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

@ExperimentalCoroutinesApi
class CombineFlowDemo : Demo {
    override suspend fun launchDemo() {
        val demoFlow = flow1()
            .combine(flow2) { a, b ->
                "$b $a"
            }
        val result = measureTimeMillis {
            demoFlow.collect {
                println(it)
            }
        }

        println("Zip flow finished in: $result ms")

    }


   private fun flow1() = flowOf(1, 2, 3, 4)
        .onEach {
            delay(1000)
        }.onCompletion {
            println("flow1 complete")
        }

   private val flow2 = flowOf("Item 1:", "Item 2:", "Item 3:").onCompletion {
        println("flow2 complete")
    }
}