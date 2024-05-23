/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.util;

/**
 *
 * @author Frederick
 */

public class LoginDAO {
    public static boolean login(String user, String password) {
	boolean loggedIn = false;
        if(user.equals("nobody" ) && password.equals("1234"))
                loggedIn = true;
        return loggedIn;
    }
}
