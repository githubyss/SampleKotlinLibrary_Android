package com.githubyss.sample_kotlin.test

import com.githubyss.sample_kotlin.test.thread.thread
import com.githubyss.sample_kotlin.test.time_delay.timeDelay


fun main(args: Array<String>) {
    println(args)
    println()

    // callByInterface()
    // callByFunction()

    // TestThread.thread()
    thread()

    timeDelay()

    // TestSynchronized.synchronize()
}