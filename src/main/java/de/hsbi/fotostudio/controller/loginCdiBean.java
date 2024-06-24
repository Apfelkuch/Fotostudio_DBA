package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Customer;
import de.hsbi.fotostudio.util.DataBean;
import de.hsbi.fotostudio.util.Util;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Frederick
 */
@Named(value = "loginCdiBean")
@SessionScoped
public class loginCdiBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String password; // Stores the user's password
    private String message, uname; // Stores messages and the username
    private int role; // Stores the user's role
    private boolean nameDataOk = false, pwdDataOk = false, login = false; // Flags for data validation and login status

    @Inject
    private DataBean dataBean; // LoginHandler instance for managing authentication

    /**
     * Handles the login process. Authenticates the user and manages the
     * session.
     */
    public void loginProject() {
        // Attempt to authenticate the user
        Customer currentUser = dataBean.login(uname, password);
        // Check if authentication was successful and data is valid
        if (currentUser != null && nameDataOk == true && pwdDataOk == true) {

            // Get Http Session and store user details
            HttpSession session = Util.getSession();
            session.setAttribute("customerId", currentUser.getCId());
            session.setAttribute("username", uname);
            session.setAttribute("userid", session.getId());
            session.setAttribute("userrole", currentUser.getRolle());
            session.setAttribute("loggedin", true);
            // Display a success message
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Anmeldung als " + Util.getUserName() + " erfolgreich!",
                            "Ihre ID: " + Util.getUserId()));
            login = true;
            // Hide the login dialog
            PrimeFaces.current().executeScript("PF('dlglog').hide()");

        } else {
            // Display an invalid login message
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ungültige Anmeldung!",
                            "Bitte versuchen Sie es erneut!"));
            // Perform logout actions
            logout();
        }
    }

    /**
     * Returns the login status.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * Handles the logout process. Invalidates the current session.
     */
    public void logout() {
        // Get the current session and invalidate it
        HttpSession session = Util.getSession();
        session.invalidate();
        login = false;
    }

    /**
     * Handles the logout process and adds a logout message.
     * Moreover the welcome page is return to exit from all Pages
     * which are not allow to be entered when logged out
     * 
     * @return Returns the path to the welcome page
     */
    public String logoutMSG() {
        //System.out.println("Logout called");
        // Perform logout actions
        logout();
        uname = null;
        // Display a logout success message
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Abmeldung erfolgreich!",
                        "Sie sind jetzt abgemeldet!"));
        return "ServiceView?faces-redirect=true";
    }

    /**
     * Validates the username. Ensures it meets the length requirements.
     *
     * @param fc FacesContext instance.
     * @param uic UIComponent instance.
     * @param obj Object to validate.
     */
    public void checkName(FacesContext fc, UIComponent uic, Object obj) {
        String value = obj.toString();
        int count = value.length();
        nameDataOk = false;
        if (count < 5) {
            // Display a warning message if the username is too short
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Mindestens 5 Zeichen!");
        } else if (count > 12) {
            // Display a warning message if the username is too long
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Maximal 12 Zeichen!");
        } else {
            nameDataOk = true;
            // Display a success message if the username length is valid
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

    /**
     * Validates the password. Ensures it meets the length requirements.
     *
     * @param fc FacesContext instance.
     * @param uic UIComponent instance.
     * @param obj Object to validate.
     */
    public void checkPwd(FacesContext fc, UIComponent uic, Object obj) {
        String value = obj.toString();
        int count = value.length();
        pwdDataOk = false;
        if (count < 5) {
            // Display a warning message if the password is too short
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Passwort)", "Mindestens 5 Zeichen !");
        } else {
            pwdDataOk = true;
            // Display a success message if the password length is valid
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

    /**
     * Resets the password and message fields.
     */
    public void reset() {
        password = null;
        message = null;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * @param uname the uname to set
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * Creates a new instance of loginCdiBean.
     */
    public loginCdiBean() {
    }
}
