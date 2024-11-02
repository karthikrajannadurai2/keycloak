In Keycloak, **Default Roles** are roles that are automatically assigned to new users during registration. This feature simplifies role assignment by ensuring that all users start with a predefined set of permissions, without requiring manual intervention. It is especially useful when you want all new users to have a basic level of access or specific permissions immediately upon account creation.

### Configuring Default Roles in Keycloak

To set up Default Roles for user registration:

1. **Navigate to Default Roles**:
    - Go to **Realm Settings** in the Keycloak admin console.
    - Select the **Default Roles** tab. This section allows you to specify the roles that all users in the realm will automatically receive upon registration.

2. **Add Default Roles**:
    - Click **Add Role** to choose from the available roles within the realm.
    - You can add single or multiple roles as default roles for new users.

3. **Assigning Composite Roles**:
    - If you need a more complex setup, you can assign composite roles as default roles. Composite roles are roles that include a set of other roles, so when they are assigned, all included roles are automatically applied.
    - This is useful if you have predefined groups of permissions (like `basic_user` that includes `view_profile`, `update_profile`, etc.).

---

### Example Use Cases for Default Roles

1. **Basic Access for New Users**:
    - Suppose you want all newly registered users to have access to general application features, like viewing and updating their profile. You can create a role called `basic_user` and assign it as a default role.
    - This way, every new user will have `basic_user` permissions, granting them access to basic features immediately.

2. **Role-Based Permissions**:
    - In an application with various access levels, you might create roles like `viewer`, `editor`, or `guest`.
    - You can set `viewer` as the default role, so every new user has read-only access, and grant higher permissions manually or through group assignments when required.

3. **Organization-Wide Roles**:
    - If you have roles applicable to all users within a realm (e.g., `member` or `client_user`), assigning these roles as default ensures that every user has organization-level access upon registration.

---

### Additional Options for Role Assignment

In addition to setting Default Roles, Keycloak provides other options for managing roles during user registration:

- **Groups with Default Roles**:
    - Assigning a user to a default **group** can also grant roles, as groups can have their own roles. You can set up a default group for new users that has specific roles assigned, giving them role-based access via group membership.

- **Fine-Tuning with Conditional Role Assignment**:
    - If you need to assign roles based on user attributes, you can create custom user registration flows with conditional role assignment logic using custom authenticators.

### Example Configuration

Let’s say you want all new users to have basic permissions to access the application and view their own profile. You would:

1. Create a role called `basic_access`.
2. Go to **Realm Settings** > **Default Roles** and add `basic_access` as a default role.
3. Optionally, add `basic_access` as a composite role to include other permissions (like `view_profile`).

When users register, they will automatically receive `basic_access`, giving them the ability to log in and use the core application features right away.

---

### Managing Default Roles after Registration

If roles need to be updated after registration:

- **Admin Console**: Admins can manually modify user roles from the **Users** section.
- **Groups**: Moving users into specific groups can also grant additional roles, as groups can have roles that are applied to all members.

Default roles streamline user onboarding in Keycloak by ensuring that all new users have the necessary base permissions right from the start.