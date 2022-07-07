package com.githubyss.sample_kotlin.test.circulation


fun circulation() {
    println("循环")
    println()

    repeat()
    forIn()
}

private fun repeat() {
    println("通过 repeat 循环")
    repeat(100) {
        println(it)
    }
    println()
}

private fun forIn() {
    println("通过 for 循环")
    for (i in 0..(100 - 1)) {
        println(i)
    }

    for (i in 0 until 100) {
        println(i)
    }
    println()
}
