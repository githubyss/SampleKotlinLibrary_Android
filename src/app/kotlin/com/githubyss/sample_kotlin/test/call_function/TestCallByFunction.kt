package com.githubyss.sample_kotlin.test.call_function


fun callByFunction() {
    println("通过函数参数调用（高阶函数：函数做函数的参数）")
    println()

    println("传入匿名函数体")
    callByFunction0Arg0Return(fun() = println("no param"))
    callByFunction2Arg0Return(fun(param1: Int, param2: Int) = println("param1: $param1, param2: $param2"))
    callByFunction0Arg1Return(fun(): String = "no param")
    callByFunction2Arg1Return(fun(param1: Int, param2: Int): String = "param1: $param1, param2: $param2")
    println()

    println("传入 Lambda 函数体")
    callByFunction0Arg0Return({ println("no param") })
    callByFunction2Arg0Return({ param1, param2 -> println("param1: $param1, param2: $param2") })
    callByFunction0Arg1Return({ "no param" })
    callByFunction2Arg1Return({ param1, param2 -> "param1: $param1, param2: $param2" })
    println()

    println("Lambda 函数体后置")
    callByFunction0Arg0Return { println("no param") }
    callByFunction2Arg0Return { param1, param2 -> println("param1: $param1, param2: $param2") }
    callByFunction0Arg1Return { "no param" }
    callByFunction2Arg1Return { param1, param2 -> "param1: $param1, param2: $param2" }
    println()
}

private fun callByFunction0Arg0Return(doSt: () -> Unit): Unit {
    println("0参数，0返回")
    doSt()
    println()
}

private fun callByFunction2Arg0Return(doSt: (param1: Int, param2: Int) -> Unit): Unit {
    println("2参数，0返回")
    doSt(1, 2)
    println()
}

private fun callByFunction0Arg1Return(getSt: () -> String): Unit {
    println("0参数，1返回")
    println("return: ${getSt()}")
    println()
}

private fun callByFunction2Arg1Return(getSt: (param1: Int, param2: Int) -> String): Unit {
    println("2参数，1返回")
    println("return: ${getSt(1, 2)}")
    println()
}
