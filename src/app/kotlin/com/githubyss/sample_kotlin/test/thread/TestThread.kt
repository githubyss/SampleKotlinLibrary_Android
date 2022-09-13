package com.githubyss.sample_kotlin.test.thread

import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread


/**
 * TestThread
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/26 18:50:51
 */
fun thread() {
    println("多线程")
    println()

    threadNormal()
    threadClosure()
    threadLambda()
    threadConcurrent()
    threadCustomClass()
    threadRunnable()
    threadFactory()
}

private fun threadNormal() {
    val thread: Thread = object : Thread() {
        override fun run() {
            println("run() >> 单独使用 Thread（直接实例化 Thread）。");
            println("threadNormal 1");
            println("threadNormal 2");
            println("threadNormal 3");
            println("threadNormal 4");
            println("threadNormal 5");
            println();
        }
    }
    thread.start()
}

private fun threadClosure() {
    val thread: Thread = Thread {
        run {
            println("run() >> 单独使用 Thread（使用 Thread 闭包写法）。")
            println("threadClosure 1");
            println("threadClosure 2");
            println("threadClosure 3");
            println("threadClosure 4");
            println("threadClosure 5");
            println()
        }
    }
    thread.start()
}

private fun threadLambda() {
    val thread: Thread = Thread {
        println("run() >> 单独使用 Thread（使用 Thread Lambda 写法）。")
        println("threadLambda 1");
        println("threadLambda 2");
        println("threadLambda 3");
        println("threadLambda 4");
        println("threadLambda 5");
        println()
    }
    thread.start()
}

private fun threadConcurrent() {
    thread {
        println("run() >> 单独使用 Thread（使用并发包 concurrent 中的 thread 写法）。")
        println("threadConcurrent 1");
        println("threadConcurrent 2");
        println("threadConcurrent 3");
        println("threadConcurrent 4");
        println("threadConcurrent 5");
        println()
    }
}

private fun threadCustomClass() {
    val thread: CustomThread = CustomThread()
    thread.start()
}

private class CustomThread : Thread() {
    override fun run() {
        println("run() >> 单独使用 Thread（使用自定义 Thread 类）。");
        println("threadCustomClass 1");
        println("threadCustomClass 2");
        println("threadCustomClass 3");
        println("threadCustomClass 4");
        println("threadCustomClass 5");
        println();
    }
}

private fun threadRunnable() {
    val runnable: Runnable = Runnable {
        println("run() >> 使用 Runnable，达到重用运行代码的目的。");
        println("threadRunnable 1");
        println("threadRunnable 2");
        println("threadRunnable 3");
        println("threadRunnable 4");
        println("threadRunnable 5");
        println();
    }
    val thread: Thread = Thread(runnable)
    thread.start()
}

private fun threadFactory() {
    val runnable: Runnable = Runnable {
        println("${Thread.currentThread().name} >>> run() >> 使用 ThreadFactory。");
        println("${Thread.currentThread().name} threadFactory 1");
        println("${Thread.currentThread().name} threadFactory 2");
        println("${Thread.currentThread().name} threadFactory 3");
        println("${Thread.currentThread().name} threadFactory 4");
        println("${Thread.currentThread().name} threadFactory 5");
        println();
    }

    // val threadFactory: ThreadFactory = ThreadFactory { r ->
    //     var count: AtomicInteger = AtomicInteger(0)
    //     // 使用工厂方法模式，对新线程的创建进行初始化。
    //     Thread(r, "Thread-${count.incrementAndGet()}")
    // }

    val threadFactory: ThreadFactory = object : ThreadFactory {
        var count: AtomicInteger = AtomicInteger(0)

        override fun newThread(r: Runnable?): Thread {
            // 使用工厂方法模式，对新线程的创建进行初始化。
            return Thread(r, "Thread-${count.incrementAndGet()}")
        }
    }

    val thread1: Thread = threadFactory.newThread(runnable)
    thread1.start()
    val thread2: Thread = threadFactory.newThread(runnable)
    thread2.start()
}
