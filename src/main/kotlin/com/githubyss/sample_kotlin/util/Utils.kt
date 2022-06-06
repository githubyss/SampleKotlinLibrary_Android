package com.githubyss.sample_kotlin.util

import com.githubyss.mobile.common.kit.util.currentDatetimeYmdH24msMillisFullDividedByEnDash


fun print(string: String) {
    kotlin.io.print(string)
}

fun println() {
    kotlin.io.println()
}

fun println(string: String) {
    kotlin.io.println(string)
}

fun printlnWithTime(string: String) {
    kotlin.io.println("$string | CurrentTime: ${currentDatetimeYmdH24msMillisFullDividedByEnDash()}")
}