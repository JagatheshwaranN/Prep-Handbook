# Java - Method Overriding Questions & Answers

---

## 1. What is method overriding in Java?

Method overriding in Java allows a **subclass to provide a specific implementation** for a method that is already defined in its superclass.

---

## 2. What are the rules for method overriding in Java?

| # | Rule |
|---|------|
| 1 | The method in the subclass must have the **same signature** as the method in the superclass. |
| 2 | The method must have the **same return type** (or a covariant subtype). |
| 3 | The **access level** of the overriding method cannot be more restrictive than the overridden method. |
| 4 | The overriding method **cannot throw broader checked exceptions** than the overridden method. |

---

## 3. Can the overridden method be private or static?

No. The overridden method:
- **Cannot be `private`** — private methods are not visible to subclasses.
- **Cannot be `static`** — static methods use static binding (compile-time), not dynamic dispatch.

---

## 4. Why is the `@Override` annotation used?

The `@Override` annotation indicates that a method is **intended to override** a method in the superclass. It helps the compiler catch errors if the method does not actually override a superclass method.

```java
class Animal {
    public void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("Dog barks");
    }
}
```

---

## 5. Explain the difference between method overloading and method overriding.

| Feature | Method Overloading | Method Overriding |
|---------|-------------------|-------------------|
| **Location** | Same class. | Subclass overrides superclass method. |
| **Parameters** | Must differ. | Must be identical. |
| **Return type** | Can differ. | Must be same or covariant. |
| **Binding** | Compile-time. | Runtime (dynamic dispatch). |
| **Purpose** | Multiple ways to call the same method. | Specific implementation of inherited method. |

---

## 6. What is the significance of the `super` keyword in method overriding?

The `super` keyword is used to **call the overridden method from the superclass**. It is useful when the subclass wants to extend or modify the superclass behavior while still utilizing the original functionality.

```java
class Animal {
    public void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        super.speak(); // Calls Animal's speak()
        System.out.println("Dog barks");
    }
}
```

---

## 7. Can a subclass override a private method in its superclass?

No. Private methods are **not visible to subclasses** and therefore cannot be overridden. The subclass can define a method with the same name, but it is a new method — not an override.

---

## 8. How does method overriding support polymorphism in Java?

Method overriding enables **polymorphic behavior** — objects of different classes can be treated as objects of a common superclass, with each executing its own specific implementation.

```java
Animal animal = new Dog();
animal.speak(); // Calls Dog's speak() at runtime — dynamic dispatch
```

---

## 9. What is covariant return type in method overriding?

Covariant return type allows a subclass method to have a **return type that is a subtype** of the return type declared in the superclass method. Introduced in Java 5.

```java
class Animal {
    public Animal getBaby() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    public Dog getBaby() { // Covariant return type — Dog is a subtype of Animal
        return new Dog();
    }
}
```

> **Note:** Covariant return types only apply to **non-primitive types**.

---

## 10. Explain dynamic method dispatch in method overriding.

Dynamic method dispatch is the process where the **appropriate overridden method is called at runtime** based on the actual type of the object — not the reference type. This is the foundation of polymorphism in Java.

```java
Animal a = new Dog();
a.speak(); // Runtime determines Dog.speak() is called, not Animal.speak()
```

---

## 11. What are the conditions required for method overriding?

- Same method **name**.
- Must exist in an **inheritance hierarchy**.
- Method must be **non-static**.
- Method must **not be private**.

---

## 12. Can you override a static method? Why or why not?

No. Static methods use **static binding** — resolved at compile-time based on the reference type, not the actual object type.

| Feature | Method Overriding | Static Methods |
|---------|------------------|----------------|
| **Binding** | Runtime (dynamic). | Compile-time (static). |
| **Inheritance** | Method is inherited and overridden. | Not inherited in the traditional sense. |
| **Polymorphism** | Supports runtime polymorphism. | Does not support runtime polymorphism. |
| **Overridable?** | ✅ Yes | ❌ No |

