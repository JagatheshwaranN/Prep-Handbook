# Selenium - Handy Notes: Complete Quick Reference

---

## 1. Alert

**Interface:** `Alert`  
**Purpose:** Handle alert/popup dialogs.

**Types:** Simple | Confirm | Prompt

```java
Alert alert = driver.switchTo().alert();
```

| Method | Description |
|--------|-------------|
| `accept()` | Click OK. |
| `dismiss()` | Click Cancel. |
| `getText()` | Get alert message text. |
| `sendKeys(value)` | Enter text into prompt. |
| `wait.until(ExpectedConditions.alertIsPresent())` | Wait and switch to alert. |

**Exceptions:** `NoAlertPresentException` | `UnhandledAlertException`

---

## 2. Dropdown

**Class:** `Select` (implements `ISelect` and `WrapElement` interfaces)  
**Types:** Single | Multiple

```java
Select selectObj = new Select(WebElement);
```

| Method | Description |
|--------|-------------|
| `selectByIndex(int)` | Select option by index. |
| `selectByValue(String)` | Select option by `value` attribute. |
| `selectByVisibleText(String)` | Select option by visible text. |
| `deselectByIndex(int)` | Deselect by index. |
| `deselectByValue(String)` | Deselect by value. |
| `deselectByVisibleText(String)` | Deselect by visible text. |
| `deselectAll()` | Deselect all — **MultiSelect ONLY**. |
| `isMultiple()` | Check if multi-select dropdown. |
| `getOptions()` | Get all options as `List<WebElement>`. |
| `getAllSelectedOptions()` | Get all selected options. |
| `getFirstSelectedOption()` | Get first selected option. |
| `getOptions().get(index)` | Get specific option by index. |

**Exception:** `UnexpectedTagNameException`

> **Notes:**
> - `deselectBy*` methods work **only with MultiSelect** dropdowns.
> - `selectByIndex()` — index may start from 0 or 1 depending on implementation.

---

## 3. Frame

**Class:** `RemoteWebDriver.RemoteTargetLocator` (implements `WebDriver.TargetLocator`)

```java
driver.switchTo().frame(locator);
```

| Method | Description |
|--------|-------------|
| `frame(int index)` | Switch to frame by index (starts at 0). |
| `frame(String idOrName)` | Switch to frame by ID or Name. |
| `frame(WebElement element)` | Switch to frame by WebElement. |
| `parentFrame()` | Switch from child frame to parent frame. |
| `defaultContent()` | Switch from frame back to main window. |
| `wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By))` | Wait and switch to frame. |

**Exception:** `NoSuchFrameException`

> **Note:** Frame index starts at **0**.

---

## 4. Navigation

**Class:** `RemoteWebDriver.RemoteNavigation` (implements `WebDriver.Navigation`)

```java
driver.navigate().to(url);
driver.navigate().back();
driver.navigate().forward();
driver.navigate().refresh();
```

| Method | Description |
|--------|-------------|
| `to(url)` | Navigate to a URL. |
| `back()` | Navigate back (browser Back button). |
| `forward()` | Navigate forward (browser Forward button). |
| `refresh()` | Reload the current page. |

---

## 5. Window

### Switching Windows

**Class:** `RemoteWebDriver.RemoteTargetLocator` (implements `WebDriver.TargetLocator`)

```java
driver.switchTo().window(handle);
driver.switchTo().newWindow(WindowType.WINDOW); // or WindowType.TAB
```

| Method | Description |
|--------|-------------|
| `window(handle)` | Switch to window by handle. |
| `newWindow(WindowType.WINDOW / TAB)` | Open and switch to new window or tab. |
| `driver.getWindowHandle()` | Get current window handle. |
| `driver.getWindowHandles()` | Get all open window handles. |

### Window Size & Position

**Class:** `RemoteWindow` (implements `WebDriver.Window`)

```java
driver.manage().window().maximize();
```

| Method | Description |
|--------|-------------|
| `maximize()` | Maximize the window. |
| `minimize()` | Minimize the window. |
| `fullscreen()` | Full-screen mode. |
| `setPosition(new Point(x, y))` | Set window position. |
| `getPosition()` | Get current position → `Point`. |
| `setSize(new Dimension(w, h))` | Set window size. |
| `getSize()` | Get current size → `Dimension`. |

