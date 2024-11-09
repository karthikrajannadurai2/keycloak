Signing a JSON Web Token (JWT) with a private key involves a series of steps that use cryptography to ensure the token’s authenticity and integrity. Here’s a breakdown of the process:

1. **Define the JWT Structure**: JWTs have three parts:
    - **Header**: Specifies the type of token (JWT) and the signing algorithm used (e.g., RS256 for RSA-based signing).
    - **Payload**: Contains the claims, or statements about the user and additional metadata. Common claims include `sub` (subject), `iat` (issued at), and `exp` (expiration time).
    - **Signature**: Ensures the token’s authenticity and that it hasn’t been altered.

2. **Base64-Encode Header and Payload**:
    - The `header` and `payload` are converted to JSON, then encoded in Base64 URL format. This produces two parts:
      ```
      Base64(Header).Base64(Payload)
      ```

3. **Generate the Signature**:
    - Using the private key, a digital signature is created by applying a signing algorithm to the encoded header and payload.
    - For example, with RSA, the private key is used along with the specified algorithm (e.g., RS256) to create the signature:
      ```
      Signature = Sign(private_key, Base64(Header) + "." + Base64(Payload))
      ```

4. **Combine All Parts**:
    - The encoded header, encoded payload, and generated signature are combined with periods (`.`) as separators:
      ```
      JWT = Base64(Header) + "." + Base64(Payload) + "." + Signature
      ```
    - The result is a signed JWT token that can be shared with clients or users.

5. **Verification (by Recipients)**:
    - When the JWT is received, recipients can use the corresponding *public key* to verify the token’s signature. If the signature matches, they know the token is authentic and has not been tampered with.

Signing a JWT with a private key is a secure way to prove the token’s integrity and origin, as only the private key owner could have generated that particular signature. This process is commonly used in authentication systems, such as OAuth2 and OpenID Connect.