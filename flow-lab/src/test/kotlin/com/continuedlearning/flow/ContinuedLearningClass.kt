package com.continuedlearning.flow

import com.continuedlearning.demo.buildingFlow.BuildingFlowTest
import com.continuedlearning.demo.sequential.FlowIsSequentialDemo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class ContinuedLearningClass {

    private val demo = BuildingFlowTest()

    //convert an iterable to a sequence
    @Test
    fun convertIterableToSequence() = runBlockingTest {
        val demo = FlowIsSequentialDemo()
        val result = demo.helloIterable()
        assert(result < 9)
    }

    //implement a flow
    @Test
    fun buildAFlow() = runBlockingTest{
        val verifiedData = listOf(1,2,3,4)
        val result = demo.buildAFlow().toList()
        assert(verifiedData == result)
    }

    //implement a flow with some operators
    @Test
    fun buildAFlowWithOperators() = runBlockingTest{
        val input = listOf(1,2,3,4)
        val verifiedData = listOf("6","7")
        val result = demo.buildAFlowWithOperators(input).toList()
        assert(verifiedData == result)
    }

    //fix a flow that crashes
    @Test
    fun catchCrashingFlow() = runBlockingTest {
       demo.catchCrashingFlow().collect {
            assertNotNull(it)
        }
    }

    //combine a flow
    @Test
    fun combineDistinctFlows() = runBlockingTest {
        val flowA = flowOf(1,2,3,4)
        val flowB = flowOf("Item 1:", "Item 2:","Item 3:", "Item 4:")
        val result =  demo.combineFlowTest(flowA, flowB)

        assertEquals(listOf("Item 1: 1", "Item 2: 2","Item 3: 3", "Item 4: 4"), result.toList())
    }


}