package de.hsbi.fotostudio.validator;

import de.hsbi.fotostudio.controller.RegisterCdiBean;
import de.hsbi.fotostudio.util.Util;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.inject.Inject;
import java.io.Serializable;

@FacesValidator("passwordValidation")
@SessionScoped
public class PasswordValidation implements Validator, Serializable {

    @Inject
    private RegisterCdiBean regBean;

    /**
     * Validates the password. Ensures it meets the minimum length requirement.
     *
     * @param fc FacesContext instance
     * @param uic UIComponent instance
     * @param obj the object to validate
     */
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object obj) {
        System.out.println("Ich bin hier \n");
        if (regBean != null) {
            System.out.println("Ich bin schon wieder Null \n");
            String wert = obj.toString();
            String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}|\\d{5}$";
            String res = wert.replaceFirst(pattern, "");
            regBean.setPwdDataOk(false);
            if (!res.isEmpty()) {
                // Password does not match pattern, show warning messag
                Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Passwort)", "Groß-/Kleinschribung, Sonderzeichen, Zahlen, 8 Zeichen");
            } else {
                // Password valid
                regBean.setPwdDataOk(true);
                Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
            }
        }
    }
}
