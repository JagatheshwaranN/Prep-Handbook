# Rest Assured - Authentication Questions & Answers

---

## 1. What are the different types of authentication supported by Rest Assured?

Rest Assured supports various authentication mechanisms:

| # | Authentication Type | Description |
|---|---------------------|-------------|
| 1 | **Basic Authentication** | Insecure for production due to cleartext transmission. |
| 2 | **Digest Authentication** | A more secure alternative to Basic Authentication that uses a challenge-response mechanism. |
| 3 | **OAuth** | Employs tokens (access tokens, refresh tokens) to access resources, issued by an authorization server after successful authentication. |
| 4 | **API Key Authentication** | Involves using a unique identifier (API key) to access an API. |
| 5 | **JWT Authentication** | Employs digitally signed tokens containing user information, issued by an authorization server after successful authentication. |

---

## 2. Explain Basic Authentication and how it is implemented in Rest Assured.

Basic Authentication is a simple authentication scheme built into the HTTP protocol. It involves sending a **base64-encoded username and password** with each request in the `Authorization` header.

There are two approaches:

- **Challenge-based (default)**:  
  The client first sends a request without credentials. The server responds with `401 Unauthorized`, and then the client retries with credentials.

- **Preemptive Authentication**:  
  The client sends the credentials **in the first request itself**, without waiting for a server challenge.

In Rest Assured, both approaches are supported using:
- `.auth().basic(username, password)` → Challenge-based authentication
- `.auth().preemptive().basic(username, password)` → Preemptive authentication

---

## 3. How do you implement Basic Authentication in Rest Assured?

### Challenge-Based Basic Authentication (Default)

```java
RestAssured.given()
  .auth().basic("username", "password")
  .get("https://api.example.com/resource");
```

- Sends request **without credentials initially**
- Waits for `401 Unauthorized` response
- Retries with `Authorization` header
- Involves **two HTTP calls**

### Preemptive Basic Authentication

```java
RestAssured.given()
  .auth().preemptive().basic("username", "password")
  .get("https://api.example.com/resource");
```

- Sends credentials **in the first request**
- No `401` challenge required
- Only **one HTTP call**
- More efficient for API automation

## Key Differences

| Feature | basic() | preemptive().basic() |
|--------|--------|----------------------|
| Credentials sent | After 401 challenge | In first request |
| HTTP calls | 2 | 1 |
| Performance | Slower | Faster |
| Use case | Strict protocol flow | Optimized API testing |

## Summary

- **Basic Auth (default)** follows the standard challenge-response mechanism
- **Preemptive Basic Auth** skips the challenge and improves performance by sending credentials upfront

---

## 4. What is Digest Authentication and how does it differ from Basic Authentication?

Digest Authentication is an improved version of Basic Authentication that provides additional security by **hashing the password** before sending it over the network. It prevents the interception of plain-text passwords.

Rest Assured supports Digest Authentication through the `.auth().digest(username, password)` method.

| Feature | Basic Authentication | Digest Authentication |
|---------|---------------------|-----------------------|
| Password Transmission | Base64-encoded (cleartext) | Hashed (MD5) |
| Security | Less secure | More secure |
| Mechanism | Direct credentials | Challenge-response |

---

## 5. How do you implement Digest Authentication in Rest Assured?

```java
RestAssured.given()
  .auth().digest("username", "password")
  .get("https://api.example.com/resource");
```

---

## 6. Explain OAuth and its usage in Rest Assured.

OAuth (Open Authorization) is a protocol that allows **secure authorization** in a simple and standard method. It enables a third-party application to obtain limited access to an HTTP service on behalf of a resource owner.

Rest Assured can handle OAuth authentication by:
1. Obtaining access tokens through the OAuth flow.
2. Including those tokens in API requests.

---

## 7. How do you implement OAuth authentication in Rest Assured?

OAuth authentication involves obtaining an access token through the OAuth flow (e.g., OAuth 2.0 Authorization Code Grant) and including it in the request headers using the `.header()` method.

```java
String accessToken = "your-oauth-access-token";
RestAssured.given()
  .header("Authorization", "Bearer " + accessToken)
  .get("https://api.example.com/resource");
```

---

## 8. Explain API Key Authentication and its implementation in Rest Assured.

API Key Authentication involves sending a **unique API key** with each request to authenticate the client. It is commonly used in public APIs.

In Rest Assured, API Key Authentication can be implemented by including the API key in the request headers using the `.header()` method.

---

## 9. How do you implement API Key Authentication in Rest Assured?

```java
RestAssured.given()
  .header("X-API-KEY", "your-api-key")
  .get("https://api.example.com/resource");
```

---

## 10. What is JWT (JSON Web Token) Authentication and how do you handle it in Rest Assured?

JWT Authentication involves sending a **digitally signed token** containing user information with each request. Rest Assured can handle JWT Authentication by:
1. Extracting the token from the response.
2. Including it in subsequent requests using the `.header()` method.

---

## 11. How do you handle JWT Authentication in Rest Assured?

```java
String token = "your-JWT-token";
RestAssured.given()
  .header("Authorization", "Bearer " + token)
  .get("https://api.example.com/resource");
```

---

## 12. What's the difference between access tokens and refresh tokens in OAuth?

| Token Type | Lifespan | Purpose |
|------------|----------|---------|
| **Access Token** | Short-lived | Used to access protected resources. Expires after a certain period and must be retrieved again. |
| **Refresh Token** | Long-lived | Used to obtain new access tokens when the original access token expires, without requiring re-authentication. |

---

*Happy Testing! 🚀*
