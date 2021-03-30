package com.continuedlearning.flow.completedtasks.aslivedata

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import com.continuedlearning.flow.ContinuedLearningTaskFragment

class LiveDataFlowFragment : ContinuedLearningTaskFragment() {

    private val emittedItems = 0
    private val data = listOf(1,2,3,4)

    private lateinit var viewModel: LiveDataFlowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = LiveDataFlowViewModel(data)

        viewModel.data.observe(viewLifecycleOwner) { item ->
            binding.taskOutput.append(item.toString())
        }
    }

    override suspend fun executeTask(): Boolean {
        Toast.makeText(requireContext(), "Not yet implemented", Toast.LENGTH_SHORT ).show()
        return verifyTaskComplete()
    }

    override fun getTaskTitle(): String {
       return "Covert flow to livedata and emit all items from viewmodel.data to binding.taskOutput"
    }

    override fun verifyTaskComplete():Boolean {
        return emittedItems == data.size && binding.taskOutput.text.toString() == "1234" && viewModel.data is LiveData<*>
    }


}
