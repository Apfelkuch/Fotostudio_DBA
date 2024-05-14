package de.hsbi.fotostudio.modul;

import java.util.logging.Logger;

/**
 * This class is model for a Produkt
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
public class Produkt {
    
    private int id;
    private String name;
    private String beschreibung;
    private Kategorie kategorie;
    private Abrechnungsart abrechnungsart;
    private float preis;
    
    /**
     * <= 0 : Menge vorhanden
     *  -1  : Menge unbegrenzt
     */
    private int menge;
    private Lagerstatus lagerstatus;
    
    private static final Logger LOG = Logger.getLogger(Produkt.class.getName());
    
    /**
     * Creates instance of Produkt
     */
    public Produkt() {
        this.id = -1;
        kategorie = new Kategorie();
        abrechnungsart = new Abrechnungsart();
        lagerstatus = new Lagerstatus();
    }

    /**
     * Creates instance of Produkt using the id, name, beschriebung, kategorie, abrechnungart, preis, menge und lagerstatus parameters
     * @param id the id parameter for the new instance
     * @param name the name parameter for the new instance
     * @param beschreibung the beschreibung parameter for the new instance
     * @param kategorie the kategorie parameter for the new instance
     * @param abrechnungsart the abrechnugnsart parameter for the new instance
     * @param preis the preis parameter for the new instance
     * @param menge the menge parameter for the new instance
     * @param lagerstatus the lagerstatus parameter for the new instance
     */
    public Produkt(int id, String name, String beschreibung, Kategorie kategorie, Abrechnungsart abrechnungsart, float preis, int menge, Lagerstatus lagerstatus) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.abrechnungsart = abrechnungsart;
        this.preis = preis;
        this.menge = menge;
        this.lagerstatus = lagerstatus;
    }
    
    /**
     * Generates hash code of the class using the id and name parameter
     * 
     * @return the hash code for this class
     */
    @Override
    public int hashCode() {
        return (id == -1 ? 0 : 31 * id + name.hashCode());
    }
    
    /**
     * Compares a object to this instance using the id.
     * 
     * @param obj ne objekt this instance is compared against
     * @return true if the object and this instance are the equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass()!= obj.getClass())
            return false;
        Produkt other = (Produkt) obj;
        return (this.id == other.id);
    }

    /**
     * Converts the Obeject date into a String, for easier debugging
     * 
     * @return the String which represants the Object data
     */
    @Override
    public String toString() {
        return "[" + id + ", " + name + ", " + beschreibung 
                + ", " + kategorie.getName() + ", " + abrechnungsart.getName() + ", " 
                + menge + ", " + preis + ", " + lagerstatus.getName()+ "]";
    }
    
    /**
     * Methode to check if a this produkt is in a given kategorie
     * 
     * @param kategorie the given kategorie, which is compared to the kategorie of this object
     * @return true if the given kategorie is equal to the kategorie of this object, otherwise false
     */
    public boolean inKategorie(Kategorie kategorie) {
        return this.kategorie.equals(kategorie);
    }
    
    // GETTER && SETTER

    /**
     * Get Value of id
     * 
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set Value of id
     * 
     * @param id the new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get Value of name
     * 
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Value of name
     * 
     * @param name the new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Value of beschreibung
     * 
     * @return the value of beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Set Value of beschreibung
     * 
     * @param beschreibung the new value of beschreibung
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Get Value of kategorie
     * 
     * @return the value of kategorie
     */
    public Kategorie getKategorie() {
        return kategorie;
    }

    /**
     * Set Value of kategorie
     * 
     * @param kategorie the new value of kategorie
     */
    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * Get Value of abrechnungsart
     * 
     * @return the value of abrechnungsart
     */
    public Abrechnungsart getAbrechnungsart() {
        return abrechnungsart;
    }

    /**
     * Set Value of abrechnungsart
     * 
     * @param abrechnungsart the new value of abrechnungsart
     */
    public void setAbrechnungsart(Abrechnungsart abrechnungsart) {
        this.abrechnungsart = abrechnungsart;
    }

    /**
     * Get Value of preis
     * 
     * @return the value of preis
     */
    public float getPreis() {
        return preis;
    }

    /**
     * Set Value of preis
     * 
     * @param preis the new value of preis
     */
    public void setPreis(float preis) {
        this.preis = preis;
    }

    /**
     * Get Value of menge
     * 
     * @return the value of menge
     */
    public int getMenge() {
        return menge;
    }

    /**
     * Set Value of menge
     * 
     * @param menge the new value of menge
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }

    /**
     * Get Value of lagerstatus
     * 
     * @return the value of lagerstatus
     */
    public Lagerstatus getLagerstatus() {
        return lagerstatus;
    }

    /**
     * Set Value of lagerstatus
     * 
     * @param lagerstatus the new value of lagerstatus
     */
    public void setLagerstatus(Lagerstatus lagerstatus) {
        this.lagerstatus = lagerstatus;
    }
    
}
