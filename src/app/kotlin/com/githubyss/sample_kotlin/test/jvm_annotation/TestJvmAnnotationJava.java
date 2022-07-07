package com.githubyss.sample_kotlin.test.jvm_annotation;


public class TestJvmAnnotationJava {
    public static void jvmAnnotation() {
        JvmFunFile.jvmFun("0");
        JvmFunFile.jvmFun("0", 0);

        JvmFunObject.tag();
        JvmFunObject.jvmFun("0", 0);
    }
}
