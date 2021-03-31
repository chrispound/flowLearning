package com.continuedlearning.flow.tasks.concurrentflow

import android.util.Log
import androidx.lifecycle.ViewModel
import com.continuedlearning.flow.repository.DemoDataRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion

class ConcurrentFlowViewModel : ViewModel() {

    var flowThrewException = false
    val data = DemoDataRepository().latestData.catch {
        Log.e("Caught Exception", it.toString())
    }
        .onCompletion { flowThrewException = it != null }

    override fun onCleared() {
        super.onCleared()
        Log.d("onCleared", "viewModelCleared")
    }
}