**`Point` and `Dimension` classes:**

```java
// Position
driver.manage().window().setPosition(new Point(100, 200));
int x = driver.manage().window().getPosition().getX();
int y = driver.manage().window().getPosition().getY();

// Size
driver.manage().window().setSize(new Dimension(1280, 720));
int width  = driver.manage().window().getSize().getWidth();
int height = driver.manage().window().getSize().getHeight();
```

**Exception:** `NoSuchWindowException`

---

## 6. Cookies

**Class:** `RemoteWebDriverOptions` (implements `WebDriver.Options`)  
**Cookie class:** `Cookie` — holds attribute details for creating a cookie.

```java
Cookie cookie = new Cookie(name, value);
driver.manage().addCookie(cookie);
```

| Method | Description |
|--------|-------------|
| `addCookie(cookieObject)` | Add a cookie. |
| `getCookieNamed(name)` | Get a specific cookie by name. |
| `getCookies()` | Get all cookies. |
| `deleteCookieNamed(name)` | Delete cookie by name. |
| `deleteCookie(cookieObject)` | Delete a specific cookie object. |
| `deleteAllCookies()` | Delete all cookies. |

**Exceptions:** `InvalidCookieDomainException` | `NoSuchCookieException` | `UnableToSetCookieException`

---

## 7. Element

**Class:** `RemoteWebElement` (implements `WebElement` and other interfaces)

```java
WebElement element = driver.findElement(By.locator);
```

| Method | Description |
|--------|-------------|
| `clear()` | Clear input field. |
| `click()` | Click the element. |
| `sendKeys(value)` | Type text into element. |
| `isEnabled()` | Check if element is enabled. |
| `isDisplayed()` | Check if element is visible. |
| `isSelected()` | Check if element is selected. |
| `getText()` | Get visible text of element. |
| `getAttribute(name)` | Get HTML attribute value. |
| `getCssValue(property)` | Get CSS property value. |
| `getDomProperty(name)` | Get DOM property value. |
| `getDomAttribute(name)` | Get DOM attribute value. |
| `findElement(locator)` | Find child element. |
| `findElements(locator)` | Find all matching child elements. |
| `getShadowRoot()` | Get shadow root of element. |
| `getTagName()` | Get HTML tag name. |
| `getAriaRole()` | Get ARIA role. |
| `getAccessibleName()` | Get accessible name. |

**Exception:** `NoSuchElementException`

---

## 8. Mouse Actions

**Class:** `Actions`

```java
Actions actions = new Actions(driver);
actions.click(element).perform();
```

| Method | Description |
|--------|-------------|
| `click(element)` | Click on element. |
| `clickAndHold(element)` | Hold mouse button down. |
| `release(element)` | Release held mouse button. |
| `contextClick(element)` | Right-click. |
| `doubleClick(element)` | Double-click. |
| `moveToElement(element)` | Hover over element. |
| `scrollToElement(element)` | Scroll element into view. |
| `dragAndDrop(src, target)` | Drag and drop. |
| `dragAndDropBy(src, xOffset, yOffset)` | Drag by offset. |
| `sendKeys(element, value)` | Type into element. |
| `keyDown(Keys)` | Press and hold a key. |
| `keyUp(Keys)` | Release a held key. |
| `pause(Duration)` | Pause between actions. |

```java
// Offset calculation for dragAndDropBy
int xOffset = droppable.getX() - draggable.getX();
int yOffset = droppable.getY() - draggable.getY();
```

### PointerInput

```java
PointerInput mouse = new PointerInput(PointerInput.Kind.MOUSE, "Default Mouse");
// Methods: createPointerDown(), createPointerUp()
```

### Sequence

```java
Sequence sequence = new Sequence(inputSource, length);
sequence.addAction(action);
```

**Exceptions:** `MoveTargetOutOfBoundsException` | `InvalidCoordinatesException`

---

## 9. Waits

### Implicit Wait

