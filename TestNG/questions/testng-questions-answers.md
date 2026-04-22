# TestNG - Questions & Answers

---

## 1. What is TestNG?

TestNG is a testing framework inspired by JUnit and NUnit but introduces new functionalities that make it **more powerful and easier to use**.

---

## 2. What are the advantages of TestNG over JUnit?

| # | Advantage |
|---|-----------|
| 1 | **Automatic Reports** — Produces reports with details like failed tests, passed tests, and execution times. |
| 2 | **Annotations** — Uses intuitive annotations like `@BeforeMethod`, `@Test`, etc., named after their functionality. |
| 3 | **Grouping** — Groups multiple methods as one unit and performs operations on all tests in the group at once. |
| 4 | **Parameterization** — Allows providing parameters to call functions repeatedly with different values, supporting data-driven testing. |
| 5 | **Prioritization** — Allows altering the default execution sequence of test methods by defining priorities. |
| 6 | **Parallel Testing** — Supports parallel test execution, improving efficiency and reducing overall run time. |
| 7 | **Tool Integration** — Easily integrates with tools like Maven, Jenkins, etc. |
| 8 | **Cross-Browser Testing** — Supports running test methods on various browsers to detect compatibility issues. |
| 9 | **Independent Re-runs** — Allows running a single failed test independently in the next execution. |
| 10 | **Test Dependency** — Allows test methods to depend on each other. |
| 11 | **Assertions** — Provides a rich set of assertion methods for efficient testing. |

---

## 3. What are the different annotations used in TestNG?

| Annotation | Description |
|------------|-------------|
| `@Test` | Marks a method as a test method. |
| `@BeforeSuite` | Runs before all tests in the suite. |
| `@AfterSuite` | Runs after all tests in the suite. |
| `@BeforeTest` | Runs before any test method in the `<test>` tag. |
| `@AfterTest` | Runs after all test methods in the `<test>` tag. |
| `@BeforeGroups` | Runs before the first test method of the specified groups. |
| `@AfterGroups` | Runs after the last test method of the specified groups. |
| `@BeforeClass` | Runs before the first test method in the current class. |
| `@AfterClass` | Runs after all test methods in the current class. |
| `@BeforeMethod` | Runs before each test method. |
| `@AfterMethod` | Runs after each test method. |

---

## 4. How do you perform dependency testing in TestNG?

TestNG supports dependency testing through the `dependsOnMethods` attribute of the `@Test` annotation. By specifying dependencies between test methods, you can ensure a test runs only after the **successful execution of its dependent methods**.

```java
@Test
public void loginTest() { ... }

@Test(dependsOnMethods = "loginTest")
public void dashboardTest() { ... }
```

---

## 5. How can you run test cases in parallel in TestNG?

TestNG supports parallel execution by setting the `parallel` attribute of the `<suite>` tag in the `testng.xml` file. Parallel execution can be configured at different levels:

```xml
<suite name="Suite" parallel="methods" thread-count="4">
  ...
</suite>
```

Levels supported: `methods`, `tests`, `classes`, and `suites`.

---

## 6. Explain parameterization in TestNG.

Parameterization allows running the **same test method with different sets of data**. This is achieved using the `dataProvider` attribute of the `@Test` annotation, which points to a data provider method returning the test data.

```java
@DataProvider(name = "loginData")
public Object[][] getData() {
    return new Object[][] { {"user1", "pass1"}, {"user2", "pass2"} };
}

@Test(dataProvider = "loginData")
public void loginTest(String username, String password) { ... }
```

---

## 7. What is the purpose of listeners in TestNG?

Listeners are interfaces that allow you to implement **custom logic at specific points during test execution**. They are useful for logging, reporting, or other custom actions before or after a test method, class, or suite.

TestNG provides several built-in listeners including `ITestListener`, `IAnnotationTransformer`, `ISuiteListener`, etc.

---

## 8. What's the difference between `@BeforeTest` and `@BeforeMethod`?

| Annotation | Runs |
|------------|------|
| `@BeforeTest` | Once before all test methods inside a `<test>` tag in the TestNG XML file. |
| `@BeforeMethod` | Before **each individual** test method in a class. |

---

## 9. What is the importance of the `testng.xml` file?

The `testng.xml` file is an **XML configuration file** used to define how TestNG executes tests. It allows you to specify suites, test cases, groups, dependencies, parameters, and other settings.

---

## 10. What are the functionalities you can achieve using the `testng.xml` file?

1. Run multiple tests in order in a single execution.
2. Include and exclude specific test methods and groups.
3. Add dependencies between groups.
4. Pass parameters to test case methods.
5. Configure parallel test execution.

