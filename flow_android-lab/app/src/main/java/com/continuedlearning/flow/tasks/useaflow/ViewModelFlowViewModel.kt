package com.continuedlearning.flow.tasks.useaflow

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.asFlow

class ViewModelFlowViewModel(items: List<Any>) : ViewModel() {

    val data = items.asFlow()

    override fun onCleared() {
        super.onCleared()
        Log.d("onCleared", "viewModelCleared")
    }
}