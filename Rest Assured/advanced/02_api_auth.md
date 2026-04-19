# 🔐 Part 2: API Authentication & Authorization

## 2.1 Auth Mechanisms

### Basic Auth

```
Header: Authorization: Basic base64(username:password)
```

- Stateless, simple
- Must use HTTPS always
- Not suitable for production APIs

---

### API Key

```
Header: X-API-Key: abc123
Query:  GET /api/users?api_key=abc123
```

- Simple but less secure
- No expiry by default

---

### Bearer Token / OAuth 2.0

```
Header: Authorization: Bearer eyJhbGc...
```

**OAuth 2.0 Grant Types:**

```
Authorization Code  → Web apps (most secure)
Implicit            → SPAs (deprecated in OAuth 2.1)
Client Credentials  → Machine-to-machine (M2M)
Password Grant      → Trusted first-party apps (deprecated)
Device Code         → TVs, IoT devices
```

---

### JWT (JSON Web Token) Structure

```
Header.Payload.Signature
eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwiZXhwIjoxNjAwMDAwMDAwfQ.signature

Header:  { "alg": "RS256", "typ": "JWT" }
Payload: { "sub": "user123", "role": "admin", "exp": 1600000000 }
```

> ⚠️ **Testing JWT:** Always test expired tokens, tampered signatures, missing claims, algorithm confusion (`alg: none` attack), and role escalation in payload.

---

## API Key vs JWT vs OAuth 2.0

| Feature        | API Key    | JWT      | OAuth 2.0 |
|----------------|:----------:|:--------:|:---------:|
| Expiry         | No         | Yes      | Yes       |
| Stateless      | Yes        | Yes      | Depends   |
| Revocable      | Manually   | Hard     | Yes       |
| User Context   | No         | Yes      | Yes       |
