# Java - Inner Classes Questions & Answers

---

## 1. What is an inner class in Java?

An inner class in Java is a **class defined within another class**. It has access to all members (fields and methods) of the outer class, including private members.

---

## 2. How many types of inner classes are there in Java?

There are **four types** of inner classes:

| # | Type | Description |
|---|------|-------------|
| 1 | **Static nested class** | A `static` member of the outer class. |
| 2 | **Non-static inner class** | Belongs to an instance of the outer class. |
| 3 | **Local inner class** | Defined within a method. |
| 4 | **Anonymous inner class** | A nameless class defined and instantiated inline. |

---

## 3. Explain the difference between static and non-static inner classes.

| Feature | Static Nested Class | Non-static Inner Class |
|---------|--------------------|-----------------------|
| **Declaration** | `static class Inner { }` | `class Inner { }` |
| **Belongs to** | The **class** itself. | An **instance** of the outer class. |
| **Access outer members** | Only **static** members. | Both **static and non-static** members. |
| **Instantiation** | `new Outer.Inner()` | `outerObj.new Inner()` |
| **Memory** | No implicit reference to outer instance. | Holds implicit reference to outer instance. |

```java
class Outer {
    private int outerField = 10;
    private static int staticField = 20;

    static class StaticNested {
        void show() {
            System.out.println(staticField);  // ✅ Static member — OK
            // System.out.println(outerField); // ❌ Cannot access non-static
        }
    }

    class Inner {
        void show() {
            System.out.println(outerField);   // ✅ Both static and non-static — OK
            System.out.println(staticField);  // ✅ Static — OK
        }
    }
}
```

---

## 4. What is the purpose of using an inner class?

| Purpose | Description |
|---------|-------------|
| **Logical grouping** | Groups classes that are only used in one place. |
| **Encapsulation** | Hides implementation details within the outer class. |
| **Readability** | Keeps related code together for better maintainability. |
| **Access to outer class** | Inner classes can access private members of the outer class. |

---

## 5. Can an inner class access private members of its outer class?

Yes. Inner classes have **direct access to all members** of the outer class — including `private` fields and methods — because they are considered part of the outer class's scope.

```java
class Outer {
    private String secret = "Hidden data";

    class Inner {
        void reveal() {
            System.out.println(secret); // ✅ Direct access to private member
        }
    }
}
```

---

## 6. How do you instantiate an inner class from outside the outer class?

First create an instance of the outer class, then use it to create the inner class instance.

```java
Outer outerObj = new Outer();
Outer.Inner innerObj = outerObj.new Inner(); // Uses outer instance

// Static nested class — no outer instance needed
Outer.StaticNested staticObj = new Outer.StaticNested();
```

---

## 7. Can an inner class access local variables of the method it is defined in?

Yes, but the local variables must be **`final` or effectively final** (not modified after initialization). This is because local variables may be garbage collected after the method exits, so the inner class captures a copy.

```java
void myMethod() {
    final int localVar = 10;    // final — accessible
    int effectiveFinal = 20;    // effectively final — accessible

    class LocalInner {
        void show() {
            System.out.println(localVar);      // ✅ OK
            System.out.println(effectiveFinal); // ✅ OK
        }
    }

    new LocalInner().show();
}
```

---

## 8. Explain anonymous inner classes in Java.

Anonymous inner classes are **inner classes without a name**, defined and instantiated at the same time. They are typically used to implement interfaces or extend classes on-the-fly without creating a separate class.

```java
interface Greeter { void greet(); }

// Anonymous inner class — implements Greeter inline
Greeter g = new Greeter() {
    @Override
    public void greet() {
        System.out.println("Hello from anonymous class!");
    }
};
g.greet();

// Lambda equivalent (Java 8+)
Greeter lambdaGreeter = () -> System.out.println("Hello from lambda!");
```

---

## 9. How are nested classes different from inner classes?

| Term | Meaning |
|------|---------|
| **Nested class** | Broad term — any class defined within another class (includes all 4 types). |
| **Inner class** | Specifically refers to **non-static** nested classes. |

All inner classes are nested classes, but not all nested classes are inner classes (static nested classes are not inner classes).

