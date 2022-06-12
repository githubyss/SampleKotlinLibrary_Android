package com.githubyss.sample_kotlin.test.coroutine

import com.githubyss.mobile.common.kit.util.currentTimeMillis
import com.githubyss.sample_kotlin.util.*
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.io.println


/**
 * TestCoroutineCallback
 * 调测协程回调
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/06 15:50:49
 */

private var threadSleepInMillis: Long = 999999

fun coroutineCallback() {
    printlnPostWithTime("调测协程回调 Start：CurrentThread: ${Thread.currentThread()}")

    // launchCheck()
    launchCheckParallelAsyncStartLazyConcurrent()

    printlnPostWithTime("调测协程回调 End：CurrentThread: ${Thread.currentThread()}")

    Thread.sleep(threadSleepInMillis)
}


/**
 * 检索（串行）。
 *
 * @param
 * @return
 */
private fun launchCheck() {
    printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchCheck CoroutineScope().launch{} 外部")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchCheck CoroutineScope().launch{} 内部")
        println("Check start.")
        val startTime: Long = currentTimeMillis()

        // 直接使用挂起函数，默认串行执行
        val result1: Boolean = checkSuspendCoroutine()
        val result2: Boolean = checkSuspendCoroutineByPolling()
        val result3: Boolean = checkSuspendCoroutineByPolling()
        val resultFinal = result1 && result2 && result3

        val endTime: Long = currentTimeMillis()
        printlnWithTime("resultFinal = $resultFinal", "launchCheck")
        println("总耗时：${endTime - startTime} ms", "launchCheck")

        printlnPrePost("Check end.")
    }
    println("launch end.")
}

/**
 * 检索（async 并行）。
 *
 * @param
 * @return
 */
private fun launchCheckParallelAsyncStartLazyConcurrent() {
    printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchCheckParallelAsyncStartLazyConcurrent CoroutineScope().launch{} 外部")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchCheckParallelAsyncStartLazyConcurrent CoroutineScope().launch{} 内部")
        printlnPrePost("Check start.")
        val startTime: Long = currentTimeMillis()

        val resultFinal = concurrentCheckWithContext()

        val endTime: Long = currentTimeMillis()
        printlnWithTime("resultFinal = $resultFinal", "launchCheckParallelAsyncStartLazyConcurrent")
        println("总耗时：${endTime - startTime} ms", "launchCheckParallelAsyncStartLazyConcurrent")

        printlnPrePost("Check end.")
    }
    println("launch end.")

    Thread.sleep(threadSleepInMillis)
}

private suspend fun concurrentCheckWithContext(): Boolean = withContext(Dispatchers.Default) {
    val result1: Deferred<Boolean> = async(start = CoroutineStart.LAZY) { checkSuspendCoroutine() }
    val result2: Deferred<Boolean> = async(start = CoroutineStart.LAZY) { checkSuspendCoroutineByPolling() }
    val result3: Deferred<Boolean> = async(start = CoroutineStart.LAZY) { checkSuspendCoroutineByPolling() }

    // 协程作用域中执行 cancel()，会使整个协程作用域的协程取消。
    // this.cancel()

    result1.start()
    result2.start()
    result3.start()

    result1.await() && result2.await() && result3.await()
}

/**
 * 使用 suspendCoroutine{} 实现数据检索。
 *
 * @param
 * @return
 */
private suspend fun checkSuspendCoroutine(): Boolean = suspendCoroutine {
    printlnWithTime("CurrentThread check: ${Thread.currentThread()}", "suspendCoroutine")
    val startTime: Long = currentTimeMillis()

    var result: Boolean = false
    for (i in 0 until 2000000001) {
        if (i == 2000000000) {
            result = true
            it.resume(result)
        }
    }

    val endTime: Long = currentTimeMillis()
    printlnWithTime("result = $result", "suspendCoroutine")
    printlnPost("耗时：${endTime - startTime} ms", "suspendCoroutine")
}

/**
 * 使用 suspendCoroutine{} 实现数据检索（通过自定义标志位控制轮询）。
 *
 * @param
 * @return
 */
private suspend fun checkSuspendCoroutineByPolling(): Boolean = suspendCoroutine { continuation ->
    printlnWithTime("CurrentThread check: ${Thread.currentThread()}", "suspendCoroutineByPolling")
    val startTime: Long = currentTimeMillis()

    var isActive: Boolean = true
    var i: Long = 0
    var result: Boolean = false
    while (isActive) {
        if (i == 2000000000L) {
            result = true
            continuation.resume(result)
            isActive = false
        }
        i++
    }

    val endTime: Long = currentTimeMillis()
    printlnWithTime("result = $result", "suspendCoroutineByPolling")
    printlnPost("耗时：${endTime - startTime} ms", "suspendCoroutineByPolling")
}
