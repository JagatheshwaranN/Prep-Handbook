# Java - Handy Notes: Complete Quick Reference

---

## 1. Polymorphism

**Polymorphism** — Ability of an object to take multiple forms. Objects of different classes treated as objects of a common superclass through Inheritance, Method Overloading, and Overriding.

### Two Types

| Type | Mechanism | Also Known As |
|------|-----------|---------------|
| **Compile-time** | Method Overloading | Early Binding / Static Binding / Static Method Dispatch |
| **Runtime** | Method Overriding | Late Binding / Dynamic Binding / Dynamic Method Dispatch |

> **Key note:** Parent class reference can hold a Child object. Using that reference, you can only call methods available in the parent class — not child-specific methods.
>
> ```java
> List l = new ArrayList();   // Polymorphism
> List l = new LinkedList();  // Polymorphism
> ```

---

## 2. Method Overloading

**Method Resolution — handled by Compiler**

| # | Key Point |
|---|-----------|
| 1 | Same method name with **different parameters**. |
| 2 | Criteria: Number, Type, Order of params. Order alone is NOT sufficient. |
| 3 | Return type and access modifiers are **NOT** considered. |
| 4 | VarArg methods can be overloaded — but take **least precedence**. |
| 5 | Compiler chooses the **most specific / closest** implementation. |
| 6 | Autoboxing (primitive → wrapper) and Widening (smaller → larger type) apply. |
| 7 | Works on **reference type**, not runtime object. |

> **Notes:**
> - Autoboxing/Unboxing may cause **ambiguity** in overloading.
> - **Widening or Narrowing takes precedence** over Autoboxing/Unboxing.

---

## 3. Method Overriding

**Method Resolution — handled by JVM**

| # | Key Point |
|---|-----------|
| 1 | Subclass provides implementation for a method already in Superclass. |
| 2 | Criteria: Same name, inheritance hierarchy, non-static, non-private. |
| 3 | `private`, `static`, and `final` class methods **cannot** be overridden. |
| 4 | **Method Hiding** — creating a static method with the same name in a subclass. |
| 5 | Return type: same or **covariant (subtype)**. |
| 6 | Access modifier: can be **increased or maintained**, but NOT decreased. |
| 7 | Checked exceptions: same or **fewer** than superclass. Unchecked exceptions: no restriction. |
| 8 | `super` keyword used to call Superclass methods/members. |
| 9 | Superclass default constructor called automatically when Subclass constructor invoked. |
| 10 | Works based on **runtime object**, not the reference type. |

---

## 4. Encapsulation

| # | Key Point |
|---|-----------|
| 1 | **Encapsulation = Data Hiding + Abstraction** |
| 2 | Criteria: Private variables + Public getters/setters. |
| 3 | Benefits: Data hiding, Modularity, Flexibility, Security. |
| 4 | Without encapsulation: data inconsistency and integrity compromise. |
| 5 | Promotes **Loose Coupling** and **High Cohesion**. |
| 6 | Alternatives to setters/getters: **Builder Pattern** and **Immutable Objects**. |
| 7 | Java Reflection can bypass encapsulation — security risk. |

> - **Data Hiding** — internal state inaccessible from outside.
> - **Abstraction** — hides complex implementation details, shows only essentials.

---

## 5. Inheritance

| # | Key Point |
|---|-----------|
| 1 | Inheritance = **"Is-A" Relationship** |
| 2 | Mechanism by which one class acquires properties and behaviors of another. |
| 3 | `extends` keyword used to implement inheritance. |
| 4 | Types: Single, Multilevel, Hierarchical, Multiple (**via interfaces only**). |
| 5 | Properties and methods are inherited — **constructors are NOT**. |
| 6 | `final` classes and `private` members cannot be inherited. |
| 7 | `Object` class is the root class — `equals()`, `hashCode()`, `toString()` accessible to all. |
| 8 | Benefits: Code reusability, Polymorphism, Easy maintenance. |
| 9 | Limitations: Tight coupling, Potential fragility (changes to super can impact child). |
| 10 | Applicable for both **classes and interfaces**. |

---

## 6. Abstract Class

