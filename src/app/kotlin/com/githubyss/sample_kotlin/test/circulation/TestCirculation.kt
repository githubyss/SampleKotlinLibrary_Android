package com.githubyss.sample_kotlin.test.circulation


val array = arrayOf("a", "b", "c", "d", "e")
val list = listOf("a", "b", "c", "d", "e")

fun circulation() {
    println("循环")
    println()

    printInfo()
    repeat()
    forIn()
    forEach()
}

private fun printInfo() {
    println("数组源数据")
    println("array: $array")
    println("array.toString(): ${array.toString()}")
    println("array.joinToString(): ${array.joinToString()}")
    println("array.contentToString(): ${array.contentToString()}")
    println()

    println("列表源数据")
    println("list: $list")
    println("list.toString(): ${list.toString()}")
    println("list.joinToString(): ${list.joinToString()}")
    println()
}

private fun repeat() {
    println("通过 repeat 循环")

    println("重复循环，0 到 times-1")
    repeat(5) {
        println(it)
    }
    println()
}

private fun forIn() {
    println("通过 for in 循环")

    println("递增循环：0..5，全闭，含头含尾")
    for (i in 0..5) {
        println(i)
    }

    println("递增循环：0 until 5，前闭后开，含头不含尾")
    for (i in 0 until 5) {
        println(i)
    }

    println("递减循环：5 downTo 0，全闭，含头含尾")
    for (i in 5 downTo 0) {
        println(i)
    }

    println("循环间隔：0 until 5 step 2，步进 2，前闭后开，含头不含尾")
    for (i in 0 until 5 step 2) {
        println(i)
    }

    println()


    println("数组循环：项")
    for (item in array) {
        println("item: $item")
    }

    println("数组循环：下标")
    for (idx in array.indices) {
        println("idx: $idx")
    }

    println("数组循环：项&下标")
    for ((idx, item) in array.withIndex()) {
        println("idx: $idx, item: $item")
    }

    println()


    println("列表循环：项")
    for (item in list) {
        println("item: $item")
    }

    println("列表循环：下标")
    for (idx in list.indices) {
        println("idx: $idx")
    }

    println("列表循环：项&下标")
    for ((idx, item) in list.withIndex()) {
        println("idx: $idx, item: $item")
    }

    println()
}

private fun forEach() {
    println("通过 for each 循环")


    println("数组遍历：项")
    array.forEach {
        println("item: $it")
    }

    println("数组遍历：下标")
    array.indices.forEach {
        println("idx: $it")
    }

    println("数组遍历：项&下标")
    array.forEachIndexed { idx, item ->
        println("idx: $idx, item: $item")
    }
    array.withIndex().forEach {
        println("idx: ${it.index}, item: ${it.value}")
    }

    println()


    println("列表遍历：项")
    list.forEach {
        println("item: $it")
    }

    println("列表遍历：下标")
    list.indices.forEach {
        println("idx: $it")
    }

    println("列表遍历：项&下标")
    list.forEachIndexed { idx, item ->
        println("idx: $idx, item: $item")
    }
    list.withIndex().forEach {
        println("idx: ${it.index}, item: ${it.value}")
    }

    println()
}
