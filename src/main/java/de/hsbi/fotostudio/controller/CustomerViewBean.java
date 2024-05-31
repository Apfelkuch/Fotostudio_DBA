package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.User;
import de.hsbi.fotostudio.util.LoginHandler;
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
    
    private List<User> userList;
    
    @Inject
    private LoginHandler loginHandler;

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
        selectTopSeller();
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
            case "topSeller":
                selectTopSeller();
                break;
            case "shopKeeper":
                selectTopSeller();
                break;
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
        
        LOG.info("[CustomerViewBean] length: " + userList.size());
    }
    
    public void selectTopSeller() {
        userList = loginHandler.selectTopSeller();
    }
    
    public void selectShopKeeper() {
        userList = loginHandler.selectShopKeeper();
    }
    
    // GETTER && SETTER
    
    /**
     * Get Value of userList
     * 
     * @return the value of userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Set Value of userList
     * 
     * @param userList the new value of userList
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
}