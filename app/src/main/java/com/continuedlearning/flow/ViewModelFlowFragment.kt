package com.continuedlearning.flow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.continuedlearning.flow.databinding.ViewModelFlowFragmentBinding
import com.continuedlearning.flow.repository.DemoDataRepository

class ViewModelFlowFragment : Fragment() {

    private var _binding: ViewModelFlowFragmentBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var viewModel: ViewModelFlowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewModelFlowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelFlowViewModel(DemoDataRepository())

        with(viewModel) {
            latestNum.observe(viewLifecycleOwner) {
                binding.data.text = "Current data: $it"
            }
            latestNumNonMain.observe(viewLifecycleOwner) {
                binding.nonMainDisplay.text = "Current data: $it"
            }
        }

        binding.goToAdvancedFlow.setOnClickListener {
            findNavController().navigate(R.id.action_viewModelFlowFragment_to_advancedFlow)
        }
    }

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
