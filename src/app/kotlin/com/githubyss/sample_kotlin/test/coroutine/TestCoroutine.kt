package com.githubyss.sample_kotlin.test.coroutine

import com.githubyss.sample_kotlin.util.printlnWithTime
import kotlinx.coroutines.*


/**
 * TestCoroutine
 *
 * - Dispatchers.Default：
 * *默认的调度器，适合处理后台计算，是一个CPU密集型任务调度器。如果创建 Coroutine 的时候没有指定 dispatcher，则一般默认使用这个作为默认值。Default dispatcher 使用一个共享的后台线程池来运行里面的任务。注意它和IO共享线程池，只不过限制了最大并发数不同。*
 *
 * - Dispatchers.IO：
 * *顾名思义这是用来执行阻塞 IO 操作的，是和Default共用一个共享的线程池来执行里面的任务。根据同时运行的任务数量，在需要的时候会创建额外的线程，当任务执行完毕后会释放不需要的线程。*
 *
 * - Dispatchers.Unconfined：
 * *由于Dispatchers.Unconfined未定义线程池，所以执行的时候默认在启动线程。遇到第一个挂起点，之后由调用resume的线程决定恢复协程的线程。*
 *
 * - Dispatchers.Main：
 * *指定执行的线程是主线程，在Android上就是UI线程。*
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/18 17:17:09
 */

private var delayInMillis: Long = 5000
private var threadSleepInMillis: Long = 999999

fun coroutine() {
    printlnWithTime("调测协程：CurrentThread: ${Thread.currentThread()}")
    println()

    launchAllInOne()
    // launchInMulti()
    // runBlocking()
}

private fun launchAllInOne() {
    printlnWithTime("CoroutineScope().launch{} 外部：CurrentThread: ${Thread.currentThread()}")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部：CurrentThread: ${Thread.currentThread()}")

        coroutineLaunch()
        coroutineLaunch()
        coroutineLaunch()
        coroutineCoroutineScope()
        coroutineCoroutineScope()
        coroutineCoroutineScope()
        coroutineWithContext()
        coroutineWithContext()
        coroutineWithContext()
        coroutineDelay()
        coroutineDelay()
        coroutineDelay()
    }

    Thread.sleep(threadSleepInMillis)
}

private fun launchInMulti() {
    printlnWithTime("CoroutineScope().launch{} 外部：CurrentThread: ${Thread.currentThread()}")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部 for launch：CurrentThread: ${Thread.currentThread()}")

        coroutineLaunch()
        coroutineLaunch()
        coroutineLaunch()
    }

    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部 for coroutineScope：CurrentThread: ${Thread.currentThread()}")

        coroutineCoroutineScope()
        coroutineCoroutineScope()
        coroutineCoroutineScope()
    }

    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部 for withContext：CurrentThread: ${Thread.currentThread()}")

        coroutineWithContext()
        coroutineWithContext()
        coroutineWithContext()
    }

    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部 for delay：CurrentThread: ${Thread.currentThread()}")

        coroutineDelay()
        coroutineDelay()
        coroutineDelay()
    }

    Thread.sleep(threadSleepInMillis)
}

private fun runBlocking() {
    printlnWithTime("runBlocking(){} 外部：CurrentThread: ${Thread.currentThread()}")
    runBlocking {
        printlnWithTime("runBlocking(){} 内部：CurrentThread: ${Thread.currentThread()}")

        coroutineLaunch()
        coroutineLaunch()
        coroutineCoroutineScope()
        coroutineCoroutineScope()
        coroutineWithContext()
        coroutineWithContext()
        coroutineDelay()
        coroutineDelay()
    }
}


/**
 * 由 CoroutineScope(Dispatchers.Default).launch{} 实现的挂起，在同一个协程作用域中可以并行。
 *
 * @param
 * @return
 */
private suspend fun coroutineLaunch() {
    println()
    printlnWithTime("CoroutineScope(Dispatchers.Default).launch{} 实现的挂起，可以多个并行。CurrentThread: ${Thread.currentThread()}")
    println("suspend launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("launch{} >> start! \t\t\t\t\t\t CurrentThread: ${Thread.currentThread()}")
        delay(delayInMillis)
        printlnWithTime("launch{} >> finish! \t\t\t\t\t CurrentThread: ${Thread.currentThread()}")
    }
    println("suspend launch end.")
}

/**
 * 由 coroutineScope{} 实现的挂起，在同一个协程作用域中只能挨个运行，在多个协程中可以并行。
 *
 * @param
 * @return
 */
private suspend fun coroutineCoroutineScope() {
    println()
    printlnWithTime("coroutineScope{} 实现的挂起，只能一个一个运行。CurrentThread: ${Thread.currentThread()}")
    println("suspend coroutineScope start.")
    coroutineScope {
        printlnWithTime("coroutineScope{} >> start! \t\t\t\t CurrentThread: ${Thread.currentThread()}")
        delay(delayInMillis)
        printlnWithTime("coroutineScope{} >> finish! \t\t\t CurrentThread: ${Thread.currentThread()}")
    }
    println("suspend coroutineScope end.")
}

/**
 * 由 withContext(){} 实现的挂起，在同一个协程作用域中只能挨个运行，在多个协程中可以并行。
 *
 * @param
 * @return
 */
private suspend fun coroutineWithContext() {
    println()
    printlnWithTime("withContext(){} 实现的挂起，只能一个一个运行。CurrentThread: ${Thread.currentThread()}")
    println("suspend withContext start.")
    withContext(Dispatchers.IO) {
        printlnWithTime("withContext(){} >> start! \t\t\t\t CurrentThread: ${Thread.currentThread()}")
        delay(delayInMillis)
        printlnWithTime("withContext(){} >> finish! \t\t\t\t CurrentThread: ${Thread.currentThread()}")
    }
    println("suspend withContext end.")
}

/**
 * 由 delay() 实现的挂起，在同一个协程作用域中只能挨个运行，在多个协程中可以并行。
 *
 * @param
 * @return
 */
private suspend fun coroutineDelay() {
    println()
    printlnWithTime("delay() 实现的挂起，只能一个一个运行。CurrentThread: ${Thread.currentThread()}")
    println("suspend delay start.")
    printlnWithTime("delay() >> start! \t\t\t\t\t\t CurrentThread: ${Thread.currentThread()}")
    delay(delayInMillis)
    printlnWithTime("delay() >> finish! \t\t\t\t\t\t CurrentThread: ${Thread.currentThread()}")
    println("suspend delay end.")
}
