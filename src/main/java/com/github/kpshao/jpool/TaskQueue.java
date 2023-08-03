package com.github.kpshao.jpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    // The enqueue method should add a task to the queue.
    public void enqueue(Runnable task) throws InterruptedException {
        queue.put(task);
    }

    // The dequeue method should remove and return a task from the queue.
    public Runnable dequeue() throws InterruptedException {
        return queue.take();
    }
}
