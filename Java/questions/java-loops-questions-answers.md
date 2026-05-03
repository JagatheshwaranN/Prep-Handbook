# Java - Loops Questions & Answers

---

## 1. What are the different types of loops available in Java?

Java supports **three types of loops**:

| Loop Type | Description |
|-----------|-------------|
| `for` loop | Used when the number of iterations is known. |
| `while` loop | Used when iterations depend on a condition checked before each iteration. |
| `do-while` loop | Used when the loop body must execute at least once. |

---

## 2. Explain the syntax of a `for` loop in Java.

```java
for (initialization; condition; update) {
    // code block to be executed
}
```

**Example:**

```java
for (int i = 0; i < 5; i++) {
    System.out.println("Iteration: " + i);
}
// Output: Iteration: 0 → 1 → 2 → 3 → 4
```

| Part | Description |
|------|-------------|
| `initialization` | Executed once before the loop starts. |
| `condition` | Checked before each iteration — loop runs while `true`. |
| `update` | Executed after each iteration. |

---

## 3. How does a `while` loop work in Java?

A `while` loop repeats a block of code **as long as the specified condition is true**. The condition is checked **before** each iteration.

```java
int i = 0;
while (i < 5) {
    System.out.println(i);
    i++;
}
// Output: 0 1 2 3 4
```

> If the condition is `false` initially, the loop body **never executes**.

---

## 4. What is the purpose of a `do-while` loop in Java?

A `do-while` loop is similar to a `while` loop, but **executes the loop body at least once**, even if the condition is false — because the condition is checked **after** the body executes.

```java
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 5);
// Output: 0 1 2 3 4
```

**Example with initially false condition:**

```java
int i = 10;
do {
    System.out.println("Runs at least once: " + i);
} while (i < 5);
// Output: Runs at least once: 10  (body executes once even though condition is false)
```

---

## 5. Discuss the differences between a `while` loop and a `do-while` loop.

| Feature | `while` loop | `do-while` loop |
|---------|-------------|-----------------|
| **Condition check** | **Before** each iteration. | **After** each iteration. |
| **Minimum executions** | 0 — body may never run if condition is false. | **At least 1** — body always runs once. |
| **Best used when** | Condition may be false before entering. | Body must run at least once. |

---

## 6. Explain the concept of an infinite loop. How can it be avoided?

An **infinite loop** runs indefinitely because the loop condition is always `true`. This typically happens due to a missing or incorrect update statement.

```java
// Infinite loop — i never increments
while (true) {
    System.out.println("Running forever...");
}
```

**How to avoid:**
- Ensure the **loop condition eventually becomes `false`**.
- Always include a proper **update statement** (e.g., `i++`).
- Use `break` to exit based on a specific condition when needed.

---

## 7. How can you terminate a loop prematurely in Java?

Use the **`break` statement** to exit the loop immediately based on a specific condition.

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) break; // Exit loop when i reaches 5
    System.out.println(i);
}
// Output: 0 1 2 3 4
```

---

## 8. What is the role of the `break` statement in loops?

The `break` statement **exits the loop immediately**, skipping all remaining iterations.

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break; // Stop the loop
    }
    System.out.println(i);
}
// Output: 0 1 2 3 4
```

**In nested loops:** `break` exits only the **innermost loop** it is in.

```java
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) break; // Exits inner loop only
        System.out.print(i + "" + j + " ");
    }
}
// Output: 00 10 20
```

---

## 9. How does the `continue` statement work in loops?

The `continue` statement **skips the current iteration** and proceeds with the next one.

```java
for (int i = 0; i < 5; i++) {
    if (i == 2) {
        continue; // Skip iteration when i == 2
    }
    System.out.println(i);
}
// Output: 0 1 3 4  (2 is skipped)
```

---

## 10. Discuss nested loops in Java.

Nested loops are **loops within another loop**. The inner loop completes all its iterations for each iteration of the outer loop.

