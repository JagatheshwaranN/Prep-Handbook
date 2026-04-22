# Cucumber BDD - Questions & Answers

---

## 1. What is Cucumber?

Cucumber is a testing framework that supports **Behavior-Driven Development (BDD)**. It allows you to write test cases in a natural language format, known as **Gherkin**, which is easily understandable by non-technical stakeholders.

---

## 2. What are the key components of Cucumber?

| # | Component | Description |
|---|-----------|-------------|
| 1 | **Feature Files** | Written in Gherkin syntax to describe application behavior. |
| 2 | **Step Definitions** | Written in Java, Ruby, or JavaScript to implement the steps. |
| 3 | **Runner Classes** | Used to execute the feature files. |
| 4 | **Hooks** | Special methods for setup and teardown operations. |
| 5 | **Tags** | Annotations to organize and run specific scenarios or features. |

---

## 3. What is Gherkin syntax?

Gherkin is a **business-readable, domain-specific language** used to describe the behavior of a software application in a structured format. It consists of predefined keywords such as `Given`, `When`, `Then`, `And`, and `But`, which help in writing scenarios in a human-readable format.

---

## 4. What are the advantages of using Cucumber for testing?

1. Improved collaboration between technical and non-technical stakeholders.
2. Easy-to-understand feature files facilitate clear communication and documentation.
3. Reusability of step definitions across different scenarios.
4. Integration with various programming languages and testing frameworks.
5. Ability to generate reports for test results and coverage analysis.

---

## 5. How do you write a scenario in a feature file?

Scenarios in feature files are written using Gherkin syntax. Each scenario typically consists of a title, followed by steps using `Given`, `When`, `Then`, `And`, or `But` keywords.

```gherkin
Feature: User Login

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid username and password
    Then I should be redirected to the dashboard
```

---

## 6. Explain the concept of tags in Cucumber.

Tags are **annotations used to organize and filter** scenarios or features. They are prefixed with the `@` symbol and can be added to feature files, scenarios, or individual steps. Tags can mark scenarios for different purposes such as smoke testing, regression testing, or environment-specific execution.

```gherkin
@smoke @regression
Scenario: Successful login
  Given I am on the login page
  ...
```

---

## 7. What are step definitions?

Step definitions are the **implementation of the steps** described in feature files. They are written in programming languages like Java, Ruby, or JavaScript and execute the corresponding actions or validations when a step is matched during test execution.

```java
@Given("I am on the login page")
public void iAmOnTheLoginPage() {
    driver.get("https://example.com/login");
}
```

---

## 8. How do you parameterize steps in Cucumber?

Steps can be parameterized by using **placeholders in Gherkin syntax**, typically enclosed within angle brackets `< >`. These placeholders are passed as arguments to step definition methods.

```gherkin
Given I have <number> cucumbers
```

```java
@Given("I have {int} cucumbers")
public void iHaveCucumbers(int number) {
    // use number
}
```

---

## 9. Explain the concept of Background in Cucumber.

`Background` allows you to specify **steps common to all scenarios** in a feature file. These steps execute before each scenario, reducing duplication and improving readability.

```gherkin
Feature: Shopping Cart

  Background:
    Given I am logged in as a registered user
    And I am on the shopping page

  Scenario: Add item to cart
    When I add a shirt to my cart
    Then the cart quantity should be 1
```

---

## 10. How do you handle data tables and scenario outlines in Cucumber?

- **Data Tables** — Used to pass tabular data directly to steps.
- **Scenario Outlines** — Define a scenario template that runs with different sets of data provided in an `Examples` table.

```gherkin
Scenario Outline: Login with different credentials
  Given I am on the login page
  When I enter "<username>" and "<password>"
  Then I should be logged in successfully

  Examples:
    | username | password |
    | user1    | pass1    |
    | user2    | pass2    |
```

---

## 11. What are the keywords used in Gherkin syntax?

