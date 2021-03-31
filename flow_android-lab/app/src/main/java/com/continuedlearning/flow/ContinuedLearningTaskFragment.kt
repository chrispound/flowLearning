package com.continuedlearning.flow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.continuedlearning.flow.databinding.FlowTaskFragmentBinding


abstract class ContinuedLearningTaskFragment: Fragment(), ContinuedLearningTask {
    var enableNextText = false
    private var _binding: FlowTaskFragmentBinding? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FlowTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding) {
            taskTitle.text = getTaskTitle()
            nextTask.setBackgroundColor(resources.getColor(R.color.red_500, requireActivity().theme))
            executeTask.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    enableNextText = executeTask()
                    if(enableNextText) {
                        nextTask.setBackgroundColor(resources.getColor(R.color.green_700, requireActivity().theme))
                    } else {
                        Toast.makeText(requireContext(), "Something's not right. Try Again", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            nextTask.setOnClickListener {
                if(enableNextText) {
                    findNavController().navigate(getNextTaskAction())
                } else {
                    Toast.makeText(requireContext(), "Something's not right. Try Again", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    abstract fun getTaskTitle(): String
    abstract fun verifyTaskComplete(): Boolean
    abstract fun getNextTaskAction(): Int

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("onDestroyView", "view destroyed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "fragment destroyed")
    }

}