**Class:** `RemoteTimeouts` (implements `Timeouts`)

```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
```

### Explicit Wait — `WebDriverWait`

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
```

### Fluent Wait

```java
Wait<WebDriver> wait = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofSeconds(2))
    .ignoring(NoSuchElementException.class);
wait.until(driver -> driver.findElement(By.id("id")));
```

| Wait Type | Scope | Polling | Exception Ignoring |
|-----------|-------|---------|-------------------|
| Implicit | Global | Default | ❌ |
| Explicit (`WebDriverWait`) | Per condition | Default | ❌ |
| Fluent | Per condition | ✅ Custom | ✅ Custom |

---

## 10. Screenshot

**Interface:** `TakesScreenshot`  
**Implemented by:** `RemoteWebDriver`

```java
TakesScreenshot ts = (TakesScreenshot) driver;
File src = ts.getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(src, new File("/path/screenshot.png"));

// Element-level screenshot
File elemSrc = driver.findElement(By.id("id")).getScreenshotAs(OutputType.FILE);
```

| Format | Description |
|--------|-------------|
| `OutputType.FILE` | Screenshot as a `File`. |
| `OutputType.BYTES` | Screenshot as `byte[]`. |
| `OutputType.BASE64` | Screenshot as Base64 string. |

> **Note:** Screenshot can be taken at **page level** and **element level**.

---

## 11. Print (PDF)

**Classes/Interfaces:** `PrintOptions` | `Pdf` | `PrintsPage`

```java
PrintOptions prints = new PrintOptions();
Pdf pdf = ((PrintsPage) driver).print(prints);
String content = pdf.getContent();
Files.write(OutputType.BYTES.convertFromBase64Png(content), new File("./print.pdf"));
```

---

## 12. Page

**Class:** `RemoteWebDriver` (implements `WebDriver`)

```java
String title   = driver.getTitle();
String url     = driver.getCurrentUrl();
String source  = driver.getPageSource();
```

---

## 13. Wheel / Scroll

**Classes:** `WheelInput` | `ScrollOrigin` | `Actions`

```java
// Scroll from element
WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(element);
new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 500).perform();

// Scroll from element with offset
WheelInput.ScrollOrigin scrollOriginWithOffset = WheelInput.ScrollOrigin.fromElement(element, 10, 50);

// Scroll from viewport
WheelInput.ScrollOrigin viewportOrigin = WheelInput.ScrollOrigin.fromViewport(100, 200);

// Scroll to element
new Actions(driver).scrollToElement(element).perform();

// Scroll by amount
new Actions(driver).scrollByAmount(0, 500).perform();
```

| Method | Description |
|--------|-------------|
| `ScrollOrigin.fromElement(element)` | Set element as scroll origin. |
| `ScrollOrigin.fromElement(element, x, y)` | Set element + offset as origin. |
| `ScrollOrigin.fromViewport(x, y)` | Set viewport position as origin. |
| `scrollToElement(element)` | Scroll until element is in view. |
| `scrollByAmount(x, y)` | Scroll by X and Y pixels. |
| `scrollFromOrigin(origin, x, y)` | Scroll from origin by given pixels. |

---

## 14. Keyboard

**Class:** `Actions` | **Enum:** `Keys`

```java
Actions actions = new Actions(driver);
actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
actions.sendKeys(Keys.chord(Keys.CONTROL, "c")).perform();
```

| Method | Description |
|--------|-------------|
| `keyDown(Keys)` | Press and hold a key. |
| `keyUp(Keys)` | Release a held key. |
| `sendKeys(Keys)` | Send key press. |
| `sendKeys(chord)` | Send key combination. |
| `sendKeys(element, Keys)` | Send keys to specific element. |
| `Keys.chord(Keys...)` | Create key combination. |

---

## 15. File Upload & Download

### File Upload

```java
// Input type="file" required
driver.findElement(By.id("fileInput")).sendKeys("/path/to/file.txt");
```

> **Alternatives:** AutoIT | Robot class

### File Download (Chrome)

```java
HashMap<String, Object> prefs = new HashMap<>();
prefs.put("profile.default_content_setting.popups", 0);
prefs.put("download.default_directory", System.getProperty("user.dir"));

