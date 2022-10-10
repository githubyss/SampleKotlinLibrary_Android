package com.githubyss.sample_kotlin.util

import com.githubyss.common.kit.util.currentTimeStringH24msmFullDividedByChs


fun print(msg: String) {
    kotlin.io.print(msg)
}

fun println() {
    kotlin.io.println()
}

fun println(msg: String, suffix: String = "") {
    val suffix: String = when {
        suffix.isEmpty() -> ""
        else -> " >> $suffix"
    }
    kotlin.io.println("$msg$suffix")
}

fun printlnPre(msg: String, suffix: String = "") {
    println()
    println(msg, suffix)
}

fun printlnPost(msg: String, suffix: String = "") {
    println(msg, suffix)
    println()
}

fun printlnPrePost(msg: String, suffix: String = "") {
    println()
    println(msg, suffix)
    println()
}

fun printlnWithTime(msg: String, suffix: String = "") {
    val suffix = when {
        suffix.isEmpty() -> ""
        else -> " >> $suffix"
    }
    println("$msg | CurrentTime: ${currentTimeStringH24msmFullDividedByChs}$suffix")
}

fun printlnPreWithTime(msg: String, suffix: String = "") {
    println()
    printlnWithTime(msg, suffix)
}

fun printlnPostWithTime(msg: String, suffix: String = "") {
    printlnWithTime(msg, suffix)
    println()
}

fun printlnPrePostWithTime(msg: String, suffix: String = "") {
    println()
    printlnWithTime(msg, suffix)
    println()
}
