package com.githubyss.sample_kotlin.test.coroutine

import com.githubyss.mobile.common.kit.util.currentTimeMillis
import com.githubyss.sample_kotlin.util.*
import kotlinx.coroutines.*


/**
 * TestCoroutineAsync
 * 调测协程（并发）
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/06 14:13:30
 */

private var threadSleepInMillis: Long = 999999

fun coroutineAsync() {
    printlnPostWithTime("调测协程（并发） Start：CurrentThread: ${Thread.currentThread()}")

    // launchSumSerial()
    // launchSumSerialAsync()
    // launchSumParallelAsync()
    // launchSumParallelAsyncStartLazy()
    // launchSumParallelAsyncStartLazyConcurrent()
    launchTimeConsumingParallelAsyncStartLazy()

    printlnPostWithTime("调测协程（并发） End：CurrentThread: ${Thread.currentThread()}")

    Thread.sleep(threadSleepInMillis)
}


/** ******************** 串行、并行 ******************** */

/** ********** 求和 ********** */

/**
 * 启动求和（串行）。
 *
 * @param
 * @return
 */
private fun launchSumSerial() {
    printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumSerial CoroutineScope().launch{} 外部")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumSerial CoroutineScope().launch{} 内部")
        printlnPrePost("Sum start.")
        val startTime: Long = currentTimeMillis()

        // 直接使用挂起函数，默认串行执行
        val sum1: Double = sumCoroutineScope()
        val sum2: Double = sumWithContextDefault()
        val sum3: Double = sumWithContextDefaultByPolling()
        val sumTotal = sum1 + sum2 + sum3

        val endTime: Long = currentTimeMillis()
        printlnWithTime("sumTotal = $sumTotal", "launchSumSerial")
        println("总耗时：${endTime - startTime} ms", "launchSumSerial")

        printlnPrePost("Sum end.")
    }
    println("launch end.")
}

/**
 * 启动求和（async 串行）。
 *
 * @param
 * @return
 */
private fun launchSumSerialAsync() {
    printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumSerialAsync CoroutineScope().launch{} 外部")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumSerialAsync CoroutineScope().launch{} 内部")
        printlnPrePost("Sum start.")
        val startTime: Long = currentTimeMillis()

        // 通过 async{} 使用挂起函数，通过 Deferred.await() 方法获取挂起函数得到的数据
        // 这种写法还是串行执行。
        val sum1: Double = async { sumCoroutineScope() }.await()
        val sum2: Double = async { sumWithContextDefault() }.await()
        val sum3: Double = async { sumWithContextDefaultByPolling() }.await()
        val sumTotal = sum1 + sum2 + sum3

        val endTime: Long = currentTimeMillis()
        printlnWithTime("sumTotal = $sumTotal", "launchSumSerialAsync")
        println("总耗时：${endTime - startTime} ms", "launchSumSerialAsync")

        printlnPrePost("Sum end.")
    }
    println("launch end.")
}

/**
 * 启动求和（async 并行）。
 *
 * @param
 * @return
 */
private fun launchSumParallelAsync() {
    printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumParallelAsync CoroutineScope().launch{} 外部")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumParallelAsync CoroutineScope().launch{} 内部")
        printlnPrePost("Sum start.")
        val startTime: Long = currentTimeMillis()

        // 通过 async{} 使用挂起函数，实现并行执行
        val sum1: Deferred<Double> = async { sumCoroutineScope() }
        val sum2: Deferred<Double> = async { sumWithContextDefault() }
        val sum3: Deferred<Double> = async { sumWithContextDefaultByPolling() }

        // 通过 Deferred.await() 方法获取挂起函数得到的数据
        val sumTotal = sum1.await() + sum2.await() + sum3.await()

        val endTime: Long = currentTimeMillis()
        printlnWithTime("sumTotal = $sumTotal", "launchSumParallelAsync")
        println("总耗时：${endTime - startTime} ms", "launchSumParallelAsync")

        printlnPrePost("Sum end.")
    }
    println("launch end.")
}

/**
 * 启动求和（async 并行惰性启动（延迟启动并发））。
 *
 * @param
 * @return
 */