| Keyword | Purpose |
|---------|---------|
| `Feature` | Defines a high-level application feature. |
| `Scenario` | Describes a specific user behavior within a feature. |
| `Given` | Defines preconditions before the scenario starts. |
| `When` | Describes the action taken by the user. |
| `Then` | Describes the expected outcome after the action. |
| `And` | Combines multiple steps within a scenario. |
| `But` | Combines multiple steps but represents negative statements. |
| `Scenario Outline` | Describes a scenario with data-driven testing. |
| `*` | Wildcard keyword used in place of `Given`, `When`, or `Then`. |
| `Description` | Provides a description about the scenario. |
| `#comment` | Provides comments about the code. |

---

## 12. How do you handle data-driven testing with Cucumber?

Cucumber supports **data tables in feature files** to provide multiple sets of data for a scenario. Step definitions can access this data using the `DataTable` library.

```gherkin
Scenario: Add multiple users
  Given the following users exist
    | Name  | Email            |
    | John  | john@example.com |
    | Jane  | jane@example.com |
```

```java
@Given("the following users exist")
public void theFollowingUsersExist(DataTable dataTable) {
    List<Map<String, String>> users = dataTable.asMaps();
    for (Map<String, String> user : users) {
        System.out.println(user.get("Name") + " - " + user.get("Email"));
    }
}
```

---

## 13. How do you handle errors and exceptions in Cucumber tests?

- Use **Java exception handling** mechanisms (`try-catch`) within step definitions.
- Use **assertions** from libraries like JUnit (`assertEquals`, `assertTrue`) to verify expected behavior.

```java
@Then("the response status should be {int}")
public void theResponseStatusShouldBe(int expectedStatus) {
    Assert.assertEquals(expectedStatus, response.getStatusCode());
}
```

---

## 14. What are hooks in Cucumber, and how are they used?

Hooks are **special methods that run before or after certain events** (scenarios or steps) during Cucumber execution. They allow setup and teardown tasks such as initializing data or closing resources.

Key points:
- Not part of the feature file.
- Can be written in a step definition class or a separate configuration file.

---

## 15. How many types of hooks are there in Cucumber?

| Hook | Execution |
|------|-----------|
| `@Before` | Runs **before each scenario**. |
| `@After` | Runs **after each scenario**. |
| `@BeforeStep` | Runs **before each step** of the scenario. |
| `@AfterStep` | Runs **after each step** of the scenario. |

```java
@Before
public void setUp() {
    System.out.println("Setting up before scenario");
}

@After
public void tearDown() {
    System.out.println("Cleaning up after scenario");
}
```

---

## 16. What is the purpose of `@BeforeStep` and `@AfterStep` hooks?

`@BeforeStep` and `@AfterStep` hooks allow you to execute setup and teardown tasks **before and after each step** in a scenario. Common use cases include:
- Logging step execution details.
- Capturing screenshots after each step.
- Setting up or cleaning up context for individual steps.

---

## 17. How do you prioritize hooks in Cucumber?

Hooks can be prioritized using the `order` attribute. By default, hooks execute in the order they are defined, but you can specify a **numerical value** to control the execution order.

```java
@Before(order = 1)
public void firstHook() { ... }

@Before(order = 2)
public void secondHook() { ... }
```

---

## 18. What are the benefits of using Hooks?

1. **Improved Code Readability** — Separating setup and cleanup logic from test steps makes the code cleaner and easier to understand.
2. **Reduced Redundancy** — Common setup and cleanup tasks are defined once in hooks, avoiding repetitive code across scenarios.
3. **Better Test Organization** — Hooks help structure tests by separating concerns and promoting modularity.

---

## 19. What are the 2 types of expressions supported in Cucumber?

| Type | Description |
|------|-------------|
| **Cucumber Expression** | Generated by default in step definition files. |
| **Regular Expression** | Can also be used in step definition files. |

> **Note:** Both Cucumber Expressions and Regular Expressions **cannot** be used in the same method of a step definition file.

