package org.keycloak.smsAuthentication;

import jakarta.ws.rs.core.MultivaluedMap;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.common.util.SecretGenerator;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.FormMessage;

public class SmsAuthenticationProvider implements Authenticator {
    @Override
    public void authenticate(AuthenticationFlowContext authenticationFlowContext) {

        String otp = SecretGenerator.getInstance().randomString(Integer.parseInt(authenticationFlowContext.getAuthenticatorConfig().getConfig().get("CODE_LENGTH")));
        System.out.println("otp--" + otp);
        authenticationFlowContext.getAuthenticationSession().setAuthNote("otp", otp);
        authenticationFlowContext.getAuthenticationSession().setAuthNote("ttl", String.valueOf(System.currentTimeMillis()));

        LoginFormsProvider form = authenticationFlowContext.form().setAttribute("userName", authenticationFlowContext.getUser().getUsername())
                .setAttribute("Phone_Number", authenticationFlowContext.getUser().getFirstAttribute("Phone_Number"));
        authenticationFlowContext.challenge(form.createForm("enter-otp.ftl"));
    }

    @Override
    public void action(AuthenticationFlowContext authenticationFlowContext) {

        MultivaluedMap<String, String> formData = authenticationFlowContext.getHttpRequest().getDecodedFormParameters();
        String entered_otp = formData.getFirst("phone_number");
        String otp = authenticationFlowContext.getAuthenticationSession().getAuthNote("otp");
        String ttl = authenticationFlowContext.getAuthenticationSession().getAuthNote("ttl");
        int CODE_TTL = Integer.parseInt(authenticationFlowContext.getAuthenticatorConfig().getConfig().get("CODE_TTL")) * 1000;
        System.out.println("ttl--" + (Long.parseLong(ttl) - System.currentTimeMillis()));
        System.out.println("otp--" + entered_otp);
        if (otp == null || !otp.equals(entered_otp)) {
            LoginFormsProvider form = authenticationFlowContext.form().setAttribute("userName", authenticationFlowContext.getUser().getUsername())
                    .setAttribute("Phone_Number", authenticationFlowContext.getUser().getFirstAttribute("Phone_Number"));
            form.addError(new FormMessage("Phone_Number", "otp is invalid"));
            authenticationFlowContext.challenge(form.createForm("enter-otp.ftl"));
        } else if (Long.parseLong(ttl) - System.currentTimeMillis() < CODE_TTL) {

            System.out.println("otp--" + SecretGenerator.getInstance().randomString(Integer.parseInt(authenticationFlowContext.getAuthenticatorConfig().getConfig().get("CODE_LENGTH"))));
            authenticationFlowContext.getAuthenticationSession().setAuthNote("otp", otp);
            authenticationFlowContext.getAuthenticationSession().setAuthNote("ttl", String.valueOf(System.currentTimeMillis()));

            LoginFormsProvider form = authenticationFlowContext.form().setAttribute("userName", authenticationFlowContext.getUser().getUsername())
                    .setAttribute("Phone_Number", authenticationFlowContext.getUser().getFirstAttribute("Phone_Number"));
            form.addError(new FormMessage("Phone_Number", "OTP is expired, Please enter new otp."));
            authenticationFlowContext.challenge(form.createForm("enter-otp.ftl"));
        } else {
            authenticationFlowContext.success();
        }
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {

    }
}
