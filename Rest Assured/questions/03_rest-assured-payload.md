# Rest Assured - Request & Response Payload Questions & Answers

---

## 1. What is a request payload in Rest Assured testing?

A request payload (also known as a **request body**) is the data sent with an HTTP request, typically in methods like `POST`, `PUT`, or `PATCH`. It contains information needed by the server to process the request.

> Data sent by the client (your test) to the server along with the request. It typically contains information needed to perform the desired action (e.g., creating a new resource, updating existing data).

---

## 2. How can you set a request payload in Rest Assured?

In Rest Assured, you can set a request payload using the `.body()` method, passing the payload data as a **string** or an **object**.

---

## 3. Give an example of setting a JSON request payload in Rest Assured.

**Approach 1 — Raw JSON String:**
```java
RestAssured.given()
  .contentType(ContentType.JSON)
  .body("{ \"name\": \"John\", \"age\": 30 }")
  .post("https://api.example.com/resource");
```

**Approach 2 — POJO (Plain Old Java Object):**

Create a Java class representing the JSON structure and use `body()` with an instance of that class.

```java
public class User {
  private String name;
  private String email;
  // Getters and setters
}

User newUser = new User("John Doe", "john.doe@example.com");

RestAssured.given()
  .contentType(ContentType.JSON)
  .body(newUser)
  .post("https://api.example.com/user");
```

---

## 4. What is a response payload in Rest Assured testing?

A response payload (also known as a **response body**) is the data returned by the server in response to an HTTP request. It typically contains the requested information or the result of the requested operation.

> Data sent back by the server in response to the client's request. It contains the requested information or a success/error message depending on the outcome of the request.

---

## 5. How can you retrieve and validate a response payload in Rest Assured?

There are multiple approaches to retrieve and validate a response payload:

1. **`getBody()` method** — Retrieve the raw response body and validate using assertions.
2. **JSON Path** — Use `jsonPath()` to navigate through the JSON structure and assert specific values:
```java
.then()
  .body("name", equalTo("John Doe"))
  .body("email", equalTo("john.doe@example.com"));
```
3. **XML Path** — Similar to JSON Path, use `xmlPath()` to navigate XML structures.
4. **Groovy Expressions** — For complex assertions, use Groovy expressions within `body()`.

---

## 6. Give an example of retrieving and validating a JSON response payload in Rest Assured.

```java
Response response = RestAssured.get("https://api.example.com/resource");
String responseBody = response.getBody().asString();
Assert.assertTrue(responseBody.contains("expectedValue"));
```

---

## 7. What are the common content types for request and response payloads in Rest Assured?

| Content Type | MIME Type |
|--------------|-----------|
| **JSON** | `application/json` |
| **XML** | `application/xml` |
| **Form Data** | `application/x-www-form-urlencoded` |
| **Binary Data** | `application/octet-stream` |

---

## 8. How do you set the content type of a request payload in Rest Assured?

You can set the content type of a request payload using the `.contentType()` method, passing the appropriate content type as an argument.

---

## 9. Give an example of setting the content type of a request payload to JSON in Rest Assured.

```java
RestAssured.given()
  .contentType(ContentType.JSON)
  .body("{ \"name\": \"John\", \"age\": 30 }")
  .post("https://api.example.com/resource");
```

---

## 10. How do you validate the content type of a response payload in Rest Assured?

You can retrieve the content type of a response payload using the `.getContentType()` method and then validate it using assertions.

---

## 11. Give an example of validating the content type of a response payload in Rest Assured.

```java
Response response = RestAssured.get("https://api.example.com/resource");
String contentType = response.getContentType();
Assert.assertEquals(contentType, "application/json");
```

---

## 12. How do you handle binary response payloads in Rest Assured?

Rest Assured provides dedicated methods to handle binary response payloads:

| Method | Description |
|--------|-------------|
| `.asByteArray()` | Retrieves the response as a **byte array**. |
| `.asInputStream()` | Retrieves the response as an **input stream**. |

---

## 13. How do you handle empty responses or responses without a body?

Some requests may not return a response body (e.g., `DELETE` requests). Use `.then().statusCode(204)` to assert that an empty response is received as expected.

```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .delete("/users/123")
  .then()
    .statusCode(204);  // 204 No Content - confirms empty response
```

---

*Happy Testing! 🚀*