| # | Key Point |
|---|-----------|
| 1 | Class with methods with no implementation — defined using `abstract` keyword. |
| 2 | Purpose: To achieve **abstraction**. |
| 3 | Criteria: Class NOT `final`; methods NOT `private` or `static`. |
| 4 | **Cannot be instantiated** — no object creation. |
| 5 | Can have **constructors** — invoked by subclass via `super()`. |
| 6 | Can have **both abstract and concrete methods**. |
| 7 | If subclass doesn't implement all abstract methods → must be declared `abstract` too. |
| 8 | Benefits: Code Reusability, Abstraction, Encapsulation, Polymorphism. |
| 9 | Limitations: Cannot be instantiated, Mandatory method implementation, Added complexity. |

---

## 7. Interface

| # | Key Point |
|---|-----------|
| 1 | Blueprint/contract defining methods a class **must implement**. |
| 2 | Purpose: To achieve **abstraction** and **multiple inheritance**. |
| 3 | Every method is implicitly `public abstract`. |
| 4 | Every variable is implicitly `public static final`. |
| 5 | Cannot have instance variables, constructors, or non-static methods (pre-Java 8). |
| 6 | Can have: constants, abstract, `default`, `private`, and `static` methods (Java 8+). |
| 7 | **Cannot be instantiated** — can only be implemented or extended. |
| 8 | **Marker Interface** — no methods; provides metadata. Examples: `Serializable`, `Cloneable`, `RandomAccess`. |
| 9 | **Functional Interface** — single abstract method; usable with lambda / method reference. |
| 10 | Interface can `extend` multiple interfaces. |
| 11 | Used in **Adapter pattern** — allows incompatible interfaces to work together. |
| 12 | Benefits: Abstraction, Code reusability, Loose coupling, Polymorphism, Contract definition. |

---

## 8. Composition & Aggregation (HAS-A Relationship)

| Feature | Composition | Aggregation |
|---------|------------|-------------|
| **Relationship** | Strong — parent owns child. | Weak — parent uses child. |
| **Child lifetime** | Depends on parent — destroyed with parent. | Independent — can exist without parent. |
| **Implementation** | Constructor injection. | Member variables or method parameters. |

| # | Key Point |
|---|-----------|
| 1 | Both provide **"Has-A" relationship** between objects. |
| 2 | In Composition, contained object often **initialized inside constructor**. |
| 3 | Benefits: Improved lifecycle management, Modular design, Encapsulation. |
| 4 | Drawback: Excessive composition leads to **tight coupling**. |
| 5 | Composition can replace Inheritance — prefer Has-A over Is-A for partial functionality. |
| 6 | If partial functionality needed → **Has-A**. If complete functionality → **Is-A**. |

---

## 9. Cohesion & Coupling

| Concept | Goal | How to Achieve |
|---------|------|---------------|
| **Cohesion** | Degree elements of a module belong together. | **High** — SRP, group related functions, private methods. |
| **Coupling** | Degree of dependency between modules. | **Low** — Interface-based programming, Dependency Injection, Observer/Factory patterns. |

> **Always aim for: High Cohesion + Low Coupling.**
>
> Advantages: Code Maintainability, Understandability, Flexibility, Testability, Robustness, Scalability.

---

## 10. Constructor

| # | Key Point |
|---|-----------|
| 1 | Special method to initialize objects (instance variables). Called implicitly at instantiation. |
| 2 | Same name as class; **no return type**. |
| 3 | Types: Default and Parameterized. |
| 4 | Cannot be `final`, `static`, or `abstract`. |
| 5 | Can be **overloaded** — allows object creation in different ways. |
| 6 | **Cannot be overridden**. |
| 7 | `this()` — constructor chaining (must be first statement). |
| 8 | `super()` — calls superclass constructor (must be first statement). |
| 9 | Can throw exceptions. |
| 10 | Private constructor — allowed; used in **Singleton Design Pattern**. |
| 11 | Allowed modifiers: `public`, `private`, `protected`, default. |
| 12 | Default constructor provided by compiler **only if no constructor is defined**. |

---

## 11. Type Casting

| # | Key Point |
|---|-----------|
| 1 | Converting one type to another. **Only for reference types** — not primitives. |
| 2 | Types: **Upcasting** (Subclass → Superclass) and **Downcasting** (Superclass → Subclass). |
| 3 | Requires **inheritance relationship**. |
| 4 | Upcasting: Safe, done automatically by compiler. |
| 5 | Downcasting: Risky, explicit cast required, may throw `ClassCastException`. |
| 6 | Use `instanceof` operator before downcasting to verify the type. |
| 7 | `Integer` → `Double`: Unboxing (Integer → int) then Autoboxing (int → Double). |

