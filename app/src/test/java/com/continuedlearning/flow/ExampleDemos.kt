package com.continuedlearning.flow

import com.continuedlearning.demo.sequential.FlowIsSequentialDemo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleDemos {
    @ExperimentalCoroutinesApi
    @Test
   fun demoSequence() = runBlockingTest {
       val demo = FlowIsSequentialDemo()
        demo.launchDemo()
        assert(true)
    }
}