In OAuth 2.0 and OpenID Connect (OIDC), the `.well-known` endpoints are special URIs that allow clients to discover metadata and important configuration details about the authorization server. These endpoints are standardized for ease of integration and automation of client setup. Below are the most commonly used `.well-known` endpoints, their purpose, and the request parameters involved.

### 1. **OAuth 2.0 Authorization Server Metadata** (`.well-known/oauth-authorization-server`)

The **OAuth 2.0 Authorization Server Metadata** endpoint provides a machine-readable way for clients to discover the authorization server's configuration. This includes endpoints for token issuance, introspection, revocation, and supported features like grant types, signing algorithms, and more.

#### **Endpoint**:
```
GET /.well-known/oauth-authorization-server/{realm}
```
- `{realm}`: The realm or tenant in a multi-tenant system (like Keycloak).

#### **Response**:
The response is a JSON object containing metadata about the OAuth 2.0 server configuration, including:

- `issuer`: The identifier for the authorization server.
- `authorization_endpoint`: The endpoint for initiating the authorization code or implicit flow.
- `token_endpoint`: The endpoint where clients can exchange authorization codes or refresh tokens for access tokens.
- `jwks_uri`: The endpoint for retrieving the server’s public keys (used for JWT validation).
- `scopes_supported`: The list of scopes supported by the authorization server.
- `response_types_supported`: The response types the server supports (e.g., `code`, `token`, etc.).
- `grant_types_supported`: The grant types supported by the authorization server (e.g., `authorization_code`, `client_credentials`, `password`, etc.).

Example Response:
```json
{
  "issuer": "https://auth.example.com/realms/myrealm",
  "authorization_endpoint": "https://auth.example.com/realms/myrealm/protocol/openid-connect/auth",
  "token_endpoint": "https://auth.example.com/realms/myrealm/protocol/openid-connect/token",
  "jwks_uri": "https://auth.example.com/realms/myrealm/protocol/openid-connect/certs",
  "scopes_supported": ["openid", "profile", "email"],
  "response_types_supported": ["code", "token", "id_token"],
  "grant_types_supported": ["authorization_code", "client_credentials", "refresh_token"]
}
```

---

### 2. **OpenID Connect Discovery Document** (`.well-known/openid-configuration`)

The **OpenID Connect Discovery** document is similar to the OAuth 2.0 metadata endpoint but tailored for OpenID Connect (OIDC). It provides essential information that OIDC clients need to know about the identity provider (IdP), including endpoints for authorization, token exchange, user info, and public key retrieval.

#### **Endpoint**:
```
GET /.well-known/openid-configuration
```

This endpoint typically responds with a JSON document containing the following fields:

- `issuer`: The authorization server’s issuer identifier.
- `authorization_endpoint`: URL for the authorization endpoint.
- `token_endpoint`: URL for the token endpoint.
- `userinfo_endpoint`: URL for retrieving user information (in OIDC).
- `jwks_uri`: URL for retrieving the JSON Web Key Set (JWKS), which contains the public keys used to verify tokens.
- `scopes_supported`: Scopes that the authorization server supports (e.g., `openid`, `profile`, `email`).
- `response_types_supported`: Supported response types for authorization requests (e.g., `code`, `token`, `id_token`).
- `grant_types_supported`: Supported grant types (e.g., `authorization_code`, `client_credentials`, `password`, `refresh_token`).
- `id_token_signing_alg_values_supported`: Supported signing algorithms for ID tokens (e.g., `RS256`, `HS256`).

Example Response:
```json
{
  "issuer": "https://auth.example.com/realms/myrealm",
  "authorization_endpoint": "https://auth.example.com/realms/myrealm/protocol/openid-connect/auth",
  "token_endpoint": "https://auth.example.com/realms/myrealm/protocol/openid-connect/token",
  "userinfo_endpoint": "https://auth.example.com/realms/myrealm/protocol/openid-connect/userinfo",
  "jwks_uri": "https://auth.example.com/realms/myrealm/protocol/openid-connect/certs",
  "response_types_supported": ["code", "token", "id_token"],
  "grant_types_supported": ["authorization_code", "client_credentials", "refresh_token"],
  "scopes_supported": ["openid", "profile", "email"],
  "id_token_signing_alg_values_supported": ["RS256"]
}
```

#### **Usage**:
- **OIDC Clients** can use this discovery document to configure themselves dynamically by fetching endpoint locations, supported features, and requirements of the OpenID Provider (OP).

---

### 3. **JSON Web Key Set (JWKS) Endpoint** (`/protocol/openid-connect/certs`)

The **JWKS endpoint** provides the public keys that the authorization server uses to sign tokens (such as JWTs). Clients or resource servers use these public keys to verify the authenticity and integrity of tokens.

#### **Endpoint**:
```
GET /realms/{realm}/protocol/openid-connect/certs
```

- `{realm}`: The realm from which to retrieve the public keys.

#### **Response**:
The response is a JSON object containing the **JSON Web Key Set (JWKS)**, which is an array of public keys.

