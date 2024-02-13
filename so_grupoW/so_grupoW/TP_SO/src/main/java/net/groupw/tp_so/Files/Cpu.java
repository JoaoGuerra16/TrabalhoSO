package net.groupw.tp_so.Files;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class Cpu {
    private final Object lock = new Object();
    private Queue<Runnable> taskQueue;
    private List<Middleware> middlewareThreads;
    private Mem memory;
    private Middleware middleware;

    private int middlewareThreadPoolSize;


    public Cpu(int threadPoolSize, Mem memory, int middlewareThreadPoolSize) {
        this.memory = memory;
        taskQueue = new LinkedList<>();
        initializeWorkerThreads(threadPoolSize);
        this.middlewareThreadPoolSize = middlewareThreadPoolSize;
    }

    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
        initializeMiddlewareThreads(middlewareThreadPoolSize);
    }

    private void initializeWorkerThreads(int threadPoolSize) {
        for (int i = 0; i < threadPoolSize; i++) {
            WorkerThread thread = new WorkerThread(this);
            thread.start();
        }
    }

    private void initializeMiddlewareThreads(int threadPoolSize) {
        middlewareThreads = new ArrayList<>();

        for (int i = 0; i < threadPoolSize; i++) {
            Thread thread = new Thread(middleware);
            thread.setName("[Middleware_Thread "+ i +" ]");
            thread.start();
            middleware.startMiddlewareThread();
        }
    }

    public void executeTask(Runnable task) {
        synchronized (lock) {
            taskQueue.offer(task);
            lock.notify(); // Notify a waiting thread that a task is available
        }
    }

    public Runnable getTask() throws InterruptedException {
        synchronized (lock) {
            while (taskQueue.isEmpty()) {
                lock.wait(); // Wait until a task is available
            }
            return taskQueue.poll();
        }
    }

}
