package com.continuedlearning.flow.tasks.stateflow


import com.continuedlearning.flow.ContinuedLearningTaskFragment
import com.continuedlearning.flow.completedtasks.stateflow.StateFlowViewModel

class StateFlowFragment: ContinuedLearningTaskFragment() {

   private val viewModel: StateFlowViewModel = StateFlowViewModel()


    override fun getTaskTitle(): String {
        return "Collected and monitor the changes from the state flow"
    }

    override fun verifyTaskComplete(): Boolean {
        return viewModel._mutableStateFlow.subscriptionCount.value > 1
    }

    override fun getNextTaskAction(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun executeTask(): Boolean {
        TODO("Not yet implemented")
    }
}