private fun launchSumParallelAsyncStartLazy() {
    printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumParallelAsyncStartLazy CoroutineScope().launch{} 外部")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumParallelAsyncStartLazy CoroutineScope().launch{} 内部")
        printlnPrePost("Sum start.")
        val startTime: Long = currentTimeMillis()

        // 通过 start = CoroutineStart.LAZY 设置 async{} 为惰性启动
        // 在这种模式下，只有当我们调用 await 获取协程数据的时候，才会启动 async 的协程计算
        val sum1: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumCoroutineScope() }
        val sum2: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumWithContextDefault() }
        val sum3: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumWithContextDefaultByPolling() }

        // 如果还想主动去启动并发的话，那么就需要通过调用 start 函数来启动。
        // 启动并行协程，否则并行不会生效。哪个没有 start，哪个就默认串行执行。
        sum1.start()
        sum2.start()
        sum3.start()

        val sumTotal = sum1.await() + sum2.await() + sum3.await()

        val endTime: Long = currentTimeMillis()
        printlnWithTime("sumTotal = $sumTotal", "launchSumParallelAsyncStartLazy")
        println("总耗时：${endTime - startTime} ms", "launchSumParallelAsyncStartLazy")

        printlnPrePost("Sum end.")
    }
    println("launch end.")
}

/**
 * 启动求和（async 并行、惰性启动、结构化并发）。
 *
 * @param
 * @return
 */
private fun launchSumParallelAsyncStartLazyConcurrent() {
    printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumParallelAsyncStartLazyConcurrent CoroutineScope().launch{} 外部")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchSumParallelAsyncStartLazyConcurrent CoroutineScope().launch{} 内部")
        printlnPrePost("Sum start.")
        val startTime: Long = currentTimeMillis()

        // val sumTotal = concurrentSumCoroutineScope()
        val sumTotal = concurrentSumWithContext()

        val endTime: Long = currentTimeMillis()
        printlnWithTime("sumTotal = $sumTotal", "launchSumParallelAsyncStartLazyConcurrent")
        println("总耗时：${endTime - startTime} ms", "launchSumParallelAsyncStartLazyConcurrent")

        printlnPrePost("Sum end.")
    }
    println("launch end.")
}

/** ********** 耗时 ********** */

/**
 * 启动耗时操作（async 并行惰性启动（延迟启动并发））。
 *
 * @param
 * @return
 */
private fun launchTimeConsumingParallelAsyncStartLazy() {
    printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchTimeConsumingParallelAsyncStartLazy CoroutineScope().launch{} 外部")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CurrentThread: ${Thread.currentThread()}", "launchTimeConsumingParallelAsyncStartLazy CoroutineScope().launch{} 内部")
        printlnPrePost("TimeConsuming start.")
        val startTime: Long = currentTimeMillis()

        val actTimeConsuming1000: Deferred<Unit> = async(start = CoroutineStart.LAZY) { actTimeConsuming1000() }
        val actTimeConsuming5000: Deferred<Unit> = async(start = CoroutineStart.LAZY) { actTimeConsuming5000() }

        actTimeConsuming1000.start()
        actTimeConsuming5000.start()

        val endTime: Long = currentTimeMillis()
        println("总耗时：${endTime - startTime} ms", "launchTimeConsumingParallelAsyncStartLazy")

        printlnPrePost("TimeConsuming end.")
    }
    println("launch end.")
}


/** ******************** 结构化并发 ******************** */

/** ********** 求和 ********** */

/**
 * 使用 coroutineScope{} 实现结构化并发。
 * 这种情况下如果一个子协程（并发分支）出现了失败。那么其他的async和等待的父协程都会被取消。
 *
 * @param
 * @return
 */
private suspend fun concurrentSumCoroutineScope(): Double = coroutineScope {
    val sum1: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumCoroutineScope() }
    val sum2: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumWithContextDefault() }
    val sum3: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumWithContextDefaultByPolling() }
    sum1.start()
    sum2.start()
    sum3.start()
    sum1.await() + sum2.await() + sum3.await()
}

/**
 * 使用 withContext(CoroutineContext){} 实现结构化并发。
 *
 * @param
 * @return
 */
private suspend fun concurrentSumWithContext(): Double = withContext(Dispatchers.Default) {
    val sum1Deferred: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumCoroutineScope() }
    val sum2Deferred: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumWithContextDefault() }
    val sum3Deferred: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumWithContextDefaultByPolling() }
    sum1Deferred.start()
    sum2Deferred.start()
    sum3Deferred.start()

    val sum1 = sum1Deferred.await()
    val sum2 = sum2Deferred.await()
    val sum3 = sum3Deferred.await()

    sum1 + sum2 + sum3
}


/** ******************** 耗时操作 ******************** */

/** ********** 求和 ********** */

/**
 * 使用 coroutineScope{} 实现求和计算。
 *
 * @param
 * @return
 */
