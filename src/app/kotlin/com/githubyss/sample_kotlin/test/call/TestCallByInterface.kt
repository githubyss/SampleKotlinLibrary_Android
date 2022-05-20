package com.githubyss.sample_kotlin.test.call

import java.lang.System.currentTimeMillis


fun callByInterface() {
    println("通过接口调用（接口做函数的参数）")
    println()

    val tag = currentTimeMillis().toString()

    println("callByInterface：传入匿名接口")
    callByInterface(tag, object : CallListener {
        override fun doSt(param1: Int, param2: Int) {
            println("param1: $param1")
            println("param2: $param2")
        }
    })

    println("callByInterface：传入具名接口")
    val callListener: CallListener = object : CallListener {
        override fun doSt(param1: Int, param2: Int) {
            println("param1: $param1")
            println("param2: $param2")
        }
    }

}

private fun callByInterface(tag: String, listener: CallListener): Unit {
    println("tag: $tag")
    listener.doSt(1, 2)
    println()
}

private interface CallListener {
    fun doSt(param1: Int, param2: Int)
}
