# Java - Multithreading Questions & Answers

---

## 1. What is a thread in Java?

A thread in Java is a **lightweight process** — a separate path of execution within a program. It allows **concurrent execution of tasks**, enabling a program to perform multiple operations simultaneously.

---

## 2. How can you create a thread in Java?

There are **two main ways** to create a thread:

**Method 1 — Extending the `Thread` class:**

```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

MyThread t = new MyThread();
t.start();
```

**Method 2 — Implementing the `Runnable` interface:**

```java
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable running: " + Thread.currentThread().getName());
    }
}

Thread t = new Thread(new MyTask());
t.start();

// Or with a lambda (Java 8+)
Thread t2 = new Thread(() -> System.out.println("Lambda thread"));
t2.start();
```

---

## 3. What is the `Runnable` interface?

The `Runnable` interface represents a **task that can be executed by a thread**. It has a single abstract method `run()` that defines the task to be executed.

```java
@FunctionalInterface
public interface Runnable {
    void run();
}
```

---

## 4. What is the difference between extending `Thread` and implementing `Runnable`?

| Feature | `extends Thread` | `implements Runnable` |
|---------|-----------------|----------------------|
| **Multiple inheritance** | ❌ Cannot extend other classes. | ✅ Can extend another class. |
| **Object-oriented design** | Less cohesive — mixes task and thread. | ✅ Better — separates task from execution. |
| **Reusability** | Less reusable — tied to `Thread`. | ✅ More reusable — task can be passed to any executor. |
| **Preferred** | Less preferred. | ✅ Generally preferred. |

---

## 5. What is the `start()` method in Java threads?

The `start()` method **begins thread execution**. The JVM internally calls the `run()` method on a new thread. Calling `run()` directly does **not** start a new thread — it executes on the current thread.

```java
Thread t = new Thread(() -> System.out.println("New thread: " + Thread.currentThread().getName()));

t.run();   // NOT a new thread — runs on main thread
t.start(); // Starts a new thread — JVM calls run() on the new thread
```

---

## 6. What is the `join()` method in Java threads?

The `join()` method causes the **current thread to wait** until the specified thread finishes its execution.

```java
Thread t = new Thread(() -> {
    System.out.println("Worker thread running...");
    try { Thread.sleep(2000); } catch (InterruptedException e) { }
    System.out.println("Worker thread done.");
});

t.start();
t.join(); // Main thread waits for t to finish
System.out.println("Main continues after worker done.");
```

---

## 7. What is thread synchronization?

Thread synchronization is the process of **controlling access to shared resources** by multiple threads to prevent data inconsistency or corruption. It ensures only one thread accesses a critical section at a time.

```java
class Counter {
    private int count = 0;

    public synchronized void increment() { // Only one thread at a time
        count++;
    }

    public int getCount() { return count; }
}
```

---

## 8. What is deadlock in Java? How can it be avoided?

**Deadlock** occurs when two or more threads are blocked forever, each waiting for a resource held by the other.

```java
// Deadlock scenario
synchronized (lock1) {
    synchronized (lock2) { /* Thread 1 */ }
}

synchronized (lock2) {
    synchronized (lock1) { /* Thread 2 — deadlock! */ }
}
```

**Avoidance strategies:**

| Strategy | Description |
|----------|-------------|
| **Avoid nested locks** | Don't acquire a lock while holding another. |
| **Consistent lock ordering** | Always acquire locks in the same order across all threads. |
| **Lock timeouts** | Use `tryLock(timeout)` to avoid indefinite waiting. |
| **Higher-level utilities** | Use `java.util.concurrent` instead of manual `synchronized`. |

---

## 9. What are the different states of a thread?

| State | Description |
|-------|-------------|
| **New** | Thread created but `start()` not yet called. |
| **Runnable** | Thread is ready to run or running. |
| **Blocked** | Waiting to acquire a monitor lock. |
| **Waiting** | Waiting indefinitely for another thread (e.g., `wait()`). |
| **Timed Waiting** | Waiting for a specified time (e.g., `sleep()`, `join(timeout)`). |
| **Terminated** | Thread has finished execution. |

