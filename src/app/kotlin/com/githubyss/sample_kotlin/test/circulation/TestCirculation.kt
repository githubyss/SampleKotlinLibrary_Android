package com.githubyss.sample_kotlin.test.circulation


fun circulation() {
    println("循环")
    println()

    repeat()
    forIn()
    forEach()
}

private fun repeat() {
    println("通过 repeat 循环")
    repeat(5) {
        println(it)
    }
    println()
}

private fun forIn() {
    println("通过 for in 循环")

    println("范围循环：0..5，含头含尾")
    for (i in 0..5) {
        println(i)
    }

    println("范围循环：0 until 5，含头不含尾")
    for (i in 0 until 5) {
        println(i)
    }

    println()


    println("数组")
    val array = arrayOf("a", "b", "c", "d", "e")
    println("array: $array")
    println("array.toString(): ${array.toString()}")
    println("array.joinToString(): ${array.joinToString()}")
    println("array.contentToString(): ${array.contentToString()}")

    println("数组循环：项")
    for (item in array) {
        println("item: $item")
    }

    println("数组循环：项&下标")
    for ((idx, item) in array.withIndex()) {
        println("idx: $idx, item: $item")
    }

    println()


    println("列表")
    val list = listOf("a", "b", "c", "d", "e")
    println("list: $list")
    println("list.toString(): ${list.toString()}")
    println("list.joinToString(): ${list.joinToString()}")

    println("列表循环：项")
    for (item in list) {
        println("item: $item")
    }

    println("列表循环：项&下标")
    for ((idx, item) in list.withIndex()) {
        println("idx: $idx, item: $item")
    }

    println()
}

private fun forEach() {
    println("通过 for each 循环")


    println("数组")
    val array = arrayOf("a", "b", "c", "d", "e")
    println("array: $array")

    println("数组遍历：项")
    array.forEach {
        println("item: $it")
    }

    println("数组遍历：项&下标")
    array.forEachIndexed { idx, item ->
        println("idx: $idx, item: $item")
    }

    println()


    println("列表")
    val list = listOf("a", "b", "c", "d", "e")
    println("list: $list")

    println("列表遍历：项")
    list.forEach {
        println("item: $it")
    }

    println("列表遍历：项&下标")
    list.forEachIndexed { idx, item ->
        println("idx: $idx, item: $item")
    }

    println()
}
