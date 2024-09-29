Keycloak provides a set of built-in **email templates** that allow you to send various types of emails to users, such as password reset requests, account verification emails, and action-required notifications. These templates can be customized to fit your organization’s branding and messaging.

### Overview of Email Templates in Keycloak

- **Location of Templates**: Email templates in Keycloak are stored in the `themes` folder within the Keycloak installation directory. Each theme can have its own set of email templates, and these are typically written using **FreeMarker Template Language (FTL)**.

- **Customization**: You can customize the templates for a better user experience or to align them with your company's branding (e.g., adding logos, changing colors, or modifying content).

### Default Email Templates in Keycloak

Keycloak comes with several default email templates that handle common user-related actions. Here are some of the most important ones:

1. **Email Verification (`email-verification.ftl`)**
    - **Purpose**: This email is sent to users when they register and need to verify their email address.
    - **Content**: Contains a verification link for users to confirm their email.
    - **Template Location**:
      ```
      <keycloak-installation>/themes/<your-theme>/email/html/email-verification.ftl
      ```

   **Example Email-Verification Template**:
   ```html
   <html>
   <body>
       <p>Hello ${user.firstName},</p>
       <p>Thank you for registering. Please verify your email address by clicking the link below:</p>
       <p><a href="${link}">Verify Email</a></p>
   </body>
   </html>
   ```

2. **Password Reset (`password-reset.ftl`)**
    - **Purpose**: Sent when users request a password reset.
    - **Content**: Contains a password reset link that takes users to a secure page where they can enter a new password.
    - **Template Location**:
      ```
      <keycloak-installation>/themes/<your-theme>/email/html/password-reset.ftl
      ```

   **Example Password-Reset Template**:
   ```html
   <html>
   <body>
       <p>Hello ${user.firstName},</p>
       <p>We received a request to reset your password. Click the link below to reset it:</p>
       <p><a href="${link}">Reset Password</a></p>
       <p>If you did not request this, please ignore this email.</p>
   </body>
   </html>
   ```

3. **Identity Provider Link Email (`identity-provider-link.ftl`)**
    - **Purpose**: Sent to users when they need to link their account to an external identity provider (e.g., Google, Facebook).
    - **Content**: Contains a link that users can follow to link their external account to their Keycloak account.
    - **Template Location**:
      ```
      <keycloak-installation>/themes/<your-theme>/email/html/identity-provider-link.ftl
      ```

4. **Update Email (`update-email.ftl`)**
    - **Purpose**: Sent when users request to update their email address.
    - **Content**: Contains a link for confirming the change of the email address.
    - **Template Location**:
      ```
      <keycloak-installation>/themes/<your-theme>/email/html/update-email.ftl
      ```

5. **TOTP Setup Email (`totp-setup.ftl`)**
    - **Purpose**: Sent when users need to set up Time-Based One-Time Password (TOTP) for two-factor authentication (2FA).
    - **Template Location**:
      ```
      <keycloak-installation>/themes/<your-theme>/email/html/totp-setup.ftl
      ```

6. **Login Confirmation (`login.ftl`)**
    - **Purpose**: Sent to notify users of successful login attempts, especially useful for monitoring suspicious activity.
    - **Template Location**:
      ```
      <keycloak-installation>/themes/<your-theme>/email/html/login.ftl
      ```

7. **Executing Required Actions (`execute-actions.ftl`)**
    - **Purpose**: Sent when users are required to perform actions, such as updating their profile information or enabling MFA.
    - **Template Location**:
      ```
      <keycloak-installation>/themes/<your-theme>/email/html/execute-actions.ftl
      ```

### How to Customize Email Templates

1. **Locate the Template Directory**:
    - By default, the email templates are located within the theme that Keycloak is using. For example, if you are using the `base` theme, you will find the email templates under:
      ```
      <keycloak-installation>/themes/base/email/html/
      ```

2. **Copy the Templates to a Custom Theme**:
    - If you want to modify the templates without affecting the default ones, it’s a good idea to create a custom theme. You can copy the `email` folder from the default theme (`base`, `keycloak`, etc.) to your custom theme.

3. **Modify the Template**:
    - Open the template you wish to edit and make your changes. You can modify the text, add your company's logo, change the styling, or even add custom data that Keycloak provides.
    - Templates use FreeMarker, so dynamic content (such as user information, links, etc.) can be injected using `${}` syntax. For example:
        - `${user.firstName}` to access the user’s first name.
        - `${link}` for the action link (e.g., password reset link).

4. **Add Custom Branding (CSS, Images, etc.)**:
    - You can add your custom styles and branding to the email templates by modifying the HTML/CSS in the template files.
    - For example, adding a company logo:
      ```html
      <img src="https://example.com/logo.png" alt="Company Logo">
      ```

5. **Test Your Changes**:
    - After making changes to the email templates, test them by triggering the respective email flows (e.g., initiate a password reset or account verification) to see how the email looks and ensure all links and dynamic content work as expected.

### Example: Customizing the Password Reset Email Template

Here’s an example of how you could customize the password reset email template to include a company logo and modify the message:

```html
<html>
<head>
    <style>
        .header {
            text-align: center;
            background-color: #f7f7f7;
            padding: 10px;
        }
        .content {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .footer {
            margin-top: 20px;
            text-align: center;
            font-size: 12px;
            color: #888;
        }
    </style>
</head>
<body>
    <div class="header">
        <img src="https://example.com/logo.png" alt="Company Logo" />
    </div>
    <div class="content">
        <p>Hello ${user.firstName},</p>
        <p>It looks like you requested a password reset. Click the button below to reset your password:</p>
        <p><a href="${link}" style="background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none;">Reset Password</a></p>
        <p>If you did not request this, please ignore this email or contact support.</p>
    </div>
    <div class="footer">
        <p>Thank you for using our service!</p>
    </div>
</body>
</html>
```

### Troubleshooting Common Issues
1. **Emails Not Sending**: Make sure that your SMTP server settings are configured correctly in the Keycloak Admin Console under **Realm Settings > Email**.
2. **Template Changes Not Applied**: Ensure you are editing the correct theme that is active for your realm.
3. **Dynamic Data Not Rendering**: Verify that you are using the correct placeholders for FreeMarker variables (e.g., `${link}`, `${user.firstName}`).

---

### Summary

Keycloak's email templates provide a customizable way to manage user communication for actions like password resets, account verification, and required actions. By customizing these templates, you can align them with your organization’s branding and user experience standards. Keycloak makes it relatively easy to modify and manage these templates using the FreeMarker template language.