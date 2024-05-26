/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Birthday;
import de.hsbi.fotostudio.util.LoginHandler;
import de.hsbi.fotostudio.util.Util;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Frederick
 */
@Named(value = "registerCdiBean")
@ViewScoped
public class registerCdiBean implements Serializable {

    String username;
    String password;
    String email;
    String date;
    Birthday bday = new Birthday();
    private boolean nameDataOk = false, pwdDataOk = false, emailDataOk = false, bdayDataOk = false;

    @Inject
    private LoginHandler loginHandler;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        //return String.valueOf(bday.getDay()) + "." + String.valueOf(bday.getMonth()) + "." + String.valueOf(bday.getYear());
        return null;
    }

    public void setDate(String date) {
        convertDateString(date);
    }

    /**
     * Creates a new instance of registerCdiBean
     */
    public registerCdiBean() {
    }

    public boolean dateInRange(Birthday d) {
        System.out.println("Tag: " + d.getDay() + "Monat: " + d.getMonth() + "Jahr: " + d.getYear());
        if (d.getDay() < 32 && d.getDay() > -1) {
            if (d.getMonth() < 13 && d.getMonth() > -1) {
                if (d.getYear() < 2101 && d.getYear() > 1909) {
                    return true;
                }
            }
        }
        return false;
    }

    public Birthday convertDateString(String dateString) {
        //System.out.println("Hallo");
        int first = dateString.indexOf(".");
        int second = dateString.indexOf(".", 4);
        int length = dateString.length();
        if (first == 2 & second == 5 & length == 10) {
            //System.out.println("HUHU");
            // String in Teile aufteilen
            String[] teile = dateString.split("\\.");
            Birthday convertBday = new Birthday();
            // Prüfen, ob genügend Teile vorhanden sind
            if (teile.length == 3) {
                // Teile in Ganzzahlen konvertieren
                convertBday.setDay(Integer.parseInt(teile[0]));
                //System.out.println("Tag: " + convertBday.getDay());
                convertBday.setMonth(Integer.parseInt(teile[1]));
                //System.out.println("Monat: " + convertBday.getMonth());
                convertBday.setYear(Integer.parseInt(teile[2]));
                //System.out.println("Jahr: " + convertBday.getYear());
                return convertBday;
            }
        }
        return null;
    }

    public void insertNewUser() {
        if (nameDataOk == true & pwdDataOk == true
                & emailDataOk == true & bdayDataOk == true) {
            loginHandler.addUser(username, password, email, bday, 0);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registrierung erfolgreich",
                            "Benutzer wurde angelegt"));
            PrimeFaces.current().executeScript("PF('dlgreg').hide()");
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registrierung fehlgeschlagen",
                            "falsche Angaben von Benutzerdaten"));
        }

    }

    public void checkName(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        int count = wert.length();
        boolean alreadyExist = loginHandler.userExist(wert);
        nameDataOk = false;
        if (alreadyExist == true) {
            Util.addComponentMessage(uic.getClientId(), "error", "Benutzername", "Der Benutzer exestiert bereits");
        } else if (count < 5) {
            //msg = new FacesMessage("Mindestens 5 Zeichen!");
            //throw new ValidatorException(msg);
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Minimal 5 Zeichen!");
        } else if (count > 12) {
            //msg = new FacesMessage("Maximal 12 Zeichen!");
            //throw new ValidatorException(msg);
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Benutzername)", "Maximal 12 Zeichen!");
        } else {
            nameDataOk = true;
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

    public void checkPwd(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        int count = wert.length();
        pwdDataOk = false;
        if (count < 5) {
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Passwort)", "Minimal 5 Zeichen!");
        } else {
            pwdDataOk = true;
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

    public void checkEmail(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        String pattern = "[A-Za-z.0-9]+@[a-z]+.[a-z]{2,3}";
        String res = wert.replaceFirst(pattern, "");
        emailDataOk = false;
        if (!res.isEmpty()) {
            Util.addComponentMessage(uic.getClientId(), "warn", "Fehlerhafte E-Mail Adresse", "ungültiges Format");
        } else {
            emailDataOk = true;
            Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
        }
    }

    public void checkBday(FacesContext fc, UIComponent uic, Object obj) {
        String wert = obj.toString();
        bdayDataOk = false;
        Birthday b = convertDateString(wert);
        if (b != null) {
            if (dateInRange(b) == true) {
                Util.addComponentMessage(uic.getClientId(), "info", "OK!", "OK!");
                bdayDataOk = true;
            } else {
                Util.addComponentMessage(uic.getClientId(), "warn", "falsches Datum", " Datum zwischen 1910 und 2100");
            }
        } else {
            Util.addComponentMessage(uic.getClientId(), "warn", "Syntax (Datum)!", " falsches Format: tt.mm.jjjj");
        }
    }
}
