package de.hsbi.fotostudio.modul;

/**
 * The BasketItem class describes the form of one item in the Basket
 *
 * @author Janis Wiegräbe
 */
public class BasketItem {
    
    private int id;
    private Item item;
    private int count;
    private float price;

    /**
     * Creates instance of BasketItem
     */
    public BasketItem() {
    }
    
    /**
     * Creates instance of BasketItem using the id, item and count parameters
     * 
     * @param id the id Parameter for the new instance
     * @param item the item Parameter for the new instance
     * @param count the count Parameter for the new instance
     */
    public BasketItem(int id, Item item, int count) {
        this.id = id;
        this.item = item;
        this.count = count;
        this.setPrice(item.price * count);
    }
    
    /**
     * Increments the count by one
     * 
     * @return the new count
     */
    public int incrementCount() {
//        if ((this.count + 1) > this.item.amount) {
//            return this.count;
//        } else {
            this.price += this.item.price;
            setPrice(price);
            return ++this.count;
//        }
    }
    
    /**
     * Decrements the count by one
     * 
     * @return the new count
     */
    public int decrementCount() {
        this.price -= this.item.price;
        setPrice(price);
        return --this.count;
    }
    
    /**
     * Returns the maximum number if an item is present.
     * If the amount is set to infinity, a default maximum value
     * of 1 000 000 is used.
     * @see {double}
     * 
     * @return 
     */
    public int getMaxAmount() {
        if (item == null) {
            return 0;
        }
        if (item.amount < 0) {
            return 1000000;
        } else {
            return item.amount;
        }
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
     * Get Value of item
     * 
     * @return the value of item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Set Value of item
     * 
     * @param item the new value of item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Get Value of count
     * 
     * @return the value of count
     */
    public int getCount() {
        return count;
    }

    /**
     * Set Value of count
     * 
     * @param count the new value of count
     */
    public void setCount(int count) {
        if (count > getMaxAmount()) {
            this.count = getMaxAmount();
        } else {
            this.count = count;
        }
        setPrice(this.count * this.price);
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
        this.price = Math.round(price * 100) / 100f;
    }
    
    
    
    
}
