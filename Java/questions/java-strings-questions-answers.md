# Java - Strings Questions & Answers

---

## 1. What is a String in Java?

A `String` in Java is an **object representing a sequence of characters**. It is used to represent and manipulate text. Strings are **immutable** — once created, their content cannot be changed.

---

## 2. How can you create a String object in Java?

**Method 1 — String literal (uses String Pool):**

```java
String str = "Hello";
```

**Method 2 — Using `new` keyword (always creates new heap object):**

```java
String str = new String("Hello");
```

---

## 3. What is the difference between `==` and `.equals()` when comparing Strings?

| Operator/Method | Compares | Use For |
|----------------|---------|---------|
| `==` | **Object references** — checks if both point to the same memory location. | Reference equality. |
| `.equals()` | **Actual content** — checks if both strings have the same characters. | ✅ Content comparison. |

```java
String s1 = "Hello";
String s2 = "Hello";
String s3 = new String("Hello");

System.out.println(s1 == s2);        // true  — same pool reference
System.out.println(s1 == s3);        // false — s3 is a new heap object
System.out.println(s1.equals(s3));   // true  — same content
```

> **Best Practice:** Always use `.equals()` for String content comparison.

---

## 4. How can you concatenate strings in Java?

**Method 1 — `+` operator:**

```java
String result = "Hello" + " " + "World"; // "Hello World"
```

**Method 2 — `concat()` method:**

```java
String result = "Hello".concat(" ").concat("World");
```

**Method 3 — `StringBuilder` (best for multiple concatenations):**

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello").append(" ").append("World");
String result = sb.toString();
```

---

## 5. What is the `StringBuilder` class? How is it different from `String`?

| Feature | `String` | `StringBuilder` |
|---------|---------|----------------|
| **Mutability** | Immutable — cannot be modified. | ✅ Mutable — can be modified. |
| **Thread safety** | ✅ Thread-safe (immutable). | ❌ Not thread-safe. |
| **Performance** | Creates new objects on every modification. | ✅ Modifies in place — more efficient. |
| **Use case** | Fixed text, constants. | Frequent string modifications. |

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");
sb.insert(5, ",");
sb.reverse();
System.out.println(sb.toString()); // dlroW ,olleH
```

---

## 6. How do you convert a String to uppercase or lowercase?

```java
String str = "Hello World";

String upper = str.toUpperCase(); // "HELLO WORLD"
String lower = str.toLowerCase(); // "hello world"

System.out.println(upper); // HELLO WORLD
System.out.println(lower); // hello world
```

---

## 7. How can you check if a String contains a substring?

```java
String str = "Hello World";

// contains() — returns boolean
boolean hasHello = str.contains("Hello");  // true
boolean hasJava  = str.contains("Java");   // false

// indexOf() — returns index (-1 if not found)
int index = str.indexOf("World");  // 6
int notFound = str.indexOf("Java"); // -1

// startsWith() / endsWith()
str.startsWith("Hello"); // true
str.endsWith("World");   // true
```

---

## 8. What is the `split()` method in Java?

The `split()` method splits a String into an **array of substrings** based on a delimiter (regex).

```java
String str = "apple,banana,orange";
String[] fruits = str.split(","); // ["apple", "banana", "orange"]

for (String fruit : fruits) {
    System.out.println(fruit);
}

// Split with limit
String csv = "a,b,c,d";
String[] limited = csv.split(",", 2); // ["a", "b,c,d"] — max 2 parts
```

---

## 9. Is `String` thread-safe?

Yes. `String` objects are **inherently thread-safe** because they are **immutable** — once created, their content cannot be changed. Multiple threads can safely access and use the same `String` object without risk of data corruption.

---

## 10. What is the difference between `String`, `StringBuffer`, and `StringBuilder`?

| Feature | `String` | `StringBuffer` | `StringBuilder` |
|---------|---------|----------------|-----------------|
| **Mutability** | Immutable | ✅ Mutable | ✅ Mutable |
| **Thread safety** | ✅ Thread-safe (immutable) | ✅ Thread-safe (synchronized) | ❌ Not thread-safe |
| **Performance** | Slowest for frequent changes | Moderate | ✅ Fastest (no sync overhead) |
| **Synchronization** | N/A | ✅ Yes | ❌ No |
| **Use case** | Fixed/constant text | Multi-threaded modification | Single-threaded modification |

