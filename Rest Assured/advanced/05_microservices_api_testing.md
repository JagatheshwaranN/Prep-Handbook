# Part 5: Microservices API Testing

## 5.1 Testing Challenges Unique to Microservices

```
1. Service Discovery         - Services register/deregister dynamically
2. Network Failures          - Latency, partial failures, timeouts
3. Distributed Tracing       - Tracking request across services
4. Data Consistency          - Eventual consistency vs strong consistency
5. Service Versioning        - Multiple API versions running simultaneously
6. Circuit Breakers          - How does Service A behave when B is down?
5. Bulkhead Pattern          - Thread pool isolation testing
8. Saga Pattern              - Distributed transaction rollback testing
```

---

## 5.2 Service Virtualization / Stubbing

```java
// WireMock for stubbing downstream services
@Test
public void orderService_whenInventoryServiceIsDown_returnsDegradedResponse() {
    // Stub inventory service as down
    stubFor(get(urlEqualTo("/inventory/check"))
        .willReturn(aResponse()
            .withFault(Fault.CONNECTION_RESET_BY_PEER)));

    // Order service should use fallback (circuit breaker)
    given()
        .spec(requestSpec)
        .body(orderPayload)
    .when()
        .post("/orders")
    .then()
        .statusCode(202)   // Accepted, will retry async
        .body("status", equalTo("PENDING"))
        .body("message", containsString("inventory check delayed"));
}

// Simulate slow response (for timeout testing)
stubFor(get(urlEqualTo("/payment/process"))
    .willReturn(aResponse()
        .withFixedDelay(5000)  // 5 second delay
        .withStatus(200)));
```

---

## 5.3 Chaos Engineering Tests

```java
@Test
public void paymentService_withNetworkLatency_handlesGracefully() {
    // Inject 2 second latency via WireMock
    stubFor(post(urlEqualTo("/payment/charge"))
        .willReturn(aResponse()
            .withFixedDelay(2000)
            .withStatus(200)
            .withBody("{\"status\":\"success\"}")));

    long startTime = System.currentTimeMillis();

    given()
        .spec(requestSpec)
        .body(paymentPayload)
    .when()
        .post("/orders/pay")
    .then()
        .statusCode(anyOf(equalTo(200), equalTo(408), equalTo(504)));

    long elapsed = System.currentTimeMillis() - startTime;

    // API should have its own timeout and not hang forever
    assertTrue(elapsed < 3500, "API should timeout and respond within 3.5s");
}
```

---

## 5.4 API Versioning Testing

```java
// Test multiple versions simultaneously
@Test
public void v1_and_v2_returnDifferentSchemas() {
    // V1 response
    Response v1Response = given().spec(requestSpec)
        .when().get("/v1/users/" + userId)
        .then().statusCode(200).extract().response();

    // V2 response (breaking change: 'name' split to 'firstName'/'lastName')
    Response v2Response = given().spec(requestSpec)
        .when().get("/v2/users/" + userId)
        .then().statusCode(200).extract().response();

    // V1 has 'name', V2 has 'firstName' + 'lastName'
    assertNotNull(v1Response.jsonPath().getString("name"));
    assertNull(v2Response.jsonPath().getString("name"));
    assertNotNull(v2Response.jsonPath().getString("firstName"));
    assertNotNull(v2Response.jsonPath().getString("lastName"));
}
```

---

## 5.5 Event-Driven / Async API Testing

```java
@Test
public void createOrder_triggersInventoryUpdateEvent() throws InterruptedException {
    // Place order
    String orderId = given().spec(requestSpec)
        .body(orderPayload)
        .when().post("/orders")
        .then().statusCode(202)
        .extract().jsonPath().getString("orderId");

    // Poll for async status (with timeout)
    Awaitility.await()
        .atMost(30, TimeUnit.SECONDS)
        .pollInterval(2, TimeUnit.SECONDS)
        .until(() -> {
            String status = given().spec(requestSpec)
                .pathParam("orderId", orderId)
                .when().get("/orders/{orderId}")
                .then().extract()
                .jsonPath().getString("status");
            return "CONFIRMED".equals(status);
        });
}
```
