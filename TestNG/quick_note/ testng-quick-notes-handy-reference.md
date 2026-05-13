# TestNG - Handy Notes: Complete Quick Reference

---

## 1. Annotations (in Execution Order)

```
@BeforeSuite
@BeforeTest
@BeforeClass
@BeforeMethod
@Test
@AfterMethod
@AfterClass
@AfterTest
@AfterSuite

@BeforeGroups
@AfterGroups

@Factory    — Generates instances for Test class.
```

**`@Test` — Method Level and Class Level:**

```java
// Class level — all public methods become test methods
@Test
public class Demo {
    public void demoTest() { }
}

// Method level — specific methods are test methods
public class Demo {
    @Test
    public void demoTest() { }
}
```

**`@Factory` — Dynamic test instance generation:**

```java
public class HandleFactoryTest {
    @Factory
    public Object[] createTestInstance() {
        Object[] result = new Object[3];
        for (int i = 0; i < 3; i++) {
            result[i] = new FactoryDemoTest(i);
        }
        return result;
    }
}

public class FactoryDemoTest {
    int id;
    public FactoryDemoTest(int id) { this.id = id; }

    @Test
    public void displayMessage() {
        System.out.println("Message " + id);
    }
}
```

---

## 2. Assertion

**Two types:**

| Type | Class | Behavior on Failure |
|------|-------|---------------------|
| **Hard Assertion** | `Assert` | Fails and **halts** execution immediately. |
| **Soft Assertion** | `SoftAssert` | **Captures** failure and reports at **end** of execution. |

### Hard Assertion

```java
Assert.assertEquals(actual, expected);
Assert.assertNotEquals(actual, expected);
Assert.assertTrue(condition);
Assert.assertFalse(condition);
Assert.assertSame(actual, expected);
Assert.assertNotSame(actual, expected);
Assert.fail("Failure message");
Assert.assertNull(object);
Assert.assertNotNull(object);
```

### Soft Assertion

```java
SoftAssert softAssert = new SoftAssert();

softAssert.assertEquals(actual, expected);
softAssert.assertNotEquals(actual, expected);
softAssert.assertTrue(condition);
softAssert.assertFalse(condition);
softAssert.assertSame(actual, expected);
softAssert.assertNotSame(actual, expected);
softAssert.fail("Failure message");
softAssert.assertNull(object);
softAssert.assertNotNull(object);
softAssert.assertAll(); // Reports all collected failures
```

---

## 3. Parameter

**Purpose:** Pass runtime values to test methods from `testng.xml`.

```java
public class Demo {
    @Test
    @Parameters({"name"})
    public void demoTest(@Optional("John") String name) {
        System.out.println("Name: " + name);
    }
}
```

**testng.xml:**

```xml
<suite name="Suite">
    <parameter name="name" value="Alice"/>
    <test name="Test">
        <parameter name="name" value="Bob"/> <!-- Test level takes precedence -->
        <classes>
            <class name="Demo"/>
        </classes>
    </test>
</suite>
```

> **Notes:**
> - `@Optional("defaultValue")` — used when no value is passed for the parameter.
> - **Test-level parameter takes precedence** over Suite-level parameter.
> - Parameters are passed from `testng.xml` files.

---

## 4. Attributes

### `dependsOnMethods`

Makes a test depend on other test methods.

**Types:**

| Type | `alwaysRun` | Behavior |
|------|------------|----------|
| **Hard Dependency** | `false` (default) | Skips the dependent test if the dependency fails. |
| **Soft Dependency** | `true` | Runs the dependent test even if the dependency fails. |

```java
@Test
public void test1() { }

@Test(dependsOnMethods = "test1", alwaysRun = true)
public void test2() { }       // Runs even if test1 fails

@Test(dependsOnMethods = {"test1", "test2"})
public void test3() { }       // Depends on both
```

