# Java - Method Overloading Questions & Answers

---

## 1. What is Method Overloading?

Method Overloading is a feature in Java that allows a class to have **multiple methods with the same name but different parameters**.

---

## 2. How is Method Overloading different from Method Overriding?

| Feature | Method Overloading | Method Overriding |
|---------|-------------------|-------------------|
| **Location** | Same class. | Subclass overrides a method from its superclass. |
| **Parameters** | Must differ (number, type, or order). | Must be identical to the parent method. |
| **Return type** | Can be the same or different. | Must be the same (or covariant). |
| **Binding** | Compile-time (static binding). | Runtime (dynamic binding). |
| **Purpose** | Provides multiple ways to call the same method. | Provides a specific implementation of an inherited method. |

---

## 3. What are the criteria for method overloading?

Method overloading is based on:
- **Number** of parameters
- **Type** of parameters
- **Order** of parameters

> **Note:** Return types and access modifiers are **not** considered during method overloading.

---

## 4. Can we overload methods by changing the return type?

No. Method overloading is **not based on return type**. Overloaded methods must differ in the number or type of parameters. Changing only the return type results in a **compilation error**.

---

## 5. Give an example of method overloading in Java.

```java
public class Calculator {

    // Two integer parameters
    public int add(int a, int b) {
        return a + b;
    }

    // Three integer parameters
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Two double parameters
    public double add(double a, double b) {
        return a + b;
    }

    // String concatenation
    public String add(String a, String b) {
        return a + b;
    }
}
```

---

## 6. Explain the concept of Varargs in method overloading.

Varargs (variable-length argument lists) allow a method to accept a **variable number of arguments**. You can overload a method by including a varargs parameter.

```java
public class Printer {

    // Fixed parameter
    public void print(String message) {
        System.out.println(message);
    }

    // Varargs - accepts any number of strings
    public void print(String... messages) {
        for (String msg : messages) {
            System.out.println(msg);
        }
    }
}
```

> **Note:** The compiler prefers the **non-varargs method** when the number of arguments matches exactly.

---

## 7. How does the compiler determine which method to call during method overloading?

The compiler determines the method to call based on this priority order:

1. **Exact match** — finds a method with parameters exactly matching the argument types.
2. **Widening** — converts smaller types to larger ones (e.g., `int` → `long`).
3. **Autoboxing** — converts primitive types to wrapper types (e.g., `int` → `Integer`).
4. **Varargs** — considered last when no other match is found.

---

## 8. Can methods with the same parameters but different return types be overloaded?

No. Methods with the **same parameters and different return types cannot be overloaded**. This results in a **compilation error**.

```java
// COMPILATION ERROR - cannot overload by return type alone
public int getValue() { return 1; }
public double getValue() { return 1.0; } // Error!
```

---

## 9. Explain the role of autoboxing and widening in method overloading.

| Mechanism | Description |
|-----------|-------------|
| **Autoboxing** | Converts primitive types to their corresponding wrapper classes (e.g., `int` → `Integer`). |
| **Widening** | Converts smaller data types to larger ones (e.g., `int` → `long`, `float` → `double`). |

Both allow the compiler to match arguments with method parameters when no exact match exists.

---

## 10. Can you overload methods based on the order of parameters?

**Only if the parameter types differ.** The order of parameters alone (with the same types) cannot be used for overloading — the methods must have different parameter types in different positions.

```java
// Valid - different types in different positions
public void method(int a, String b) { }
public void method(String a, int b) { }

// NOT valid - same types, same count
public void method(int a, int b) { }
// public void method(int b, int a) { } // Duplicate!
```

---

## 11. What happens when two overloaded methods differ only in varargs vs. fixed parameters?

The **method without varargs** is preferred when the number of arguments matches exactly. Varargs is considered **less specific** and is chosen only when no other match exists.

```java
public void show(int a) { System.out.println("Fixed: " + a); }
public void show(int... a) { System.out.println("Varargs"); }

show(5); // Calls show(int a) - fixed method preferred
```

---

## 12. With `int` and `long` overloaded methods, which is called when passing an `int` argument?

