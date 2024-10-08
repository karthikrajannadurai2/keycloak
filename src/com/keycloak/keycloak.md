**1. Realm Settings :**

* Realm is the isolated environment where we can store the group of clients and their settings together.

**2. Clients :**
* Clients are the applications that interact with the keycloak (Auth server) for authentication.
  * client list :- list of clients
  * initial access token :- initial access token generated by keycloak to create new client. (Refer clientRegisteration.md)
  * Client Registration Policy :- policy or condition for the client registration. (Refer clientRegisteration.md)
  * Initial access token is required only for the authenticated client registration.
  * Anonymous policy is for the client registration with the authentication (without initial access token).
  * There is postman collection is there under **Keycloak** (Authenticated_Client_registeration_Initial_access_token).

**3. Client Scope :**
* Clients scope is the one that gets added to the access token or ID token. (**Scope :-** Opportunity to do something.)
* scope is like a allowed actions, that gets added in the tokens.
  * Create the client scope name.
  * client scope comes from the user attributes or default predefined scope is there.
  * User attribute are extra info of the user.
  * Under scope in the client scope, there is a option to assign role to it, so that only particular users in the role can access the client scope.
  * **Assigned type** will be default (by default it is applied to client), Optional (it is sent, if it is required in Oauth2.0 scope (see in postman, in OAUTH authorization there will be scope)).
  
**4. Realm Roles :-**
 * Realm roles are the roles applicable only for the particular realm.

**5. Users:-**
  * Details Tab:-
    * ID :- unique id for the user
    * Created At :- time of user creation
    * Required user actions :- once the users logged in, action to be performed. This actions comes from the **Required Actions** Tab, in Authentication. (we can configure it there). 
    * Email Verified :- email is verified or not.
    * General is normal things, you can understand.
  * Attributes Tab :-
    * We can add the extra info of the user here in this tab.
  * Credentials Tab :- 
    * Credentials tab, has password related things.
    * Temporary (User need to change the password on login).
  * Role Mapping :-
    * Assigning role to the user.
    * Roles can be private to the client and if we create role in realm, it is globally available.
    * In role mapping, Filter by clients (in clients we can have separate roles, we can assign it here to the user.)and Filter by realm roles (global roles in realm).
  * Groups :-
    * Groups is the common area, where related users and roles are grouped.
  * Consents :-
    * The consents will only be recorded when users try to access a client that is configured to require consent. In that case, users will get a consent page which asks them to grant access to the client.
  * Identity Providers :-
    * **TODO**
  * Sessions :- 
    * Active login sessions will be displayed here.
    
**6. Groups:-**
  * Groups can be created here.

**7. Sessions:-**
  * Sessions are sessions of users in this realm and the clients that they access within the session.

**8. Events:-**
  * Events related to the login will be displayed here.

**9. Endpoint Details :-**
  *  {keycloak_base_url}/realms/{realm_name}/.well-known/openid-configuration

**10. Realm Settings :-**
  * General Tab :
      * Realm ID : Id for the particular realm.
      * Display Name : name of the realm.
      * HTML Display Name : Html of the name in the login page.
      * Frontend Url : (See in notes).
      * Require SSL : Is HTTPS required? 'None' means HTTPS is not required for any client IP address. 'External requests' means localhost and private IP addresses can access without HTTPS. 'All requests' means HTTPS is required for all IP addresses.
      * Unmanaged Attributes : Unmanaged attributes are user attributes not explicitly defined in the user profile configuration. By default, unmanaged attributes are `Disabled` and are not available from any context such as registration, account, and the administration console. By setting `Enabled`, unmanaged attributes are fully recognized by the server and accessible through all contexts, useful if you are starting migrating an existing realm to the declarative user profile and you don't have yet all user attributes defined in the user profile configuration. By setting `Only administrators can write`, unmanaged attributes can be managed only through the administration console and API, useful if you have already defined any custom attribute that can be managed by users but you are unsure about adding other attributes that should only be managed by administrators. By setting `Only administrators can view`, unmanaged attributes are read-only and only available through the administration console and API.
        * **By enabling this only the attributes tab will appear in the users options.**
      * User - managed Access : If enabled, users are allowed to manage their resources and permissions using the Account Management UI.
      * Endpoints : Shows the configuration of the Service Provider endpoint.
  * Login Tab : 
      * Easily understandable see ? mark near it.
  * Email Tab :
      * Email template related settings.
  * Themes Tab :
       * Customizing themes section. (see md document for more info).
  * Keys Tab :
       * Cryptographic keys for encryption and signing purpose in Oauth specification in keycloak.
  * Events Tab :
       * Configure event listener.
       * Configure the user events like we need login event or not, like that here.
       * configure the admin events here. https://www.keycloak.org/server/logging **REFER**!!!.



**Some Resources are present in this slide also :**

<span style="color:red"> refer this slide :-- <a>https://docs.google.com/presentation/d/1E1wdsMe-Bqb2JFVA2HI0rHfLLdVR7BCjJ-tpxKLeU4Y/edit</a> </span>