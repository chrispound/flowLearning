package com.continuedlearning.flow.advanced.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.lifecycle.lifecycleScope
import com.continuedlearning.flow.databinding.AdvancedFlowFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn

@InternalCoroutinesApi
class AdvancedFlow : Fragment() {

    private  var job: Job? = null
    private var _binding: AdvancedFlowFragmentBinding? = null
   private  val binding
        get() = _binding!!

    private lateinit var viewModel: AdvancedFlowViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AdvancedFlowViewModel::class.java)
        _binding = AdvancedFlowFragmentBinding.inflate(layoutInflater, container, false)
        lifecycleScope.launchWhenCreated {
            viewModel.slowProducerFlow
                .flowOn(Dispatchers.IO)
                .onCompletion {
                    Log.d("slowFlow", "completed")
                }
                .stateIn(this)
                .collect {
                binding.searchResultsLabel.text = "flow value: $it"
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launchWhenCreated {
            viewModel.stateFlowSample.collect {
                binding.searchResultsLabel.text = it.name
            }
        }

    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }
}