package com.githubyss.sample_kotlin.test.call

import java.lang.System.currentTimeMillis


fun callInterface() {
    val tag = currentTimeMillis().toString()

    callInterface(tag, object : CallListener {
        override fun doSt(param1: Int, param2: Int) {
            print(param1)
            print(param2)
        }
    })
}

private fun callInterface(tag: String, listener: CallListener): Unit {
    print(tag)
    listener.doSt(1, 2)
}

private interface CallListener {
    fun doSt(param1: Int, param2: Int)
}
