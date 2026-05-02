# Java - Type Casting Questions & Answers

---

## 1. What is type casting in Java?

Type casting in Java is the process of **converting one data type into another**. In object-oriented programming, it refers to treating an object of one class as another class.

| Type | Description |
|------|-------------|
| **Implicit (automatic)** | Done by the compiler — e.g., upcasting, widening primitives. |
| **Explicit (manual)** | Done by the developer using the cast operator `(Type)`. |

---

## 2. What is the difference between upcasting and downcasting?

| Feature | Upcasting | Downcasting |
|---------|-----------|-------------|
| **Direction** | Subclass → Superclass | Superclass → Subclass |
| **Safety** | ✅ Always safe. | ⚠️ May throw `ClassCastException`. |
| **Explicit cast needed?** | ❌ No — done automatically by compiler. | ✅ Yes — requires explicit `(SubClass)` cast. |
| **Example** | `Animal a = new Dog();` | `Dog d = (Dog) a;` |

---

## 3. How do you perform upcasting in Java?

Upcasting is performed **automatically by the compiler**. No explicit cast is required.

```java
class Animal { }
class Dog extends Animal { }

Animal obj = new Dog(); // Upcasting — automatic, always safe
```

After upcasting, the reference variable `obj` can only access methods defined in `Animal` (not `Dog`-specific methods), unless overridden.

---

## 4. How do you perform downcasting in Java?

Downcasting requires an **explicit cast** using the cast operator `(SubClass)`.

```java
Animal obj1 = new Dog();         // Upcasting
Dog obj2 = (Dog) obj1;           // Downcasting — explicit cast required
obj2.fetch();                    // Now Dog-specific methods are accessible
```

> **⚠️ Risk:** If `obj1` is not actually a `Dog` at runtime, a `ClassCastException` will be thrown.

---

## 5. What is the `instanceof` operator used for in Java?

The `instanceof` operator checks whether an **object is an instance of a particular class or interface**. It returns `true` if it is, `false` otherwise.

```java
Animal animal = new Dog();

System.out.println(animal instanceof Dog);    // true
System.out.println(animal instanceof Animal); // true
System.out.println(animal instanceof Cat);    // false
```

---

## 6. How is the `instanceof` operator related to type casting?

`instanceof` is used **before downcasting** to ensure the cast is safe and prevent `ClassCastException`.

```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal; // Safe — confirmed to be a Dog
    dog.fetch();
}
```

> **Best Practice:** Always check with `instanceof` before downcasting.

---

## 7. Can you upcast and downcast primitive data types in Java?

No. Primitive types **cannot be upcasted or downcasted** like objects. They use **widening** or **narrowing conversions** instead:

| Conversion | Description | Example |
|------------|-------------|---------|
| **Widening** | Smaller type → Larger type (implicit). | `int i = 5; double d = i;` |
| **Narrowing** | Larger type → Smaller type (explicit). | `double d = 5.7; int i = (int) d;` |

---

## 8. Can you upcast and downcast between unrelated classes?

No. Casting is only valid between classes in an **inheritance hierarchy** (superclass–subclass). Attempting to cast unrelated classes results in a **compile-time error** or a `ClassCastException` at runtime.

```java
class Cat { }
class Dog { }

Dog dog = new Dog();
// Cat cat = (Cat) dog; // COMPILATION ERROR — no relationship between Cat and Dog
```

---

## 9. What happens if you downcast an object to an unrelated class?

A **`ClassCastException`** is thrown at runtime because the JVM cannot perform the type conversion due to the lack of an inheritance relationship.

```java
Animal animal = new Dog();
Cat cat = (Cat) animal; // ClassCastException at runtime!
```

---

## 10. How do you ensure safe downcasting in Java?

Use `instanceof` to verify the object's type before casting.

```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal;  // Safe downcast
    dog.fetch();
} else {
    System.out.println("Not a Dog — cannot downcast.");
}
```

**Java 16+ pattern matching shortcut:**

```java
if (animal instanceof Dog dog) { // Pattern matching — cast + check in one step
    dog.fetch();
}
```

---

## 11. How do you safely downcast an interface reference to a specific implementing class?

Check the type with `instanceof` before downcasting, and handle the case where neither subtype matches.

```java
public interface Drawable {
    void draw();
}

public static class Circle implements Drawable {
    @Override public void draw() { System.out.println("Draw a Circle"); }
}

public static class Square implements Drawable {
    @Override public void draw() { System.out.println("Draw a Square"); }
}

public static void main(String[] args) {
    Drawable drawable = new Circle();

    if (drawable instanceof Circle) {
        Circle circle = (Circle) drawable;
        circle.draw(); // Draw a Circle
    } else if (drawable instanceof Square) {
        Square square = (Square) drawable;
        square.draw();
    } else {
        System.out.println("Unknown Drawable type.");
    }
}
```

