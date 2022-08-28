package com.githubyss.sample_kotlin.test

import com.githubyss.sample_kotlin.test.call.callByFunction
import com.githubyss.sample_kotlin.test.call.callByInterface
import com.githubyss.sample_kotlin.test.circulation.circulation
import com.githubyss.sample_kotlin.test.component.component
import com.githubyss.sample_kotlin.test.coroutine.coroutine
import com.githubyss.sample_kotlin.test.coroutine.coroutineAsync
import com.githubyss.sample_kotlin.test.coroutine.coroutineCallback
import com.githubyss.sample_kotlin.test.delegate.delegate
import com.githubyss.sample_kotlin.test.higher_order.highOrder
import com.githubyss.sample_kotlin.test.infix.infix
import com.githubyss.sample_kotlin.test.jvm_annotation.TestJvmAnnotation
import com.githubyss.sample_kotlin.test.jvm_annotation.TestJvmAnnotationJava
import com.githubyss.sample_kotlin.test.list.list
import com.githubyss.sample_kotlin.test.nest.nest
import com.githubyss.sample_kotlin.test.time_delay.timeDelay


/**
 *
 * 报错：Error running 'xxxx-8032': Cannot run program "C:\home\Java\jdk1.8.0_92\bin\java.exe" (in directory "D:\home\idea\master1\tools"): CreateProcess error=206, 文件名或扩展名太长。
 * 解决方案：修改项目下 .idea\workspace.xml，找到标签 <component name="PropertiesComponent"> ， 在标签里加一行  <property name="dynamic.classpath" value="true" />
 */
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

    // nest()
    // infix()
    // component()
    // highOrder()
    // delegate()
    // circulation()

    list()

    // TestJvmAnnotation.jvmAnnotation()
    // TestJvmAnnotationJava.jvmAnnotation()

    // TestSynchronized.synchronize()
}