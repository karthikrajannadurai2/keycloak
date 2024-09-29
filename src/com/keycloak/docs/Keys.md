In Keycloak, the **Keys** tab under **Realm Settings** allows you to manage cryptographic keys and certificates that are used for various security-related functions, such as **signing** and **encryption**. These keys are essential for securing tokens, such as **JSON Web Tokens (JWT)**, and managing communication between different services in your realm.

### Purpose of Keys in Keycloak

Keycloak uses cryptographic keys for:
1. **Signing Tokens**: To verify the integrity of tokens (such as ID tokens, access tokens, and SAML assertions) and prove that they originated from Keycloak.
2. **Encryption**: Encrypts tokens or sensitive information exchanged with clients and external systems.
3. **HTTPS Communication**: Secures communication between Keycloak and external systems, such as browsers, identity providers, and applications.

### Types of Keys in Keycloak

Keycloak supports different types of keys for various purposes, and they can be managed in the **Keys** tab of the realm settings.

#### 1. **Active Keys**
These are the currently active keys that are being used by Keycloak. Each key has a **priority** (the higher the number, the higher the priority), and Keycloak will use the key with the highest priority for signing.

#### 2. **RSA Keys**
**RSA (Rivest–Shamir–Adleman)** keys are typically used for signing **JWT tokens** (including OIDC tokens). RSA is one of the most common asymmetric encryption algorithms, and Keycloak uses RSA key pairs for signing and verifying tokens.

- **Private Key**: Used to sign tokens.
- **Public Key**: Used by clients or other services to verify the token’s signature.

#### 3. **ECDSA Keys**
**ECDSA (Elliptic Curve Digital Signature Algorithm)** keys are used for signing tokens in a more efficient way compared to RSA. ECDSA provides a similar level of security with smaller key sizes, which can result in better performance in some cases.

#### 4. **HMAC Keys**
**HMAC (Hash-based Message Authentication Code)** keys are used for symmetric signing, where the same key is used for both signing and verification. HMAC is typically used for certain types of authentication tokens or systems that support symmetric encryption.

#### 5. **AES Keys**
**AES (Advanced Encryption Standard)** keys are used for encrypting data. AES is a symmetric encryption algorithm, and Keycloak can use it for encrypting tokens or other sensitive information that must be protected during transmission.

#### 6. **Client Keys**
Each client can have its own set of keys for signing and encryption. These keys can be managed within each client's settings in Keycloak.

### Key Management Operations

In the **Keys** tab of realm settings, you can perform the following operations:

#### 1. **Generate New Keys**
You can generate new keys for signing or encryption directly within the Keycloak Admin Console.

- **RSA Key Pair**: You can generate a new RSA key pair by selecting the key type and specifying the priority.
- **ECDSA Key Pair**: Generate a new ECDSA key pair.
- **HMAC Secret**: Generate an HMAC secret for symmetric key signing.

When generating a new key, you must assign a **priority** value to determine the order of key usage. The key with the highest priority will be used for signing.

#### 2. **Import Keys**
You can also import existing keys or certificates (such as those issued by a Certificate Authority) into Keycloak for use in your realm. This is useful if you have externally managed keys or need to use specific certificates for regulatory or compliance reasons.

- **Public Key Certificate**: You can import a public key certificate (PEM format) to verify signed tokens.
- **Private Key**: You can import a private key (PEM format) for signing tokens.

#### 3. **Rotate Keys**
Key rotation allows you to replace an old key with a new one without breaking existing tokens. You can generate or import new keys, give them a higher priority, and Keycloak will start using the new keys while still honoring tokens signed with the old ones (as long as the old keys are still available for verification).

- **Rotation** is important for security and compliance, ensuring that your keys are regularly refreshed to prevent key compromise.

#### 4. **View and Export Keys**
Keycloak allows you to view your active and passive keys. You can export public keys or certificates for use by external clients or services to verify the integrity of tokens signed by Keycloak.

- **Public Key**: Export the public key in PEM format for sharing with external systems (e.g., clients or federated identity providers).

#### 5. **Set Key Usage**
Each key in Keycloak can be configured with a specific usage type:
- **Signature**: The key is used for signing tokens (such as JWTs).
- **Encryption**: The key is used for encrypting data or tokens.
- **Both**: The key can be used for both signing and encryption.

### Key Algorithms Supported in Keycloak

Keycloak supports a variety of cryptographic algorithms for signing and encryption:

- **RSA Algorithms**: RSA keys can use different signature algorithms, such as `RS256`, `RS384`, and `RS512`.
- **ECDSA Algorithms**: ECDSA keys can use `ES256`, `ES384`, or `ES512` algorithms for signature.
- **HMAC Algorithms**: HMAC supports algorithms like `HS256`, `HS384`, and `HS512`.
- **AES Algorithms**: AES keys are typically used for encryption and decryption of sensitive data.

### Example: Managing Keys in Keycloak

#### 1. **Generate a New RSA Key Pair for Signing**
To generate a new RSA key pair in Keycloak:

1. Go to **Realm Settings** → **Keys**.
2. Click **Add Key**.
3. Select **RSA** as the provider and choose the **Key size** (2048 or 4096 bits).
4. Set a **Priority** (e.g., 100).
5. Click **Generate**.

Keycloak will now use this key for signing tokens, and clients can verify them using the corresponding public key.

#### 2. **Export Public Key for Verification**
To allow an external client or service to verify tokens signed by Keycloak:

1. Go to **Realm Settings** → **Keys**.
2. Find the active RSA key in the list.
3. Click the **Public Key** button.
4. Copy the **PEM format** public key and provide it to the external service.

The external service can now use this public key to verify tokens signed by Keycloak.

### Key Use in OIDC and SAML

- **OIDC (OpenID Connect)**: The keys are used to sign and verify **JWT tokens**. Clients consuming tokens from Keycloak will validate the token's signature using the public key.
- **SAML**: The keys are used to sign and encrypt **SAML assertions**. The service provider will verify the signature using the public key provided by Keycloak.

### Conclusion

The **Keys** section in Keycloak's realm settings is vital for managing cryptographic keys used in signing and encryption. Proper management of keys, including key generation, import, rotation, and usage, is crucial for the security of your tokens and data exchanges. With support for RSA, ECDSA, HMAC, and AES, Keycloak provides flexible options for securing tokens and communication across services.