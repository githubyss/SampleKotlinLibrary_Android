package com.githubyss.sample_kotlin.test.higher_order_function


var function0Arg0Return: () -> Unit = { }
var function2Arg0Return: (Int, Int) -> Unit = { _, _ -> }
var function0Arg1Return: () -> Int = { 0 }
var function2Arg1Return: (Int, Int) -> Int = { _, _ -> 0 }

var function0Arg0ReturnFun: () -> (() -> Unit) = { {} }
var function0ArgFun0ReturnFun: (() -> Unit) -> (() -> Unit) = { {} }
var function: ((Int, Int) -> Int) -> ((Int, Int) -> Int) = { { _, _ -> 0 } }


fun highOrder() {
    println("高阶函数")
    println()

    println("{ _, _ -> 0 } Lambda 表达式方式")
    println("::something 函数引用方式")

    highOrder0Arg0Return()
    highOrder2Arg0Return()
    highOrder0Arg1Return()
    highOrder2Arg1Return()
}

private fun highOrder0Arg0Return() {
    println("0参数，0返回")

    println("function0Arg0Return = $function0Arg0Return")
    println("::printNothing = ${::printNothing}")
    function0Arg0Return = ::printNothing
    println("function0Arg0Return = $function0Arg0Return")
    println("function0Arg0Return() = ${function0Arg0Return()}")

    println()
}

private fun highOrder2Arg0Return() {
    println("2参数，0返回")

    println("function2Arg0Return = $function2Arg0Return")
    println("::printResult = ${::printResult}")
    function2Arg0Return = ::printResult
    println("function2Arg0Return = $function2Arg0Return")
    println("function2Arg0Return(2, 3) = ${function2Arg0Return(2, 3)}")

    println()
}

private fun highOrder0Arg1Return() {
    println("0参数，1返回")

    println("function0Arg1Return = $function0Arg1Return")
    println("::getDefault = ${::getDefault}")
    function0Arg1Return = ::getDefault
    println("function0Arg1Return = $function0Arg1Return")
    println("function0Arg1Return() = ${function0Arg1Return()}")

    println()
}

private fun highOrder2Arg1Return() {
    println("2参数，1返回")

    println("function2Arg1Return = $function2Arg1Return")
    println("::getResult = ${::getResult}")
    function2Arg1Return = ::getResult
    println("function2Arg1Return = $function2Arg1Return")
    println("function2Arg1Return(2, 3) = ${function2Arg1Return(2, 3)}")

    println()
}

private fun highOrderComplex() {
    function = ::getFunction
}

private fun printNothing() = println("printNothing >> nothing")
private fun printResult(num1: Int, num2: Int) = println("printResult >> ${num1 + num2}")
private fun getDefault() = 100.also { println("getDefault >> 100") }
private fun getResult(num1: Int, num2: Int) = (num1 + num2).also { println("getResult >> ${num1 + num2}") }
private fun getFunction(func: ((Int, Int) -> Int)?) = func ?: { num1: Int, num2: Int -> num1 + num2 }
