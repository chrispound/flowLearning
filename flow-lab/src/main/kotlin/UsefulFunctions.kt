import kotlinx.coroutines.delay

class UsefulFunctions {

    companion object {

        suspend fun runtimeFailure(throwable: Throwable = Throwable("runTimeFailure")) {
            delay(5000)
            throw throwable
        }

        fun nonMainThreadSafeWork(): ApiData {
            if(Thread.currentThread().name.toLowerCase() == "main") {
                throw Throwable("Cannot call on main thread")
            }
            return ApiData("Got result on non main thread")
        }


    }

}