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
 * Managed Bean for user login. This bean handles user input, validates
 * it, and interacts with the DataBean to login the users.
 * @version 0.1
 * @author Frederick Zahn
 */
@Named(value = "loginCdiBean")
@SessionScoped
public class loginCdiBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String password; // Stores the user's password
    private String message, uname; // Stores messages and the username
    private boolean login = false; // Flags for data validation and login status

    @Inject
    private DataBean dataBean; // LoginHandler instance for managing authentication

    /**
     * Handles the login process. Authenticates the user and manages the
     * session.
     */
    public void loginProject() {
        // Attempt to authenticate the user
        Customer currentUser = dataBean.login(uname, password);
        System.out.println("Ich werde aufgerufen für login user");
        // Get Http Session and store user details
        HttpSession session = Util.getSession();
        session.setAttribute("customerId", currentUser.getCId());
        session.setAttribute("username", uname);
        session.setAttribute("userid", session.getId());
        session.setAttribute("userrole", currentUser.getRolle());
        session.setAttribute("loggedin", true);
        System.out.println("Ich werde aufgerufen für Message");
        // Display a success message
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Anmeldung als " + Util.getUserName() + " erfolgreich!",
                        "Ihre ID: " + Util.getUserId()));
        login = true;
        System.out.println("Ich werde aufgerufen für hide");
        // Hide the login dialog
        PrimeFaces.current().executeScript("PF('dlglog').hide()");
        password = null;
        uname = null;
        System.out.println("Ich werde aufgerufen für refresh");
        PrimeFaces.current().ajax().update("FORMLOG");
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
     * Handles the logout process and adds a logout message. Moreover the
     * welcome page is return to exit from all Pages which are not allow to be
     * entered when logged out
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