The method with the **`int` parameter** is called — it is the most specific match. Java does **not** widen to `long` when an exact match exists.

```java
public void method(int a) { System.out.println("int method"); }
public void method(long a) { System.out.println("long method"); }

method(42); // Calls method(int a) → "int method"
```

---

## 13. How does autoboxing and unboxing affect method overloading?

Autoboxing and unboxing can cause **ambiguity** when both primitive and wrapper type methods exist.

```java
public void process(int value) { System.out.println("int"); }
public void process(Integer value) { System.out.println("Integer"); }

process(10);        // Exact match → process(int)
process(Integer.valueOf(10)); // Exact match → process(Integer)
```

---

## 14. With `Object` and `String` overloaded methods, which is called when passing `null`?

The method with the **`String` parameter** is called. Java chooses the **most specific type** — `String` is more specific than `Object`.

```java
public void method(Object obj) { System.out.println("Object"); }
public void method(String str) { System.out.println("String"); }

method(null); // Calls method(String str) → "String"
```

---

## 15. Can you overload methods with different access modifiers?

Yes. **Access modifiers are not considered** during method overloading. You can have overloaded methods with `public`, `private`, `protected`, or default access.

```java
public void display(String msg) { }
private void display(int num) { }    // Valid overload
protected void display(double d) { } // Valid overload
```

---

## 16. With `int[]` and `int...` (varargs) overloaded methods, which is called with multiple `int` arguments?

The **varargs method** is called when multiple individual `int` arguments are passed, since `int[]` requires an explicit array.

```java
public void method(int[] arr) { System.out.println("array"); }
public void method(int... nums) { System.out.println("varargs"); }

method(1, 2, 3);         // Calls method(int... nums) → "varargs"
method(new int[]{1,2,3}); // Calls method(int[] arr) → "array"
```

---

## 17. With `double` and `float` overloaded methods, which is called when passing a `float` argument?

The method with the **`float` parameter** is called — it is the most specific match. Java does not widen to `double` when an exact match exists.

```java
public void method(float f) { System.out.println("float method"); }
public void method(double d) { System.out.println("double method"); }

method(3.14f); // Calls method(float f) → "float method"
```

---

## 18. Can you overload methods by changing only the return type using generics?

No. Changing only the return type — even with generics — **does not create a unique method signature**. It results in a **compilation error**.

---

## 19. Scenario: Overloaded Methods with Inheritance

```java
public class BaseClass {
    public void display(String message) {
        System.out.println("BaseClass: " + message);
    }

    public void display(int number) {
        System.out.println("BaseClass: " + number);
    }
}

public class SubClass extends BaseClass {
    @Override
    public void display(String message) {
        System.out.println("SubClass: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        BaseClass baseObject = new BaseClass();
        SubClass subObject = new SubClass();

        baseObject.display("Hello");  // BaseClass: Hello
        baseObject.display(42);       // BaseClass: 42

        System.out.println("-------------");

        subObject.display("World");   // SubClass: World (overridden)
        subObject.display(42);        // BaseClass: 42 (inherited, not overridden)
    }
}
```

**Key rules:**
- `SubClass` **overrides** `display(String)` — runtime polymorphism applies.
- `SubClass` **inherits** `display(int)` from `BaseClass` — not overridden.
- Both overloading and overriding can coexist in an inheritance hierarchy.

---

## 20. Real-world Scenario: API Design with Method Overloading

```java
public class MathUtility {

    // Addition for integers
    public static int add(int a, int b) {
        return a + b;
    }

    // Addition for doubles
    public static double add(double a, double b) {
        return a + b;
    }

    // Addition for three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    // Addition for three doubles
    public static double add(double a, double b, double c) {
        return a + b + c;
    }

    // Average of integer array
    public static double calculateAverage(int[] numbers) {
        if (numbers.length == 0)
            throw new IllegalArgumentException("Array must not be empty");
        int sum = 0;
        for (int num : numbers) sum += num;
        return (double) sum / numbers.length;
    }

    // Average of double array
    public static double calculateAverage(double[] numbers) {
        if (numbers.length == 0)
            throw new IllegalArgumentException("Array must not be empty");
        double sum = 0;
        for (double num : numbers) sum += num;
        return sum / numbers.length;
    }
}
```