Example Response:
```json
{
  "keys": [
    {
      "kty": "RSA",
      "kid": "abc123",
      "use": "sig",
      "alg": "RS256",
      "n": "big_base64url_encoded_modulus",
      "e": "AQAB"
    }
  ]
}
```

Fields:
- `kty`: Key type (usually `RSA` or `EC`).
- `kid`: Key ID, used to match the key with the signature of a JWT.
- `use`: How the key is used (`sig` for signature, `enc` for encryption).
- `alg`: The algorithm used to sign the token (`RS256`, `HS256`, etc.).
- `n`: The modulus for the RSA public key.
- `e`: The exponent for the RSA public key.

#### **Usage**:
- **Token Verification**: Resource servers use these public keys to verify the signature of JWT access tokens or ID tokens issued by the authorization server.

---

### 4. **OAuth 2.0 Token Introspection Endpoint**

The **token introspection** endpoint is used by resource servers to validate the status of a token (e.g., access token or refresh token) and retrieve additional metadata about the token.

#### **Endpoint**:
```
POST /realms/{realm}/protocol/openid-connect/token/introspect
```

#### **Request Parameters**:
- `token`: The token (access token or refresh token) to be introspected.
- `token_type_hint`: (Optional) Hint about the type of the token (`access_token` or `refresh_token`).
- **Authentication**: Typically, the client needs to provide its credentials (e.g., via HTTP Basic Authentication).

Example Request:
```bash
POST https://auth.example.com/realms/myrealm/protocol/openid-connect/token/introspect
-d "token=ACCESS_TOKEN"
--user client_id:client_secret
```

#### **Response**:
The response is a JSON object containing details about the token.

Example Response:
```json
{
  "active": true,
  "username": "john_doe",
  "client_id": "my_client",
  "scope": "profile email",
  "exp": 1693571000,
  "iat": 1693567400,
  "sub": "12345678-1234-5678-1234-567812345678"
}
```

---

### 5. **OAuth 2.0 Token Revocation Endpoint**

The **token revocation** endpoint is used to revoke tokens, such as access tokens or refresh tokens, to ensure they can no longer be used.

#### **Endpoint**:
```
POST /realms/{realm}/protocol/openid-connect/revoke
```

#### **Request Parameters**:
- `token`: The token to be revoked (access or refresh token).
- `token_type_hint`: (Optional) Hint about the type of the token (`access_token` or `refresh_token`).
- **Authentication**: The client must authenticate, usually via HTTP Basic Authentication.

Example Request:
```bash
POST https://auth.example.com/realms/myrealm/protocol/openid-connect/revoke
-d "token=ACCESS_TOKEN"
--user client_id:client_secret
```

#### **Response**:
- **Success**: The server returns an HTTP 200 status code with no body.
- **Error**: In case of an error (e.g., invalid token), the server returns an appropriate error response.

---

### 6. **OAuth 2.0 Userinfo Endpoint** (`/protocol/openid-connect/userinfo`)

The **userinfo** endpoint returns claims (such as the user's profile information) about the authenticated user. It is part of the OIDC standard and allows clients to retrieve information about the user who authorized the access token.

#### **Endpoint**:
```
GET /realms/{realm}/protocol/openid-connect/userinfo
```

#### **Request Parameters**:
- **Authentication**: The request must include the access token in the `Authorization` header as a Bearer token.

Example Request:
```bash
GET https://auth.example.com/realms/myrealm/protocol/openid-connect/userinfo
-H "Authorization: Bearer ACCESS

_TOKEN"
```

#### **Response**:
A JSON object with the user's claims, such as name, email, and profile information.

Example Response:
```json
{
  "sub": "12345678-1234-5678-1234-567812345678",
  "name": "John Doe",
  "email": "john.doe@example.com",
  "preferred_username": "john_doe",
  "given_name": "John",
  "family_name": "Doe"
}
```

---

### Summary of `.well-known` Endpoints

| **Endpoint**                               | **Purpose**                                                                                           |
|--------------------------------------------|-------------------------------------------------------------------------------------------------------|
| `/realms/{realm}/.well-known/oauth-authorization-server` | Provides OAuth 2.0 metadata for the authorization server.                                              |
| `/realms/{realm}/.well-known/openid-configuration`       | Provides OIDC metadata for the OpenID Provider (OP).                                                   |
| `/realms/{realm}/protocol/openid-connect/certs`          | Provides the JSON Web Key Set (JWKS) for validating token signatures.                                  |
| `/realms/{realm}/protocol/openid-connect/token/introspect` | Allows resource servers to validate tokens (access tokens, refresh tokens).                           |
| `/realms/{realm}/protocol/openid-connect/revoke`         | Allows clients to revoke access or refresh tokens.                                                     |
| `/realms/{realm}/protocol/openid-connect/userinfo`       | Returns user claims for the authenticated user.                                                        |

These endpoints facilitate dynamic client configuration and provide essential information for token handling and user profile retrieval.