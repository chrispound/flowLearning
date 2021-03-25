package com.continuedlearning.flow.repository

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DemoDataRepository {

    val latestData: Flow<Int> =
        flow{
            Log.d("latestDataFlow", " I've been created")
            Log.d("latestDataFlow", "Current Thread: ${Thread.currentThread().name}")
            while(true) {
                val latestNumber = Math.random() * 100
                emit(latestNumber.toInt())
                delay(1500)
            }
        }
}