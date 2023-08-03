package com.github.kpshao.jpool;

import org.junit.jupiter.api.Test;
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
}