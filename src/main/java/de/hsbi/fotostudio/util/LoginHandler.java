package de.hsbi.fotostudio.util;

import de.hsbi.fotostudio.modul.Birthday;
import de.hsbi.fotostudio.modul.User;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages user login functionality for the photo studio application.
 * Provides methods to initialize login data, add new users, authenticate users,
 * and check if a user exists.
 */
@ApplicationScoped
public class LoginHandler implements Serializable {

    // Attributes
    private List<User> loginListDAO = new ArrayList<>();

    // Constructor
    /**
     * Initializes LoginHandler with default login data.
     */
    public LoginHandler() {
        initLogin();
    }

    // Methods
    /**
     * Initializes the login data with default users.
     */
    public void initLogin() {
        loginListDAO.add(new User("nobody", "11111", "nobody@gmail.com", new Birthday(01, 01, 2011), 0));
        loginListDAO.add(new User("admin", "12345", "admin@gmail.com", new Birthday(02, 2, 2022), 1));
        loginListDAO.add(new User("janis", "janis", "janis@hsbi.de", new Birthday(11, 11, 2011), 2));
        
    }

    /**
     * Adds a new user to the login data.
     * @param pName The username of the new user
     * @param pPasword The password of the new user
     * @param pEmail The email of the new user
     * @param pBday The birthday of the new user
     * @param pRole The role of the new user
     */
    public void addUser(String pName, String pPasword, String pEmail, Birthday pBday, int pRole) {
        loginListDAO.add(new User(pName, pPasword, pEmail, pBday, pRole));
    }

    /**
     * Authenticates a user by checking username and password.
     * @param user The username of the user to authenticate
     * @param password The password of the user to authenticate
     * @return The authenticated user if successful, otherwise null
     */
    public User login(String user, String password) {
        for (int i = 0; i < loginListDAO.size(); i++) {
            if (user.equals(loginListDAO.get(i).getUsername())
                    && password.equals(loginListDAO.get(i).getPassword())) {
                return loginListDAO.get(i);
            }
        }
        return null;
    }

    /**
     * Checks if a user exists.
     * @param user The username to check
     * @return True if the user exists, otherwise false
     */
    public boolean userExist(String user) {
        for (int i = 0; i < loginListDAO.size(); i++) {
            if (user.equals(loginListDAO.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns a List of all Users which purchased something in the last
     * 3 months. The Users are sorted in descending order of their purchases.
     * 
     * @return list of top seller users
     */
    public List<User> selectTopSeller() {
        return loginListDAO;
    }
    
    /**
     * Returns a List of all Users which have not purchased something
     * this year.
     * 
     * @return list of the shop keeper users
     */
    public List<User> selectShopKeeper() {
        return loginListDAO;
    }
    
}
