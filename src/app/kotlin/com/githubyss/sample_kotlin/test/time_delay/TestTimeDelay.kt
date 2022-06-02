package com.githubyss.sample_kotlin.test.time_delay

import android.os.Handler
import com.githubyss.mobile.common.kit.util.currentDatetimeYmdHmsMillisFullDividedByEnDash
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    delayCoroutine()
    delayThread()
    delayTimerTask()
    // delayHandler()
}

private fun delayCoroutine() {
    CoroutineScope(Dispatchers.Default).launch {
        println("CoroutineScope().launch {} >> delay start! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
        delay(delayInMillis)
        println("CoroutineScope().launch {} >> delay finish! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
    }
}

private fun delayThread() {
    object : Thread() {
        override fun run() {
            println("object : Thread() {} >> sleep start! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
            sleep(delayInMillis)
            println("object : Thread() {} >> sleep finish! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
        }
    }.start()

    Thread {
        run {
            println("Thread { run {}} >> sleep start! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
            Thread.sleep(delayInMillis)
            println("Thread { run {}} >> sleep finish! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
        }
    }.start()

    Thread {
        println("Thread {} >> sleep start! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
        Thread.sleep(delayInMillis)
        println("Thread {} >> sleep finish! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
    }.start()

    thread {
        println("thread {} >> sleep start! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
        Thread.sleep(delayInMillis)
        println("thread {} >> sleep finish! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
    }

    println()
}

private fun delayTimerTask() {
    println("object : TimerTask() {} >> schedule start! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
    Timer().schedule(object : TimerTask() {
        override fun run() {
            println("object : TimerTask() {} >> schedule finish! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
        }
    }, delayInMillis)

    println("timerTask {} >> schedule start! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
    Timer().schedule(timerTask {
        println("timerTask {} >> schedule finish! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
    }, delayInMillis)

    println()
}

private fun delayHandler() {
    println("postDelayed {} >> delay start! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
    Handler().postDelayed(Runnable {
        println("postDelayed {} >> delay finish! CurrentTime: ${currentDatetimeYmdHmsMillisFullDividedByEnDash()}")
    }, delayInMillis)

    println()
}
