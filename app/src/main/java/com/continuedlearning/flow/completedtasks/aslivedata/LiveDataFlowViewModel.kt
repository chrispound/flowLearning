package com.continuedlearning.flow.completedtasks.aslivedata

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.asFlow

class LiveDataFlowViewModel(items: List<Any>) : ViewModel() {

    val data = items.asFlow().asLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.d("onCleared", "viewModelCleared")
    }
}