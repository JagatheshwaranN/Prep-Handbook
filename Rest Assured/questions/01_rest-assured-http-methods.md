# Rest Assured - Interview Questions & Answers

---

## 1. What is Rest Assured?

Rest Assured is a Java library that provides a **Domain Specific Language (DSL)** for writing powerful, maintainable tests for RESTful APIs.

---

## 2. Explain HTTP methods commonly used in RESTful APIs and their corresponding actions.

| Method | Action |
|--------|--------|
| **GET** | Retrieves data from a resource (e.g., fetching a list of users). |
| **POST** | Creates a new resource on the server (e.g., adding a new user). |
| **PUT** | Updates a complete resource on the server (e.g., modifying an existing user's details). |
| **PATCH** | Updates a partial resource on the server (e.g., changing a user's password). |
| **DELETE** | Deletes a resource from the server (e.g., removing a user). |
| **HEAD** | Retrieves the header information of a resource without transferring the actual body. |

---

## 3. How do you perform a POST request with Rest Assured?

**Approach 1:**
```java
Response response = RestAssured.given().contentType(ContentType.JSON)
                    .body("{ \"name\": \"John\", \"age\": 30 }")
                    .post("https://api.example.com/resource");
System.out.println("Response Body: " + response.getBody().asString());
```

**Approach 2:**
```java
String userPayload = "{\"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\"}";
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .post("/users")
    .body(userPayload)  // Provide data for the new resource in JSON format
  .then()
    .statusCode(201);  // Assert successful creation (usually 201 Created)
```

---

## 4. How can you perform a GET request using Rest Assured?

**Approach 1:**
```java
Response response = RestAssured.get("https://api.example.com/resource");
System.out.println("Response Body: " + response.getBody().asString());
```

**Approach 2:**
```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .get("/users/123")
  .then()
    .statusCode(200);
```

---

## 5. How do you perform a PUT request with Rest Assured?

**Approach 1:**
```java
String requestBody = "{ \"name\": \"Updated Name\", \"age\": 35 }";
Response response = RestAssured.given().contentType(ContentType.JSON)
                    .body(requestBody)
                    .put("https://api.example.com/resource/123");
System.out.println("Response Body: " + response.getBody().asString());
```

**Approach 2:**
```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .put("/users/123")  // Replace 123 with the user ID to update
    .body("{\"name\": \"John Doe Updated\"}")  // Update data in JSON format
  .then()
    .statusCode(200);  // Assert successful update (code may vary)
```

---

## 6. How do you perform a PATCH request with Rest Assured?

**Approach 1:**
```java
String requestBody = "{ \"age\": 40 }";
Response response = RestAssured.given().contentType(ContentType.JSON)
                    .body(requestBody)
                    .patch("https://api.example.com/resource/123");
System.out.println("Response Body: " + response.getBody().asString());
```

**Approach 2:**
```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .patch("/users/123")  // Replace 123 with the user ID to update
    .body("{\"password\": \"newPassword\"}")  // Update partial data (password)
  .then()
    .statusCode(200);  // Assert successful update (code may vary)
```

---

## 7. How do you perform a DELETE request with Rest Assured?

**Approach 1:**
```java
Response response = RestAssured.delete("https://api.example.com/resource/123");
System.out.println("Response Body: " + response.getBody().asString());
```

**Approach 2:**
```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .delete("/users/123")  // Replace 123 with the user ID to delete
  .then()
    .statusCode(204);  // Assert successful deletion (usually 204 No Content)
```

---

## 8. How do you perform a HEAD request with Rest Assured?

```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .head("/users/123")  // Replace 123 with the user ID
  .then()
    .statusCode(200);  // Assert successful retrieval of headers (code may vary)
```

---

## 9. How do you validate the response status code using Rest Assured?

**Approach 1:**
```java
Response response = RestAssured.get("https://api.example.com/resource");
int statusCode = response.getStatusCode();
Assert.assertEquals(statusCode, 200);
```

**Approach 2:**
```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .get("/users")
  .then()
    .statusCode(200);
```

---

## 10. How can you validate the response body using Rest Assured?

**Approach 1:**
```java
Response response = RestAssured.get("https://api.example.com/resource");
String responseBody = response.getBody().asString();
Assert.assertTrue(responseBody.contains("expectedValue"));
```

**Approach 2:**
```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .get("/users")
  .then()
    .body("name", equalTo("John Doe"));  // Assert JSON body value
```

---

## 11. Explain the difference between PUT and POST methods.

| Method | Description |
|--------|-------------|
| **PUT** | Typically used to **update** an existing resource. It replaces the existing resource with the new one sent in the request. |
| **POST** | Used to **create** a new resource. It sends data to the server to create a new entity. |

---

## 12. How can you validate the response headers using Rest Assured?

**Approach 1:**
```java
Response response = RestAssured.get("https://api.example.com/resource");
String contentTypeHeader = response.getHeader("Content-Type");
Assert.assertEquals(contentTypeHeader, "application/json");
```

**Approach 2:**
```java
RestAssured.given()
  .baseUri("https://api.example.com")
  .when()
    .get("/users")
  .then()
    .header("Location", "https://api.example.com/users/123");  // Assert header value
```

---

## 13. What are the advantages of using Rest Assured for API testing?

1. Provides a simple and intuitive syntax for writing tests.
2. Integrates seamlessly with popular Java testing frameworks like **JUnit** and **TestNG**.
3. Supports easy validation of response data, status codes, headers, etc.
4. Allows for easy handling of **authentication and authorization** in API requests.

---

## 14. How do you add parameters, headers, and a request body to a Rest Assured request?

### Parameters

Use `queryParam()` or `formParam()` methods depending on the parameter location (query string or form data).

```java
RestAssured.given()
  ...
  .queryParam("name", "John")  // Add query parameter
  ...
```

### Headers

Use the `header()` method to set custom headers.

```java
RestAssured.given()
  ...
  .header("Content-Type", "application/json")  // Set header
  ...
```

### Request Body

Use the `body()` method with appropriate data format (e.g., JSON string) for POST, PUT, and PATCH requests.

```java
String userPayload = "{\"name\": \"John Doe\"}";
RestAssured.given()
  ...
  .body(userPayload)  // Set request body
  ...
  .post("/users");
```

---

*Happy Testing! 🚀*
