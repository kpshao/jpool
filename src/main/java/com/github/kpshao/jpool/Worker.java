package com.github.kpshao.jpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Runnable {
    private final BlockingQueue<Runnable> taskQueue;
    private volatile boolean isShutdown = false;
    private AtomicInteger completedTasks = new AtomicInteger(0);
    private boolean isRunning = false;
    private boolean isFinished = false;

    public Worker(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    // The run method should fetch tasks from the queue and execute them.
    public void run() {
        while (!isShutdown || !taskQueue.isEmpty()) {
            try {
                Runnable task = taskQueue.take();
                isRunning = true;
                task.run();
                isRunning = false;
                completedTasks.incrementAndGet();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        isFinished = true;
    }

    // The getCompletedTasks method should return the number of tasks completed by this worker.
    public int getCompletedTasks() {
        return completedTasks.get();
    }

    // The isRunning method should return whether this worker is currently running a task.
    public boolean isRunning() {
        return isRunning;
    }

    // The isFinished method should return whether this worker has finished.
    public boolean isFinished() {
        return isFinished;
    }

    // The shutdown method should set the shutdown flag.
    public void shutdown() {
        isShutdown = true;
    }
}