---

## 20. Regular Expression Quantifiers and Examples.

**Character class:** `([0-9])` → Matches digits between 0 and 9.

| Quantifier | Meaning | Example | Matches |
|------------|---------|---------|---------|
| `+` | One or more | `([0-9]+)` | `1`, `123`, `9999` |
| `*` | Zero or more | `([0-9]*)` | ` `, `1`, `123` |
| `?` | Zero or once | `([0-9]?)` | ` `, `5` |
| `{n}` | Exactly n times | `([0-9]{4})` | `0000`, `1234`, `9999` |

---

## 21. How do you access DataTable values in step definitions?

In Java, DataTable values are accessed using the `DataTable` class. You can retrieve the data as:
- A **list of lists** — rows and columns as strings.
- A **list of maps** — column names as keys, cell values as values.

---

## 22. What is the difference between a list of lists and a list of maps when accessing DataTable values?

| Type | Structure | Access |
|------|-----------|--------|
| **List of Lists** | Rows and columns as lists of strings. | By index: `row.get(0)` |
| **List of Maps** | Each row as a map with column names as keys. | By key: `row.get("Username")` |

---

## 23. How do you convert a DataTable into a list of lists in Cucumber?

```java
List<List<String>> data = dataTable.asLists();
```

---

## 24. When would you use DataTables in Cucumber scenarios?

DataTables are useful when you need to:
- Test scenarios with **multiple input combinations**.
- Provide **structured input data** directly within feature files.
- Implement **data-driven testing** or parameterization.

---

## 25. Provide an example of using DataTables for parameterization in Cucumber.

```gherkin
Scenario Outline: Login with different credentials
  Given I am on the login page
  When I enter the following username and password
    | Username   | Password |
    | <username> | <password> |
  And I click the login button
  Then I should be logged in successfully

  Examples:
    | username | password |
    | user1    | pass1    |
    | user2    | pass2    |
```

---

## 26. How do you handle dynamic DataTables with varying column lengths in Cucumber?

Use **Scenario Outlines with Examples tables**. Each row in the `Examples` table represents a different set of data, allowing you to handle different DataTable structures dynamically.

---

## 27. Are DataTables mutable in Cucumber?

No, DataTables are **immutable by default** in Cucumber. Once created, the data in a DataTable cannot be modified.

---

## 28. What is the BDD Approach?

BDD (Behavior-Driven Development) is an approach where **everyone involved** (developers, testers, and stakeholders) collaborates to describe the desired behavior of the software before any coding begins.

**Software Example:**

Imagine building a shopping cart system:
1. **Story time** — Everyone discusses a user story: *"As a customer, I want to add items to my cart so I can buy them later."*
2. **Examples speak louder** — Break the story into specific examples: *"If I add a shirt, the quantity should be 1. If I add another, it should be 2."*
3. **Shared language** — Use plain language focusing on what the user sees and does: *"When I click 'Add to Cart'... Then I should see '1 item in your cart'."*
4. **Building with a guide** — Developers use these examples as a guide and automate them as tests.

> **Key idea:** BDD focuses on the **behavior the system should have, from the user's perspective**. It promotes clear communication and ensures everyone is aligned before coding starts.

---

## 29. Is BDD a test automation framework?

No, **BDD is a process or approach**, not a specific test automation framework. It is a collaborative approach that emphasizes clear communication between developers, testers, and stakeholders.

However, frameworks like **Cucumber** support the BDD process by providing tools to write, manage, and automate tests based on behavior-driven principles using Gherkin syntax (`Given`, `When`, `Then`).

---

## 30. What is the purpose of the `features` attribute in `@CucumberOptions`?

The `features` attribute specifies the **location of the feature files** that contain the Gherkin syntax defining the behavior of the application.

```java
@CucumberOptions(features = "src/test/resources/features")
```

---

## 31. Explain the significance of the `tags` attribute in `@CucumberOptions`.

