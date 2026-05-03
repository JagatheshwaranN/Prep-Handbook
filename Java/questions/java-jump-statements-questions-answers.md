# Java - Jump Statements Questions & Answers

---

## 1. What is the purpose of the `break` statement in Java?

The `break` statement is used to **terminate the loop or switch statement** it is inside. It is particularly useful when you want to exit a loop prematurely based on certain conditions.

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) break; // Exit the loop when i reaches 5
    System.out.println(i);
}
// Output: 0 1 2 3 4
```

---

## 2. How does a labeled `break` statement work in Java?

A **labeled `break`** terminates a specific loop identified by a label — not just the innermost loop. It transfers control to the statement immediately following the labeled loop.

```java
outer:
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (i == 1 && j == 1) {
            break outer; // Exits the OUTER loop, not just the inner one
        }
        System.out.println("i=" + i + ", j=" + j);
    }
}
// Output:
// i=0, j=0
// i=0, j=1
// i=0, j=2
// i=1, j=0
// (stops here — outer loop exited)
```

---

## 3. What are jump statements in Java, and how do they affect program flow?

Jump statements **alter the normal sequential execution** of code by transferring control to a different point in the program.

| Statement | Effect |
|-----------|--------|
| `break` | Exits the enclosing loop or switch statement immediately. |
| `continue` | Skips the remaining code in the current loop iteration and moves to the next. |
| `return` | Exits the current method, optionally returning a value to the caller. |

---

## 4. Explain the `break` statement with examples.

The `break` statement terminates the **innermost enclosing loop or switch statement**.

**Exiting a loop when a condition is met:**

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;
    }
    System.out.println(i);
}
// Output: 0 1 2 3 4
```

**Exiting a switch case:**

```java
int day = 3;
switch (day) {
    case 1: System.out.println("Monday");    break;
    case 2: System.out.println("Tuesday");   break;
    case 3: System.out.println("Wednesday"); break; // Exits here
    default: System.out.println("Other");
}
```

---

## 5. When would you use the `continue` statement?

The `continue` statement **skips the remaining code in the current loop iteration** and proceeds to the next iteration.

**Printing only odd numbers:**

```java
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) {
        continue; // Skip even numbers
    }
    System.out.println(i);
}
// Output: 1 3 5 7 9
```

**Labeled `continue` — skip to next iteration of outer loop:**

```java
outer:
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) {
            continue outer; // Skip to next outer iteration
        }
        System.out.println("i=" + i + ", j=" + j);
    }
}
// Output: i=0,j=0 | i=1,j=0 | i=2,j=0
```

---

## 6. Describe the purpose of the `return` statement.

The `return` statement **exits the current method** and optionally returns a value to the caller. The method stops executing immediately when `return` is encountered.

**Returning a value:**

```java
public static int factorial(int n) {
    if (n == 0) {
        return 1;              // Base case — exits early
    }
    return n * factorial(n - 1); // Recursive return
}

System.out.println(factorial(5)); // Output: 120
```

**Void method with early return:**

```java
public static void printIfPositive(int n) {
    if (n <= 0) {
        return; // Exit early without printing
    }
    System.out.println("Positive: " + n);
}
```

---

## 7. What are the drawbacks of using jump statements excessively?

| Drawback | Description |
|----------|-------------|
| **Reduced readability** | Excessive jumps make code harder to follow and understand. |
| **Harder debugging** | Non-linear flow makes it difficult to trace execution paths. |
| **Maintenance issues** | Complex jump patterns are fragile and hard to modify safely. |

**Alternatives:**
- **Restructure loops** to use better conditions instead of `break`.
- **Extract conditional logic** into separate methods instead of complex `continue` chains.
- Use **early return** in methods to avoid deeply nested `if` statements.

---

## 8. What is the purpose of the `continue` statement in Java?

The `continue` statement **skips the rest of the loop body** for the current iteration and moves to the next iteration of the loop (checking the loop condition again).

```java
for (int i = 1; i <= 5; i++) {
    if (i == 3) continue; // Skip iteration when i == 3
    System.out.print(i + " ");
}
// Output: 1 2 4 5
```

