In Keycloak’s **User Profile** settings, **annotations** are used to provide additional metadata and customization options for each attribute in a user profile. Annotations allow administrators to define specific characteristics or rules for user attributes, enhancing control over data collection and presentation. This feature helps customize the behavior of profile attributes, making it possible to implement more advanced requirements such as conditional visibility, tooltip hints, and validation messages.

### Key Annotations in Keycloak’s User Profile

Annotations in Keycloak’s User Profile generally fall into a few categories:

1. **UI Annotations**  
2. **Validation Annotations**  
3. **Behavioral Annotations**  
4. **Conditional Annotations**  

---

### 1. **UI Annotations**

UI annotations control how an attribute is displayed in the user interface. These can be used to improve user experience by providing guidance or grouping related fields.

- **`displayName`**: Sets the label for the attribute as it appears in the UI. For example, `displayName: "Phone Number"` will label the attribute in the profile as "Phone Number".
  
- **`tooltip`**: Provides additional guidance for the attribute, such as examples or explanations, which appear as a tooltip. For example, `tooltip: "Enter your contact number in the format +1-234-567-890"`.

- **`group`**: Defines the group in which the attribute will appear, which is helpful for organizing fields in the profile (e.g., "Personal Information" or "Contact Details").

- **`isHtmlInputType`**: Specifies the type of HTML input used for the attribute, such as `text`, `email`, or `password`. This can ensure fields are rendered with appropriate UI controls (e.g., `isHtmlInputType: "email"` for email fields).

---

### 2. **Validation Annotations**

Validation annotations allow administrators to enforce specific rules or constraints on attribute values. These annotations ensure data integrity and guide users to enter valid information.

- **`validation`**: Specifies the type of validation to apply, such as regex patterns or length restrictions. For example, `validation: {"regex": "^\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{4}$"}` for a phone number format.

- **`min` and `max`**: Sets minimum and maximum values for numeric or length-based attributes. For example, for an age field, `min: 18` and `max: 65` restricts entries to values between 18 and 65.

- **`options`**: Defines a set of acceptable values for an attribute, often used for dropdown lists. For example, `options: ["HR", "Engineering", "Sales", "Marketing"]` would create a dropdown list for selecting a department.

- **`validationMessage`**: Provides a custom message that appears if validation fails, guiding the user to correct their input. For example, `validationMessage: "Please enter a valid phone number in the format +1-234-567-890"`.

---

### 3. **Behavioral Annotations**

Behavioral annotations define how an attribute behaves in specific scenarios, such as whether it is read-only, required, or conditional.

- **`required`**: Marks the attribute as mandatory. For instance, `required: true` ensures the field must be completed before submission.

- **`readOnly`**: When set to `true`, this attribute cannot be modified by the user. This is useful for fields that should be populated and maintained only by administrators, like `employee_id`.

- **`adminOnly`**: Limits visibility and edit permissions of an attribute to administrators only. For example, `adminOnly: true` would make an attribute like `user_role` visible only in the admin console.

- **`defaultValue`**: Provides a default value for an attribute when the profile is created, which can be overwritten if needed. For instance, `defaultValue: "USA"` for a country field.

---

### 4. **Conditional Annotations**

Conditional annotations control attribute behavior based on other conditions, such as user roles, other attribute values, or the context in which the profile is accessed. This feature is especially useful for conditional visibility and access control.

- **`visibleWhen`**: Configures when an attribute is visible based on certain conditions. For instance, `visibleWhen: { "role": "admin" }` could make an attribute visible only to users with the `admin` role.

- **`editableWhen`**: Controls when an attribute is editable based on specific conditions. For example, `editableWhen: { "attribute": "isActive", "value": true }` might allow editing only if another attribute (`isActive`) is `true`.

- **`requiredWhen`**: Specifies conditions under which an attribute becomes required. For example, `requiredWhen: { "attribute": "country", "value": "USA" }` could make a `state` field required if the `country` attribute is set to "USA".

---

### Practical Example of Annotations in Keycloak User Profile

Let’s walk through an example configuration for a custom user profile with annotated attributes:

```json
{
  "attributes": [
    {
      "name": "phone_number",
      "displayName": "Phone Number",
      "type": "string",
      "required": true,
      "validation": {
        "regex": "^\\+\\d{1,3}-\\d{3}-\\d{3}-\\d{4}$"
      },
      "validationMessage": "Please enter a valid phone number in the format +1-234-567-890",
      "tooltip": "Enter your phone number with country code",
      "group": "Contact Information",
      "isHtmlInputType": "tel"
    },
    {
      "name": "department",
      "displayName": "Department",
      "type": "list",
      "required": true,
      "options": ["HR", "Engineering", "Sales", "Marketing"],
      "group": "Employment Details",
      "adminOnly": true
    },
    {
      "name": "isActive",
      "displayName": "Account Active",
      "type": "boolean",
      "required": false,
      "defaultValue": true,
      "readOnly": true
    },
    {
      "name": "state",
      "displayName": "State",
      "type": "string",
      "group": "Contact Information",
      "requiredWhen": {
        "attribute": "country",
        "value": "USA"
      }
    }
  ]
}
```

#### Explanation of the Example:

- **`phone_number`**: A required attribute for a phone number with a tooltip for guidance and regex validation to ensure a proper phone format.
- **`department`**: A list attribute restricted to a set of values (departments) that is visible only to administrators.
- **`isActive`**: A read-only attribute that defaults to `true`, which might be used to indicate whether a user account is active.
- **`state`**: A conditional attribute that becomes required only if the `country` attribute is set to "USA".

### Summary of Annotations in Keycloak

Annotations provide a flexible way to customize how user profile attributes behave, appear, and validate data. They allow for creating dynamic and role-based profile experiences that adapt to user context, helping organizations meet complex data collection and compliance requirements while ensuring a smooth user experience.