package com.githubyss.sample_kotlin.test.jvm_annotation


/**
 * TestJvmAnnotation
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/27 20:03:35
 */
object TestJvmAnnotation {
    fun jvmAnnotation() {
        jvmFun("0")
        jvmFun("0", 0)

        JvmFunObject.tag
        JvmFunObject.jvmFun("0", 0)
    }
}