---

## 9. Can you use a `continue` statement outside of a loop?

No. The `continue` statement is **only valid inside loop constructs** (`for`, `while`, `do-while`). Using it outside a loop results in a **compilation error**.

```java
// COMPILATION ERROR — continue outside of loop
continue; // Not inside any loop
```

---

## 10. Explain the `return` statement in Java.

The `return` statement exits the current method and optionally returns a value to the calling code. When encountered, **the method stops executing immediately** and control returns to the caller.

| Method type | `return` syntax |
|-------------|----------------|
| `void` method | `return;` — exits method with no value. |
| Non-void method | `return value;` — exits and returns the value. |

```java
// Void method
public static void greet(String name) {
    if (name == null) return; // Exit early
    System.out.println("Hello, " + name);
}

// Non-void method
public static int square(int n) {
    return n * n; // Exit and return value
}
```

---

## 11. What happens if a `return` statement is encountered within a loop?

If `return` is inside a loop, it **immediately exits both the loop and the enclosing method**. No code after the `return` statement (inside the loop or the method) is executed.

```java
public static int findFirst(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i; // Exits the loop AND the method immediately
        }
    }
    return -1; // Reached only if target not found
}

int[] nums = {1, 3, 5, 7, 9};
System.out.println(findFirst(nums, 5)); // Output: 2
```

---

## 12. Can a method have multiple `return` statements?

Yes. A method can have **multiple `return` statements**, but **only one executes** during any given invocation — the first one reached during execution.

```java
public static String classify(int n) {
    if (n > 0) return "Positive";
    if (n < 0) return "Negative";
    return "Zero"; // Reached only if n == 0
}

System.out.println(classify(10));  // Positive
System.out.println(classify(-5));  // Negative
System.out.println(classify(0));   // Zero
```

---

## 13. How do you return a value conditionally in Java?

Use **conditional statements** (`if`, `else if`, `else`) to determine which value to return. Each branch contains its own `return` statement.

```java
public static double calculateDiscount(String membership) {
    if (membership.equals("Gold")) {
        return 0.20;      // 20% discount
    } else if (membership.equals("Silver")) {
        return 0.10;      // 10% discount
    } else if (membership.equals("Bronze")) {
        return 0.05;      // 5% discount
    } else {
        return 0.0;       // No discount
    }
}

System.out.println(calculateDiscount("Gold")); // 0.20
```

---

## 14. What is the difference between `return` and `System.exit()` in Java?

| Feature | `return` | `System.exit()` |
|---------|----------|-----------------|
| **Scope** | Exits the **current method** only. | Terminates the **entire JVM and program**. |
| **Execution continues** | ✅ Yes — caller continues after method call. | ❌ No — program stops immediately. |
| **Return value** | Can return a value to the caller. | Takes an exit status code (`0` = success, non-zero = error). |
| **Use case** | Normal method exit with or without a result. | Emergency shutdown or abnormal termination. |
| **`finally` block** | ✅ Executes. | ✅ Executes (except `Runtime.halt()`). |

```java
// return — exits the method, caller continues
public static int getValue() {
    return 42; // Caller receives 42 and continues
}

// System.exit() — terminates the entire program
public static void shutdown(int code) {
    System.out.println("Shutting down...");
    System.exit(code); // Exits JVM — nothing after this runs
}
```

---

## Jump Statements — Quick Reference

| Statement | Scope | Effect |
|-----------|-------|--------|
| `break` | Loop or switch | Exits the innermost enclosing loop or switch. |
| `break label` | Labeled loop | Exits the specifically labeled outer loop. |
| `continue` | Loop only | Skips remaining code in current iteration, proceeds to next. |
| `continue label` | Labeled loop | Skips to next iteration of the specified outer loop. |
| `return` | Method | Exits the method, optionally returning a value. |
| `return` inside loop | Method | Exits both the loop and the method. |
| `System.exit(0)` | Entire program | Terminates the JVM — program ends. |

---

*Happy Coding! 🚀*