The `tags` attribute allows you to **include or exclude specific scenarios** during test execution based on the tags assigned in the feature files.

```java
@CucumberOptions(tags = "@smoke")         // Run only smoke scenarios
@CucumberOptions(tags = "not @skip")      // Exclude scenarios tagged @skip
```

---

## 32. What does the `glue` attribute represent in `@CucumberOptions`?

The `glue` attribute specifies the **package where step definitions are located**. Cucumber uses this to locate the step definitions corresponding to the steps defined in the feature files.

```java
@CucumberOptions(glue = "com.example.stepdefinitions")
```

---

## 33. Describe the purpose of the `plugin` attribute in `@CucumberOptions`.

The `plugin` attribute specifies the **types of reports or output formats** for test results.

```java
@CucumberOptions(
  plugin = {
    "pretty",                              // Console output
    "json:target/cucumber-reports.json",   // JSON report
    "junit:target/cucumber-results.xml",   // JUnit report
    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  // Extent report
  }
)
```

---

## 34. What does the `publish` attribute do in `@CucumberOptions`?

The `publish` attribute specifies whether to **publish generated reports** to the Cucumber reports server.

```java
@CucumberOptions(publish = true)
```

---

## 35. Explain the significance of the `monochrome` attribute in `@CucumberOptions`.

The `monochrome` attribute determines whether console output is displayed **with or without colors**. Setting `monochrome = true` removes colors, making the console output easier to read.

```java
@CucumberOptions(monochrome = true)
```

---

## 36. What is the purpose of the `strict` attribute in `@CucumberOptions`?

The `strict` attribute specifies whether to **treat undefined and pending steps as errors**. When `strict = true`, any undefined or pending steps will cause test execution to fail, ensuring all steps are properly implemented.

```java
@CucumberOptions(strict = true)
```

---

## 37. How can you rerun only the failed scenarios in Cucumber?

You can rerun failed scenarios using the **rerun plugin**, which generates a `.txt` file containing the paths of the failed scenarios. This file is then used as input for a subsequent test run.

---

## 38. Explain how the rerun plugin works in Cucumber.

**Steps to configure and use the rerun plugin:**

1. **Configure the rerun plugin** in your Cucumber runner to generate `rerun.txt` after execution:
```java
@CucumberOptions(
  plugin = {"rerun:target/rerun.txt"}
)
```

2. **Create a separate runner class** specifically for rerunning failed scenarios:
```java
@CucumberOptions(
  features = "@target/rerun.txt"  // Points to the rerun file using @
)
```

3. **Run the separate runner class** to execute only the failed scenarios listed in `rerun.txt`.

---

## 39. What are the ways to rerun failed scenarios in Cucumber?

When using **TestNG** for execution, there are 2 ways to rerun failed Cucumber scenarios:

| # | Method | Description |
|---|--------|-------------|
| 1 | **Rerun plugin** (Cucumber) | Generates a `rerun.txt` file with paths of failed scenarios. |
| 2 | **`testng-failed.xml`** (TestNG) | Uses TestNG's built-in failed test XML file for re-execution. |

---

## 40. How would you skip scenarios using tags in Cucumber?

Tag the scenarios you want to skip and use a tag expression to **exclude those tags** during execution.

```gherkin
@skip
Scenario: This scenario will be skipped
  Given ...
```

```java
@CucumberOptions(tags = "not @skip")
```

---

## 41. Besides tags, are there other ways to skip scenarios in Cucumber?

Yes, scenarios can also be skipped **programmatically within hooks** using conditional logic and Cucumber's `Scenario` object.

```java
@Before(value = "@skipScenario", order = 0)
public void skipScenarioExecution(Scenario scenario) {
    System.out.println("Skipped Scenario: " + scenario.getName());
    Assume.assumeTrue(false);  // Causes the scenario to be skipped
}
```

---

*Happy Testing! 🚀*
