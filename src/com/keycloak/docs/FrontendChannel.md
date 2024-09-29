In Keycloak, the **Frontend URL** setting is important for defining how your Keycloak instance communicates with users and client applications over the web. You can configure this under **Realm Settings**.

Here's how to set the **Frontend URL**:

1. **Login to Keycloak Admin Console:**
    - Navigate to the Keycloak Admin Console (usually at `http://<host>:8080/auth/admin/`).

2. **Go to Realm Settings:**
    - In the left sidebar, click on **Realm Settings**.

3. **Switch to the General Tab:**
    - Under the General tab, you'll see the **Frontend URL** field.

4. **Configure Frontend URL:**
    - **Frontend URL** is the URL that your users and client applications will use to access Keycloak. This should be the external URL if you're behind a proxy, like `https://auth.example.com`.
    - For example: `https://<your-domain>/auth/`.

5. **Proxy Consideration:**
    - If you're behind a proxy, this URL must match the external endpoint visible to users. Make sure to correctly configure the proxy settings in Keycloak (`web-origin`, `base-url`, etc.).

6. **Save Changes:**
    - After configuring the Frontend URL, click **Save** to apply the changes.

This will allow Keycloak to redirect users to the correct external URL for login, logout, and other actions.

The **Frontend URL** in Keycloak specifies the external URL that client applications and users will use to access Keycloak services, such as login pages, logout actions, and account management. This URL is important in scenarios where Keycloak is running behind a reverse proxy or load balancer and the internal URL differs from the public-facing one.

### Purpose of the Frontend URL:
- To provide the correct external address for redirects when users are authenticated.
- To ensure that Keycloak generates URLs (for login, logout, token exchange, etc.) that match the external-facing URL when Keycloak is behind a proxy or in a containerized environment.
- To avoid issues with mixed content (HTTP vs. HTTPS) and invalid redirect URIs when Keycloak runs behind an SSL-terminating proxy.

### How the Frontend URL is Used:
- **Login/Logout Redirects**: When a user logs in or logs out, the frontend URL ensures that the redirect is to the proper external domain rather than an internal domain that might not be accessible from the internet.
- **OIDC/SAML Flows**: Keycloak will use the frontend URL to generate callback URIs for OpenID Connect or SAML authentication, ensuring that users and client applications can communicate correctly.

---

### Example of a Frontend URL Configuration:

Let’s assume Keycloak is running on an internal server with the following setup:
- **Internal Keycloak URL**: `http://keycloak.internal:8080/auth/`
- **External URL (behind a reverse proxy)**: `https://auth.example.com/`

If you don’t configure the frontend URL properly, users who attempt to log in might be redirected to the internal URL (`http://keycloak.internal:8080/auth/`), which is not accessible externally, resulting in errors.

To fix this, you would set the **Frontend URL** to match the external URL.

#### Steps to Configure Frontend URL:

1. **Log into Keycloak Admin Console**:
    - Go to the admin console, typically at `http://<your-server>:8080/auth/admin/`.

2. **Navigate to Realm Settings**:
    - On the left side, click on **Realm Settings**.

3. **Go to the General Tab**:
    - In the **General** tab, find the field labeled **Frontend URL**.

4. **Set the Frontend URL**:
    - In the **Frontend URL** field, enter the external URL that will be exposed to the public.
      ```
      https://auth.example.com/auth/
      ```
    - This is the URL users will see in their browsers.

5. **Save the Configuration**:
    - Click **Save** to apply the settings.

Now, whenever Keycloak generates a redirect URL for login or logout, it will use `https://auth.example.com/` instead of the internal `http://keycloak.internal:8080/` URL.

### Frontend URL Configuration Example:

- **Internal Keycloak Address**: `http://keycloak.internal:8080/auth/`
- **Proxy (Nginx or Apache)**:
    - The proxy handles requests at `https://auth.example.com/` and forwards them to the internal Keycloak instance.
- **Frontend URL** in Keycloak:
    - Set to `https://auth.example.com/auth/`.

### Nginx Proxy Example Configuration:

If you're using Nginx as a reverse proxy, the configuration might look like this:

```nginx
server {
    listen 443 ssl;
    server_name auth.example.com;

    location / {
        proxy_pass http://keycloak.internal:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

### Example Scenario:
1. **User visits** `https://auth.example.com/realms/demo/protocol/openid-connect/auth`.
2. **Keycloak processes the request** internally at `http://keycloak.internal:8080/`.
3. The user is redirected to `https://auth.example.com/auth` to complete authentication.
4. The external-facing URL is consistent throughout the process.

By setting the **Frontend URL**, you ensure that all external-facing URLs generated by Keycloak use the correct address that clients and users can access.