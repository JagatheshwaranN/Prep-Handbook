# Selenium - Window Handling Questions & Answers

---

## General Questions

---

### 1. What is Window Handling in Selenium?

Window handling in Selenium refers to the mechanism of **interacting with multiple browser windows or tabs** during a test session. Selenium provides methods to switch between these windows and perform actions on them.

---

### 2. Explain the difference between `getWindowHandle()` and `getWindowHandles()` in Selenium.

| Method | Description |
|--------|-------------|
| `getWindowHandle()` | Returns the handle of the **current** window as a single string. |
| `getWindowHandles()` | Returns handles of **all open** windows or tabs as a `Set<String>`. |

> `getWindowHandles()` is typically used to switch between multiple windows.

---

### 3. How can you switch between windows in Selenium?

Use the `switchTo().window(handle)` method after obtaining window handles with `getWindowHandles()`.

```java
Set<String> windowHandles = driver.getWindowHandles();
for (String handle : windowHandles) {
    driver.switchTo().window(handle);
}
```

---

### 4. What is the purpose of the `getWindowHandle()` method?

`getWindowHandle()` retrieves the **unique alphanumeric handle** of the current window, which can be stored and used to switch back to that window later.

```java
String mainWindow = driver.getWindowHandle();
// Perform actions on other windows...
driver.switchTo().window(mainWindow); // Switch back
```

---

### 5. How do you close a specific window in Selenium?

Switch to the desired window using its handle, then call `close()`.

```java
String windowHandle = driver.getWindowHandle();
driver.switchTo().window(windowHandle).close();
```

---

### 6. How do you handle a situation where a new window opens as a result of a click?

After the click, use `getWindowHandles()` to get all handles, iterate to find the new window, switch to it, perform actions, and switch back when needed.

```java
String mainWindow = driver.getWindowHandle();
driver.findElement(By.id("openLink")).click();

Set<String> allHandles = driver.getWindowHandles();
for (String handle : allHandles) {
    if (!handle.equals(mainWindow)) {
        driver.switchTo().window(handle);
        // Perform actions in new window
        break;
    }
}
// Switch back to main window
driver.switchTo().window(mainWindow);
```

---

### 7. Explain the differences between `getWindowHandle()` and `getWindowHandles().size()`.

| Method | Returns |
|--------|---------|
| `getWindowHandle()` | A **single handle** string for the current window. |
| `getWindowHandles().size()` | The **total count** of currently open windows. |

---

### 8. How do you handle multiple browser tabs using Selenium?

Use `getWindowHandles()` to retrieve all tab handles, then iterate and switch between them as needed.

```java
Set<String> allTabs = driver.getWindowHandles();
for (String tab : allTabs) {
    driver.switchTo().window(tab);
    System.out.println("Tab title: " + driver.getTitle());
}
```

---

### 9. What is the default behavior of `driver.switchTo().window()` if no handle is provided?

If no window handle is provided, it switches to the **main or default window**.

---

### 10. How can you verify you are on the correct window/tab after switching?

| Method | Description |
|--------|-------------|
| `driver.getTitle()` | Retrieves the title of the current window/tab. |
| `driver.getCurrentUrl()` | Returns the URL of the current window/tab. |
| Specific page elements | Check for elements unique to the target window/tab. |

---

### 11. How do you handle situations where multiple windows open without any specific identifiers?

- Iterate through all window handles, switching to each and verifying the outcome of an action.
- Use `switchTo().activeElement()` to switch to the most recently opened window/tab.

---

### 12. How do you handle actions needed in both an original and a new window simultaneously?

Switch to the new window using `getWindowHandles()`, perform the required actions, then switch back to the original window and continue.

```java
String mainWindow = driver.getWindowHandle();
// Perform action in new window
driver.switchTo().window(newWindowHandle);
// ... actions ...
// Switch back
driver.switchTo().window(mainWindow);
```

---

### 13. Can you switch to a window without using its handle?

