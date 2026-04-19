# 1.1 Core Concepts

## What is an API?

An API (Application Programming Interface) is a contract between two software systems defining how they communicate. For interviews, always talk about:

- Request/Response cycle
- Stateless nature of REST
- Resource-based architecture

## HTTP Methods & Their Semantics

| Method  | Purpose                  | Idempotent | Safe | Body |
|---------|--------------------------|:----------:|:----:|:----:|
| GET     | Retrieve resource        | ✅         | ✅   | ❌   |
| POST    | Create resource          | ❌         | ❌   | ✅   |
| PUT     | Replace entire resource  | ✅         | ❌   | ✅   |
| PATCH   | Partial update           | ❌         | ❌   | ✅   |
| DELETE  | Remove resource          | ✅         | ❌   | ❌   |
| HEAD    | Like GET but no body     | ✅         | ✅   | ❌   |
| OPTIONS | Discover allowed methods | ✅         | ✅   | ❌   |

> ⚠️ **Interview Trap:** "Is PATCH idempotent?" — Answer: Not necessarily. PATCH can be idempotent but is not required to be, unlike PUT.

---

# 1.2 HTTP Status Codes (Deep Dive)

```
2xx - Success
  200 OK           - Standard success (GET, PUT, PATCH)
  201 Created      - Resource created (POST)
  202 Accepted     - Async processing accepted
  204 No Content   - Success, no body (DELETE)

3xx - Redirection
  301 Moved Permanently  - Permanent redirect
  302 Found              - Temporary redirect
  304 Not Modified       - Cache is still valid (ETag/Last-Modified)

4xx - Client Errors
  400 Bad Request        - Malformed syntax, invalid params
  401 Unauthorized       - Not authenticated (missing/invalid token)
  403 Forbidden          - Authenticated but no permission
  404 Not Found          - Resource doesn't exist
  405 Method Not Allowed - HTTP method not supported
  409 Conflict           - State conflict (duplicate, version mismatch)
  410 Gone               - Resource permanently deleted
  415 Unsupported Media  - Wrong Content-Type
  422 Unprocessable      - Semantically incorrect (validation failed)
  429 Too Many Requests  - Rate limit exceeded

5xx - Server Errors
  500 Internal Server Error  - Generic server failure
  502 Bad Gateway            - Upstream server failure
  503 Service Unavailable    - Server overloaded/maintenance
  504 Gateway Timeout        - Upstream timeout
```

> ⚠️ **Interview Trap:** "Difference between 401 and 403?" — 401 means *who are you?* (authentication). 403 means *I know who you are, but you can't do this* (authorization).

> ⚠️ **Interview Trap:** "Difference between 400 and 422?" — 400 is syntactically wrong (bad JSON). 422 is syntactically correct but semantically invalid (missing required field with valid JSON).

---

# 1.3 REST Principles (Richardson Maturity Model)

```
Level 0 - Single URI, one method (SOAP-like)
Level 1 - Multiple URIs, resources
Level 2 - HTTP Verbs + Status Codes properly used ← Most APIs
Level 3 - HATEOAS (Hypermedia links in responses)
```

---

# 1.4 API Architectural Styles

| Style     | Protocol  | Format      | Use Case          |
|-----------|-----------|-------------|-------------------|
| REST      | HTTP      | JSON/XML    | Web/Mobile APIs   |
| SOAP      | HTTP/SMTP | XML only    | Enterprise, Banking |
| GraphQL   | HTTP      | JSON        | Flexible querying |
| gRPC      | HTTP/2    | Protobuf    | Microservices     |
| WebSocket | WS        | Any         | Real-time         |
| Webhook   | HTTP      | JSON        | Event-driven      |
