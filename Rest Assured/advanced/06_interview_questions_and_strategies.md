# Part 6: Interview Questions & Expert Answers

## Tier 1 — Conceptual (Warm-up)

**Q: What's the difference between PUT and PATCH?**

PUT replaces the entire resource — you must send all fields. PATCH is for partial updates, only send what needs changing. A PUT without all fields may nullify omitted fields; PATCH won't.

---

**Q: When would you return 200 vs 201 vs 202?**

200 = synchronous success, resource updated/returned. 201 = resource created, include Location header. 202 = accepted for async processing, final outcome unknown yet.

---

**Q: How do you test an API when documentation doesn't exist?**

Explore via Postman/Insomnia, use network tab, reverse-engineer from frontend traffic, try OPTIONS endpoint, look for OpenAPI spec at `/swagger.json` or `/api-docs`, study error messages for clues.

---

## Tier 2 — Scenario-Based (Mid-level)

**Q: Your test creates a user but you cannot predict the user ID. How do you get it for subsequent tests?**

Extract from the 201 response Location header or response body, store it as a variable (Postman env variable or Rest Assured instance variable). Use `@BeforeClass` or `@BeforeMethod` in TestNG to create the user once and reuse.

---

**Q: How do you handle test data cleanup after tests?**

Three strategies: (1) Delete in `@AfterMethod`/`@AfterClass`, (2) Use dedicated test tenant that gets wiped nightly, (3) Database-level rollback with test transactions. Always tag test data with a recognizable prefix (e.g., `TEST_`) for safe cleanup.

---

**Q: How would you test a payment API without processing real payments?**

Use sandbox environment provided by payment gateway (Stripe, PayPal all have this). Use WireMock to stub payment service in lower environments. Use magic card numbers (e.g., `4242 4242 4242 4242` for Stripe success). Test failure scenarios using specific magic numbers for declined, insufficient funds, etc.

---

## Tier 3 — Advanced / Architect Level

**Q: How would you design an API test framework from scratch for a microservices project with 20 services?**

Key decisions: (1) Centralized BaseTest with shared request specs. (2) Per-service test modules as separate Maven modules in a parent POM. (3) Service clients abstraction layer — no direct RestAssured calls in tests. (4) Centralized test data management service. (5) Contract tests with Pact broker. (6) Environment config via Typesafe config. (7) Allure for reporting. (6) Parallel execution via TestNG parallel suites. (9) CI/CD integration with gates on coverage thresholds.

---

**Q: How do you prevent test pollution in a parallel test execution setup?**

Each test creates its own data and stores context in `ThreadLocal`. Tests are fully independent — no shared mutable state. Use unique identifiers (UUID or timestamp) in test data. Tenant isolation — each test thread uses its own test tenant. Tests clean up after themselves in `@AfterMethod`.

---

**Q: How would you test a HATEOAS API?**

Validate `rel` links are present and correct. Follow links dynamically instead of hardcoding URLs. Test that valid transitions exist and invalid transitions don't. Test the state machine: e.g., you can only cancel an order when it's in `PENDING` state — the cancel link should only appear then.

---

# 🔑 Part 9: Key Testing Strategies Summary

## Test Pyramid for APIs

```
          ▲
        /E2E\        ← Few, slow, brittle (Postman/Newman)
       /─────\
      / Integ \      ← Services integrated (RestAssured + WireMock)
     /─────────\
    /  Contract \    ← Consumer-Driven (Pact)
   /─────────────\
  /     Unit      \  ← Business logic, validators, parsers
 /─────────────────\
```

---

## Test Types Checklist

| | Test Type | Focus |
|---|---|---|
| ✅ | Functional Testing | Happy paths, all CRUD operations |
| ✅ | Negative Testing | Invalid inputs, missing fields |
| ✅ | Boundary Testing | Min/max values, edge cases |
| ✅ | Security Testing | Auth, IDOR, injection, OWASP API Top 10 |
| ✅ | Performance Testing | Response time assertions, load: JMeter/Gatling |
| ✅ | Contract Testing | Pact, OpenAPI schema validation |
| ✅ | Idempotency Testing | PUT, DELETE, GET |
| ✅ | Pagination Testing | Page boundaries, sort order |
| ✅ | Concurrency Testing | Race conditions, duplicate prevention |
| ✅ | Versioning Testing | Backward compatibility |
| ✅ | Error Handling Testing | Error format consistency, no stack traces |
| ✅ | Caching Testing | ETag, Cache-Control, 304 responses |
| ✅ | Retry/Resilience Testing | Circuit breakers, timeouts |
| ✅ | Audit/Logging Testing | Sensitive data not logged, audit trail |

---

## 🏆 OWASP API Security Top 10 — Test Coverage

| # | Risk | Test |
|---|---|---|
| API1 | Broken Object Level Authorization | IDOR tests — access another user's data |
| API2 | Broken Authentication | Expired/tampered tokens, brute force |
| API3 | Broken Object Property Level Auth | Mass assignment, over-exposure |
| API4 | Unrestricted Resource Consumption | Rate limiting, large payload tests |
| API5 | Broken Function Level Authorization | Standard user calling admin endpoints |
| API6 | Unrestricted Access to Sensitive Business Flows | Workflow bypass, skipping steps |
| API7 | Server Side Request Forgery | SSRF via URL parameters |
| API6 | Security Misconfiguration | HTTP headers, CORS, verbose errors |
| API9 | Improper Inventory Management | Old/undocumented versions accessible |
| API10 | Unsafe Consumption of APIs | Third-party API response injection |
