package com.githubyss.sample_kotlin.test.call


private var tag: String = ""

fun callByInterface() {
    println("通过接口调用（接口做函数的参数）")
    println()

    tag = "callByInterface：传入匿名接口，0参数，0返回"
    callByInterface0Arg0Return(tag, object : CallListener0Arg0Return {
        override fun doSt() {
            println("no param")
        }
    })

    tag = "callByInterface：传入匿名接口，2参数，0返回"
    callByInterface2Arg0Return(tag, object : CallListener2Arg0Return {
        override fun doSt(param1: Int, param2: Int) {
            println("param1: $param1, param2: $param2")
        }
    })

    tag = "callByInterface：传入匿名接口，0参数，1返回"
    callByInterface0Arg1Return(tag, object : CallListener0Arg1Return {
        override fun getSt(): String {
            return "no param"
        }
    })

    tag = "callByInterface：传入匿名接口，2参数，1返回"
    callByInterface2Arg1Return(tag, object : CallListener2Arg1Return {
        override fun getSt(param1: Int, param2: Int): String {
            return "param1: $param1, param2: $param2"
        }
    })

    tag = "callByInterface：传入具名接口，0参数，0返回"
    val callListener0Arg0Return: CallListener0Arg0Return = object : CallListener0Arg0Return {
        override fun doSt() {
            println("no param")
        }
    }
    callByInterface0Arg0Return(tag, callListener0Arg0Return)

    tag = "callByInterface：传入具名接口，2参数，0返回"
    val callListener2Arg0Return: CallListener2Arg0Return = object : CallListener2Arg0Return {
        override fun doSt(param1: Int, param2: Int) {
            println("param1: $param1, param2: $param2")
        }
    }
    callByInterface2Arg0Return(tag, callListener2Arg0Return)

    tag = "callByInterface：传入具名接口，0参数，1返回"
    val callListener0Arg1Return: CallListener0Arg1Return = object : CallListener0Arg1Return {
        override fun getSt(): String {
            return "no param"
        }
    }
    callByInterface0Arg1Return(tag, callListener0Arg1Return)

    tag = "callByInterface：传入具名接口，2参数，1返回"
    val callListener2Arg1Return: CallListener2Arg1Return = object : CallListener2Arg1Return {
        override fun getSt(param1: Int, param2: Int): String {
            return "param1: $param1, param2: $param2"
        }
    }
    callByInterface2Arg1Return(tag, callListener2Arg1Return)
}

private fun callByInterface0Arg0Return(tag: String, listener: CallListener0Arg0Return): Unit {
    println(tag)
    listener.doSt()
    println()
}

private fun callByInterface2Arg0Return(tag: String, listener: CallListener2Arg0Return): Unit {
    println(tag)
    listener.doSt(1, 2)
    println()
}

private fun callByInterface0Arg1Return(tag: String, listener: CallListener0Arg1Return): Unit {
    println(tag)
    println("return: ${listener.getSt()}")
    println()
}

private fun callByInterface2Arg1Return(tag: String, listener: CallListener2Arg1Return): Unit {
    println(tag)
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
