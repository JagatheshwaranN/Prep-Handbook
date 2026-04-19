# Part 3: REST Assured — Deep Dive

## 3.1 Framework Architecture

```
src/
├── main/java/
│   ├── config/
│   │   ├── ConfigManager.java        # Property file reader
│   │   └── EnvironmentConfig.java    # Env-specific configs
│   ├── constants/
│   │   ├── APIEndpoints.java
│   │   └── StatusCodes.java
│   ├── models/
│   │   ├── request/
│   │   │   └── CreateUserRequest.java
│   │   └── response/
│   │       └── UserResponse.java
│   ├── utils/
│   │   ├── AuthUtils.java            # Token generation
│   │   ├── RequestBuilder.java       # Fluent request builder
│   │   ├── ResponseValidator.java
│   │   └── TestDataGenerator.java    # Faker, random data
│   └── specs/
│       ├── RequestSpecifications.java
│       └── ResponseSpecifications.java
├── test/java/
│   ├── base/
│   │   └── BaseTest.java             # Setup/teardown, logging
│   ├── tests/
│   │   ├── smoke/
│   │   ├── regression/
│   │   ├── contract/
│   │   └── performance/
│   └── listeners/
│       └── ExtentReportListener.java
└── resources/
    ├── config.properties
    ├── testdata/
    └── schemas/                      # JSON schemas
```

---

## 3.2 Core Rest Assured Code — Production Grade

### BaseTest.java

```java
public class BaseTest {
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeSuite
    public void globalSetup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.useRelaxedHTTPSValidation(); // For non-prod environments
    }

    @BeforeClass
    public void setupSpecs() {
        requestSpec = new RequestSpecBuilder()
            .setBaseUri(ConfigManager.getBaseUrl())
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + AuthUtils.getToken())
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new AllureRestAssured())         // Allure reporting
            .setRelaxedHTTPSValidation()
            .build();

        responseSpec = new ResponseSpecBuilder()
            .expectResponseTime(lessThan(3000L), TimeUnit.MILLISECONDS)
            .build();
    }
}
```

### RequestSpecifications.java — Reusable Specs

```java
public class RequestSpecifications {

    public static RequestSpecification getAuthenticatedSpec() {
        return new RequestSpecBuilder()
            .setBaseUri(ConfigManager.getBaseUrl())
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + AuthUtils.getToken())
            .addHeader("Accept", "application/json")
            .addHeader("X-Correlation-Id", UUID.randomUUID().toString())
            .build();
    }

    public static RequestSpecification getMultipartSpec() {
        return new RequestSpecBuilder()
            .setBaseUri(ConfigManager.getBaseUrl())
            .setContentType("multipart/form-data")
            .addHeader("Authorization", "Bearer " + AuthUtils.getToken())
            .build();
    }

    public static RequestSpecification getBasicAuthSpec(String user, String pass) {
        return new RequestSpecBuilder()
            .setBaseUri(ConfigManager.getBaseUrl())
            .setAuth(RestAssured.basic(user, pass))
            .setContentType(ContentType.JSON)
            .build();
    }
}
```

### CRUD Operations — Production Style

```java
public class UserAPITests extends BaseTest {

    private static String createdUserId;

    // ─── CREATE ───────────────────────────────────────────────────────────────
    @Test(priority = 1, groups = {"smoke", "regression"})
    public void createUser_withValidPayload_returns201() {
        CreateUserRequest payload = CreateUserRequest.builder()
            .name("John Doe")
            .email("john.doe+" + System.currentTimeMillis() + "@test.com")
            .role("STANDARD")
            .build();

        Response response = given()
            .spec(requestSpec)
            .body(payload)
        .when()
            .post(APIEndpoints.USERS)
        .then()
            .spec(responseSpec)
            .statusCode(201)
            .header("Location", matchesRegex(".*/users/[a-z0-9-]+"))
            .body("id", notNullValue())
            .body("name", equalTo("John Doe"))
            .body("email", equalTo(payload.getEmail()))
            .body("createdAt", notNullValue())
            .body("status", equalTo("ACTIVE"))
            .extract().response();

        createdUserId = response.jsonPath().getString("id");
    }

    // ─── READ ─────────────────────────────────────────────────────────────────
    @Test(priority = 2, dependsOnMethods = "createUser_withValidPayload_returns201")
    public void getUser_withValidId_returns200() {
        given()
            .spec(requestSpec)
            .pathParam("id", createdUserId)
        .when()
            .get(APIEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .body("id", equalTo(createdUserId))
            .body("$", hasKey("name"))
            .body("$", hasKey("email"))
            .body("$", not(hasKey("password"))); // Security: password NEVER exposed
    }

    // ─── UPDATE (PUT) ─────────────────────────────────────────────────────────
    @Test(priority = 3)
    public void updateUser_withPUT_replacesEntireResource() {
        UpdateUserRequest payload = UpdateUserRequest.builder()
            .name("Jane Doe")
            .email("jane.doe@test.com")
            .role("ADMIN")
            .build();

        given()
            .spec(requestSpec)
            .pathParam("id", createdUserId)
            .body(payload)
        .when()
            .put(APIEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .body("name", equalTo("Jane Doe"))
            .body("role", equalTo("ADMIN"));
    }

    // ─── UPDATE (PATCH) ───────────────────────────────────────────────────────
    @Test(priority = 4)
    public void updateUser_withPATCH_updatesOnlyGivenFields() {
        String patchPayload = "{ \"name\": \"Updated Name\" }";

        given()
            .spec(requestSpec)
            .pathParam("id", createdUserId)
            .body(patchPayload)
        .when()
            .patch(APIEndpoints.USER_BY_ID)
        .then()
            .statusCode(200)
            .body("name", equalTo("Updated Name"))
            .body("email", notNullValue()); // Email should still be there
    }

    // ─── DELETE ───────────────────────────────────────────────────────────────
    @Test(priority = 5)
    public void deleteUser_withValidId_returns204() {
        given()
            .spec(requestSpec)
            .pathParam("id", createdUserId)
        .when()
            .delete(APIEndpoints.USER_BY_ID)
        .then()
            .statusCode(204)
            .body(emptyOrNullString());
    }

    @Test(priority = 6, dependsOnMethods = "deleteUser_withValidId_returns204")
    public void getUser_afterDeletion_returns404() {
        given()
            .spec(requestSpec)
            .pathParam("id", createdUserId)
        .when()
            .get(APIEndpoints.USER_BY_ID)
        .then()
            .statusCode(404);
    }
}
```

