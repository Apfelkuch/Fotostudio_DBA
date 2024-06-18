package de.hsbi.fotostudio.modul;

import de.hsbi.fotostudio.util.DataBean;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The class Products is the connecting class between the Backing-Beans
 * and the data of the shop.
 * 
 * @version 0.1
 * @author Janis Wiegräbe
 */
@Named(value = "products")
@SessionScoped
public class Products implements Serializable {

    private static final Logger LOG = Logger.getLogger(Products.class.getName());
    
    private List<Produkt> currentProducts;
    private Produkt currentProduct;
    private boolean addNewItem = false;
    
    private List<Service> currentServices;
    private Service currentService;
    
    private Category currentCategory;
    
    @Inject
    private DataBean dataBean;
    
    /**
     * Creates a new instance of Products
     */
    public Products() {
    }
    
    /**
     * Initalization of the Products is called directly after the Konstruktor
     * Initalizes the variables and sets a default Category
     */
    @PostConstruct
    public void init() {
        currentProducts = new ArrayList<>();
        currentServices = new ArrayList<>();
        selectProductCategory(0);
        selectServiceCategory(0);
        currentProduct = new Produkt();
        currentService = new Service();
        currentCategory = null;
    }
    
    /**
     * This Methode updates the list currentProducts to only contain 
     * Products, which have the same category as the given categorieId
     * 
     * @param categorieId new id of the selected category
     */
    public void selectProductCategory(int categorieId) {
        currentCategory = dataBean.getProduct_category_list().get(categorieId);
        LOG.info("[Products] category of the currentProducts is updated, new Category is: " + currentCategory.getName());
        List<Produkt> immutableCopy = List.copyOf(dataBean.getProduct_list());
        currentProducts = null;
        if (categorieId == 0) {
            currentProducts = immutableCopy;
        } else {
            currentProducts = immutableCopy.stream()
                    .filter(product -> product.inCategory(currentCategory))
                    .collect(Collectors.toList());
        }
        if (currentProducts.isEmpty()) {
            LOG.info("[Products] No Elements in Category or Category does not exist");
            currentProducts = new ArrayList<>();
        }
    }
    
    /**
     * This Methode updates the list currentServices to only contain 
     * Services, which have the same category as the given categorieId
     * 
     * @param categorieId new id of the selected category
     */
    public void selectServiceCategory(int categorieId) {
        currentCategory = dataBean.getService_category_list().get(categorieId);
        LOG.info("[Products] category of the currentServices is updated, new Category is: " + currentCategory.getName());
        List<Service> immutableCopy = List.copyOf(dataBean.getService_list());
        currentServices = null;
        if (categorieId == 0) {
            currentServices = immutableCopy;
        } else {
            currentServices = immutableCopy.stream()
                    .filter(service -> service.inCategory(currentCategory))
                    .collect(Collectors.toList());
        }
        if (currentServices.isEmpty()) {
            LOG.info("[Products]  No Elements in Category or Category does not exist");
            currentServices = new ArrayList<>();
        }
    }

    /**
     * Updates a Product in ProductData and reselects the category to update
     * the changes in the ProductView
     * 
     * @param id the id of the Product which should be updated
     * @param product the Product with the new data
     * @return true if the Product is found and updated, otherwise false
     */
    public boolean updateProduct(int id, Produkt product) {
        if (id < 0 || id >= dataBean.getProduct_list().size()){
            return false;
        }
        
        // update Product daten in list (database)
        boolean returnValue = dataBean.updateProduct_list(id, product);
        
        return returnValue;
    }
    
    /**
     * Updates a Service in ProductData and reselects the category to update
     * the changes in the ServiceView
     * 
     * @param id the id of the Service which should be updated
     * @param service the Service with the new data
     * @return true if the Service is found and updated, otherwise false
     */
    public boolean updateService(int id, Service service) {
        if (id < 0 || id >= dataBean.getService_list().size()){
            return false;
        }
        
        // update Service daten in list (database)
        boolean returnValue = dataBean.updateService_list(id, service);
        
        return returnValue;
    }
    
    /**
     * This Methode finds the product category to a given id
     * 
     * @param id the id of the product category this Methode searchs for 
     * @return the product category to the given id
     */
    public Category findProductCategoryWithId(int id) {
        LOG.info("[Products] findProductCategoryWithId => id: " + id);
        for (Category k : dataBean.getProduct_category_list()) {
            if (k.getId() == id) {
                return k;
            }
        }
        return null;
    }
    
