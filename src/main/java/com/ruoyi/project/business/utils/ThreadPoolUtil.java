package com.ruoyi.project.business.utils;

import java.util.concurrent.*;
/**
 * @ClassName: ThreadPoolUtil
 * @Description:
 * @Author: zhangwk
 * @Date: 2025-04-10 18:28
 * @Version: 1.0
 **/
public class ThreadPoolUtil {

    private static final int CORE_POOL_SIZE = 2;

    private static final int MAXIMUM_POOL_SIZE = 4;

    private static final long KEEP_ALIVE_TIME = 3600L;

    // 创建一个固定大小的线程池
    public static ExecutorService createFixedThreadPool() {
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE, // 核心线程数
                MAXIMUM_POOL_SIZE, // 最大线程数
                KEEP_ALIVE_TIME, // 非核心线程空闲存活时间
                TimeUnit.MILLISECONDS, // 时间单位
                new LinkedBlockingQueue<Runnable>(), // 任务队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );
    }

    // 创建一个缓存线程池
    public static ExecutorService createCachedThreadPool() {
        return new ThreadPoolExecutor(
                CORE_POOL_SIZE, // 核心线程数
                MAXIMUM_POOL_SIZE, // 最大线程数
                KEEP_ALIVE_TIME, // 非核心线程空闲存活时间
                TimeUnit.SECONDS, // 时间单位
                new SynchronousQueue<Runnable>(), // 任务队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );
    }

    // 创建一个单线程线程池
    public static ExecutorService createSingleThreadExecutor() {
        return new ThreadPoolExecutor(
                1, // 核心线程数
                1, // 最大线程数
                KEEP_ALIVE_TIME, // 非核心线程空闲存活时间
                TimeUnit.MILLISECONDS, // 时间单位
                new LinkedBlockingQueue<Runnable>(), // 任务队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );
    }

    // 关闭线程池
    public static void shutdownExecutorService(ExecutorService executorService) {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown(); // 停止接收新任务，但已提交的任务会继续执行
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow(); // 强制关闭线程池
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow(); // 强制关闭线程池
                Thread.currentThread().interrupt(); // 恢复中断状态
            }
        }
    }

}
