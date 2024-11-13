To verify if Keycloak has successfully picked up and loaded a custom provider (such as a custom SPI or extension), you can check several places, such as the logs, Keycloak’s admin console, or by using the Keycloak CLI. Here are the recommended steps:

### 1. Check Keycloak Logs
The easiest way to confirm if Keycloak has loaded the custom provider is by checking the logs:

1. **Run Keycloak with Log Level Set to Debug**:
   Set the log level to debug to see detailed information about which providers Keycloak is loading.

   ```bash
   docker run -p 8080:8080 -e KEYCLOAK_LOGLEVEL=DEBUG quay.io/keycloak/keycloak:latest
   ```

2. **Check for Log Entries Related to Your Custom Provider**:
   Look for log messages indicating that Keycloak is loading the provider. Typical log entries might look like:

   ```
   INFO  [org.keycloak.services] (ServerService Thread Pool -- 67) Loaded provider <your-provider-name>
   ```

3. **Check for Errors**:
   If there are issues with loading the provider, you might see errors in the logs related to class loading or missing dependencies. Fixing these errors will often resolve the issue.

### 2. Check the Admin Console for Custom Provider Configuration
Once Keycloak is running, you can verify if the custom provider is available through the Admin Console (assuming the provider has a configuration entry):

1. **Log in to Keycloak Admin Console** at `http://localhost:8080/admin` (default credentials are `admin/admin` unless otherwise configured).
2. **Navigate to the Relevant Section**:
   - For custom authenticators, go to **Authentication > Flows** to check if the custom provider is listed as an option in the flows.
   - For other providers, like a custom user storage SPI, check **User Federation** to see if it appears as an available provider.

### 3. Use Keycloak CLI to List Providers
If you're running Keycloak in a way that allows CLI access, you can use the Keycloak CLI to list providers.

1. **Open a Terminal in the Running Keycloak Container**:
   ```bash
   docker exec -it <container_id> /bin/bash
   ```

2. **Use the CLI to List Providers**:
   Keycloak’s command-line interface can list providers if it’s enabled. For example, run:

   ```bash
   kc.sh show-spis
   ```

This will list all Service Provider Interfaces (SPIs) currently available in Keycloak, including your custom provider if it was loaded correctly.

### 4. Confirm Provider is Functional
If your provider adds specific functionality (e.g., a new authentication flow or a custom endpoint), you can test this functionality directly in Keycloak to confirm it's working as expected.

### Summary
Using these steps, especially checking the logs and admin console, should confirm whether Keycloak has successfully loaded your custom provider. If there are issues, reviewing the logs usually reveals what went wrong during the startup process.