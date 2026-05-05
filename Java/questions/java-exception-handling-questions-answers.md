# Java - Exception Handling Questions & Answers

---

## 1. What is an exception in Java?

An exception in Java is an **event that disrupts the normal flow of a program's execution**. It can occur due to various reasons such as:
- Invalid user input
- Network failure
- Insufficient memory
- File not found
- Array index out of bounds

---

## 2. What are the types of exceptions in Java?

| Type | Description | Checked at | Examples |
|------|-------------|------------|---------|
| **Checked Exceptions** | Must be caught or declared with `throws`. | Compile time | `IOException`, `SQLException` |
| **Unchecked Exceptions** | Also known as runtime exceptions — not required to be caught. | Runtime | `NullPointerException`, `ArrayIndexOutOfBoundsException` |

---

## 3. What is the purpose of the `try-catch` block?

The `try-catch` block handles exceptions:
- Code that may throw an exception goes inside the **`try` block**.
- If an exception occurs, control transfers to the **`catch` block** where it is handled.

```java
try {
    int result = 10 / 0; // ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
}
// Output: Cannot divide by zero: / by zero
```

---

## 4. Can we have multiple `catch` blocks following a single `try` block?

Yes. Multiple `catch` blocks allow different exception types to be handled separately.

```java
try {
    String str = null;
    int[] arr = new int[5];
    System.out.println(str.length()); // NullPointerException
    System.out.println(arr[10]);       // ArrayIndexOutOfBoundsException
} catch (NullPointerException e) {
    System.out.println("Null reference: " + e.getMessage());
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Index out of bounds: " + e.getMessage());
} catch (Exception e) {
    System.out.println("General exception: " + e.getMessage()); // Catch-all
}
```

> **Best Practice:** Order catch blocks from most specific to most general.

---

## 5. What is the purpose of the `finally` block?

The `finally` block **always executes** — whether an exception is thrown or not. It is typically used for **resource cleanup** (closing files, database connections, etc.).

```java
Connection conn = null;
try {
    conn = getConnection();
    // Database operations
} catch (SQLException e) {
    System.out.println("DB Error: " + e.getMessage());
} finally {
    if (conn != null) {
        conn.close(); // Always closes, even if exception occurred
        System.out.println("Connection closed.");
    }
}
```

---

## 6. Explain the difference between checked and unchecked exceptions.

| Feature | Checked Exceptions | Unchecked Exceptions |
|---------|------------------|---------------------|
| **Checked at** | Compile time. | Runtime. |
| **Must handle?** | ✅ Yes — catch or declare with `throws`. | ❌ No — optional. |
| **Superclass** | `Exception` (not `RuntimeException`). | `RuntimeException`. |
| **Cause** | External factors (I/O, network). | Programming errors (null pointer, bad index). |
| **Examples** | `IOException`, `SQLException` | `NullPointerException`, `ClassCastException` |

---

## 7. What is the difference between `throw` and `throws` in Java?

| Feature | `throw` | `throws` |
|---------|---------|----------|
| **Purpose** | Explicitly **throws** an exception instance. | **Declares** that a method may throw exceptions. |
| **Location** | Inside the method body. | In the method signature. |
| **Followed by** | An instance of `Throwable` (e.g., `new Exception()`). | A list of exception types. |

```java
// throws — in method signature
public static void readFile(String path) throws IOException {
    // throw — in method body
    throw new IOException("File not found: " + path);
}
```

---

## 8. How can you create custom exceptions in Java?

Extend `Exception` (for checked) or `RuntimeException` (for unchecked) to create custom exceptions representing application-specific error conditions.

```java
// Custom unchecked exception
public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Custom checked exception
public class InsufficientFundsException extends Exception {
    private double amount;

    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Required: " + amount);
        this.amount = amount;
    }

    public double getAmount() { return amount; }
}

// Usage
public static void setAge(int age) {
    if (age < 0 || age > 150) {
        throw new InvalidAgeException("Invalid age: " + age);
    }
}
```

---

## 9. How do you handle exceptions in a multi-threaded environment?

1. **Try-catch inside `run()`** — wrap the thread's logic in a try-catch block.
2. **Uncaught exception handler** — use `Thread.setDefaultUncaughtExceptionHandler()` for exceptions not caught within the thread.

```java
// Method 1 — try-catch inside run()
Thread thread = new Thread(() -> {
    try {
        int result = 10 / 0; // ArithmeticException
    } catch (ArithmeticException e) {
        System.out.println("Thread caught: " + e.getMessage());
    }
});
thread.start();

// Method 2 — uncaught exception handler
Thread.setDefaultUncaughtExceptionHandler((t, e) ->
    System.out.println("Uncaught in thread " + t.getName() + ": " + e.getMessage()));
```

---

