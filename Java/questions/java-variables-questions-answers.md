# Java - Variables Questions & Answers

---

## 1. What is a variable in Java?

A variable in Java is a **named memory location that stores data**. It has a data type and can hold a value that can be changed during the execution of a program.

```java
int age = 25;        // Variable named 'age' holding value 25
String name = "Alice"; // Variable named 'name' holding "Alice"
```

---

## 2. How do you declare a variable in Java?

```java
dataType variableName;

// Examples
int age;
String name;
double salary;

// Declare and initialize in one step
int age = 25;
```

---

## 3. What are the different types of variables in Java?

| # | Type | Declaration Location |
|---|------|---------------------|
| 1 | **Local variable** | Inside a method, constructor, or block. |
| 2 | **Instance variable** | Inside a class, outside any method — no `static`. |
| 3 | **Class variable (static field)** | Inside a class, outside any method — with `static`. |

```java
class Example {
    int instanceVar = 10;          // Instance variable
    static int classVar = 20;      // Class (static) variable

    void method() {
        int localVar = 30;         // Local variable
    }
}
```

---

## 4. What is the difference between local, instance, and class variables?

| Feature | Local Variable | Instance Variable | Class Variable (Static) |
|---------|--------------|-------------------|------------------------|
| **Declared in** | Method, constructor, or block. | Class body (outside methods). | Class body with `static`. |
| **Scope** | Within the enclosing block. | Throughout the entire class. | Throughout the class and class lifetime. |
| **Default value** | ❌ None — must initialize before use. | ✅ Default values assigned. | ✅ Default values assigned. |
| **Access modifiers** | ❌ Not allowed. | ✅ Allowed. | ✅ Allowed. |
| **Shared** | No — unique to execution. | No — unique per instance. | ✅ Yes — shared across all instances. |
| **Lifetime** | During method execution. | As long as the object exists. | As long as the class is loaded. |

---

## 5. What is variable initialization?

Variable initialization is the process of **assigning a value to a variable** at declaration or later in the program. All variables in Java must be initialized before they are used.

```java
int x;      // Declared but not initialized
x = 10;     // Initialized later

int y = 20; // Declared and initialized in one step
```

---

## 6. What are the default values of variables in Java?

Instance and class variables are automatically assigned default values. Local variables have **no default value** and must be explicitly initialized.

| Data Type | Default Value |
|-----------|--------------|
| `byte`, `short`, `int`, `long` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` (null character) |
| Reference types (`Object`, `String`, arrays) | `null` |

```java
class Defaults {
    int i;         // 0
    double d;      // 0.0
    boolean b;     // false
    String s;      // null
}
```

---

## 7. Can a `final` variable be reassigned a new value?

No. A `final` variable **cannot be reassigned** once initialized. It acts as a constant.

```java
final int MAX_SIZE = 100;
// MAX_SIZE = 200; // COMPILATION ERROR — cannot reassign final variable

final String GREETING = "Hello";
// GREETING = "Hi"; // COMPILATION ERROR
```

---

## 8. What are the naming conventions for variables in Java?

| Rule | Description |
|------|-------------|
| **Valid characters** | Letters, digits, `_`, `$`. Cannot start with a digit. |
| **Case-sensitive** | `age` and `Age` are different variables. |
| **Convention** | Use **camelCase** for variable names (e.g., `myVariableName`). |
| **Constants** | Use `UPPER_SNAKE_CASE` for `final` constants (e.g., `MAX_VALUE`). |
| **Meaningful names** | Use descriptive names (e.g., `employeeAge` over `ea`). |

```java
int employeeAge = 30;       // ✅ camelCase
final int MAX_SIZE = 100;   // ✅ UPPER_SNAKE_CASE for constants
// int 1count = 0;          // ❌ Cannot start with digit
```

---

## 9. How do you declare and initialize constants in Java?

Use the `final` keyword. Constants are conventionally named in `UPPER_SNAKE_CASE`.

```java
final int MAX_VALUE = 100;
final double PI = 3.14159;
final String APP_NAME = "MyApp";

