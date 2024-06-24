package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Birthday;
import de.hsbi.fotostudio.util.DataBean;
import de.hsbi.fotostudio.util.Util;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import org.primefaces.PrimeFaces;

/**
 * Managed Bean for user registration. This bean handles user input, validates
 * it, and interacts with the DataBean to register new users.
 * @version  0.1
 * @author Frederick Zahn
 */
@Named(value = "registerCdiBean")
@ViewScoped
public class registerCdiBean implements Serializable {

    // User registration details
    private String username;  // Stores the username input from the user
    private String password;  // Stores the password input from the user
    private String email;     // Stores the email input from the user
    private Birthday bday = new Birthday(); // Birthday object to store parsed date

    @Inject
    private DataBean dataBean; // Injected LoginHandler for user management

    // Getter and Setter methods for the user registration details
    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the date of birth.
     *
     * @return the date of birth
     */
    public String getDate() {
        //return String.valueOf(bday.getDay()) + "." + String.valueOf(bday.getMonth()) + "." + String.valueOf(bday.getYear());
        return null;
    }

    /**
     * Sets the date of birth.
     *
     * @param date the date to set
     */
    public void setDate(String date) {
        bday = convertDateString(date);
    }

    /**
     * Default constructor for registerCdiBean.
     */
    public registerCdiBean() {
    }

    /**
     * Converts a date string into a Birthday object. The expected format of the
     * date string is "dd.MM.yyyy".
     *
     * @param dateString the date string to convert
     * @return a Birthday object or null if the format is invalid
     */
    public Birthday convertDateString(String dateString) {
        String[] teile = dateString.split("\\.");
        Birthday convertBday = new Birthday();
        // Prüfen, ob genügend Teile vorhanden sind
        if (teile.length == 3) {
            // Parse and set day, month, and year
            convertBday.setDay(Integer.parseInt(teile[0]));
            //System.out.println("Tag: " + convertBday.getDay());
            convertBday.setMonth(Integer.parseInt(teile[1]));
            //System.out.println("Monat: " + convertBday.getMonth());
            convertBday.setYear(Integer.parseInt(teile[2]));
            //System.out.println("Jahr: " + convertBday.getYear());
            return convertBday;
        }
        return null;
    }

    /**
     * Attempts to register a new user. If all validation checks pass, the user
     * is added using the LoginHandler.
     */
    public void insertNewUser() {
        //add the new user            
        dataBean.addUser(username, password, email, bday, 0);
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Registrierung erfolgreich",
                        "Benutzer wurde angelegt"));
        PrimeFaces.current().executeScript("PF('dlgreg').hide()");
        // Reset user details after registration attempt
        username = null;
        password = null;
        email = null;
        bday = null;
        // Optionally, update the form to reflect changes
        PrimeFaces.current().ajax().update("FORMREG");
    }

    /**
     * Validates the username. Ensures it is not already taken and meets the
     * length requirements.
     *
     * @param fc FacesContext instance
     * @param uic UIComponent instance
     * @param obj the object to validate
     */
    public void checkName(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        boolean alreadyExist = dataBean.userExist(wert);
        if (alreadyExist == true) {
            // Username already exists, show error message
            FacesContext.getCurrentInstance().validationFailed();
            Util.addComponentMessage(uic.getClientId(), "error", "Benutzername", "Der Benutzer exestiert bereits");
        } else {

            String pattern = "^.{5,12}$";
            String res = wert.replaceFirst(pattern, "");
            if (!res.isEmpty()) {
                // Username does not match pattern, show warning messag
                FacesContext.getCurrentInstance().validationFailed();
                Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Zwischen 5 und 12 Zeichen!");
            } else {
                Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
            }
        }
    }

    /**
     * Validates the email address. Ensures it matches the required pattern.
     *
     * @param fc FacesContext instance
     * @param uic UIComponent instance
     * @param obj the object to validate
     */
    public void checkEmail(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        String pattern = "^(\\w{3,})(-|_|\\+|\\.)?\\w*@((\\w+)(\\.|-))+(\\w{2,})$";
        String res = wert.replaceFirst(pattern, "");
        if (!res.isEmpty()) {
            // Email does not match pattern, show warning messag
            FacesContext.getCurrentInstance().validationFailed();
            Util.addComponentMessage(uic.getClientId(), "warn", "Fehlerhafte E-Mail Adresse", "ungültiges Format");
        } else {
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

    /**
     * Validates the birthday. Ensures it is in the correct format and within
     * the acceptable range.
     *
     * @param fc FacesContext instance
     * @param uic UIComponent instance
     * @param obj the object to validate
     */
    public void checkBday(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();

        String pattern = "^((0[1-9])|([12]\\d)|30|31)\\.(?<=\\.)(0[\\d]|1[012])\\.(?<=\\.)((19|20)[\\d]{2})$";
        String res = wert.replaceFirst(pattern, "");

        if (!res.isEmpty()) {
            // Date format invalid, show warning message
            FacesContext.getCurrentInstance().validationFailed();
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Datum)!", " falsches Format: tt.mm.jjjj");
        } else {
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }
}
