package com.continuedlearning.flow.api

import ApiCallback
import ApiData
import ApiError
import kotlinx.coroutines.delay

class ApiRepository(var callback: ApiCallback) {

    suspend fun execute() {
        delay(5000)
        callback.onNext(ApiData("Hello Callback"))
    }

    suspend fun executeWithError() {
        delay(5000)
        callback.onError(ApiError("Error"))
    }

    suspend fun nonMainThreadSafeWOrk() {
        if(Thread.currentThread().name == "Main") {
            throw Throwable("Cannot call on main thread")
        }
    }
}