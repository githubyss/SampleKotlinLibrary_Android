package com.githubyss.sample_kotlin.test.call

import java.lang.System.currentTimeMillis


fun callFunction() {
    val tag = currentTimeMillis().toString()

    callFunction(tag, fun(param1: Int, param2: Int) {
        print(param1)
        print(param2)
    })

    callFunction(tag, { param1, param2 ->
        print(param1)
        print(param2)
    })

    callFunction(tag) { param1, param2 ->
        print(param1)
        print(param2)
    }
}

private fun callFunction(tag: String, doSt: (param1: Int, param2: Int) -> Unit): Unit {
    print(tag)
    doSt(1, 2)
}