ChromeOptions options = new ChromeOptions();
options.setExperimentalOption("prefs", prefs);
WebDriver driver = new ChromeDriver(options);
```

> **Verification:** Use Java `File` class to check the downloaded file exists.

---

## 16. Driver Options

### Browser Options

```java
ChromeOptions chromeOptions   = new ChromeOptions();
EdgeOptions edgeOptions       = new EdgeOptions();
FirefoxOptions firefoxOptions = new FirefoxOptions();
```

| Method | Description |
|--------|-------------|
| `addArguments(String)` | Add browser argument. |
| `setExperimentalOption(String, Object)` | Set experimental option. |
| `setCapability(Capability, boolean)` | Set capability. |
| `setPageLoadStrategy(PageLoadStrategy)` | Set page load strategy. |
| `setAcceptInsecureCerts(boolean)` | Accept insecure SSL certs. |
| `setStrictFileInteractability(boolean)` | Set strict file interactability. |
| `setBinary(File)` | Set custom browser binary. |

**Common Arguments:**

```java
// Chrome / Edge
chromeOptions.addArguments("--headless=new");
chromeOptions.addArguments("incognito");
chromeOptions.addArguments("start-maximized");

// Edge
edgeOptions.addArguments("-inprivate");

// Firefox
firefoxOptions.addArguments("--headless");
firefoxOptions.addArguments("--private");
```

**Get Capabilities:**

```java
Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
cap.getBrowserName();
cap.getBrowserVersion();
```

**Proxy:**

```java
Proxy proxy = new Proxy();
proxy.setHttpProxy("IP:Port");
chromeOptions.setProxy(proxy);
```

**ChromeDriverService:**

```java
ChromeDriverService service = new ChromeDriverService.Builder()
    .usingDriverExecutable(new File("path/to/chromedriver"))
    .usingPort(9515)
    .build();
```

---

## 17. JavaScriptExecutor

**Interface:** `JavascriptExecutor`  
**Implemented by:** `RemoteWebDriver`

```java
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript(script, arguments);
js.executeAsyncScript(script, arguments);
```

**Common Examples:**

```java
// Click element
js.executeScript("arguments[0].click()", element);

// Set value
js.executeScript("arguments[0].value='" + value + "';", input);

// Remove attribute
js.executeScript("arguments[0].removeAttribute('disabled');", input);

// Clear value
js.executeScript("arguments[0].value='';", input);

// Highlight element
js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid green;')", element);

// Scroll to bottom
js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

// Scroll by pixels
js.executeScript("window.scrollBy(0, 500)");

// Scroll element into view
js.executeScript("arguments[0].scrollIntoView(true)", element);

// Navigate to URL
js.executeScript("window.location = 'https://example.com/'");

// Refresh page
js.executeScript("document.location.reload()");

// Get title
String title = js.executeScript("return document.title").toString();

// Get inner text
String text = js.executeScript("return document.documentElement.innerText;").toString();

// Get window dimensions
long height = (long) js.executeScript("return window.innerHeight;");
long width  = (long) js.executeScript("return window.innerWidth;");

