package org.keycloak.customRequiredAction;

import org.keycloak.Config;
import org.keycloak.authentication.RequiredActionFactory;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class PhNoCustomRequiredActionFactory implements RequiredActionFactory {

    @Override
    public String getDisplayText() {
        return "Phone Number";
    }

    @Override
    public RequiredActionProvider create(KeycloakSession keycloakSession) {
        return new PhNoCustomRequiredActionProvider();
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "Phone_Number";
    }
}
