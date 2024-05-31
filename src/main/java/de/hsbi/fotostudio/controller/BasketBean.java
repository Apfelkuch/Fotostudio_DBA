package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.Basket;
import de.hsbi.fotostudio.modul.BasketItem;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * The class BasketBean is the Backing-Bean for the Basket.xhtml page.
 * This class links the xhtml page and the underlying logic.
 *
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "basketBean")
@ViewScoped
public class BasketBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(BasketBean.class.getName());
    
    @Inject
    private Basket basket;

    /**
     * Creates a new instance of Basket
     */
    public BasketBean() {
    }
    
    /**
     * Return the whole basket
     * 
     * @return the basket
     */
    public List<BasketItem> getBasket() {
        return basket.getBasket();
    }
    
    /**
     * Increment the given BasketItem by one
     * 
     * @param basketItem the BasketItem which is modified
     */
    public void incrementBasketItem(BasketItem basketItem) {
        basket.incrementBasketItem(basketItem.getItem());
    }
    
    /**
     * Decrement the given BasketItem by one
     * 
     * @param basketItem the BasketItem which is modified
     */
    public void decrementBasketItem(BasketItem basketItem) {
        basket.decrementBasketItem(basketItem.getItem());
    }
    
    /**
     * Remove the given BasketItem from the basket
     * 
     * @param basketItem the BasketItem which is removed
     */
    public void removeBasketItem(BasketItem basketItem) {
        LOG.info("[BasketBean] remove basket item");
        if (basket.removeBasketItem(basketItem)) {
            LOG.info("[BasketBean] basket item removed");
        }
    }
    
    /**
     * Methode is called when the amount of the basketItem is changed in
     * the basket View. When the amount reaches 0, the basketItem is removed
     * 
     * @param basketItem the modified basketItem
     */
    public void changeAmount(BasketItem basketItem) {
        LOG.info("[BasketBean] change Amount: " + basketItem.getCount());
        if (basketItem.getCount() <= 0) {
            removeBasketItem(basketItem);
        } else {
            basketItem.setPrice(basketItem.getCount() * basketItem.getPrice());
        }
    }
    
}