---

## 10. What is the scope of an inner class in Java?

The scope of an inner class is **limited to the outer class**. However, an instance of the inner class can be passed outside the outer class's scope if the inner class is accessible (not private).

---

## 11. Can inner classes have their own methods and variables?

Yes. Inner classes can have their **own fields, methods, and constructors** just like any other class.

```java
class Outer {
    class Inner {
        private int value = 100;

        public int getValue() { return value; }
        public void display() { System.out.println("Inner value: " + value); }
    }
}
```

---

## 12. How do you access an inner class from outside its outer class?

Access it through an instance of the outer class (for non-static inner classes):

```java
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner(); // Non-static inner class
inner.display();

// Static nested class — no instance needed
Outer.StaticNested staticNested = new Outer.StaticNested();
```

---

## 13. Can an inner class be declared within a method?

Yes. These are called **local inner classes** and are scoped to the method where they are defined.

```java
void myMethod() {
    class LocalClass {
        void sayHello() { System.out.println("Hello from local class!"); }
    }
    new LocalClass().sayHello();
}
// LocalClass is NOT accessible outside myMethod
```

---

## 14. What is a nested interface in Java?

A **nested interface** is an interface declared within another class or interface. It can be static or non-static.

```java
class Outer {
    interface Printable {    // Nested interface — implicitly static
        void print();
    }
}

class Document implements Outer.Printable {
    @Override
    public void print() { System.out.println("Printing document..."); }
}
```

---

## 15. Explain the use of inner classes in Java Swing event handling.

In Java Swing, inner classes — particularly **anonymous inner classes** — are commonly used to implement event listeners inline, keeping the event handling code close to the UI component.

```java
JButton button = new JButton("Click Me");

// Anonymous inner class for event handling
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
    }
});

// Lambda equivalent (Java 8+)
button.addActionListener(e -> System.out.println("Button clicked!"));
```

---

## 16. Is it possible to declare an interface inside an inner class?

Yes. This creates a **nested interface** inside an inner class.

```java
class Outer {
    class Inner {
        interface NestedInterface {
            void doSomething();
        }
    }
}
```

---

## 17. Can an inner class implement multiple interfaces?

Yes. Inner classes follow the same rules as any other Java class — they can implement multiple interfaces.

```java
interface A { void methodA(); }
interface B { void methodB(); }

class Outer {
    class Inner implements A, B {
        @Override public void methodA() { System.out.println("Method A"); }
        @Override public void methodB() { System.out.println("Method B"); }
    }
}
```

---

## 18. Can a local inner class access non-final local variables of the enclosing method?

No. A local inner class can **only access local variables that are `final` or effectively final**. Non-final variables may be garbage collected after the method exits — capturing them would lead to inconsistencies.

```java
void method() {
    int x = 10;            // Effectively final — OK
    int y = 20;
    y = 30;                // Modified — NOT effectively final

    class Local {
        void show() {
            System.out.println(x); // ✅ OK
            // System.out.println(y); // ❌ COMPILATION ERROR — y is not effectively final
        }
    }
}
```

---

## 19. Can an anonymous inner class extend a class and implement an interface simultaneously?

No. In Java, an anonymous inner class can either:
- **Extend a class**, OR
- **Implement an interface**

But **not both simultaneously**.

```java
// ✅ Extending a class
Runnable r = new Thread() {
    @Override public void run() { }
};

// ✅ Implementing an interface
Runnable task = new Runnable() {
    @Override public void run() { }
};

// ❌ Both — not allowed
// new SomeClass() implements SomeInterface { } // INVALID
```

---

## 20. Can an inner class be declared as `private` or `protected`?

Yes. Inner classes can use **any access modifier** — `private`, `protected`, `public`, or default (package-private).

```java
class Outer {
    private class PrivateInner { }       // Only accessible within Outer
    protected class ProtectedInner { }   // Accessible within Outer and subclasses
    public class PublicInner { }         // Accessible from anywhere
    class DefaultInner { }               // Accessible within the same package
}
```

---

## 21. Can an anonymous inner class access static members of its outer class directly?