---

## 11. How can you achieve data-driven testing in TestNG?

Use the `@DataProvider` annotation to supply test data for a test method. The data provider method returns a **multidimensional array** used by the test method for each iteration.

```java
@DataProvider(name = "userData")
public Object[][] provideData() {
    return new Object[][] {
        {"John", "john@example.com"},
        {"Jane", "jane@example.com"}
    };
}

@Test(dataProvider = "userData")
public void createUser(String name, String email) { ... }
```

---

## 12. How can you handle failures in TestNG?

TestNG provides assertion methods like `assertEquals`, `assertTrue`, etc., to verify expected results. Failing assertions cause test failures. Unexpected errors can be handled using standard Java exception handling.

---

## 13. Explain the difference between soft assertions and hard assertions in TestNG.

| Type | Behavior |
|------|----------|
| **Hard Assertions** | Stop test execution immediately when an assertion fails. Invoked using `Assert.assertEquals()`, `Assert.assertTrue()`, etc. |
| **Soft Assertions** | Collect all failures and throw an assertion error only at the end of the test. Invoked using `SoftAssert` class methods. Useful when you want to continue executing steps after a failure to gather more information. |

```java
// Hard Assertion
Assert.assertEquals(actual, expected);

// Soft Assertion
SoftAssert softAssert = new SoftAssert();
softAssert.assertEquals(actual, expected);
softAssert.assertAll(); // Throws error here if any assertion failed
```

---

## 14. How can you configure TestNG to run tests in a specific order?

By default, TestNG runs test methods in **lexicographic order**. To run tests in a specific order, use the `priority` attribute of the `@Test` annotation. TestNG executes methods in **ascending order of priority**.

```java
@Test(priority = 1)
public void firstTest() { ... }

@Test(priority = 2)
public void secondTest() { ... }
```

---

## 15. How can you achieve parallel execution of tests with different data sets in TestNG?

This requires a combination of techniques:

1. Use `@DataProvider` to provide different data sets for the test method.
2. Set the `parallel` attribute to `true` in the `@Test` annotation.
3. Use the `threadPoolSize` attribute in `@Test` to specify the number of threads.
4. Ensure test methods are **independent and don't share state**, as parallel execution can cause concurrency issues.

```java
@Test(dataProvider = "data", parallel = true, threadPoolSize = 3)
public void myTest(String input) { ... }
```

---

## 16. How would you handle a scenario where a test case depends on the successful execution of another test case from a different class?

TestNG doesn't directly support inter-class dependencies. However, these workarounds can be used:

1. **Listener** — Create a custom listener to monitor test execution events. Update a shared flag/variable when the first test finishes successfully.
2. **TestNG Factory** — Develop a factory class that generates test instances pre-populated with data from the first test case's execution.
3. **External Data Storage** — Use a database or shared file to store the result of the first test case and access it in the second.

---

## 17. How can you handle dynamic test cases in TestNG?

Use the `@Factory` annotation to **dynamically generate test cases at runtime**. A factory method creates instances of the test class with different configurations or parameters, where each instance represents a separate test case.

```java
@Factory
public Object[] createTests() {
    return new Object[] { new MyTest("config1"), new MyTest("config2") };
}
```

---

## 18. How do you re-run failed tests in TestNG?

1. **TestNG Listeners** — Implement a custom `ITestListener` to capture failed test method names and class names during execution.
2. **`testng.xml` Configuration** — Create a separate `testng.xml` listing only the failed tests identified by the listener.
3. **Command-Line Execution** — Use the command below to re-run only the failed tests:

```bash
testng -rerun failed.xml
```

---

## 19. Define the correct order of tags in the TestNG XML file.

```xml
<suite>
  <test>
    <classes>
      <class>
        <methods>
        </methods>
      </class>
    </classes>
  </test>
</suite>
```

---

## 20. What are the types of reports generated in TestNG by default?

TestNG generates two types of reports by default after all test methods finish execution:

| Report | File Name | Location |
|--------|-----------|----------|
| **Emailable Report** | `emailable-report.html` | `<project>/test-output/` |
| **Index Report** | `index.html` | `<project>/test-output/` |

---

## 21. Where is the emailable report & index report generated and saved in TestNG?

Both reports are saved under the project folder in the `test-output` subfolder:
- **Emailable Report** → `test-output/emailable-report.html`
- **Index Report** → `test-output/index.html`

---

## 22. When parameters are declared at both Test and Suite level, which takes priority?

When parameters are defined at both levels, the **test-level parameter takes priority** over the suite-level parameter.

---