```java
// String — creates new object on modification
String s = "Hello";
s = s + " World"; // New object created!

// StringBuffer — thread-safe modifications
StringBuffer sb = new StringBuffer("Hello");
sb.append(" World"); // Modifies in place, synchronized

// StringBuilder — fastest for single-threaded use
StringBuilder builder = new StringBuilder("Hello");
builder.append(" World"); // Modifies in place, no sync overhead
```

---

## 11. What is the difference between creating a String using a literal vs. the `new` keyword?

| Feature | String Literal `"Hello"` | `new String("Hello")` |
|---------|------------------------|-----------------------|
| **Storage** | String Pool (in heap). | Heap memory (outside pool). |
| **Reuse** | ✅ Reused if identical string exists in pool. | ❌ Always creates a new object. |
| **Memory** | More efficient. | Less efficient. |
| **`==` comparison** | Two literals with same value → `true`. | Literal vs `new` → `false`. |

```java
String s1 = "Hello";          // Points to pool
String s2 = "Hello";          // Reuses same pool reference
String s3 = new String("Hello"); // New heap object

System.out.println(s1 == s2); // true  — same pool reference
System.out.println(s1 == s3); // false — different objects
```

---

## 12. What is the String Constant Pool?

The **String Constant Pool** (String Pool) is a **special area in the Java heap** where string literals are stored. It enables **string interning** — when an identical string literal is encountered, the existing pool object is reused instead of creating a new one.

```
Heap Memory:
┌──────────────────────────────────────┐
│  String Pool                          │
│  ┌───────────┐  ← "Hello" (shared)    │
│  │  "Hello"  │  ← s1 and s2 point here│
│  └───────────┘                        │
│                                       │
│  ┌───────────┐  ← new String("Hello") │
│  │  "Hello"  │  ← s3 points here      │
│  └───────────┘  (separate heap object)│
└──────────────────────────────────────┘
```

---

## 13. How do you find the length of a String?

Use the `length()` method.

```java
String name = "Alice";
int len = name.length(); // 5
```

---

## 14. How do you access individual characters in a String?

Use the `charAt(index)` method (0-based index).

```java
String str = "Hello";
char first = str.charAt(0); // 'H'
char last  = str.charAt(4); // 'o'

// Iterate through each character
for (int i = 0; i < str.length(); i++) {
    System.out.print(str.charAt(i) + " ");
}
// Output: H e l l o
```

---

## 15. How do you compare two Strings for equality?

```java
String s1 = "hello";
String s2 = "Hello";

s1.equals(s2);           // false — case-sensitive
s1.equalsIgnoreCase(s2); // true  — case-insensitive
s1.compareTo(s2);        // positive, zero, or negative (lexicographic)
```

> **Best Practice:** Use `.equals()` for content comparison, never `==`.

---

## 16. How do you extract a substring from a String?

Use `substring(startIndex)` or `substring(startIndex, endIndex)`.

```java
String name = "Hello World";

String sub1 = name.substring(6);     // "World" — from index 6 to end
String sub2 = name.substring(0, 5);  // "Hello" — from 0 to 4 (endIndex exclusive)
String lastName = name.substring(6); // "World"

System.out.println(sub1); // World
System.out.println(sub2); // Hello
```

---

## 17. How can you improve String concatenation efficiency?

Use `StringBuilder` (single-threaded) or `StringBuffer` (multi-threaded) instead of the `+` operator for frequent modifications.

```java
// Inefficient — creates a new String object on each iteration
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i; // Creates 1000 intermediate objects!
}

// Efficient — modifies a single StringBuilder object
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);
}
String result = sb.toString();
```

---

## 18. What is the output? (`str1 == str2` with literals)

```java
String str1 = "hello";
String str2 = "hello";
System.out.println(str1 == str2);
```

**Output: `true`**

Both `str1` and `str2` are string literals with the same value. Java reuses the existing `"hello"` object from the **String Pool**, so both references point to the **same object**.

---

## 19. What is the output? (`str1 == str2` with `new`)

```java
String str1 = "hello";
String str2 = new String("hello");
System.out.println(str1 == str2);
```

**Output: `false`**

