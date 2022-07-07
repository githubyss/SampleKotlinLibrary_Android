package com.githubyss.sample_kotlin.test.higher_order

import com.githubyss.sample_kotlin.test.entity.Response


val responses = ArrayList<Response>()
val dealResponses = ArrayList<Response>()

fun highOrder() {
    println("高阶函数")
    println()

    forEach()
    filter()
}

private fun forEach() {
    println("通过 forEach 遍历筛选")
    responses.forEach {
        if (it.code == 200) {
            dealResponses.add(it)
        }
    }
    println()
}

private fun filter() {
    println("通过 filter 筛选")
    dealResponses.addAll(responses.filter { it.code == 200 })
    println()
}