```java
Animal animal = new Dog();         // Upcasting — automatic
Dog dog = (Dog) animal;            // Downcasting — explicit

if (animal instanceof Dog d) {     // Safe downcast (Java 16+ pattern matching)
    d.fetch();
}
```

---

## 12. Static Block

| # | Key Point |
|---|-----------|
| 1 | Block enclosed with `static` keyword — executed **automatically at class loading**. |
| 2 | Purpose: Static variable initialization, one-time initialization tasks. |
| 3 | If error occurs in static block → class not loaded → `ExceptionInInitializerError`. |
| 4 | **Non-static members NOT allowed** — only static members. |
| 5 | Safer to initialize `final static` variables inside static block. |
| 6 | `try-catch` can be used inside static block. |
| 7 | Static block executes **before static variable assignments** in order of appearance. |
| 8 | Before Java 1.7: programs could run using only static blocks (without `main()`). |

---

## 13. Static Control Flow

**Sequence of execution when a Java class runs:**

```
1. Identification of static members (top to bottom)
2. Execution of static variable assignments and static blocks (top to bottom)
3. Execution of main() method
```

| # | Key Point |
|---|-----------|
| 1 | Static variables affect block execution if accessed before initialization (**forward reference**). |
| 2 | **Parent class** static members initialized/loaded **first**, then Child class. |
| 3 | Forward Reference — accessing a static variable or method declared later in the class. |

---

## 14. Instance Blocks & Control Flow

**Sequence of execution at object creation:**

```
1. Identification of instance members (top to bottom)
2. Execution of instance variable assignments and instance blocks (top to bottom)
3. Execution of constructor
```

| # | Key Point |
|---|-----------|
| 1 | Executed **each time** an object is created. |
| 2 | Purpose: Initialize instance variables and perform tasks not possible in constructors. |
| 3 | Error in instance block → object creation fails. |
| 4 | **Parent class** instance members initialized **first**, then Child. |
| 5 | Can access **both static and instance members**. |
| 6 | Forward Reference applies to instance blocks too. |

---

## 15. Loops

| Loop | Use When | Minimum Executions |
|------|----------|--------------------|
| `for` | Number of iterations known beforehand. | 0 |
| `while` | Number unknown — check condition first. | 0 |
| `do-while` | Body must execute at least once. | **1** |
| `for-each` | Iterating over arrays/collections (`Iterable`). | 0 |

| Statement | Effect |
|-----------|--------|
| `break` | Exits loop prematurely based on condition. |
| `continue` | Skips current iteration, moves to next. |

> **Note:** If `for` loop condition is not provided, compiler treats it as `true` → infinite loop.

---

## 16. Selection Statements

| Statement | Use When |
|-----------|----------|
| `if-else` | Complex conditions, inequality checks, multiple variables. |
| `switch` | Fixed set of simple values to compare. |

| # | Key Point |
|---|-----------|
| 1 | `switch` allowed types: `byte`, `short`, `char`, `int`, wrapper types, `Enum`, `String`. |
| 2 | Short-circuit evaluation: `&&` skips right operand if left is `false`; `\|\|` skips if left is `true`. |
| 3 | Ternary operator `? :` is shorthand for `if-else`. |

---

## 17. Transfer Statements

| Statement | Effect |
|-----------|--------|
| `break` | Exits loop or switch prematurely. |
| `continue` | Skips remaining code in current iteration. |
| `return` | Exits method, optionally returns a value. |
| Labeled `break` | Breaks out of a specific outer loop directly. |
| Labeled `continue` | Skips current iteration of a specific outer loop. |

> **Drawback:** Overuse makes code harder to read and maintain.

---

## 18. Exception Handling

| # | Key Point |
|---|-----------|
| 1 | Exception = event that disrupts normal program flow. |
| 2 | Hierarchy: `Throwable` → `Exception` / `Error`. |
| 3 | **Checked** (compile-time): `IOException`, `SQLException`. |
| 4 | **Unchecked** (runtime): `NullPointerException`, `ArrayIndexOutOfBoundsException`. |
| 5 | `try-catch` handles exceptions; `finally` always executes (for cleanup). |
| 6 | `throw` — explicitly throw an exception object to JVM. |
| 7 | `throws` — delegate exception handling responsibility to caller. |
| 8 | Custom exceptions: extend `Exception` (checked) or `RuntimeException` (unchecked). |
| 9 | `try-with-resources` — implicit resource management (Java 7+). |
| 10 | Exception Chaining (Wrapping) — preserves original cause while propagating. |
| 11 | `finally` won't run if: `OutOfMemoryError`, `ThreadDeath`, or `System.exit()`. |