private suspend fun sumCoroutineScope(): Double = coroutineScope {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}", "coroutineScope")
    val startTime: Long = currentTimeMillis()

    var sum: Double = 0.0
    for (i in 1 until 2000000001) {
        sum += i
    }

    val endTime: Long = currentTimeMillis()
    printlnWithTime("sum = $sum", "coroutineScope")
    printlnPost("耗时：${endTime - startTime} ms", "coroutineScope")
    sum
}

/**
 * 使用 withContext(CoroutineContext){} 实现求和计算。
 *
 * @param
 * @return
 */
private suspend fun sumWithContextDefault(): Double = withContext(Dispatchers.Default) {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}", "withContextDefault")
    val startTime: Long = currentTimeMillis()

    var sum: Double = 0.0
    for (i in 1 until 2000000001) {
        sum += i
    }

    val endTime: Long = currentTimeMillis()
    printlnWithTime("sum = $sum", "withContextDefault")
    printlnPost("耗时：${endTime - startTime} ms", "withContextDefault")
    sum
}

/**
 * 使用 withContext(CoroutineContext){} 实现求和计算（通过自定义标志位控制轮询）。
 * 通过自定义标志位控制循环，达到条件就退出循环。
 *
 * @param
 * @return
 */
private suspend fun sumWithContextDefaultByPolling(): Double = withContext(Dispatchers.Default) {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}", "withContextDefaultByPolling")
    val startTime: Long = currentTimeMillis()

    var isActive: Boolean = true
    var i: Long = 1
    var sum: Double = 0.0
    while (isActive) {
        if (i == 2000000000L) {
            isActive = false
        }
        sum += i
        i++
    }

    val endTime: Long = currentTimeMillis()
    printlnWithTime("sum = $sum", "withContextDefaultByPolling")
    printlnPost("耗时：${endTime - startTime} ms", "withContextDefaultByPolling")
    sum
}

/**
 * 使用 withContext(CoroutineContext){} 实现求和计算（通过 CoroutineScope.isActive 标志位控制轮询）。
 * 通过 CoroutineScope.isActive 标志位控制循环，达到条件就 CoroutineScope.cancel() 退出循环。
 * 实际上这样做会取消这个挂起函数所在的协程作用域，导致作用域中该挂起函数后面的代码无法执行。
 *
 * @param
 * @return
 */
private suspend fun sumWithContextDefaultByPollingByCancel(): Double = withContext(Dispatchers.Default) {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}", "withContextDefaultByPollingByCancel")
    val startTime: Long = currentTimeMillis()

    var i: Long = 1
    var sum: Double = 0.0
    while (isActive) {
        if (i == 2000000000L) {
            // 协程作用域中执行 cancel()，会使整个协程作用域的协程取消。
            this.cancel()
        }
        sum += i
        i++
    }

    val endTime: Long = currentTimeMillis()
    printlnWithTime("sum = $sum", "withContextDefaultByPollingByCancel")
    printlnPost("耗时：${endTime - startTime} ms", "withContextDefaultByPollingByCancel")
    sum
}

/** ********** 耗时 ********** */

private suspend fun actTimeConsuming1000() {
    val result: Long = timeConsuming1000()
    printlnWithTime("result = $result", "actTimeConsuming1000")
    actTimeConsuming1000()
}

private suspend fun actTimeConsuming1000ByWithContext() {
    withContext(Dispatchers.Default) {
        val result: Long = timeConsuming1000()
        printlnWithTime("result = $result", "actTimeConsuming1000ByWithContext")
        actTimeConsuming1000ByWithContext()
    }
}

private suspend fun timeConsuming1000(): Long = withContext(Dispatchers.Default) {
    val intervalMillis: Long = 1000
    delay(intervalMillis)
    intervalMillis
}

private suspend fun actTimeConsuming5000() {
    val result: Long = timeConsuming5000()
    printlnWithTime("result = $result", "actTimeConsuming5000")
    actTimeConsuming5000()
}

private suspend fun actTimeConsuming5000ByWithContext() {
    withContext(Dispatchers.Default) {
        val result: Long = timeConsuming5000()
        printlnWithTime("result = $result", "actTimeConsuming5000ByWithContext")
        actTimeConsuming5000ByWithContext()
    }
}

private suspend fun timeConsuming5000(): Long = withContext(Dispatchers.Default) {
    val intervalMillis: Long = 5000
    delay(intervalMillis)
    intervalMillis
}
