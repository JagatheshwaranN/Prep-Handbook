# Rest Assured - API Concepts Questions & Answers

---

## 1. Difference between API and Web Service

| Feature | API | Web Service |
|--------|-----|-------------|
| Definition | Set of rules for communication between software components | A type of API accessible over the web |
| Protocol | Can use multiple protocols | Uses web protocols (HTTP, SMTP, etc.) |
| Scope | Broader concept | Subset of APIs |

---

## 2. Difference between SOAP and REST API

| Feature | SOAP | REST |
|--------|------|------|
| Type | Protocol | Architectural style |
| Format | XML only | JSON, XML, etc. |
| Performance | Heavy | Lightweight |
| Flexibility | Rigid | Flexible |

## Protocol (SOAP) → Strict rulebook

A protocol is a fixed set of rules that everyone must follow exactly to communicate.

1. It tells you how to send, what format to use, how errors look, everything.
2. Very structured and strict.
3. Example: Like a formal postal service
   → You must write the address in a specific format, use proper stamps, follow exact steps.

👉 SOAP is a protocol because:

1. It has predefined standards (XML format, strict messaging rules)
2. You can’t freely change how communication works

## Architectural Style (REST) → Flexible guideline

An architectural style is more like a set of best practices or design guidelines, not strict rules.

1. It tells you how things should be designed, but gives flexibility.
2. You can choose how to implement details.
3. Example: Like ordering food at a restaurant
→ There’s a general flow (menu → order → get food), but different restaurants do it differently.

👉 REST is an architectural style because:

1. It uses standard web methods (GET, POST, etc.)
2. But no strict format is enforced (can use JSON, XML, etc.)
3. Developers have freedom in implementation
---

## 3. Sample API and JSON

**API URL:**  
`https://api.example.com/users`

**JSON Sample:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com"
}
```

---

## 4. Handling Authentication Token

```java
given()
  .header("Authorization", "Bearer <token>")
  .get("/endpoint");
```

---

## 5. Types of Authentication

| Type |
|------|
| Basic Authentication |
| Digest Authentication |
| OAuth 1.0 |
| OAuth 2.0 |
| API Key |
| Bearer Token |

---

## 6. Difference between OAuth1.0 and OAuth2.0

| Feature | OAuth 1.0 | OAuth 2.0 |
|--------|-----------|-----------|
| Security | Signature-based | Token-based |
| Complexity | Complex | Simpler |
| Usage | Legacy | Widely used |

```java
given()
  .auth().oauth2("access_token")
  .get("/endpoint");
```

---

## 7. BaseURI in Rest Assured

Base URI is a static variable used to define the base URL for all API requests, reducing repetition.

---

## 8. RequestSpecification Explanation

`RestAssured.given()` returns a `RequestSpecification` used to define request details like headers, params, and authentication.

---

## 9. Return Type of jsonPath().getJsonObject()

Returns a JSON object corresponding to the specified key.

---

## 10. Extracting and Validating JSON

- Use JSONPath or POJO mapping
- Validate using assertions (status, headers, body)

---

## 11. Save Response to JSON File

```java
Response response = given().get("/endpoint");
String body = response.getBody().asString();

try (FileWriter file = new FileWriter("response.json")) {
    file.write(body);
}
```

---

## 12. Validating Headers

```java
response.then().header("Content-Type", "application/json");
```

---

## 13. Headers vs Header

| Feature | Headers | Header |
|--------|---------|--------|
| Meaning | Collection | Single header |

---

## 14. response.header() vs response.headers()

| Method | Description |
|--------|-------------|
| `header("xyz")` | Returns specific header |
| `headers()` | Returns all headers |

---

## 15. Extract All Headers

```java
Headers headers = response.getHeaders();
```

---

## 16. Common Methods Explanation

| Method | Description |
|--------|-------------|
| JSONObject() | Create JSON object |
| request.header() | Get request header |
| response.path() | Extract JSON value |
| asString() | Convert response to string |
| prettyPrint() | Format response |
| queryParam() | Add query param |

---

## 17. request.get() vs request.request()

| Method | Description |
|--------|-------------|
| `get(url)` | Direct GET call |
| `request(Method.GET, path)` | Uses base URI |

---

## 18. PUT vs PATCH

| Feature | PUT | PATCH |
|--------|-----|-------|
| Update | Full | Partial |

---

## 19. Status Code Categories

| Category | Meaning |
|----------|--------|
| 2xx | Success |
| 3xx | Redirection |
| 4xx | Client Error |
| 5xx | Server Error |

---

## 20. Print Response

```java
response.getBody().prettyPrint();
```

---

## 21. POST Body Handling

- Use `.body()` or `.with()`
- Supports JSON, XML, form data

---

## 22. REST Resource Definition

A resource is any data (JSON, HTML, images, etc.) accessible via a unique URI.

---

## 23. HTTP Response Elements

| Component | Description |
|----------|-------------|
| Method | GET, POST, etc. |
| URI | Resource identifier |
| Version | HTTP version |
| Headers | Metadata |
| Body | Actual data |

---

## 24. Caching Techniques

| Type | Description |
|------|-------------|
| Browser Cache | Stored in browser |
| Proxy Cache | Stored in proxy |
| CDN Cache | Stored geographically |
| App Cache | Stored in app |

---

## 25. Securing Sensitive Data

```java
given()
  .auth().basic("user", "pass")
.when()
  .post("/login")
.then()
  .blacklistHeader("Authorization");
```

---

## 26. Method Chaining

- Improves readability
- Enables fluent interface
- Makes tests maintainable

---

## 27. Extracting Data from Response

```java
String res = get("/users").then().extract().asString();
int id = get("/user/1").then().extract().path("id");
```

---

## 28. Filters in REST Assured

- Modify requests/responses
- Add logging or authentication
- Apply custom logic

---

## 29. JSONPath

JSONPath is used to extract values from JSON responses using expressions.

---

## 30. Handling SSL/TLS

```java
given().relaxedHTTPSValidation();
```

---

## 31. Handling Dynamic Responses

- Use matchers (`any()`, `matches()`)
- Ignore fields
- Validate patterns

---

## 32. Advanced Error Handling

- Custom messages
- Filters for failures
- Logging

---

## 33. Protocol Used by REST

HTTP is used for RESTful communication.

---

## 34. Maximum Payload Size

No fixed limit; depends on server and configuration.

---

## 35. What is REST?

REST is an architectural style for building scalable APIs using HTTP methods like GET, POST, PUT, DELETE.

---

## 36. API Response Validation Techniques

| Type | Method |
|------|--------|
| Status | `statusCode()` |
| Headers | `header()` |
| Body | `body()` |
| Time | `time()` |
| Cookies | `cookie()` |
| Schema | JSON/XML validators |

---

*Happy Testing! 🚀*