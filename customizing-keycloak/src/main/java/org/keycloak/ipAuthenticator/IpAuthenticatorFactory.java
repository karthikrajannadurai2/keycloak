package org.keycloak.ipAuthenticator;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.List;

import static org.keycloak.provider.ProviderConfigProperty.STRING_TYPE;

public class IpAuthenticatorFactory implements AuthenticatorFactory {

    static final String ID = "IpAuthenticator";
    static final IpAuthenticatorProvider ipAuthenticatorProvider = new IpAuthenticatorProvider();
    static final String ALLOWED_IP_ADDRESS_CONFIG = "allowed_ip_address";


    @Override
    public String getDisplayType() {
        logEvents("getDisplayType");
        return "IpAuthenticator";
    }

    @Override
    public String getReferenceCategory() {
        logEvents("getReferenceCategory");
        return "";
    }

    @Override
    public boolean isConfigurable() {
        logEvents("isConfigurable");
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        logEvents("getRequirementChoices");
        return new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.CONDITIONAL,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE

        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        logEvents("isUserSetupAllowed");
        return false;
    }

    @Override
    public String getHelpText() {
        logEvents("getHelpText");
        return "Allows only configured IP address";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        logEvents("getConfigProperties");
        ProviderConfigProperty name = new ProviderConfigProperty();
        name.setType(STRING_TYPE);
        name.setName(ALLOWED_IP_ADDRESS_CONFIG);
        name.setLabel("IP Address from which sign ins are allowed");
        name.setHelpText("Only accepts IP addresses, no CIDR nor masks nor ranges supported");
        ProviderConfigProperty demo = new ProviderConfigProperty();
        demo.setName("DEMO");
        demo.setLabel("HI Hellow");
        demo.setRequired(false);
        demo.setType(STRING_TYPE);
        return List.of(name, demo);
    }

    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
        logEvents("create");
        return ipAuthenticatorProvider;
    }

    @Override
    public void init(Config.Scope scope) {
        logEvents("init");
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        logEvents("postInit");
    }

    @Override
    public void close() {
        logEvents("close");
    }

    @Override
    public String getId() {
        logEvents("getId");
        return ID;
    }

    static void logEvents(String method) {
        System.out.println("IpAuthenticator:---" + method);
    }
}
