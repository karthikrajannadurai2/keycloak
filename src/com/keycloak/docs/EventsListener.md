https://www.youtube.com/watch?v=noWZ7GKHXlk
https://www.youtube.com/watch?v=Rh8LstBPBOE

In Keycloak, a **custom event listener** allows you to listen to specific events that happen within the Keycloak ecosystem, such as user login, logout, registration, password reset, or token issuance. You can then implement custom logic, such as logging the event, sending notifications, or integrating with external systems.

To write a **custom event listener** in Keycloak, you need to implement the Keycloak **EventListenerProvider** interface and deploy it as a Keycloak **SPI (Service Provider Interface)**.

Here’s a step-by-step guide to create and deploy a custom event listener in Keycloak.

### Steps to Create a Custom Event Listener in Keycloak

#### 1. **Set Up Your Development Environment**
You need a Java development environment and Keycloak dependencies to build the custom provider. Make sure you have the following:

- **Java JDK** (version 11 or above)
- **Maven** for building the project
- **Keycloak dependencies**

You can generate a simple Maven project to hold your Keycloak event listener.

#### 2. **Create a Maven Project**

You can create a Maven project using the following command:

```bash
mvn archetype:generate -DgroupId=com.example.keycloak -DartifactId=custom-event-listener -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

This creates a simple project structure for your Keycloak event listener.

#### 3. **Add Keycloak Dependencies to `pom.xml`**

In the `pom.xml` file of your project, add Keycloak dependencies to interact with the Keycloak SPI and APIs:

```xml
<dependencies>
    <dependency>
        <groupId>org.keycloak</groupId>
        <artifactId>keycloak-core</artifactId>
        <version>${keycloak.version}</version>
    </dependency>
    <dependency>
        <groupId>org.keycloak</groupId>
        <artifactId>keycloak-server-spi</artifactId>
        <version>${keycloak.version}</version>
    </dependency>
    <dependency>
        <groupId>org.keycloak</groupId>
        <artifactId>keycloak-server-spi-private</artifactId>
        <version>${keycloak.version}</version>
    </dependency>
</dependencies>
```

Replace `${keycloak.version}` with the version of Keycloak you are using (e.g., `20.0.1`).

#### 4. **Create the Event Listener Class**

You need to implement the `EventListenerProvider` interface and handle the events you're interested in. Create a new class, e.g., `CustomEventListenerProvider.java`, in the `src/main/java/com/example/keycloak` directory:

```java
package com.example.keycloak;

import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;

public class CustomEventListenerProvider implements EventListenerProvider {

    private static final Logger logger = Logger.getLogger(CustomEventListenerProvider.class);

    @Override
    public void onEvent(Event event) {
        // Listen to user events
        logger.info("User event type: " + event.getType());
        switch (event.getType()) {
            case LOGIN:
                logger.info("User logged in: " + event.getUserId());
                break;
            case REGISTER:
                logger.info("User registered: " + event.getUserId());
                break;
            case LOGOUT:
                logger.info("User logged out: " + event.getUserId());
                break;
            default:
                logger.info("Other event: " + event.getType());
        }
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        // Listen to admin events
        logger.info("Admin event type: " + event.getOperationType());
        if (event.getOperationType() == OperationType.CREATE) {
            logger.info("Admin created a resource: " + event.getResourceTypeAsString());
        }
    }

    @Override
    public void close() {
        // Clean up resources if needed
    }
}
```

This listener listens to both **user events** (e.g., login, registration, logout) and **admin events** (e.g., creation of resources).

#### 5. **Create the Provider Factory**

Next, you need to create a factory class that implements `EventListenerProviderFactory`. This factory is responsible for creating instances of your event listener.

```java
package com.example.keycloak;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;

public class CustomEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new CustomEventListenerProvider();
    }

    @Override
    public void init(Config.Scope config) {
        // Initialization if needed
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // Post-initialization logic if needed
    }

    @Override
    public void close() {
        // Clean up resources if needed
    }

    @Override
    public String getId() {
        return "custom-event-listener";
    }
}
```

#### 6. **Create the `META-INF` Services Configuration**

Keycloak uses Java’s **ServiceLoader** mechanism to load custom providers. To register your event listener, you need to create a service configuration file under `src/main/resources/META-INF/services/`.

1. In the `src/main/resources/META-INF/services/` directory, create a file named `org.keycloak.events.EventListenerProviderFactory`.
2. Inside this file, add the fully qualified name of your provider factory:

```
com.example.keycloak.CustomEventListenerProviderFactory
```

#### 7. **Build the Project**

Use Maven to build the JAR file:

```bash
mvn clean package
```

This will generate a JAR file in the `target/` directory (e.g., `custom-event-listener-1.0-SNAPSHOT.jar`).

#### 8. **Deploy the Event Listener to Keycloak**

To deploy your custom event listener:

1. Copy the JAR file to Keycloak’s `standalone/deployments` directory. For example:
   ```bash
   cp target/custom-event-listener-1.0-SNAPSHOT.jar <keycloak-home>/standalone/deployments/
   ```

2. Restart Keycloak.

#### 9. **Enable the Event Listener in the Admin Console**

To use your custom event listener:

1. Go to the **Keycloak Admin Console**.
2. Navigate to **Realm Settings** → **Events**.
3. Under **Event Listeners**, add `custom-event-listener` to the list of enabled listeners.
4. Save the configuration.

#### 10. **Test the Event Listener**

You can now test the custom event listener by triggering events such as user logins or registrations. Check the Keycloak logs, and you should see output from your custom event listener, e.g., logging messages like:

```
INFO  [com.example.keycloak.CustomEventListenerProvider] User event type: LOGIN
INFO  [com.example.keycloak.CustomEventListenerProvider] User logged in: some-user-id
```

### Types of Events You Can Listen To

Keycloak provides the following types of events you can listen to:

1. **User Events**:
    - `LOGIN`: When a user logs in.
    - `LOGOUT`: When a user logs out.
    - `REGISTER`: When a new user registers.
    - `UPDATE_PROFILE`: When a user updates their profile.
    - `RESET_PASSWORD`: When a password reset is requested.

2. **Admin Events**:
    - Operations such as `CREATE`, `UPDATE`, `DELETE` on different resources, e.g., users, clients, roles.

### Conclusion

Creating a custom event listener in Keycloak allows you to monitor and react to various events, such as user logins, logouts, and registrations. By following the steps above, you can build, deploy, and configure your own event listener to integrate Keycloak with external systems, log custom events, or trigger business-specific workflows.