**Best practices for API design with overloading:**
- Use consistent naming that clearly reflects the purpose.
- Ensure each overloaded method is logically related.
- Avoid ambiguous overloads that confuse the compiler or the developer.
- Document parameter differences clearly.

---

## 21. Scenario: Overloaded Constructors and Methods

```java
public class Shape {
    private double area;

    // Generic shape
    public Shape() {
        System.out.println("Creating a generic shape.");
    }

    // Circle - one parameter
    public Shape(double radius) {
        calculateCircleArea(radius);
    }

    // Rectangle - two parameters
    public Shape(double length, double width) {
        calculateRectangleArea(length, width);
    }

    private void calculateCircleArea(double radius) {
        area = Math.PI * Math.pow(radius, 2);
        System.out.println("Area of the circle: " + area);
    }

    private void calculateRectangleArea(double length, double width) {
        area = length * width;
        System.out.println("Area of the rectangle: " + area);
    }

    public void displayArea() {
        System.out.println("Current area: " + area);
    }

    public static void main(String[] args) {
        Shape genericShape = new Shape();          // Generic shape
        Shape circle      = new Shape(5.0);        // Circle
        Shape rectangle   = new Shape(3.0, 4.0);   // Rectangle
    }
}
```

**Compiler resolution:** The compiler matches the constructor call based on the number and types of arguments — `Shape()`, `Shape(double)`, and `Shape(double, double)` are all distinct signatures.

---

## 22. Complex Scenario: Generics and Method Overloading

```java
import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        String[] array1 = {"Hello", ", ", "World"};
        System.out.println(concatenate(array1));   // Calls String[] version

        Character[] array2 = {'J', 'A', 'V', 'A'};
        System.out.println(concatenate(array2));   // Calls generic T[] version
        printArray(array2);

        Integer[] array3 = {1, 2, 3, 4, 5};
        System.out.println(calculateSum(array3));  // Output: 15.0
    }

    // Generic print
    public static <T> void printArray(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    // Generic sum - bounded to Number types
    public static <T extends Number> double calculateSum(T[] array) {
        double sum = 0.0;
        for (T element : array) {
            sum += element.doubleValue();
        }
        return sum;
    }

    // Specific String[] version - preferred over generic when String[] is passed
    public static String concatenate(String[] array) {
        StringBuilder result = new StringBuilder();
        for (String str : array) result.append(str);
        return result.toString();
    }

    // Generic T[] version - called for non-String arrays
    public static <T> String concatenate(T[] array) {
        StringBuilder result = new StringBuilder();
        for (T item : array) result.append(item.toString());
        return result.toString();
    }
}
```

**Key consideration:** The **specific `String[]` version** is preferred over the generic `T[]` version when a `String[]` is passed — Java always resolves to the most specific method.

---

## 23. Scenario: Overloaded Methods with Autoboxing and Widening

```java
public class NumberProcessor {

    public static void processNumber(int value) {
        System.out.println("Processing primitive int: " + value);
    }

    public static void processNumber(Integer value) {
        System.out.println("Processing Integer: " + value);
    }

    public static void main(String[] args) {
        int primitiveValue = 42;
        processNumber(primitiveValue);  // Exact match → processNumber(int)

        Integer integerValue = 55;
        processNumber(integerValue);    // Exact match → processNumber(Integer)

        byte byteValue = 10;
        processNumber(byteValue);       // Widened: byte → int → processNumber(int)
    }
}
```

**Method resolution order:**
1. **Exact match** → preferred always.
2. **Widening** → `byte` widens to `int`.
3. **Autoboxing** → primitive to wrapper if no other match.
4. **Varargs** → last resort.

---

## 24. Complex Scenario: Overloading with Multiple Inheritance

**Challenge — Name Collision and Resolution Ambiguity:**

