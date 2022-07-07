package com.githubyss.sample_kotlin.test.infix


fun infix() {
    println("Infix")
    println()

    val param1 = 1
    val param2 = 2

    println("param1 plus param2 = ${param1 plus param2}")
    println()
}

infix fun Int.plus(added: Int): Int {
    return this + added
}
