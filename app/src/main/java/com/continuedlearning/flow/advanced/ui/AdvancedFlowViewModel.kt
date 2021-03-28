package com.continuedlearning.flow.advanced.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.continuedlearning.flow.advanced.data.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flowOn

@InternalCoroutinesApi
class AdvancedFlowViewModel : ViewModel() {
    private val _searchApiResult =  ResultRepository().makeRequest().flowOn(Dispatchers.IO).asLiveData()
    val searchApiResult: LiveData<String>
    get() = _searchApiResult


    fun getSearchResult() {

    }
}