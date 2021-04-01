package com.continuedlearning.flow.service

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.properties.Delegates
import kotlin.random.Random

class DataEmittingService(onDataChange: (Int) -> Unit) {

    private val runningService = AtomicBoolean(true)
    private var latestDataItem  by Delegates.observable(0) {  _, _, newValue ->
        onDataChange(newValue)
    }

    suspend fun start(timeBetweenItems: Long) {
        withContext(Dispatchers.IO) {
            while (runningService.acquire) {
                latestDataItem = Random.nextInt()
                delay(timeBetweenItems)
            }
        }
    }

    fun stopService() {
        runningService.set(false)
    }
}