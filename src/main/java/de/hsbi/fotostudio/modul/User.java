/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.modul;

/**
 *
 * @author Frederick
 */
public class User {

    private String username;
    private String password;
    private String emai;
    private Birthday bday;
    private int role; // 0 = nobody, 1 = admin, 2 = janis

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role; 
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public Birthday getBday() {
        return bday;
    }

    public void setBday(Birthday bday) {
        this.bday = bday;
    }

    public User() {
        username = "INVALID";
        password = "0000";
        emai = "INVALID@INVALID.com";
        bday = new Birthday();
        role = 0;
    }

    public User(String pUsername, String pPassword, String pEmai, Birthday pBday, int pRole) {
        username = pUsername;
        password = pPassword;
        emai = pEmai;
        bday = pBday;
        role = pRole;
    }

}
