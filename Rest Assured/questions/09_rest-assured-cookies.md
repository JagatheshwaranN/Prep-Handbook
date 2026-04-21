# Rest Assured - Cookies Questions & Answers

---

## 1. What are cookies in the context of web applications?

Cookies are small pieces of data that websites store on a user's device. They are typically used to:

- Remember user preferences
- Track user behavior
- Maintain user sessions across multiple requests

---

## 2. How can you handle cookies in REST Assured tests?

In Rest Assured, cookies can be handled using:

- `.cookie()` – to set cookies in the request
- `.cookies()` – to extract cookies from the response

You can also validate cookies using assertions.

---

## 3. Explain the importance of testing cookies in REST Assured API testing.

Testing cookies ensures that:

- Cookies are correctly set in the response
- Attributes like expiration date and domain are valid
- Session management works correctly
- Authentication mechanisms relying on cookies function properly

---

## 4. How do you verify the presence of a specific cookie in a REST Assured test?

You can verify a cookie using assertions:

```java
then().assertThat().cookie("cookieName");
```

This checks whether the specified cookie is present in the response.

---

## 5. Can you demonstrate how to set a cookie in a REST Assured request?

```java
given()
  .cookie("sessionId", "12345");
```

This sets a cookie named `sessionId` with value `12345`.

---

## 6. What are some common scenarios where testing cookies in REST Assured is necessary?

| Scenario | Description |
|----------|-------------|
| Authentication | Verify cookies are set after login |
| Session Management | Ensure session cookies persist across requests |
| Logout Handling | Validate cookies are expired or deleted after logout |

---

## 7. How would you use Rest Assured to extract a cookie value and validate it in a subsequent request?

Steps involved:

1. Extract the cookie:
   ```java
   String cookieValue = response.getCookie("cookieName");
   ```

2. Add cookie to next request:
   ```java
   given().cookie("cookieName", cookieValue);
   ```

3. Validate cookie presence:
   ```java
   then().assertThat().cookie("cookieName");
   ```

4. Validate cookie value:
   ```java
   then().assertThat().cookie("cookieName", equalTo("expectedValue"));
   ```

---

## 8. How would you configure RestAssured to ignore specific cookies during testing?

You can configure Rest Assured to relax cookie validation:

```java
given()
  .baseUri("https://api.example.com")
  .relaxedHTTPSValidation()
  // additional configuration
```

(Custom cookie filtering typically requires manual handling or response manipulation.)

---

## 9. What are the different types of Cookies available?

| Type | Description |
|------|-------------|
| Session Cookie | Maintains user session; deleted when browser closes |
| Persistent Cookie | Stored with expiry and persists after session ends |
| Secure Cookie | Sent only over HTTPS connections |
| Http-Only Cookie | Cannot be accessed via JavaScript; improves security |
| SameSite Cookie | Restricted to same domain to prevent cross-site attacks |

---

## Cookies — Summary

| Feature | Description |
|--------|-------------|
| Purpose | Store user session and state |
| Common Usage | Authentication, tracking, personalization |
| REST Assured Methods | `.cookie()`, `.cookies()` |
| Validation | `then().assertThat().cookie()` |

---

*Happy Testing! 🚀*