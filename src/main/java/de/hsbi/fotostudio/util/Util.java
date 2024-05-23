/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.util;

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
}
