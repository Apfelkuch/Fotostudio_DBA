/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hsbi.fotostudio.modul;

import java.util.logging.Logger;

/**
 * This class is model for a Service
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
public class Service {
    
    private int id;
    private String name;
    private String description;
    private Category category;
    private BillingType billingType;
    private float price;
    private StorageStatus storageStatus;
    
    private static final Logger LOG = Logger.getLogger(Service.class.getName());
    
    /**
     * Creates instance of Product
     */
    public Service() {
        this.id = -1;
        category = new Category();
        billingType = new BillingType();
        storageStatus = new StorageStatus();
    }

    /**
     * Creates instance of Product using the id, name, beschriebung, category, abrechnungart, price, amount und storageStatus parameters
     * 
     * @param id the id parameter for the new instance
     * @param name the name parameter for the new instance
     * @param description the description parameter for the new instance
     * @param category the category parameter for the new instance
     * @param billingType the billingType parameter for the new instance
     * @param price the price parameter for the new instance
     * @param storageStatus the storageStatus parameter for the new instance
     */
    public Service(int id, String name, String description, Category category, BillingType billingType, float price, StorageStatus storageStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.billingType = billingType;
        this.price = price;
        this.storageStatus = storageStatus;
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
        Service other = (Service) obj;
        return (this.id == other.id);
    }

    /**
     * Converts the Obeject date into a String, for easier debugging
     * 
     * @return the String which represants the Object data
     */
    @Override
    public String toString() {
        return "[" + id + ", " + name + ", " + description 
                + ", " + category.getName() + ", " + billingType.getName()
                + ", " + price + ", " + storageStatus.getName()+ "]";
    }
    
    /**
     * Methode to check if a this product is in a given category
     * 
     * @param category the given category, which is compared to the category of this object
     * @return true if the given category is equal to the category of this object, otherwise false
     */
    public boolean inCategory(Category category) {
        return this.category.equals(category);
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
     * Get Value of description
     * 
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set Value of description
     * 
     * @param description the new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get Value of category
     * 
     * @return the value of category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Set Value of category
     * 
     * @param category the new value of category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Get Value of billingType
     * 
     * @return the value of billingType
     */
    public BillingType getBillingType() {
        return billingType;
    }

    /**
     * Set Value of billingType
     * 
     * @param billingType the new value of billingType
     */
    public void setBillingType(BillingType billingType) {
        this.billingType = billingType;
    }

    /**
     * Get Value of price
     * 
     * @return the value of price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set Value of price
     * 
     * @param price the new value of price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get Value of storageStatus
     * 
     * @return the value of storageStatus
     */
    public StorageStatus getStorageStatus() {
        return storageStatus;
    }

    /**
     * Set Value of storageStatus
     * 
     * @param storageStatus the new value of storageStatus
     */
    public void setStorageStatus(StorageStatus storageStatus) {
        this.storageStatus = storageStatus;
    }
    
}

