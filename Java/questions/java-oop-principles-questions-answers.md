# Java - Object-Oriented Programming (OOP) Questions & Answers

---

## 1. Can you explain what OOP is and how Java supports it?

**Object-Oriented Programming (OOP)** is a programming paradigm that revolves around the concept of **objects** — instances of classes representing real-world entities. OOP aims to organize code into **reusable and modular components**, making it easier to understand, maintain, and scale.

### How Java Supports OOP

| OOP Feature | How Java Implements It |
|-------------|------------------------|
| **Classes & Objects** | Classes serve as blueprints; objects are instances that encapsulate data (fields) and behavior (methods). |
| **Inheritance** | `extends` keyword — subclasses inherit attributes and methods from superclasses. |
| **Polymorphism** | Method overriding and overloading — objects treated as instances of their parent class. |
| **Encapsulation** | Access modifiers (`public`, `private`, `protected`) control access to internal state. |
| **Abstraction** | `abstract` classes and `interface` types hide complexity and expose essential features. |

```java
// All four pillars demonstrated
public class Animal {                      // Abstraction — abstract template
    private String name;                   // Encapsulation — private field

    public Animal(String name) { this.name = name; }
    public String getName() { return name; } // Controlled access via getter

    public void makeSound() {             // Polymorphism — overridden by subclasses
        System.out.println("...");
    }
}

public class Dog extends Animal {         // Inheritance — Dog extends Animal
    public Dog(String name) { super(name); }

    @Override
    public void makeSound() {             // Polymorphism — runtime override
        System.out.println(getName() + " says: Woof!");
    }
}

// Polymorphic usage
Animal animal = new Dog("Rex");
animal.makeSound(); // Dog says: Woof! (runtime dispatch)
```

---

## 2. What are the main principles of OOP, and how are they implemented in Java?

---

### 1. Encapsulation

**Encapsulation** is the bundling of data (fields) and methods that operate on that data within a single class, with controlled access to internal state.

**Key characteristics:**
- Instance variables are declared `private`.
- Access is provided via `public` getters and setters.
- Internal implementation details are hidden from outside code.

```java
public class BankAccount {
    private double balance;         // Hidden — not directly accessible
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Controlled access — read only
    public double getBalance() { return balance; }

    // Controlled modification — with validation
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
}

// External code cannot access balance directly
BankAccount acc = new BankAccount("ACC001", 1000.0);
// acc.balance = -5000; // COMPILATION ERROR — private field
acc.deposit(500);       // ✅ Controlled access
```

**Benefits:**
- Prevents unauthorized data modification.
- Enables validation logic in setters.
- Makes code easier to maintain and refactor.

---

### 2. Inheritance

**Inheritance** allows a class (subclass) to acquire the properties and behaviors of another class (superclass), promoting **code reuse** and enabling hierarchical relationships.

**Key characteristics:**
- Use `extends` keyword for class inheritance.
- Use `implements` for interface implementation.
- Subclasses can override superclass methods.
- Java supports single inheritance for classes, multiple inheritance via interfaces.

```java
class Vehicle {
    String brand;
    int speed;

    Vehicle(String brand) { this.brand = brand; }

    void move() { System.out.println(brand + " is moving."); }
}

class Car extends Vehicle {           // Car inherits from Vehicle
    int numberOfDoors;

    Car(String brand, int doors) {
        super(brand);                 // Calls Vehicle constructor
        this.numberOfDoors = doors;
    }

    void honk() { System.out.println(brand + " goes beep!"); }
}

class ElectricCar extends Car {       // Multi-level inheritance
    int batteryLevel;

    ElectricCar(String brand, int doors, int battery) {
        super(brand, doors);
        this.batteryLevel = battery;
    }

    @Override
    void move() {
        System.out.println(brand + " moves silently on electricity.");
    }
}

ElectricCar tesla = new ElectricCar("Tesla", 4, 90);
tesla.move();  // Tesla moves silently on electricity. (overridden)
tesla.honk();  // Tesla goes beep! (inherited from Car)
```