---

## 13. What happens if you change the return type of the overriding method?

The return type must be the **same or a covariant (subtype)**. Changing to an unrelated type causes a **compilation error**.

---

## 14. Can you override private methods?

No. Private methods are **not inherited** by subclasses, so there is nothing to override. A method with the same name in the subclass is treated as a completely new method.

---

## 15. How do you call the superclass version of an overridden method from the subclass?

Use the `super` keyword.

```java
class Superclass {
    public void display() {
        System.out.println("Superclass display");
    }
}

class Subclass extends Superclass {
    @Override
    public void display() {
        super.display(); // Calls Superclass.display()
        System.out.println("Subclass display");
    }
}
```

---

## 16. What are the access modifier rules during method overriding?

The access level of the overriding method **cannot be more restrictive** than the overridden method. You can widen access but not narrow it.

| Superclass Modifier | Allowed in Subclass |
|--------------------|---------------------|
| `public` | `public` only |
| `protected` | `protected` or `public` |
| `default` (package) | `default`, `protected`, or `public` |
| `private` | Cannot be overridden |

---

## 17. Can you throw a checked exception in the overriding method that wasn't declared in the superclass?

No. The overriding method can only throw:
- **Unchecked exceptions** (RuntimeExceptions) — no restrictions.
- The **same** or **narrower** checked exceptions than the superclass method.

```java
class Parent {
    void method() throws IOException { }
}

class Child extends Parent {
    @Override
    void method() throws FileNotFoundException { } // Valid — narrower exception
    // void method() throws Exception { } // INVALID — broader exception
}
```

---

## 18. Explain method overriding in the context of the `Object` class.

Every Java class inherits from `Object`. Common overridden methods include:

| Method | Purpose |
|--------|---------|
| `equals(Object obj)` | Defines logical equality between objects. |
| `hashCode()` | Returns a hash code for use in hash-based collections. |
| `toString()` | Returns a string representation of the object. |

---

## 19. What is Method Hiding?

Method hiding occurs when a **subclass defines a static method** with the same name and signature as a static method in the superclass. The subclass method **hides** (not overrides) the superclass method.

```java
class Parent {
    public static void printMessage() {
        System.out.println("Static method from Parent");
    }
}

class Child extends Parent {
    public static void printMessage() { // Hides Parent's static method
        System.out.println("Static method from Child");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        p.printMessage(); // Output: Static method from Parent (method hiding — based on reference type)
    }
}
```

> **Key difference:** With method **overriding**, the subclass method is called based on the object type. With method **hiding**, the method called is based on the **reference type**.

---

## 20. Can the overriding method throw a broader exception than the superclass method?

No. The overriding method must throw the **same exception type or a subtype**. This ensures compatibility with the superclass contract.

---

## 21. If an object of the subclass is created and an overridden method is called, which method executes?

The **subclass method** executes. Java uses dynamic dispatch — the method is resolved at **runtime** based on the actual object type, not the reference type.

---

## 22. What is the difference between hiding a method and overriding it?

| Feature | Method Overriding | Method Hiding |
|---------|------------------|---------------|
| **Method type** | Instance methods. | Static methods. |
| **Resolution** | Runtime (dynamic). | Compile-time (based on reference type). |
| **Polymorphism** | ✅ Supports runtime polymorphism. | ❌ Does not support polymorphism. |
| **Annotation** | `@Override` applicable. | `@Override` not applicable for hiding. |

---

## 23. Explain the concept of `final` methods in the context of method overriding.

A method marked `final` in the superclass **cannot be overridden** in any subclass. This ensures the implementation remains unchanged throughout the class hierarchy.

```java
class Superclass {
    public final void display() {
        System.out.println("Cannot be overridden");
    }
}

class Subclass extends Superclass {
    // COMPILATION ERROR — cannot override a final method
    // public void display() { }
}
```

