# Rest Assured - RequestSpecification & ResponseSpecification Questions & Answers

---

## 1. What is a RequestSpecification in Rest Assured API testing?

A `RequestSpecification` is an interface in Rest Assured that allows you to specify the details of an HTTP request, such as:
- Base URI
- Query parameters
- Headers
- Cookies
- Request body

It acts as a **builder for creating HTTP requests** and allows you to define common request properties that can be **reused across multiple test cases**, helping avoid code duplication and improving test organization.

---

## 2. What are the common methods available in RequestSpecification?

| Method | Purpose |
|--------|---------|
| `baseUri()` | Sets the base URI for the request. |
| `basePath()` | Sets the base path appended to the base URI. |
| `queryParam()` | Adds query parameters to the request URL. |
| `header()` | Adds custom headers to the request. |
| `cookie()` | Adds cookies to the request. |
| `body()` | Sets the request payload/body. |
| `auth()` | Configures authentication for the request. |

---

## 3. Why is RequestSpecification useful in Rest Assured testing?

`RequestSpecification` allows you to **define common request attributes and reuse them** across multiple API requests. Key benefits include:
- Maintaining consistency across tests.
- Reducing redundancy and code duplication.
- Improving code readability and maintainability.

---

## 4. How do you create a RequestSpecification in Rest Assured?

You can create a `RequestSpecification` using the `given()` method provided by Rest Assured.

```java
RequestSpecification requestSpec = RestAssured.given();
```

---

## 5. Explain how to set base URI and base path using RequestSpecification.

You can set the base URI and base path using the `baseUri()` and `basePath()` methods respectively.

```java
RequestSpecification requestSpec = RestAssured.given()
  .baseUri("https://api.example.com")
  .basePath("/v1");
```

---

## 6. What is a ResponseSpecification in Rest Assured API testing?

A `ResponseSpecification` is an interface in Rest Assured that allows you to specify the **expected details of an HTTP response**, such as:
- Status code
- Headers
- Cookies
- Response body

It acts as a **builder for validating HTTP responses** and can be reused across multiple test cases.

---

## 7. What are the common methods available in ResponseSpecification?

| Method | Purpose |
|--------|---------|
| `statusCode()` | Validates the HTTP response status code. |
| `header()` | Validates a specific response header value. |
| `cookie()` | Validates a specific cookie in the response. |
| `body()` | Validates the response body content. |
| `time()` | Validates the response time. |
| `statusLine()` | Validates the full HTTP status line. |

---

## 8. Why is ResponseSpecification useful in Rest Assured testing?

`ResponseSpecification` allows you to **define common expectations for response attributes and reuse them** across multiple API responses. Key benefits include:
- Maintaining consistency in response validation.
- Reducing redundancy and code duplication.
- Improving code readability and maintainability.

---

## 9. How do you create a ResponseSpecification in Rest Assured?

You can create a `ResponseSpecification` using the `expect()` method provided by Rest Assured.

```java
ResponseSpecification responseSpec = RestAssured.expect();
```

---

## 10. Explain how to validate status code and response body using ResponseSpecification.

You can validate the status code and response body using the `statusCode()` and `body()` methods respectively.

```java
ResponseSpecification responseSpec = RestAssured.expect()
  .statusCode(200)
  .body("key", equalTo("value"));
```

---

## 11. How do you apply RequestSpecification to a Rest Assured request?

You can apply a `RequestSpecification` to a Rest Assured request using the `.spec()` method. This allows the same spec to be reused across multiple test cases.

```java
// Define the RequestSpecification once
RequestSpecification requestSpec = RestAssured.given()
  .baseUri("https://api.example.com")
  .basePath("/v1")
  .header("Content-Type", "application/json");

// Reuse the requestSpec in multiple test cases
RestAssured.given()
  .spec(requestSpec)
  .pathParam("userId", 123)
  .when()
    .get("/users/{userId}")
  .then()
    ...;
```

---

## 12. How do you apply ResponseSpecification to a Rest Assured request?

You can apply a `ResponseSpecification` to a Rest Assured request using the `.spec()` method in the `.then()` block.

```java
// Define the ResponseSpecification once
ResponseSpecification responseSpec = RestAssured.expect()
  .statusCode(200)
  .contentType("application/json")
  .body("name", equalTo("John Doe"));

// Reuse the responseSpec in multiple test cases
RestAssured.given()
  ...
  .when()
    .get("/users/123")
  .then()
    .spec(responseSpec);
```

---

## RequestSpecification vs ResponseSpecification — Summary

| Feature | RequestSpecification | ResponseSpecification |
|---------|---------------------|-----------------------|
| **Purpose** | Defines request properties | Defines expected response properties |
| **Created with** | `RestAssured.given()` | `RestAssured.expect()` |
| **Applied with** | `.spec()` in `given()` block | `.spec()` in `then()` block |
| **Common methods** | `baseUri()`, `header()`, `body()`, `auth()` | `statusCode()`, `body()`, `header()`, `time()` |

---

*Happy Testing! 🚀*