No, a window handle is required to switch to a specific window. However, you can use `getWindowHandles()` to retrieve all handles and switch based on their order or other properties like title or URL.

---

### 14. How do you handle dynamic pop-ups where window handles change each session?

Store all current window handles, compare them with the previous set, and identify the new window by finding the handle that wasn't present before.

```java
Set<String> beforeClick = driver.getWindowHandles();
driver.findElement(By.id("openPopup")).click();
Set<String> afterClick = driver.getWindowHandles();
afterClick.removeAll(beforeClick);
String newWindow = afterClick.iterator().next();
driver.switchTo().window(newWindow);
```

---

### 15. How do you ensure a new window has completely loaded before performing actions?

Use `WebDriverWait` with `ExpectedConditions.numberOfWindowsToBe()` to wait until the expected number of windows are open.

```java
WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
```

---

### 16. What challenges might you face when working with multiple windows in parallel?

| Challenge | Solution |
|-----------|----------|
| Synchronization issues | Use proper waits (`WebDriverWait`). |
| Unexpected pop-ups | Handle using `switchTo().alert()` or conditionals. |
| Order of actions | Maintain and track window handles explicitly. |

---

### 17. Can you switch to a window using a partial window title or URL?

Yes, iterate through all window handles and use `getTitle()` or `getCurrentUrl()` to find the matching window.

```java
Set<String> handles = driver.getWindowHandles();
for (String handle : handles) {
    driver.switchTo().window(handle);
    if (driver.getTitle().contains("Partial Title")) {
        break; // Found the desired window
    }
}
```

---

### 18. How do you handle a window that opens asynchronously (e.g., via AJAX)?

Use dynamic waits with `ExpectedConditions` to wait for a specific element or condition in the new window before proceeding.

```java
WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.numberOfWindowsToBe(2));
// Switch to new window and wait for an element
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("elementId")));
```

---

### 19. What happens if you switch to a non-existent window handle?

It throws a **`NoSuchWindowException`** as there is no window with the specified handle.

---

### 20. Can you switch between frames within a window while handling multiple windows?

No, frame switching is **specific to a single window**. You must first switch to the required window, and then switch between frames within that window.

---

### 21. How do you handle multiple pop-ups or windows that share similar attributes?

Use a combination of attributes — title, URL, or other unique identifiers — to distinguish between similar windows. Loop through all handles to find the correct one.

---

### 22. How would you test a scenario where multiple tabs open asynchronously within the same window?

- Use `WebDriverWait` and `ExpectedConditions` to wait for specific conditions like element presence or title changes indicating the new tab content has loaded.
- If newly loaded content appears in a frame, switch to the appropriate frame before interacting with elements.

---

### 23. What happens if you close the current window after storing handles with `getWindowHandles()`?

The closed window's handle becomes **invalid**. Attempting to switch to it will throw a `NoSuchWindowException`.

**Solutions:**
- Maintain an updated list of valid handles after closing any windows.
- Iterate through handles in reverse to avoid invalidating handles during the loop.

---

### 24. How do you handle multiple windows with similar titles or URLs?

- **Page element identification** — Locate elements unique to the target window and verify their presence after switching.
- **Partial URL matching** — Use substring matching to identify the desired window when URLs are partially similar.

---

### 25. How do you handle browser pop-ups that appear on top of the main window?

- Use `switchTo().alert()` to handle alert-type pop-ups (`accept()`, `dismiss()`, or retrieve text).
- Use `switchTo().activeElement()` to switch to the topmost element in browser-specific scenarios.

---

### 26. How would you test multiple tabs that open asynchronously within the same window?

Use `WebDriverWait` with `ExpectedConditions` for conditions like element presence or title changes, and combine with frame handling if the new content appears within a frame.

---

### 27. How do you handle a new window that takes time to load after a button click?

Use `WebDriverWait` to wait for the new window to appear, then switch to it.

