In Keycloak, the **"From"** field in an email template defines the **sender's email address** that appears in the email headers when users receive emails (like password resets, verification emails, etc.). This field can be configured globally within the Keycloak Admin Console under the **Email settings** for each realm.

### Configuring the "From" Field in Keycloak Emails

You can configure the "From" field in Keycloak emails by setting the **SMTP server** configuration, which includes specifying the sender's email address and display name.

#### Steps to Configure the "From" Field:

1. **Log into Keycloak Admin Console**:
    - Go to the Keycloak Admin Console (e.g., `http://<your-domain>:8080/auth/admin/`).

2. **Navigate to Realm Settings**:
    - On the left-hand menu, click on **Realm Settings**.

3. **Open the Email Tab**:
    - Under **Realm Settings**, click on the **Email** tab.

4. **Set the "From" Email Address and Display Name**:
    - **From**: This is the email address that will appear as the sender in the emails (e.g., `noreply@example.com`).
    - **From Display Name**: This is the name that will appear alongside the email address (e.g., `Example Support Team`).

   Example configuration:
    - **From**: `noreply@example.com`
    - **From Display Name**: `Example App`

   These settings will determine the sender details for all email notifications that Keycloak sends for this realm.

   Example setup:
   ```
   From: noreply@example.com
   From Display Name: Example App
   ```

5. **Save the Configuration**:
    - After filling in the details, click **Save** to apply the changes.

### Using the "From" Field in Email Templates

Keycloak templates themselves do not typically include a direct configuration for the "From" field within the template files (`.ftl`). Instead, this is handled globally at the realm level via the SMTP configuration.

However, the configured "From" address and display name will automatically be used when emails are sent out, so it appears in the user's inbox as coming from the specified sender (e.g., `noreply@example.com`).

### Example

For a password reset email:
- **From**: `noreply@example.com`
- **From Display Name**: `Example App`

When a user requests a password reset, they will receive an email that looks something like this in their inbox:

**Sender**: Example App `<noreply@example.com>`  
**Subject**: Password Reset Request

This ensures the email appears professional and matches your application's branding.

### Additional "From" Field Configurations

1. **Reply-To** Address:
    - Keycloak also allows you to configure a **Reply-To** address. This is the email address that users can reply to (e.g., `support@example.com`), which can differ from the "From" address. This setting can be configured in the same **Email** settings tab.
    - **Reply-To Display Name**: Similar to the "From" field, you can set a custom display name for the reply-to address.

2. **Example SMTP Configuration for Gmail**:
    - **Host**: `smtp.gmail.com`
    - **Port**: `587` (for TLS)
    - **From**: `noreply@yourdomain.com`
    - **From Display Name**: `Your App`
    - **Reply-To**: `support@yourdomain.com`
    - **Reply-To Display Name**: `Your App Support`
    - **Username**: `your-gmail-username`
    - **Password**: `your-gmail-password`
    - **Encryption Method**: `TLS`

### Testing the "From" Field

Once you've configured the "From" field and saved your changes, it's important to test whether the emails are being sent correctly with the expected sender information.

- You can trigger an email flow by:
    - Registering a new user to check the email verification flow.
    - Initiating a password reset to test the reset email.

You can also use the **Test Connection** button in the **Email** settings to send a test email and verify that the "From" field is configured properly.

### Summary

In Keycloak, the **"From"** field for email notifications is configured globally in the **Email settings** for each realm and includes the sender's email address and display name. This field determines how the emails sent by Keycloak appear in users' inboxes. While the email templates themselves do not directly control the "From" field, they rely on the SMTP server configuration set in the Keycloak Admin Console.