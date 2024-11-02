You’re right! Keycloak doesn’t provide a built-in executor specifically named "Enforce HTTPS Redirect URIs." However, this security measure can still be achieved by configuring other Keycloak settings or by writing a custom executor if necessary. Let’s go over how you can enforce HTTPS for redirect URIs in Keycloak without a specific executor.

### Ways to Enforce HTTPS Redirect URIs in Keycloak

1. **Configure HTTPS Enforced Settings for the Realm**:
    - In the **Realm Settings** under the **Security Defenses** tab, set **Require SSL** to **All Requests**. This will require all communication with Keycloak to use HTTPS.
    - This setting enforces HTTPS for all requests, not just redirect URIs. It helps ensure that the entire authentication flow, including redirect URIs, occurs over secure channels.

2. **Restrict Redirect URIs in Client Settings**:
    - When configuring a client in Keycloak, set the **Valid Redirect URIs** field to only accept `https://` URLs. You can explicitly list the secure URLs for each client rather than using wildcards.
    - This way, if a client attempts to register or update with an HTTP redirect URI, it will be rejected by Keycloak.

3. **Custom Executor for Enforcing HTTPS Redirect URIs**:
    - If you need stricter enforcement, you could write a custom executor to validate redirect URIs during client registration and updates. Keycloak’s **Service Provider Interface (SPI)** allows you to create a custom executor that checks if all redirect URIs start with `https://` and rejects those that do not.

---

### Example: Writing a Custom Executor to Enforce HTTPS Redirect URIs

If you decide to implement a custom executor to enforce HTTPS on redirect URIs, here’s a general outline:

1. **Create a New Java Class** for the custom executor, implementing the `ClientPolicyExecutorProvider` interface.
2. **Implement the Validation Logic**:
    - In the executor, check each redirect URI for the `https://` prefix.
    - If any URI does not start with `https://`, throw an exception to reject the client registration or update.
3. **Register the Executor with Keycloak**:
    - Use Keycloak’s `spi` configuration in `META-INF/services` to register your custom executor as a new client policy executor provider.
4. **Add the Executor to a Client Policy**:
    - Once the custom executor is registered, create a client policy and add your custom executor to enforce HTTPS on redirect URIs.

By using this custom executor approach, you have precise control over redirect URIs, ensuring that all registered clients use secure HTTPS endpoints.