```java
WebElement button = driver.findElement(By.id("openButton"));
button.click();

WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.numberOfWindowsToBe(2));

Set<String> windowHandles = driver.getWindowHandles();
Iterator<String> iterator = windowHandles.iterator();
String mainWindow = iterator.next();
String newWindow = iterator.next();
driver.switchTo().window(newWindow);
```

---

### 28. How do you handle dynamic windows where the number of windows is not known in advance?

Use `getWindowHandles()` in a loop to iterate through all handles dynamically.

```java
Set<String> windowHandles = driver.getWindowHandles();
for (String handle : windowHandles) {
    driver.switchTo().window(handle);
    // Perform actions in the current window
    if (driver.getTitle().contains("Desired Window Title")) {
        break;
    }
}
```

---

### 29. How do you handle scenarios where a link opens different types of windows (popup, tab, etc.) randomly?

After clicking the link, iterate through all window handles and check properties of each window to identify the desired type.

```java
WebElement link = driver.findElement(By.id("openLink"));
link.click();

Set<String> windowHandles = driver.getWindowHandles();
for (String handle : windowHandles) {
    driver.switchTo().window(handle);
    if (driver.getTitle().contains("Expected Title")) {
        // Perform actions in the identified window
        break;
    }
}
```

---

### 30. How do you handle a scenario where closing one window triggers the opening of another?

Handle the closing of the first window, switch back to the main window, and then handle the newly opened window.

```java
String mainWindow = driver.getWindowHandle();
// Perform actions that lead to closing and opening of a new window
driver.close(); // Close the current window
driver.switchTo().window(mainWindow); // Switch back to main
// Handle the new window as needed
```

---

### 31. How do you handle windows with dynamic titles or attributes?

Use partial matching or regular expressions to identify windows.

```java
Set<String> windowHandles = driver.getWindowHandles();
for (String handle : windowHandles) {
    driver.switchTo().window(handle);
    if (driver.getTitle().contains("Partial Title")) {
        // Perform actions in this window
        break;
    }
}
```

---

### 32. How do you automate login in a new window where credentials are pre-filled?

1. Use `getWindowHandles()` to identify and switch to the new window.
2. Access pre-filled fields using locators and enter/modify values.
3. Submit the form by clicking the submit button.
4. Switch back and validate the successful login.

```java
String mainWindow = driver.getWindowHandle();
// Switch to new window
driver.switchTo().window(newWindowHandle);
driver.findElement(By.id("username")).clear();
driver.findElement(By.id("username")).sendKeys("user@example.com");
driver.findElement(By.id("submitBtn")).click();
driver.switchTo().window(mainWindow);
```

---

### 33. How do you automate a test where clicking a button opens multiple windows in sequence?

- Use a loop to iterate through all handles from `getWindowHandles()`.
- Switch to each window, perform the required actions using locators.
- Track closed windows and update the valid handles list to avoid issues during iteration.

---

### 34. How do you close all windows except the one containing a specific keyword in the title?

```java
String keepHandle = null;
Set<String> windowHandles = driver.getWindowHandles();
for (String handle : windowHandles) {
    driver.switchTo().window(handle);
    if (driver.getTitle().contains("Keep This")) {
        keepHandle = handle;
    } else {
        driver.close(); // Close windows that don't match
    }
}
// Switch back to the window to keep
driver.switchTo().window(keepHandle);
```

---

### 35. What does `driver.switchTo().newWindow(WindowType.TAB)` do in Selenium?

It **creates a new browser tab** and switches the WebDriver's focus to the newly opened tab, allowing seamless interaction with its content.

```java
driver.switchTo().newWindow(WindowType.TAB);
driver.get("https://example.com");
```

---

### 36. How does `switchTo().newWindow(WindowType.TAB)` differ from opening a tab via JavaScript?

| Approach | Behavior |
|----------|----------|
| **JavaScript / Keyboard shortcut** | Opens a new tab at the browser level only. |
| **`switchTo().newWindow(WindowType.TAB)`** | Opens a new tab AND automatically switches WebDriver focus to it. |

