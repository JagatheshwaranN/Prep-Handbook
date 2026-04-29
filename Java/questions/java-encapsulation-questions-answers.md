# Java - Encapsulation Questions & Answers

---

## 1. What is Encapsulation in Java?

Encapsulation in Java is the mechanism of **wrapping data (variables) and code acting on the data (methods) together as a single unit**. It restricts direct access to some of the object's components, preventing accidental modification of data and ensuring data remains consistent.

---

## 2. Why is Encapsulation important in Java?

| # | Reason |
|---|--------|
| 1 | Maintains data **integrity** by preventing direct access to internal state. |
| 2 | Enables **access control** through access modifiers. |
| 3 | Facilitates **code organization and maintenance** by grouping related data and behavior. |

---

## 3. How do you achieve Encapsulation in Java?

Encapsulation is achieved by:
1. Declaring instance variables as **`private`**.
2. Providing **`public` getter and setter methods** to access and modify these variables.
3. Controlling access to the object's internal state through these methods.

```java
public class Person {
    private String name;  // private — cannot be accessed directly
    private int age;

    // Getter
    public String getName() { return name; }

    // Setter with validation
    public void setAge(int age) {
        if (age > 0) this.age = age;
    }

    public int getAge() { return age; }
}
```

---

## 4. What are the benefits of Encapsulation?

| Benefit | Description |
|---------|-------------|
| **Data Hiding** | Hides the internal state of objects and only exposes necessary operations. |
| **Modularity** | Separates concerns and provides clear boundaries for components. |
| **Flexibility** | Internal implementation can be changed without affecting external code. |
| **Security** | Prevents unauthorized access and modification of data. |

---

## 5. Explain the principle of "data hiding" in Encapsulation.

Data hiding refers to the practice of making the **internal state of an object inaccessible from outside**. This is achieved by declaring instance variables as `private`, so they cannot be accessed directly from outside the class — only through controlled public methods.

---

## 6. What are access modifiers in Java? How do they relate to Encapsulation?

Access modifiers control the **visibility of classes, variables, methods, and constructors**. They play a crucial role in Encapsulation by specifying who can access class members.

| Modifier | Accessibility |
|----------|--------------|
| `private` | Accessible only within the **same class**. |
| `default` (no modifier) | Accessible only within the **same package**. |
| `protected` | Accessible within the same package and by **subclasses**. |
| `public` | Accessible from **any class**. |

---

## 7. How does Encapsulation contribute to information hiding?

Encapsulation contributes to information hiding by:
- **Encapsulating internal state** and providing controlled access via public methods.
- **Preventing exposure** of implementation details to external code.
- **Allowing internal changes** without affecting the code that uses the class.

---

## 8. Provide an example of Encapsulation in a real-world scenario.

A bank account is a classic example. The `accountNumber` and `balance` are private — external code interacts only through controlled `deposit()` and `withdraw()` methods.

```java
public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
}
```

---

## 9. What happens if we don't use Encapsulation?

Without Encapsulation, the internal state of objects is **exposed**, making them prone to:
- **Accidental modification** of data.
- **Data inconsistency** across the application.
- **Compromised code integrity** and unpredictable behavior.

---

## 10. Can Encapsulation be achieved without using access modifiers?

No. **Access modifiers are essential** for controlling the visibility of class members and enforcing Encapsulation principles. Without them, there is no mechanism to restrict access to internal data.

---

## 11. What is the difference between Encapsulation and Abstraction?

| Feature | Encapsulation | Abstraction |
|---------|--------------|-------------|
| **Focus** | Bundling data and methods, hiding implementation details. | Hiding complexity, showing only essential features. |
| **Purpose** | Data hiding and access control. | Simplifying the interface. |
| **How achieved** | `private` fields + getters/setters. | Abstract classes and interfaces. |
| **Concern** | *How* data is protected. | *What* functionality is exposed. |

---

## 12. How can we implement Encapsulation in a Java class?