> **Notes:**
> - Normal test methods **take precedence** over `dependsOnMethods` tests.
> - In **inherited** `dependsOnMethods` (between classes): failure is NOT ignored.
> - In **normal** `dependsOnMethods` (same class): failure causes dependent test to be skipped.

---

### `dependsOnGroups`

Makes a test depend on test groups.

```java
@Test(groups = "smoke")
public void test1() { }

@Test(dependsOnGroups = "smoke", groups = "sanity", alwaysRun = true)
public void test2() { }

@Test(dependsOnGroups = {"smoke", "sanity"})
public void test3() { }
```

**testng.xml — Include/Exclude groups:**

```xml
<groups>
    <run>
        <include name="smoke"/>
        <exclude name="regression"/>
    </run>
    <dependencies>
        <group name="sanity" depends-on="smoke"/>
    </dependencies>
</groups>
```

---

### Other Useful Attributes

| Attribute | Description |
|-----------|-------------|
| `enabled = false` | Ignore/disable the test from execution. |
| `expectedExceptions = ArithmeticException.class` | Test passes only if specified exception is thrown. |
| `priority = 1` | Sets execution order (lower number = higher priority). |
| `timeOut = 1000` | Test must complete within 1000ms or is marked as failed. |
| `invocationCount = 5` | Run the test method 5 times. |
| `threadPoolSize = 2` | Number of threads used with `invocationCount`. |
| `SkipException` | Throw inside a test to skip it from execution. |

---

## 5. DataProvider

**Purpose:** Provide test data for data-driven testing.

**Two ways to configure:**

**Method 1 — In the same test class:**

```java
public class Demo {
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        System.out.println(username + " / " + password);
    }

    @DataProvider(name = "loginData")
    public Object[][] provideData() {
        return new Object[][] {
            {"user1", "pass1"},
            {"user2", "pass2"}
        };
    }
}
```

**Method 2 — From a separate DataProvider class:**

```java
// Test class
@Test(dataProvider = "loginData", dataProviderClass = DataSupplier.class)
public void loginTest(String username, String password) { }

// DataProvider class
public class DataSupplier {
    @DataProvider(name = "loginData")
    public Object[][] provideData() {
        return new Object[][] {
            {"user1", "pass1"},
            {"user2", "pass2"}
        };
    }
}
```

> **Note:** DataProvider returns data as `Object[][]`. Can be used with **parallel** feature.

---

## 6. Parallel Execution

**Configured in `testng.xml`:**

```xml
<suite name="Suite" parallel="methods" thread-count="4">
    <test name="Test">
        <classes>
            <class name="DemoTest"/>
        </classes>
    </test>
</suite>
```

| Parallel Type | Behavior |
|--------------|----------|
| `tests` | Multiple `<test>` blocks run in parallel. |
| `methods` | Each test method in a class runs in parallel. |
| `instances` | Multiple instances of a test class run in parallel. |
| `classes` | Different classes run in parallel; methods within a class run sequentially. |

---

## 7. Test Result — `ITestResult`

**Purpose:** Capture test method status (pass/fail/skip) for reporting.

```java
public void afterMethod(ITestResult result) {
    if (ITestResult.SUCCESS == result.getStatus()) {
        System.out.println(result.getName() + " PASSED!");
    } else if (ITestResult.FAILURE == result.getStatus()) {
        System.out.println(result.getName() + " FAILED!");
    } else if (ITestResult.SKIP == result.getStatus()) {
        System.out.println(result.getName() + " SKIPPED!");
    }
}
```

| Status Constant | Meaning |
|----------------|---------|
| `ITestResult.SUCCESS` | Test passed. |
| `ITestResult.FAILURE` | Test failed. |
| `ITestResult.SKIP` | Test skipped. |

---

## 8. Listeners

