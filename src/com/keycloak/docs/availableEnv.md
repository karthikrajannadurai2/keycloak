Keycloak provides many environment variables for server configuration, enabling customization without modifying configuration files directly. These variables follow the format:

```
KC_<CONFIGURATION_OPTION>
```

Here’s a detailed list of commonly used environment variables in Keycloak, categorized by functionality.

---

### **1. General Server Configuration**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_DB`                                   | Specifies the database vendor (e.g., `postgres`, `mysql`, `mariadb`, `oracle`, etc.).       |
| `KC_HOSTNAME`                             | Sets the public hostname of the Keycloak server.                                            |
| `KC_HOSTNAME_STRICT`                      | Enables/disables strict hostname checking. (`true` or `false`)                             |
| `KC_HTTP_PORT`                            | HTTP port to listen on (default is `8080`).                                                |
| `KC_HTTPS_PORT`                           | HTTPS port to listen on (default is `8443`).                                               |
| `KC_LOG_LEVEL`                            | Sets the server log level (e.g., `INFO`, `DEBUG`, `WARN`).                                  |
| `KC_PROXY`                                | Configures proxy mode (e.g., `edge`, `reencrypt`, `passthrough`).                           |

---

### **2. Database Configuration**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_DB_URL`                               | JDBC URL for the database connection.                                                      |
| `KC_DB_USERNAME`                          | Database username.                                                                         |
| `KC_DB_PASSWORD`                          | Database password.                                                                         |
| `KC_DB_SCHEMA`                            | Database schema (default for some databases).                                              |
| `KC_DB_URL_HOST`                          | Database host (e.g., `localhost`).                                                         |
| `KC_DB_URL_PORT`                          | Database port (e.g., `5432` for PostgreSQL).                                               |
| `KC_DB_URL_DATABASE`                      | Database name.                                                                             |

---

### **3. Security and Authentication**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_KEYSTORE`                             | Path to the keystore file for HTTPS configuration.                                          |
| `KC_KEYSTORE_PASSWORD`                    | Password for the keystore.                                                                 |
| `KC_KEY_PASSWORD`                         | Password for the key within the keystore.                                                  |
| `KC_HTTPS_CERTIFICATE_FILE`               | Path to the HTTPS certificate file.                                                        |
| `KC_HTTPS_CERTIFICATE_KEY_FILE`           | Path to the HTTPS certificate key file.                                                    |

---

### **4. SPI Configuration**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_SPI_<SPI_NAME>_<CONFIG_OPTION>`       | SPI-specific configuration. Replace `<SPI_NAME>` and `<CONFIG_OPTION>` accordingly.         |
| Example:                                  | `KC_SPI_OTP_APPLICATION_FREEOTP_ENABLED=true` (if supported).                               |

---

### **5. Caching**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_CACHE`                                | Cache type (e.g., `local`, `distributed`, or `ispn`).                                       |
| `KC_CACHE_STACK`                          | Defines the cache stack (e.g., `ispn`).                                                    |
| `KC_CACHE_CONFIG_FILE`                    | Path to a custom Infinispan cache configuration file.                                       |

---

### **6. Clustering**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_CLUSTER`                              | Clustering mode (e.g., `standalone`, `kubernetes`, or `jboss`).                             |
| `KC_CLUSTER_STACK`                        | Specifies the clustering stack (e.g., `tcp`, `udp`).                                       |

---

### **7. Theme Customization**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_THEME`                                | Sets the default theme for Keycloak.                                                       |
| `KC_THEME_CACHE_ENABLED`                  | Enables/disables theme caching. (`true` or `false`)                                        |
| `KC_THEME_STATIC_MAX_AGE`                 | Sets the maximum age for static resources.                                                 |

---

### **8. Deployment-Specific Variables**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_METRICS_ENABLED`                      | Enables metrics for Prometheus.                                                            |
| `KC_HEALTH_ENABLED`                       | Enables health checks.                                                                     |
| `KC_FEATURES`                             | Enables experimental or preview features (e.g., `scripts`, `admin-fine-grained-authz`).     |

---

### **9. Logging**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_LOG_LEVEL`                            | Configures the log level (e.g., `INFO`, `DEBUG`, `WARN`).                                   |
| `KC_LOG_CONSOLE_ENABLED`                  | Enables/disables console logging.                                                          |
| `KC_LOG_CONSOLE_OUTPUT`                   | Defines the console output format (`DEFAULT`, `JSON`).                                      |

---

### **10. Miscellaneous**
| Environment Variable                      | Description                                                                                 |
|-------------------------------------------|---------------------------------------------------------------------------------------------|
| `KC_TRANSACTION_XA_ENABLED`               | Enables XA transactions for the database.                                                  |
| `KC_MAIL_FROM`                            | Sets the default sender email for notifications.                                           |
| `KC_MAIL_HOST`                            | Mail server hostname.                                                                      |
| `KC_MAIL_PORT`                            | Mail server port.                                                                          |
| `KC_MAIL_USER`                            | Mail server username.                                                                      |
| `KC_MAIL_PASSWORD`                        | Mail server password.                                                                      |

---

### **How to List All Active Configuration Variables**

You can list all active Keycloak configuration variables with the following command:
```bash
bin/kc.sh show-config
```

This will output all default and overridden configurations, including those defined via environment variables.

Let me know if you have specific configurations in mind or need more details!