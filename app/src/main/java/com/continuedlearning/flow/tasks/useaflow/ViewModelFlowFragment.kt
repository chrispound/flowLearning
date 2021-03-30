package com.continuedlearning.flow.tasks.useaflow

import android.os.Bundle
import android.view.View
import com.continuedlearning.flow.ContinuedLearningTaskFragment
import com.continuedlearning.flow.completedtasks.useaflow.ViewModelFlowViewModel

class ViewModelFlowFragment : ContinuedLearningTaskFragment() {

    private val emittedItems = 0
    private val data = listOf(1,2,3,4)

    private lateinit var viewModel: ViewModelFlowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelFlowViewModel(data)
    }

    override suspend fun executeTask(): Boolean {
        TODO("Not yet implemented")
        return verifyTaskComplete()
    }

    override fun getTaskTitle(): String {
       return "Emit all flow items from viewmodel.data to binding.taskOutput"
    }

    override fun verifyTaskComplete():Boolean {
        return emittedItems == data.size && binding.taskOutput.text == "1,2,3,4"
    }


}
