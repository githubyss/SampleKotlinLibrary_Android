package com.githubyss.sample_kotlin.test.timer

import com.githubyss.sample_kotlin.util.printlnWithTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timerTask


/**
 * TestTimer
 *
 * - 一个 Timer 会和一个 Thread 关联，所有的任务都在这个关联的 Thread 里执行。
 * - 只有一个关联的 Thread，所以不会存在同一 Timer 的两个 Task 同时执行的情况。
 * - Timer.cancel() 会取消所有等待执行的 Task，并且关联的 Thread 会被释放。
 * - TimerTask.cancel() 仅仅只是修改 Task 的状态值，并没有及时清理失效的任务，一次只取消一个 Task。
 * - Timer.purge() 方法就是用来释放内存引用的。purge 方法会检查 timer queue 里标记为 canceled 的 task，将对它的引用置为 null。
 * - 纵观整个 Timer 源码，唯一进行自我清理是在 TimerThread 中维护的 queue.removeMin();（前提是当前失效的任务优先级最高）。
 *
 * 使用定时时需要注意两个点：
 * - 任务精确性：当 Task 中的任务耗时较长时，会阻塞线程，导致后续的任务都延后。因此需要在子线程做耗时处理。
 * - 内存泄漏：当用户取消了一个 Task 以后，失效的任务依然会占据着 queue 队列，造成内存泄漏。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/09 16:00:07
 */

private var momentDate = Date("2022/09/13 19:30:00")
private var delayInMillis = 2_000L
private var periodInMillis = 1_000L


fun timer() {
    println("定时器")
    printlnWithTime("CurrentThread: ${Thread.currentThread()}")
    println()

    // timerDelay()
    timerMoment()
    // timerDelayPeriod()
    // timerMomentPeriod()

    // timerAccuracy()
    // timerMemoryLeak()

    println()
}

private fun timerDelay() {
    println("指定定时器延迟 delay 后执行任务（只执行一次）")

    val timer = Timer()

    printTimerSchedule(timer, 1)
    timer.schedule(timerTask {
        printTaskFinish(this, 1)
    }, delayInMillis)

    printTimerSchedule(timer, 2)
    timer.schedule(timerTask {
        printTaskFinish(this, 2)
    }, delayInMillis)

    println()
}


private fun timerDelayPeriod() {
    println("延迟 delay 时间后执行，间隔 period 重复执行")

    val timer = Timer()
    val task = TimerTaskPeriod(timer)

    printTimerSchedule(timer, 1)
    timer.schedule(task, delayInMillis, periodInMillis)

    printTimerSchedule(timer, 2)
    timer.schedule(timerTask {
        printTaskFinish(this)
    }, delayInMillis, periodInMillis)
}

private class TimerTaskPeriod(private val timer: Timer, var taskId: Int = 0) : TimerTask() {
    override fun run() {
        printTaskFinish(this@TimerTaskPeriod, taskId)

        if (taskId >= 5) {
            printTaskCancel(this, taskId)
            this.cancel()
            timer.purge()
        }

        taskId++
    }
}

private fun timerMoment() {
    println("指定某个时间执行定时器（之执行一次），对于一过去的时间立即执行")

    val timer = Timer()

    printTimerSchedule(timer)
    timer.schedule(timerTask {
        printTaskFinish(this)
    }, momentDate)
}

private fun timerMomentPeriod() {
    println("设置首次执行时间，间隔 period 重复执行（首次时间已经过去立即执行）")

    val timer = Timer()

    printTimerSchedule(timer, 1)
    timer.schedule(timerTask {
        printTaskFinish(this)
    }, momentDate, periodInMillis)
}


private fun timerAccuracy() {
    println("任务精确性")

    val timer = Timer()
    val task = TimerTaskAccuracy()

    printTimerSchedule(timer)
    timer.schedule(task, delayInMillis, periodInMillis)
}

private class TimerTaskAccuracy(var taskId: Int = 0) : TimerTask() {
    override fun run() {
        CoroutineScope(Dispatchers.Default).launch {
            printTaskFinish(this@TimerTaskAccuracy, taskId)
            taskId++

            // 模拟执行时间
            // delay(5_000)
            Thread.sleep(5_000)
        }
    }
}


private fun timerMemoryLeak() {
    println("内存泄漏")

    val timer = Timer()
    var timerId = 0

    printTimerSchedule(timer, timerId)
    timer.schedule(TimerTaskMemoryLeak(), delayInMillis, periodInMillis)

    while (true) {
        val task = TimerTaskMemoryLeak()
        printTimerSchedule(timer, timerId)
        timer.schedule(task, delayInMillis, periodInMillis)

        printTaskCancel(task, task.taskId)
        // 虽然 TimeTask.cancel() 提供了一个及时取消的接口，但却没有一个自动机制保证失效的任务及时回收（需要用户手动处理）。
        task.cancel()
        // 为了防止内存泄漏，Timer 提供了一个方法 purge() 及时清除无效任务。注释是“从 task queue 里移除所有标记为 canceled 的 task”。
        // 用户只要合理地使用 Timer.purge() 就能避免内存泄漏。
        // 如果没有这一句，则会造成 OOM，主线程因 OOM 异常退出，而 TimerThread 线程不受影响。
        timer.purge()

        Thread.yield()
        timerId++
    }
}

private class TimerTaskMemoryLeak(var taskId: Int = 0) : TimerTask() {
    // 模拟执行内存消耗
    val bytes = ByteArray(10 * 1024 * 1024)

    override fun run() {
        printTaskFinish(this, taskId)
        taskId++
    }
}


private fun printTimerSchedule(timer: Timer, id: Int = 0) {
    printlnWithTime("Schedule $timer $id start! CurrentThread: ${Thread.currentThread()}")
}

private fun printTaskFinish(task: TimerTask, id: Int = 0) {
    printlnWithTime("Task $task $id finish! CurrentThread: ${Thread.currentThread()}")
}

private fun printTaskCancel(task: TimerTask, id: Int = 0) {
    printlnWithTime("Task $task $id cancel! CurrentThread: ${Thread.currentThread()}")
}


/** 通过 object : TimerTask() { } 创建 TimerTask，由于一个 TimerTask 实例只能被 Timer 执行一次，所以需要用 get() = 方式保证每次调用到 timerTaskObject 时都是新建的实例 */
private val timerTaskObject
    get() = object : TimerTask() {
        override fun run() {}
    }

private val timerTaskInline
    get() = timerTask {}

private val timerTaskDefault get() = DefaultTimerTask()

private class DefaultTimerTask : TimerTask() {
    override fun run() {}
}