```java
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        System.out.print(i + "" + j + " ");
    }
    System.out.println();
}
// Output:
// 00 01 02
// 10 11 12
// 20 21 22
```

**Common use case — printing a multiplication table:**

```java
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.printf("%4d", i * j);
    }
    System.out.println();
}
```

---

## 11. What are the advantages and disadvantages of nested loops?

**Advantages:**

| Advantage | Description |
|-----------|-------------|
| **Multidimensional data** | Efficiently works with matrices, 2D arrays, and similar structures. |
| **Complex repetition** | Handles complex repetitive tasks that involve multiple dimensions. |

**Disadvantages:**

| Disadvantage | Description |
|-------------|-------------|
| **Readability** | Deeply nested loops are hard to read and understand. |
| **Performance** | Execution time increases exponentially with each additional nested level (O(n²), O(n³), etc.). |

> **Best Practice:** Limit nesting to 2–3 levels. Extract inner loops into methods if deeper nesting is needed.

---

## 12. What is the difference between a `for` loop and a `for-each` loop?

| Feature | `for` loop | `for-each` (enhanced `for`) loop |
|---------|-----------|----------------------------------|
| **Introduced** | From the beginning. | Java 5.0. |
| **Index variable** | ✅ Requires an explicit index. | ❌ No index variable needed. |
| **Traversal direction** | ✅ Both forward and backward. | ❌ Forward only. |
| **Modifying elements** | ✅ Can modify elements using index. | ❌ Cannot modify elements during iteration. |
| **Use with** | Any counter-based iteration. | Arrays and Collections only. |
| **Syntax simplicity** | More verbose. | ✅ Simpler and cleaner. |

```java
int[] arr = {1, 2, 3, 4, 5};

// Traditional for loop — with index
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// Enhanced for loop — cleaner syntax
for (int num : arr) {
    System.out.println(num);
}
```

---

## 13. What does the enhanced `for` loop provide?

The enhanced `for` loop (introduced in **Java 5.0**) provides a **simpler syntax** for iterating over arrays or collections. It eliminates the need for explicit initialization, condition, or increment statements.

```java
// Array iteration
int[] arr = {1, 2, 3, 4, 5};
for (int num : arr) {
    System.out.println(num);
}

// Collection iteration
List<String> names = List.of("Alice", "Bob", "Charlie");
for (String name : names) {
    System.out.println(name);
}
```

---

## 14. When should you use `for` vs. `while` vs. `do-while`?

| Scenario | Best Loop |
|----------|-----------|
| Number of iterations **known beforehand**. | `for` loop |
| Iterating over a **range of values** with an index. | `for` loop |
| Iterating over **arrays or collections** without index. | `for-each` loop |
| Number of iterations **not known** — condition-dependent. | `while` loop |
| Loop body must execute **at least once**. | `do-while` loop |
| Reading input until a **sentinel value** is entered. | `do-while` loop |

```java
// for — known iterations
for (int i = 1; i <= 10; i++) { ... }

// while — unknown iterations
Scanner sc = new Scanner(System.in);
while (sc.hasNextLine()) { String line = sc.nextLine(); ... }

// do-while — at least once
int input;
do {
    System.out.print("Enter a positive number: ");
    input = scanner.nextInt();
} while (input <= 0);
```

---

## Loops — Quick Reference

| Loop | Condition checked | Min executions | Best for |
|------|------------------|----------------|----------|
| `for` | Before each iteration | 0 | Known iteration count. |
| `while` | Before each iteration | 0 | Condition-dependent, unknown count. |
| `do-while` | After each iteration | **1** | Must execute at least once. |
| `for-each` | N/A (element-based) | 0 | Arrays and collections. |

| Statement | Effect |
|-----------|--------|
| `break` | Exits the loop immediately. |
| `continue` | Skips current iteration, proceeds to next. |
| `break` (nested) | Exits only the **innermost** loop. |

---

*Happy Coding! 🚀*
