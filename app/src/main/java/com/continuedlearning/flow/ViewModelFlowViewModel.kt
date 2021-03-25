package com.continuedlearning.flow

import androidx.lifecycle.*
import com.continuedlearning.flow.repository.DemoDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ViewModelFlowViewModel(demoRepo: DemoDataRepository) : ViewModel() {
    private val _latestNum = demoRepo.latestData.asLiveData()
    val latestNum: LiveData<Int>
        get() = _latestNum
    private val _latestNumNonMain = demoRepo.latestData
        .flowOn(Dispatchers.IO)
        .asLiveData()
    val latestNumNonMain : LiveData<Int>
    get() = _latestNumNonMain

}