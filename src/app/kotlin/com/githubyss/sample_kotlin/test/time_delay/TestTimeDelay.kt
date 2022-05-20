package com.githubyss.sample_kotlin.test.time_delay

import android.os.Handler
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

private var delayInMillis: Long = 3000

fun timeDelay() {
    println("调测延迟")
    println()

    delayThread()
    delayTimerTask()
    // delayHandler()
}

private fun delayThread() {
    object : Thread() {
        override fun run() {
            println("object : Thread() {} >> delay start!")
            sleep(delayInMillis)
            println("object : Thread() {} >> delay finish!")
        }
    }.start()

    Thread {
        run {
            println("Thread { run {}} >> delay start!")
            Thread.sleep(delayInMillis)
            println("Thread { run {}} >> delay finish!")
        }
    }.start()

    Thread {
        println("Thread {} >> delay start!")
        Thread.sleep(delayInMillis)
        println("Thread {} >> delay finish!")
    }.start()

    thread {
        println("thread {} >> delay start!")
        Thread.sleep(delayInMillis)
        println("thread {} >> delay finish!")
    }

    println()
}

private fun delayTimerTask() {
    println("object : TimerTask() {} >> delay start!")
    Timer().schedule(object : TimerTask() {
        override fun run() {
            println("object : TimerTask() {} >> delay finish!")
        }
    }, delayInMillis)

    println("timerTask {} >> delay start!")
    Timer().schedule(timerTask {
        println("timerTask {} >> delay finish!")
    }, delayInMillis)

    println()
}

private fun delayHandler() {
    println("postDelayed {} >> delay start!")
    Handler().postDelayed(Runnable {
        println("postDelayed {} >> delay finish!")
    }, delayInMillis)

    println()
}
