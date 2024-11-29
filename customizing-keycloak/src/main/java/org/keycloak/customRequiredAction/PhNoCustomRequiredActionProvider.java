package org.keycloak.customRequiredAction;

import jakarta.ws.rs.core.MultivaluedMap;
import org.keycloak.authentication.RequiredActionContext;
import org.keycloak.authentication.RequiredActionProvider;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.FormMessage;

public class PhNoCustomRequiredActionProvider implements RequiredActionProvider {

    @Override
    public void evaluateTriggers(RequiredActionContext requiredActionContext) {
        if (requiredActionContext.getUser().getFirstAttribute("PhoneNumber") == null) {
            requiredActionContext.getUser().addRequiredAction("Phone_Number");
        }
    }

    @Override
    public void requiredActionChallenge(RequiredActionContext requiredActionContext) {

        LoginFormsProvider form = requiredActionContext.form().setAttribute("userName", requiredActionContext.getUser().getUsername())
                .setAttribute("Phone_Number", requiredActionContext.getUser().getFirstAttribute("Phone_Number"));
        requiredActionContext.challenge(form.createForm("update-phone-number.ftl"));
    }

    @Override
    public void processAction(RequiredActionContext requiredActionContext) {
        MultivaluedMap<String, String> formData = requiredActionContext.getHttpRequest().getDecodedFormParameters();
        String phno = formData.getFirst("phone_number");
        if (phno == null || phno.length() < 10) {
            LoginFormsProvider form = requiredActionContext.form().setAttribute("userName", requiredActionContext.getUser().getUsername())
                    .setAttribute("Phone_Number", requiredActionContext.getUser().getFirstAttribute("Phone_Number"));
            form.addError(new FormMessage("Phone_Number", "phone number is invalid"));
            requiredActionContext.challenge(form.createForm("update-phone-number.ftl"));
            return;
        }
        UserModel userModel = requiredActionContext.getUser();
        userModel.setSingleAttribute("PhoneNumber", phno);
        userModel.removeRequiredAction("Phone_Number");
        requiredActionContext.success();
    }

    @Override
    public void close() {

    }
}