---

## 3.3 JSON Schema Validation

```java
@Test
public void createUser_responseMatchesSchema() {
    given()
        .spec(requestSpec)
        .pathParam("id", "123")
    .when()
        .get(APIEndpoints.USER_BY_ID)
    .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schemas/user-response-schema.json"));
}
```

### schemas/user-response-schema.json

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "required": ["id", "name", "email", "status", "createdAt"],
  "properties": {
    "id":        { "type": "string", "format": "uuid" },
    "name":      { "type": "string", "minLength": 1 },
    "email":     { "type": "string", "format": "email" },
    "status":    { "type": "string", "enum": ["ACTIVE", "INACTIVE", "SUSPENDED"] },
    "role":      { "type": "string", "enum": ["ADMIN", "STANDARD", "READ_ONLY"] },
    "createdAt": { "type": "string", "format": "date-time" },
    "password":  { "not": {} }
  },
  "additionalProperties": false
}
```

---

## 3.4 Data-Driven Testing

```java
@DataProvider(name = "invalidUserPayloads")
public Object[][] invalidPayloads() {
    return new Object[][] {
        // { payload,                    expectedStatus, expectedError }
        { "{}",                           400, "name is required"   },
        { "{\"name\":\"\"}",              422, "name cannot be blank" },
        { "{\"name\":\"a\".repeat(256)}", 422, "name too long"       },
        { "{\"email\":\"not-an-email\"}", 422, "invalid email"       },
        { "{\"role\":\"SUPERADMIN\"}",    422, "invalid role"        },
    };
}

@Test(dataProvider = "invalidUserPayloads")
public void createUser_withInvalidData_returnsExpectedError(
        String payload, int expectedStatus, String expectedMessage) {
    given()
        .spec(requestSpec)
        .body(payload)
    .when()
        .post(APIEndpoints.USERS)
    .then()
        .statusCode(expectedStatus)
        .body("message", containsString(expectedMessage));
}
```

---

## 3.5 Authentication Testing Patterns

```java
public class AuthTests extends BaseTest {

    @Test
    public void api_withoutToken_returns401() {
        given()
            .baseUri(ConfigManager.getBaseUrl())
            .contentType(ContentType.JSON)
            // No auth header
        .when()
            .get(APIEndpoints.USERS)
        .then()
            .statusCode(401)
            .body("error", equalTo("Unauthorized"));
    }

    @Test
    public void api_withExpiredToken_returns401() {
        given()
            .spec(requestSpec)
            .header("Authorization", "Bearer " + AuthUtils.getExpiredToken())
        .when()
            .get(APIEndpoints.USERS)
        .then()
            .statusCode(401)
            .body("error", containsString("token expired"));
    }

    @Test
    public void api_withTamperedToken_returns401() {
        String tamperedToken = AuthUtils.getValidToken() + "tampered";
        given()
            .spec(requestSpec)
            .header("Authorization", "Bearer " + tamperedToken)
        .when()
            .get(APIEndpoints.USERS)
        .then()
            .statusCode(401);
    }

    @Test
    public void standardUser_accessingAdminEndpoint_returns403() {
        given()
            .header("Authorization", "Bearer " + AuthUtils.getStandardUserToken())
            .contentType(ContentType.JSON)
        .when()
            .delete(APIEndpoints.USER_BY_ID.replace("{id}", "someOtherUserId"))
        .then()
            .statusCode(403)
            .body("error", equalTo("Forbidden"));
    }

