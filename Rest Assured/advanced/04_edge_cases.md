# Part 4: Edge Cases & Out-of-the-Box Scenarios

## 4.1 Negative Testing Scenarios (Must Know)

### String Fields

```
- Empty string ""
- Single character "a"
- Max allowed length (e.g., 255 chars) ← Should PASS
- Max + 1 (256 chars)                  ← Should FAIL with 422
- SQL injection: "'; DROP TABLE users;--"
- XSS: "<script>alert('xss')</script>"
- Unicode/Emoji: "用户名🚀"
- HTML entities: "&lt;script&gt;"
- Null value: null
- Whitespace only: "   "
```

### Numeric Fields

```
- 0, -1, negative values
- Max integer (2147483647)
- Max + 1 overflow
- Float precision: 99.9999999
- Exponential: 1e10
```

### Date/Time Fields

```
- Past date (e.g., 1900-01-01)
- Future date (9999-12-31)
- Leap year: 2024-02-29
- Non-leap year: 2023-02-29 ← Should FAIL
- Timezone edge cases
- Invalid format: "2024-13-45"
- Unix epoch: 0
- DST transitions
```

### ID Fields

```
- Valid UUID
- Invalid UUID format
- UUID of non-existent resource ← 404
- UUID of deleted resource      ← 404 or 410
- UUID from different tenant    ← 403 (IDOR)
```

---

## 4.2 Security Testing Scenarios

### IDOR (Insecure Direct Object Reference)

```java
@Test
public void user_cannotAccessAnotherTenantData() {
    // Tenant A's token should NOT access Tenant B's resource
    given()
        .header("Authorization", "Bearer " + TENANT_A_TOKEN)
    .when()
        .get("/users/" + TENANT_B_USER_ID)
    .then()
        .statusCode(anyOf(equalTo(403), equalTo(404)));
        // Some APIs return 404 to not leak resource existence
}
```

### Mass Assignment

```java
@Test
public void createUser_withAdminRoleInPayload_doesNotEscalatePrivileges() {
    String payload = """
        {
          "name": "Hacker",
          "email": "hacker@test.com",
          "role": "ADMIN",
          "isAdmin": true,
          "permissions": ["DELETE_ALL"]
        }
        """;

    given()
        .spec(requestSpec)
        .body(payload)
    .when()
        .post("/users")
    .then()
        .statusCode(201)
        .body("role", not(equalTo("ADMIN"))) // Should be STANDARD by default
        .body("$", not(hasKey("isAdmin")));
}
```

### Header Injection

```java
@Test
public void api_withInjectedHeaders_isHandledSafely() {
    given()
        .spec(requestSpec)
        .header("X-Custom-Header", "value\r\nX-Injected: hacked")
    .when()
        .get("/users")
    .then()
        .statusCode(anyOf(equalTo(200), equalTo(400)));
}
```

### Rate Limiting

```java
@Test
public void api_whenRateLimitExceeded_returns429WithRetryAfter() {
    int limit = 100;
    Response lastResponse = null;

    for (int i = 0; i <= limit + 1; i++) {
        lastResponse = given()
            .spec(requestSpec)
            .when()
            .get("/users")
            .thenReturn();
    }

    assertThat(lastResponse.statusCode(), equalTo(429));
    assertThat(lastResponse.header("Retry-After"), notNullValue());
    assertThat(lastResponse.header("X-RateLimit-Limit"), notNullValue());
}
```

---

## 4.3 Concurrency & Race Condition Testing

```java
@Test
public void concurrentRequests_onSameResource_doNotCauseDataCorruption()
        throws InterruptedException {
    int threads = 10;
    ExecutorService executor = Executors.newFixedThreadPool(threads);
    CountDownLatch latch = new CountDownLatch(threads);
    List<Integer> statusCodes = Collections.synchronizedList(new ArrayList<>());

    for (int i = 0; i < threads; i++) {
        executor.submit(() -> {
            try {
                int status = given()
                    .spec(requestSpec)
                    .body(Map.of("name", "Concurrent User " + Thread.currentThread().getId()))
                    .when()
                    .post("/users")
                    .statusCode();
                statusCodes.add(status);
            } finally {
                latch.countDown();
            }
        });
    }

    latch.await(30, TimeUnit.SECONDS);
    executor.shutdown();

    // All should succeed without server errors
    assertTrue(statusCodes.stream().noneMatch(code -> code == 500));
}
```

---

## 4.4 Idempotency Testing

```java
@Test
public void deleteUser_calledTwice_returns404OnSecondCall() {
    // First DELETE → 204
    given().spec(requestSpec)
        .pathParam("id", createdUserId)
        .when().delete("/users/{id}")
        .then().statusCode(204);

    // Second DELETE → 404 (or 204 for truly idempotent APIs)
    given().spec(requestSpec)
        .pathParam("id", createdUserId)
        .when().delete("/users/{id}")
        .then().statusCode(anyOf(equalTo(404), equalTo(204)));
}

@Test
public void putRequest_calledMultipleTimes_producesIdenticalResults() {
    UpdateUserRequest payload = UpdateUserRequest.builder()
        .name("Idempotent Name")
        .email("idem@test.com")
        .build();

    // Execute PUT 3 times
    for (int i = 0; i < 3; i++) {
        given().spec(requestSpec)
            .pathParam("id", createdUserId)
            .body(payload)
            .when().put("/users/{id}")
            .then().statusCode(200)
            .body("name", equalTo("Idempotent Name"));
    }
}
```

---

## 4.5 Caching & ETag Testing

```java
@Test
public void api_supportsETagCaching() {
    // First request — get ETag
    String etag = given()
        .spec(requestSpec)
        .pathParam("id", userId)
        .when().get("/users/{id}")
        .then().statusCode(200)
        .extract().header("ETag");

    assertNotNull(etag);

    // Second request with If-None-Match — should return 304
    given()
        .spec(requestSpec)
        .pathParam("id", userId)
        .header("If-None-Match", etag)
        .when().get("/users/{id}")
        .then().statusCode(304)
        .body(emptyOrNullString()); // No body on 304
}

@Test
public void api_afterUpdate_returnsNewETag() {
    String originalEtag = getEtag(userId);

    // Update the resource
    updateUser(userId);

    String newEtag = getEtag(userId);

    assertNotEquals(originalEtag, newEtag); // ETag must change after update
}
```

---

## 4.6 Content Negotiation Testing

```java
@Test
public void api_requestingXML_returnsXMLIfSupported() {
    given()
        .spec(requestSpec)
        .header("Accept", "application/xml")
        .when().get("/users/" + userId)
        .then()
        .statusCode(anyOf(equalTo(200), equalTo(406)));
        // 200 if XML supported, 406 Not Acceptable if not
}

@Test
public void api_withInvalidContentType_returns415() {
    given()
        .spec(requestSpec)
        .contentType("text/plain")
        .body("name=John")
        .when().post("/users")
        .then().statusCode(415);
}
```
