# Java - Cohesion & Coupling Questions & Answers

---

## 1. What is Cohesion?

Cohesion refers to the **degree to which the elements of a module belong together**. It measures the strength of the relationship between the methods and data of a class or the components of a module.

- **High cohesion** — responsibilities are closely related, making the class more understandable, reusable, and maintainable.
- **Low cohesion** — elements have little relation and perform unrelated functionalities — generally indicates poor design.

---

## 2. What is Coupling?

Coupling describes **how tightly connected or dependent modules are to each other**. It measures the degree of interdependence between modules.

- **High coupling** — a module is highly dependent on others, making it harder to modify or replace without affecting the rest.
- **Low coupling (loose coupling)** — promotes module independence, enhancing flexibility and maintainability.

---

## 3. What is the difference between cohesion and coupling?

| Feature | Cohesion | Coupling |
|---------|----------|----------|
| **Focus** | Internal relatedness of elements **within** a single module/class. | Interdependencies **between** different modules/classes. |
| **Goal** | **High** cohesion — each class has a single, well-defined responsibility. | **Low** coupling — reduce impact of changes and improve modularity. |
| **Measures** | How well a class/module does one specific thing. | How much one module depends on another. |
| **Impact** | High cohesion → easier to understand and test. | Low coupling → easier to change and replace modules. |

---

## 4. How can you achieve High Cohesion and Low Coupling in Java?

**High Cohesion:**

1. Ensure each class has a **single responsibility** (Single Responsibility Principle — SRP).
2. **Group related functions and data** together in classes.
3. Use **private methods** for functionality not meant to be exposed outside the class.

**Low Coupling:**

1. **Interface-based programming** — define interfaces for interactions between classes, reducing dependency on concrete implementations.
2. **Dependency Injection** — pass dependencies from outside rather than creating them inside the class.
3. **Design patterns** — use the Observer pattern for event-driven communication or the Factory pattern to decouple object creation.

```java
// High coupling (BAD) — UserService directly creates EmailService
class UserService {
    private EmailService emailService = new EmailService(); // Tightly coupled
}

// Low coupling (GOOD) — dependency injected via constructor
class UserService {
    private EmailService emailService;

    UserService(EmailService emailService) { // Dependency Injection
        this.emailService = emailService;
    }
}
```

---

## 5. Why are High Cohesion and Low Coupling important?

| Benefit | Description |
|---------|-------------|
| **Maintainability** | Easier to understand, debug, and modify individual components. |
| **Understandability** | Classes with focused responsibilities are simpler to read. |
| **Flexibility** | Low coupling allows components to be replaced without affecting others. |
| **Testability** | Loosely coupled, highly cohesive classes are easier to unit test. |
| **Scalability** | Modular, independent components scale more easily. |

Together, high cohesion and low coupling build systems that are **robust, scalable, and easier to maintain**.

---

## 6. What are the different levels of cohesion in Java?

| Level | Type | Description | Desirability |
|-------|------|-------------|-------------|
| 1 (Highest) | **Functional Cohesion** | All elements contribute to a single, well-defined task. Example: a method that calculates a square root. | ✅ Most desirable |
| 2 | **Sequential Cohesion** | Elements are executed sequentially but don't always contribute to a single task. Example: read → process → write. | ✅ Good |
| 3 | **Communicational Cohesion** | Elements operate on the same input data or produce the same output. Example: a stats calculator on a dataset. | ✅ Acceptable |
| 4 | **Temporal Cohesion** | Elements are related because they execute at the same time (e.g., initialization routines). | ⚠️ Moderate |
| 5 | **Procedural Cohesion** | Elements are grouped because they are part of the same algorithm, even if performing different tasks. | ⚠️ Weak |
| 6 | **Sequential Coincidental** | Elements are somewhat related but may perform multiple subtasks. | ⚠️ Low |
| 7 (Lowest) | **Coincidental Cohesion** | Elements are completely unrelated and don't contribute to a single task. Indicates poor design. | ❌ Avoid |

---

## 7. What are the different types of coupling in Java?

### Types of Coupling (from low to high):

| Level | Type | Description | Desirability |
|-------|------|-------------|-------------|
| 1 (Lowest) | **No Coupling (Independent)** | Modules are completely independent of each other. | ✅ Ideal |
| 2 | **Data Coupling** | Modules communicate through simple data items (primitives or simple objects). | ✅ Most preferred |
| 3 | **Stamp Coupling** | Modules share complex data structures. Changes in one may affect the other. | ✅ Acceptable |
| 4 | **Control Coupling** | One module dictates execution flow in another via control flags. | ⚠️ Moderate |
| 5 | **External Coupling** | Modules share a common external data source (file/database). | ⚠️ Moderate |
| 6 | **Common Coupling** | Multiple modules access the same global data. | ❌ Avoid |
| 7 (Highest) | **Content Coupling** | Modules share internal details or implementations. | ❌ Strongly avoid |

