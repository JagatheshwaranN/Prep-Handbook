# Java - Static Control Flow Questions & Answers

---

## 1. What is static control flow in Java?

Static control flow refers to the **sequence of execution of static blocks, static variable initialization, and static method invocations** during the loading and initialization phase of a Java class.

---

## 2. Explain the order of execution for static blocks, variables, and methods.

During the class loading process, the order is:

| Step | What Happens |
|------|-------------|
| 1 | **Static variables** are initialized in the order they are declared. |
| 2 | **Static blocks** are executed in the order they appear in the class. |
| 3 | **Static methods** can be invoked only after all static variables are initialized and static blocks have executed. |

> **Note:** Static variables and static blocks are interleaved — they execute **top to bottom** as they appear in the source code.

---

## 3. What are static blocks? How are they different from static methods?

| Feature | Static Block | Static Method |
|---------|-------------|---------------|
| **Declaration** | `static { ... }` — code block. | `static returnType methodName() { ... }` |
| **Execution** | Automatically once during class loading. | Explicitly called any number of times. |
| **Purpose** | Initialization tasks — run setup code. | Reusable functionality. |
| **Return value** | None. | Can return a value. |
| **Direct invocation** | ❌ Cannot be called directly. | ✅ Called by method name. |

---

## 4. Can you have multiple static blocks within a class?

Yes. A class can have **multiple static blocks**, executed in the **order they appear** from top to bottom during class loading.

```java
class MultiBlock {
    static { System.out.println("Static block 1"); }
    static { System.out.println("Static block 2"); }
    static { System.out.println("Static block 3"); }
}
// Output: Static block 1 → Static block 2 → Static block 3
```

---

## 5. Explain the use of static variables in Java.

Static variables:
- Belong to the **class** rather than any instance.
- Are **shared among all instances** of the class.
- Are **initialized only once** at the start of execution.
- **Retain their values** until the program terminates.

```java
class Counter {
    static int count = 0; // Shared across all instances

    Counter() { count++; }

    static int getCount() { return count; }
}

new Counter(); new Counter(); new Counter();
System.out.println(Counter.getCount()); // 3 — shared across all instances
```

---

## 6. What is the purpose of static initialization blocks?

Static initialization blocks **initialize static variables** and execute setup code during class loading, ensuring that static variables are fully initialized **before the class is used**.

```java
class Config {
    static String dbUrl;

    static {
        dbUrl = System.getenv("DB_URL");
        if (dbUrl == null) dbUrl = "jdbc:mysql://localhost:3306/default";
    }
}
```

---

## 7. How do you ensure static variables are initialized only once in a multi-threaded environment?

Use synchronization mechanisms to ensure thread-safe initialization:

1. **`synchronized` blocks** — protect the initialization code.
2. **`volatile` keyword** — ensures visibility across threads.
3. **Initialization-on-demand holder idiom** — leverages class loading guarantees.

```java
// Thread-safe Singleton using class loading guarantee
class Singleton {
    private static class Holder {
        static final Singleton INSTANCE = new Singleton(); // Initialized once by JVM
    }

    private Singleton() { }

    public static Singleton getInstance() {
        return Holder.INSTANCE; // JVM guarantees single initialization
    }
}
```

---

## 8. Give an example of static control flow in a Java class.

```java
public class StaticControlFlowExample {

    static {
        System.out.println("Static block 1");   // Step 1
    }

    static int x = 10;                          // Step 2 — variable initialized

    static {
        System.out.println("Static block 2");   // Step 3
        System.out.println("Value of x: " + x); // x is already 10
    }

    public static void main(String[] args) {
        System.out.println("Inside main method"); // Step 4
    }
}
// Output:
// Static block 1
// Static block 2
// Value of x: 10
// Inside main method
```

---

## 9. Can you have loops or conditional statements inside static blocks?

Yes. Static blocks can contain **any valid Java statements**, including loops and conditionals. However:

| Consideration | Description |
|---------------|-------------|
| **Infinite loops** | Must be avoided — the class will never finish loading. |
| **Blocking operations** | Long-running or waiting operations can delay or prevent class loading. |
| **Deadlocks** | Circular dependencies between classes in static blocks can cause deadlocks. |

```java
class LookupTable {
    static int[] squares = new int[10];

    static {
        for (int i = 0; i < squares.length; i++) {
            squares[i] = i * i;  // Loop in static block — safe and finite
        }
        System.out.println("Lookup table initialized.");
    }
}
```

---

## 10. How does the presence of static variables impact static block execution order?

Static variables and static blocks are **interleaved and executed top to bottom**. If a static block depends on a static variable, that variable must be **declared and initialized before** the static block that uses it.

```java
class Example {
    static int a = 5;       // Initialized first

    static {
        System.out.println("a = " + a); // ✅ a is already 5
    }

    static int b = a * 2;   // b = 10 — initialized after the static block

    static {
        System.out.println("b = " + b); // ✅ b is now 10
    }
}
// Output: a = 5 → b = 10
```

---

## 11. What happens if you access an instance variable from within a static block?

It results in a **compilation error**. Static blocks belong to the class — there is no object context, so instance variables cannot be accessed directly.

```java
class Example {
    int instanceVar = 10;    // Instance variable
    static int staticVar = 20; // Static variable

    static {
        // System.out.println(instanceVar); // COMPILATION ERROR
        System.out.println(staticVar);      // ✅ OK
    }
}
```

