package com.githubyss.sample_kotlin.test.coroutine

import com.githubyss.mobile.common.kit.util.currentTimeMillis
import com.githubyss.sample_kotlin.util.printlnWithTime
import kotlinx.coroutines.*


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
    printlnWithTime("调测协程回调：CurrentThread: ${Thread.currentThread()}")
    println()

    launchCheck()
}


/**
 * 检索。
 *
 * @param
 * @return
 */
private fun launchCheck() {
    printlnWithTime("CoroutineScope().launch{} 外部：CurrentThread: ${Thread.currentThread()}")
    println("launch start.")
    CoroutineScope(Dispatchers.Default).launch {
        printlnWithTime("CoroutineScope().launch{} 内部：CurrentThread: ${Thread.currentThread()}")
        println("Check start.")
        val startTime: Long = currentTimeMillis()
        println()

        // 直接使用挂起函数，默认串行执行
        val result1: Double = checkCoroutineScope()
        printlnWithTime("result1 = $result1")
        println()

        val result2: Double = checkWithContextDefault()
        printlnWithTime("result2 = $result2")
        println()

        val result3: Double = checkWithContextIO()
        printlnWithTime("result3 = $result3")
        println()

        val result = result1 + result2 + result3
        printlnWithTime("result = $result")
        println()

        println("Check end.")
        val endTime: Long = currentTimeMillis()
        println("总耗时：${endTime - startTime} ms")
        println()
    }
    println("launch end.")

    Thread.sleep(threadSleepInMillis)
}

/**
 * 使用 coroutineScope{} 实现求和计算。
 *
 * @param
 * @return
 */
private suspend fun checkCoroutineScope(): Double = coroutineScope {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}")
    var sum: Double = 0.0
    for (i in 0 until 2000000000) {
        sum += i
    }
    sum
}

/**
 * 使用 withContext(CoroutineContext){} 实现求和计算。
 *
 * @param
 * @return
 */
private suspend fun checkWithContextDefault(): Double = withContext(Dispatchers.Default) {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}")
    var sum: Double = 0.0
    for (i in 0 until 2000000000) {
        sum += i
    }
    sum
}

/**
 * 使用 withContext(CoroutineContext){} 实现求和计算。
 *
 * @param
 * @return
 */
private suspend fun checkWithContextIO(): Double = withContext(Dispatchers.IO) {
    printlnWithTime("CurrentThread sum: ${Thread.currentThread()}")
    var sum: Double = 0.0
    for (i in 0 until 2000000000) {
        sum += i
    }
    sum
}