**Benefits:**
- Reduces code duplication — common logic lives in the superclass.
- Enables a hierarchical classification of objects.
- Supports the "is-a" relationship.

---

### 3. Polymorphism

**Polymorphism** allows objects of different classes to be treated as objects of a common superclass, enabling **flexible and extensible method invocation** at runtime.

**Two types in Java:**

| Type | Mechanism | Resolved At |
|------|-----------|-------------|
| **Compile-time (static)** | Method overloading | Compile time |
| **Runtime (dynamic)** | Method overriding | Runtime |

**Compile-time polymorphism (Method Overloading):**

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}

Calculator calc = new Calculator();
calc.add(2, 3);       // int version
calc.add(2.5, 3.5);   // double version
```

**Runtime polymorphism (Method Overriding):**

```java
class Shape {
    double area() { return 0; }
}

class Circle extends Shape {
    double radius;
    Circle(double r) { radius = r; }
    @Override
    double area() { return Math.PI * radius * radius; }
}

class Rectangle extends Shape {
    double width, height;
    Rectangle(double w, double h) { width = w; height = h; }
    @Override
    double area() { return width * height; }
}

// Polymorphic array — treating all as Shape
Shape[] shapes = { new Circle(5), new Rectangle(4, 6) };
for (Shape s : shapes) {
    System.out.println("Area: " + s.area()); // Correct method called at runtime
}
// Output: Area: 78.54 | Area: 24.0
```

**Benefits:**
- Enables writing flexible and extensible code.
- Allows new subclasses to be added without changing existing code.
- Supports the Open/Closed Principle — open for extension, closed for modification.

---

### 4. Abstraction

**Abstraction** is the process of hiding complex implementation details and exposing only the **essential features** of an object. In Java, abstraction is achieved through **abstract classes** and **interfaces**.

**Two levels of abstraction:**

| Tool | Description |
|------|-------------|
| **Abstract class** | Partial implementation — can have both abstract and concrete methods. |
| **Interface** | Full abstraction — defines a contract that implementing classes must fulfill. |

**Abstract class example:**

```java
abstract class Animal {
    String name;

    Animal(String name) { this.name = name; }

    abstract void makeSound();          // Abstract — no implementation

    void breathe() {                    // Concrete — shared behavior
        System.out.println(name + " is breathing.");
    }
}

class Cat extends Animal {
    Cat(String name) { super(name); }

    @Override
    void makeSound() { System.out.println(name + " says: Meow!"); }
}
```

**Interface example:**

```java
interface Drawable {
    void draw();           // Abstract method
    default void describe() {
        System.out.println("I am a drawable object."); // Default method (Java 8+)
    }
}

interface Resizable {
    void resize(double factor);
}

class Square implements Drawable, Resizable { // Multiple interface implementation
    double side;

    Square(double side) { this.side = side; }

    @Override public void draw() { System.out.println("Drawing square with side: " + side); }
    @Override public void resize(double factor) { side *= factor; }
}

Drawable d = new Square(5);
d.draw();     // Drawing square with side: 5.0
d.describe(); // I am a drawable object.
```

**Benefits:**
- Reduces complexity by hiding implementation details.
- Enables programming to interfaces rather than concrete implementations.
- Supports the Dependency Inversion Principle.

---

## OOP Principles — Summary

| Principle | Core Idea | Java Mechanism | Key Benefit |
|-----------|-----------|----------------|-------------|
| **Encapsulation** | Bundle data + methods; restrict access. | `private` fields + getters/setters | Data protection and integrity. |
| **Inheritance** | Reuse and extend existing class behavior. | `extends`, `implements` | Code reuse, hierarchical design. |
| **Polymorphism** | One interface, many forms. | Method overriding + overloading | Flexibility and extensibility. |
| **Abstraction** | Hide complexity, expose essentials. | `abstract` classes, interfaces | Simplicity and loose coupling. |

---

*Happy Coding! 🚀*