---

## 10. What is the difference between a process and a thread?

| Feature | Process | Thread |
|---------|---------|--------|
| **Definition** | Independent program execution environment. | Unit of execution within a process. |
| **Weight** | Heavyweight — own memory space. | Lightweight — shares process memory. |
| **Resources** | Own separate resources. | Shares resources with other threads. |
| **Communication** | Inter-process communication (IPC) — complex. | Direct shared memory — simpler. |
| **Creation overhead** | High. | Low. |

---

## 11. What is the difference between `start()` and `run()` methods?

| Method | Behavior |
|--------|----------|
| `start()` | Creates a **new thread** and calls `run()` on it — actual multithreading. |
| `run()` | Executes the thread's code on the **current thread** — no new thread created. |

```java
Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));

t.run();   // Output: main (runs on main thread)
t.start(); // Output: Thread-0 (runs on new thread)
```

---

## 12. What are daemon threads?

Daemon threads run **in the background**, providing services to other threads. They automatically terminate when all **non-daemon (user) threads** finish.

```java
Thread daemon = new Thread(() -> {
    while (true) { System.out.println("Daemon running..."); }
});
daemon.setDaemon(true); // Must be set BEFORE start()
daemon.start();

// When main thread ends, daemon thread automatically terminates
```

**Examples:** Garbage collector, monitoring threads, JVM background services.

---

## 13. What is synchronization?

Synchronization is the process of **coordinating access to shared resources** by multiple threads to prevent **race conditions** and **data corruption**.

---

## 14. What are different ways to achieve synchronization in Java?

| Mechanism | Description |
|-----------|-------------|
| **`synchronized` method** | Locks on the object instance when called. |
| **`synchronized` block** | Locks on a specific object within a method. |
| **`ReentrantLock`** | Explicit lock with try-lock, timeout, fairness support. |
| **`volatile` keyword** | Ensures visibility of variable changes across threads. |
| **Atomic classes** | `AtomicInteger`, `AtomicBoolean` — lock-free thread safety. |
| **`java.util.concurrent` utilities** | `CountDownLatch`, `Semaphore`, `CyclicBarrier`, etc. |

```java
// Synchronized method
public synchronized void increment() { count++; }

// Synchronized block
public void increment() {
    synchronized (this) { count++; }
}

// ReentrantLock
Lock lock = new ReentrantLock();
lock.lock();
try { count++; }
finally { lock.unlock(); }

// Atomic
AtomicInteger atomicCount = new AtomicInteger(0);
atomicCount.incrementAndGet();
```

---

## 15. What is a thread pool?

A thread pool is a **collection of pre-initialized threads** ready to perform tasks. Instead of creating a new thread for each task, thread pools **reuse existing threads**, reducing creation/destruction overhead.

```java
// Fixed thread pool with 4 threads
ExecutorService pool = Executors.newFixedThreadPool(4);

for (int i = 0; i < 10; i++) {
    int taskId = i;
    pool.submit(() -> System.out.println("Task " + taskId + " on " + Thread.currentThread().getName()));
}

pool.shutdown(); // Stop accepting new tasks, finish pending ones
```

---

## 16. What are the benefits of using multithreading?

| Benefit | Description |
|---------|-------------|
| **Improved performance** | Multiple tasks run concurrently, especially on multi-core CPUs. |
| **Enhanced responsiveness** | Applications stay interactive during long-running tasks. |
| **Resource sharing** | Threads share memory and file handles efficiently. |
| **Modular design** | Complex tasks are broken into smaller, manageable units. |
| **Parallel execution** | Reduces overall execution time for intensive operations. |
| **Better scalability** | Applications scale with hardware improvements. |
| **Asynchronous programming** | Handles events and I/O operations efficiently. |

---

## 17. Explain `wait()`, `notify()`, and `notifyAll()` methods.

| Method | Description |
|--------|-------------|
| `wait()` | Causes the current thread to wait until `notify()` or `notifyAll()` is called on the same object. |
| `notify()` | Wakes up **one** arbitrary thread waiting on the object's monitor. |
| `notifyAll()` | Wakes up **all** threads waiting on the object's monitor — they then compete for the lock. |

