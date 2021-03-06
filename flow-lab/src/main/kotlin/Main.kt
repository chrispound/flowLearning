import com.continuedlearning.flow.service.StateEmittingService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    //Let's start by replacing this println with a basic flow{}.collect{}
    val main = Main()
    println(main.helloFlow())
}

@ExperimentalCoroutinesApi
class Main {

    // change this function to return a flow that emits Hello Flow
    fun helloFlow(): String {
        return ("Hello Flow")
    }

}