    /**
     * This Methode finds the service category to a given id
     * 
     * @param id the id of the service category this Methode searchs for 
     * @return the service category to the given id
     */
    public Category findServiceCategoryWithId(int id) {
        LOG.info("[Products] findServiceCategoryWithId => id: " + id);
        for (Category k : dataBean.getService_category_list()) {
            if (k.getId() == id) {
                return k;
            }
        }
        return null;
    }
        
    /**
     * This Methode finds the StorageStatus to a given id
     * 
     * @param id the id of the StorageStatus this Methode searchs for 
     * @return the StorageStatus to the given id
     */
    public StorageStatus findStorageStatusWithId(int id) {
        LOG.info("[Products] findStorageStatusWithId => id: " + id);
        for (StorageStatus l : dataBean.getStorageStatus_list()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    /**
     * This Methode finds the BillingType to a given id
     * 
     * @param id the id of the BillingType this Methode searchs for 
     * @return the BillingType to the given id
     */
    public BillingType findBillingTypeWithId(int id) {
        LOG.info("[Products] findBillingTypeWithId => id: " + id);
        for (BillingType b : dataBean.getBillingType_list()) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }
    
    /**
     * This method searches the product list for all products
     * that have the searched part in the name
     * 
     * @param namefragment the searched part of name
     * @return true if products where found, otherwise false
     */
    public boolean findProductWithNamefragment(String namefragment) {
        currentCategory = null;
        LOG.info("[Products] current Products contain in their title: " + namefragment.toLowerCase());
        List<Produkt> immutableCopy = List.copyOf(dataBean.getProduct_list());
        currentProducts = null;
        currentProducts = immutableCopy.stream()
                .filter(product -> product.getName().toLowerCase().contains(namefragment.toLowerCase()))
                .collect(Collectors.toList());
        if (currentProducts.isEmpty()) {
            LOG.info("[Products] No Elements with the given titlepart");
            currentProducts = new ArrayList<>();
            return false;
        }
        return true;
    }
    
    /**
     * This method searches the service list for all services
     * that have the searched part in the name
     * 
     * @param namefragment the searched part of name
     * @return true if products where found, otherwise false
     */
    public boolean findServiceWithNamefragment(String namefragment) {
        currentCategory = null;
        LOG.info("[Products] current Services contain in their title: " + namefragment.toLowerCase());
        List<Service> immutableCopy = List.copyOf(dataBean.getService_list());
        currentServices = null;
        currentServices = immutableCopy.stream()
                .filter(service -> service.getName().toLowerCase().contains(namefragment.toLowerCase()))
                .collect(Collectors.toList());
        if (currentServices.isEmpty()) {
            LOG.info("[Products] No Elements with the given titlepart");
            currentServices = new ArrayList<>();
            return false;
        }
        return true;
    }
    
    // GETTER && SETTER

    /**
     * Get Value of currentProducts
     * 
     * @return the value of currentProducts
     */
    public List<Produkt> getCurrentProducts() {
        return currentProducts;
    }

    /**
     * Get Value of currentServices
     * 
     * @return the value of currentServices
     */
    public List<Service> getCurrentServices() {
        return currentServices;
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
        LOG.info("[Products] current Product: " + currentProduct.getName());
        this.currentProduct = currentProduct;
    }

    /**
     * Get Value of currentService
     * 
     * @return the value of currentService
     */
    public Service getCurrentService() {
        return currentService;
    }

    /**
     * Set Value of currentService
     * 
     * @param currentService the new value of currentService
     */
    public void setCurrentService(Service currentService) {
        LOG.info("[Products] current Service: " + currentService.getName());
        this.currentService = currentService;
    }

    /**
     * Get Value of currentCategory
     * 
     * @return the value of currentCategory
     */
    public Category getCurrentCategory() {
        return currentCategory;
    }

    /**
     * Get Value of addNewItem
     * 
     * @return the value of addNewItem
     */
    public boolean isAddNewItem() {
        return addNewItem;
    }

    /**
     * Get Value of addNewItem
     * 
     * @param addNewItem the new value of addNewItem
     */
    public void setAddNewItem(boolean addNewItem) {
        this.addNewItem = addNewItem;
    }
    
    
}