## 10. What are the keywords used in exception handling?

| Keyword | Purpose |
|---------|---------|
| `try` | Encloses code that might throw an exception. |
| `catch` | Handles a specific exception type. |
| `finally` | Executes regardless of whether an exception occurred. |
| `throw` | Explicitly throws an exception. |
| `throws` | Declares exceptions a method may throw. |

---

## 11. Explain the Java Exception Hierarchy.

```
Throwable
├── Error (serious, typically unrecoverable)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
└── Exception
    ├── IOException (checked)
    ├── SQLException (checked)
    ├── ClassNotFoundException (checked)
    └── RuntimeException (unchecked)
        ├── NullPointerException
        ├── ArrayIndexOutOfBoundsException
        ├── ClassCastException
        └── ArithmeticException
```

| Class | Description |
|-------|-------------|
| `Throwable` | Root class for all exceptions and errors. |
| `Error` | Serious, typically unrecoverable system errors. |
| `Exception` | Represents conditions a program can reasonably handle. |
| `RuntimeException` | Unchecked exceptions from programming errors. |

---

## 12. When should you use exception handling?

- Use exception handling to **manage unexpected errors** and prevent your program from crashing.
- **Do not use** exceptions for normal program flow control — exceptions have overhead and reduce readability.

**Good use:**
```java
try {
    int value = Integer.parseInt(userInput); // May throw NumberFormatException
} catch (NumberFormatException e) {
    System.out.println("Please enter a valid number.");
}
```

**Bad use (avoid):**
```java
// Don't use exceptions for expected flow control
try {
    list.get(index); // Don't use IndexOutOfBoundsException to check bounds
} catch (IndexOutOfBoundsException e) {
    // Should check if (index < list.size()) instead
}
```

---

## 13. How can you improve your exception handling code?

| Best Practice | Description |
|---------------|-------------|
| **Use specific catch blocks** | Catch specific exception types rather than catching `Exception` broadly. |
| **Never leave catch empty** | Always log, re-throw, or handle the exception. Empty catch blocks hide problems. |
| **Use try-with-resources** | Automatically closes `AutoCloseable` resources — cleaner than `finally`. |
| **Provide meaningful messages** | Include context in exception messages to aid debugging. |
| **Don't catch `Error`** | `OutOfMemoryError` and similar errors should not be caught. |

```java
// BAD — empty catch block
try {
    riskyOperation();
} catch (Exception e) { } // Silently swallows the error!

// GOOD — log and handle
try {
    riskyOperation();
} catch (IOException e) {
    logger.error("IO operation failed", e);
    throw new ServiceException("Operation failed", e);
}
```

---

## 14. Can a method have both `throws` and `throw` keywords?

Yes. `throws` is used in the method signature to declare exceptions, while `throw` is used in the method body to actually throw an exception.

```java
public static void validateAge(int age) throws InvalidAgeException {
    if (age < 0) {
        throw new InvalidAgeException("Age cannot be negative: " + age); // throw in body
    }
    System.out.println("Valid age: " + age);
}
```

---

## 15. What happens if an exception is not caught anywhere in the code?

If an exception propagates all the way up the call stack without being caught, the program **terminates abruptly**. The JVM prints the exception type, message, and stack trace to the console.

```
Exception in thread "main" java.lang.ArithmeticException: / by zero
    at Main.divide(Main.java:5)
    at Main.main(Main.java:10)
```

---

## 16. Explain the difference between `finally` block and `finalize()` method.

| Feature | `finally` block | `finalize()` method |
|---------|----------------|---------------------|
| **Type** | Part of `try-catch` exception handling. | Method of `Object` class. |
| **Called by** | JVM — automatically after `try-catch`. | Garbage Collector — before object is GC'd. |
| **Purpose** | Resource cleanup (close files, connections). | Object cleanup before destruction. |
| **Reliability** | ✅ Always executes (with few exceptions). | ⚠️ Not guaranteed to be called (deprecated in Java 9). |
| **Control** | ✅ Developer controls what runs. | ❌ GC decides when (if ever) it runs. |

---

## 17. Can you catch an exception thrown in a `finally` block?

Yes. However, if a `finally` block throws an exception and it is **not caught within that block**, it **overrides any exception** thrown in the corresponding `try` or `catch` block.

```java
try {
    throw new RuntimeException("From try");
} finally {
    throw new RuntimeException("From finally"); // This overrides the try exception!
}
// Only "From finally" is propagated — "From try" is lost
```

> **Best Practice:** Avoid throwing exceptions from `finally` blocks to prevent masking the original exception.

---

## 18. What is the purpose of `try-with-resources` (introduced in Java 7)?

`try-with-resources` ensures that resources implementing `AutoCloseable` are **automatically closed** after the `try` block finishes, even if an exception occurs. This eliminates the need for explicit cleanup in `finally`.

