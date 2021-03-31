package com.continuedlearning.flow.tasks.stateflow


import com.continuedlearning.flow.ContinuedLearningTaskFragment
import com.continuedlearning.flow.completedtasks.stateflow.State
import com.continuedlearning.flow.completedtasks.stateflow.StateFlowViewModel

class StateFlowFragment : ContinuedLearningTaskFragment() {

    private val viewModel: StateFlowViewModel = StateFlowViewModel()
    private val results = mutableListOf<State>()

    override fun getTaskTitle(): String {
        return "Collected 10 items from the stateflow"
    }

    override fun verifyTaskComplete(): Boolean {
        return viewModel._mutableStateFlow.subscriptionCount.value == 0 && results.size == 10
    }

    override fun getNextTaskAction(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun executeTask(): Boolean {
        TODO("Not yet implemented")
    }
}