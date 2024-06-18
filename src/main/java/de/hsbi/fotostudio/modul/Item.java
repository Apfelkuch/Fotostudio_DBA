package de.hsbi.fotostudio.modul;

import de.hsbi.fotostudio.util.DataBean;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 * The Item interface is used to combine Products and Services
 * to go together in the Basket
 *
 * @since 1.1
 * @author Janis Wiegräbe
 */
public abstract class Item {
    
    /**
     * >=0 -> amount valid
     * < 0  -> amount is infinite
     */
    
    @Inject
    private DataBean dataBean;
    
    protected Category category;
    protected BillingType billingType;
    protected StorageStatus storageStatus;
    
    public Item() {
        category = new Category();
        billingType = new BillingType();
        storageStatus = new StorageStatus();
    }

    /**
     * Methode to get the Type of the Item, 
     * needs to be implementet by every Item
     * @return 
     */
    public abstract String getType();
    
    // GETTER && SETTER
    /**
     * Get Value of name
     * 
     * @return the value of name
     */
    public abstract String getName();
    
    /**
     * Get Value of preis
     * 
     * @return the value of preis
     */
    public abstract BigDecimal getPreis();
    
    /**
     * Get Value of menge
     * 
     * @return the value of menge
     */
    public abstract long getMenge();

    /**
     * Set Value of category
     * 
     * @param category the new value of category
     */
    public void setCategory(Category category) {
        this.category = category;
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
     * Set Value of storageStatus
     * 
     * @param storageStatus the new value of storageStatus
     */
    public void setStorageStatus(StorageStatus storageStatus) {
        this.storageStatus = storageStatus;
    }
    
}
