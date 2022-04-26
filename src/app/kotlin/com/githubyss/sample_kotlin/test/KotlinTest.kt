package com.githubyss.sample_kotlin.test

import com.githubyss.sample_kotlin.test.call.callFunction
import com.githubyss.sample_kotlin.test.call.callInterface


fun main(args: Array<String>) {
    println(args)

    callInterface()
    callFunction()
}