---

## 24. A superclass has both a private and a public method with the same name. Can the subclass override the public method?

The subclass **can override the public method**. However, the private method is **not visible** and cannot be overridden. The private method in the superclass is a completely separate method with no relationship to the subclass's implementation.

---

## 25. What is the role of unchecked exceptions in method overriding?

Overriding methods can throw **unchecked exceptions (RuntimeExceptions)** even if the superclass method does not declare them — there are no restrictions on unchecked exceptions.

```java
class Superclass {
    void method() { }
}

class Subclass extends Superclass {
    @Override
    void method() throws NullPointerException { } // Valid — NullPointerException is unchecked
}
```

---

## 26. Discuss scenarios where the `super` keyword is necessary during method overriding.

**Scenario 1 — Calling the overridden superclass method:**

```java
class Superclass {
    void display() {
        System.out.println("Superclass display method");
    }
}

class Subclass extends Superclass {
    @Override
    void display() {
        super.display(); // Calls superclass method
        System.out.println("Subclass display method");
    }
}
// Output:
// Superclass display method
// Subclass display method
```

**Scenario 2 — Accessing shadowed superclass member variables:**

```java
class Superclass {
    String message = "Hello from Superclass";
    void display() { System.out.println(message); }
}

class Subclass extends Superclass {
    String message = "Hello from Subclass";

    @Override
    void display() {
        System.out.println(super.message); // Superclass variable
        super.display();                   // Superclass method
        System.out.println(message);       // Subclass variable
    }
}
// Output:
// Hello from Superclass
// Hello from Superclass
// Hello from Subclass
```

**Scenario 3 — Invoking a specific superclass constructor:**

```java
class Superclass {
    int value;
    Superclass(int value) { this.value = value; }
}

class Subclass extends Superclass {
    Subclass(int value, int additionalValue) {
        super(value); // Calls Superclass(int) constructor
        this.value += additionalValue;
    }

    void display() { System.out.println("Value: " + value); }
}
// new Subclass(5, 10).display() → Output: Value: 15
```

---

## 27. How is method overriding achieved when a class implements multiple interfaces with same-named methods?

When interfaces have the same method name but **different parameter lists**, the implementing class can provide separate overrides — no ambiguity exists since the parameter lists differ.

```java
interface Interface1 {
    void doSomething(String message);
}

interface Interface2 {
    void doSomething(int number);
}

class MyClass implements Interface1, Interface2 {
    @Override
    public void doSomething(String message) {
        System.out.println("Processing message: " + message);
    }

    @Override
    public void doSomething(int number) {
        System.out.println("Processing number: " + number);
    }
}
```

---

## 28. Class extends a class and implements an interface — both with the same method. Which is called?

**The method in the class (or inherited from the parent class) takes precedence** over the interface's default method.

```java
static class Base {
    public void display() { System.out.println("Base Display"); }
    public void show() { System.out.println("Base Show"); }
}

interface BAC {
    void display(int value);
    void show();
}

static class NonBase extends Base implements BAC {
    @Override
    public void display() { System.out.println("NonBase Display"); }

    @Override
    public void display(int value) { System.out.println(value); }

    @Override
    public void show() { System.out.println("NonBase Show"); }
}
```

**Resolution rules:**
1. Method defined in the **current class takes precedence** over interface defaults.
2. If ambiguity exists across interfaces, the class **must provide its own implementation**.

---

## 29. Explain the concept of "Bridge Methods" in generics and method overriding.

Bridge methods are **synthetic methods automatically generated by the Java compiler** to ensure compatibility between generics (which use type erasure) and method overriding.

**The problem:**
```java
class Node<T> {
    public void setData(T data) { System.out.println("Node: " + data); }
}

class MyNode extends Node<Integer> {
    @Override
    public void setData(Integer data) { System.out.println("MyNode: " + data); }
}
```

After type erasure, both methods become `setData(Object data)`, causing a signature conflict.