1. Declare instance variables as **`private`**.
2. Provide **`public` getter methods** to read variables.
3. Provide **`public` setter methods** to modify variables (with validation if needed).

```java
public class Employee {
    private String name;
    private double salary;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) {
        if (salary > 0) this.salary = salary; // Validation in setter
    }
}
```

---

## 13. What are the different access levels provided by Java access modifiers?

| Modifier | Same Class | Same Package | Subclass | Other Classes |
|----------|-----------|--------------|----------|---------------|
| `private` | ✅ | ❌ | ❌ | ❌ |
| `default` | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

---

## 14. Explain getter and setter methods in Encapsulation.

| Method Type | Purpose |
|-------------|---------|
| **Getter** | Retrieves (reads) the value of a private variable. |
| **Setter** | Sets (modifies) the value of a private variable, optionally with validation. |

```java
public class Temperature {
    private double celsius;

    public double getCelsius() { return celsius; }

    public void setCelsius(double celsius) {
        if (celsius >= -273.15) { // Cannot be below absolute zero
            this.celsius = celsius;
        }
    }

    public double getFahrenheit() { return (celsius * 9/5) + 32; } // Derived getter
}
```

---

## 15. How does Encapsulation help in maintaining code integrity and security?

1. **Prevents direct access** to internal data, reducing accidental modification.
2. **Enforces access control** through access modifiers.
3. **Facilitates maintenance** — implementation can change without affecting external code.
4. **Enhances readability** by providing clear boundaries and encapsulating related functionality.

---

## 16. What is the difference between Encapsulation and Data Abstraction?

| Feature | Encapsulation | Data Abstraction |
|---------|--------------|-----------------|
| **Purpose** | Protects data by keeping it inside a class. | Hides complex details, shows only what's necessary. |
| **Focus** | *How* data is managed and accessed. | *What* an object does, not how. |
| **Mechanism** | `private` fields + controlled methods. | Abstract classes and interfaces. |
| **Relationship** | Encapsulation is a **mechanism** for achieving data abstraction. | Abstraction is the **goal**. |

---

## 17. How can access modifiers be used to control inheritance?

- **`private` members** are **not inherited** by subclasses.
- **`protected` members** can be accessed by subclasses within the same package or through inheritance.
- **`public` members** are fully accessible to all subclasses.

---

## 18. How does Encapsulation promote loose coupling and high cohesion?

| Principle | How Encapsulation Helps |
|-----------|------------------------|
| **Loose Coupling** | Classes are self-contained with minimal dependencies on external factors. Changes inside one class don't break others. |
| **High Cohesion** | Related data and methods are grouped together, keeping internal functionality focused and organized. |

---

## 19. Can you achieve encapsulation without using `private` access modifiers?

Yes, partially. Using **package-private (default) access** provides a limited form of encapsulation — members are accessible only within the same package. However, this does not provide the same level of control as `private` modifiers and is less secure.

---

## 20. Alternative approaches to modifying private data without exposing member variables.

### Builder Pattern

The Builder pattern constructs complex objects step by step, allowing creation with flexible parameters while keeping the object immutable or encapsulated.

```java
public class BankAccount {
    private final String accountNumber;
    private final double balance;

    private BankAccount(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.balance = builder.balance;
    }

    // Getters only — no setters (immutable after construction)
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    public static class Builder {
        private String accountNumber;
        private double balance;

        public Builder(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Builder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }
    }
}

// Usage
BankAccount account = new BankAccount.Builder("12345")
                          .balance(1000.0)
                          .build();
```

### Immutable Objects

Immutable objects cannot be modified after construction. Java `record` (Java 16+) provides a concise way to create them.

```java
// Using Java record (Java 16+)
record BankAccount(String accountNumber, double balance) { }

// Using traditional immutable class
class BankAccount1 {
    private final String accountNumber;
    private final double balance;

    BankAccount1(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    // No setters — state cannot change after construction
}
```

---

## 21. How can Encapsulation enforce business logic and data integrity?

