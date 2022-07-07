package com.githubyss.sample_kotlin.test.jvm_annotation


object TestJvmAnnotation {
    fun jvmAnnotation() {
        jvmFun("0")
        jvmFun("0", 0)

        JvmFunObject.tag
        JvmFunObject.jvmFun("0", 0)
    }
}
