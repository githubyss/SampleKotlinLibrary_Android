package com.githubyss.sample_kotlin.test.jvm_annotation


object JvmFunObject {
    @JvmStatic
    // 使用 @get:JvmName 注解，可以在 Java 代码中使用 JvmFunObject.tag(); 调用成员属性，而不必加 get 前缀
    @get:JvmName("tag")
    var tag = "JvmFunObject"
        private set

    // 使用 @JvmStatic 注解，可以在 Java 代码中使用 JvmFunObject.jvmFun("0", 0); 调用函数，而不必加 .INSTANCE 或者 .Companion
    @JvmStatic
    // 使用 @JvmOverloads 注解，可以在 Java 代码中使用 Kotlin 函数中的默认值。
    @JvmOverloads
    fun jvmFun(param1: String, param2: Int = 0) {
        println("param1:$param1, param2:$param2")
        println()
    }
}
