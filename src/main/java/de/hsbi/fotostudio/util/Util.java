package de.hsbi.fotostudio.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class providing methods for handling user sessions and messages in the photo studio application.
 */
public class Util {
    /**
     * Retrieves the current HTTP session.
     * @return The current HTTP session
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext
                                .getCurrentInstance()
                                .getExternalContext()
                                .getSession(false);
    }

    /**
     * Retrieves the current HTTP servlet request.
     * @return The current HTTP servlet request
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext
                                        .getCurrentInstance()
                                        .getExternalContext()
                                        .getRequest();
    }

    /**
     * Retrieves the username of the current user from the session.
     * @return The username of the current user
     */
    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                                                          .getExternalContext()
                                                          .getSession(false);
        return session.getAttribute("username").toString();
    }

    /**
     * Retrieves the user ID of the current user from the session.
     * @return The user ID of the current user
     */
    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }
    
    /**
     * Retrieves the role of the current user from the session.
     * @return The role of the current user
     */
    public static int getUserRole() {
        HttpSession session = getSession();
        if (session != null)
            return (int) session.getAttribute("userrole");
        else
            return -1;
    }
    
    /**
     * Checks if a user is logged in.
     * @return True if a user is logged in, otherwise false
     */
    public static boolean isLoggedin() {
        HttpSession session = getSession();
        if (session != null)
            return (boolean) session.getAttribute("loggedin");
        else
            return false;
    }
    
    /**
     * Adds a Faces message to the context.
     * @param summary The summary of the message
     * @param detail The detail of the message
     */
    public static void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * Adds a Faces message to a specific component.
     * @param uicomp The ID of the UI component
     * @param severity The severity of the message (info, error, warn)
     * @param summary The summary of the message
     * @param detail The detail of the message
     */
    public static void addComponentMessage(String uicomp, String severity, String summary, String detail) {
        switch (severity) {
            case "info":
                FacesContext.getCurrentInstance().addMessage(
                            uicomp,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_INFO,
                                    summary,
                                    detail));
                break;
            case "error":
                FacesContext.getCurrentInstance().addMessage(
                            uicomp,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR,
                                    summary,
                                    detail));
                break;
            case "warn":
                FacesContext.getCurrentInstance().addMessage(
                            uicomp,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_WARN,
                                    summary,
                                    detail));
                break;
        }
    }
}
