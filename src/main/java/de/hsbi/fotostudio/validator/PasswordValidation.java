package de.hsbi.fotostudio.validator;

import de.hsbi.fotostudio.util.Util;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import java.io.Serializable;

@FacesValidator(value = "passwordValidation")
@SessionScoped
public class PasswordValidation implements Validator, Serializable {

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
        System.out.println("Ich bin schon wieder Null \n");
        String wert = obj.toString();
        String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}|\\d{5}$";
        String res = wert.replaceFirst(pattern, "");
        if (!res.isEmpty()) {
            // Password does not match pattern, show warning messag
            FacesContext.getCurrentInstance().validationFailed();
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Passwort)", "Groß-/Kleinschribung, Sonderzeichen, Zahlen, 8 Zeichen");
        } else {
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }

    }
}
