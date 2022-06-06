package com.githubyss.sample_kotlin.test.coroutine

import com.githubyss.mobile.common.kit.util.currentTimeMillis
import com.githubyss.sample_kotlin.util.printlnWithTime
import kotlinx.coroutines.*


/**
 * TestCoroutineAsync
 * 调测并行协程
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/06 14:13:30
 */

private var threadSleepInMillis: Long = 999999

fun coroutineAsync() {
    printlnWithTime("调测并行协程：CurrentThread: ${Thread.currentThread()}")
    println()

    // launchSumSerial()
    // launchSumParallel()
    // launchSumParallelStartLazy()
    launchSumParallelStartLazyConcurrent()
}


/**
 * 求和（串行）
 *
 * @param
 * @return
 */
private fun launchSumSerial() {
    printlnWithTime("CoroutineScope().launch{} 外部：CurrentThread: ${Thread.currentThread()}")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部：CurrentThread: ${Thread.currentThread()}")
        println("Sum start.")
        val startTime: Long = currentTimeMillis()
        println()

        // 直接使用挂起函数，默认串行执行
        val sum1: Double = sumCoroutineScope()
        printlnWithTime("sum1 = $sum1")
        println()

        val sum2: Double = sumWithContextDefault()
        printlnWithTime("sum2 = $sum2")
        println()

        val sum3: Double = sumWithContextIO()
        printlnWithTime("sum3 = $sum3")
        println()

        val sum = sum1 + sum2 + sum3
        printlnWithTime("sum = $sum")
        println()

        println("Sum end.")
        val endTime: Long = currentTimeMillis()
        println("总耗时：${endTime - startTime} ms")
        println()
    }
    println("launch end.")

    Thread.sleep(threadSleepInMillis)
}

/**
 * 求和（并行）
 *
 * @param
 * @return
 */
private fun launchSumParallel() {
    printlnWithTime("CoroutineScope().launch{} 外部：CurrentThread: ${Thread.currentThread()}")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部：CurrentThread: ${Thread.currentThread()}")
        println("Sum start.")
        val startTime: Long = currentTimeMillis()
        println()

        // 通过 async{} 使用挂起函数，实现并行执行
        val sum1: Deferred<Double> = async { sumCoroutineScope() }
        printlnWithTime("sum1 = $sum1")
        println()

        val sum2: Deferred<Double> = async { sumWithContextDefault() }
        printlnWithTime("sum2 = $sum2")
        println()

        val sum3: Deferred<Double> = async { sumWithContextIO() }
        printlnWithTime("sum3 = $sum3")
        println()

        // 通过 Deferred.await() 方法获取挂起函数得到的数据
        val sum = sum1.await() + sum2.await() + sum3.await()
        printlnWithTime("sum = $sum")

        println("Sum end.")
        val endTime: Long = currentTimeMillis()
        println("总耗时：${endTime - startTime} ms")
        println()
    }
    println("launch end.")

    Thread.sleep(threadSleepInMillis)
}

/**
 * 求和（并行惰性启动（延迟启动并发））
 *
 * @param
 * @return
 */
private fun launchSumParallelStartLazy() {
    printlnWithTime("CoroutineScope().launch{} 外部：CurrentThread: ${Thread.currentThread()}")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部：CurrentThread: ${Thread.currentThread()}")
        println("Sum start.")
        val startTime: Long = currentTimeMillis()
        println()

        // 通过 start = CoroutineStart.LAZY 设置 async{} 为惰性启动
        // 在这种模式下，只有当我们调用 await 获取协程数据的时候，才会启动 async 的协程计算
        val sum1: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumCoroutineScope() }
        printlnWithTime("sum1 = $sum1")
        println()

        val sum2: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumWithContextDefault() }
        printlnWithTime("sum2 = $sum2")
        println()

        val sum3: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumWithContextIO() }
        printlnWithTime("sum3 = $sum3")
        println()

        // 如果还想主动去启动并发的话，那么就需要通过调用 start 函数来启动。
        // 启动并行协程，否则并行不会生效。哪个没有 start，哪个就默认串行执行。
        sum1.start()
        sum2.start()
        sum3.start()

        val sum = sum1.await() + sum2.await() + sum3.await()
        printlnWithTime("sum = $sum")
        println()

        println("Sum end.")
        val endTime: Long = currentTimeMillis()
        println("总耗时：${endTime - startTime} ms")
        println()
    }
    println("launch end.")

    Thread.sleep(threadSleepInMillis)
}

private fun launchSumParallelStartLazyConcurrent() {
    printlnWithTime("CoroutineScope().launch{} 外部：CurrentThread: ${Thread.currentThread()}")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部：CurrentThread: ${Thread.currentThread()}")
        println("Sum start.")
        val startTime: Long = currentTimeMillis()
        println()

        printlnWithTime("sum = ${concurrentSum()}")
        println()

        println("Sum end.")
        val endTime: Long = currentTimeMillis()
        println("总耗时：${endTime - startTime} ms")
        println()
    }
    println("launch end.")

    Thread.sleep(threadSleepInMillis)
}

/**
 * 使用 coroutineScope 实现结构化并发。
 * 这种情况下如果一个子协程（并发分支）出现了失败。那么其他的async和等待的父协程都会被取消
 *
 * @param
 * @return
 */
private suspend fun concurrentSum(): Double = coroutineScope {
    val sum1: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumCoroutineScope() }
    val sum2: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumCoroutineScope() }
    val sum3: Deferred<Double> = async(start = CoroutineStart.LAZY) { sumCoroutineScope() }
    sum1.start()
    sum2.start()
    sum3.start()
    sum1.await() + sum2.await() + sum3.await()
}

private suspend fun sumCoroutineScope(): Double = coroutineScope {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}")
    var sum: Double = 0.0
    for (i in 0 until 2000000000) {
        sum += i
    }
    sum
}

private suspend fun sumWithContextDefault(): Double = withContext(Dispatchers.Default) {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}")
    var sum: Double = 0.0
    for (i in 0 until 2000000000) {
        sum += i
    }
    sum
}

private suspend fun sumWithContextIO(): Double = withContext(Dispatchers.IO) {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}")
    var sum: Double = 0.0
    for (i in 0 until 2000000000) {
        sum += i
    }
    sum
}