**Common Exceptions:**
`ArrayIndexOutOfBoundsException` | `NullPointerException` | `ClassCastException` | `StackOverflowError` | `NoClassDefFoundError` | `ExceptionInInitializerError` | `IllegalArgumentException` | `NumberFormatException` | `IllegalStateException` | `AssertionError`

---

## 19. Threads

**Thread Life Cycle:**

```
New/Born → Ready/Runnable → Running → Dead
```

| # | Key Point |
|---|-----------|
| 1 | Thread = lightweight process; separate path of execution within a program. |
| 2 | Two ways to create: extend `Thread` class OR implement `Runnable` interface. |
| 3 | States: New, Runnable, Blocked, Waiting (Timed Waiting), Terminated. |
| 4 | `Runnable` preferred — allows extending another class simultaneously. |
| 5 | `start()` — registers thread with scheduler and calls `run()`. Direct `run()` call ≠ new thread. |
| 6 | `join()` — makes calling thread wait for target thread to complete. |
| 7 | Synchronization — controls access to shared resources. Methods, blocks, `ReentrantLock`. |
| 8 | Deadlock — threads blocked forever waiting for each other. Avoid nested locks, use timeouts. |
| 9 | Daemon thread — background service thread (e.g., Garbage Collector). |
| 10 | Thread Pool — pre-initialized threads; reduces creation/destruction overhead. |
| 11 | `wait()` — current thread waits. `notify()` — wakes one thread. `notifyAll()` — wakes all. |
| 12 | Thread Starvation — thread denied CPU time. Use fair scheduling (`ReentrantLock(true)`). |
| 13 | `volatile` — variable always read/written from main memory; ensures visibility across threads. |
| 14 | Livelock — threads active but stuck in repetitive cycle without progress. |
| 15 | `ThreadLocal` — each thread has its own isolated copy of data. |
| 16 | `InheritableThreadLocal` — child threads inherit data from parent thread. |
| 17 | Thread paused by: `yield()`, `join()`, `sleep()`. Thread interrupted by: `interrupt()`. |

---

## 20. Inner Class (Introduced in Java 1.1)

| Type | Access to Outer Members |
|------|------------------------|
| **Normal Inner Class** | Both static and non-static. |
| **Static Inner Class** | Static members only. |
| **Local Inner Class** | Outer members + effectively final local vars. |
| **Anonymous Inner Class** | Outer members + effectively final local vars. |

| # | Key Point |
|---|-----------|
| 1 | Inner class has access to **all members** of outer class — including private. |
| 2 | Scope of inner class is **limited to outer class scope**. |
| 3 | Inner class relationship with outer = **Has-A (Composition/Aggregation)**. |
| 4 | Can be declared `private`, `protected`, or `public`. |
| 5 | Anonymous inner classes — no name; defined and instantiated at same time. |
| 6 | Interface inside interface: always `public static`. Class inside interface: always `public static`. Interface inside class: always `static`. |

---

## 21. Array

| # | Key Point |
|---|-----------|
| 1 | Stores multiple values of the **same type** under a single variable. |
| 2 | Elements accessed by **0-based index**. |
| 3 | Iterated using `for` or `for-each` loop. |
| 4 | Default values: primitives → `0`/`false`; reference types → `null`. |
| 5 | Multidimensional array = array of arrays (tabular form). |
| 6 | **Anonymous array** — array without a name; for one-time use. |
| 7 | Advantage: Fast element access. |
| 8 | Limitation: Fixed size; slow insertion/deletion. |

---

## 22. Variables

| Type | Location | Memory | Default Value | Access Modifiers |
|------|----------|--------|--------------|-----------------|
| **Local** | Method/block/constructor | Stack | ❌ None | ❌ Not allowed |
| **Instance** | Class body (no `static`) | Heap | ✅ Type defaults | ✅ Allowed |
| **Class (Static)** | Class body (`static`) | Method Area (Heap) | ✅ Type defaults | ✅ Allowed |

| Default Values | |
|---------------|---|
| Numeric (`int`, `long`, etc.) | `0` |
| `float` / `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` |
| Reference types | `null` |