No — not without the class name. Anonymous inner classes can access **non-static** members through `this`, but **static members must be accessed using the outer class name**.

```java
class Outer {
    static int staticValue = 100;
    int instanceValue = 200;

    Runnable task = new Runnable() {
        @Override
        public void run() {
            // System.out.println(staticValue);      // ❌ Not directly
            System.out.println(Outer.staticValue);   // ✅ Using class name
            System.out.println(instanceValue);        // ✅ Non-static — OK
        }
    };
}
```

---

## 22. How do you differentiate members with the same name in inner and outer class?

Use **shadowing** with the outer class name as a prefix:
- `this.memberName` — refers to the **inner class** member.
- `OuterClass.this.memberName` — refers to the **outer class** member.

```java
class Outer {
    int value = 10; // Outer value

    class Inner {
        int value = 20; // Inner value — shadows outer value

        void display() {
            System.out.println(value);            // 20 — inner class member
            System.out.println(this.value);       // 20 — inner class member (explicit)
            System.out.println(Outer.this.value); // 10 — outer class member
        }
    }
}
```

---

## 23. How can you create a thread-safe inner class?

| Approach | Description |
|----------|-------------|
| **Make it static** | Static nested classes are inherently safer — no implicit reference to a shared outer instance. |
| **Synchronize methods** | If the inner class accesses shared outer resources, use `synchronized` on those methods. |

```java
class Outer {
    private int sharedResource = 0;

    // Thread-safe static nested class
    static class SafeStaticNested { }

    // Thread-safe member inner class with synchronized access
    class SafeInner {
        synchronized void increment() {
            sharedResource++; // Synchronized access to outer resource
        }
    }
}
```

---

## 24. How does serialization of inner classes work in Java?

When serializing a **non-static inner class**, the **outer class instance is also serialized** because the inner class holds an implicit reference to it. Considerations:
- The **outer class must also implement `Serializable`**.
- If the outer class is not serializable, serialization will fail.
- **Static nested classes** don't hold a reference to the outer class, so this issue doesn't apply.

```java
class Outer implements Serializable {
    int data = 42;

    class Inner implements Serializable {
        int innerData = 10;
        // Implicitly serializes Outer along with Inner
    }
}
```

> **Best Practice:** Prefer static nested classes if serialization is needed, to avoid serializing the outer instance unnecessarily.

---

## 25. Explain shadowing in inner classes and how to access shadowed members.

**Shadowing** occurs when an inner class declares a member with the **same name** as a member in the outer class. Java resolves this using:
- `this.memberName` — current inner class instance.
- `OuterClassName.this.memberName` — outer class instance.

```java
class Outer {
    String name = "Outer Name";

    class Inner {
        String name = "Inner Name"; // Shadows outer 'name'

        void display() {
            System.out.println(name);            // Inner Name — inner class field
            System.out.println(this.name);       // Inner Name — inner class field (explicit)
            System.out.println(Outer.this.name); // Outer Name — outer class field
        }
    }
}

// Usage
new Outer().new Inner().display();
// Output:
// Inner Name
// Inner Name
// Outer Name
```

---

## Inner Classes — Quick Reference

| Type | Declaration | Access to Outer | Instantiation |
|------|-------------|-----------------|---------------|
| **Static nested class** | `static class Inner { }` | Static members only. | `new Outer.Inner()` |
| **Non-static inner class** | `class Inner { }` | All members (static + instance). | `outerObj.new Inner()` |
| **Local inner class** | Inside a method | All outer members + effectively final locals. | `new LocalClass()` within the method |
| **Anonymous inner class** | Inline `new Type() { }` | All outer members + effectively final locals. | Defined and instantiated at once |

| Rule | Description |
|------|-------------|
| Inner class accesses outer private | ✅ Yes — full access. |
| Local variable access | Only `final` or effectively final. |
| Anonymous class: extend + implement | ❌ One or the other, not both. |
| Shadowing resolution | Use `OuterClass.this.member`. |
| Serialization | Non-static inner → outer instance serialized too. |
| Thread safety | Use `static` nested or `synchronized` methods. |

---

*Happy Coding! 🚀*