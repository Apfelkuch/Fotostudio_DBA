package de.hsbi.fotostudio.modul;

/**
 * Represents a ShowUser in the photo studio system.
 * Each ShowUser has a username, email and purchases
 */
public class ShowUser {

    // Attributes
    private String username; // User's username
    private String email;    // User's email
    private int purchases; // User's purchases

    // Constructor
    /**
     * Default constructor initializes user with default values.
     * Username: "INVALID"
     * Email: "INVALID@INVALID.com"
     * Purchases: -1
     */
    public ShowUser() {
        username = "INVALID";
        email = "INVALID@INVALID.com";
        purchases = -1;
    }

    /**
     * Constructor to create a user with specified attributes.
     * @param pUsername The username of the user
     * @param pEmail The email of the user
     * @param pPurchases The number if purchases of the user
     */
    public ShowUser(String pUsername, String pEmail, int pPurchases) {
        username = pUsername;
        email = pEmail;
        purchases = pPurchases;
    }

    // Getter and Setter Methods
    /**
     * Get the username of the user.
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user.
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the purchases of the user.
     * @return The purchases of the user
     */
    public int getPurchases() {
        return purchases;
    }

    /**
     * Set the purchases of the user.
     * @param purchases The purchases to set
     */
    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    /**
     * Get the email of the user.
     * @return The email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user.
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
