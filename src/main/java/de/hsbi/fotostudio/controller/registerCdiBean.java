package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Birthday;
import de.hsbi.fotostudio.util.LoginHandler;
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
 * it, and interacts with the LoginHandler to register new users.
 *
 * @author Frederick
 */
@Named(value = "registerCdiBean")
@ViewScoped
public class registerCdiBean implements Serializable {

    // User registration details
    private String username;  // Stores the username input from the user
    private String password;  // Stores the password input from the user
    private String email;     // Stores the email input from the user
    private String date;      // Date of birth in string format
    private Birthday bday = new Birthday(); // Birthday object to store parsed date

    // Validation flags for user input
    // Validation flags for user input
    private boolean nameDataOk = false; // Flag to check if username is valid
    private boolean pwdDataOk = false;  // Flag to check if password is valid
    private boolean emailDataOk = false;// Flag to check if email is valid
    private boolean bdayDataOk = false; // Flag to check if birthday is valid

    @Inject
    private LoginHandler loginHandler; // Injected LoginHandler for user management

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
     * Checks if the given birthday is within the acceptable range. The
     * acceptable range is from the year 1910 to 2100.
     *
     * @param d the Birthday object to check
     * @return true if the date is within the range, false otherwise
     */
    public boolean dateInRange(Birthday d) {
        System.out.println("Tag: " + d.getDay() + "Monat: " + d.getMonth() + "Jahr: " + d.getYear());
        if (d.getDay() < 32 && d.getDay() > -1) { // Day must be between 1 and 31
            if (d.getMonth() < 13 && d.getMonth() > -1) { // Month must be between 1 and 12
                if (d.getYear() < 2101 && d.getYear() > 1909) { // Year must be between 1910 and 2100
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Converts a date string into a Birthday object. The expected format of the
     * date string is "dd.MM.yyyy".
     *
     * @param dateString the date string to convert
     * @return a Birthday object or null if the format is invalid
     */
    public Birthday convertDateString(String dateString) {
        //System.out.println("Hallo");
        int first = dateString.indexOf(".");
        int second = dateString.indexOf(".", 4);
        int length = dateString.length();
        if (first == 2 & second == 5 & length == 10) {
            // Split the string into components based on the '.' delimiter
            //System.out.println("HUHU");
            // String in Teile aufteilen
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
        }
        return null;
    }

    /**
     * Attempts to register a new user. If all validation checks pass, the user
     * is added using the LoginHandler.
     */
    public void insertNewUser() {
        if (nameDataOk == true & pwdDataOk == true
                & emailDataOk == true & bdayDataOk == true) {
            // All validation checks passed, add the new user
            loginHandler.addUser(username, password, email, bday, 0);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registrierung erfolgreich",
                            "Benutzer wurde angelegt"));
            PrimeFaces.current().executeScript("PF('dlgreg').hide()");
        } else {
            // Validation failed, display an error message
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registrierung fehlgeschlagen",
                            "falsche Angaben von Benutzerdaten"));
        }
        // Reset user details after registration attempt
        username = null;
        password = null;
        email = null;
        bday = null;

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
        int count = wert.length();
        boolean alreadyExist = loginHandler.userExist(wert);
        nameDataOk = false;
        if (alreadyExist == true) {
            // Username already exists, show error message
            Util.addComponentMessage(uic.getClientId(), "error", "Benutzername", "Der Benutzer exestiert bereits");
        } else if (count < 5) {
            // Username too short, show warning message
            //msg = new FacesMessage("Mindestens 5 Zeichen!");
            //throw new ValidatorException(msg);
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Minimal 5 Zeichen!");
        } else if (count > 12) {
            // Username too long, show warning message
            //msg = new FacesMessage("Maximal 12 Zeichen!");
            //throw new ValidatorException(msg);
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Maximal 12 Zeichen!");
        } else {
            // Username valid
            nameDataOk = true;
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

    /**
     * Validates the password. Ensures it meets the minimum length requirement.
     *
     * @param fc FacesContext instance
     * @param uic UIComponent instance
     * @param obj the object to validate
     */
    public void checkPwd(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        int count = wert.length();
        pwdDataOk = false;
        if (count < 5) {
            // Password too short, show warning message
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Passwort)", "Minimal 5 Zeichen!");
        } else {
            // Password valid
            pwdDataOk = true;
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
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
        String pattern = "[A-Za-z.0-9]+@[a-z]+.[a-z]{2,3}";
        String res = wert.replaceFirst(pattern, "");
        emailDataOk = false;
        if (!res.isEmpty()) {
            // Email does not match pattern, show warning messag
            Util.addComponentMessage(uic.getClientId(), "warn", "Fehlerhafte E-Mail Adresse", "ungültiges Format");
        } else {
            // Email valid
            emailDataOk = true;
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
        bdayDataOk = false;
        Birthday b = convertDateString(wert);
        if (b != null) {
            if (dateInRange(b) == true) {
                // Date is within range, show info message
                Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
                bdayDataOk = true;
            } else {
                // Date is out of range, show warning message
                Util.addComponentMessage(uic.getClientId(), "warn", "falsches Datum", " Datum zwischen 1910 und 2100");
            }
        } else {
            // Date format invalid, show warning message
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Datum)!", " falsches Format: tt.mm.jjjj");
        }
    }
}
