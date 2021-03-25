package com.continuedlearning.flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.continuedlearning.flow.databinding.ViewModelFlowFragmentBinding
import com.continuedlearning.flow.repository.DemoDataRepository

class ViewModelFlowFragment : Fragment() {

    private  var binding: ViewModelFlowFragmentBinding? = null
    private lateinit var viewModel: ViewModelFlowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewModelFlowFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelFlowViewModel(DemoDataRepository())
        viewModel.latestNum.observe(viewLifecycleOwner) {
            binding!!.data.text = "Current data: $it"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}