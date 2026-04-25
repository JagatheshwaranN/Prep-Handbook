# Selenium - Mouse Actions Questions & Answers

---

## General Questions

---

### 1. What is the Actions class in Selenium?

The `Actions` class in Selenium is used to perform **complex user interactions**, such as mouse and keyboard actions. It provides methods to simulate various input device actions, enabling automation of more sophisticated user interactions.

---

### 2. Explain the importance of the Actions class in Selenium testing.

The `Actions` class is crucial for automating scenarios that involve complex user interactions like drag-and-drop, double-click, right-click, etc. It allows testers to simulate real user actions more accurately, making automation scripts more robust and reflective of actual user behavior.

---

### 3. How do you perform a right-click using the Actions class?

Use the `contextClick()` method.

```java
Actions actions = new Actions(driver);
actions.contextClick(element).build().perform();
```

---

### 4. Explain the difference between `click()` and `sendKeys()` methods in the Actions class.

| Method | Type | Description |
|--------|------|-------------|
| `click()` | Mouse action | Simulates a **mouse click** on an element. |
| `sendKeys()` | Keyboard action | Simulates **keyboard input** (typing text or key presses). |

---

### 5. How do you perform a drag-and-drop operation using the Actions class?

Use the `dragAndDrop()` method with the source and target elements.

```java
Actions actions = new Actions(driver);
actions.dragAndDrop(sourceElement, targetElement).build().perform();
```

---

### 6. How can you handle composite actions using the Actions class?

Composite actions involve a **sequence of multiple actions**. Chain them together, then execute using `build().perform()`.

```java
Actions actions = new Actions(driver);
actions.moveToElement(element1)
       .click()
       .moveToElement(element2)
       .click()
       .build()
       .perform();
```

---

### 7. How do you perform a mouse click operation using Selenium?

Use the `click()` method from the `Actions` class.

```java
Actions actions = new Actions(driver);
actions.click(element).build().perform();
```

---

### 8. Explain the difference between `click()` and `doubleClick()` in Selenium.

| Method | Description |
|--------|-------------|
| `click()` | Simulates a **single** mouse click. |
| `doubleClick()` | Simulates a **double** mouse click. |

```java
Actions actions = new Actions(driver);

// Single click
actions.click(element).build().perform();

// Double click
actions.doubleClick(element).build().perform();
```

---

### 9. How do you simulate mouse hovering over an element in Selenium?

Use the `moveToElement()` method.

```java
Actions actions = new Actions(driver);
actions.moveToElement(element).build().perform();
```

---

### 10. How can you perform a right-click and select an option from the context menu?

After `contextClick()`, use `sendKeys()` with arrow keys to navigate the context menu and `Keys.RETURN` to select.

```java
Actions actions = new Actions(driver);
actions.contextClick(element)
       .sendKeys(Keys.ARROW_DOWN)
       .sendKeys(Keys.RETURN)
       .build()
       .perform();
```

---

### 11. How would you handle a dynamic context menu whose options change based on conditions?

- Use `contextClick()` to right-click the element.
- Dynamically locate and click the desired option using XPath or other locators.
- Use `sendKeys(Keys.ARROW_DOWN)` and `sendKeys(Keys.RETURN)` to navigate options.
- Implement dynamic waits (`WebDriverWait`) to ensure options are fully loaded before interaction.

```java
Actions actions = new Actions(driver);
actions.contextClick(element).build().perform();

WebDriverWait wait = new WebDriverWait(driver, 10);
WebElement menuOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
    By.xpath("//div[@class='context-menu']/li[text()='Desired Option']")));
menuOption.click();
```

---

### 12. How do you perform drag-and-drop when elements are not directly draggable or droppable?

Use `JavascriptExecutor` to manipulate the underlying HTML attributes controlling drag-and-drop behavior — setting `draggable` to `true` for the source element and triggering the drop action on the target.

```java
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript(
    "arguments[0].setAttribute('draggable', 'true');", sourceElement);
// Trigger custom drag events if needed
js.executeScript(
    "var e = new DragEvent('drop', {bubbles:true}); arguments[0].dispatchEvent(e);", targetElement);
```

---

### 13. How do you simulate holding the mouse button, moving to an element, and releasing it?

Use `clickAndHold()`, `moveToElement()`, and `release()` in sequence. This is also the manual approach for drag-and-drop.

```java
Actions actions = new Actions(driver);
actions.clickAndHold(sourceElement)
       .moveToElement(targetElement)
       .release()
       .build()
       .perform();
```

---

### 14. How do you handle dynamic content loading during a hover action?

Use `moveToElement()` to hover, then implement a `WebDriverWait` to wait for the dynamically loaded content to become visible or interactive before proceeding.

```java
Actions actions = new Actions(driver);
actions.moveToElement(element).build().perform();

WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicContent")));
```

---

### 15. What issues might occur with mouse actions in headless mode, and how do you address them?

In headless mode, graphical interactions including mouse actions may not behave as expected. Solutions include:
- Run tests in **non-headless mode** for debugging.
- Adjust **browser-specific configurations** or window sizes.
- Use **`JavascriptExecutor`** as a fallback for critical interactions that fail in headless mode.

---

### 16. How do you perform drag-and-drop across multiple frames?

Switch to the frame containing the source element, perform the drag, switch to the target frame, and release.

