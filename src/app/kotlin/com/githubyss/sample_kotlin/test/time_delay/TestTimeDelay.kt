package com.githubyss.sample_kotlin.test.time_delay

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
 * TestTimeDelay
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/05/20 14:13:23
 */

private var delayInMillis: Long = 2000

fun timeDelay() {
    println("调测延迟")
    printlnWithTime("CurrentThread: ${Thread.currentThread()}")
    println()

    delayCoroutine()
    // delayThread()
    // delayTimerTask()
    // delayHandler()

    println()
}

private fun delayCoroutine() {
    runBlocking {
        printlnWithTime("withContext(){} >> delay start! \t CurrentThread: ${Thread.currentThread()}")
        withContext(Dispatchers.Default) {
            delay(delayInMillis)
            printlnWithTime("withContext(){} >> delay finish! \t CurrentThread: ${Thread.currentThread()}")
        }

        println()
    }
}

private fun delayThread() {
    printlnWithTime("object:Thread(){} >> sleep start! \t\t\t CurrentThread: ${Thread.currentThread()}")
    object : Thread() {
        override fun run() {
            Thread.sleep(delayInMillis)
            printlnWithTime("object:Thread(){} >> sleep finish! \t\t\t CurrentThread: ${Thread.currentThread()}")
        }
    }.start()

    printlnWithTime("Thread{run{}} >> sleep start! \t\t\t\t CurrentThread: ${Thread.currentThread()}")
    Thread {
        run {
            Thread.sleep(delayInMillis)
            printlnWithTime("Thread{run{}} >> sleep finish! \t\t\t\t CurrentThread: ${Thread.currentThread()}")
        }
    }.start()

    printlnWithTime("Thread{} >> sleep start! \t\t\t\t\t CurrentThread: ${Thread.currentThread()}")
    Thread {
        Thread.sleep(delayInMillis)
        printlnWithTime("Thread{} >> sleep finish! \t\t\t\t\t CurrentThread: ${Thread.currentThread()}")
    }.start()

    printlnWithTime("thread{} >> sleep start! \t\t\t\t\t CurrentThread: ${Thread.currentThread()}")
    thread {
        Thread.sleep(delayInMillis)
        printlnWithTime("thread{} >> sleep finish! \t\t\t\t\t CurrentThread: ${Thread.currentThread()}")
    }

    println()
}

private val timerTask
    get() = timerTask {
        printlnWithTime("Run task! \t\t\t CurrentThread: ${Thread.currentThread()}")
        System.gc()
    }

private fun delayTimerTask() {
    val timer = Timer()

    printlnWithTime("Schedule start 1! \t CurrentThread: ${Thread.currentThread()}")
    timer.schedule(timerTask, delayInMillis)

    printlnWithTime("Schedule start 2! \t CurrentThread: ${Thread.currentThread()}")
    timer.schedule(timerTask, delayInMillis)

    println()
}

private fun delayHandler() {
    printlnWithTime("postDelayed{} >> delay start! \t\t CurrentThread: ${Thread.currentThread()}")
    Handler().postDelayed(Runnable {
        printlnWithTime("postDelayed{} >> delay finish! \t\t CurrentThread: ${Thread.currentThread()}")
    }, delayInMillis)

    println()
}
