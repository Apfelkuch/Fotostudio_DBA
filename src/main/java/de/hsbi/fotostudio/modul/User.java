package de.hsbi.fotostudio.modul;

/**
 * Represents a User in the photo studio system.
 * Each user has a username, password, email, birthday, and role.
 * The role determines the level of access the user has within the system.
 * Roles: 0 = nobody, 1 = admin, 2 = janis
 */
public class User {

    // Attributes
    private int id;
    private String username; // User's username
    private String password; // User's password
    private String email;    // User's email
    private Birthday bday; // User's birthday
    private int role;        // User's role (0 = nobody, 1 = admin, 2 = janis)

    // Constructor
    /**
     * Default constructor initializes user with default values.
     * Username: "INVALID"
     * Password: "0000"
     * Email: "INVALID@INVALID.com"
     * Birthday: default Birthday object
     * Role: nobody (0)
     */
    public User() {
        id = -1;
        username = "INVALID";
        password = "0000";
        email = "INVALID@INVALID.com";
        bday = new Birthday();
        role = 0;
    }

    /**
     * Constructor to create a user with specified attributes.
     * @param pUsername The username of the user
     * @param pPassword The password of the user
     * @param pEmail The email of the user
     * @param pBirthday The birthday of the user
     * @param pRole The role of the user (0 = nobody, 1 = admin, 2 = janis)
     */
    public User(int pId, String pUsername, String pPassword, String pEmail, Birthday pBirthday, int pRole) {
        id = pId;
        username = pUsername;
        password = pPassword;
        email = pEmail;
        bday = pBirthday;
        role = pRole;
    }

    // Getter and Setter Methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
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
     * Get the role of the user.
     * @return The role of the user
     */
    public int getRole() {
        return role; 
    }

    /**
     * Set the role of the user.
     * @param role The role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Get the password of the user.
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user.
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * Get the birthday of the user.
     * @return The birthday of the user
     */
    public Birthday getBday() {
        return bday;
    }

    /**
     * Set the birthday of the user.
     * @param bday The birthday to set
     */
    public void setBday(Birthday bday) {
        this.bday = bday;
    }
}
