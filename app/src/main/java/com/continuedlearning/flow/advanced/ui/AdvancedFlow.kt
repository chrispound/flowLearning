package com.continuedlearning.flow.advanced.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.continuedlearning.flow.R

class AdvancedFlow : Fragment() {

    companion object {
        fun newInstance() = AdvancedFlow()
    }

    private lateinit var viewModel: AdvancedFlowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.advanced_flow_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdvancedFlowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}