package com.githubyss.sample_kotlin.test.call


private var tag: String = ""

fun callByFunction() {
    println("通过函数参数调用（高阶函数：函数做函数的参数）")
    println()

    tag = "callByFunction：传入匿名函数体，0参数，0返回"
    callByFunction0Arg0Return(tag, fun() {
        println("no param")
    })
    tag = "callByFunction：传入匿名函数体，2参数，0返回"
    callByFunction2Arg0Return(tag, fun(param1: Int, param2: Int) {
        println("param1: $param1, param2: $param2")
    })
    tag = "callByFunction：传入匿名函数体，0参数，1返回"
    callByFunction0Arg1Return(tag, fun(): String {
        return "no param"
    })
    tag = "callByFunction：传入匿名函数体，2参数，1返回"
    callByFunction2Arg1Return(tag, fun(param1: Int, param2: Int): String {
        return "param1: $param1, param2: $param2"
    })

    tag = "callByFunction：传入 Lambda 函数体，0参数，0返回"
    callByFunction0Arg0Return(tag, {
        println("no param")
    })
    tag = "callByFunction：传入 Lambda 函数体，2参数，0返回"
    callByFunction2Arg0Return(tag, { param1, param2 ->
        println("param1: $param1, param2: $param2")
    })
    tag = "callByFunction：传入 Lambda 函数体，0参数，1返回"
    callByFunction0Arg1Return(tag, {
        "no param"
    })
    tag = "callByFunction：传入 Lambda 函数体，2参数，1返回"
    callByFunction2Arg1Return(tag, { param1, param2 ->
        "param1: $param1, param2: $param2"
    })

    tag = "callByFunction：Lambda 函数体后置，0参数，0返回"
    callByFunction0Arg0Return(tag) {
        println("no param")
    }
    tag = "callByFunction：Lambda 函数体后置，2参数，0返回"
    callByFunction2Arg0Return(tag) { param1, param2 ->
        println("param1: $param1, param2: $param2")
    }
    tag = "callByFunction：Lambda 函数体后置，0参数，1返回"
    callByFunction0Arg1Return(tag) {
        "no param"
    }
    tag = "callByFunction：Lambda 函数体后置，2参数，1返回"
    callByFunction2Arg1Return(tag) { param1, param2 ->
        "param1: $param1, param2: $param2"
    }
}

private fun callByFunction0Arg0Return(tag: String, doSt: () -> Unit): Unit {
    println(tag)
    doSt()
    println()
}

private fun callByFunction2Arg0Return(tag: String, doSt: (param1: Int, param2: Int) -> Unit): Unit {
    println(tag)
    doSt(1, 2)
    println()
}

private fun callByFunction0Arg1Return(tag: String, getSt: () -> String): Unit {
    println(tag)
    println("return: ${getSt()}")
    println()
}

private fun callByFunction2Arg1Return(tag: String, getSt: (param1: Int, param2: Int) -> String): Unit {
    println(tag)
    println("return: ${getSt(1, 2)}")
    println()
}