```java
// Traditional approach — explicit close in finally
FileReader fr = null;
try {
    fr = new FileReader("file.txt");
    // read file
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (fr != null) fr.close(); // Explicit cleanup
}

// try-with-resources — automatic close
try (FileReader fr = new FileReader("file.txt");
     BufferedReader br = new BufferedReader(fr)) {
    String line = br.readLine();
    System.out.println(line);
} catch (IOException e) {
    e.printStackTrace();
}
// fr and br automatically closed when block exits
```

---

## 19. Explain exception chaining in Java.

Exception chaining (or **exception wrapping**) allows one exception to **wrap another**, preserving the original cause while propagating a higher-level exception up the call stack.

```java
// Exception chaining using constructor
try {
    // Low-level database operation
    throw new SQLException("DB connection failed");
} catch (SQLException e) {
    throw new ServiceException("Service unavailable", e); // Chains SQLException as cause
}

// Exception chaining using initCause()
RuntimeException serviceEx = new RuntimeException("Service error");
serviceEx.initCause(new IOException("Original IO error"));
throw serviceEx;

// Retrieving the cause
try {
    riskyOperation();
} catch (ServiceException e) {
    System.out.println("Cause: " + e.getCause().getMessage());
}
```

---

## 20. In what scenarios might the `finally` block NOT execute?

| Scenario | `finally` executes? |
|----------|---------------------|
| Exception thrown in `try` | ✅ Yes |
| No exception — normal execution | ✅ Yes |
| `return` in `try` block | ✅ Yes — before returning |
| `System.exit()` called | ❌ No — JVM terminates |
| JVM crash (`OutOfMemoryError`, `ThreadDeath`) | ❌ No — JVM terminates abruptly |
| Thread killed abruptly | ❌ May not execute |

---

## 21. Advantages of `try-with-resources` over traditional `try-catch-finally`.

| Feature | Traditional `try-catch-finally` | `try-with-resources` |
|---------|--------------------------------|---------------------|
| **Resource closing** | Manual — in `finally` block. | ✅ Automatic — at end of block. |
| **Code verbosity** | More verbose. | ✅ Concise. |
| **Error-prone** | Easy to forget to close resources. | ✅ No resource leaks. |
| **Multiple resources** | Complex nested closes needed. | ✅ Multiple resources in one declaration. |
| **Exception suppression** | Original exception may be masked. | ✅ Handles suppressed exceptions cleanly. |

---

## 22. When and why would you create custom exceptions?

Custom exceptions represent **application-specific error conditions** not covered by built-in exceptions. They:
- Provide **more descriptive error messages**.
- Improve **code readability and maintainability**.
- Allow **fine-grained exception handling** for business logic errors.

```java
// Custom checked exception
public class InsufficientFundsException extends Exception {
    private double amount;

    public InsufficientFundsException(double amount) {
        super(String.format("Insufficient funds. Required: %.2f", amount));
        this.amount = amount;
    }

    public double getAmount() { return amount; }
}

// Usage
public class BankAccount {
    private double balance;

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
    }
}
```

---

## 23. Differentiate between exception chaining and nested exceptions.

| Feature | Exception Chaining | Nested Exceptions |
|---------|-------------------|-------------------|
| **Mechanism** | Uses `initCause()` or constructor parameter to link cause. | Embeds another exception object as a field within the custom exception class. |
| **Introduced** | Before Java 7. | Java 7 — structured representation. |
| **Access** | `e.getCause()` | Custom getter in the exception class. |
| **Purpose** | Preserve the original cause during propagation. | More structured, multi-level error representation. |

```java
// Exception chaining — initCause
RuntimeException highLevel = new RuntimeException("High-level error");
highLevel.initCause(new IOException("Low-level IO cause"));

// Nested exception — custom field
class OperationException extends Exception {
    private final Exception nestedCause;

    OperationException(String message, Exception nested) {
        super(message);
        this.nestedCause = nested;
    }

    public Exception getNestedCause() { return nestedCause; }
}
```

---

## Exception Handling — Quick Reference

| Keyword | Purpose |
|---------|---------|
| `try` | Encloses code that might throw an exception. |
| `catch` | Handles a specific exception type. |
| `finally` | Always executes — for cleanup. |
| `throw` | Throws an exception instance. |
| `throws` | Declares exceptions a method may throw. |

| Exception Type | Superclass | Checked? | Example |
|---------------|-----------|----------|---------|
| Checked | `Exception` | ✅ Yes | `IOException`, `SQLException` |
| Unchecked | `RuntimeException` | ❌ No | `NullPointerException`, `ArithmeticException` |
| Error | `Error` | ❌ No | `OutOfMemoryError`, `StackOverflowError` |

---

*Happy Coding! 🚀*