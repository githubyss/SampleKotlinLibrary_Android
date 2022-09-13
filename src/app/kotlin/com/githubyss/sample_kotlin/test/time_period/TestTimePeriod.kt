package com.githubyss.sample_kotlin.test.time_period

import android.os.Handler
import com.githubyss.sample_kotlin.util.printlnWithTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.thread
import kotlin.concurrent.timerTask


/**
 * TestTimePeriod
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/09 15:41:17
 */

private var delayInMillis: Long = 1000
private var periodInMillis: Long = 5000

fun timePeriod() {
    printlnWithTime("轮询}")
    println()

    periodCoroutine()
    periodTimerTask()
    // periodHandler()

    println()
}

private fun periodCoroutine() {
    runBlocking {
        printlnWithTime("withContext(){} >> delay start! \t CurrentThread: ${Thread.currentThread()}")
        withContext(Dispatchers.Default) {
            delay(delayInMillis)
            printlnWithTime("withContext(){} >> delay finish! \t CurrentThread: ${Thread.currentThread()}")
        }

        println()
    }
}

private fun periodTimerTask() {
    val timer = Timer()
    printlnWithTime("object:TimerTask(){} >> schedule start! \t CurrentThread: ${Thread.currentThread()}")
    timer.schedule(object : TimerTask() {
        override fun run() {
            printlnWithTime("object:TimerTask(){} >> schedule finish! \t CurrentThread: ${Thread.currentThread()}")
        }
    }, delayInMillis, periodInMillis)

    printlnWithTime("timerTask{} >> schedule start! \t\t\t\t CurrentThread: ${Thread.currentThread()}")
    timer.schedule(timerTask {
        printlnWithTime("timerTask{} >> schedule finish! \t\t\t CurrentThread: ${Thread.currentThread()}")
    }, delayInMillis, periodInMillis)

    println()
}

private fun periodHandler() {
    printlnWithTime("postDelayed{} >> delay start! \t\t CurrentThread: ${Thread.currentThread()}")
    Handler().postDelayed(Runnable {
        printlnWithTime("postDelayed{} >> delay finish! \t\t CurrentThread: ${Thread.currentThread()}")
    }, delayInMillis)

    println()
}
