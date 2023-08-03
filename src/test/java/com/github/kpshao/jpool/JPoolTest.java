package com.github.kpshao.jpool;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class JPoolTest {
    @Test
    public void testJPool() throws InterruptedException {
        JPool pool = new JPool(5);

        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        pool.shutdown();

        assertEquals(10, pool.getCompletedTasks());
        assertEquals(0, pool.getWaitingTasks());
        assertEquals(0, pool.getRunningTasks());
    }

    @Test
    public void testPerformance() throws InterruptedException {
        int numTasks = 100000;
        JPool pool = new JPool(100);

        long start = System.currentTimeMillis();
        for (int i = 0; i < numTasks; i++) {
            pool.submit(() -> {
                // Simulate a simple task by sleeping for 1 millisecond
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        pool.shutdown();
        long end = System.currentTimeMillis();

        System.out.println("JPool completed " + numTasks + " tasks in " + (end - start) + " milliseconds");
    }

    @Test
    public void testJavaThreadPoolPerformance() throws InterruptedException {
        int numTasks = 100000;
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        long start = System.currentTimeMillis();
        for (int i = 0; i < numTasks; i++) {
            executorService.submit(() -> {
                // Simulate a simple task by sleeping for 1 millisecond
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        long end = System.currentTimeMillis();

        System.out.println("Java ThreadPool completed " + numTasks + " tasks in " + (end - start) + " milliseconds");
    }
}