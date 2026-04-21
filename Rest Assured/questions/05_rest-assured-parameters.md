# Rest Assured - Parameters Questions & Answers

---

## 1. What are parameters in API requests?

Parameters are **values passed with an API request** to modify the behavior of the request or to provide additional data to the server.

---

## 2. What are the different types of parameters commonly used in API requests?

| # | Parameter Type |
|---|----------------|
| 1 | **Query Parameters** |
| 2 | **Path Parameters** |
| 3 | **Header Parameters** |
| 4 | **Form Parameters** (or Request Body Parameters) |
| 5 | **Cookie Parameters** |
| 6 | **MultiPart Parameters** |

---

## 3. Explain Query Parameters in API requests.

Query parameters are **key-value pairs appended to the URL** of an API request. They are:
- Separated from the URL by a question mark (`?`)
- Separated from each other by an ampersand (`&`)

They are commonly used to **filter, sort, or paginate** results.

**Example URL:**
```
https://api.example.com/users?limit=10&sort=name
```

---

## 4. How do you include Query Parameters in Rest Assured requests?

You can include query parameters using the `.queryParam()` method.

```java
RestAssured.given()
  .queryParam("key", "value")
  .get("https://api.example.com/resource");
```

```java
RestAssured.given()
  .baseUri("https://api.example.com/users")
  .queryParam("limit", 10)   // Limit results to 10
  .queryParam("sort", "name") // Sort by name
  .get();
```

---

## 5. Explain Path Parameters in API requests.

Path parameters are **placeholders in the URL path** of an API request. They are used to specify variable parts of the URL, such as IDs or names, which are replaced with actual values when making the request.

**Example URL:**
```
https://api.example.com/users/{userId}
```

---

## 6. How do you include Path Parameters in Rest Assured requests?

You can include path parameters using the `.pathParam()` method.

```java
RestAssured.given()
  .pathParam("id", "123")
  .get("https://api.example.com/resource/{id}");
```

```java
RestAssured.given()
  .baseUri("https://api.example.com/users/{userId}")
  .pathParam("userId", 123)  // Replace with specific user ID
  .get();
```

---

## 7. What are Header Parameters in API requests?

Header parameters are **key-value pairs included in the request headers**. They provide additional information to the server, such as:
- Authentication credentials
- Content type
- Custom metadata

---

## 8. How do you include Header Parameters in Rest Assured requests?

You can include header parameters using the `.header()` method.

```java
RestAssured.given()
  .header("Authorization", "Bearer token")
  .get("https://api.example.com/resource");
```

---

## 9. Explain Form Parameters (or Request Body Parameters) in API requests.

Form parameters (also known as **request body parameters**) are data sent in the **body of an HTTP request**, typically in `POST` or `PUT` requests. They are used to send complex data structures or larger amounts of data.

---

## 10. How do you include Form Parameters in Rest Assured requests?

You can include form parameters using the `.formParam()` method.

```java
RestAssured.given()
  .formParam("username", "john")
  .formParam("password", "secret")
  .post("https://api.example.com/login");
```

---

## 11. What are Cookie Parameters in API requests?

Cookie parameters are **key-value pairs stored in the client's browser** and sent with each request to the server. They are used for:
- Maintaining session state
- Passing user-specific information

---

## 12. How do you include Cookie Parameters in Rest Assured requests?

You can include cookie parameters using the `.cookie()` method.

```java
RestAssured.given()
  .cookie("session_id", "abc123")
  .get("https://api.example.com/resource");
```

---

## 13. What are MultiPart Parameters in API requests?

The `.multiPart()` method in Rest Assured is used to include **multipart/form-data** parameters in API requests. This is particularly useful when:
- Uploading files
- Sending binary data along with other form fields in an HTTP request

---

## 14. How do you include MultiPart Parameters in Rest Assured requests?

You can include multipart parameters using the `.multiPart()` method.

```java
RestAssured.given()
  .multiPart("file", new File("/path/to/file.txt"))  // Upload a file
  .multiPart("field1", "value1")                     // Additional form field
  .multiPart("field2", "value2")                     // Additional form field
  .post("https://api.example.com/upload");
```

---

## 15. What are the main types of parameters used in RESTful APIs?

| Parameter Type | Description | Example |
|----------------|-------------|---------|
| **Query Parameters** | Appended to the URL after `?`, consisting of key-value pairs separated by `&`. Used to filter, sort, or paginate data. | `?limit=10&sort=name` |
| **Path Parameters** | Embedded within the URL path itself, enclosed in curly braces `{}`. Represent specific resource identifiers. | `/users/{userId}` |

---

## 16. When would you use Query Parameters vs. Path Parameters?

| Parameter Type | Best Used For |
|----------------|---------------|
| **Query Parameters** | Filtering, sorting, pagination, or any options that can vary across requests. |
| **Path Parameters** | Identifying specific resources within the API structure (e.g., user ID, product ID). |

---

*Happy Testing! 🚀*
