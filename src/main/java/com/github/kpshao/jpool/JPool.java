package com.github.kpshao.jpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class JPool {
    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private final List<Worker> workers = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();
    private volatile boolean isShutdown = false;

    public JPool(int numWorkers) {
        for (int i = 0; i < numWorkers; i++) {
            Worker worker = new Worker(taskQueue);
            workers.add(worker);
            Thread thread = new Thread(worker);
            threads.add(thread);
            thread.start();
        }
    }

    // The submit method should submit a task to the queue.
    public void submit(Runnable task) throws InterruptedException {
        if (!isShutdown) {
            taskQueue.put(task);
        } else {
            throw new IllegalStateException("Cannot add tasks to a shutdown JPool");
        }
    }

    // The shutdown method should gracefully shut down the Worker Pool.
    public void shutdown() throws InterruptedException {
        isShutdown = true;
        for (Worker worker : workers) {
            worker.shutdown();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    // The getCompletedTasks method should return the total number of tasks completed by all workers.
    public int getCompletedTasks() {
        int totalCompletedTasks = 0;
        for (Worker worker : workers) {
            totalCompletedTasks += worker.getCompletedTasks();
        }
        return totalCompletedTasks;
    }

    // The getWaitingTasks method should return the number of tasks waiting in the queue.
    public int getWaitingTasks() {
        return taskQueue.size();
    }

    // The getRunningTasks method should return the number of tasks currently being run by workers.
    public int getRunningTasks() {
        int runningTasks = 0;
        for (Worker worker : workers) {
            if (worker.isRunning()) {
                runningTasks++;
            }
        }
        return runningTasks;
    }
}