| # | Key Point |
|---|-----------|
| 1 | `final` variables = constants; must be initialized at declaration or in constructor. |
| 2 | **Shadowing** — inner variable with same name hides outer variable. |
| 3 | `volatile` — always read/written from main memory. |
| 4 | Primitive holds **actual data**; Reference holds **memory address** of object. |

---

## 23. `main()` Method

| # | Key Point |
|---|-----------|
| 1 | Entry point of the Java program — JVM calls `main()` to execute. |
| 2 | `String[] args` — accepts command-line arguments. |
| 3 | Cannot be `private` or `protected` — JVM needs `public` access. |
| 4 | Can be `final` — not required, as `static` is already implicitly final. |
| 5 | Can be `synchronized` — not common; introduces overhead. |
| 6 | Overloading applies; Overriding does NOT (Method Hiding applies instead). |
| 7 | Java 1.6: static block execution without `main()` → `NoSuchMethodError`. Java 1.7+: Error message. |

**Why the standard signature?**

| Part | Reason |
|------|--------|
| `public` | JVM needs to call it from anywhere. |
| `static` | Called without creating an object. |
| `void` | Returns nothing to JVM. |
| `main` | Name configured inside JVM. |
| `String[] args` | Command-line args; String is universal and convertible. |

**Valid `main()` variants:**
```java
public static void main(String[] args)
static public void main(String[] args)
public static final void main(String[] args)
public static synchronized void main(String[] args)
static final synchronized strictfp public void main(String... we)
```

---

## 24. Objects & Classes

| # | Key Point |
|---|-----------|
| 1 | Class = blueprint; Object = instance of class. |
| 2 | Class defines structure (fields + methods); Object holds actual data. |
| 3 | `this` — refers to current instance; also used for constructor chaining. |
| 4 | JVM generates a unique **HashCode** for each object (used in hash-based data structures). |
| 5 | `getClass()` — returns runtime class definition; gives access to class metadata. |

---

## 25. String

| Type | Mutability | Thread-safe | Best Used |
|------|-----------|------------|-----------|
| `String` | Immutable | ✅ Yes | Constant/fixed text. |
| `StringBuffer` | Mutable | ✅ Yes (synchronized) | Multi-threaded modification. |
| `StringBuilder` | Mutable | ❌ No | Single-threaded modification (fastest). |

| # | Key Point |
|---|-----------|
| 1 | `String s = "Hello"` → created in **SCP (String Constant Pool)** only if not already present. |
| 2 | `String s = new String("Hello")` → always creates object in **Heap**, even if SCP has one. |
| 3 | **SCP** = special memory area in Heap where string literals are stored and shared. |
| 4 | Immutability makes String inherently **thread-safe**. |
| 5 | String constructors: `new String()`, `new String(literal)`, `new String(StringBuffer)`, `new String(char[])`, `new String(byte[])`. |

---

## 26. Collections

| Interface | Characteristics | Key Implementations |
|-----------|----------------|---------------------|
| `List` | Ordered, allows duplicates. | `ArrayList`, `LinkedList` |
| `Set` | No duplicates. | `HashSet`, `LinkedHashSet`, `TreeSet` |
| `Map` | Key-value pairs; unique keys. | `HashMap`, `LinkedHashMap`, `TreeMap` |
| `Queue` | Holds elements before processing (FIFO). | `LinkedList`, `PriorityQueue` |
| `Deque` | Double-ended queue. | `ArrayDeque` |

| # | Key Point |
|---|-----------|
| 1 | `Collection` = interface; `Collections` = utility class. |
| 2 | `HashMap` — hashtable storage; fail-fast iterator (`ConcurrentModificationException`). |
| 3 | `Hashtable` — synchronized; fail-safe enumerator. Prefer `ConcurrentHashMap` today. |
| 4 | `Comparable` — natural sorting; `Comparator` — custom sorting. |
| 5 | Synchronized wrappers: `Collections.synchronizedList()`, `synchronizedSet()`, `synchronizedMap()`. |
| 6 | `ArrayList` — dynamic array; fast random access. `LinkedList` — doubly-linked; fast insert/delete. |
| 7 | `HashSet` — no order. `TreeSet` — sorted. `LinkedHashSet` — insertion order preserved. |
| 8 | `Iterator` — forward traversal. `ListIterator` — bidirectional. `Enumeration` — legacy. |
| 9 | `HashSet` internally uses `HashMap`; uniqueness via `hashCode()` + `equals()`. |

---

*Happy Coding! 🚀*