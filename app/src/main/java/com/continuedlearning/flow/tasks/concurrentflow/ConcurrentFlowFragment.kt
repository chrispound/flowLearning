package com.continuedlearning.flow.tasks.concurrentflow

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.continuedlearning.flow.ContinuedLearningTaskFragment
import com.continuedlearning.flow.R
import com.continuedlearning.flow.completedtasks.concurrentflow.ConcurrentFlowViewModel

class ConcurrentFlowFragment : ContinuedLearningTaskFragment() {

    private val emittedItems = 0
    private val data = listOf(1,2,3,4)

    private lateinit var viewModel: ConcurrentFlowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ConcurrentFlowViewModel()
    }

    override suspend fun executeTask(): Boolean {
        Toast.makeText(requireContext(), "Not yet implemented", Toast.LENGTH_SHORT ).show()
        return verifyTaskComplete()
    }

    override fun getTaskTitle(): String {
       return "Fix the flow so it doesn't throw an exception"
    }

    override fun verifyTaskComplete():Boolean {
        return viewModel.flowThrewException
    }

    override fun getNextTaskAction(): Int {
        return R.id.action_concurrentFlowFragment_to_stateFlowFragment
    }


}
