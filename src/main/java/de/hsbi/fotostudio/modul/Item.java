package de.hsbi.fotostudio.modul;

import java.util.logging.Logger;

/**
 * The Item interface is used to combine Products and Services
 * to go together in the Basket
 *
 * @author Janis Wiegräbe
 */
public abstract class Item {

    private static final Logger LOG = Logger.getLogger(Item.class.getName());
    
    protected String name;
    protected float price;
    /**
     * >=0 -> amount valid
     * < 0  -> amount is infinite
     */
    protected int amount;

    /**
     * Creates an Item using the name and price parameters
     * 
     * @param name the name for the new Item
     * @param price the price for the new Item
     * @param amount the amount for the new Item
     */
    public Item(String name, float price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    
    /**
     * Creates an Item
     */
    public Item() {
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
     * Get Value of amount
     * 
     * @return the value of amount
     */
    public int getAmount() {
        LOG.info("[Item] amount: " + amount);
        return amount;
    }

    /**
     * Set Value of amount
     * 
     * @param amount the new value of amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
