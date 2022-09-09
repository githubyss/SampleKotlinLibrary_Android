package com.githubyss.sample_kotlin.test.call_function


fun callByInterface() {
    println("通过接口调用（接口做函数的参数）")
    println()

    println("传入匿名接口")
    callByInterface0Arg0Return(object : CallListener0Arg0Return {
        override fun doSt() = println("no param")
    })

    callByInterface2Arg0Return(object : CallListener2Arg0Return {
        override fun doSt(param1: Int, param2: Int) = println("param1: $param1, param2: $param2")
    })

    callByInterface0Arg1Return(object : CallListener0Arg1Return {
        override fun getSt(): String = "no param"
    })

    callByInterface2Arg1Return(object : CallListener2Arg1Return {
        override fun getSt(param1: Int, param2: Int): String = "param1: $param1, param2: $param2"
    })

    println()


    println("传入具名接口")
    val callListener0Arg0Return = object : CallListener0Arg0Return {
        override fun doSt() = println("no param")
    }
    callByInterface0Arg0Return(callListener0Arg0Return)

    val callListener2Arg0Return = object : CallListener2Arg0Return {
        override fun doSt(param1: Int, param2: Int) {
            println("param1: $param1, param2: $param2")
        }
    }
    callByInterface2Arg0Return(callListener2Arg0Return)

    val callListener0Arg1Return = object : CallListener0Arg1Return {
        override fun getSt(): String {
            return "no param"
        }
    }
    callByInterface0Arg1Return(callListener0Arg1Return)

    val callListener2Arg1Return = object : CallListener2Arg1Return {
        override fun getSt(param1: Int, param2: Int): String {
            return "param1: $param1, param2: $param2"
        }
    }
    callByInterface2Arg1Return(callListener2Arg1Return)

    println()
}

private fun callByInterface0Arg0Return(listener: CallListener0Arg0Return): Unit {
    println("0参数，0返回")
    listener.doSt()
    println()
}

private fun callByInterface2Arg0Return(listener: CallListener2Arg0Return): Unit {
    println("2参数，0返回")
    listener.doSt(1, 2)
    println()
}

private fun callByInterface0Arg1Return(listener: CallListener0Arg1Return): Unit {
    println("0参数，1返回")
    println("return: ${listener.getSt()}")
    println()
}

private fun callByInterface2Arg1Return(listener: CallListener2Arg1Return): Unit {
    println("2参数，1返回")
    println("return: ${listener.getSt(1, 2)}")
    println()
}

private interface CallListener0Arg0Return {
    fun doSt()
}

private interface CallListener2Arg0Return {
    fun doSt(param1: Int, param2: Int)
}

private interface CallListener0Arg1Return {
    fun getSt(): String
}

private interface CallListener2Arg1Return {
    fun getSt(param1: Int, param2: Int): String
}
