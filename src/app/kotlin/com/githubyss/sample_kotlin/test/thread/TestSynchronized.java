package com.githubyss.sample_kotlin.test.thread;

import com.githubyss.sample_kotlin.util.UtilsKt;


/**
 * TestSynchronized
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/27 00:38:23
 */
public class TestSynchronized {
    public static void synchronize() {
        threadStart();
    }

    private static void threadStart() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                UtilsKt.println("直接使用 Thread。", "");
                UtilsKt.println("Thread run.", "");
                UtilsKt.println();
            }
        };
        thread.start();
    }
}
