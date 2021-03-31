package com.continuedlearning.flow.tasks.useaflow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.continuedlearning.flow.ContinuedLearningTaskFragment
import com.continuedlearning.flow.R
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
        Toast.makeText(requireContext(), "Not yet implemented", Toast.LENGTH_SHORT ).show()
        return verifyTaskComplete()
    }

    override fun getTaskTitle(): String {
       return "Emit all flow items from viewmodel.data to binding.taskOutput"
    }

    override fun verifyTaskComplete():Boolean {
        return emittedItems == data.size && binding.taskOutput.text.toString() == "1234"
    }

    override fun getNextTaskAction(): Int {
        return R.id.action_viewModelFlowFragment_to_liveDataFlowFragment
    }


}
