# Rest Assured - Proxy Testing Questions & Answers

---

## 1. What is Proxy Testing in Rest Assured API Testing?

Proxy testing in Rest Assured involves configuring API requests to be routed through a **proxy server** for debugging, monitoring, or security purposes.

---

## 2. What is Proxy Testing in the context of Rest Assured?

Proxy testing allows Rest Assured to send requests through a proxy server. This helps to:

- Monitor API traffic (request and response data).
- Debug network issues like timeouts or connection failures.
- Simulate network conditions such as delays or request modifications.

---

## 3. How do you configure proxy settings in Rest Assured?

You can configure proxy settings in Rest Assured using the `.proxy()` method.

---

## 4. Explain the parameters of the `.proxy()` method in Rest Assured.

The `.proxy()` method accepts:

- **Proxy host** – The proxy server address.
- **Proxy port** – The port number.
- **Username (optional)** – For authentication.
- **Password (optional)** – For authentication.

---

## 5. How do you configure Rest Assured to use a proxy server without authentication?

```java
RestAssured.proxy("proxy.example.com", 8080);
```

---

## 6. How do you configure Rest Assured to use a proxy server with authentication?

```java
RestAssured.proxy("proxy.example.com", 8080, "username", "password");
```

---

## 7. How do you configure RestAssured to use a proxy server?

There are two main approaches:

### 1. Static Proxy

Specify the proxy host and port directly:

```java
RestAssured.proxy("localhost", 8080);
```

### 2. ProxySpecification

Create a flexible proxy configuration with additional details:

```java
ProxySpecification proxySpec = new ProxySpecification("localhost", 8080, "http")
  .username("proxy_user")
  .password("proxy_password");

RestAssured.given()
  .proxy(proxySpec);
```

---

## 8. What precautions should be taken when using proxy servers in API testing?

When using proxy servers, ensure:

- Sensitive data (tokens, credentials, PII) is not exposed or intercepted.
- Secure communication (HTTPS) is enforced.
- Proper authentication and authorization are configured.
- Proxy access is restricted to prevent unauthorized usage.

---

## Proxy Testing — Summary

| Feature | Details |
|--------|--------|
| **Purpose** | Route API requests through a proxy server |
| **Usage** | Debugging, monitoring, and security testing |
| **Configuration Method** | `.proxy()` |
| **Authentication Support** | Yes (username & password) |
| **Advanced Setup** | `ProxySpecification` |

---

*Happy Testing! 🚀*