package com.continuedlearning.flow

import com.continuedlearning.demo.buildingFlow.BuildingFlowTest
import com.continuedlearning.demo.combine.CombineFlowDemo
import com.continuedlearning.demo.sequential.FlowIsSequentialDemo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class ContinuedLearningClass {


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
        val demo = BuildingFlowTest()
        val result = demo.buildAFlow().toList()
        assert(verifiedData == result)
    }

    //implement a flow with some operators
    @Test
    fun buildAFlowWithOperators() = runBlockingTest{
        val input = listOf(1,2,3,4)
        val verifiedData = listOf("6","7","8", "9")
        val demo = BuildingFlowTest()
        val result = demo.buildAFlowWithOperators(input).toList()
        assert(verifiedData == result)
    }
    //cancel a flow safely
    //fix a flow that crashes
    //combine a flow
    //use flows onComplete to cleanup



}