## 23. What is an Optional Parameter in TestNG?

Optional parameters act as **default values** — if no parameter value is specified, the optional value is used instead.

```java
@Test
@Parameters({ "Message" })
public void testTestLevelParameter(@Optional("Have a good day!") String message) {
    System.out.println("Hello! " + message);
}
```

---

## 24. How do you exclude a group from the test execution cycle?

Define the group to exclude in the `testng.xml` file using the `<exclude>` tag:

```xml
<test>
  <groups>
    <run>
      <exclude name="demo" />
    </run>
  </groups>
</test>
```

---

## 25. Can we use regular expressions in TestNG groups?

Yes, regular expressions can be used to match groups by pattern. For example, to run all groups starting with `func`:

```xml
<test>
  <groups>
    <run>
      <include name="func.*" />
    </run>
  </groups>
</test>
```

---

## 26. What is the significance of `timeout` in TestNG?

`timeout` defines the **maximum time a method can take for execution**. It is useful to prevent endless test execution.

It can be declared at:
- **Suite level** — Applies a time constraint to all methods in the suite.
- **Method level** — Applies a time constraint to a specific method.

```java
@Test(timeout = 1000)  // 1000 milliseconds
public void myTest() { ... }
```

---

## 27. What is meant by `invocationCount` in TestNG?

`invocationCount` defines the **number of times a test method runs** in a single execution.

```java
@Test(invocationCount = 5)  // Runs the test method 5 times
public void myTest() { ... }
```

---

## 28. What is meant by parallel test execution in TestNG?

Parallel test execution means **executing different test methods simultaneously** using multiple threads. It is more efficient than sequential execution and is managed automatically by the operating system's thread scheduler.

---

## 29. On what levels can we apply parallel testing in TestNG?

| Level | Description |
|-------|-------------|
| `methods` | Runs all `@Test` methods in parallel. |
| `tests` | Runs all test cases inside the `<test>` tag in parallel. |
| `classes` | Runs all test cases in the classes defined in the XML in parallel. |
| `instances` | Runs all test cases in parallel within the same instance. |

---

## 30. How is exception handling done in TestNG?

Exception handling is done by defining the expected exception at the `@Test` annotation level. The test will **not fail** even if the specified exception is raised.

```java
@Test(expectedExceptions = NumberFormatException.class)
public void myTest() { ... }
```

---

## 31. Can we disable a test in TestNG?

Yes, tests can be disabled using the `enabled` attribute. A disabled test will not run in the next execution cycle.

```java
@Test(enabled = false)
public void skippedTest() { ... }
```

---

## 32. Can we give a negative priority in TestNG?

Yes, **negative priorities are acceptable** in TestNG. Any integer value — negative, zero, or positive — can be assigned to the `priority` parameter.

---

## 33. Why is the Reporter class used in TestNG?

The `Reporter` class logs **tester-defined messages** into the reports generated by TestNG. These messages are printed in the reports and can be shared with the team.

---

## 34. Define the syntax for generating logs through the Reporter class in TestNG.

```java
Reporter.log("Your custom message here");
```

---

## 35. What is `@Factory` annotation in TestNG?

The `@Factory` annotation is used to create a factory method that **generates instances of test classes at runtime**. It is useful when you need to dynamically generate test cases based on certain conditions, parameters, or configurations.

---

## 36. What is the difference between `@Factory` and `@DataProvider` annotations?

| Feature | `@Factory` | `@DataProvider` |
|---------|-----------|-----------------|
| **Purpose** | Executes test methods multiple times by creating **different instances** of the same class. | Runs a test method multiple times using a **different set of data**. |
| **Scope** | Creates new class instances for each run. | Works within a single class instance. |

---

## 37. What are the types of Listeners in TestNG?

| # | Listener |
|---|----------|
| 1 | `ITestListener` |
| 2 | `IReporter` |
| 3 | `ISuiteListener` |
| 4 | `IInvokedMethodListener` |
| 5 | `IHookable` |
| 6 | `IConfigurationListener` |
| 7 | `IConfigurableListener` |
| 8 | `IAnnotationTransformer` |
| 9 | `IExecutionListener` |
| 10 | `IMethodInterceptor` |
| 11 | `IRetryAnalyzer` |

---

## 38. What is `ITestListener` in TestNG?

`ITestListener` is the most commonly used listener in TestNG with Selenium WebDriver. It listens to specific events and executes code defined inside its overridden methods.

It can be implemented in two ways:
- **Class level** — Annotating listeners on each class in the test code.
- **Suite level** — Defining class names in the `testng.xml` file.

---

## 39. What is `IReporter` Listener in TestNG?