    @Test
    public void user_accessingAnotherUsersData_returns403() {
        // User A tries to access User B's private data
        given()
            .header("Authorization", "Bearer " + AuthUtils.getUserAToken())
        .when()
            .get(APIEndpoints.USER_BY_ID.replace("{id}", USER_B_ID))
        .then()
            .statusCode(403); // IDOR prevention check
    }
}
```

---

## 3.6 Response Time & Performance Assertions

```java
@Test
public void getUserList_respondsWithin2Seconds() {
    given()
        .spec(requestSpec)
    .when()
        .get(APIEndpoints.USERS)
    .then()
        .statusCode(200)
        .time(lessThan(2000L)); // milliseconds
}

@Test
public void getUserList_respondsWithin2Seconds_withTimeUnit() {
    given()
        .spec(requestSpec)
    .when()
        .get(APIEndpoints.USERS)
    .then()
        .statusCode(200)
        .time(lessThan(2L), TimeUnit.SECONDS);
}
```

---

## 3.7 File Upload Testing

```java
@Test
public void uploadProfilePicture_withValidImage_returns200() {
    File imageFile = new File("src/test/resources/testdata/profile.jpg");

    given()
        .spec(RequestSpecifications.getMultipartSpec())
        .multiPart("file", imageFile, "image/jpeg")
        .multiPart("userId", createdUserId)
    .when()
        .post(APIEndpoints.UPLOAD_PROFILE)
    .then()
        .statusCode(200)
        .body("url", matchesRegex("https://.*\\.jpg"));
}

@Test
public void uploadFile_withExecutableFile_returns400() {
    File exeFile = new File("src/test/resources/testdata/malicious.exe");

    given()
        .spec(RequestSpecifications.getMultipartSpec())
        .multiPart("file", exeFile, "application/octet-stream")
    .when()
        .post(APIEndpoints.UPLOAD_PROFILE)
    .then()
        .statusCode(400)
        .body("error", containsString("file type not allowed"));
}
```

---

## 3.8 Pagination Testing

```java
@Test
public void getUsers_withPagination_returnsCorrectPage() {
    given()
        .spec(requestSpec)
        .queryParam("page", 1)
        .queryParam("size", 10)
        .queryParam("sort", "createdAt,desc")
    .when()
        .get(APIEndpoints.USERS)
    .then()
        .statusCode(200)
        .body("content.size()", equalTo(10))
        .body("page", equalTo(1))
        .body("totalPages", greaterThanOrEqualTo(1))
        .body("totalElements", greaterThanOrEqualTo(10))
        .body("first", equalTo(true))
        .body("last", anyOf(equalTo(true), equalTo(false)));
}

@Test
public void getUsers_withPageBeyondTotal_returnsEmptyList() {
    given()
        .spec(requestSpec)
        .queryParam("page", 99999)
        .queryParam("size", 10)
    .when()
        .get(APIEndpoints.USERS)
    .then()
        .statusCode(200)
        .body("content", empty());
}
```

---

## 3.9 Chaining API Calls (End-to-End Flow)

```java
@Test
public void e2e_orderFlow_createOrderAndVerifyPayment() {
    // Step 1: Create a product
    String productId = given().spec(requestSpec)
        .body(ProductFactory.createValid())
        .when().post("/products")
        .then().statusCode(201)
        .extract().jsonPath().getString("id");

    // Step 2: Add to cart
    String cartId = given().spec(requestSpec)
        .body(Map.of("productId", productId, "quantity", 2))
        .when().post("/cart")
        .then().statusCode(201)
        .extract().jsonPath().getString("cartId");

    // Step 3: Place order
    String orderId = given().spec(requestSpec)
        .body(Map.of("cartId", cartId, "paymentMethod", "CREDIT_CARD"))
        .when().post("/orders")
        .then().statusCode(201)
        .body("status", equalTo("PENDING_PAYMENT"))
        .extract().jsonPath().getString("orderId");

    // Step 4: Verify order status
    given().spec(requestSpec)
        .pathParam("orderId", orderId)
        .when().get("/orders/{orderId}")
        .then().statusCode(200)
        .body("status", equalTo("PENDING_PAYMENT"));
}
```

---

## 3.10 Contract Testing with Pact (Consumer-Driven)

```java
// Consumer side
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserService")
public class UserServiceContractTest {

    @Pact(consumer = "OrderService")
    public RequestResponsePact getUserPact(PactDslWithProvider builder) {
        return builder
            .given("user with id 123 exists")
            .uponReceiving("a request for user 123")
                .path("/users/123")
                .method("GET")
            .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                    .stringType("id", "123")
                    .stringType("name", "John Doe")
                    .stringType("email", "john@example.com")
                )
            .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getUserPact")
    public void verifyGetUserContract(MockServer mockServer) {
        given()
            .baseUri(mockServer.getUrl())
        .when()
            .get("/users/123")
        .then()
            .statusCode(200)
            .body("id", equalTo("123"));
    }
}
```
