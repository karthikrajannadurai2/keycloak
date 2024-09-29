Keycloak allows for extensive **theme customization** to tailor the look and feel of the user-facing pages such as login, registration, account management, and emails. Customizing a theme enables you to align Keycloak with your organization's branding by modifying everything from simple CSS changes to more complex layouts, JavaScript functionality, and email templates.

### Types of Themes in Keycloak

Keycloak provides the ability to customize the following types of themes:

1. **Login Theme**: Used for login, registration, account recovery, and other identity-related pages.
2. **Account Theme**: Applies to the account management console where users can update their profiles, manage passwords, and view sessions.
3. **Admin Console Theme**: Customizes the appearance of the admin console that administrators use.
4. **Email Theme**: Allows customization of the email templates for notifications like password resets, account verification, etc.
5. **Welcome Theme**: Used for the welcome page when Keycloak is first installed.

### Steps to Create and Customize a Theme in Keycloak

#### 1. **Set Up Keycloak for Theme Customization**
- Locate the Keycloak installation directory on your system. Inside the `themes` folder, Keycloak stores its default themes.
- The path typically looks like this:
  ```
  <keycloak-home>/themes/
  ```

#### 2. **Create a Custom Theme**
- To create a custom theme, you can start by copying one of the default themes (such as `keycloak` or `base`) and modify it.
- Example:
  ```
  cp -r <keycloak-home>/themes/keycloak <keycloak-home>/themes/my-custom-theme
  ```
- This creates a new folder called `my-custom-theme`, which you can customize.

#### 3. **Structure of a Theme**
A Keycloak theme is organized in subfolders according to the type of customization you're doing. Here’s the basic structure:

- **login/**: Contains templates and resources for login pages.
- **account/**: Templates for user account management pages.
- **admin/**: Templates for the admin console.
- **email/**: Email templates for user notifications.
- **resources/**: CSS, JS, and image files.

Example structure:
   ```
   my-custom-theme/
   ├── login/
   ├── account/
   ├── admin/
   ├── email/
   ├── resources/
   │   ├── css/
   │   ├── img/
   │   └── js/
   └── theme.properties
   ```

#### 4. **Customize Theme Files**

- **Templates (FTL)**:
  Templates in Keycloak are written in **FreeMarker Template Language (FTL)**. You can modify these templates to adjust the structure of the HTML.
  For example, you can customize the login form by editing the `login.ftl` file inside the `login` directory.

- **CSS and JS**:
  Add your custom styles in the `resources/css` folder. You can modify existing styles or include a completely new design.
  You can also add custom JavaScript in the `resources/js` folder to add interactivity to the pages.

- **Images and Logos**:
  Place your logos and other images in the `resources/img` folder. You can update the templates to reference these custom images.

**Example: Changing the Logo on the Login Page**
1. Place your logo file in `resources/img/logo.png`.
2. Open the `login.ftl` file in the `login/` directory.
3. Modify the `<img>` tag to reference your custom logo:
   ```html
   <img src="${url.resourcesPath}/img/logo.png" alt="My Custom Logo">
   ```

#### 5. **Theme Configuration File (theme.properties)**
- The `theme.properties` file is used to define the properties of your theme. It determines which parts of the theme are customized and contains basic metadata about the theme.
- Example of `theme.properties` for a login theme:
  ```
  parent=keycloak
  internationalizationEnabled=true
  ```
- The `parent` property indicates which theme your custom theme extends from (in this case, `keycloak`), allowing you to only override specific parts of the base theme.

#### 6. **Activate Your Custom Theme**
After you've made your changes, you need to activate your custom theme in the Keycloak Admin Console:

1. **Login to the Admin Console**.
2. Go to **Realm Settings** → **Themes**.
3. Under **Login Theme**, select your custom theme (e.g., `my-custom-theme`) from the dropdown.
4. Click **Save**.

You can also apply custom themes to the account management pages, the admin console, and email templates by selecting them in their respective dropdowns.

#### 7. **Customizing Specific Pages**
Each theme type (login, account, email, etc.) has specific pages and templates that you can customize. Here are some common examples:

- **Login Pages**: Customize `login.ftl` to modify the login page, `login-reset-password.ftl` for the password reset page, etc.
- **Account Pages**: Modify `account.ftl` and other files in the `account` directory to change the look of the user profile and account management pages.
- **Emails**: Modify the `*.ftl` files in the `email` directory to customize emails such as account verification, password reset, etc.

### Example of Theme Customization

Suppose you want to change the background color of the login page and add your company logo. Here's how you would do it:

1. **Edit the CSS**:
    - Create a custom CSS file in `my-custom-theme/resources/css/custom.css` with the following content:
      ```css
      body {
          background-color: #f4f4f4;
      }
      .kc-logo {
          background-image: url('/resources/img/my-logo.png');
      }
      ```

2. **Modify the Login Template**:
    - In `my-custom-theme/login/login.ftl`, add a reference to your custom CSS:
      ```html
      <link rel="stylesheet" href="${url.resourcesPath}/css/custom.css">
      ```

3. **Place the Logo**:
    - Place your custom logo in `my-custom-theme/resources/img/my-logo.png`.

4. **Activate the Theme**:
    - Go to **Realm Settings** → **Themes**, select your theme for **Login**, and save the changes.

Now, when users visit your login page, they will see your custom background color and logo.

### Advanced Theme Customizations

1. **Internationalization (i18n)**:
    - Keycloak supports **internationalization** for themes, allowing you to create localized versions of your templates.
    - To enable internationalization, set `internationalizationEnabled=true` in your `theme.properties` file.
    - Then, you can create language-specific `.properties` files (e.g., `messages_en.properties`, `messages_es.properties`) for translations.

2. **Custom JavaScript**:
    - You can add custom JavaScript files for additional functionality, such as form validation, interactivity, or analytics tracking. Place your JS files in the `resources/js` folder and reference them in your templates.

3. **Dynamic Content with FreeMarker**:
    - Keycloak uses the **FreeMarker** template engine, which allows for dynamic content. You can use conditionals, loops, and access variables (such as user information) to build more complex and dynamic pages.
    - For example, you could conditionally show different content based on user roles or authentication context.

### Summary

Customizing themes in Keycloak allows you to fully personalize the user-facing aspects of your identity management system. By modifying templates, CSS, images, and email templates, you can integrate your organization’s branding and provide a seamless user experience. The structure is flexible, allowing you to make minor visual tweaks or significant functional changes to Keycloak’s appearance and behavior.

https://www.youtube.com/watch?v=idMolacFVjI
https://storybook.keycloakify.dev/?path=/story/login-login-ftl--default
https://www.youtube.com/watch?v=PhNE-3EwwP8
https://www.youtube.com/watch?v=rcVf-xjNqvc