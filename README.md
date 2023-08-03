
Based on the current implementation of JPool, here is the updated README:

```markdown
# JPool

JPool is a simple, lightweight, and easy-to-use worker pool implementation in Java.

## Features

- **Task Queue**: JPool uses a `BlockingQueue` to store tasks to be processed. This queue is thread-safe, allowing multiple workers to fetch tasks concurrently.

- **Worker Pool**: JPool maintains a pool of worker threads. Each worker can fetch tasks from the task queue and execute them. The size of the worker pool is configurable at the time of JPool creation.

- **Task Submission**: Tasks can be submitted to JPool using the `submit` method. If JPool has been shut down, an `IllegalStateException` will be thrown.

- **Graceful Shutdown**: JPool provides a `shutdown` method for gracefully shutting down the worker pool. This method will wait for all workers to finish their current tasks before returning.

- **Task Monitoring**: JPool provides several methods for monitoring the status of tasks:
  - `getCompletedTasks`: Returns the total number of tasks completed by all workers.
  - `getWaitingTasks`: Returns the number of tasks waiting in the queue.
  - `getRunningTasks`: Returns the number of tasks currently being run by workers.

## Usage

To use JPool, first create a new `JPool` instance with the desired number of workers. Then, submit tasks to the pool using the `submit` method. When all tasks are finished, call the `shutdown` method to gracefully shut down the worker pool.

Here is a simple example:

```java
// Create a new JPool with 4 workers
JPool pool = new JPool(4);

// Submit tasks to the pool
for (int i = 0; i < 10; i++) {
    pool.submit(() -> {
        // This is the task to be executed
        System.out.println("Task executed by " + Thread.currentThread().getName());
    });
}

// Gracefully shut down the pool
pool.shutdown();
```
In this example, we create a new JPool with 4 workers. We then submit 10 tasks to the pool. Each task simply prints a message to the console. Finally, we call the shutdown method to wait for all tasks to finish and shut down the pool.

## Performance

JPool is designed to be lightweight and efficient. However, the performance may vary depending on the nature of the tasks and the hardware resources available. It is recommended to adjust the size of the worker pool and the type of the task queue based on the specific use case.

## License

JPool is open-sourced software licensed under the [MIT license](LICENSE).
```

This README provides a clear and concise description of JPool's features and usage. It also includes a note on performance and a link to the license.
