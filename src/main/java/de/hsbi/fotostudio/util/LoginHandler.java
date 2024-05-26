/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.util;

import de.hsbi.fotostudio.modul.Birthday;
import de.hsbi.fotostudio.modul.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frederick
 */
@ApplicationScoped
public class LoginHandler implements Serializable {

    private List<User> loginListDAO = new ArrayList<>();

    ;

    public LoginHandler() {
        initLogin();
    }

    public void initLogin() {
        loginListDAO.add(new User("nobody", "11111", "nobody@gmail.com", new Birthday(01, 01, 2011), 0));
        loginListDAO.add(new User("admin", "12345", "admin@gmail.com", new Birthday(02, 2, 2022), 1));
    }

    public void addUser(String pName, String pPasword, String pEmail, Birthday pBday, int pRole) {
        loginListDAO.add(new User(pName, pPasword, pEmail, pBday, pRole));
    }

    public User login(String user, String password) {
        for (int i = 0; i < loginListDAO.size(); i++) {
            if (user.equals(loginListDAO.get(i).getUsername())
                    && password.equals(loginListDAO.get(i).getPassword())) {
                return loginListDAO.get(i);
            }
        }
        return null;
    }

    public boolean userExist(String user) {
        for (int i = 0; i < loginListDAO.size(); i++) {
            if (user.equals(loginListDAO.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }
}