```java
interface Shape {
    void draw();
    void draw(String color);
}

interface Drawable {
    void draw(int width, int height);
    void draw(String color); // Same signature as Shape!
}
```

**Resolution 1 — Explicit implementation in implementing class:**

```java
class ShapeDrawer implements Shape, Drawable {

    @Override
    public void draw() {
        System.out.println("Drawing a generic shape.");
    }

    @Override
    public void draw(String color) {
        System.out.println("Drawing with color: " + color);
    }

    @Override
    public void draw(int width, int height) {
        System.out.println("Drawing with width: " + width + ", height: " + height);
    }

    // Explicit unique method for combined parameters
    public void draw(String color, int width, int height) {
        System.out.println("Drawing with color: " + color + ", width: " + width + ", height: " + height);
    }
}
```

**Resolution 2 — Method Renaming in interfaces:**

```java
interface Shape {
    void draw();
    void drawWithColor(String color); // Renamed to avoid conflict
}

interface Drawable {
    void draw(int width, int height);
    void drawWithColor(String color); // Renamed to avoid conflict
}
```

**Key challenges:**

| Challenge | Description | Resolution |
|-----------|-------------|------------|
| **Name Collision** | Same method name in multiple interfaces with overlapping signatures. | Provide explicit implementations in the implementing class. |
| **Resolution Ambiguity** | Compiler cannot determine which interface's method to call. | Rename conflicting methods in the interfaces. |

---

## 25. Scenario: Overloaded Methods with Primitive and Wrapper Types

```java
public class NumberUtil {

    public void processNumber(int number) {
        System.out.println("Processing int: " + number);
    }

    public void processNumber(Integer number) {
        System.out.println("Processing Integer: " + number);
    }
}

// Usage
NumberUtil util = new NumberUtil();

util.processNumber(10);              // Exact match → processNumber(int)
util.processNumber(Integer.valueOf(20)); // Exact match → processNumber(Integer)
```

**Method resolution steps:**

| Step | Mechanism | Description |
|------|-----------|-------------|
| 1 | **Exact Match** | Compiler finds a method with the exact parameter type. |
| 2 | **Autoboxing** | If no exact match, primitives are boxed to wrapper types. |
| 3 | **Widening Conversion** | Smaller primitive types are widened to larger ones. |

---

## 26. Real-world Scenario: Database Interaction

```java
public class DatabaseUtil {

    public void insertData(String tableName, String columnName, String value) {
        System.out.println("Inserting String: " + value + " into " + tableName + "." + columnName);
    }

    public void insertData(String tableName, String columnName, int value) {
        System.out.println("Inserting Integer: " + value + " into " + tableName + "." + columnName);
    }

    public void insertData(String tableName, String columnName, Date value) {
        System.out.println("Inserting Date: " + value + " into " + tableName + "." + columnName);
    }

    public void insertData(String tableName, String columnName, double value) {
        System.out.println("Inserting Double: " + value + " into " + tableName + "." + columnName);
    }
}

// Usage
DatabaseUtil util = new DatabaseUtil();
util.insertData("users",    "name",       "John Doe");
util.insertData("orders",   "created_at", new Date());
util.insertData("products", "price",      19.99);
```

**Benefits of this design:**

| Benefit | Description |
|---------|-------------|
| **Clean API** | Developers use the same method name (`insertData`) for all data types. |
| **Type Safety** | Each method enforces a specific data type, reducing errors. |
| **Extensibility** | New overloads can be added for additional types (`boolean`, `long`, etc.) without breaking existing code. |

---

## Method Overloading — Quick Reference

| Rule | Valid? |
|------|--------|
| Different number of parameters | ✅ Yes |
| Different parameter types | ✅ Yes |
| Different parameter order (with different types) | ✅ Yes |
| Different return type only | ❌ No — Compilation error |
| Different access modifiers only | ❌ No — Not a valid overload |
| Varargs vs fixed parameters | ✅ Yes — Fixed preferred over varargs |
| Primitive vs Wrapper type | ✅ Yes — Exact match preferred |

---

*Happy Coding! 🚀*
