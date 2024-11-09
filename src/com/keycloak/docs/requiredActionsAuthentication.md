In Keycloak, **Required Actions** are specific tasks or steps that a user must complete as part of the authentication or login process. These actions are enforced to improve security, gather additional user information, or ensure that the user profile meets certain standards. Administrators can assign these actions to individual users, groups of users, or apply them broadly across a realm.

Here’s an overview of Required Actions in Keycloak and how they work:

---

### Keycloak Default Required Actions

Keycloak provides several built-in Required Actions that can be configured and enforced. Some of the common Required Actions include:

1. **Update Password**
    - Users are prompted to update their password during the next login. This is often used when an account is newly created or when administrators want users to periodically update their passwords for security reasons.

2. **Verify Email**
    - Users receive a verification email with a link they must click to confirm their email address. This action is important to ensure that users are using a valid email and for email-based notifications or account recovery.

3. **Configure OTP (One-Time Password)**
    - Users are prompted to set up a time-based OTP (like Google Authenticator) as a second factor for multi-factor authentication (MFA). This helps secure accounts by adding an additional layer of protection beyond the password.

4. **Update Profile**
    - Users must review and update their profile information, such as name, email, and phone number. This is especially useful if additional user information is required for access control, personalization, or compliance purposes.

5. **Terms and Conditions**
    - Users are presented with terms and conditions (e.g., privacy policy or acceptable use policy) that they must read and accept to proceed. This action is common in organizations where legal or regulatory compliance is important.

6. **Configure WebAuthn**
    - Prompts users to set up WebAuthn authentication, such as a hardware security key or biometric authentication. WebAuthn provides a strong, passwordless authentication method that’s becoming more popular for high-security environments.

---

### Custom Required Actions

Keycloak also supports **custom Required Actions**, which allow administrators to define additional actions specific to the organization’s needs. Examples of custom Required Actions could include:

- **Upload a document** for identity verification or other compliance requirements.
- **Set security questions** as an additional step for account recovery.
- **Accept an organization's privacy policy** if it needs to be separate from terms and conditions.

To create custom Required Actions, you would need to:
1. Develop a custom Java class that implements the `RequiredActionProvider` interface.
2. Deploy the custom provider as a Keycloak extension.
3. Configure the custom action within the Keycloak Admin Console under **Authentication** > **Required Actions**.

---

### Configuring Required Actions

You can configure Required Actions in the Keycloak Admin Console by:

1. Navigating to **Authentication** in your Keycloak realm.
2. Selecting the **Required Actions** tab to view the available actions.
3. Enabling or disabling actions as required.
4. Optionally, you can select **Default Action** for certain required actions to make them mandatory for all new users.

For individual users, you can assign Required Actions from the **Users** section:
1. Go to **Users** and select a user.
2. Under the **Details** tab, scroll to **Required User Actions** and select actions from the dropdown.

---

### How Required Actions Work in the Login Flow

When a user logs in:
1. Keycloak checks the list of Required Actions assigned to that user.
2. If the user has any pending Required Actions, Keycloak will prompt them one-by-one to complete each action before proceeding with normal login.
3. Once all Required Actions are completed, the user is granted access.

Required Actions are typically enforced on the **next login** after they’re assigned, though users can also be prompted to fulfill them in the same session if an admin triggers it manually.

---

### Benefits of Using Required Actions

- **Enhanced Security**: By enforcing actions like OTP configuration or password updates, organizations can strengthen user account security.
- **Data Collection and Compliance**: Required Actions like profile updates or terms and conditions acceptance help organizations gather required user information and ensure compliance.
- **Improved User Experience**: Users can be prompted to complete actions on their own time, reducing the need for support interventions and keeping account information current.

### Summary

Required Actions in Keycloak are versatile tools for enforcing specific security and data management practices as part of the login process. By combining default and custom actions, organizations can create tailored workflows that improve both security and compliance while maintaining a seamless user experience.