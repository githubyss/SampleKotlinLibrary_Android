package com.githubyss.sample_kotlin.test

import com.githubyss.sample_kotlin.test.circulation.circulation
import com.githubyss.sample_kotlin.test.component.component
import com.githubyss.sample_kotlin.test.delegate.delegate
import com.githubyss.sample_kotlin.test.higher_order.highOrder
import com.githubyss.sample_kotlin.test.infix.infix
import com.githubyss.sample_kotlin.test.jvm_annotation.TestJvmAnnotation
import com.githubyss.sample_kotlin.test.jvm_annotation.TestJvmAnnotationJava
import com.githubyss.sample_kotlin.test.nest.nest


fun main(args: Array<String>) {
    println(args)
    println()

    // callByInterface()
    // callByFunction()

    // TestThread.thread()
    // thread()

    // timeDelay()
    // coroutine()
    // coroutineAsync()
    // coroutineCallback()

    nest()
    infix()
    component()
    highOrder()
    delegate()
    circulation()

    TestJvmAnnotation.jvmAnnotation()
    TestJvmAnnotationJava.jvmAnnotation()

    // TestSynchronized.synchronize()
}