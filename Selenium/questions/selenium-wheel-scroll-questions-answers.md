# Selenium - Wheel & Scroll Questions & Answers

---

## General Questions

---

### 1. How do you scroll by a given pixel amount in Selenium?

Use the `scrollByAmount()` method from the `Actions` class. It takes **X-axis** and **Y-axis** values as input and scrolls by the provided amount.

```java
Actions actions = new Actions(driver);
actions.scrollByAmount(0, 500).perform(); // Scroll down 500px
```

> Positive Y value scrolls **down**; negative Y value scrolls **up**.
> Positive X value scrolls **right**; negative X value scrolls **left**.

---

### 2. How do you scroll from a specific element to a given pixel in Selenium?

Use Selenium's `WheelInput` and `ScrollOrigin` classes. The `ScrollOrigin.fromElement()` method sets the element as the scroll origin, and `scrollFromOrigin()` scrolls from that origin by the given pixel amount.

```java
WebElement element = driver.findElement(By.id("elementId"));

ScrollOrigin scrollOrigin = ScrollOrigin.fromElement(element);
Actions actions = new Actions(driver);
actions.scrollFromOrigin(scrollOrigin, 0, 300).perform();
// Scrolls 300px down from the element's position
```

---

### 3. How do you scroll from a specific element with X & Y offsets to a given pixel in Selenium?

Use `ScrollOrigin.fromElement()` with additional **X and Y offsets** to set the origin relative to the element, then use `scrollFromOrigin()` to scroll from that offset position.

```java
WebElement element = driver.findElement(By.id("elementId"));

// Set origin at element with 10px X offset and 50px Y offset
ScrollOrigin scrollOrigin = ScrollOrigin.fromElement(element, 10, 50);
Actions actions = new Actions(driver);
actions.scrollFromOrigin(scrollOrigin, 0, 300).perform();
// Scrolls 300px down from the offset position of the element
```

---

### 4. How do you scroll from the viewport to a given pixel in Selenium?

Use `ScrollOrigin.fromViewport()` with X and Y offsets to set the viewport as the scroll origin, then use `scrollFromOrigin()` to scroll.

```java
// Set origin at viewport position (100px from left, 200px from top)
ScrollOrigin scrollOrigin = ScrollOrigin.fromViewport(100, 200);
Actions actions = new Actions(driver);
actions.scrollFromOrigin(scrollOrigin, 0, 500).perform();
// Scrolls 500px down from the viewport origin
```

---

### 5. How do you scroll to a specific element in Selenium without using JavaScript?

Use the `scrollToElement()` method from the `Actions` class. It takes the target element as input and scrolls until the element is visible in the viewport.

```java
WebElement element = driver.findElement(By.id("targetElement"));
Actions actions = new Actions(driver);
actions.scrollToElement(element).perform();
```

---

### 6. How do you get the X and Y axis coordinates of an element?

Use the `getRect()` method of the `WebElement` interface, which returns a `Rectangle` object containing the element's position and size.

```java
WebElement element = driver.findElement(By.id("name"));

int xAxis = element.getRect().x;
int yAxis = element.getRect().y;
int width  = element.getRect().width;
int height = element.getRect().height;

System.out.println("X: " + xAxis + ", Y: " + yAxis);
```

---

### 7. What is the difference between `scrollToElement()` and `moveToElement()`?

| Feature | `scrollToElement()` | `moveToElement()` |
|---------|--------------------|--------------------|
| **Selenium Version** | Selenium **4+** | Available **before** Selenium 4 |
| **Primary Function** | Scrolls the element **fully into view** in the viewport. | Moves the **mouse cursor** to the element. |
| **Scrolling Behavior** | Explicitly scrolls to bring the element fully into view. | Implicit scrolling only — may not fully bring the element into view. |
| **Reliability** | ✅ Reliable for visibility. | ⚠️ Unreliable for ensuring full visibility. |
| **Use Case** | When you need the element to be **fully visible** before interaction. | When the primary goal is to **hover** or move the cursor to an element. |

```java
// scrollToElement - Scroll element into full view
Actions actions = new Actions(driver);
actions.scrollToElement(element).perform();

// moveToElement - Move mouse cursor to element (may scroll implicitly)
actions.moveToElement(element).perform();
```

---

## Scroll Methods — Quick Reference

| Method | Class | Description |
|--------|-------|-------------|
| `scrollByAmount(x, y)` | `Actions` | Scroll by a given pixel amount on X and Y axes. |
| `scrollToElement(element)` | `Actions` | Scroll until the element is fully visible in the viewport. |
| `scrollFromOrigin(origin, x, y)` | `Actions` | Scroll from a defined origin by X and Y pixels. |
| `ScrollOrigin.fromElement(element)` | `ScrollOrigin` | Sets a web element as the scroll origin. |
| `ScrollOrigin.fromElement(element, x, y)` | `ScrollOrigin` | Sets a web element with X/Y offset as the scroll origin. |
| `ScrollOrigin.fromViewport(x, y)` | `ScrollOrigin` | Sets a viewport position as the scroll origin. |
| `element.getRect().x` | `WebElement` | Gets the X-axis coordinate of the element. |
| `element.getRect().y` | `WebElement` | Gets the Y-axis coordinate of the element. |

---

*Happy Testing! 🚀*
