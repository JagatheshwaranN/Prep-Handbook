# Rest Assured - HTTP Status Codes Questions & Answers

---

## 1. What are the common HTTP error codes encountered in REST API testing?

Common HTTP error codes encountered in REST API testing include:

- **400 Bad Request**
- **401 Unauthorized**
- **404 Not Found**
- **500 Internal Server Error**
- **503 Service Unavailable**

Each error code signifies a different type of problem encountered during the API request-response cycle.

---

## 2. What strategies do you employ to troubleshoot and debug errors related to HTTP status codes in REST Assured tests?

To troubleshoot and debug errors related to HTTP status codes in Rest Assured tests, the following strategies are commonly used:

- Review the test code to ensure the correct endpoint is being tested.
- Inspect API documentation to verify expected behavior and status codes.
- Log request and response details for analysis.
- Validate request payload, headers, and authentication details.
- Check environment configurations (base URI, ports, etc.).

---

## 3. What are the common 4xx Client Error codes seen in testing?

| Status Code | Name | Description |
|------------|------|-------------|
| 400 | Bad Request | The server cannot process the request due to client errors such as invalid syntax or missing parameters. |
| 401 | Unauthorized | Authentication is required; valid credentials must be provided. |
| 403 | Forbidden | The request is understood but access is denied due to insufficient permissions. |
| 404 | Not Found | The requested resource is not available or does not exist. |

---

## 4. What are the common 5xx Server Error codes seen in testing?

| Status Code | Name | Description |
|------------|------|-------------|
| 500 | Internal Server Error | A generic server-side error where the exact cause is unknown. |
| 502 | Bad Gateway | Invalid response received from an upstream server. |
| 503 | Service Unavailable | Server is temporarily unavailable due to overload or maintenance. |
| 504 | Gateway Timeout | No timely response received from the upstream server. |

---

## 5. What are the common 2xx Success codes seen in testing?

| Status Code | Name | Description |
|------------|------|-------------|
| 200 | OK | The request was successful. |
| 201 | Created | A new resource has been successfully created. |
| 202 | Accepted | The request has been accepted but not yet processed. |

---

## 6. What are the common 3xx Redirection codes seen in testing?

| Status Code | Name | Description |
|------------|------|-------------|
| 301 | Moved Permanently | The resource has been permanently moved to a new URL. |
| 302 | Found | The resource is temporarily available at a different URL. |
| 304 | Not Modified | The resource has not changed since the last request. |

---

## 7. What are the common 1xx Informational codes seen in testing?

These codes indicate that the request is being processed and further action may be required. They are typically not visible in standard API testing scenarios or browsers.

---

## HTTP Status Codes — Summary

| Category | Description | Common Codes |
|----------|-------------|--------------|
| **1xx** | Informational responses | 100 Continue |
| **2xx** | Successful responses | 200, 201, 202 |
| **3xx** | Redirection messages | 301, 302, 304 |
| **4xx** | Client errors | 400, 401, 403, 404 |
| **5xx** | Server errors | 500, 502, 503, 504 |

---

*Happy Testing! 🚀*