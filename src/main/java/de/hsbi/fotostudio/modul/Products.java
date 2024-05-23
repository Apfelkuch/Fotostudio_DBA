package de.hsbi.fotostudio.modul;

import de.hsbi.fotostudio.util.ProductData;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
@ApplicationScoped
public class Products {

    private static final Logger LOG = Logger.getLogger(Products.class.getName());
    
    private List<Product> currentProducts;
    private Product currentProduct;
    
    private List<Service> currentServices;
    private Service currentService;
    
    private Category currentCategory;
    
    @Inject
    private ProductData productData;
    
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
        currentProduct = new Product();
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
        currentCategory = productData.getProduct_category_list().get(categorieId);
        LOG.info("[Products] category of the currentProducts is updated, new Category is: " + currentCategory.getName());
        List<Product> immutableCopy = List.copyOf(productData.getProduct_list());
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
        currentCategory = productData.getService_category_list().get(categorieId);
        LOG.info("[Products] category of the currentServices is updated, new Category is: " + currentCategory.getName());
        List<Service> immutableCopy = List.copyOf(productData.getService_list());
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
    public boolean updateProdukt(int id, Product product) {
        if (id < 0 || id >= productData.getProduct_list().size()){
            return false;
        }
        
        // update Product daten in list (database)
        boolean returnValue = productData.updateProduct_list(id, product);
        
        // set Category to reload the product list
        selectProductCategory(currentProduct.getCategory().getId());
        
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
        if (id < 0 || id >= productData.getService_list().size()){
            return false;
        }
        
        // update Service daten in list (database)
        boolean returnValue = productData.updateService_list(id, service);
        
        // set Category to reload the product list
        selectServiceCategory(currentService.getCategory().getId());
        
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
        for (Category k : productData.getProduct_category_list()) {
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
        for (Category k : productData.getService_category_list()) {
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
        LOG.info("[Products] billingType => id: " + id);
        for (StorageStatus l : productData.getStorageStatus_list()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }
    
    /**
     * This method searches the product list for all products
     * that have the searched part in the name
     * 
     * @param namefragment the searched part of name
     */
    public boolean findProductWithNamefragment(String namefragment) {
        currentCategory = null;
        LOG.info("[Products] current Products contain in their title: " + namefragment);
        List<Product> immutableCopy = List.copyOf(productData.getProduct_list());
        currentProducts = null;
        currentProducts = immutableCopy.stream()
                .filter(product -> product.getName().contains(namefragment))
                .collect(Collectors.toList());
        if (currentProducts.isEmpty()) {
            LOG.info("[Products]  No Elements with the given titlepart");
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
     */
    public boolean findServiceWithNamefragment(String namefragment) {
        currentCategory = null;
        LOG.info("[Products] current Services contain in their title: " + namefragment);
        List<Service> immutableCopy = List.copyOf(productData.getService_list());
        currentServices = null;
        currentServices = immutableCopy.stream()
                .filter(service -> service.getName().contains(namefragment))
                .collect(Collectors.toList());
        if (currentServices.isEmpty()) {
            LOG.info("[Products]  No Elements with the given titlepart");
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
    public List<Product> getCurrentProducts() {
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
    public Product getCurrentProduct() {
        return currentProduct;
    }

    /**
     * Set Value of currentProduct
     * 
     * @param currentProduct the new value of currentProduct
     */
    public void setCurrentProduct(Product currentProduct) {
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
        LOG.info("[Products] current Product: " + currentService.getName());
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
}