**The compiler's solution — a bridge method:**
```java
// Generated automatically by the compiler (invisible to programmer)
public void setData(Object data) {
    setData((Integer) data); // Casts and delegates to the specific override
}
```

**Key points:**
- Bridge methods are **invisible** to the programmer.
- They ensure correct **polymorphism** with generics.
- They maintain **type safety** through casting.

---

## 30. Discuss the impact of covariant return types on method overriding.

| Aspect | Description |
|--------|-------------|
| **Code Flexibility** | Subclasses can return a more specific type without callers needing to cast. |
| **Readability** | Clearly communicates the specific return type in subclass methods. |
| **Compatibility** | Available only in Java 5 and later. |
| **Potential pitfall** | Explicit casting may still be needed when assigning to a superclass reference. |

```java
Animal animal = new Dog();
Animal babyAnimal = animal.getBaby();  // Returns Dog, but typed as Animal
Puppy puppy = (Puppy) babyAnimal;      // Cast needed for subclass-specific methods
puppy.playFetch();
```

---

## 31. How does type erasure affect overriding a method with a generic return type?

Due to type erasure, generic type parameters are replaced with `Object` at runtime. This means the overridden method in the subclass must account for the raw type.

```java
class BaseClass<T> {
    T getValue() { return null; }
}

class DerivedClass<T> extends BaseClass<T> {
    @Override
    T getValue() { return null; }
    // At runtime (after erasure): Object getValue()
}
```

**Precaution:** Provide the generic type parameter at the class level to retain type safety and avoid unexpected `Object` returns.

---

## 32. How do Java 8 default methods in interfaces interact with method overriding?

Default methods allow **new methods to be added to interfaces without breaking existing implementations**.

```java
interface MyInterface {
    void regularMethod();

    default void defaultMethod() {
        System.out.println("Default method in MyInterface");
    }
}

class MyClass implements MyInterface {
    @Override
    public void regularMethod() {
        System.out.println("regularMethod in MyClass");
    }
    // defaultMethod is inherited — no need to override
}

class MySubClass extends MyClass {
    @Override
    public void defaultMethod() {
        System.out.println("Overridden default method in MySubClass");
    }
}
```

**Impact on class hierarchies:**
- ✅ **Backward compatibility** — existing implementations don't break.
- ✅ **Multiple inheritance** — a class can implement multiple interfaces with default methods.
- ✅ **Reusability** — shared behavior defined once in the interface.

---

## 33. How does method overriding interact with multithreading and synchronization?

When multiple threads access overridden methods on shared resources, synchronization is critical to prevent race conditions.

```java
class SharedResource {
    private int sharedValue = 0;

    public synchronized void increment() { // synchronized ensures thread safety
        sharedValue++;
    }

    public int getSharedValue() { return sharedValue; }
}

class MyThread extends Thread {
    private SharedResource sharedResource;

    public MyThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            sharedResource.increment();
        }
    }
}
```

**Key considerations:**
- Use `synchronized` to protect shared mutable state in overridden methods.
- Use `volatile` for visibility guarantees across threads.
- Subclass overriding a synchronized method should also synchronize if accessing shared state.

---

## 34. How does a private method in the superclass affect method overriding when called from a public method?

Private methods in a superclass are **not accessible or overridable** in subclasses. Even if a public method calls a private method, the private method's behavior cannot be changed by the subclass.

```java
class Superclass {
    public void publicMethod() {
        System.out.println("Public method in Superclass");
        privateMethod(); // Calls Superclass's private method
    }

    private void privateMethod() {
        System.out.println("Private method in Superclass");
    }
}

class Subclass extends Superclass {
    @Override
    public void publicMethod() {
        System.out.println("Overridden public method in Subclass");
        // privateMethod() is NOT accessible here
    }
}
```

This enforces **encapsulation** — implementation details in private methods are hidden from subclasses.

---