// Class-level constant (shared by all instances)
static final int DEFAULT_TIMEOUT = 30;
```

---

## 10. What is variable scope in Java?

Variable scope refers to the **region of the program where a variable is accessible**.

| Scope | Description |
|-------|-------------|
| **Local scope** | Variables inside a method, constructor, or block — accessible only within that block. |
| **Instance scope** | Instance variables accessible throughout the entire class — exist as long as the object. |
| **Class scope** | Static variables accessible throughout the class — exist as long as the class is loaded. |

```java
class ScopeExample {
    int instanceVar = 10;        // Instance scope
    static int classVar = 20;    // Class scope

    void method() {
        int localVar = 30;       // Local scope — only inside this method
        System.out.println(instanceVar); // ✅ Accessible
        System.out.println(classVar);    // ✅ Accessible
        System.out.println(localVar);    // ✅ Accessible
    }
    // localVar is NOT accessible here
}
```

---

## 11. What is the difference between primitive and reference variables?

| Feature | Primitive Variable | Reference Variable |
|---------|------------------|--------------------|
| **Stores** | The **actual value** directly. | A **memory address** pointing to the object. |
| **Types** | `int`, `double`, `boolean`, `char`, etc. | `Object`, `String`, arrays, custom classes. |
| **Default value** | `0`, `false`, `'\u0000'` | `null` |
| **Memory location** | Stack | Stack (reference) + Heap (object) |
| **Comparison** | `==` compares values. | `==` compares references; `.equals()` compares content. |

```java
int a = 5;          // Primitive — stores value 5
String s = "Hello"; // Reference — stores address pointing to "Hello" object
```

---

## 12. When do you use local, instance, and static variables?

| Variable Type | Use When |
|--------------|----------|
| **Local variables** | Temporary data needed only within a method. |
| **Instance variables** | Data specific to each object (e.g., `name`, `age` per employee). |
| **Static variables** | Data shared by all objects (e.g., constants, counters, utility values). |

---

## 13. What is the difference between `==` and `.equals()` when comparing objects?

| Feature | `==` operator | `.equals()` method |
|---------|--------------|-------------------|
| **Compares** | **References** — checks if both point to the same memory location. | **Content** — checks if the values are logically equal. |
| **For primitives** | ✅ Compares values directly. | N/A |
| **For objects** | Checks if same instance. | Should be overridden for custom comparison. |

```java
String s1 = new String("Hello");
String s2 = new String("Hello");

System.out.println(s1 == s2);       // false — different objects in memory
System.out.println(s1.equals(s2));  // true — same content

int a = 5, b = 5;
System.out.println(a == b);         // true — primitives compared by value
```

---

## 14. Explain the concept of variable shadowing in Java.

**Shadowing** occurs when a variable in an inner scope has the **same name** as a variable in an outer scope. The inner variable "shadows" the outer one — the outer becomes inaccessible in the inner scope.

```java
int x = 10; // Outer variable

void method() {
    int x = 20;              // Inner variable — shadows outer x
    System.out.println(x);   // 20 — inner variable
}
// After method: outer x is still 10
```

**In inner classes — using `OuterClass.this`:**

```java
class Outer {
    int x = 10;

    class Inner {
        int x = 20;

        void display() {
            System.out.println(x);            // 20 — inner x
            System.out.println(Outer.this.x); // 10 — outer x
        }
    }
}
```

---

## 15. Explain the concept of `volatile` variables in Java.

A `volatile` variable is always **read from and written to main memory**, ensuring that changes made by one thread are **immediately visible** to all other threads.

```java
class StopFlag {
    private volatile boolean running = true; // Without volatile, other threads might see stale value

    public void stop() {
        running = false; // Change immediately visible to all threads
    }

