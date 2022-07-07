package com.githubyss.sample_kotlin.test.nest


fun nest() {
    println("函数嵌套")
    println()

    val param1 = "1"
    val param2 = "2"

    fun innerFunction() {
        println("param1:$param1, param2:$param2")
    }

    innerFunction()

    println()
}
