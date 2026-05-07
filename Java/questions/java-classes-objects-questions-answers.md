# Java - Classes & Objects Questions & Answers

---

## 1. What is a class in Java?

A class in Java is a **blueprint or template for creating objects**. It defines the properties (fields) and behaviors (methods) that objects of that type will have.

```java
class Car {
    // Properties (fields)
    String color;
    String model;
    int speed;

    // Behaviors (methods)
    void accelerate() { speed += 10; }
    void brake()      { speed -= 10; }
    void displayInfo() {
        System.out.println(model + " (" + color + ") at " + speed + " km/h");
    }
}
```

---

## 2. What is an object in Java?

An object is a **real-world entity** with its own set of properties and functionalities. In Java, an object is an **instance of a class** — it has **state** (fields) and **behavior** (methods).

```java
// Creating objects from the Car class
Car car1 = new Car();
car1.color = "Red";
car1.model = "Toyota";

Car car2 = new Car();
car2.color = "Blue";
car2.model = "Honda";

car1.accelerate();
car1.displayInfo(); // Toyota (Red) at 10 km/h

// car1 and car2 are separate objects with their own state
```

---

## 3. What is the `this` keyword in Java?

The `this` keyword refers to the **current instance of the class**. It is used to:

| Use Case | Description |
|----------|-------------|
| **Reference instance variables** | Differentiate instance variables from local variables with the same name. |
| **Call instance methods** | Call methods of the current object. |
| **Invoke constructors** | Call another constructor in the same class using `this()`. |

```java
class Employee {
    String name;
    int salary;

    // Differentiate instance var from parameter (same name)
    Employee(String name, int salary) {
        this.name = name;     // this.name = instance variable
        this.salary = salary; // salary = parameter
    }

    // Constructor chaining using this()
    Employee(String name) {
        this(name, 50000); // Calls Employee(String, int)
    }

    void display() {
        System.out.println(this.name + " earns " + this.salary); // this is optional here
    }
}
```

---

## 4. What are the four pillars of OOP in Java?

| Pillar | Description | Mechanism |
|--------|-------------|-----------|
| **Encapsulation** | Bundles data and methods together within a class, protecting data from direct access. | `private` fields + getters/setters |
| **Inheritance** | Allows new classes to inherit properties and behaviors from existing classes. | `extends` keyword |
| **Polymorphism** | Enables objects of different classes to respond differently to the same method call. | Method overriding, overloading |
| **Abstraction** | Hides implementation complexity, exposing only essential details. | `abstract` classes, interfaces |

```java
// Encapsulation
class BankAccount {
    private double balance;
    public double getBalance() { return balance; }
}

// Inheritance
class Dog extends Animal { }

// Polymorphism
Animal animal = new Dog();
animal.makeSound(); // Calls Dog's implementation at runtime

// Abstraction
abstract class Shape { abstract double area(); }
```

---

## 5. What is the difference between an object and a class?

| Feature | Class | Object |
|---------|-------|--------|
| **Definition** | A **blueprint** or template. | An **instance** created from the blueprint. |
| **Contains** | Structure — defines fields and methods. | Actual **data** — holds values for fields. |
| **Memory** | No memory allocated for fields at class definition. | Memory allocated when object is created. |
| **Existence** | Defined once. | Multiple objects can be created from one class. |
| **Example** | `class Car { }` | `Car myCar = new Car();` |

---

## 6. Can you have a class without a constructor in Java?

Yes. If no constructor is explicitly defined, Java automatically provides a **default no-argument constructor**. However, once any constructor is explicitly defined, the default constructor is **no longer provided automatically**.

```java
// No explicit constructor — Java provides default: Person() { }
class Person {
    String name;
    int age;
}
Person p = new Person(); // ✅ Works — default constructor used

// Explicit constructor — default is NOT provided
class Student {
    String name;
    Student(String name) { this.name = name; }
}
// new Student(); // ❌ COMPILATION ERROR — no default constructor
new Student("Alice"); // ✅ Works
```

---

## 7. Explain the `static` keyword with respect to classes and methods.

| Applied to | Meaning |
|-----------|---------|
| **Static variable** | Shared among **all instances** of the class — one copy in memory. Accessed via class name. |
| **Static method** | Belongs to the **class itself** — can be called without creating an instance. Can only access static members. |

```java
class Counter {
    static int count = 0;    // Static — shared across all instances
    int id;                  // Instance — unique per object

    Counter() {
        count++;             // Increment shared counter
        id = count;          // Assign unique ID
    }

    static int getCount() {  // Static method — no instance needed
        return count;
    }
}

new Counter(); new Counter(); new Counter();
System.out.println(Counter.getCount()); // 3 — called via class name
```

