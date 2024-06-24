package de.hsbi.fotostudio.controller;

import de.hsbi.fotostudio.modul.BillingType;
import de.hsbi.fotostudio.modul.Category;
import de.hsbi.fotostudio.modul.StorageStatus;
import de.hsbi.fotostudio.modul.Produkt;
import de.hsbi.fotostudio.modul.Products;
import de.hsbi.fotostudio.util.DataBean;
import de.hsbi.fotostudio.util.Util;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.inject.Inject;
import java.util.List;
import java.util.logging.Logger;
import org.primefaces.PrimeFaces;

/**
 * The class ProductDialogBean is the Backing-Bean for the ProductDialog.xhtml page.
 * This class links the xhtml page and the underlying logic.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "productDialogBean")
@ViewScoped
public class ProductDialogBean implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(ProductDialogBean.class.getName());
    
    private String currentCategoryName;
    private List<Category> category_list;
    
    private String currentBillingTypeName;
    private BillingType currentBillingType;
    private List<BillingType> billingType_list;
    
    private String currentStorageStatusName;
    private StorageStatus currentStorageStatus;
    private List<StorageStatus> storageStatus_list;
    
    private Produkt currentProduct;
    
    @Inject
    private Products products;
    
    @Inject
    private DataBean dataBean;
    
    /**
     * Creates new instance of ProduktDialogBean
     */
    public ProductDialogBean() {
    }
    
    /**
     * Initalization of the ProductDialogBean is called directly after the
     * Konstruktor Initalizes the variables
     */
    @PostConstruct
    public void init() {
        category_list = dataBean.getProduct_category_list();
        billingType_list = dataBean.getBillingType_list();
        storageStatus_list = dataBean.getStorageStatus_list();
        currentProduct = new Produkt();
    }

    /**
     * Methode to save a Product to the products - Class.
     * When the Class is called the Product is saved to the product-Class.
     * After the saving of the Product the ProductView is updated.
     */
    public void saveEdits() {
        LOG.info("[ProductDialogBean] save: " + currentProduct.toString());
        FacesMessage message;
        if (!products.isAddNewItem()) {
            currentProduct.setPId(products.getCurrentProduct().getPId());
            currentProduct.setDateipfad(products.getCurrentProduct().getDateipfad());
            products.updateProduct(currentProduct.getPId(), currentProduct);
            // set Category to reload the product list
            products.selectProductCategory(currentProduct.getKategorie().getId());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produkt gespeichert", "");
            PrimeFaces.current().ajax().update("form-product-view:data-view");
        } else {
            if(!currentProduct.getName().isBlank()) {
                Produkt p = dataBean.addProduct_list(currentProduct);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produkt hinzugefügt", p.toString());
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kein Produkt hinzugefügt", "Ein Produkt braucht mindestens einen Namen");
            }
        }
        PrimeFaces.current().ajax().update("form-product-dialog");
        PrimeFaces.current().executeScript("PF('pD').hide()");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * The ValueChangeListener-Methode to change the Category.
     *
     * @param event the ValueChangeEvent contains Information about the state
     * befor and after the ValueChange
     */
    public void getCategory(ValueChangeEvent event) {
        LOG.info("[ProductDialogBean] getKategorie");
        Category newCategory = (Category) event.getNewValue();
        
        for (Category k : category_list) {
            if (k.getName().equals(newCategory.getName())) {
                currentProduct.setKategorie(k);
                break;
            }
        }
        if (currentProduct.getKategorie() != null) {
            LOG.info("[ProductDialogBean] selected category: "
                    + currentProduct.getKategorie().getName());
//            FacesContext.getCurrentInstance().
//                    addMessage(null,new FacesMessage("Category gültig!"));
        }
    }
    
    /**
     * The ValueChangeListener-Methode to change the BillingType.
     *
     * @param event the ValueChangeEvent contains Information about the state
     * befor and after the ValueChange
     */
    public void getBillingType(ValueChangeEvent event) {
        LOG.info("[ServiceDialogBean] getBillingType");
        
        BillingType newBillingType = (BillingType) event.getNewValue();
        
        for (BillingType b : billingType_list) {
            if (b.getName().equals(newBillingType.getName())) {
                currentProduct.setAbrechnungsart(b);
                break;
            }
        }
        if (currentProduct.getAbrechnungsart() != null) {
            LOG.info("[ServiceDialogBean] selected billingType: "
                    + currentProduct.getAbrechnungsart().getName());
        }
    }
    
    
    /**
     * The ValueChangeListener-Methode to change the StorageStatus.
     *
     * @param event the ValueChangeEvent contains Information about the state
     * befor and after the ValueChange
     */
    public void getStorageStatus(ValueChangeEvent event) {
        LOG.info("[ProductDialogBean] StorageStatus");
        StorageStatus newStorageStatus = (StorageStatus) event.getNewValue();
        
        for (StorageStatus l : storageStatus_list) {
            if (l.getName().equals(newStorageStatus.getName())) {
                currentProduct.setLagerstatus(l);
                break;
            }
        }
        if (currentStorageStatus != null) {
            LOG.info("[ProductDialogBean] selected StorageStatus");
//            FacesContext.getCurrentInstance().
//                    addMessage(null,new FacesMessage("StorageStatus gültig!"));
        }
    }
    
    
    /**
     * Methode to initalise the Product Dialog by updating the
     * "currentProduct"-Variable.
     * 
     * @return the id of the "currentProduct"-Variable
     */
    public int getHeader() {
        this.currentProduct = products.getCurrentProduct();
        LOG.info("[ProductDialogBean] load: " + currentProduct.getPId());
        
        return currentProduct.getPId();
    }
    
    /**
     * Returns true if the logged in user is admin or developer
     * 
     * @return true if logged in user is admin or developer
     */
    public boolean isEditing() {
        return Util.getUserRole() >= 1;
    }
        
    // GETTER && SETTER

    /**
     * Get Value of products
     * 
     * @return the value of products
     */
    public Products getProducts() {
        return products;
    }
    
    /**
     * Set Value of products
     * 
     * @param products the new value of products
     */
    public void setProducts(Products products) {
        this.products = products;
    }

    /**
     * Get Value of category_list
     * 
     * @return the value of category_list
     */
    public List<Category> getCategory_list() {
        return category_list;
    }

    /**
     * Set Value of category_list
     * 
     * @param category_list the new value of category_list
     */
    public void setCategory_list(List<Category> category_list) {
        this.category_list = category_list;
    }

    /**
     * Get Value of currentBillingTypeName
     * 
     * @return the value of currentBillingTypeName
     */
    public String getCurrentBillingTypeName() {
        return currentBillingTypeName;
    }

    /**
     * Set Value of currentBillingTypeName
     * 
     * @param currentBillingTypeName the new value of currentBillingTypeName
     */
    public void setCurrentBillingTypeName(String currentBillingTypeName) {
        this.currentBillingTypeName = currentBillingTypeName;
    }

    /**
     * Get Value of currentBillingType
     * 
     * @return the value of currentBillingType
     */
    public BillingType getCurrentBillingType() {
        return currentBillingType;
    }

    /**
     * Set Value of currentBillingType
     * 
     * @param currentBillingType the new value of currentBillingType
     */
    public void setCurrentBillingType(BillingType currentBillingType) {
        this.currentBillingType = currentBillingType;
    }

    /**
     * Get Value of billingType_list
     * 
     * @return the value of billingType_list
     */
    public List<BillingType> getBillingType_list() {
        return billingType_list;
    }

    /**
     * Set Value of billingType_list
     * 
     * @param billingType_list the new value of billingType_list
     */
    public void setBillingType_list(List<BillingType> billingType_list) {
        this.billingType_list = billingType_list;
    }

    /**
     * Get Value of currentStorageStatus
     * 
     * @return the value of currentStorageStatus
     */
    public StorageStatus getCurrentStorageStatus() {
        return currentStorageStatus;
    }

    /**
     * Set Value of currentStorageStatus
     * 
     * @param currentStorageStatus the new value of currentStorageStatus
     */
    public void setCurrentStorageStatus(StorageStatus currentStorageStatus) {
        this.currentStorageStatus = currentStorageStatus;
    }

    /**
     * Get Value of storageStatus_list
     * 
     * @return the value of storageStatus_list
     */
    public List<StorageStatus> getStorageStatus_list() {
        return storageStatus_list;
    }

    /**
     * Set Value of storageStatus_list
     * 
     * @param storageStatus_list the new value of storageStatus_list
     */
    public void setStorageStatus_list(List<StorageStatus> storageStatus_list) {
        this.storageStatus_list = storageStatus_list;
    }

    /**
     * Get Value of currentCategoryName
     * 
     * @return the value of currentCategoryName
     */
    public String getCurrentCategoryName() {
        return currentCategoryName;
    }

    /**
     * Set Value of currentCategoryName
     * 
     * @param currentCategoryName the new value of currentCategoryName
     */
    public void setCurrentCategoryName(String currentCategoryName) {
        this.currentCategoryName = currentCategoryName;
    }

    /**
     * Get Value of currentStorageStatusName
     * 
     * @return the value of currentStorageStatusName
     */
    public String getCurrentStorageStatusName() {
        return currentStorageStatusName;
    }

    /**
     * Set Value of currentStorageStatusName
     * 
     * @param currentStorageStatusName the new value of currentStorageStatusName
     */
    public void setCurrentStorageStatusName(String currentStorageStatusName) {
        this.currentStorageStatusName = currentStorageStatusName;
    }

    /**
     * Get Value of currentProduct
     * 
     * @return the value of currentProduct
     */
    public Produkt getCurrentProduct() {
        return currentProduct;
    }

    /**
     * Set Value of currentProduct
     * 
     * @param currentProduct the new value of currentProduct
     */
    public void setCurrentProduct(Produkt currentProduct) {
        this.currentProduct = currentProduct;
    }
    
}
