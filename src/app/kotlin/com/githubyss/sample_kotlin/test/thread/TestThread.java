package com.githubyss.sample_kotlin.test.thread;

import android.graphics.Bitmap;

import com.githubyss.sample_kotlin.util.UtilsKt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * TestThread
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/26 18:51:29
 */
public class TestThread {
    public static void thread() {
        threadStart();
        threadRunnable();
        threadFactory();
        executor();
        callable();
    }

    private static void threadStart() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                UtilsKt.println("直接使用 Thread。");
                UtilsKt.println("Thread run.");
                UtilsKt.println();
            }
        };
        thread.start();
    }

    private static void threadRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                UtilsKt.println("使用 Runnable，达到重用运行代码的目的。");
                UtilsKt.println("Thread with Runnable run.");
                UtilsKt.println();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void threadFactory() {
        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                // 使用工厂方法模式，对新线程的创建进行初始化。
                return new Thread(r, "Thread-" + count.incrementAndGet());
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                UtilsKt.println("使用 ThreadFactory。");
                UtilsKt.println(Thread.currentThread().getName() + " run.");
                UtilsKt.println();
            }
        };

        Thread thread1 = threadFactory.newThread(runnable);
        thread1.start();
        Thread thread2 = threadFactory.newThread(runnable);
        thread2.start();
    }

    private static void executor() {
        // 常用的线程池。默认0个，上限最大（相当于无限），闲置60秒回收。
        Executors.newCachedThreadPool();
        // 单个线程的线程池。应用场景：
        Executors.newSingleThreadExecutor();
        // 指定固定线程数的线程池，初始固定，上限固定。应用场景：集中处理多个瞬时爆发的任务。
        Executors.newFixedThreadPool(10);
        //
        Executors.newScheduledThreadPool(10);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                UtilsKt.println("使用 Executor。");
                UtilsKt.println("Thread with Runnable run.");
                UtilsKt.println();
            }
        };

        // 常用的线程池。默认0个，上限最大（相当于无限），闲置60秒回收。
        Executor cachedThreadPool = Executors.newCachedThreadPool();

        cachedThreadPool.execute(runnable);
        cachedThreadPool.execute(runnable);
        cachedThreadPool.execute(runnable);


        Runnable runnableProcessImage = new Runnable() {
            @Override
            public void run() {
                UtilsKt.println("处理图片。");
            }
        };

        List<Bitmap> bitmaps = new ArrayList<>(10);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        for (Bitmap bitmap : bitmaps) {
            fixedThreadPool.execute(runnableProcessImage);
        }
        fixedThreadPool.shutdown();


        ExecutorService mainExecutor = new ThreadPoolExecutor(5, 100, 5, TimeUnit.MINUTES, new SynchronousQueue<>());
        mainExecutor.execute(runnable);
    }

    private static void callable() {
        // Callable 与 Runnable 相比，它的 call() 函数是有返回值的。
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    UtilsKt.println("使用 Callable，达到重用运行代码的目的。");
                    UtilsKt.println("Thread with Callable call.");
                    Thread.sleep(1500);
                    UtilsKt.println();
                }
                catch (InterruptedException e) {
                    UtilsKt.println(e.getMessage());
                }
                return "Done!";
            }
        };

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Future<String> future = cachedThreadPool.submit(callable);
        // 循环等待 callable 给 future 返回值。
        while (true) {
            // 在等待 callable 给 future 返回值的期间，做些其他事情。
            UtilsKt.println("Do something else.");

            // 判断 Callable 是否完成（future 是否拿到返回值）。
            boolean isCallableDone = future.isDone();
            if (isCallableDone) {
                try {
                    // 获取 callable 的返回值。
                    // 如果此时 callable 还没有运行结束，则会阻塞，等待返回值。
                    // 所以可以放在 while 死循环中，通过 future.isDone() 获取 callable 的运行完成状态，在等待期间做一些其他事情，直到 callable 运行结束。
                    String result = future.get();
                    UtilsKt.println("result: " + result);
                }
                catch (InterruptedException | ExecutionException e) {
                    UtilsKt.println(e.getMessage());
                }
                // 退出循环
                break;
            }
        }
        cachedThreadPool.shutdown();
    }
}