---

## 12. Explain the difference between casting `int` to `double` vs. casting `Integer` to `Double`.

| Operation | Steps | Description |
|-----------|-------|-------------|
| `int` → `double` (primitive widening) | 1 step | The `int` value is automatically converted to `double` — no object creation. |
| `Integer` → `Double` (object casting) | 2 steps | **Unboxing:** `Integer` → `int`, then **Autoboxing:** `int` → `Double`. |

```java
// Primitive widening — 1 step
int i = 5;
double d = i;          // Implicit widening — no cast needed

// Object casting — 2 steps (unboxing + autoboxing)
Integer intObj = 5;
Double doubleObj = intObj.doubleValue(); // Unboxed to int, then autoboxed to Double
// Double doubleObj = (Double)(Object) intObj; // Direct cast — compiles but risky
```

> **Note:** Casting an `Integer` directly to `Double` (`(Double)(Integer)`) is not valid. Use `.doubleValue()` or explicit conversion instead.

---

## 13. How does type casting relate to polymorphism in Java?

| Concept | Role |
|---------|------|
| **Polymorphism** | Enables treating objects of different subclasses through a common superclass interface. Methods are resolved at runtime based on the actual object type. |
| **Type casting** | Allows accessing subclass-specific methods when a superclass reference is used. |

They work together for flexible OOP:

```java
Animal animal = new Dog();  // Polymorphism — treat Dog as Animal
animal.makeSound();          // Runtime: Dog's makeSound() is called

if (animal instanceof Dog dog) {
    dog.fetch();             // Type casting — access Dog-specific method
}
```

---

## 14. Are there situations where you should avoid downcasting? How?

Yes. **Minimize downcasting** due to potential runtime errors. Alternatives:

| Alternative | Description |
|-------------|-------------|
| **Use interfaces/abstract classes** | Design code to rely on interfaces for common functionality. Subclasses implement specific behaviors without requiring downcasting in the main code. |
| **`instanceof` checks** | Verify the type before performing subclass-specific operations. |
| **Visitor pattern** | Use the Visitor design pattern to add new operations without downcasting. |
| **Polymorphic method calls** | Add behavior to the superclass or interface so downcasting is unnecessary. |

---

## 15. What are the performance implications of casting in Java?

| Aspect | Impact |
|--------|--------|
| **Upcasting** | No performance overhead — handled at compile time. |
| **Downcasting** | Small overhead — JVM performs a runtime type check (`ClassCastException` check). |
| **Frequent downcasting** | Can impact performance due to repeated runtime checks. |
| **Autoboxing/Unboxing** | Slight overhead compared to direct primitive operations. |

> **Best Practice:** Minimize downcasting in performance-critical code. Design with interfaces and polymorphism to avoid it.

---

## 16. Differentiate between `instanceof` and `==` for type checking during downcasting.

| Feature | `instanceof` | `==` (equality operator) |
|---------|-------------|--------------------------|
| **Checks** | Whether an object is an **instance of a class** or its subclass hierarchy. | Whether two references point to the **same object in memory**. |
| **Use in downcasting** | ✅ Safe — prevents `ClassCastException`. | ❌ Not suitable for type checking. |
| **Returns** | `true` if object is of the specified type (or subtype). | `true` only if both references point to identical object. |

```java
Animal a1 = new Dog();
Animal a2 = a1;

System.out.println(a1 instanceof Dog); // true — type check
System.out.println(a1 == a2);          // true — same object reference
System.out.println(a1 instanceof Cat); // false — not a Cat

Dog d = new Dog();
System.out.println(a1 == d);           // false — different objects, even if same type
```

---

## Type Casting — Quick Reference

| Concept | Description |
|---------|-------------|
| **Upcasting** | Subclass → Superclass. Implicit, always safe. |
| **Downcasting** | Superclass → Subclass. Explicit, may throw `ClassCastException`. |
| **`instanceof`** | Check type before downcasting to ensure safety. |
| **Pattern matching** | `instanceof Dog dog` — Java 16+, combines check and cast. |
| **`ClassCastException`** | Thrown at runtime when downcasting fails. |
| **Widening (primitives)** | Smaller type → Larger type. Implicit. `int → double` |
| **Narrowing (primitives)** | Larger type → Smaller type. Explicit. `double → int` |
| **Autoboxing** | `int → Integer` — automatic wrapping. |
| **Unboxing** | `Integer → int` — automatic unwrapping. |
| **Unrelated class cast** | ❌ Compile-time error (or runtime `ClassCastException`). |

---

*Happy Coding! 🚀*