Encapsulation goes beyond hiding data — methods can **enforce business rules** through validation, ensuring the object always remains in a valid state.

```java
public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0)
            throw new IllegalArgumentException("Initial balance cannot be negative");
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit amount must be positive");
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        if (amount > this.balance)
            throw new IllegalArgumentException("Insufficient funds");
        this.balance -= amount;
    }

    public double getBalance() { return this.balance; }
}
```

**Business rules enforced:**
- Initial balance cannot be negative.
- Deposit and withdrawal amounts must be positive.
- Cannot withdraw more than the available balance.

---

## 22. How can Reflection bypass Encapsulation, and what are the security implications?

Reflection allows accessing and modifying `private` members at runtime, **bypassing access modifiers**.

```java
BankAccount account = new BankAccount(1000.0);

// Access private field using reflection
Field balanceField = BankAccount.class.getDeclaredField("balance");
balanceField.setAccessible(true); // Bypass access check
double currentBalance = (double) balanceField.get(account);
System.out.println("Current Balance: " + currentBalance);

// Modify private field
balanceField.set(account, 2000.0);

// Invoke private method
Method logMethod = BankAccount.class.getDeclaredMethod("logTransaction", double.class);
logMethod.setAccessible(true);
logMethod.invoke(account, 500.0);
```

**Security implications:**

| Risk | Description |
|------|-------------|
| **Access to private members** | Sensitive data can be exposed unintentionally. |
| **Violation of encapsulation** | Private fields/methods can be modified, causing unexpected behavior. |
| **Security vulnerabilities** | Malicious code can exploit reflection to gain unauthorized access. |

> **⚠️ Caution:** Use reflection sparingly and only in trusted contexts. Avoid `setAccessible(true)` on sensitive classes.

---

## 23. How does combining Encapsulation with synchronization ensure thread safety?

Encapsulating shared data with `synchronized` methods ensures that **only one thread can execute the method at a time**, preventing race conditions.

```java
public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) { this.balance = initialBalance; }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        }
    }

    public synchronized double getBalance() { return balance; }
}

// Usage with multiple threads
BankAccount account = new BankAccount(1000.0);

Thread depositThread  = new Thread(() -> { for (int i = 0; i < 5; i++) account.deposit(100.0); });
Thread withdrawThread = new Thread(() -> { for (int i = 0; i < 5; i++) account.withdraw(100.0); });

depositThread.start();
withdrawThread.start();
depositThread.join();
withdrawThread.join();

System.out.println("Final Balance: " + account.getBalance());
```

**Key points:**
- `synchronized` methods prevent concurrent access issues.
- Even `getBalance()` is synchronized to ensure reads are consistent with writes.
- Encapsulation + synchronization = **thread-safe data access**.

---

## 24. How does Encapsulation contribute to code reusability and maintainability?

| Contribution | Description |
|--------------|-------------|
| **Modular design** | Encapsulated objects are self-contained, making them easy to reuse across different contexts. |
| **Abstraction** | Clear interfaces allow components to be swapped without impacting other parts of the codebase. |
| **Building blocks** | Encapsulated objects serve as reusable building blocks for larger, more complex systems. |
| **Maintainability** | Internal implementation can change freely without breaking external code that uses the class. |

---

## Encapsulation — Quick Reference

| Concept | Description |
|---------|-------------|
| **Private fields** | Core of encapsulation — restrict direct access. |
| **Getters** | Public methods to read private fields. |
| **Setters** | Public methods to modify private fields (with optional validation). |
| **Access modifiers** | `private` → `default` → `protected` → `public` (increasing visibility). |
| **Builder pattern** | Alternative to setters for immutable or complex object construction. |
| **Immutable objects** | No setters — state fixed after construction (`final` fields or `record`). |
| **Synchronized methods** | Combine encapsulation with thread safety. |
| **Reflection risk** | Can bypass encapsulation — use `setAccessible(true)` with caution. |

---

*Happy Coding! 🚀*
