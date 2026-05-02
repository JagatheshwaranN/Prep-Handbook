# Java - Composition & Aggregation Questions & Answers

---

## 1. What is composition and aggregation in Java?

Composition and aggregation are two types of **"has-a" relationships** between classes in object-oriented programming. They differ in the **strength** of the relationship.

| Feature | Composition | Aggregation |
|---------|------------|-------------|
| **Relationship strength** | Strong ("whole-part") | Weak ("has-a") |
| **Child independence** | ❌ Child cannot exist without the parent. | ✅ Child can exist independently of the parent. |
| **Lifetime** | Child is destroyed when the parent is destroyed. | Child's lifetime is independent of the parent. |
| **Ownership** | Parent owns the child exclusively. | Parent uses the child but doesn't own it exclusively. |

---

## 2. How would you differentiate between composition and aggregation?

| Composition | Aggregation |
|-------------|-------------|
| "**Whole-part**" relationship. | "**Has-a**" relationship. |
| Part **cannot exist** without the whole. | Part **can exist independently**. |
| Strong ownership — parent controls lifecycle. | Weak association — no lifecycle control. |
| Example: Car **has an** Engine (Engine dies with Car). | Example: Library **has** Books (Books exist beyond the Library). |

---

## 3. Give an example of composition in Java.

In composition, the `Engine` is created **inside** the `Car` and cannot exist independently.

```java
class Engine {
    private String type;

    Engine(String type) { this.type = type; }

    public String getType() { return type; }
}

class Car {
    private Engine engine; // Composition — Car owns Engine

    public Car() {
        this.engine = new Engine("V8"); // Engine created and controlled by Car
    }

    public Engine getEngine() { return engine; }
}

// Usage
Car car = new Car();
System.out.println(car.getEngine().getType()); // V8
// When car is destroyed, engine is also destroyed
```

---

## 4. Provide an example of aggregation in Java.

In aggregation, `Books` exist **independently** of the `Library`. Books can be added and removed, and they continue to exist even if the Library is destroyed.

```java
class Books {
    private String title;

    Books(String title) { this.title = title; }

    public String getTitle() { return title; }
}

class Library {
    private List<Books> books; // Aggregation — Library uses Books but doesn't own them

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Books book) { books.add(book); }
    public void removeBook(Books book) { books.remove(book); }
}

// Usage
Books book1 = new Books("Clean Code");
Books book2 = new Books("Effective Java");

Library library = new Library();
library.addBook(book1);
library.addBook(book2);
// book1 and book2 exist independently — they can be used elsewhere
```

---

## 5. What are the advantages of using composition over inheritance?

| Advantage | Description |
|-----------|-------------|
| **Flexibility** | Behavior of classes can be changed at runtime by swapping composed objects. |
| **Code reusability** | Promotes reuse without creating complex inheritance hierarchies. |
| **Loose coupling** | Avoids tight coupling issues that arise with deep inheritance. |
| **Testability** | Composed objects can be mocked or replaced easily in unit tests. |
| **Encapsulation** | Internal details of composed objects are hidden from the outer class. |

> **Design Principle:** "Favor composition over inheritance" — a core principle of object-oriented design.

---

## 6. How do you implement composition in Java?

Composition is implemented by:
1. **Including an instance** of one class as a field within another class.
2. **Initializing** the contained object inside the constructor of the containing class.
3. **Not exposing** setter methods that allow replacing the contained object.

```java
class Battery {
    int capacity;
    Battery(int capacity) { this.capacity = capacity; }
}

class Smartphone {
    private Battery battery; // Composition

    Smartphone(int batteryCapacity) {
        this.battery = new Battery(batteryCapacity); // Controlled by Smartphone
    }

    public int getBatteryCapacity() { return battery.capacity; }
    // No setter for battery — maintains composition semantics
}
```

---

## 7. Explain the concept of "ownership" in composition.

In composition:
- The **containing object (parent) owns** the contained object (child).
- The child has **no independent existence**.
- The child is **destroyed when the parent is destroyed**.

```java
class Heart {
    Heart() { System.out.println("Heart created"); }
}

class Human {
    private Heart heart; // Human owns Heart — strong ownership

    Human() {
        this.heart = new Heart(); // Created with Human
        System.out.println("Human created");
    }
}

// When the Human object is garbage collected, the Heart object is also eligible for GC
```

---

## 8. Can you have a circular reference in composition or aggregation?

| Relationship | Circular Reference | Recommendation |
|-------------|-------------------|----------------|
| **Composition** | Possible but **not recommended**. | Avoid — can cause memory leaks and complex lifecycle issues. |
| **Aggregation** | Possible and **often used**. | Common in data structures like trees, graphs, and linked lists. |

```java
// Circular aggregation — common in tree structures
class TreeNode {
    int value;
    TreeNode parent;   // Reference to parent (circular)
    List<TreeNode> children = new ArrayList<>();

    TreeNode(int value) { this.value = value; }
}
```

---

## 9. How do you handle memory management in composition and aggregation?

