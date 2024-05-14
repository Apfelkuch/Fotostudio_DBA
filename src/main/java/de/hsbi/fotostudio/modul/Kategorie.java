package de.hsbi.fotostudio.modul;

/**
 * This class is model for a Kategorie
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
public class Kategorie {
    
    private int id;
    private String name;

    /**
     * Creates instance of Kategorie using the id and name parameters
     * 
     * @param id the id Parameter for the new instance
     * @param name the name Parameter for the new instance
     */
    public Kategorie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Creates instance of Kategorie
     */
    public Kategorie() {
        this.id = -1;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kategorie other = (Kategorie) obj;
        return this.id == other.id;
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
    
}
