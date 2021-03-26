package com.continuedlearning.flow

import android.util.Log
import androidx.lifecycle.*
import com.continuedlearning.flow.repository.DemoDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class ViewModelFlowViewModel(demoRepo: DemoDataRepository) : ViewModel() {

    private val _latestNum = demoRepo.latestData
        .onCompletion {
            Log.d("latestDataFlow", "Flow completed")
        }
        .asLiveData()
    val latestNum: LiveData<Int>
        get() = _latestNum

    private val _latestNumNonMain = demoRepo.latestData
        .flowOn(Dispatchers.IO)
        .onCompletion {
            Log.d("latestDataFlow", "Flow completed")
        }
        .asLiveData()
    val latestNumNonMain : LiveData<Int>
    get() = _latestNumNonMain

    val latestNumFlow = demoRepo.latestData

    override fun onCleared() {
        super.onCleared()
        Log.d("onCleared", "viewModelCleared")
    }
}