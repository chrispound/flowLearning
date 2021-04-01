interface ApiCallback {
    fun onNext(data: ApiData)
    fun onError(error: ApiError)
}

data class ApiData(val response: String)

data class ApiError(val msg: String)