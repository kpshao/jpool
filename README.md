# JPool

JPool is a high-performance, feature-rich, and easy-to-use worker pool program written in Java.

## Features

- **Task Queue**: JPool has a task queue for storing tasks to be processed. This queue is thread-safe, allowing multiple workers to fetch tasks concurrently.

- **Thread Pool**: JPool has a thread pool, each thread in the pool can fetch tasks from the task queue and execute them. The size of the thread pool is configurable, allowing for optimization based on the system's hardware resources and the nature of the tasks.

- **Load Balancing**: JPool can automatically adjust the distribution of tasks based on the load of each worker. For example, if a worker is processing a time-consuming task, new tasks should be assigned to other workers.

- **Error Handling**: JPool can handle errors that may occur when workers execute tasks. For example, if a task fails, JPool can choose to retry the task or remove it from the task queue.

- **Graceful Shutdown**: JPool provides a method for gracefully shutting down all workers. This means that when JPool is shut down, all tasks being executed should be completed, and new tasks should not be accepted.

- **Monitoring and Logging**: JPool provides a method for monitoring the status and performance of each worker. In addition, JPool logs detailed information for debugging and performance optimization.

- **Task Priority**: JPool supports task priority, allowing important tasks to be executed first.

- **Task Timeout**: JPool supports task timeout settings. If a task is not completed within a specified time, there should be a corresponding handling mechanism.

- **Flexible Task Scheduling**: JPool supports the scheduling of timed tasks and periodic tasks.

- **Resource Limiting**: JPool can limit the resources used by each worker, such as CPU, memory, etc.

## License

JPool is open-sourced software licensed under the [MIT license](LICENSE).