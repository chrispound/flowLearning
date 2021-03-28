package com.continuedlearning.flow.advanced.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.continuedlearning.flow.R
import com.continuedlearning.flow.databinding.AdvancedFlowFragmentBinding
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class AdvancedFlow : Fragment() {

    companion object {
        fun newInstance() = AdvancedFlow()
    }

    var _binding: AdvancedFlowFragmentBinding? = null
    val binding
        get() = _binding!!

    private lateinit var viewModel: AdvancedFlowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AdvancedFlowFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdvancedFlowViewModel::class.java)
        viewModel.searchApiResult.observe(viewLifecycleOwner) {
            binding.searchResults.text = it
        }
    }

}