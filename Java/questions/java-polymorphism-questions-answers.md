# Java - Polymorphism Questions & Answers

---

## 1. What is polymorphism in Java?

Polymorphism is the ability of an object to **take on multiple forms**. In Java, it allows objects of different classes to be treated as objects of a common superclass through inheritance, method overriding, and method overloading.

---

## 2. What are the two types of polymorphism in Java?

| Type | Also Known As | Mechanism |
|------|--------------|-----------|
| **Compile-time polymorphism** | Static polymorphism | Method overloading |
| **Runtime polymorphism** | Dynamic polymorphism | Method overriding |

---

## 3. Explain compile-time polymorphism with an example.

Compile-time polymorphism is achieved through **method overloading**. The compiler determines which method to call based on the number and type of arguments at compile time.

```java
public class Calculator {

    // Same method name, different parameter types
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(2, 3));         // Calls add(int, int)
        System.out.println(calc.add(2.5, 3.5));     // Calls add(double, double)
        System.out.println(calc.add(1, 2, 3));      // Calls add(int, int, int)
    }
}
```

---

## 4. Explain runtime polymorphism with an example.

Runtime polymorphism is achieved through **method overriding**. The method to be executed is determined at runtime based on the actual type of the object.

```java
class Animal {
    public void makeSound() {
        System.out.println("Generic animal sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Dog(); // Reference is Animal, object is Dog
        Animal animal2 = new Cat(); // Reference is Animal, object is Cat

        animal1.makeSound(); // Output: Woof! (determined at runtime)
        animal2.makeSound(); // Output: Meow! (determined at runtime)
    }
}
```

---

## 5. What is dynamic method dispatch?

Dynamic method dispatch is a mechanism in Java where **the method to be executed is determined at runtime** based on the actual type of the object — not the reference type. It allows a subclass to provide a specific implementation of a method defined in its superclass, enabling runtime polymorphism.

```java
Animal animal = new Dog(); // Reference: Animal, Object: Dog
animal.makeSound();        // JVM resolves to Dog.makeSound() at runtime
```

---

## 6. What is static method dispatch?

For **static methods**, method dispatch is determined at **compile-time** — not runtime. This is because static methods belong to the class itself, not to instances. The method is resolved based on the **compile-time type** of the reference variable.

```java
class Animal {
    public static void eat() { System.out.println("Generic animal eat"); }
}

class Dog extends Animal {
    public static void eat() { System.out.println("Dog eat"); } // Method hiding
}

Animal animal = new Dog();
animal.eat(); // Output: Generic animal eat (compile-time type is Animal)
```

> **Note:** Static methods use static binding — the reference type determines which method is called, not the object type.

---

## 7. How can you achieve polymorphism using interfaces?

Interfaces define abstract methods that concrete classes must implement. Objects of different classes that implement the same interface can be **treated polymorphically**.

```java
interface Shape {
    double calculateArea();
}

class Circle implements Shape {
    private double radius;
    Circle(double radius) { this.radius = radius; }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    private double length, width;
    Rectangle(double length, double width) { this.length = length; this.width = width; }

    @Override
    public double calculateArea() {
        return length * width;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(5), new Rectangle(4, 6) };

        for (Shape shape : shapes) {
            System.out.println("Area: " + shape.calculateArea()); // Polymorphic call
        }
    }
}
```

---

## 8. How do you handle situations where a method needs to access the specific type of an object at runtime in a polymorphic context?

Use the **`instanceof` operator** to check the object's type, then cast it to the specific type if necessary.

```java
void processAnimal(Animal animal) {
    if (animal instanceof Dog dog) {        // Pattern matching (Java 16+)
        dog.fetch();                        // Dog-specific method
    } else if (animal instanceof Cat cat) {
        cat.purr();                         // Cat-specific method
    } else {
        animal.makeSound();                 // Common method
    }
}
```

> **Best Practice:** Avoid excessive casting — it often signals a design issue. Prefer polymorphism via overriding or interfaces.

---

## 9. What is static binding and late binding in Java?

| Feature | Static Binding (Early Binding) | Late Binding (Dynamic Binding) |
|---------|-------------------------------|-------------------------------|
| **Resolution time** | Compile-time | Runtime |
| **Based on** | Reference type (declared type) | Actual object type |
| **Applies to** | `private`, `final`, `static` members | Virtual methods (instance methods) |
| **Performance** | Faster — resolved at compile time | Slight overhead — resolved at runtime |
| **Polymorphism** | ❌ No runtime polymorphism | ✅ Enables runtime polymorphism |

**Complete example demonstrating both:**

```java
class Animal {
    public void makeSound() {             // Instance method — dynamic binding
        System.out.println("Generic animal sound");
    }

    public static void eat() {            // Static method — static binding
        System.out.println("Generic animal eat");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {             // Overrides — uses dynamic binding
        System.out.println("Woof!");
    }

    public static void eat() {            // Hides — uses static binding
        System.out.println("Dog eat");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Animal();
        Animal animal2 = new Dog();

        // Dynamic Binding — resolved at runtime based on actual object type
        animal1.makeSound(); // Output: Generic animal sound
        animal2.makeSound(); // Output: Woof! (Dog's override is called)

        // Static Binding — resolved at compile time based on reference type
        animal1.eat();       // Output: Generic animal eat
        animal2.eat();       // Output: Generic animal eat (reference type is Animal)
    }
}
```

---

## Polymorphism — Quick Reference

| Concept | Type | Resolved At | Mechanism |
|---------|------|-------------|-----------|
| **Method Overloading** | Compile-time polymorphism | Compile-time | Different parameter lists |
| **Method Overriding** | Runtime polymorphism | Runtime | Subclass provides specific implementation |
| **Static Binding** | Early binding | Compile-time | `private`, `final`, `static` methods |
| **Dynamic Binding** | Late binding | Runtime | Instance (virtual) methods |
| **Interface polymorphism** | Runtime polymorphism | Runtime | Multiple classes implement same interface |
| **Dynamic method dispatch** | Runtime | Runtime | JVM selects method based on actual object type |

---

*Happy Coding! 🚀*
