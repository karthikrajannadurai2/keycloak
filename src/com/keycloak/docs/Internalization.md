Keycloak supports **Internationalization (i18n)**, allowing you to provide your users with a localized experience in their preferred language. This can include translating the login pages, emails, error messages, and other user-facing text within Keycloak.

Here’s how you can implement internationalization in Keycloak:

### 1. **Enable Internationalization in Keycloak**

#### Steps:
1. **Login to the Keycloak Admin Console**.
2. Select your **Realm** (e.g., `master`).
3. Go to **Realm Settings** → **Localization**.
4. Enable the option **Internationalization**.
5. Select the **Supported Locales** (languages) that you want to offer to your users (e.g., English, French, Spanish, etc.).
6. Set a **Default Locale**. This is the language that will be used if the user’s preferred language is not supported.
7. Click **Save**.

Keycloak will now display a language selector on the login page, allowing users to choose their preferred language from the list of supported locales.

### 2. **Built-in Localization**

Keycloak comes with built-in localization for several languages. By default, these translations will be used for:

- Login forms
- Error messages
- Email notifications
- Consent screens
- Account management console

Keycloak’s standard themes (e.g., `login`, `email`, `account`) already have translations for various languages.

### 3. **Customizing Localization (Adding Custom Translations)**

If you need to customize the translations or add support for a language that is not already available, you can provide your own translations.

#### Steps to Customize:

1. **Create a Custom Theme**:
   If you haven't already created a custom theme, create one by copying the default Keycloak theme.

    1. Go to the `themes` directory in your Keycloak installation.
    2. Copy the **default theme** into a new directory (e.g., `custom-theme`):
       ```bash
       cp -r themes/base themes/custom-theme
       ```

2. **Add Language Files**:
   Inside your custom theme directory (e.g., `themes/custom-theme`), navigate to the `messages` directory:

   ```bash
   themes/custom-theme/login/messages
   ```

   Add a new properties file for the language you want to customize, such as:

    - `messages_en.properties` for English
    - `messages_fr.properties` for French
    - `messages_es.properties` for Spanish

3. **Edit the Translation File**:
   Inside the properties file, provide key-value pairs for the translations. For example, in `messages_fr.properties` (for French):

   ```properties
   loginTitle = Connexion
   loginUsername = Nom d'utilisateur
   loginPassword = Mot de passe
   loginButton = Se connecter
   errorInvalidUserMessage = Nom d'utilisateur ou mot de passe incorrect
   ```

   The keys must match the existing ones in the default language file (usually English).

4. **Edit the Theme’s `theme.properties`**:
   In your custom theme's `theme.properties` file, ensure that the theme uses the `messages` files you added. For example:

   ```properties
   internationalizationEnabled = true
   supportedLocales = en,fr,es
   ```

5. **Activate the Custom Theme**:
   After editing the theme, activate it by going to the **Admin Console**:

    1. Go to **Realm Settings** → **Themes**.
    2. Under **Login Theme**, select your **custom theme** (e.g., `custom-theme`).
    3. Save the settings.

### 4. **Customizing Email Templates for Localization**

Keycloak also supports internationalization for email templates.

#### Steps to Customize Emails:

1. **Navigate to the Email Template Directory**:
   Keycloak email templates are located in the `email` directory of the theme. For example, in your custom theme:

   ```bash
   themes/custom-theme/email/messages
   ```

2. **Create or Modify Email Translation Files**:
   Add or modify the `messages_xx.properties` files for the email templates in different languages. For example:

    - `messages_en.properties` for English
    - `messages_fr.properties` for French

   Here’s an example of a custom message in `messages_fr.properties`:

   ```properties
   emailSubjectForgotPassword = Réinitialisez votre mot de passe
   emailBodyForgotPassword = Bonjour {username},\n\nPour réinitialiser votre mot de passe, cliquez sur le lien suivant...
   ```

3. **Edit HTML Templates (Optional)**:
   If you need to customize the HTML structure of the emails themselves, you can modify the HTML templates in the `email` directory, which correspond to different email actions (e.g., `login.ftl`, `password-reset.ftl`).

### 5. **Adding Language Switcher on the Login Page**

By default, once internationalization is enabled and multiple languages are supported, Keycloak automatically displays a language selector on the login page. Users can use this selector to change their preferred language. If you want to customize its appearance:

1. Modify the `login.ftl` template in your theme.
2. Locate the block where the language selector is added and adjust the HTML/CSS as necessary.

For example:

```html
<div id="kc-locale">
   <select id="kc-locale-select">
      <#list locales as l>
         <option value="${l.locale}" <#if l.selected>selected</#if>>${l.label}</option>
      </#list>
   </select>
</div>
```

### 6. **Keycloak’s Locale Resolution**

Keycloak resolves the locale (language) in the following order:

1. **User Preferred Language**: If a user has a preferred language set in their account, Keycloak will use that language.
2. **Browser Language**: If no preferred language is set, Keycloak will use the browser’s language settings.
3. **Default Locale**: If neither of the above is set, Keycloak will fall back to the default locale configured in the realm settings.

### 7. **Using External Resources for Translations**

Keycloak supports fetching translations from external sources via the **REST API** if needed. This feature allows you to maintain translation files centrally and load them dynamically.

To achieve this, you can customize the theme templates (e.g., `login.ftl`) to make API calls to external services to retrieve translations dynamically.

### 8. **Customizing Error Messages**

Error messages in Keycloak can also be translated by editing the `messages_xx.properties` files in your custom theme. These error messages include responses for invalid logins, account lockouts, or permission denials.

For example, you can customize an invalid login message in `messages_fr.properties` like so:

```properties
errorInvalidUserCredentialsMessage = Identifiant ou mot de passe incorrect
```

### 9. **Programmatic Language Setting**

If you need to set the language programmatically for users, such as setting the locale based on user profile information, you can use Keycloak’s **Client Locale** feature. This can be done through the **Authentication SPI** to inject logic that sets the user's locale dynamically based on external conditions.

### Conclusion

Keycloak’s internationalization (i18n) features allow for a fully localized user experience, supporting multiple languages for login pages, emails, and error messages. Whether you use built-in translations or provide your own, you can easily configure and customize localization in Keycloak for different use cases. Custom themes make it straightforward to override any part of the user interface and email templates, and programmatic solutions give you the flexibility to dynamically manage language preferences.