package org.keycloak.ipAuthenticator;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.*;
import org.keycloak.models.credential.OTPCredentialModel;

import java.util.Collections;
import java.util.Map;

import static org.keycloak.ipAuthenticator.IpAuthenticatorFactory.logEvents;

public class IpAuthenticatorProvider implements Authenticator {

    private static final String IP_BASED_OTP_CONDITIONAL_USER_ATTRIBUTE = "ip_based_otp_conditional";

    @Override
    public void authenticate(AuthenticationFlowContext authenticationFlowContext) {
        logEvents("authenticate");
        KeycloakSession session = authenticationFlowContext.getSession();
        RealmModel realm = authenticationFlowContext.getRealm();
        UserModel user = authenticationFlowContext.getUser();

        String remoteIPAddress = authenticationFlowContext.getConnection().getRemoteAddr();
        String allowedIPAddress = getAllowedIPAddress(authenticationFlowContext);

        if (!allowedIPAddress.equals(remoteIPAddress)) {
            System.out.printf("IPs do not match. Realm %s expected %s but user %s logged from %s  ", realm.getName(), allowedIPAddress, user.getUsername(), remoteIPAddress);
            SubjectCredentialManager credentialManager = user.credentialManager();

            if (!credentialManager.isConfiguredFor(OTPCredentialModel.TYPE)) {
                user.addRequiredAction(UserModel.RequiredAction.CONFIGURE_TOTP);
            }

            user.setAttribute(IP_BASED_OTP_CONDITIONAL_USER_ATTRIBUTE, Collections.singletonList("force"));
        } else {
            user.setAttribute(IP_BASED_OTP_CONDITIONAL_USER_ATTRIBUTE, Collections.singletonList("skip"));
        }

        authenticationFlowContext.success();
    }

    private String getAllowedIPAddress(AuthenticationFlowContext context) {
        AuthenticatorConfigModel configModel = context.getAuthenticatorConfig();
        Map<String, String> config = configModel.getConfig();
        return config.get(IpAuthenticatorFactory.ALLOWED_IP_ADDRESS_CONFIG);
    }


    @Override
    public void action(AuthenticationFlowContext authenticationFlowContext) {
        logEvents("action");
    }

    @Override
    public boolean requiresUser() {
        logEvents("requiresUser");
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        logEvents("configuredFor");
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        logEvents("setRequiredActions");
    }

    @Override
    public void close() {
        logEvents("close");
    }
}