    public void run() {
        while (running) {
            // Process tasks
        }
        System.out.println("Stopped.");
    }
}
```

> **Note:** `volatile` ensures **visibility** but not **atomicity**. For compound operations like `i++`, use `AtomicInteger` or `synchronized`.

---

## 16. What is the difference between local, instance, and class variables in terms of memory allocation?

| Feature | Local Variable | Instance Variable | Class (Static) Variable |
|---------|--------------|-------------------|------------------------|
| **Memory location** | **Stack** | **Heap** (with the object) | **Method area of Heap** |
| **Default value** | ❌ None — must initialize. | ✅ Default values. | ✅ Default values. |
| **Lifetime** | During method execution. | As long as the object exists. | As long as the class is loaded. |
| **Initialization** | Manually before use. | At object creation. | Once, at class loading. |

---

## 17. Explain varargs (variable-length argument lists) in Java.

Varargs allow methods to **accept a variable number of arguments** of the same type. Denoted by `...` after the data type. Inside the method, varargs are treated as an **array**.

```java
void printValues(int... values) {
    for (int value : values) {
        System.out.print(value + " ");
    }
    System.out.println();
}

// Can be called with any number of arguments
printValues(1, 2, 3);           // 1 2 3
printValues(10, 20);             // 10 20
printValues();                   // (no output)
printValues(new int[]{5, 6, 7}); // 5 6 7 — array also accepted
```

> **Note:** Varargs must be the **last parameter** in the method signature. A method can have only one varargs parameter.

---

## 18. Are primitives and objects passed by value or by reference in Java?

**Java always passes by value.** However, what is "passed" differs:

| Type | What is passed | Effect in method |
|------|---------------|-----------------|
| **Primitive** (`int`, `double`) | A **copy of the value**. | Changes inside the method do NOT affect the original. |
| **Object reference** (`String`, etc.) | A **copy of the reference** (memory address). | Changes to the object's properties AFFECT the original object. Reassigning the reference does NOT affect the original. |

```java
void modifyPrimitive(int x) {
    x = 100; // Changes the copy — original unchanged
}

void modifyObject(int[] arr) {
    arr[0] = 100; // Modifies the actual array object — reflected in caller
}

void reassignReference(int[] arr) {
    arr = new int[]{999}; // Reassigns local copy — original unchanged
}

int num = 5;
modifyPrimitive(num);
System.out.println(num); // 5 — unchanged

int[] array = {1, 2, 3};
modifyObject(array);
System.out.println(array[0]); // 100 — changed

reassignReference(array);
System.out.println(array[0]); // 100 — not changed by reassignment
```

---

## 19. Explain the order of execution with static initialization blocks and variable declarations.

```
Class loading order:
1. Static variable declarations (with default values)
2. Static initialization blocks and static variable assignments — top to bottom
3. (When new object is created): Instance variable initialization
4. (When new object is created): Constructors
```

```java
class InitOrder {
    static int staticVar = 10;          // Step 1 — static variable

    static {
        System.out.println("1. Static block, staticVar = " + staticVar); // Step 2
        staticVar = 20;
    }

    int instanceVar = 30;               // Step 3 — instance variable (at object creation)

    InitOrder() {
        System.out.println("2. Constructor, instanceVar = " + instanceVar); // Step 4
    }

    public static void main(String[] args) {
        System.out.println("Static block runs BEFORE main");
        new InitOrder();
    }
}
// Output:
// 1. Static block, staticVar = 10
// Static block runs BEFORE main
// 2. Constructor, instanceVar = 30
```

---

## Variables — Quick Reference

| Variable Type | Location | Default Value | Access Modifiers | Shared? |
|--------------|----------|--------------|-----------------|---------|
| **Local** | Inside method/block | ❌ None | ❌ Not allowed | No |
| **Instance** | Class body (no `static`) | ✅ Type defaults | ✅ Allowed | Per instance |
| **Static (Class)** | Class body (`static`) | ✅ Type defaults | ✅ Allowed | All instances |

| Default Values | |
|---------------|---|
| `int`/`long`/`short`/`byte` | `0` |
| `float`/`double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` |
| Reference type | `null` |

| Concept | Key Point |
|---------|-----------|
| `final` variable | Cannot be reassigned after initialization. |
| `volatile` variable | Ensures visibility of changes across threads. |
| Varargs (`...`) | Variable number of arguments — treated as array, must be last parameter. |
| Shadowing | Inner variable hides outer with same name. |
| `==` vs `.equals()` | `==` checks references; `.equals()` checks content. |
| Pass by value | Java always passes by value — primitives copy value, objects copy reference. |

---

*Happy Coding! 🚀*