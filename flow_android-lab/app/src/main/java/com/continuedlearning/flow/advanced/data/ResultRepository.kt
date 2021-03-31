package com.continuedlearning.flow.advanced.data

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ResultRepository {
    companion object {
        val api by lazy {
            Retrofit.Builder()
                .baseUrl("https://google.com/")
                .build()
                .create(GoogleSearchService::class.java)
        }
    }

    fun getSearchResults(): Flow<String> =
        flow {
            emit(api.search("test"))
        }.map {
            Log.d("getSearchResults", Thread.currentThread().name)
            delay(1000 * 30)
            "Results: $it"
        }

    fun makeRequest(): Flow<String> {
        val url = URL("https://www.google.com/search?q=test")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

       return flow {
            val input: InputStream = BufferedInputStream(urlConnection.getInputStream())
            urlConnection.inputStream.bufferedReader().use {
                emit(it.readText())
            }
        }.map {
            "Results: $it"
        }.onCompletion {
            urlConnection.disconnect()
        }
    }
}

interface GoogleSearchService {
    @GET("search")
    suspend fun search(@Query("q") search: String): ResponseBody
}