| Listener Interface | Purpose |
|-------------------|---------|
| `ITestListener` | Listen to test execution events (start, success, failure, skip). |
| `ISuiteListener` | Listen to suite-level events (onStart, onFinish). |
| `IInvokedMethodListener` | Listen to individual method invocation events (before/after each method). |
| `IExecutionListener` | Capture events before/after the entire test suite execution. |
| `IHookableListener` | Intercept test method execution to customize behavior. |
| `IMethodInterceptor` | Intercept and modify test methods before execution (filter/reorder). |
| `IReporter` | Implement custom test reports. |
| `IRetryAnalyzer` | Implement retry logic for failed tests — used with `IAnnotationTransformer`. |

**Two ways to configure `ITestListener`:**

```java
// 1. Class level — using @Listeners annotation
@Listeners(MyListener.class)
public class DemoTest { }

// 2. Suite level — in testng.xml
// <listeners>
//     <listener class-name="com.example.MyListener"/>
// </listeners>
```

**`IRetryAnalyzer` example:**

```java
public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            return true; // Retry
        }
        return false; // Stop retrying
    }
}

// Use with @Test
@Test(retryAnalyzer = RetryAnalyzer.class)
public void flakyTest() { }
```

---

## TestNG — General Notes

| # | Key Point |
|---|-----------|
| 1 | TestNG is a test execution framework inspired by JUnit and NUnit. |
| 2 | **Advantages:** Reports, Annotations, Grouping, Parameterization, DataProvider, Priorities, Parallel execution, Maven/Jenkins integration, Dependency testing, Rich assertions. |
| 3 | Parallel execution configured in `testng.xml` — levels: `methods`, `tests`, `classes`, `instances`. |
| 4 | Listeners — interfaces for custom logic during execution. Examples: `ITestListener`, `ISuiteListener`. |
| 5 | `testng.xml` — XML config file to define suites, tests, groups, dependencies, parameters, and settings. |
| 6 | `@Factory` — dynamically generates test class instances at runtime. |
| 7 | TestNG generates **2 reports** after execution: **Emailable Report** and **Index Report**. |
| 8 | `@Optional` — sets a default parameter value when none is passed at runtime. |
| 9 | Regex patterns can be used for group names in `testng.xml`. |
| 10 | `invocationCount` — runs a test method a specified number of times. |
| 11 | `Reporter.log("message")` — logs custom messages into TestNG reports. |
| 12 | Hard Assertion halts execution on failure; Soft Assertion collects failures and reports at the end. |

---

## TestNG — Quick Reference

### Annotation Execution Order

```
BeforeSuite → BeforeTest → BeforeClass → BeforeMethod
→ Test → AfterMethod → AfterClass → AfterTest → AfterSuite
```

### testng.xml Structure

```xml
<suite name="Suite" parallel="methods" thread-count="4">
    <listeners>
        <listener class-name="com.example.MyListener"/>
    </listeners>
    <parameter name="env" value="staging"/>
    <groups>
        <run>
            <include name="smoke"/>
        </run>
    </groups>
    <test name="Regression">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="com.example.LoginTest"/>
            <class name="com.example.DashboardTest"/>
        </classes>
    </test>
</suite>
```

### Key Attributes Summary

| Attribute | Value Type | Example |
|-----------|-----------|---------|
| `groups` | String[] | `groups = {"smoke", "sanity"}` |
| `dependsOnMethods` | String[] | `dependsOnMethods = {"login"}` |
| `dependsOnGroups` | String[] | `dependsOnGroups = {"smoke"}` |
| `alwaysRun` | boolean | `alwaysRun = true` |
| `enabled` | boolean | `enabled = false` |
| `priority` | int | `priority = 1` |
| `timeOut` | long (ms) | `timeOut = 5000` |
| `invocationCount` | int | `invocationCount = 3` |
| `threadPoolSize` | int | `threadPoolSize = 2` |
| `dataProvider` | String | `dataProvider = "loginData"` |
| `expectedExceptions` | Class[] | `expectedExceptions = {Exception.class}` |
| `retryAnalyzer` | Class | `retryAnalyzer = RetryAnalyzer.class` |

---

*Happy Testing! 🚀*