| Relationship | Memory Management |
|-------------|------------------|
| **Composition** | Handled **automatically** by the garbage collector. When the containing object is no longer referenced, the contained object is also eligible for GC. |
| **Aggregation** | Each object manages its **own memory independently**. When an object is no longer needed, removing references makes it eligible for GC. |

---

## 10. What are the key considerations when choosing between composition and aggregation?

| Consideration | Use Composition | Use Aggregation |
|---------------|----------------|-----------------|
| **Lifecycle** | Child must live and die with the parent. | Child has an independent lifecycle. |
| **Ownership** | Parent exclusively owns the child. | Parent uses but doesn't own the child. |
| **Relationship strength** | Strong "whole-part" relationship. | Weaker "has-a" relationship. |
| **Independence** | Child is meaningless without the parent. | Child can exist in multiple contexts. |

---

## 11. What are the benefits of using Composition and Aggregation?

| Benefit | Description |
|---------|-------------|
| **Improved lifecycle management** | Ensures proper creation and destruction of related objects. |
| **Modular design** | Breaks down complex objects into smaller, reusable components. |
| **Encapsulation** | Hides internal implementation details of contained objects. |
| **Loose coupling** | Reduces dependencies between unrelated parts of the system. |

---

## 12. How does composition relate to the "HAS-A" relationship?

Composition represents a **"HAS-A" relationship** where one class contains another as part of its state.

```
Car HAS-A Engine        → Composition (strong — Engine is part of Car)
Library HAS-A Books     → Aggregation (weak — Books exist independently)
Human HAS-A Heart       → Composition (strong — Heart is part of Human)
Department HAS-A Employee → Aggregation (weak — Employee exists independently)
```

---

## 13. What are the potential drawbacks of excessive composition?

| Drawback | Description |
|----------|-------------|
| **Tight coupling** | Over-composed objects can become tightly coupled, making them less reusable. |
| **Testing difficulty** | Deeply nested composed objects are harder to test in isolation. |
| **Complex hierarchies** | Can lead to complex object hierarchies that are difficult to manage. |
| **Lifecycle complexity** | Managing the creation and destruction of many composed objects can be challenging. |

---

## 14. How can composition and aggregation achieve immutability in Java?

**Composition for immutability:**

Make all member objects immutable — the whole object then becomes immutable.

```java
final class ImmutableCar {
    private final Engine engine; // Engine is immutable (final field)

    ImmutableCar(String engineType) {
        this.engine = new Engine(engineType); // Set once — never changed
    }

    public Engine getEngine() { return engine; }
    // No setters — immutable after construction
}
```

**Aggregation for immutability:**

Use immutable part objects that can be shared safely across multiple aggregating objects.

---

## 15. Can you achieve composition using public constructors for both whole and part objects?

Yes. Even with public constructors on both classes, composition semantics are maintained by **how the whole class manages the part object's lifecycle internally**.

```java
public class Processor {
    private String model;

    public Processor(String model) { // Public constructor
        this.model = model;
    }

    public String getModel() { return model; }
}

public class Computer {
    private Processor processor; // Composition — Computer owns Processor

    public Computer(String processorModel) {
        this.processor = new Processor(processorModel); // Computer controls lifecycle
    }

    public Processor getProcessor() { return processor; }
    // No setProcessor() — Processor cannot be replaced after construction
}
```

**Key points:**
- **Public constructors** allow external creation, but the `Computer` controls the `Processor` internally.
- **Controlled lifecycle** — `Processor` is instantiated within `Computer`'s constructor and bound to its lifetime.
- **Encapsulation** — no setter for `processor` means it cannot be swapped out, preserving the composition relationship.

---

## 16. How are Composition and Aggregation implemented in Java?

| Relationship | Implementation Techniques |
|-------------|--------------------------|
| **Composition** | Constructor injection — composed object created inside the constructor. No setter to replace it. |
| **Aggregation** | Member variables or method parameters — the aggregated object is passed in from outside. Setter methods may exist. |

```java
// Composition — created internally, no external replacement
class House {
    private final Foundation foundation; // Created inside constructor
    House() { this.foundation = new Foundation(); }
}

// Aggregation — passed from outside, can be replaced
class School {
    private Teacher teacher;   // Passed in from outside
    School(Teacher teacher) { this.teacher = teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; } // Can replace
}
```

---

## Composition vs Aggregation — Quick Reference

| Feature | Composition | Aggregation |
|---------|------------|-------------|
| **UML symbol** | Filled diamond (◆) | Hollow diamond (◇) |
| **Relationship** | "Part-of" / "Whole-part" | "Has-a" |
| **Lifecycle** | Child tied to parent. | Child independent. |
| **Ownership** | Parent owns child exclusively. | Parent uses child, doesn't own. |
| **Child independence** | ❌ Cannot exist without parent. | ✅ Can exist independently. |
| **Destruction** | Child destroyed with parent. | Child survives parent destruction. |
| **Example** | Car → Engine | Library → Books |
| **Implementation** | Constructor injection, no setter. | Constructor/setter injection from outside. |
| **Memory management** | Handled by GC with parent. | Each object manages its own memory. |

---

*Happy Coding! 🚀*
