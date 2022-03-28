import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

fun main() = runBlocking {

    // 1. In Java you use 'threads' to execute several tasks in parallel
    // using the cores of your processor which usually are 2 or more nowadays
    // to properly handle threads and tasks in Java you might need to
    // start, pause, join, etc..

    /*
    thread(start = true, name = "another thread"){
      (0..10).forEach{
        println("Message #$it from ${Thread.currentThread().name} ")
      }
    }

    (0..10).forEach {
      println("Message #$it from ${Thread.currentThread().name} ")
    }

     */

    // 2. Coroutines
    // In Kotlin you receive coroutines out of the box, this library comes
    // with some utilities that helps you to handle asynchronous programming

    launch(Dispatchers.Default) {
        (0..10).forEach {
            println("Message #$it from ${Thread.currentThread().name} ")
        }
    }

    (0..10).forEach {
        println("Message #$it from ${Thread.currentThread().name} ")
    }

    // 3. Configuring coroutines
    // Any coroutine gets executed inside a 'CoroutineScope' that contains an instance of 'CoroutineContext'
    // Which is represented by a collection of configurations

    // 4. Job
    // a Job is basically a process in the background, it can have a state (active, cancelled, completed, etc...)
    // and have children Jobs

    // 5. Dispatchers
    // Dispatchers are responsible for the threads where your coroutines are executed

    // Dispatcher.Default : for resource demanding operations,
    // number of threads equal to the numbers of core in your processor, and at least 2

    // Dispatcher.IO: for input/output operations (network requests, saving/reading data)
    // 64 threads in pool

    // Dispatcher.Unconfined: no limited to any thread, do not use if you can use any other thread

    // 6. CoroutineScope
    // is an interface that provides a CoroutineContext, just that, that's it...
    //

    // 7. Obtaining a scope
    // GlobalScope: Accessible from anywhere, used to bind a process to the whole application lifetime
    // MainScope: handy to work with UI components
    // CoroutineScope(context: CoroutineContext): wraps a specific context

    // 8. Coroutines builders
    // launch(), runblocking(), etc...

    // 9. runBlocking()
    /*
    public fun <T> runBlocking(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> T
    ): T
    */
    // block the current thread until the coroutine parameter ('block') is finished
    // is supposed to be used for testing and in the main()

    // 10. launch()
    /*
    public fun CoroutineScope.launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job
    */
    // similar to runblocking creates a new coroutine but doesn't block the current thread, instead returns a Job
    // which lets you control the coroutine execution
    // you can use 'join()' in the job to suspend the current coroutine/suspend function until the job is done

    // CoroutineStart:
    //  DEFAULT = immediate start
    //  LAZY = won't be launched until calling 'start()' on the Job
    //  ATOMIC = runs immediately and cannot be cancelled
    //  UNDISPATCHED = launched immediately until the first suspension point in the current thread


    // 12. async()
    /*
    public fun <T> CoroutineScope.async(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T>
     */
    // useful for when you want to get a result form the coroutine
    // for example getting data from a server
    // similar to 'launch()' but returns a 'Deferred<T>'
    // in order to wait for the result use 'wait()'

    // 13. withContext()
    /*
    public suspend fun <T> withContext(
        context: CoroutineContext,
        block: suspend CoroutineScope.() -> T
    ): T
     */
    // gets the result of the execution as async()
    // but is intended for more straightforward cases
    // you just get the result, not wrapped in a Deferred


    // 14. Example: A high-rise building
    // BuildingYard.startProject("Smart house", 20)


    // 15. Error Handling
    // you can use 'try/catch' blocks with coroutines as in synchronous programming

    // 16. Using CoroutineExceptionHandler
    // You can set a global exception hanbdler for all your coroutines

    val scope = CoroutineScope(Dispatchers.Default)
    val handler = CoroutineExceptionHandler { context: CoroutineContext, exception: Throwable ->
        println(exception.message)
    }


    val buildings = BuildingYard.startProject(
        listOf(
            BuildingYard.Project("Building 1", 20),
            BuildingYard.Project("Building 2", 5),
            BuildingYard.Project("Building 3", 10)
        )
    )

    println("$buildings")
}