// Zoom
js.executeScript("document.body.style.zoom='" + percent + "'");
```

**Use Cases:** DOM manipulation | Dynamic elements | AJAX handling | Scrolling | Drag-and-drop

---

## 18. Locators

**Types:** Traditional (8) | Relative (5)

### Traditional Locators

| Locator | Usage |
|---------|-------|
| `By.id(value)` | `driver.findElement(By.id("id"))` |
| `By.className(value)` | `driver.findElement(By.className("class"))` |
| `By.name(value)` | `driver.findElement(By.name("name"))` |
| `By.tagName(value)` | `driver.findElement(By.tagName("input"))` |
| `By.linkText(value)` | `driver.findElement(By.linkText("Click Here"))` |
| `By.partialLinkText(value)` | `driver.findElement(By.partialLinkText("Click"))` |
| `By.cssSelector(value)` | `driver.findElement(By.cssSelector("#id"))` |
| `By.xpath(value)` | `driver.findElement(By.xpath("//input[@id='x']"))` |

### Relative Locators (Selenium 4)

```java
driver.findElement(with(By.tagName("input")).above(By.id("refElement")));
```

| Locator | Description |
|---------|-------------|
| `above(By)` | Element above the reference. |
| `below(By)` | Element below the reference. |
| `toLeftOf(By)` | Element to the left of reference. |
| `toRightOf(By)` | Element to the right of reference. |
| `near(By)` | Element near the reference (any direction). |

### XPath Functions

`contains` | `starts-with` | `text()` | `following` | `ancestor` | `child` | `preceding` | `following-sibling` | `parent` | `self` | `descendant` | `ancestor-or-self` | `descendant-or-self`

### CSS Selector Patterns

| Pattern | Example |
|---------|---------|
| By ID | `#elementId` |
| By class | `.className` |
| By tag + ID | `tag#id` |
| By tag + class | `tag.class` |
| By attribute | `tag[attr=value]` |
| Starts with | `tag[attr^=value]` |
| Ends with | `tag[attr$=value]` |
| Contains | `tag[attr*=value]` |
| Parent > child | `parent > child[attr=value]` |
| nth-child | `li:nth-child(n)` |
| First/Last child | `li:first-child` / `li:last-child` |
| Not selector | `li:nth-child(n):not(ol > li:nth-child(n))` |
| Adjacent sibling | `tag + siblingTag` |
| Descendant | `tag ~ descendantTag` |

---

## 19. Shadow DOM

**Types:** Open | Closed

**Components:**

| Term | Description |
|------|-------------|
| **Shadow HOST** | Regular DOM node the Shadow DOM is attached to. |
| **Shadow TREE** | DOM tree inside the Shadow DOM. |
| **Shadow BOUNDARY** | Where Shadow DOM ends and regular DOM begins. |
| **Shadow ROOT** | Root node of the Shadow tree. |

```java
// Method 1 — getShadowRoot() (Selenium 4)
WebElement shadowHost = driver.findElement(By.cssSelector("shadow-host-selector"));
SearchContext shadowRoot = shadowHost.getShadowRoot();
shadowRoot.findElement(By.cssSelector("inner-element")).sendKeys("Hello");

// Method 2 — JavaScriptExecutor
WebElement shadowHost = driver.findElement(By.cssSelector("shadow-host-selector"));
WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver)
    .executeScript("return arguments[0].shadowRoot", shadowHost);
shadowRoot.findElement(By.cssSelector("inner-element")).click();
```

> **Note:** Only **CSS selectors** work inside Shadow DOM — XPath is not supported.

---

## Complete Selenium Class/Interface Map

| Feature | Class/Interface | Method Chain |
|---------|----------------|--------------|
| Alert | `Alert` (interface) | `driver.switchTo().alert()` |
| Dropdown | `Select` class | `new Select(element)` |
| Frame | `TargetLocator` | `driver.switchTo().frame()` |
| Navigation | `Navigation` | `driver.navigate()` |
| Window switch | `TargetLocator` | `driver.switchTo().window()` |
| Window size | `Window` | `driver.manage().window()` |
| Cookies | `Options` | `driver.manage().addCookie()` |
| Element | `WebElement` | `driver.findElement()` |
| Mouse/Keyboard | `Actions` | `new Actions(driver)` |
| Implicit Wait | `Timeouts` | `driver.manage().timeouts()` |
| Explicit Wait | `WebDriverWait` | `new WebDriverWait(driver, duration)` |
| Fluent Wait | `FluentWait` | `new FluentWait<>(driver)` |
| Screenshot | `TakesScreenshot` | `(TakesScreenshot) driver` |
| Print (PDF) | `PrintsPage` | `(PrintsPage) driver` |
| Page info | `WebDriver` | `driver.getTitle()` etc. |
| Scroll | `Actions` + `ScrollOrigin` | `new Actions(driver).scrollToElement()` |
| JavaScript | `JavascriptExecutor` | `(JavascriptExecutor) driver` |
| Shadow DOM | `SearchContext` | `element.getShadowRoot()` |

---

*Happy Testing! 🚀*