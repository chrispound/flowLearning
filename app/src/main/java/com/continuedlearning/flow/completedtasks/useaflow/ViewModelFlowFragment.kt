package com.continuedlearning.flow.completedtasks.useaflow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.continuedlearning.flow.ContinuedLearningTaskFragment
import com.continuedlearning.flow.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModelFlowFragment : ContinuedLearningTaskFragment() {

    private var emittedItems = 0
    private val data = listOf(1, 2, 3, 4)

    private lateinit var viewModel: ViewModelFlowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelFlowViewModel(data)
    }

    override suspend fun executeTask(): Boolean {
        emittedItems = 0
        binding.taskOutput.text = ""
        viewModel.data.collect {
            emittedItems++
            binding.taskOutput.append(it.toString())
        }
        return verifyTaskComplete()
    }

    override fun getTaskTitle(): String {
        return "Emit all flow items from viewmodel.data to binding.taskOutput"
    }

    override fun verifyTaskComplete(): Boolean {
        return emittedItems == data.size && binding.taskOutput.text.toString() == "1234"
    }

    override fun getNextTaskAction(): Int {
        return R.id.action_viewModelFlowFragment_to_liveDataFlowFragment
    }

}
