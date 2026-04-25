# Selenium - Navigation Questions & Answers

---

## General Questions

---

### 1. How can you navigate to a URL in Selenium WebDriver?

Use the `driver.get()` method to navigate to a URL.

```java
driver.get("https://www.example.com");
```

---

### 2. Explain the difference between `get()` and `navigate().to()` in Selenium WebDriver.

Both methods are used to navigate to a URL, but they differ in behavior and use case:

| Feature | `get(url)` | `navigate().to(url)` |
|---------|-----------|----------------------|
| **Interface** | Part of the `WebDriver` interface. | Part of the `Navigation` interface. |
| **Primary Function** | Load a page and wait for it to fully load. | Navigate to a page within the same session. |
| **Waits for Page Load** | ✅ Yes — waits for page to fully load. | ❌ No — use `WebDriverWait` if needed. |
| **Browser History** | Not maintained. | ✅ Maintained. |
| **Session State** | May discard cookies/session state. | ✅ Preserved (cookies, authentication). |
| **Best Used When** | Starting a new navigation where full page load is required. | Navigating between pages while maintaining session/history. |

```java
// Using get()
driver.get("https://www.example.com");

// Using navigate().to()
driver.navigate().to("https://www.example.com");
```

---

### 3. How do you navigate back in the browser using Selenium WebDriver?

Use `driver.navigate().back()` to simulate clicking the browser's **Back** button.

```java
driver.navigate().back();
```

---

### 4. How do you navigate forward in the browser using Selenium WebDriver?

Use `driver.navigate().forward()` to simulate clicking the browser's **Forward** button.

```java
driver.navigate().forward();
```

---

### 5. Explain the purpose of `navigate().refresh()` in Selenium WebDriver.

`navigate().refresh()` is used to **reload the current page**, simulating pressing the browser's Refresh button.

```java
driver.navigate().refresh();
```

---

### 6. What is the purpose of `driver.navigate().to("about:blank")` in Selenium WebDriver?

Navigates to a **blank (empty) page**. Useful when you want to:
- Start fresh within the current browser session.
- Reset the current page state without opening a new browser instance.

```java
driver.navigate().to("about:blank");
```

---

### 7. Explain the purpose of `driver.navigate().to("javascript:void(0);")` in Selenium WebDriver.

Executes a **JavaScript command that effectively does nothing**. It can be used to:
- Trigger certain page events without navigating away from the current page.
- Prevent default link navigation behavior during testing.

```java
driver.navigate().to("javascript:void(0);");
```

---

## Navigation Methods — Quick Reference

| Method | Description |
|--------|-------------|
| `driver.get(url)` | Navigate to a URL and wait for full page load. |
| `driver.navigate().to(url)` | Navigate to a URL while preserving session state and history. |
| `driver.navigate().back()` | Simulate clicking the browser **Back** button. |
| `driver.navigate().forward()` | Simulate clicking the browser **Forward** button. |
| `driver.navigate().refresh()` | Reload the current page. |
| `driver.navigate().to("about:blank")` | Navigate to a blank/empty page. |
| `driver.navigate().to("javascript:void(0);")` | Execute a no-op JavaScript without navigating away. |
| `driver.getCurrentUrl()` | Get the URL of the current page. |
| `driver.getTitle()` | Get the title of the current page. |

---

*Happy Testing! 🚀*
