/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.User;
import de.hsbi.fotostudio.util.LoginHandler;
import de.hsbi.fotostudio.util.Util;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.validator.ValidatorException;
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
    private String password;
    private String message, uname;
    private int role;
    private boolean nameDataOk = false, pwdDataOk = false, login = false;

    @Inject
    private LoginHandler loginHandler;

    public void loginProject() {
        User currentUser = loginHandler.login(uname, password);
        if (currentUser != null & nameDataOk == true & pwdDataOk == true) {

            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", uname);
            session.setAttribute("userid", session.getId());
            session.setAttribute("userrole", currentUser.getRole());
            session.setAttribute("loggedin", true);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Login als " + Util.getUserName() + " ok!",
                            "Deine ID: " + Util.getUserId()));
            login = true;
            PrimeFaces.current().executeScript("PF('dlglog').hide()");

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            logout();

        }
    }

    public boolean isLogin() {
        return login;
    }

    public void logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        login = false;
    }

    public void logoutMSG() {
        System.out.println("Logout UFGERUFEN");
        logout();
        FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Logout erfolgreich!",
                            "Sie sind jetzt ausgeloggt!"));
    }

    public void checkName(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        int count = wert.length();
        nameDataOk = false;
        if (count < 5) {
            //msg = new FacesMessage("Mindestens 5 Zeichen!");
            //throw new ValidatorException(msg);
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Minimal 5 Zeichen!");
        } else if (count > 12) {
            //msg = new FacesMessage("Maximal 12 Zeichen!");
            //throw new ValidatorException(msg);
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Maximal 12 Zeichen!");
        } else {
            nameDataOk = true;
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

    public void checkPwd(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        int count = wert.length();
        pwdDataOk = false;
        if (count < 5) {
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Passwort)", "Minimal 5 Zeichen!");
        } else {
            pwdDataOk = true;
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

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
     * Creates a new instance of loginCdiBean
     */
    public loginCdiBean() {
    }

}
