package com.lecshop.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dujinkai on 17/6/20.
 * 线程池
 */
public class ThreadTask {

    private static final ThreadTask threadTask = new ThreadTask();

    private ThreadTask() {
    }

    public static ThreadTask getInstance() {
        return threadTask;
    }

    /**
     * 固定线程池
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 加入线程池执行
     *
     * @param runnable 执行线程
     */
    public void addTask(Runnable runnable) {
        executorService.execute(runnable);
    }
}