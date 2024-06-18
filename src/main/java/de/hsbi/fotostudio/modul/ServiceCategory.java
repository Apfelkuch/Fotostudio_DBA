package de.hsbi.fotostudio.modul;

/**
 * This enum is model for a ServiceCategory
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
public enum ServiceCategory {
    Alles("Alles"),
    Innerhaus("Innerhaus"),
    Außerhaus("Außerhaus");
    
    private final String value;
    
    /**
     * Constructs a ServiceCategory with a given value
     * 
     * @param value The value of the ServiceCategory
     */
    private ServiceCategory(String value) {
        this.value = value;
    }
    
    /**
     * Get Value of value
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
