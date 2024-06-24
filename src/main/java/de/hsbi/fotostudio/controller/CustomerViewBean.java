package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.ShowUser;
import de.hsbi.fotostudio.util.DataBean;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Janis Wiegräbe
 */
@Named(value = "customerViewBean")
@ViewScoped
public class CustomerViewBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CustomerViewBean.class.getName());
    
    private List<ShowUser> showUserList;
    
    @Inject
    private DataBean dataBean;

    /**
     * Creates a new instance of CustomerViewBean
     */
    public CustomerViewBean() {
    }
    
    /**
     * Initalization of the Customer view is called directly after the
     * Konstruktor Initalizes the variables
     */
    @PostConstruct
    public void init() {
        selectTopSellerCustomer();
    }
    
    /**
     * The ValueChangeListener-Methode to change the User view.
     *
     * @param event the ValueChangeEvent contains Information about the state
     * befor and after the ValueChange
     */
    public void getCustomerView(ValueChangeEvent event) {
        LOG.info("[CustomerViewBean] getCustomerView");

        String option = (String) event.getNewValue();
        
        switch (option) {
            case "topSellerCustomer":
                LOG.info("[CustomerViewBean] topSellerCustomer");
                selectTopSellerCustomer();
                break;
            case "shopKeeperCustomer":
                LOG.info("[CustomerViewBean] shopKeeperCustomer");
                selectShopKeeperCustomer();
                break;
            case "topSellerItem":
                LOG.info("[CustomerViewBean] topSellerItem");
                selectTopSellerItem();
                break;
            case "shopKeeperItem":
                LOG.info("[CustomerViewBean] shopKeeperItem");
                selectShopKeeperItem();
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
        
        LOG.info("[CustomerViewBean] length: " + showUserList.size());
    }
    
    public void selectTopSellerCustomer() {
        showUserList = dataBean.selectTopSellerCustomer();
    }
    
    public void selectShopKeeperCustomer() {
        showUserList = dataBean.selectShopKeeperCustomer();
    }
    
    public void selectTopSellerItem() {
        showUserList = dataBean.selectTopSellerItem();
    }
    
    public void selectShopKeeperItem() {
        showUserList = dataBean.selectShopKeeperItem();
    }
    
    // GETTER && SETTER
    
    /**
     * Get Value of showUserList
     * 
     * @return the value of showUserList
     */
    public List<ShowUser> getShowUserList() {
        return showUserList;
    }

    /**
     * Set Value of showUserList
     * 
     * @param showUserList the new value of showUserList
     */
    public void setShowUserList(List<ShowUser> showUserList) {
        this.showUserList = showUserList;
    }
    
}
