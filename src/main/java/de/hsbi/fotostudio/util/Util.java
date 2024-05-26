/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Frederick
 */

public class Util {
    public static HttpSession getSession() {
	return (HttpSession)FacesContext
                                .getCurrentInstance()
                                .getExternalContext()
                                .getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext
                                    .getCurrentInstance()
                                    .getExternalContext()
                                    .getRequest();
    }

    public static String getUserName()
    {
        HttpSession session 
            = (HttpSession) FacesContext.getCurrentInstance()
                                        .getExternalContext()
                                        .getSession(false);
        return  session.getAttribute("username").toString();
    }

    public static String getUserId()
    {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("userid");
        else
            return null;
    }
    
    public static String getUserRole()
    {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("userrole");
        else
            return null;
    }
    
        
    public static boolean isLoggedin()
    {
        HttpSession session = getSession();
        if ( session != null )
            return (boolean) session.getAttribute("loggedin");
        else
            return false;
    }
    
    public static void addMessage(String summary, String detail) {
        FacesMessage message
                = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        summary, detail);
        FacesContext.getCurrentInstance()
                .addMessage(null, message);
    }
    
     public static void addComponentMessage(
            String uicomp, 
            String severity, 
            String summary, 
            String detail) {
        switch(severity){
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