```java
driver.switchTo().frame("frame1");
WebElement sourceElement = driver.findElement(By.id("source"));

driver.switchTo().defaultContent();
driver.switchTo().frame("frame2");
WebElement targetElement = driver.findElement(By.id("target"));

Actions actions = new Actions(driver);
actions.moveToElement(sourceElement)
       .clickAndHold()
       .moveToElement(targetElement)
       .release()
       .build()
       .perform();

driver.switchTo().defaultContent();
```

---

### 17. How do you simulate a mouse click and keyboard key press simultaneously?

Use `keyDown()` to hold a key while performing the mouse click, then `keyUp()` to release.

```java
Actions actions = new Actions(driver);
actions.keyDown(Keys.CONTROL)
       .click(element)
       .keyUp(Keys.CONTROL)
       .build()
       .perform();
```

---

### 18. What are the different mouse operations available in the Actions class?

| # | Operation | Notes |
|---|-----------|-------|
| 1 | `click` | Standard mouse click. |
| 2 | `contextClick` / right-click | Opens context menu. |
| 3 | `doubleClick` | Double mouse click. |
| 4 | `dragAndDrop` (with/without offset) | Drag from source to target. |
| 5 | `clickAndHold` | Holds mouse button down. |
| 6 | `release` | Releases held mouse button. |
| 7 | `moveToElement` (with/without offset) | Hover over an element. |
| 8 | `sendKeys` | Keyboard input. |
| 9 | `ActionPause` | Pause between actions. |
| 10 | `ActionReset` | Reset via Remote WebDriver. |
| 11 | `moveToLocation` | Move to specific coordinates. |
| 12 | `moveByOffset` | Move by x/y offset from current position. |
| 13 | `mouseBackClick` | Via `PointerInput` and `Sequence` classes. |
| 14 | `mouseForwardClick` | Via `PointerInput` and `Sequence` classes. |
| 15 | `mouseMoveByCurrentPosition` | Via `PointerInput` and `Sequence` classes. |
| 16 | `mouseMoveByViewport` | Via `PointerInput` and `Sequence` classes. |

---

### 19. What is the `PointerInput` class?

The `PointerInput` class in Selenium WebDriver allows you to simulate **granular pointer interactions**, including:
- **Mouse events** — clicks, drags, hovers.
- **Touch events** — taps, swipes.

It provides finer control over pointer movements, durations, and origins (e.g., viewport, element center) compared to the `Actions` class.

---

### 20. Why and when should you use `PointerInput`?

| Scenario | Reason to Use `PointerInput` |
|----------|------------------------------|
| **Complex touch gestures** | More control over swipes, pinches that are hard to achieve with `Actions`. |
| **Touch-based or mobile web apps** | Provides precise touch simulation for touch-reliant applications. |
| **Advanced interaction scenarios** | When fine-grained control over movements, durations, and origins is needed. |

---

### 21. What is the `KeyboardInput` class?

The `KeyboardInput` class is an official Selenium class that allows for **simulating keyboard interactions** on web pages with more precision than `sendKeys()`.

---

### 22. Why and when should you use `KeyboardInput`?

| Scenario | Reason to Use `KeyboardInput` |
|----------|-------------------------------|
| **Advanced keyboard interactions** | Precise control over individual key presses, durations, and modifiers (Shift, Ctrl, Alt). |
| **Complex key combinations** | Sending specific key combinations that may be challenging with `sendKeys()`. |

---

## Exceptions

---

### 1. `MoveTargetOutOfBoundsException`

**What is it, and when does it occur?**

Thrown when attempting to perform an action (hover, click, etc.) on an element that is **not within the viewport or not accessible**.

**Cause:** The target element is outside the visible area of the browser.

**Solution:** Scroll to the element before performing the action.

```java
// Scroll the element into view first
((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

// Then perform the action
Actions actions = new Actions(driver);
actions.moveToElement(element).click().build().perform();
```

---

### 2. `InvalidCoordinatesException`

**What is it, and when does it occur?**

Thrown when attempting to move to an element or perform a mouse action at **coordinates that are not valid**.

**Cause:** The specified coordinates are outside the bounds of the browser window or not valid for the given element.

**Solution:** Verify element coordinates are within the viewport before interacting.

```java
// Get element position before acting
Point location = element.getLocation();
Dimension size = driver.manage().window().getSize();

if (location.getX() >= 0 && location.getY() >= 0
        && location.getX() <= size.getWidth()
        && location.getY() <= size.getHeight()) {
    Actions actions = new Actions(driver);
    actions.moveToElement(element).build().perform();
} else {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
}
```

---

## Actions Class — Quick Reference

| Method | Description |
|--------|-------------|
| `click(element)` | Single mouse click on an element. |
| `doubleClick(element)` | Double mouse click on an element. |
| `contextClick(element)` | Right-click on an element. |
| `moveToElement(element)` | Hover over an element. |
| `clickAndHold(element)` | Hold down mouse button on an element. |
| `release(element)` | Release held mouse button. |
| `dragAndDrop(src, target)` | Drag source element and drop on target. |
| `sendKeys(keys)` | Send keyboard input. |
| `keyDown(Keys.KEY)` | Hold down a keyboard key. |
| `keyUp(Keys.KEY)` | Release a held keyboard key. |
| `moveByOffset(x, y)` | Move mouse by x/y offset from current position. |
| `build()` | Build the action sequence. |
| `perform()` | Execute the built action sequence. |

---

*Happy Testing! 🚀*
