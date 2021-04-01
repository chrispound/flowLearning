import kotlinx.coroutines.delay

class UsefulFunctions {

    companion object {

        // Simulating some long running function that fails
        suspend fun runtimeFailure(throwable: Throwable = Throwable("runTimeFailure")) {
            delay(5000)
            throw throwable
        }

        //Simulating something on Android that would fail if ran on the main thread
        fun nonMainThreadSafeWork(): ApiData {
            if(Thread.currentThread().name.toLowerCase() == "main") {
                throw Throwable("Cannot call on main thread")
            }
            return ApiData("Got result on non main thread")
        }


    }

}