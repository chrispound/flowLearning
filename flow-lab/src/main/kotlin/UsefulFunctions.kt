import com.continuedlearning.flow.service.StateEmittingService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

        //Kickstart and collecting a state emitting service
        fun kickstartSharedFlow() = runBlocking {
            val service = StateEmittingService()

            GlobalScope.launch {
                service.start(1000)

            }

            launch {
                service.stateFlow.collect { println("flow1: $it") }
            }

        }


    }

}