---

## 12. Explain the concept of forward reference in static initialization.

A **forward reference** occurs when a static block or initializer tries to access a static variable or method **declared later** in the class. This causes a **compilation error** — Java requires all static variables and methods to be declared before being accessed.

```java
class ForwardRefExample {
    static {
        // System.out.println(value); // COMPILATION ERROR — forward reference
    }

    static int value = 42; // Declared after the static block that tries to use it
}
```

> **Best Practice:** Declare static variables before the static blocks that reference them.

---

## 13. Can a class with a `main` method also have a static block?

Yes. The static block executes **before** the `main` method — during class loading. This is useful for performing initialization tasks before the main logic begins.

```java
class AppLauncher {
    static {
        System.out.println("1. Static block — class loaded");
    }

    public static void main(String[] args) {
        System.out.println("2. main method — app started");
    }
}
// Output:
// 1. Static block — class loaded
// 2. main method — app started
```

---

## 14. How does class initialization order change in inheritance scenarios involving static members?

Subclasses are initialized **only after their superclasses** are fully initialized. The order is:

```
1. Parent static variables and blocks (top to bottom)
2. Child static variables and blocks (top to bottom)
3. Parent constructor
4. Child constructor
```

```java
class Parent {
    static int parentVar = 10;
    static { System.out.println("Parent static block, parentVar = " + parentVar); }
    Parent() { System.out.println("Parent constructor"); }
}

class Child extends Parent {
    static int childVar = parentVar * 2; // Uses parent's static variable
    static { System.out.println("Child static block, childVar = " + childVar); }
    Child() { System.out.println("Child constructor"); }
}

// new Child() → Output:
// Parent static block, parentVar = 10
// Child static block, childVar = 20
// Parent constructor
// Child constructor
```

---

## 15. What are the thread safety concerns with static initialization in a multi-threaded environment?

| Risk | Description |
|------|-------------|
| **Race conditions** | Multiple threads may attempt to initialize static members simultaneously, leading to inconsistent state. |
| **Partial initialization** | One thread may see a partially initialized static variable. |
| **Deadlocks** | Circular class-loading dependencies across threads can cause deadlocks. |

**Solutions:**
- Use `synchronized` blocks for custom lazy initialization.
- Use `volatile` for visibility guarantees.
- Leverage the JVM's class loading guarantee (each class is loaded once by the class loader) via the **initialization-on-demand holder idiom**.

---

## 16. How can you defer initialization of a static variable until first access?

Use **lazy initialization** techniques:

**Initialization-on-demand holder idiom:**

```java
class ExpensiveResource {
    private ExpensiveResource() {
        System.out.println("Resource initialized");
    }

    // Inner class — loaded only when getInstance() is first called
    private static class Holder {
        static final ExpensiveResource INSTANCE = new ExpensiveResource();
    }

    public static ExpensiveResource getInstance() {
        return Holder.INSTANCE; // Lazy — initialized only on first access
    }
}

// Resource is NOT initialized until the first call to getInstance()
ExpensiveResource r = ExpensiveResource.getInstance(); // "Resource initialized"
```

**Benefits:**
- Thread-safe without `synchronized`.
- Initialized only when first needed — avoids upfront cost.

---

## 17. Explain the difference between static initialization and instance initialization in Java.

| Feature | Static Initialization | Instance Initialization |
|---------|----------------------|------------------------|
| **When** | When the class is **loaded into memory** (JVM class loading phase). | When a **new object is created** (`new` keyword). |
| **Frequency** | **Once** per class load. | **Every time** a new instance is created. |
| **Syntax** | `static { ... }` or `static Type var = value;` | `{ ... }` (instance initializer block) or `Type var = value;` |
| **Access** | Can only access **static members**. | Can access both **static and instance members**. |
| **Order** | Before constructors — during class loading. | After static initialization, before constructor body. |

```java
class InitOrder {
    static int staticVar;
    int instanceVar;

    static {
        staticVar = 100;
        System.out.println("1. Static block");
    }

    {
        instanceVar = 200;
        System.out.println("2. Instance initializer block");
    }

    InitOrder() {
        System.out.println("3. Constructor");
    }
}

// new InitOrder() (first time) → Output:
// 1. Static block
// 2. Instance initializer block
// 3. Constructor

// new InitOrder() (second time) → Output:
// 2. Instance initializer block  (static block does NOT run again)
// 3. Constructor
```

---

## Static Control Flow — Quick Reference

| Concept | Description |
|---------|-------------|
| **Execution order** | Static variables and static blocks — top to bottom as declared. |
| **Static block runs** | Once when the class is first loaded by JVM. |
| **Multiple static blocks** | ✅ Allowed — run in declaration order. |
| **Access instance vars** | ❌ Not allowed in static blocks. |
| **Access static vars** | ✅ Allowed in static blocks. |
| **Loops/conditionals** | ✅ Allowed — avoid infinite loops. |
| **Forward reference** | ❌ Compilation error if a later-declared variable is accessed. |
| **Inheritance order** | Parent static → Child static → Parent constructor → Child constructor. |
| **Multi-threaded safety** | Use `synchronized`, `volatile`, or holder idiom. |
| **Lazy initialization** | Use initialization-on-demand holder idiom. |
| **Static vs instance init** | Static: once on class load. Instance: every time `new` is called. |

---

*Happy Coding! 🚀*