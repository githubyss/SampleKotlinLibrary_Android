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
        UtilsKt.printlnPost("调测多线程", "");

        threadNormal();
        threadCustomClass();
        threadRunnable();
        threadFactory();
        executor();
        callable();
    }

    private static void threadNormal() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                UtilsKt.println("run() >> 单独使用 Thread（直接实例化 Thread）。", "");
                UtilsKt.println("threadNormal 1", "");
                UtilsKt.println("threadNormal 2", "");
                UtilsKt.println("threadNormal 3", "");
                UtilsKt.println("threadNormal 4", "");
                UtilsKt.println("threadNormal 5", "");
                UtilsKt.println();
            }
        };
        thread.start();
    }

    private static void threadCustomClass() {
        CustomThread thread = new CustomThread();
        thread.start();
    }

    private static class CustomThread extends Thread {
        @Override
        public void run() {
            UtilsKt.println("run() >> 单独使用 Thread（使用自定义 Thread 类）。", "");
            UtilsKt.println("threadCustomClass 1", "");
            UtilsKt.println("threadCustomClass 2", "");
            UtilsKt.println("threadCustomClass 3", "");
            UtilsKt.println("threadCustomClass 4", "");
            UtilsKt.println("threadCustomClass 5", "");
            UtilsKt.println();
        }
    }

    private static void threadRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                UtilsKt.println("run() >> 使用 Runnable，达到重用运行代码的目的。", "");
                UtilsKt.println("threadRunnable 1", "");
                UtilsKt.println("threadRunnable 2", "");
                UtilsKt.println("threadRunnable 3", "");
                UtilsKt.println("threadRunnable 4", "");
                UtilsKt.println("threadRunnable 5", "");
                UtilsKt.println();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void threadFactory() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                UtilsKt.println(Thread.currentThread().getName() + " >>> run() >> 使用 ThreadFactory。", "");
                UtilsKt.println(Thread.currentThread().getName() + " threadFactory 1", "");
                UtilsKt.println(Thread.currentThread().getName() + " threadFactory 2", "");
                UtilsKt.println(Thread.currentThread().getName() + " threadFactory 3", "");
                UtilsKt.println(Thread.currentThread().getName() + " threadFactory 4", "");
                UtilsKt.println(Thread.currentThread().getName() + " threadFactory 5", "");
                UtilsKt.println();
            }
        };

        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                // 使用工厂方法模式，对新线程的创建进行初始化。
                return new Thread(r, "Thread-" + count.incrementAndGet());
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

        Runnable cachedThreadPoolRunnable = new Runnable() {
            @Override
            public void run() {
                UtilsKt.println("run() >> 使用 Executor。", "");
                UtilsKt.println("executor cachedThreadPoolRunnable 1", "");
                UtilsKt.println("executor cachedThreadPoolRunnable 2", "");
                UtilsKt.println("executor cachedThreadPoolRunnable 3", "");
                UtilsKt.println("executor cachedThreadPoolRunnable 4", "");
                UtilsKt.println("executor cachedThreadPoolRunnable 5", "");
                UtilsKt.println();
            }
        };

        // 常用的线程池。默认0个，上限最大（相当于无限），闲置60秒回收。
        Executor cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(cachedThreadPoolRunnable);
        cachedThreadPool.execute(cachedThreadPoolRunnable);
        cachedThreadPool.execute(cachedThreadPoolRunnable);


        Runnable runnableProcessImage = new Runnable() {
            @Override
            public void run() {
                UtilsKt.println("run() >> 处理图片。", "");
                UtilsKt.println("executor runnableProcessImage 1", "");
                UtilsKt.println("executor runnableProcessImage 2", "");
                UtilsKt.println("executor runnableProcessImage 3", "");
                UtilsKt.println("executor runnableProcessImage 4", "");
                UtilsKt.println("executor runnableProcessImage 5", "");
                UtilsKt.println();
            }
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        List<Bitmap> bitmaps = new ArrayList<>(10);
        for (Bitmap bitmap : bitmaps) {
            fixedThreadPool.execute(runnableProcessImage);
        }
        fixedThreadPool.shutdown();


        ExecutorService mainExecutor = new ThreadPoolExecutor(5, 100, 5, TimeUnit.MINUTES, new SynchronousQueue<>());
        mainExecutor.execute(runnableProcessImage);
    }

    private static void callable() {
        // Callable 与 Runnable 相比，它的 call() 函数是有返回值的。
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    UtilsKt.println("call() >> 使用 Callable，达到重用运行代码的目的。", "");
                    UtilsKt.println("Thread with Callable call.", "");
                    Thread.sleep(0);
                    UtilsKt.println();
                }
                catch (InterruptedException e) {
                    UtilsKt.println(e.getMessage(), "");
                }
                return "Done!";
            }
        };

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Future<String> future = cachedThreadPool.submit(callable);
        // 循环等待 callable 给 future 返回值。
        while (true) {
            // 在等待 callable 给 future 返回值的期间，做些其他事情。
            // UtilsKt.println("Do something else.");

            // 判断 Callable 是否完成（future 是否拿到返回值）。
            boolean isCallableDone = future.isDone();
            if (isCallableDone) {
                try {
                    // 获取 callable 的返回值。
                    // 如果此时 callable 还没有运行结束，则会阻塞，等待返回值。
                    // 所以可以放在 while 死循环中，通过 future.isDone() 获取 callable 的运行完成状态，在等待期间做一些其他事情，直到 callable 运行结束。
                    String result = future.get();
                    UtilsKt.println("result: " + result, "");
                }
                catch (InterruptedException | ExecutionException e) {
                    UtilsKt.println(e.getMessage(), "");
                }
                // 退出循环
                break;
            }
        }
        cachedThreadPool.shutdown();
    }
}