> **Important:** These methods must be called within a `synchronized` block, otherwise `IllegalMonitorStateException` is thrown.

```java
class SharedQueue {
    private int value;
    private boolean hasValue = false;

    public synchronized void produce(int v) throws InterruptedException {
        while (hasValue) wait();       // Wait until consumed
        value = v;
        hasValue = true;
        notify();                       // Wake up consumer
    }

    public synchronized int consume() throws InterruptedException {
        while (!hasValue) wait();      // Wait until produced
        hasValue = false;
        notify();                       // Wake up producer
        return value;
    }
}
```

---

## 18. Explain thread starvation and how it can be mitigated.

**Thread starvation** occurs when a thread is **unable to gain access to CPU time or resources** because other threads are consistently given priority.

**Mitigation strategies:**
- Use **`ReentrantLock` with `fairness = true`** — ensures the longest-waiting thread gets the lock first.
- Use **bounded thread pools** with proper queue management.
- Avoid excessively high priority for specific threads.
- Use `java.util.concurrent` utilities that provide fair scheduling.

```java
// Fair lock — longest-waiting thread gets access first
Lock fairLock = new ReentrantLock(true); // fairness = true
```

---

## 19. Explain thread-local variables and when they are useful.

**Thread-local variables** are variables where each thread has its **own independent copy** — changes in one thread don't affect others.

```java
ThreadLocal<Integer> threadLocalId = ThreadLocal.withInitial(() -> 0);

Runnable task = () -> {
    threadLocalId.set((int)(Math.random() * 100));
    System.out.println(Thread.currentThread().getName() + " ID: " + threadLocalId.get());
};

new Thread(task).start(); // Each thread has its own value
new Thread(task).start();
```

**Use cases:**
- User sessions in web applications.
- Database connection context per thread.
- Transaction context in connection pools.

---

## 20. What are potential pitfalls of multithreading, and how can they be mitigated?

| Pitfall | Description | Mitigation |
|---------|-------------|------------|
| **Deadlock** | Threads wait for each other's resources forever. | Consistent lock ordering, timeouts. |
| **Race conditions** | Multiple threads modify shared data simultaneously. | Proper synchronization, atomic variables. |
| **Excessive thread creation** | Too many threads exhaust system resources. | Use thread pools. |
| **Memory visibility issues** | Changes not visible across threads. | Use `volatile` or synchronization. |
| **Livelock** | Threads actively respond to each other without progress. | Design proper retry logic. |
| **Thread starvation** | Low-priority threads never execute. | Fair locks, bounded pools. |

---

## 21. What is the purpose of the `volatile` keyword in Java?

The `volatile` keyword ensures that a variable's value is **always read from and written to main memory**, making changes immediately visible to all threads.

```java
class StopFlag {
    private volatile boolean running = true; // Without volatile, changes may not be visible

    public void stop() { running = false; }

    public void run() {
        while (running) { // Each thread reads the latest value
            // do work
        }
        System.out.println("Thread stopped.");
    }
}
```

> **Note:** `volatile` ensures **visibility** but not **atomicity** — use `AtomicInteger` or `synchronized` for compound operations like `i++`.

---

## 22. Deadlock vs. Livelock

| Feature | Deadlock | Livelock |
|---------|----------|----------|
| **Thread state** | Threads are **blocked** — waiting indefinitely. | Threads are **active** — continuously changing state. |
| **Progress** | None — threads are stuck completely. | None — threads respond to each other but don't advance. |
| **Detection** | Easier — threads are blocked. | Harder — threads appear active but make no progress. |
| **Example** | Thread A holds Lock 1, waits for Lock 2; Thread B holds Lock 2, waits for Lock 1. | Two polite people stepping aside for each other repeatedly in a hallway. |
| **Resolution** | Lock ordering, timeouts. | Randomized retry, backoff strategies. |

---

## 23. Intrinsic Locking (`synchronized`) vs. Explicit Locking (`Lock` interface)

