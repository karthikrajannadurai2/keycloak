To enable debugging on a Keycloak Docker container, you can configure the debug options by setting the `JAVA_OPTS_APPEND` environment variable and exposing the relevant debug port. Here’s a basic example of how to do it:

### Step 1: Run Keycloak with Debug Port Enabled
1. Choose a port to expose for debugging. Keycloak uses Java, so a common debug port is `*:5005`.
2. Add the debug options to the `JAVA_OPTS_APPEND` environment variable.

Here’s a Docker command to run Keycloak with debugging enabled on port `5005`:

```bash
docker run -p 8080:8080 -p 5005:5005 \
    -e JAVA_OPTS_APPEND="-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n" \
    quay.io/keycloak/keycloak:latest
```

### Step 2: Verify Debugging Setup
1. After starting the container, ensure that the debug port `5005` is accessible.
2. Connect a debugger from your IDE (e.g., IntelliJ IDEA or WebStorm) to `localhost:5005`.

### Explanation of Debug Options
- `-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n`
    - `transport=dt_socket`: Uses a socket transport for debugging.
    - `address=*:5005`: Listens on all network interfaces on port `5005`.
    - `server=y`: Runs the JVM in server mode for remote debugging.
    - `suspend=n`: Starts the application without waiting for a debugger connection (set to `y` if you want it to wait).

You should now be able to connect to the running Keycloak instance on `localhost:5005` for debugging.

`JAVA_OPTS_APPEND` is an environment variable used in Docker images for Java applications (like Keycloak) to append additional Java Virtual Machine (JVM) options to the existing ones. It allows you to configure extra settings for the JVM without overwriting the default options set by the container or application.

### How `JAVA_OPTS_APPEND` Works
When you set `JAVA_OPTS_APPEND`, its contents are appended to the `JAVA_OPTS` variable, which is used by the JVM to control aspects like memory limits, garbage collection, and debugging settings. This approach lets you add options (such as debugging flags) without changing the default JVM configuration provided by the application.

### Example Usage
In the Keycloak Docker setup, you can enable debugging by setting `JAVA_OPTS_APPEND` as follows:

```bash
-e JAVA_OPTS_APPEND="-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n"
```

Here, the `-agentlib:jdwp` option is added through `JAVA_OPTS_APPEND` so that debugging options are appended to the JVM’s startup configuration. This helps customize JVM settings specifically for debugging, profiling, or other configurations without altering Keycloak’s default JVM options.