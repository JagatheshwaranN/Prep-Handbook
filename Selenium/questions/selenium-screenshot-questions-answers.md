# Selenium - Screenshot Questions & Answers

---

## General Questions

---

### 1. How do you take a screenshot in Selenium WebDriver?

Use the `TakesScreenshot` interface to capture a screenshot and save it to a file.

```java
WebDriver driver = new ChromeDriver();
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(source, new File("path/to/destination/screenshot.png"));
```

---

### 2. How do you capture a screenshot when using a headless browser?

The approach is the same as a regular browser — cast the WebDriver to `TakesScreenshot` and use `getScreenshotAs()`.

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless");
WebDriver driver = new ChromeDriver(options);

TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(source, new File("path/to/destination/screenshot.png"));
```

---

### 3. Can we take a screenshot of a specific element on a page?

Yes, you can capture a screenshot of a specific element using `getScreenshotAs()` directly on the `WebElement`.

```java
WebElement element = driver.findElement(By.id("button"));
File source = element.getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(source, new File("path/to/destination/screenshot.png"));
```

---

### 4. What are the different formats available to take a screenshot?

| Format | Description |
|--------|-------------|
| `OutputType.FILE` | Saves the screenshot as a temporary **`File`** object. |
| `OutputType.BYTES` | Returns the screenshot as a **byte array** (`byte[]`). |
| `OutputType.BASE64` | Returns the screenshot as a **Base64-encoded string**. |

```java
// FILE
File file = ts.getScreenshotAs(OutputType.FILE);

// BYTES
byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);

// BASE64
String base64 = ts.getScreenshotAs(OutputType.BASE64);
```

---

### 5. Can we print a page using Selenium?

Yes, Selenium provides the `PrintOptions` class, `Pdf` class, and `PrintsPage` interface to print a page as a PDF.

```java
PrintOptions printOptions = new PrintOptions();
Pdf pdf = ((PrintsPage) driver).print(printOptions);
String content = pdf.getContent();
Files.write(OutputType.BYTES.convertFromBase64Png(content), new File("./Selenium_Print.pdf"));
```

---

### 6. Does Selenium support full-page screenshots by default?

No, by default Selenium captures only the **viewport** (the visible area) of the page. To capture the full page, you need to use **third-party plugins or libraries** (e.g., AShot, Shutterbug).

---

### 7. What options does the `PrintOptions` class provide for printing a page as PDF?

| # | Option | Description |
|---|--------|-------------|
| 1 | **Orientation** | Sets the page orientation (`PORTRAIT` or `LANDSCAPE`). |
| 2 | **Scale** | Sets the scale of the content on the page. |
| 3 | **Background** | Specifies whether to print background colors and images. |
| 4 | **ShrinkToFit** | Shrinks the content to fit within the page dimensions. |
| 5 | **PageSize** | Sets the size of the PDF page (e.g., A4, Letter). |
| 6 | **PageMargin** | Defines the margins of the printed page. |
| 7 | **PageRanges** | Specifies which page ranges to include in the PDF. |

```java
PrintOptions printOptions = new PrintOptions();
printOptions.setOrientation(PrintOptions.Orientation.LANDSCAPE);
printOptions.setScale(0.8);
printOptions.setBackground(true);
printOptions.setShrinkToFit(true);
```

---

## Screenshot — Quick Reference

| Approach | Code |
|----------|------|
| **Full page screenshot** | `((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)` |
| **Element screenshot** | `element.getScreenshotAs(OutputType.FILE)` |
| **Headless screenshot** | Same as full page — works with headless `ChromeOptions`. |
| **Print to PDF** | `((PrintsPage) driver).print(new PrintOptions())` |
| **Save as FILE** | `OutputType.FILE` |
| **Save as BYTES** | `OutputType.BYTES` |
| **Save as BASE64** | `OutputType.BASE64` |

---

*Happy Testing! 🚀*
