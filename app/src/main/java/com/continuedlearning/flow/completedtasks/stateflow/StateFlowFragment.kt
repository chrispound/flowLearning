package com.continuedlearning.flow.completedtasks.stateflow


import com.continuedlearning.flow.ContinuedLearningTaskFragment
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

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
        viewModel.state.take(10).onEach {
            binding.taskOutput.text = it.name
        }.toList(results)
        return verifyTaskComplete()
    }
}