**Achieving loose coupling:**
- Use **interfaces** for interactions between classes.
- Apply **Dependency Injection**.
- Prefer **composition** over inheritance for reuse.

---

## 8. Problems caused by excessive coupling — Examples

---

### Problem 1: Difficulties in Unit Testing

Tight coupling makes it difficult to isolate and test individual components because dependencies cannot be easily mocked or replaced.

```java
// Tightly coupled — hard to test
public class ShoppingCart {
    private List<Item> items;

    public void addItem(Item item)    { items.add(item); }
    public void removeItem(Item item) { items.remove(item); }

    public double calculateTotalPrice() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice(); // Direct dependency on Item.getPrice()
        }
        return total;
    }
}
```

**Problem:** `ShoppingCart` is tightly coupled to `List<Item>` and `Item`. We cannot easily mock these dependencies to test `calculateTotalPrice()` in isolation.

**Solution — Use interfaces:**

```java
interface PriceCalculator { double calculateTotal(List<Item> items); }

class ShoppingCart {
    private List<Item> items = new ArrayList<>();
    private PriceCalculator calculator; // Injected dependency — easily mockable

    ShoppingCart(PriceCalculator calculator) { this.calculator = calculator; }

    public double calculateTotalPrice() { return calculator.calculateTotal(items); }
}
```

---

### Problem 2: Increased Risk of Side Effects When Making Changes

Tight coupling increases the risk of unintended side effects — a change in one class forces changes in another.

```java
// Tightly coupled — direct instantiation
public class UserService {
    private EmailService emailService;

    public UserService() {
        this.emailService = new EmailService(); // Hard dependency — cannot be swapped
    }

    public void registerUser(String username, String email) {
        // Registration logic
        emailService.sendEmail(email, "Welcome!"); // Directly coupled to EmailService
    }
}
```

**Problem:** Switching to a different email provider requires modifying `UserService`, risking unintended side effects.

**Solution — Dependency Injection:**

```java
interface EmailSender { void sendEmail(String recipient, String message); }

public class UserService {
    private EmailSender emailSender; // Interface — not a concrete class

    public UserService(EmailSender emailSender) { // Inject dependency
        this.emailSender = emailSender;
    }

    public void registerUser(String username, String email) {
        emailSender.sendEmail(email, "Welcome!"); // Can be any EmailSender implementation
    }
}
```

---

### Problem 3: Decreased Code Readability

Excessive coupling creates tangled dependencies that make code hard to understand and maintain.

```java
// Tightly coupled — difficult to understand
public class ReportGenerator {
    private DatabaseConnection dbConnection;

    public ReportGenerator() {
        this.dbConnection = new DatabaseConnection(); // Direct instantiation — hard to follow
    }

    public void generateReport() {
        Connection conn = dbConnection.getConnection(); // Reader must understand DatabaseConnection
        // Report generation logic
    }
}
```

**Problem:** Anyone reading `ReportGenerator` must also understand `DatabaseConnection` to grasp its functionality.

**Solution — Dependency Injection + Interface:**

```java
interface DataSource { Connection getConnection(); }

public class ReportGenerator {
    private DataSource dataSource; // Clear, abstract dependency

    public ReportGenerator(DataSource dataSource) {
        this.dataSource = dataSource; // Injected — easily readable and replaceable
    }

    public void generateReport() {
        Connection conn = dataSource.getConnection();
        // Clear, self-documenting report logic
    }
}
```

---

## Cohesion & Coupling — Quick Reference

| Concept | Goal | Desirable Level | Key Technique |
|---------|------|-----------------|---------------|
| **Cohesion** | Elements within a class are closely related. | **High** cohesion | Single Responsibility Principle (SRP) |
| **Coupling** | Independence between classes/modules. | **Low** coupling | Dependency Injection, Interfaces |

| Coupling Type | Level | Description |
|--------------|-------|-------------|
| No Coupling | ✅ Lowest | Completely independent. |
| Data Coupling | ✅ Low | Share simple data via parameters. |
| Stamp Coupling | ✅ Acceptable | Share complex data structures. |
| Control Coupling | ⚠️ Moderate | One controls flow of another. |
| External Coupling | ⚠️ Moderate | Share external data source. |
| Common Coupling | ❌ High | Share global data. |
| Content Coupling | ❌ Highest | Share internal implementation. |

| Cohesion Type | Level | Description |
|--------------|-------|-------------|
| Functional | ✅ Highest | Single well-defined task. |
| Sequential | ✅ High | Elements executed in sequence. |
| Communicational | ✅ Good | Operate on same data. |
| Temporal | ⚠️ Moderate | Execute at the same time. |
| Procedural | ⚠️ Weak | Same algorithm, different tasks. |
| Coincidental | ❌ Lowest | Unrelated elements grouped together. |

---

*Happy Coding! 🚀*
