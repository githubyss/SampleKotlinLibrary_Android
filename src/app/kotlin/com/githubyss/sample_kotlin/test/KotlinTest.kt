package com.githubyss.sample_kotlin.test

import com.githubyss.sample_kotlin.test.call.callByFunction
import com.githubyss.sample_kotlin.test.call.callByInterface


fun main(args: Array<String>) {
    println(args)
    println()

    callByInterface()
    callByFunction()

    // TestThread.thread()
    // thread()

    // TestSynchronized.synchronize()
}