`str1` points to the String Pool. `str2` is created with `new`, which **always creates a new object in the heap** outside the pool — even if the same content exists. They are different objects in memory.

---

## 20. What is the purpose of the `intern()` method?

The `intern()` method returns a **canonical representation from the String Pool**. If the string exists in the pool, that reference is returned; otherwise, the string is added to the pool.

```java
String s1 = new String("hello"); // New heap object
String s2 = s1.intern();         // Returns pool reference
String s3 = "hello";             // Pool literal

System.out.println(s1 == s2); // false — s1 is heap, s2 is pool
System.out.println(s2 == s3); // true  — both are pool references
```

---

## 21. How can you reverse a String in Java?

**Method 1 — `StringBuilder.reverse()`:**

```java
String original = "hello";
String reversed = new StringBuilder(original).reverse().toString();
System.out.println(reversed); // olleh
```

**Method 2 — Manual loop:**

```java
String str = "hello";
StringBuilder sb = new StringBuilder();
for (int i = str.length() - 1; i >= 0; i--) {
    sb.append(str.charAt(i));
}
System.out.println(sb.toString()); // olleh
```

---

## 22. How does String immutability benefit Java programs?

| Benefit | Description |
|---------|-------------|
| **Thread safety** | Immutable objects can be shared across threads without synchronization. |
| **String Pool efficiency** | Identical strings can be safely reused from the pool. |
| **Security** | String values used for passwords, file paths, etc. cannot be altered. |
| **HashMap keys** | Immutable strings make reliable hash map keys — hash code never changes. |
| **Caching** | Hash code can be cached after first computation. |

---

## 23. When would you prefer `StringBuilder` over `+` operator?

Use `StringBuilder` when **frequently modifying a String** — especially inside loops. The `+` operator creates a new `String` object for every concatenation, leading to many intermediate objects and poor performance.

```java
// Bad for performance — 10,000 new objects created
String result = "";
for (int i = 0; i < 10000; i++) {
    result += "line " + i + "\n";
}

// Good — single mutable object
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append("line ").append(i).append("\n");
}
String result = sb.toString();
```

---

## 24. Explain String interning in Java and its impact on memory and performance.

**String interning** is the process of storing **only one copy of each distinct string value** in the String Pool. The JVM automatically interns string literals.

```java
String a = "Java";          // Interned automatically
String b = "Java";          // Reuses same pool entry — no new object
String c = new String("Java").intern(); // Explicitly interned — returns pool reference

System.out.println(a == b); // true — same pool entry
System.out.println(a == c); // true — c is now the pool reference
```

**Impact:**

| Aspect | Benefit |
|--------|---------|
| **Memory** | Reduces duplication — identical strings stored once. |
| **Performance** | Allows fast `==` reference checks instead of `equals()` content checks. |
| **Trade-off** | Pool entries remain in memory for JVM lifetime — overuse can cause memory pressure. |

---

## Strings — Quick Reference

| Method | Description | Example |
|--------|-------------|---------|
| `length()` | Number of characters. | `"Hello".length()` → `5` |
| `charAt(i)` | Character at index. | `"Hello".charAt(1)` → `'e'` |
| `substring(s, e)` | Extract portion. | `"Hello".substring(1, 3)` → `"el"` |
| `contains(s)` | Check if substring exists. | `"Hello".contains("ell")` → `true` |
| `indexOf(s)` | Index of substring. | `"Hello".indexOf("l")` → `2` |
| `replace(old, new)` | Replace characters/strings. | `"Hello".replace("l", "r")` → `"Herro"` |
| `toUpperCase()` | Convert to uppercase. | `"hello".toUpperCase()` → `"HELLO"` |
| `toLowerCase()` | Convert to lowercase. | `"HELLO".toLowerCase()` → `"hello"` |
| `trim()` | Remove leading/trailing spaces. | `"  hi  ".trim()` → `"hi"` |
| `split(regex)` | Split into array. | `"a,b".split(",")` → `["a","b"]` |
| `equals(s)` | Content comparison. | `"abc".equals("abc")` → `true` |
| `intern()` | Get pool reference. | `new String("x").intern()` |
| `isEmpty()` | Check if empty. | `"".isEmpty()` → `true` |
| `isBlank()` | Check if blank (Java 11+). | `"  ".isBlank()` → `true` |

---

*Happy Coding! 🚀*