| Feature | `synchronized` (Intrinsic) | `Lock` interface (Explicit) |
|---------|---------------------------|----------------------------|
| **Simplicity** | ✅ Simple and concise. | More verbose. |
| **Try-lock** | ❌ Not supported. | ✅ `tryLock()` available. |
| **Lock timeout** | ❌ Not supported. | ✅ `tryLock(timeout)` available. |
| **Interruptible** | ❌ Cannot be interrupted. | ✅ `lockInterruptibly()` available. |
| **Fairness** | ❌ No fairness guarantee. | ✅ `new ReentrantLock(true)` for fair lock. |
| **Condition variables** | `wait()` / `notify()` | ✅ Multiple `Condition` objects. |

```java
// ReentrantLock — explicit locking with try-lock
Lock lock = new ReentrantLock();
if (lock.tryLock(5, TimeUnit.SECONDS)) {
    try {
        // Critical section
    } finally {
        lock.unlock(); // Always unlock in finally
    }
} else {
    System.out.println("Could not acquire lock");
}
```

---

## 24. Benefits and Drawbacks of Thread Pools

**Benefits:**

| Benefit | Description |
|---------|-------------|
| **Improved performance** | Reuses threads — reduces creation/destruction overhead. |
| **Better resource management** | Limits concurrent threads, preventing system overload. |
| **Simplified concurrency** | Abstracts thread management complexity. |

**Drawbacks:**

| Drawback | Description |
|----------|-------------|
| **Resource contention** | Long-running tasks can block pool threads. |
| **Management overhead** | Pool itself consumes memory and CPU. |
| **Tuning complexity** | Optimal pool size and queue configuration require careful tuning. |

---

## 25. Thread-local Variables vs. Shared Variables

| Feature | Thread-local Variables | Shared Variables |
|---------|----------------------|-----------------|
| **Visibility** | Private to each thread — not visible to others. | Accessible to all threads. |
| **Synchronization needed** | ❌ No — each thread has its own copy. | ✅ Yes — must synchronize access. |
| **Use case** | Per-thread state (sessions, transactions). | Common state shared across threads. |
| **Risk** | Memory leaks if not cleaned up (thread pools). | Race conditions if not synchronized. |

---

## 26. `ThreadLocal` vs. `InheritableThreadLocal`

| Feature | `ThreadLocal` | `InheritableThreadLocal` |
|---------|--------------|--------------------------|
| **Data scope** | Private to **each thread** — not inherited. | Inherited by **child threads** from parent. |
| **Use case** | Thread-specific configs or data that should not be shared. | Data that needs to be passed to child threads (e.g., security context). |
| **Inheritance** | ❌ Child threads don't inherit parent's value. | ✅ Child threads inherit parent's value at creation. |

```java
// ThreadLocal — not inherited
ThreadLocal<String> local = new ThreadLocal<>();
local.set("Parent value");
new Thread(() -> System.out.println(local.get())).start(); // null

// InheritableThreadLocal — inherited
InheritableThreadLocal<String> inherited = new InheritableThreadLocal<>();
inherited.set("Parent value");
new Thread(() -> System.out.println(inherited.get())).start(); // "Parent value"
```

---

## Multithreading — Quick Reference

| Concept | Description |
|---------|-------------|
| `Thread.start()` | Creates a new thread and begins execution. |
| `Thread.run()` | Runs on the current thread — not a new thread. |
| `Thread.join()` | Wait for a thread to finish. |
| `Thread.sleep(ms)` | Pause the current thread for specified milliseconds. |
| `synchronized` | Intrinsic lock — one thread at a time. |
| `volatile` | Visibility guarantee across threads. |
| `wait()` / `notify()` | Thread communication within synchronized blocks. |
| `ReentrantLock` | Explicit lock with advanced features. |
| `ThreadLocal` | Per-thread variable storage. |
| `ExecutorService` | Thread pool management. |
| **Deadlock** | Threads blocked forever — waiting for each other. |
| **Livelock** | Threads active but not making progress. |
| **Race condition** | Unsynchronized concurrent access to shared data. |
| **Daemon thread** | Background thread — terminates with last user thread. |

---

*Happy Coding! 🚀*