---

### 37. What happens if the browser doesn't support tabs when using `WindowType.TAB`?

If the browser does not support tabs, `switchTo().newWindow(WindowType.TAB)` may still open a new window. The driver's focus will be switched to the newly opened window regardless.

---

### 38. What is the purpose of `driver.switchTo().newWindow(WindowType.WINDOW)`?

It **creates a new browser window** and switches the WebDriver's focus to it, enabling interaction with elements within that new window.

```java
driver.switchTo().newWindow(WindowType.WINDOW);
driver.get("https://example.com");
```

---

### 39. How does `switchTo().newWindow(WindowType.WINDOW)` differ from opening a window via JavaScript?

| Approach | Behavior |
|----------|----------|
| **JavaScript / Keyboard shortcut** | Opens a new window at the browser level only. |
| **`switchTo().newWindow(WindowType.WINDOW)`** | Opens a new window AND automatically switches WebDriver focus to it. |

---

### 40. What happens if the browser doesn't support multiple windows when using `WindowType.WINDOW`?

The driver's focus will still be switched to the newly opened context, even if it opens as a tab instead of a separate window. Behavior depends on the browser.

---

### 41. Why would you use `driver.manage().window().setPosition()` in a Selenium script?

To **control the initial position** of the browser window on the screen. Useful when specific UI elements need to be visible or properly aligned when the browser opens.

---

### 42. What is the data type of the argument passed to `setPosition()`?

The argument is an instance of the **`Point` class**, which represents a point `(x, y)` in a 2D coordinate system.

---

### 43. Can you set the window position to negative coordinates using `setPosition()`?

Yes, negative coordinates are allowed.

```java
driver.manage().window().setPosition(new Point(-5, -5));
```

---

### 44. How do you retrieve the current position of the browser window?

```java
Point currentPosition = driver.manage().window().getPosition();
System.out.println("X: " + currentPosition.getX() + ", Y: " + currentPosition.getY());
```

---

### 45. What happens if you set the window position outside the visible screen area?

The window may **not be fully visible** or may be partially off-screen, making parts of the window inaccessible.

---

### 46. Can you use `setPosition()` after the window has been maximized?

No, attempting to set the position after maximizing may be **ignored or have no effect**. Behavior is browser-specific.

---

### 47. How do you ensure cross-browser compatibility when using `setPosition()`?

- Be aware that browser behavior may vary.
- Test and validate across different browsers.
- Use conditional logic based on the browser type to handle browser-specific behaviors.

---

### 48. When might setting the window position be necessary for test automation?

When specific UI elements or interactions are **position-dependent** — for example, if a critical element is at a specific screen location and must be visible and interactable.

---

### 49. What other methods can be used alongside `setPosition()` to manage the browser window?

| Method | Description |
|--------|-------------|
| `driver.manage().window().setSize()` | Sets the browser window dimensions. |
| `driver.manage().window().maximize()` | Maximizes the browser window. |
| `driver.manage().window().fullscreen()` | Launches the browser in fullscreen mode. |
| `driver.manage().window().getPosition()` | Retrieves the current window position. |
| `driver.manage().window().getSize()` | Retrieves the current window size. |

---

### 50. What is the purpose of the `Point` class in Selenium?

The `Point` class represents a **point (x, y) in a 2D coordinate system**. It is used for specifying the position of UI elements or setting the position of the browser window.

---

### 51. How is the `Point` class used in Selenium WebDriver?

| Usage | Method |
|-------|--------|
| Get the position of a web element | `WebElement.getLocation()` |
| Set the position of the browser window | `driver.manage().window().setPosition(Point position)` |

---

### 52. How do you retrieve the x and y coordinates from a `Point` object?

```java
Point point = new Point(10, 20);
int x = point.getX();
int y = point.getY();
System.out.println("X: " + x + ", Y: " + y);
```

---

### 53. In what scenarios would you use the `Point` class when interacting with web elements?