## 35. How does reflection interact with method overriding at runtime?

Reflection allows invoking overridden methods dynamically. The correct version is called based on the **actual runtime type** of the object.

```java
import java.lang.reflect.Method;

class BaseClass {
    public void overriddenMethod() { System.out.println("Overridden in BaseClass"); }
}

class Subclass extends BaseClass {
    @Override
    public void overriddenMethod() { System.out.println("Overridden in Subclass"); }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        Subclass instance = new Subclass();
        Method method = instance.getClass().getMethod("overriddenMethod");
        method.invoke(instance); // Output: Overridden in Subclass
    }
}
```

---

## 36. How can reflection bypass access modifiers or invoke overridden methods?

Use `setAccessible(true)` to bypass access modifiers via reflection.

```java
// Bypassing private access
Method privateMethod = BaseClass.class.getDeclaredMethod("privateMethod");
privateMethod.setAccessible(true); // Bypasses access modifier
privateMethod.invoke(instance);

// Invoking overridden protected method
Method protectedMethod = BaseClass.class.getDeclaredMethod("protectedMethod");
protectedMethod.invoke(instance);
```

> **⚠️ Caution:** Bypassing access modifiers via reflection is generally **not recommended** — it breaks encapsulation and introduces security risks. Use with caution.

---

## 37. Can you override methods in anonymous inner classes?

Yes. Anonymous inner classes commonly override methods to provide **one-time specific implementations**.

```java
interface MyInterface {
    void myMethod();
}

class MyClass {
    void display(MyInterface myInterface) {
        myInterface.myMethod();
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        // Anonymous inner class
        myClass.display(new MyInterface() {
            @Override
            public void myMethod() {
                System.out.println("Overridden in anonymous inner class");
            }
        });

        // Lambda expression (Java 8+)
        myClass.display(() -> System.out.println("Lambda for myMethod"));
    }
}
```

**Considerations:**

| Consideration | Description |
|---------------|-------------|
| **Limited reusability** | Anonymous classes are for one-time use. |
| **Final variables** | Local variables captured must be `final` or effectively final. |
| **No constructors** | Anonymous classes cannot have explicit constructors. |
| **Lambda alternative** | For single-method interfaces, lambdas (Java 8+) are preferred. |

---

## 38. Explain the scope of variables within anonymous inner classes.

Local variables from the enclosing scope must be **effectively final** to be accessible inside an anonymous inner class.

```java
interface MyInterface {
    void display();
}

public class Main {
    private int outerVariable = 10; // Instance variable — always accessible

    public void outerMethod() {
        final int localVariable = 20; // Must be final or effectively final

        MyInterface myInterface = new MyInterface() {
            @Override
            public void display() {
                System.out.println("Outer Variable: " + outerVariable); // ✅ Accessible
                System.out.println("Local Variable: " + localVariable); // ✅ Accessible (final)
            }
        };

        myInterface.display();
    }
}
```

**Key points:**

| Variable Type | Accessibility | Requirement |
|---------------|--------------|-------------|
| **Instance variables** | ✅ Always accessible. | No restriction. |
| **Local variables** | ✅ Accessible if final. | Must be `final` or effectively final. |
| **Mutable local variables** | ❌ Not accessible. | Would cause a compilation error. |

---

## Method Overriding — Quick Reference

| Rule | Allowed? |
|------|----------|
| Same method signature | ✅ Required |
| Same or covariant return type | ✅ Required |
| More restrictive access modifier | ❌ Not allowed |
| Broader checked exception | ❌ Not allowed |
| Unchecked exception (new) | ✅ Allowed |
| Override `private` method | ❌ Not allowed |
| Override `static` method | ❌ Not allowed (method hiding instead) |
| Override `final` method | ❌ Not allowed |
| Use `@Override` annotation | ✅ Recommended |
| Call superclass method via `super` | ✅ Allowed |

---

*Happy Coding! 🚀*
