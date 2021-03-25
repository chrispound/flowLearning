package com.continuedlearning.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.continuedlearning.flow.repository.DemoDataRepository

class ViewModelFlowViewModel(demoRepo: DemoDataRepository) : ViewModel() {
    private val _latestNum = demoRepo.latestData.asLiveData()
    val latestNum: LiveData<Int>
        get() = _latestNum
}