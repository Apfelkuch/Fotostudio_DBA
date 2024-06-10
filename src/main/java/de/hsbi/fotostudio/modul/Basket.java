package de.hsbi.fotostudio.modul;

import de.hsbi.fotostudio.util.DataBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The Basket class holds the state of the basket
 *
 * @author Janis Wiegräbe
 */
@SessionScoped
public class Basket implements Serializable {
    
    @Inject
    private DataBean dataBean;

    private static final Logger LOG = Logger.getLogger(Basket.class.getName());
    
    private List<BasketItem> basket;
    
    public Basket() {
        basket = new ArrayList<>();
    }
    
    /**
     * Adds a Item to the Basket
     * 
     * @param item the new item
     * @return true if the item was added otherwise false
     */
    public boolean addBasketItem(Item item) {
        BasketItem basketItem = new BasketItem(basket.size(), item, 1);
        return basket.add(basketItem);
    }
    
    /**
     * Removes a Item with the givven id out of the Basket
     * 
     * @param basketItem the basketItem which should be removed
     * @return true if the basketItem was removed otherwise false
     */
    public boolean removeBasketItem(BasketItem basketItem) {
        LOG.info("[Basket] remove");
        for (BasketItem bI :  basket) {
            if (bI.getId() == basketItem.getId()) {
                return basket.remove(basketItem);
            }
        }
        return false;
    }
    
    /**
     * Inkrements the given item in the basket by one
     * 
     * @param item the given item
     * @return the count item in the basket
     */
    public int incrementBasketItem(Item item) {
        BasketItem basketItem = searchBasketItemInList(item);
        if (basketItem != null) {
            return basketItem.incrementCount();
        } else {
            if (addBasketItem(item)) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    /**
     * Dekrements the given item in the basket by one.
     * If the count of the basket Item reaches less then
     * one the basket Item is removed from the Basket
     * 
     * @param item the given item
     * @return the count item in the basket
     */
    public int decrementBasketItem(Item item) {
        BasketItem basketItem = searchBasketItemInList(item);
        if (basketItem != null) {
            int count = basketItem.decrementCount();
            if (count == -1) {
                basket.remove(basketItem);
                return 0;
            } else {
                return count;
            }
        }
        return 0;
    }
    
    /**
     * Set the count of the basketItem to the given count
     * and returns the new count
     * 
     * @param basketItem the basketItem which is modified
     * @param count the new count for the basktItem
     * @return the new count of the basketItem
     */
    public int setCountOnBasketItem(BasketItem basketItem, int count) {
        basketItem.setCount(count);
        return basketItem.getCount();
    }
    
    /**
     * Searches for the given item in the basket and return the id of the basketItem
     * 
     * @param item the item for which is searched
     * @return the BasketItem of the searched item and null if the item is not found
     */
    private BasketItem searchBasketItemInList(Item item) {
        for (int i = 0; i < basket.size(); i++) {
            BasketItem basketItem = basket.get(i);
            if (basketItem.getItem().equals(item)) {
                return basketItem;
            }
        }
        return null;
    }

    /**
     * Get the size of the basket
     * 
     * @return the value of basket.size
     */
    public int getBasketSize() {
        return basket.size();
    }

    /**
     * Returns the total price for the whole basket
     * 
     * @return the total price of the basket
     */
    public float getTotalPrice() {
        float totalPrice = 0f;
        for (BasketItem basketItem : basket) {
            float added = Math.round((basketItem.getCount() * basketItem.getItem().getPrice()) * 100) / 100f;
            totalPrice = Math.round((totalPrice + added) * 100) / 100f;
        }
        if (totalPrice < 0) {
            totalPrice = 0;
        }
        return totalPrice;
    }    
    
    /**
     * This Methode clears all items out of the basket
     * @return true if the basket is cleared, otherwise false
     */
    public boolean clearBasket() {
        basket.clear();
        return basket.isEmpty();
    }
    
    public void addOrder() {
        dataBean.addOrder(basket);
    }
    
    // GETTER && SETTER
    
    /**
     * Get Value of basket
     * 
     * @return the value of basket
     */
    public List<BasketItem> getBasket() {
        return basket;
    }
    
}