`IReporter` provides a medium to **generate custom reports**. It contains the `generateReport()` method, invoked after all suites have run, which takes three arguments:

| Argument | Description |
|----------|-------------|
| `xmlSuite` | List of suites defined in the XML file. |
| `suites` | Object containing all test execution and suite information (package names, class names, test results, etc.). |
| `outputDirectory` | Path to the output folder where reports are saved. |

---

## 40. What is `ISuiteListener` in TestNG?

`ISuiteListener` listens to **suite-level events** such as the start and finish of a TestNG suite. Key features include:

1. **Suite-Level Events** — Provides methods to listen to suite start and finish events.
2. **Customization** — Allows setting up preconditions before a suite starts or cleanup after it finishes.
3. **Suite Information Access** — Provides access to suite name, test classes, and suite parameters.
4. **Integration** — Configured in the `testng.xml` file.

> **Note:** If a parent suite contains child suites, child suites execute before the parent suite, and their results are reflected in the parent.

---

## 41. What is `IInvokedMethodListener` in TestNG?

`IInvokedMethodListener` defines **custom behavior for method invocation events** during test execution. It provides two key methods:

| Method | Description |
|--------|-------------|
| `beforeInvocation(IInvokedMethod, ITestResult)` | Invoked **before** a test method is called. |
| `afterInvocation(IInvokedMethod, ITestResult)` | Invoked **after** a test method is called. |

---

## 42. What is `IHookable` Listener in TestNG?

`IHookable` listener is used to perform tasks **before running a method**, such as JAAS authentication or setting permissions. It runs its `run()` method instead of the `@Test` method. The `@Test` method is passed as an `IHookCallBack` object and can be invoked using `IHookable.runTestMethod()`.

---

## 43. What is `IConfigurationListener` in TestNG?

`IConfigurationListener` triggers when configuration methods pass, fail, or skip. It provides three methods:

| Method | Trigger |
|--------|---------|
| `onConfigurationSuccess(ITestResult itr)` | Invoked when a configuration method **succeeds**. |
| `onConfigurationFailure(ITestResult itr)` | Invoked when a configuration method **fails**. |
| `onConfigurationSkip(ITestResult itr)` | Invoked when a configuration method is **skipped**. |

---

## 44. What is `IConfigurable` Listener in TestNG?

`IConfigurable` provides a `run()` method that invokes **instead of each configuration method**. The configuration method is invoked when the `callback()` function is called through the `IConfigureCallBack` parameter.

```java
void run(IConfigureCallBack callback, ITestResult testresult);
```

---

## 45. What is `IAnnotationTransformer` Listener in TestNG?

`IAnnotationTransformer` **transforms annotations** found in the test code using its `transform()` method. It detects annotations and modifies them based on custom logic.

The `transform()` method contains the following parameters:

| Parameter | Description |
|-----------|-------------|
| `annotation` | The annotation read from the test class. |
| `testClass` | The class where the annotation was found (or `null`). |
| `testMethod` | The method where the annotation was found (or `null`). |
| `testConstructor` | The constructor where the annotation was found (or `null`). |

> **Note:** At least one of the above parameters must be non-null.

```java
void transform(ITestAnnotation annotation, Class testClass,
               Constructor testConstructor, Method testMethod);
```

---

## 46. What is `IExecutionListener` in TestNG?

`IExecutionListener` **monitors the start and end** of a test or suite. It provides two methods:

| Method | Trigger |
|--------|---------|
| `onExecutionStart()` | Invoked **before** the test/suite starts. |
| `onExecutionFinish()` | Invoked **after** all tests/suites finish execution. |

---

## 47. What is `IMethodInterceptor` Listener in TestNG?

`IMethodInterceptor` **alters or modifies the list of tests** to be executed by TestNG. It runs the `intercept()` method, which returns the ordered list of methods for TestNG to execute.

It contains one method:

| Method | Description |
|--------|-------------|
| `intercept()` | Returns the list of methods that TestNG will execute, in the desired order. |

---

## 48. What is `IRetryAnalyzer` Listener in TestNG?

`IRetryAnalyzer` allows users to implement **retry logic for failed test cases**. Key points:

1. Provides a way to specify custom retry behavior for individual test methods.
2. Allows failed test methods to be rerun a specified number of times.
3. Contains a single method: `retry(ITestResult result)`, which returns a `boolean` indicating whether the test should be retried.
4. The method takes an `ITestResult` parameter providing information about the test execution.

```java
public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            return true;  // Retry the test
        }
        return false;  // Stop retrying
    }
}
```

---

*Happy Testing! 🚀*