---

## 8. What is the purpose of the `final` keyword in Java?

| Applied to | Effect |
|-----------|--------|
| **`final` variable** | Acts as a **constant** — value cannot be changed after initialization. |
| **`final` method** | **Cannot be overridden** in subclasses. |
| **`final` class** | **Cannot be subclassed** — no class can extend it. |

```java
// Final variable
final int MAX = 100;
// MAX = 200; // COMPILATION ERROR

// Final method
class Base {
    public final void display() { System.out.println("Cannot override"); }
}

// Final class
final class Utility {
    // Cannot be extended
}
// class Extended extends Utility { } // COMPILATION ERROR
```

---

## 9. Explain garbage collection in Java and how it relates to objects.

Java has **automatic garbage collection**. When an object is no longer reachable from any reference variable, the garbage collector marks it for removal and frees the memory.

```java
Car car = new Car(); // Object created on heap
car = null;          // Reference removed — object eligible for GC
// GC will eventually free the memory

// Objects created in limited scope
void process() {
    Car temp = new Car(); // Object created
    // temp goes out of scope after method ends — eligible for GC
}
```

**Key points:**
- Garbage collection is **automatic** — developers don't need to manually free memory.
- The `finalize()` method (deprecated) was called before GC — not reliable.
- Use `try-with-resources` for deterministic resource cleanup.

---

## 10. How do you implement a deep copy of an object in Java?

Java does not have a built-in deep copy mechanism. Options include:

**Option 1 — Custom copy constructor or method:**

```java
class Address {
    String city;
    Address(String city) { this.city = city; }
    Address deepCopy() { return new Address(this.city); } // Deep copy
}

class Person {
    String name;
    Address address;

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Deep copy — creates new Address object
    Person deepCopy() {
        return new Person(this.name, this.address.deepCopy());
    }
}

Person original = new Person("Alice", new Address("New York"));
Person copy     = original.deepCopy();

copy.address.city = "London"; // Doesn't affect original
System.out.println(original.address.city); // New York — unchanged
```

**Option 2 — Serialization:**

```java
// Serialize to bytes then deserialize — creates a deep copy
// Requires implementing Serializable
```

**Option 3 — Third-party library (Apache Commons Lang):**

```java
Person copy = SerializationUtils.clone(original);
```

---

## 11. Explain the difference between static and non-static methods and variables.

| Feature | Static | Non-static (Instance) |
|---------|--------|----------------------|
| **Belongs to** | The **class** itself. | Each **object instance**. |
| **Memory** | One shared copy for all instances. | Separate copy per object. |
| **Accessed via** | Class name (e.g., `ClassName.member`). | Object reference (e.g., `obj.member`). |
| **Can access** | Only static members. | Both static and non-static members. |
| **`this` keyword** | ❌ Not available. | ✅ Available — refers to current instance. |

```java
class BankAccount {
    static double interestRate = 0.05; // Static — same for ALL accounts
    double balance;                     // Instance — unique per account
    String owner;

    BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    // Static method — class-level
    static double getInterestRate() {
        return interestRate;          // ✅ Only static members
        // return balance;            // ❌ Cannot access instance variable
    }

    // Instance method — object-level
    double calculateInterest() {
        return balance * interestRate; // ✅ Both static and instance members
    }
}

// Usage
System.out.println(BankAccount.getInterestRate());  // Via class name

BankAccount acc = new BankAccount("Alice", 1000);
System.out.println(acc.calculateInterest());        // Via object reference
```

---

## Classes & Objects — Quick Reference

| Concept | Description |
|---------|-------------|
| **Class** | Blueprint — defines structure (fields + methods). |
| **Object** | Instance of a class — holds actual data. |
| **`this`** | Refers to the current object instance. |
| **`static`** | Belongs to the class — shared, no instance needed. |
| **`final` variable** | Constant — cannot be reassigned. |
| **`final` method** | Cannot be overridden in subclasses. |
| **`final` class** | Cannot be subclassed. |
| **Default constructor** | Provided by Java if no constructor is defined. |
| **Garbage collection** | Automatic — removes unreachable objects from heap. |
| **Deep copy** | Creates independent copies of all nested objects. |
| **Shallow copy** | Copies references — nested objects are shared. |

| OOP Pillar | Key Mechanism |
|-----------|--------------|
| Encapsulation | `private` fields + getters/setters |
| Inheritance | `extends` keyword |
| Polymorphism | Method overriding + overloading |
| Abstraction | `abstract` classes + interfaces |

---

*Happy Coding! 🚀*
