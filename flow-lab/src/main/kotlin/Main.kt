import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

@ExperimentalCoroutinesApi
fun main() = runBlocking {

    val main = Main()
    main.demo1()
    println("demo1 done")
    println("Starting combine demo")
    val time = measureTimeMillis {
        main.combineDemo()
    }
    println("Finished combine demo in $time")

}

@ExperimentalCoroutinesApi
class Main {
    suspend fun demo1() {
        println("flow starting")

        val theFlow = makeFlow()
        theFlow.collect { value ->
            println("got $value")
        }
        println("==================")
        val repeatFlow = makeFlow().take(1)
        println("collect 1")
        repeatFlow.collect { value ->
            println("got $value")
        }
        println("collect 2")
        repeatFlow.collect { value ->
            println("got $value")
        }
        println("flow finished")

        println("================StateFlow============")
        makeStateFlow().take(1)
        println("collect 1")
        repeatFlow.collect { value ->
            println("got $value")
        }
        println("collect 2")
        repeatFlow.collect { value ->
            println("got $value")
        }


    }

    suspend fun combineDemo() {

        combine().collect {
            println(it)
        }

    }

}

private fun combine(): Flow<String> {
    val flow1 = makeSlowerFlow()
    val flow2 = makeSlowestFlow()
    return flow1.zip(flow2) { a, b ->
        "$b: $a"
    }
}

private fun makeFlow() = flow {
    println("sending first value")
    emit(1)
    println("first value collected, sending another value")
    emit(2)
    println("second value collected, sending a third value")
    emit(3)
    println("done")
}

private fun makeSlowerFlow() = flow {
    for (i in 1..3) {
        delay(2000)
        emit(i)
    }
}


private fun makeSlowestFlow() = flow {
    for (i in 1..3) {
        delay(1500)
        emit("value i")
    }
}

@ExperimentalCoroutinesApi
fun makeStateFlow() = MutableStateFlow(0).flatMapLatest {
    makeFlow()
}
