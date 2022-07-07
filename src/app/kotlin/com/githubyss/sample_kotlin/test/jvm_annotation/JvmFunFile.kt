// 使用 @file:JvmName 注解，可以在 Java 代码中使用注解标明的文件名调用文件函数，而不必加 Kt 后缀
@file:JvmName("JvmFunFile")

package com.githubyss.sample_kotlin.test.jvm_annotation


// 使用 @JvmOverloads 注解，保证 Java 代码中可以使用 Kotlin 函数中的默认值。
@JvmOverloads
fun jvmFun(param1: String, param2: Int = 0) {
    println("param1:$param1, param2:$param2")
    println()
}
