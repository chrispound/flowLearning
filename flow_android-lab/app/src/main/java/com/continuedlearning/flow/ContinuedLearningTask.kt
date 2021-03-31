package com.continuedlearning.flow

interface ContinuedLearningTask {
    suspend fun executeTask(): Boolean
}