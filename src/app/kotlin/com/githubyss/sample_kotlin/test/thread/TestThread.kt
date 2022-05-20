package com.githubyss.sample_kotlin.test.thread


/**
 * TestThread
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/26 18:50:51
 */
fun thread() {
    threadStart()
}

private fun threadStart() {
    val thread: Thread = Thread {
        run {
            println("Thread started.")
        }
    }
    thread.start()
}
