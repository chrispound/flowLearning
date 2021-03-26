package com.continuedlearning.flow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.continuedlearning.flow.databinding.ViewModelFlowFragmentBinding
import com.continuedlearning.flow.repository.DemoDataRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ViewModelFlowFragment : Fragment() {

    private var showError: Boolean = false
    private var _binding: ViewModelFlowFragmentBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var viewModel: ViewModelFlowViewModel
    private var job: Job? = null
    private var flow: Flow<Int>? = null

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

        with(binding){
            cancel.setOnClickListener {
                job?.cancel()
            }
            create.setOnClickListener {
                flow =   flow{
                    manualFlow.text = "Manual Flow block initialized"
                    delay(1000)
                    Log.d("fragmentFlow", " I've been created")
                    Log.d("fragmentFlow", "Current Thread: ${Thread.currentThread().name}")
                    while(true) {
                        val latestNumber = Math.random() * 100
                        emit(latestNumber.toInt())
                        delay(1500)
                        if(showError) {
                            throw Throwable("Flow cancelled with exception")
                        }
                    }
                }
                manualFlow.text = "Manual Flow created"
            }

            collect.setOnClickListener { _ ->
                job = viewLifecycleOwner.lifecycleScope.launch {
                     flow?.onCompletion { reason ->
                         if(reason == null) {
                             manualFlow.text = "Manual Flow exited successfuly"
                         } else {
                             manualFlow.text = "Manual Flow exited with exception: $reason"
                             showError = false
                         }
                     }
                         ?.catch { data ->
                             manualFlow.text = "Manual Flow caught exception: $data"
                         }
                    ?.collect {
                         manualFlow.text = "Manual Flow Collected: $it"
                    }

                }

            }

            cancelException.setOnClickListener {
                showError = true
            }
        }

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
