/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.util.LoginDAO;
import de.hsbi.fotostudio.util.Util;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;


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

    public void loginProject() {
        boolean result = LoginDAO.login(uname, password);
        if (result) {

            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", uname);   
            session.setAttribute("userid", session.getId());
            FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Login for "+Util.getUserName()+" ok!",
                    "Your ID: "+Util.getUserId()));
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
            
            logout();

        }
    }
 
    public void logout() {
      HttpSession session = Util.getSession();
      session.invalidate();
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