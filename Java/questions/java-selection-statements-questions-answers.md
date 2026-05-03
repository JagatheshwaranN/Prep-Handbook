# Java - Selection Statements Questions & Answers

---

## 1. What are the selection statements available in Java?

Java supports **two main selection statements**:

| Statement | Purpose |
|-----------|---------|
| `if` statement | Conditional branching based on a boolean expression. |
| `switch` statement | Multi-way branching based on the value of an expression. |

---

## 2. Explain the syntax of the `if` statement in Java.

```java
if (condition) {
    // Executed if condition is true
}
```

**With `else`:**

```java
if (condition) {
    // Executed if condition is true
} else {
    // Executed if condition is false
}
```

**With `else if`:**

```java
if (condition1) {
    // Executed if condition1 is true
} else if (condition2) {
    // Executed if condition2 is true
} else {
    // Executed if none of the above are true
}
```

---

## 3. What is the purpose of the `else` statement?

The `else` statement executes a block of code when the `if` condition is **false**. It provides an alternative execution path.

```java
int score = 45;
if (score >= 50) {
    System.out.println("Pass");
} else {
    System.out.println("Fail"); // Executed — score is 45
}
```

---

## 4. How does the `else if` statement work?

The `else if` statement allows you to check **multiple conditions sequentially** after the initial `if` condition. If the `if` condition is false, the next `else if` condition is evaluated.

```java
int marks = 75;
if (marks >= 90) {
    System.out.println("Grade A");
} else if (marks >= 75) {
    System.out.println("Grade B"); // Matches
} else if (marks >= 60) {
    System.out.println("Grade C");
} else {
    System.out.println("Grade D");
}
// Output: Grade B
```

---

## 5. Explain the syntax of the `switch` statement in Java.

```java
switch (expression) {
    case value1:
        // Executed if expression == value1
        break;
    case value2:
        // Executed if expression == value2
        break;
    // additional cases...
    default:
        // Executed if no case matches
}
```

**Example:**

```java
int dayOfWeek = 3;
switch (dayOfWeek) {
    case 1: System.out.println("Monday");    break;
    case 2: System.out.println("Tuesday");   break;
    case 3: System.out.println("Wednesday"); break; // Matches
    case 4: System.out.println("Thursday");  break;
    case 5: System.out.println("Friday");    break;
    default: System.out.println("Weekend");
}
// Output: Wednesday
```

---

## 6. What is the role of the `break` statement in a `switch`?

The `break` statement **exits the switch block immediately** after a matching case executes. Without `break`, execution falls through to the next case (see Q14 on fall-through).

```java
int x = 2;
switch (x) {
    case 1: System.out.println("One");   break; // Exits here
    case 2: System.out.println("Two");   break; // Matches, then exits
    case 3: System.out.println("Three"); break;
}
// Output: Two
```

---

## 7. How do you handle default behavior in a `switch` statement?

The **`default` case** specifies what to execute when no case matches the expression. It is optional but recommended.

```java
int value = 99;
switch (value) {
    case 1:  System.out.println("One");   break;
    case 2:  System.out.println("Two");   break;
    default: System.out.println("Unknown value"); // Executes — no match found
}
```

---

## 8. What is the difference between the `if` statement and the `switch` statement?

| Feature | `if` statement | `switch` statement |
|---------|---------------|-------------------|
| **Conditions** | Any boolean expression — complex, range-based. | Equality check against fixed values only. |
| **Flexibility** | ✅ More flexible — handles complex logic. | Less flexible — equality-based only. |
| **Data types** | Any type with boolean result. | `int`, `char`, `String`, `enum` only. |
| **Readability** | Better for complex, multi-condition logic. | ✅ Cleaner for simple, fixed-value branching. |
| **Performance** | Sequential evaluation. | Can be optimized by JVM (jump table). |

---

## 9. When would you use `if` statements versus a `switch` statement?

| Use `switch` when... | Use `if-else` when... |
|---------------------|----------------------|
| Comparing a variable against a **fixed set of simple values**. | Conditions involve **ranges** (`> 18`, `< 100`). |
| Code for each case is **relatively simple**. | Logic involves **complex conditions** or inequalities. |
| Working with `int`, `char`, `String`, or `enum`. | Conditions involve **multiple variables** or boolean logic. |

**Switch — best for fixed values (day of week):**

```java
int dayOfWeek = 3;
switch (dayOfWeek) {
    case 1: System.out.println("Monday");    break;
    case 2: System.out.println("Tuesday");   break;
    case 3: System.out.println("Wednesday"); break;
    // ...
    default: System.out.println("Invalid day");
}
```

**If-else — best for complex conditions:**

```java
int age = 25;
String gender = "male";
boolean isStudent = false;

if (age >= 18) {
    if (gender.equals("male")) {
        if (isStudent) {
            System.out.println("Male student above 18.");
        } else {
            System.out.println("Male above 18.");
        }
    } else if (gender.equals("female")) {
        System.out.println(isStudent ? "Female student above 18." : "Female above 18.");
    }
} else {
    System.out.println("Under 18 years old.");
}
```

---

## 10. Explain short-circuit evaluation in Java `if` statements.

Short-circuit evaluation means the **second operand is evaluated only if necessary**:

| Operator | Short-circuit behavior |
|----------|----------------------|
| `&&` (AND) | If the **left operand is `false`**, the right is **not evaluated** — result is already `false`. |
| `\|\|` (OR) | If the **left operand is `true`**, the right is **not evaluated** — result is already `true`. |

```java
boolean condition1 = true;
boolean condition2 = false;

// AND — condition2 evaluated because condition1 is true
if (condition1 && condition2) {
    System.out.println("Both true"); // Not reached
}

// OR — condition2 NOT evaluated because condition1 is already true
if (condition1 || condition2) {
    System.out.println("At least one true"); // Reached
}

// Practical use — null check before method call
String str = null;
if (str != null && str.length() > 0) { // Safe — str.length() not called if str is null
    System.out.println("Non-empty string");
}
```

---

## 11. What is the difference between `if` statements and ternary conditional expressions?

| Feature | `if` statement | Ternary operator (`? :`) |
|---------|---------------|--------------------------|
| **Purpose** | Conditional branching — executes blocks of code. | Conditional **expression** — returns a value. |
| **Syntax** | Multi-line. | Single line. |
| **Readability** | ✅ Better for complex conditions. | ✅ Concise for simple conditions. |
| **Use case** | Executing multiple statements per branch. | Assigning a value or returning a result. |

---

## 12. Explain the ternary operator in Java and provide an example.

The ternary operator (`? :`) is a **shorthand if-else** that evaluates a boolean expression and returns one of two values.

**Syntax:** `condition ? valueIfTrue : valueIfFalse`

```java
int x = 5;
String result = (x > 0) ? "Positive" : "Negative";
System.out.println(result); // Output: Positive

// Equivalent if-else:
String result2;
if (x > 0) {
    result2 = "Positive";
} else {
    result2 = "Negative";
}
```

**Other examples:**

```java
int a = 10, b = 20;
int max = (a > b) ? a : b;   // max = 20
String status = (age >= 18) ? "Adult" : "Minor";
```

---

## 13. How is the `switch` statement used with `enum` types?

The `switch` statement works seamlessly with `enum` types, making code more expressive and type-safe.

```java
enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

Day today = Day.WEDNESDAY;

switch (today) {
    case MONDAY:
        System.out.println("Start of the work week");
        break;
    case WEDNESDAY:
        System.out.println("Midweek");                  // Matches
        break;
    case FRIDAY:
        System.out.println("Almost the weekend!");
        break;
    case SATURDAY:
    case SUNDAY:
        System.out.println("Weekend!");
        break;
    default:
        System.out.println("Regular workday");
}
// Output: Midweek
```

---

## 14. Explain the concept of fall-through in `switch` statements.

**Fall-through** occurs when the control flow **continues to the next case** after executing the matched case, because no `break` statement is present.

```java
int x = 2;
switch (x) {
    case 1:
        System.out.println("One");
    case 2:
        System.out.println("Two");   // Matches — executes
        // No break — falls through to case 3!
    case 3:
        System.out.println("Three"); // Also executes due to fall-through
        break;
    case 4:
        System.out.println("Four");
}
// Output: Two → Three
```

**Intentional fall-through — grouping cases:**

```java
int day = 6;
switch (day) {
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        System.out.println("Weekday"); // Handles days 1–5
        break;
    case 6:
    case 7:
        System.out.println("Weekend"); // Handles days 6 and 7
        break;
}
```

> **Best Practice:** Always include `break` unless intentional fall-through is required. Add a comment when fall-through is deliberate.

---

## 15. When would you prefer a ternary operator over an `if-else` statement?

| Scenario | Prefer Ternary | Prefer `if-else` |
|----------|----------------|-----------------|
| **Simple assignment** | ✅ — `int max = (a > b) ? a : b;` | Unnecessarily verbose. |
| **Complex multi-line logic** | Becomes hard to read. | ✅ — More readable. |
| **Single return value** | ✅ — Concise inline expression. | Not needed. |
| **Multiple statements per branch** | ❌ — Not possible with ternary. | ✅ — Handles multiple statements. |
| **Nested conditions** | ❌ — Avoid nested ternaries. | ✅ — `else if` chains are clearer. |

```java
// ✅ Ternary — simple, clear
String label = (score >= 50) ? "Pass" : "Fail";

// ✅ if-else — complex logic
if (score >= 90) {
    grade = "A";
    bonus = 10;
} else if (score >= 75) {
    grade = "B";
    bonus = 5;
} else {
    grade = "C";
    bonus = 0;
}
```

---

## Selection Statements — Quick Reference

| Statement | Use When |
|-----------|----------|
| `if` | Evaluating a single boolean condition. |
| `if-else` | Two alternative branches. |
| `else if` | Multiple conditions to check sequentially. |
| `switch` | Fixed set of values for a single expression. |
| `switch` with `enum` | Type-safe multi-way branching. |
| Ternary `? :` | Simple inline conditional assignment or return. |

| Concept | Description |
|---------|-------------|
| **`break` in switch** | Exits the switch — prevents fall-through. |
| **Fall-through** | Execution continues to next case without `break`. |
| **`default` case** | Handles unmatched values in a switch. |
| **Short-circuit `&&`** | Right operand skipped if left is `false`. |
| **Short-circuit `\|\|`** | Right operand skipped if left is `true`. |

---

*Happy Coding! 🚀*
