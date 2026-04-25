# Selenium - JavaScriptExecutor Questions & Answers

---

## General Questions

---

### 1. What is JavascriptExecutor in Selenium?

`JavascriptExecutor` is an **interface provided by Selenium WebDriver** to execute JavaScript code within the context of the currently selected frame or window. It allows interaction with DOM elements and handling of asynchronous operations that are not directly supported by Selenium's standard WebDriver API.

---

### 2. When would you use JavascriptExecutor in Selenium testing?

| # | Scenario |
|---|----------|
| 1 | Interacting with **hidden or non-interactive elements** (e.g., `display: none`) not accessible via standard WebDriver methods. |
| 2 | Interacting with **dynamic web elements** that change state frequently. |
| 3 | Handling **AJAX calls** and asynchronous operations. |
| 4 | Simulating user interactions like **scrolling or dragging**. |
| 5 | Executing JavaScript to **validate or manipulate the DOM** directly. |

---

### 3. How do you instantiate JavascriptExecutor in Selenium WebDriver?

Cast the `WebDriver` instance to `JavascriptExecutor`.

```java
JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
```

---

### 4. What are the methods provided by the JavascriptExecutor interface?

| Method | Description |
|--------|-------------|
| `executeScript(String script, Object... args)` | Executes **synchronous** JavaScript code. |
| `executeAsyncScript(String script, Object... args)` | Executes **asynchronous** JavaScript code. |

```java
// Synchronous execution
jsExecutor.executeScript("return document.title;");

// Asynchronous execution
jsExecutor.executeAsyncScript("var callback = arguments[arguments.length - 1]; setTimeout(callback, 2000);");
```

---

### 5. How can you scroll to an element using JavascriptExecutor?

Use `scrollIntoView()` via `executeScript()` to scroll an element into the visible viewport.

```java
JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
WebElement element = driver.findElement(By.id("targetElement"));

// Scroll the element into view
jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
```

**Other scroll examples:**

```java
// Scroll down by 500px
jsExecutor.executeScript("window.scrollBy(0, 500);");

// Scroll to the bottom of the page
jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

// Scroll to the top of the page
jsExecutor.executeScript("window.scrollTo(0, 0);");
```

---

### 6. How do you highlight an element using JavascriptExecutor?

Change the element's border style using JavaScript to visually highlight it — useful for debugging.

```java
JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
WebElement element = driver.findElement(By.id("elementId"));

// Highlight with a green border
jsExecutor.executeScript("arguments[0].style.border='2px solid green';", element);

// Optional: Remove highlight after action
jsExecutor.executeScript("arguments[0].style.border='';", element);
```

---

### 7. Describe the return types of methods provided by JavaScriptExecutor.

| Method | Execution Type | Return Value |
|--------|---------------|--------------|
| `executeScript(script, args...)` | **Synchronous** — executes immediately in the current frame/window. | `WebElement`, primitive (`Number`, `String`, `Boolean`), `List`, or `null`. |
| `executeAsyncScript(script, args...)` | **Asynchronous** — interacts with elements that may not be immediately available. | Generally `null`; result passed via a callback function. |

**Examples:**

```java
// Returns a String
String title = (String) jsExecutor.executeScript("return document.title;");

// Returns a Long (number)
Long count = (Long) jsExecutor.executeScript("return document.querySelectorAll('a').length;");

// Returns a WebElement
WebElement element = (WebElement) jsExecutor.executeScript("return document.getElementById('myId');");

// Returns Boolean
Boolean isVisible = (Boolean) jsExecutor.executeScript(
    "return document.getElementById('myId').style.display !== 'none';");
```

---

### 8. How can you use JavaScriptExecutor to wait for an element to become visible?

Combine `JavascriptExecutor` with `WebDriverWait` to create a **custom waiting condition** based on JavaScript logic.

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

wait.until(new ExpectedCondition<Boolean>() {
    public Boolean apply(WebDriver driver) {
        return (Boolean) jsExecutor.executeScript(
            "return document.getElementById('element').style.display !== 'none';");
    }
});
```

**Simplified lambda version:**

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
    .executeScript("return document.getElementById('element').style.display !== 'none';"));
```

---

## Common JavaScriptExecutor Operations — Quick Reference

| Operation | Code |
|-----------|------|
| **Click an element** | `jsExecutor.executeScript("arguments[0].click();", element)` |
| **Scroll to element** | `jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element)` |
| **Scroll down by px** | `jsExecutor.executeScript("window.scrollBy(0, 500);")` |
| **Scroll to bottom** | `jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);")` |
| **Scroll to top** | `jsExecutor.executeScript("window.scrollTo(0, 0);")` |
| **Get page title** | `jsExecutor.executeScript("return document.title;")` |
| **Get page URL** | `jsExecutor.executeScript("return window.location.href;")` |
| **Set element value** | `jsExecutor.executeScript("arguments[0].value='text';", element)` |
| **Highlight element** | `jsExecutor.executeScript("arguments[0].style.border='2px solid red';", element)` |
| **Check visibility** | `jsExecutor.executeScript("return arguments[0].style.display !== 'none';", element)` |
| **Refresh page** | `jsExecutor.executeScript("location.reload();")` |

---

## Exceptions

---

### 1. `JavascriptException`

**What is it, and when does it occur?**

`JavascriptException` is thrown when there are **errors in the JavaScript code** being executed. Common causes include:

| Cause | Example |
|-------|---------|
| **Syntax errors** | Missing brackets, semicolons, or typos in the script. |
| **Undefined variables** | Referencing a variable that hasn't been declared. |
| **Non-existing functions or objects** | Calling a function or accessing a property that doesn't exist. |

```java
try {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    // Valid JavaScript
    String title = (String) jsExecutor.executeScript("return document.title;");
    System.out.println("Page Title: " + title);

} catch (JavascriptException e) {
    System.out.println("JavaScript execution failed: " + e.getMessage());
    // Check the script for syntax errors, undefined references, or missing objects
}
```

**Prevention strategies:**
- Test the JavaScript in the browser's developer console before using it in Selenium.
- Use `try-catch` blocks to handle `JavascriptException` gracefully.
- Validate that the DOM elements or objects referenced in the script exist before executing.

```java
// Safe approach - check element existence before executing
Boolean elementExists = (Boolean) jsExecutor.executeScript(
    "return document.getElementById('myElement') !== null;");
if (elementExists) {
    jsExecutor.executeScript("document.getElementById('myElement').click();");
}
```

---

*Happy Testing! 🚀*