- Verifying whether an element is at a specific location on the page.
- Setting the initial position of a draggable element.
- Validating element alignment or layout in UI tests.

---

### 54. How can you set the position of a browser window using the `Point` class?

```java
WebDriver driver = new ChromeDriver();
Point newPosition = new Point(100, 100);
driver.manage().window().setPosition(newPosition);
```

---

### 55. How does the `Point` class relate to the `Dimension` class in Selenium?

| Class | Represents |
|-------|------------|
| `Point` | The **position** of a point `(x, y)` in a 2D coordinate system. |
| `Dimension` | The **size** of an element or window `(width, height)`. |

Together, they are used to define both the position and size of elements or browser windows.

---

### 56. How do you verify the position of a specific element using the `Point` class?

```java
WebElement element = driver.findElement(By.id("myElement"));
Point location = element.getLocation();
System.out.println("X: " + location.getX() + ", Y: " + location.getY());
// Compare to expected coordinates
Assert.assertEquals(location.getX(), expectedX);
```

---

### 57. Why would you use `setSize()` in a Selenium script?

To **control the dimensions of the browser window**, ensuring a consistent view. Useful for:
- Simulating different screen resolutions.
- Ensuring specific UI elements are visible without manual resizing.

---

### 58. Can you set the window size to negative dimensions using `setSize()`?

No, negative dimensions are **not allowed**. Dimensions must be positive values.

---

### 59. How do you retrieve the current size of the browser window?

```java
Dimension currentSize = driver.manage().window().getSize();
System.out.println("Width: " + currentSize.getWidth() + ", Height: " + currentSize.getHeight());
```

---

### 60. What happens if you set the window size larger than the screen resolution?

The browser window may **extend beyond the visible screen area**. Behavior depends on the browser and operating system.

---

### 61. How do you maximize and minimize the browser window?

```java
driver.manage().window().maximize();  // Maximize the browser window
driver.manage().window().minimize();  // Minimize the browser window
```

---

### 62. How do you launch the browser in fullscreen mode?

```java
driver.manage().window().fullscreen();
```

---

## Exceptions

---

### 1. `NoSuchWindowException`

**What is it, and when does it occur?**

`NoSuchWindowException` is thrown when attempting to **switch to or interact with a browser window or tab that does not exist or has been closed**.

**Common causes:**
- Switching to a window that has already been closed.
- Providing an invalid window handle.
- Performing window operations after a window has been closed.

**How to handle it:**

```java
try {
    driver.switchTo().window(windowHandle);
    // Perform actions
} catch (NoSuchWindowException e) {
    System.out.println("Window not found: " + e.getMessage());
    // Log, take screenshot, or implement retry logic
}
```

**Prevention strategies:**
- Check the existence of windows before switching.
- Use `ExpectedConditions` with explicit waits for window presence.
- Handle window closure events appropriately.
- Maintain an updated list of valid window handles.

---

## Window Handling — Quick Reference

| Method | Description |
|--------|-------------|
| `driver.getWindowHandle()` | Get the current window's handle. |
| `driver.getWindowHandles()` | Get all open window handles. |
| `driver.switchTo().window(handle)` | Switch to a specific window by handle. |
| `driver.close()` | Close the current window. |
| `driver.quit()` | Close all windows and end the session. |
| `driver.switchTo().newWindow(WindowType.TAB)` | Open and switch to a new tab. |
| `driver.switchTo().newWindow(WindowType.WINDOW)` | Open and switch to a new window. |
| `driver.manage().window().maximize()` | Maximize the browser window. |
| `driver.manage().window().minimize()` | Minimize the browser window. |
| `driver.manage().window().fullscreen()` | Launch in fullscreen mode. |
| `driver.manage().window().setSize(Dimension)` | Set the window size. |
| `driver.manage().window().setPosition(Point)` | Set the window position. |
| `driver.manage().window().getSize()` | Get the current window size. |
| `driver.manage().window().getPosition()` | Get the current window position. |

---

*Happy Testing! 🚀*
