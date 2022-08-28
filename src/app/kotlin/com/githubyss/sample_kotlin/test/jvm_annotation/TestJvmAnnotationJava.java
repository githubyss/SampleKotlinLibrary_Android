package com.githubyss.sample_kotlin.test.jvm_annotation;


/**
 * TestJvmAnnotationJava
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/27 20:03:29
 */
public class TestJvmAnnotationJava {
    public static void jvmAnnotation() {
        JvmFunFile.jvmFun("0");
        JvmFunFile.jvmFun("0", 0);

        JvmFunObject.tag();
        JvmFunObject.jvmFun("0", 0);
    }
}
