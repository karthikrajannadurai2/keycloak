**User Federation** and **Single Sign-On (SSO)** are both key concepts in identity and access management (IAM), but they serve different purposes and have distinct use cases. Here’s a breakdown of each concept and their differences:

### What is User Federation?
**User Federation** refers to integrating external user directories or identity sources (like LDAP or Active Directory) with an IAM system so that the system can authenticate users and access user data from these sources. This approach allows applications to utilize a centralized user database without needing to replicate or store users in each application.

#### Key Characteristics of User Federation:
1. **Centralized User Management**: User Federation relies on a central source (like an LDAP or Active Directory) for user information and authentication.
2. **No Data Duplication**: User data is typically not copied to the IAM system. Instead, the IAM system queries the user directory directly.
3. **Integration with External Directories**: User Federation connects to external user stores (LDAP, Active Directory, custom databases), making it a bridge between applications and existing user management systems.
4. **Automatic Synchronization**: Many IAM systems can synchronize user attributes periodically, ensuring data in the IAM system remains up-to-date with the external user directory.

#### Use Case:
For example, if an organization already has users stored in an LDAP directory, User Federation would allow Keycloak to authenticate those users without creating new accounts in Keycloak. This provides a seamless way for applications using Keycloak to authenticate against the organization’s LDAP without duplicating user records.

### What is Single Sign-On (SSO)?
**Single Sign-On (SSO)** is a feature that allows users to log in once and access multiple applications without re-entering their credentials. SSO enables seamless access to various applications by using a common authentication system, often implemented using identity standards like OAuth2, OpenID Connect (OIDC), or SAML.

#### Key Characteristics of SSO:
1. **Single Authentication Session**: The user authenticates once, and a session is created that can be used across multiple applications.
2. **Token-Based Access**: SSO relies on tokens (such as JWT tokens in OAuth2/OIDC) that allow applications to verify the user’s identity without asking for credentials repeatedly.
3. **Federated Access Across Applications**: Once authenticated, the user can access different applications or services within a network, often spanning multiple domains.
4. **Improved User Experience**: SSO minimizes login friction by reducing the number of times users need to enter credentials, leading to a better experience and higher security.

#### Use Case:
For instance, with SSO enabled, an employee could log in to a central authentication portal, such as Keycloak, and then access other internal applications like an HR portal, CRM, or email without re-authenticating. SSO makes it easier for users to move between applications while maintaining a consistent authentication state.

### Key Differences Between User Federation and SSO

| Feature                 | User Federation                                     | Single Sign-On (SSO)                               |
|-------------------------|-----------------------------------------------------|----------------------------------------------------|
| **Purpose**             | Integrate with external user directories (e.g., LDAP, AD) | Enable seamless access to multiple applications |
| **Authentication**      | Authenticates against an external source            | Authenticates once, then grants access to multiple apps |
| **User Data Location**  | External directory (e.g., LDAP, AD)                 | User may be in a centralized IAM or federated sources |
| **Session Management**  | Depends on the external source and IAM settings     | Maintains a single session across applications     |
| **Typical Use Cases**   | Syncing with LDAP/AD, user lookups in external systems | Accessing multiple applications after a single login |
| **User Experience**     | Transparent for end users but not user-facing       | Enhanced, as users sign in only once               |
| **Protocols**           | LDAP, custom connectors                             | OAuth2, OIDC, SAML, Kerberos                       |

### How They Work Together in an IAM Solution like Keycloak
- **User Federation** can provide access to users stored in an external directory (like LDAP) without requiring users to be migrated or duplicated into Keycloak.
- **SSO** allows those users, once authenticated through Keycloak, to access multiple Keycloak-enabled applications in the organization with a single login.

### Example: Using Both Together
1. A company has employees stored in an Active Directory (AD) instance. Through **User Federation**, Keycloak connects to this AD, allowing it to authenticate users based on their AD credentials.
2. When an employee logs into Keycloak (authenticating against AD), they receive a session token.
3. **SSO** enables the employee to use this session token to access multiple internal applications (e.g., intranet, project management tool, CRM) without needing to log in again.

In summary:
- **User Federation** solves the problem of user data integration with external sources.
- **SSO** solves the problem of providing seamless access across multiple applications once a user is authenticated.