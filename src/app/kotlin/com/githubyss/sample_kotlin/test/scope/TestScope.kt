package com.githubyss.sample_kotlin.test.scope

import android.graphics.Color
import android.graphics.Paint
import com.githubyss.mobile.common.kit.util.dp2px


fun scope() {
    println("作用域函数")
    println()

    run()
    apply()
    let()
    also()

    println()
}

fun run() {
    // run{} 适合用于对一个对象做附加操作，比如对象的初始化操作
    // run{} 的作用域参数是 this
    // run{} 的返回值是 Unit
    val ret: Unit = Paint().run {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.BLUE
        strokeWidth = 6f.dp2px
    }
}

fun apply() {
    // apply{} 适合用于对一个对象做附加操作，比如对象的初始化操作
    // apply{} 的作用域参数是 this
    // apply{} 的返回值是调用他的对象
    val ret: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.BLUE
        strokeWidth = 6f.dp2px
    }
}

fun let() {
    // let{} 也可以对一个对象做附加操作，但是不优雅
    // let{} 的作用域参数是 it
    // let{} 的返回值是最后一行
    val paint: Paint = Paint().let {
        it.isAntiAlias = true
        it.style = Paint.Style.STROKE
        it.color = Color.BLUE
        it.strokeWidth = 6f.dp2px
        return@let it
    }

    val message: String? = null
    // let{} 更适合用来做空判断
    val ret: Unit? = message?.let {
        println("message:$it")
    }
}

fun also() {
    val message: String? = null
    // also{} 的作用域参数是 it
    // also{} 的返回值是调用他的对象
    val ret: String? = message?.also {
        println("message:$it")
    }
}
