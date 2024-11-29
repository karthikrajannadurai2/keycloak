package org.keycloak.smsAuthentication;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.List;

// https://github.com/dasniko/keycloak-2fa-sms-authenticator/blob/main/src/main/java/dasniko/keycloak/authenticator/SmsAuthenticator.java#L90
public class SmsAuthenticationFactory implements AuthenticatorFactory {

    private final String ID = "SmsAuthenticator";
    private final SmsAuthenticationProvider customRequiredActionProvider = new SmsAuthenticationProvider();

    @Override
    public String getDisplayType() {
        return "SmsAuthentication";
    }

    @Override
    public String getReferenceCategory() {
        return "Custom Authentication";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.CONDITIONAL,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE
        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Authenticate with sms otp";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return List.of(
                new ProviderConfigProperty("CODE_LENGTH", "Code length", "No of digits to be generated", ProviderConfigProperty.STRING_TYPE, 6),
                new ProviderConfigProperty("CODE_TTL", "time to live", "no of seconds, that code is valid", ProviderConfigProperty.STRING_TYPE, 30,"40")
        );
    }

    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
        return customRequiredActionProvider;
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
        return ID;
    }
}
