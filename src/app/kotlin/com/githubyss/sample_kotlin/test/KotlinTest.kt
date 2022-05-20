package com.githubyss.sample_kotlin.test

import com.githubyss.sample_kotlin.test.call.callByFunction
import com.githubyss.sample_kotlin.test.call.callByInterface
import com.githubyss.sample_kotlin.test.thread.TestSynchronized
import com.githubyss.sample_kotlin.test.thread.TestThread


fun main(args: Array<String>) {
    println(args)
    println()

    callByInterface()
    callByFunction()

    TestThread.thread()
    // thread()

    TestSynchronized.synchronize()
}